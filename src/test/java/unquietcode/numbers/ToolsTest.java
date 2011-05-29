/*******************************************************************************
 Copyright 2009-2011 Benjamin Fagin

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.


 Read the included LICENSE.TXT for more information.
 ******************************************************************************/

package unquietcode.numbers;

import org.junit.Test;

import java.util.Random;

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
}
