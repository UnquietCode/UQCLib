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

import static junit.framework.Assert.*;
import static unquietcode.util.Shortcuts.*;


import java.util.Random;

/**
 * @author  Benjamin Fagin
 * @version Nov 28, 2010
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
		StringBuilder sb = new StringBuilder();

		for (int i=0; i <= 101; ++i) {
			String cardinal = Cardinal.toCardinal(i);
			sb.append(i + "\t" + sqt(cardinal) + "\n");
		}

		out(sb.toString());
		assertEquals("0\t'zero'\n" +
				"1\t'one'\n" +
				"2\t'two'\n" +
				"3\t'three'\n" +
				"4\t'four'\n" +
				"5\t'five'\n" +
				"6\t'six'\n" +
				"7\t'seven'\n" +
				"8\t'eight'\n" +
				"9\t'nine'\n" +
				"10\t'ten'\n" +
				"11\t'eleven'\n" +
				"12\t'twelve'\n" +
				"13\t'thirteen'\n" +
				"14\t'fourteen'\n" +
				"15\t'fifteen'\n" +
				"16\t'sixteen'\n" +
				"17\t'seventeen'\n" +
				"18\t'eighteen'\n" +
				"19\t'nineteen'\n" +
				"20\t'twenty'\n" +
				"21\t'twenty-one'\n" +
				"22\t'twenty-two'\n" +
				"23\t'twenty-three'\n" +
				"24\t'twenty-four'\n" +
				"25\t'twenty-five'\n" +
				"26\t'twenty-six'\n" +
				"27\t'twenty-seven'\n" +
				"28\t'twenty-eight'\n" +
				"29\t'twenty-nine'\n" +
				"30\t'thirty'\n" +
				"31\t'thirty-one'\n" +
				"32\t'thirty-two'\n" +
				"33\t'thirty-three'\n" +
				"34\t'thirty-four'\n" +
				"35\t'thirty-five'\n" +
				"36\t'thirty-six'\n" +
				"37\t'thirty-seven'\n" +
				"38\t'thirty-eight'\n" +
				"39\t'thirty-nine'\n" +
				"40\t'forty'\n" +
				"41\t'forty-one'\n" +
				"42\t'forty-two'\n" +
				"43\t'forty-three'\n" +
				"44\t'forty-four'\n" +
				"45\t'forty-five'\n" +
				"46\t'forty-six'\n" +
				"47\t'forty-seven'\n" +
				"48\t'forty-eight'\n" +
				"49\t'forty-nine'\n" +
				"50\t'fifty'\n" +
				"51\t'fifty-one'\n" +
				"52\t'fifty-two'\n" +
				"53\t'fifty-three'\n" +
				"54\t'fifty-four'\n" +
				"55\t'fifty-five'\n" +
				"56\t'fifty-six'\n" +
				"57\t'fifty-seven'\n" +
				"58\t'fifty-eight'\n" +
				"59\t'fifty-nine'\n" +
				"60\t'sixty'\n" +
				"61\t'sixty-one'\n" +
				"62\t'sixty-two'\n" +
				"63\t'sixty-three'\n" +
				"64\t'sixty-four'\n" +
				"65\t'sixty-five'\n" +
				"66\t'sixty-six'\n" +
				"67\t'sixty-seven'\n" +
				"68\t'sixty-eight'\n" +
				"69\t'sixty-nine'\n" +
				"70\t'seventy'\n" +
				"71\t'seventy-one'\n" +
				"72\t'seventy-two'\n" +
				"73\t'seventy-three'\n" +
				"74\t'seventy-four'\n" +
				"75\t'seventy-five'\n" +
				"76\t'seventy-six'\n" +
				"77\t'seventy-seven'\n" +
				"78\t'seventy-eight'\n" +
				"79\t'seventy-nine'\n" +
				"80\t'eighty'\n" +
				"81\t'eighty-one'\n" +
				"82\t'eighty-two'\n" +
				"83\t'eighty-three'\n" +
				"84\t'eighty-four'\n" +
				"85\t'eighty-five'\n" +
				"86\t'eighty-six'\n" +
				"87\t'eighty-seven'\n" +
				"88\t'eighty-eight'\n" +
				"89\t'eighty-nine'\n" +
				"90\t'ninety'\n" +
				"91\t'ninety-one'\n" +
				"92\t'ninety-two'\n" +
				"93\t'ninety-three'\n" +
				"94\t'ninety-four'\n" +
				"95\t'ninety-five'\n" +
				"96\t'ninety-six'\n" +
				"97\t'ninety-seven'\n" +
				"98\t'ninety-eight'\n" +
				"99\t'ninety-nine'\n" +
				"100\t'one hundred'\n" +
				"101\t'one hundred one'\n", sb.toString());
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
		StringBuilder sb = new StringBuilder();

		for (int i : new int[]{100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000}) {
			String cardinal = Cardinal.toCardinal(i);
			sb.append(i + "\t" + sqt(cardinal) + "\n");
		}

		out(sb.toString());
		assertEquals("100\t'one hundred'\n" +
				"1000\t'one thousand'\n" +
				"10000\t'ten thousand'\n" +
				"100000\t'one hundred thousand'\n" +
				"1000000\t'one million'\n" +
				"10000000\t'ten million'\n" +
				"100000000\t'one hundred million'\n" +
				"1000000000\t'one billion'\n", sb.toString());
	}

}
