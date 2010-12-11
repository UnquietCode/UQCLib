/*
 * Copyright $YEAR$ Benjamin Fagin
 *
 * This work is licensed under the Common Development and Distribution License (CDDL), Version 1.0
 *
 * Read the included LICENSE.TXT for more information.
 *
 * --------------------------------------------------------------------
 */

package unquietcode.numbers;

public class ShinyNumber {
	private final int value;
	
	public ShinyNumber(int number) {
		value = number;
	}
	
	public Ordinal getOrdinal() {
		return getOrdinal(false);
	}
	
	public Ordinal getOrdinal(boolean displayLong) {
		return new Ordinal(value, displayLong);
	}
	
	public String toOrdinal() {
		return toOrdinal(false);
	}
	
	public String toOrdinal(boolean displayLong) {
		return Ordinal.toOrdinal(value, displayLong);
	}
	
	public Cardinal getCardinal() {
		return new Cardinal(value);
	}
	
	public String toCardinal() {
		return Cardinal.toCardinal(value);
	}

	public int getValue() {
		return value;
	}

	public @Override String toString() {
		return Integer.toString(value);
	}
}