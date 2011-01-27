/*
 * Copyright $YEAR$ Benjamin Fagin
 *
 * This work is licensed under the Common Development and Distribution License (CDDL), Version 1.0
 *
 * Read the included LICENSE.TXT for more information.
 *
 * --------------------------------------------------------------------
 */

package unquietcode.util;

/**
 * @author Ben
 * @version 1.0
 *          Date: Nov 28, 2010
 */
public class StringUtils {
	private StringUtils() { }

	public static String blanks(int total) {
		StringBuilder sb = new StringBuilder();

		for (int i=0; i < total; ++i) {
			sb.append(" ");
		}

		return sb.toString();
	}


	public static boolean startsWithAny(String string, String...prefixes) {
		for (String prefix : prefixes)
			if (string.startsWith(prefix))
				return true;

		return false;
	}

	public static boolean startsWithAnyCI(String string, String...prefixes) {
		string = string.toLowerCase();

		for (String prefix : prefixes)
			if (string.startsWith(prefix.toLowerCase()))
				return true;

		return false;
	}

	public static boolean endsWithAny(String string, String...suffixes) {
		for (String suffix : suffixes)
			if (string.endsWith(suffix))
				return true;

		return false;
	}

	public static boolean endsWithAnyCI(String string, String...suffixes) {
		string = string.toLowerCase();

		for (String suffix : suffixes)
			if (string.endsWith(suffix.toLowerCase()))
				return true;

		return false;
	}

	public static boolean equalsAny(String string, String...others) {
		for (String other : others)
			if (string.equals(other))
				return true;

		return false;
	}

	public static boolean equalsAnyCI(String string, String...others) {
		string = string.toLowerCase();

		for (String other : others)
			if (string.equals(other.toLowerCase()))
				return true;

		return false;
	}

	public static boolean hasText(String string) {
		return string != null  &&  !string.trim().isEmpty();
	}
}

