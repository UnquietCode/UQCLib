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

import java.util.Random;

import unquietcode.numbers.NumberTools;
import unquietcode.util.StringUtils;

import static unquietcode.util.Shortcuts.*;

/**
 * @author Ben
 * @version 1.0
 *          Date: Nov 28, 2010
 */
public class ToolsTest {

	@Test
	public void randomSplits() {
		Random gen = new Random();

		for (int i=0; i < 10; ++i) {
			int num = gen.nextInt(10000);
			int arr[] = NumberTools.splitDigits(num);

			outN(num + StringUtils.blanks(9 - arr.length));
			for (int x : arr)
				outN(x + " ");
			out();
		}
	}

	@Test
	public void commaAdding() {
		//15 random large numbers
		Random gen = new Random();

		for (int i=0; i < 15; ++i) {
			int num = gen.nextInt(10000000) + 8000;
			outN(num + "\t");
			out(NumberTools.addCommas(num));
		}
	}

	@Test
	public void negativeCommaAdding() {
		//10 random large numbers
		Random gen = new Random();

		for (int i=0; i < 10; ++i) {
			int num = gen.nextInt(10000000) + 8000;
			num *= -1;
			outN(num + "\t");
			out(NumberTools.addCommas(num));
		}
	}

	@Test
	public void specialCommaTest() {
		//special number cases
		int numbers[] = {0, 1, 5, 10, 11, 100, 101, 1000, 1001, 1000000, 10000000};

		for (int number : numbers) {
			outN(number + "\t");
			out(NumberTools.addCommas(number));
		}
	}

	@Test
	public void percentages() {
		//15 random values
		for (int i=0; i < 15; ++i) {
			Random gen = new Random();
			double value = gen.nextDouble()*15;
			int places = gen.nextInt(6);
			out(value + "\t"+ places +" places\t" + NumberTools.percentage(value, places));
		}
	}

	@Test
	public void percentagesSpecial() {
		for (double value : new double[] {0d, 1.0000000000001}) {
			Random gen = new Random();
			int places = gen.nextInt(10);
			out(value + "\t"+ places +" places\t" + NumberTools.percentage(value, places));
		}
	}

	@Test
	public void percentagesNegatives() {
		//15 random values
		for (int i=0; i < 15; ++i) {
			Random gen = new Random();
			double value = gen.nextDouble()*-1000;
			int places = gen.nextInt(6);
			out(value + "\t"+ places +" places\t" + NumberTools.percentage(value, places));
		}
	}
}
