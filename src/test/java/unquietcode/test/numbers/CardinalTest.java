/*
 * Copyright $YEAR$ Benjamin Fagin
 *
 * This work is licensed under the Common Development and Distribution License (CDDL), Version 1.0
 *
 * Read the included LICENSE.TXT for more information.
 *
 * --------------------------------------------------------------------
 */

package unquietcode.test.numbers;

import org.junit.Test;
import unquietcode.numbers.Cardinal;
import static unquietcode.util.Shortcuts.*;


import java.util.Random;

/**
 * @author Ben
 * @version 1.0
 *          Date: Nov 28, 2010
 */
public class CardinalTest {

	@Test
	public void randomCardinal() {
		Random gen = new Random();
		
		//10 random ints		
		for (int i=0; i < 10; ++i) {
			int x = gen.nextInt(1000);
			String cardinal = Cardinal.toCardinal(x);
			out(x + "\t" + sqt(cardinal));
		}
		
		//10 random doubles
		for (int i=0; i < 10; ++i) {
			double x = gen.nextDouble() * 100;
			String cardinal = Cardinal.toCardinal(x);
			out(x + "\t" + sqt(cardinal));
		}
	}

	@Test
	public void from0to101() {
		for (int i=0; i <= 101; ++i) {
			String cardinal = Cardinal.toCardinal(i);
			out(i + "\t" + sqt(cardinal));
		}
	}

	@Test
	public void from100to201() {
		for (int i=100; i <= 201; ++i) {
			String cardinal = Cardinal.toCardinal(i);
			out(i + "\t" + sqt(cardinal));
		}
	}

	@Test
	public void from990to1110() {
		for (int i=990; i <= 1110; ++i) {
			String cardinal = Cardinal.toCardinal(i);
			out(i + "\t" + sqt(cardinal));
		}
	}


}
