package dev.prefex.reforested.datagen;

import dev.prefex.reforested.Reforested;
import dev.prefex.reforested.util.StringExtensions;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import java.nio.file.Path;

public class ReforestedEnglishLangProvider extends FabricLanguageProvider {
	protected ReforestedEnglishLangProvider(FabricDataOutput dataOutput) {
		super(dataOutput, "en_us");
	}

	@Override
	public void generateTranslations(TranslationBuilder translationBuilder) {
		for (var item : Reforested.GROUP_ITEMS) {
			translationBuilder.add(item.getItem(),
					StringExtensions.capitalizeFirstLetter(
							item.getTranslationKey()
									.replace("item.reforested.", "")
									.replace("block.reforested.", "")
									.replace("_", " ")
					)
			);
		}

		// Load an existing language file.
		try {
			Path existingFilePath = this.dataOutput.getModContainer().findPath("assets/reforested/lang/en_us.template.json").get();
			translationBuilder.add(existingFilePath);
		} catch (Exception e) {
			throw new RuntimeException("Failed to add existing language file!", e);
		}
	}
}
