package dev.prefex.reforested.util;

public final class StringExtensions {
	public static String capitalizeFirstLetter(String input) {
		if (input == null || input.isEmpty()) {
			return input;
		}

		String[] words = input.split("\\s+");
		StringBuilder sb = new StringBuilder();

		for (String word : words) {
			if (!word.isEmpty()) {
				sb.append(Character.toUpperCase(word.charAt(0)));
				sb.append(word.substring(1));
				sb.append(" ");
			}
		}

		return sb.toString().trim();
	}
}
