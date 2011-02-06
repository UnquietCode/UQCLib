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

		out();
		
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

	@Test
	public void specials() {
		for (int i : new int[]{100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000}) {
			String cardinal = Cardinal.toCardinal(i);
			out(i + "\t" + sqt(cardinal));
		}
	}

}
