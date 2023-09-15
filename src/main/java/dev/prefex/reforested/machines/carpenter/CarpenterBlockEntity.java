package dev.prefex.reforested.machines.carpenter;

import dev.prefex.reforested.items.ModItems;
import dev.prefex.reforested.machines.ModMachines;
import dev.prefex.yokai.fluid.FluidStack;
import dev.prefex.yokai.helpers.DefaultedListCollector;
import dev.prefex.yokai.helpers.WrappedDelegate;
import dev.prefex.yokai.machine.MachineBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

import static dev.prefex.reforested.Reforested.id;

public class CarpenterBlockEntity extends MachineBlockEntity {
	public static final int INVENTORY_SIZE = 14;
	public static final int PROPERTY_SIZE = 2;
	public static final int Y_OFFSET = 86;

	int processTime;
	int maxProcessTime;
	public final PropertyDelegate properties;
	private final RecipeManager.MatchGetter<Inventory, ? extends CarpenterRecipe> matchGetter;

	public static final CarpenterRecipe testRecipe = new CarpenterRecipe(id("test"),FluidStack.EMPTY, Stream.of(
			Items.AIR, Items.AIR, Items.AIR,
			Items.DIAMOND, ModItems.STURDY_CASING, Items.DIAMOND,
			Items.AIR, Items.AIR, Items.AIR
	).map(Ingredient::ofItems).collect(DefaultedListCollector.toList()), Ingredient.ofItems(ModItems.STURDY_CASING), new ItemStack(ModItems.HARDENED_CASING), 3, 3);

    public CarpenterBlockEntity(BlockPos pos, BlockState state) {
        super(ModMachines.CARPENTER_BLOCK_ENTITY, pos, state, "carpenter", INVENTORY_SIZE);
		this.properties = WrappedDelegate.create(PROPERTY_SIZE, index -> switch (index) {
			case 0 -> CarpenterBlockEntity.this.processTime;
			case 1 -> CarpenterBlockEntity.this.maxProcessTime;
			default -> 0;
		}, pair -> {
			switch (pair.getLeft()) {
				case 0 -> CarpenterBlockEntity.this.processTime = pair.getRight();
				case 1 -> CarpenterBlockEntity.this.maxProcessTime = pair.getRight();
			}
		});
		this.matchGetter = RecipeManager.createCachedMatchGetter(CarpenterRecipe.Type.INSTANCE);
    }

	public static void tick(World world, BlockPos blockPos, BlockState blockState, CarpenterBlockEntity self) {
		CarpenterRecipe recipe = self.matchGetter.getFirstMatch(self, world).orElse(null);

		self.maxProcessTime = 50;

		if (recipe != null) {
			self.inventory.set(10, recipe.getResult(world.getRegistryManager())[0].copy());

			if (self.processTime < self.maxProcessTime) {
				self.processTime++;
			} else {
				self.processTime = 0;
				//self.maxProcessTime = 0;
				self.inventory.set(11, recipe.getResult(world.getRegistryManager())[0].copy());
			}
		} else {
			self.inventory.set(10, ItemStack.EMPTY);
			self.processTime = 0;
			//self.maxProcessTime = 0;
		}
	}

	@Override
	protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
		return new CarpenterScreenHandler(syncId, playerInventory, this, this.properties);
	}

	/**
	 * Writes additional server -&gt; client screen opening data to the buffer.
	 *
	 * @param player the player that is opening the screen
	 * @param buf	the packet buffer
	 */
	@Override
	public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
		buf.writeBlockPos(pos);
	}

	@Override
	public void onInventoryChanged(int slot, ItemStack stack) {

	}

	@Override
	public boolean isValid(int slot, ItemStack stack) {
		return super.isValid(slot, stack);
	}

	@Override
	public int[] getAvailableSlots(Direction side) {
		return new int[1];
	}

	@Override
	public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
		return true;
	}

	@Override
	public boolean canExtract(int slot, ItemStack stack, Direction dir) {
		return true;
	}
}