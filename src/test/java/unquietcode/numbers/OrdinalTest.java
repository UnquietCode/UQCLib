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
