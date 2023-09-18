package dev.prefex.reforested.machines.carpenter;

import dev.prefex.reforested.machines.ModMachines;
import dev.prefex.yokai.helpers.DefaultedListCollector;
import dev.prefex.yokai.helpers.WrappedDelegate;
import dev.prefex.yokai.machine.MachineBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import team.reborn.energy.api.base.SimpleEnergyStorage;

import static dev.prefex.reforested.Reforested.id;

public class CarpenterBlockEntity extends MachineBlockEntity {
	public static final int INVENTORY_SIZE = 14;
	public static final int PROPERTY_SIZE = 4;
	public static final int Y_OFFSET = 86;

	int processTime;
	int maxProcessTime;
	public final PropertyDelegate properties;
	private final RecipeManager.MatchGetter<Inventory, ? extends CarpenterRecipe> matchGetter;

	public final SimpleEnergyStorage energyStorage = new SimpleEnergyStorage(4000, 256, 0);

    public CarpenterBlockEntity(BlockPos pos, BlockState state) {
        super(ModMachines.CARPENTER_BLOCK_ENTITY, id("carpenter"), pos, state, INVENTORY_SIZE);
		this.properties = WrappedDelegate.create(PROPERTY_SIZE, index -> switch (index) {
			case 0 -> CarpenterBlockEntity.this.processTime;
			case 1 -> CarpenterBlockEntity.this.maxProcessTime;
			case 2 -> (int) CarpenterBlockEntity.this.energyStorage.amount;
			case 3 -> (int) CarpenterBlockEntity.this.energyStorage.capacity;
			default -> 0;
		}, pair -> {
			switch (pair.getLeft()) {
				case 0 -> CarpenterBlockEntity.this.processTime = pair.getRight();
				case 1 -> CarpenterBlockEntity.this.maxProcessTime = pair.getRight();
				case 2 -> CarpenterBlockEntity.this.energyStorage.amount = pair.getRight();
				case 3 -> throw new UnsupportedOperationException("Energy storage is immutable!");
			}
		});
		this.matchGetter = RecipeManager.createCachedMatchGetter(CarpenterRecipe.Type.INSTANCE);
    }

	public static void tick(World world, BlockPos blockPos, BlockState blockState, CarpenterBlockEntity self) {
		CarpenterRecipe recipe = self.matchGetter.getFirstMatch(self, world).orElse(null);

		self.maxProcessTime = 50;

		if (recipe != null && !isRecipeEmpty(self.inventory)) {
			self.inventory.set(10, recipe.getResult(world.getRegistryManager())[0].copy());

			if (self.energyStorage.amount >= 9){
				self.energyStorage.amount -= 9;

				if (self.processTime < self.maxProcessTime) {
					self.processTime++;
				} else {
					self.processTime = 0;
					craftRecipe(world.getRegistryManager(), recipe, self.inventory, self.getMaxCountPerStack());
				}
			}
		} else {
			self.inventory.set(10, ItemStack.EMPTY);
			self.processTime = 0;
		}
	}

	private static boolean isRecipeEmpty(DefaultedList<ItemStack> slots) {
		return slots.stream().limit(9).allMatch(ItemStack::isEmpty); // 10th slot is optional
	}

	private static DefaultedList<ItemStack> getCraftingSlots(DefaultedList<ItemStack> slots) {
		return slots.stream().limit(10).collect(DefaultedListCollector.toList());
	}

	private static boolean craftRecipe(DynamicRegistryManager registryManager, @Nullable CarpenterRecipe recipe, DefaultedList<ItemStack> slots, int maxCount) {
		if (recipe != null && canAcceptRecipeOutput(registryManager, recipe, slots, maxCount)) {
			DefaultedList<ItemStack> ingredients = getCraftingSlots(slots);
			ItemStack result = recipe.getResult(registryManager)[0];
			ItemStack resultSlot = slots.get(11);
			if (resultSlot.isEmpty()) {
				slots.set(11, result.copy());
			} else if (resultSlot.isOf(result.getItem())) {
				resultSlot.increment(result.getCount());
			}

			ingredients.forEach(stack -> stack.decrement(1));
			return true;
		} else {
			return false;
		}
	}

	private static boolean canAcceptRecipeOutput(DynamicRegistryManager registryManager, @Nullable CarpenterRecipe recipe, DefaultedList<ItemStack> slots, int maxCount) {
		if (!isRecipeEmpty(slots) && recipe != null) {
			ItemStack recipeResult = recipe.getResult(registryManager)[0];
			if (recipeResult.isEmpty()) {
				return false;
			} else {
				ItemStack resultSlot = slots.get(11);
				if (resultSlot.isEmpty()) {
					return true;
				} else if (!ItemStack.areItemsEqual(resultSlot, recipeResult)) {
					return false;
				} else if (resultSlot.getCount() < maxCount && resultSlot.getCount() < resultSlot.getMaxCount()) {
					return true;
				} else {
					return resultSlot.getCount() < recipeResult.getMaxCount();
				}
			}
		} else {
			return false;
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