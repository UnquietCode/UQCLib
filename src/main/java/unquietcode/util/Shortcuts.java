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
public final class Shortcuts {

	/**
	 * Alias for System.exit(code), reminding us all of php.
	 * Remember: your program will end here rather than the line where this method is invoked.
	 *
	 * @param code  exit code
	 */
	public static void die(int code) {
		System.exit(code);
	}

	/**
	 * Prints to System.out using println.
	 *
	 * @param object    object to print
	 */
	public static <T> void out(T object) {
		System.out.println(object);
	}

	/**
	 * Prints a blank line to System.out
	 */
	public static void out() {
		System.out.println();
	}

	/**
	 * Prints to System.out using print
	 *
	 * @param object    object to print
	 */
	public static <T> void outN(T object) {
		System.out.print(object);
	}

	/**
	 * Prints to System.err using println.
	 *
	 * @param object    object to print
	 */
	public static <T> void err(T object) {
		System.err.println(object);
	}

	/**
	 * Prints a blank line to System.err
	 */
	public static void err() {
		System.err.println();
	}

	/**
	 * Prints to System.err using print
	 *
	 * @param object    object to print
	 */
	public static <T> void errN(T object) {
		System.err.print(object);
	}

	/**
	 * Takes "string" and returns "'string'". Does not escape the string.
	 *
	 * @param string    String to be wrapped
	 * @return          'string'
	 */
	public static String sqt(String string) {
		return "'" + string + "'";
	}

	/**
	 * Takes "string" and returns ""string"". Does not escape the string.
	 *
	 * @param string    String to be wrapped
	 * @return          "string"
	 */
	public static String dqt(String string) {
		return "\"" + string + "\"";
	}
}
