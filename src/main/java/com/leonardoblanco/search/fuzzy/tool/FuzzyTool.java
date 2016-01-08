package com.leonardoblanco.search.fuzzy.tool;

import java.text.Normalizer;

/**
 * Some utilities for the Fuzzy Search
 * @author Leonardo Blanco
 *
 */
public class FuzzyTool {

	/**
	 * Get the max possible score for the text
	 * @param string to analyze the max score
	 * @return possible max score
	 */
	public static int getMaxFuzzyScore(String str) {

		int totalLength = str.length();
		int totalMinus = totalLength - 1;

		return (totalMinus * 2) + totalLength;

	}

	/**
	 * Remove accents from some languages
	 * @param string to remove accents
	 * @return the same string without accents
	 */
	public static String removeAccents(String str) {
		return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}
}
