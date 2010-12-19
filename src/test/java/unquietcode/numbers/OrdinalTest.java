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

import org.junit.Test;

import java.util.Random;

import static unquietcode.util.Shortcuts.*;

/**
 * @author Ben
 * @version 1.0
 *          Date: Nov 28, 2010
 */
public class OrdinalTest {

	@Test
	public void randomOrdinal() {
		Random gen = new Random();

		//10 random ints
		for (int i=0; i < 10; ++i) {
			int x = gen.nextInt(1000);
			out(x + "\t" + sqt(Ordinal.toOrdinal(x)) + "\t" + sqt(Ordinal.toOrdinal(x, true)));
		}
	}

	@Test
	public void largeRandomOrdinal() {
		Random gen = new Random();

		//10 random ints
		for (int i=0; i < 10; ++i) {
			int x = gen.nextInt(9900) + 1000;
			out(x + "\t" + sqt(Ordinal.toOrdinal(x)) + "\t" + sqt(Ordinal.toOrdinal(x, true)));
		}
	}

}
