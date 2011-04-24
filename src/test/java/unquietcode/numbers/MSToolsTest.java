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
import unquietcode.numbers.NumberTools;
import unquietcode.util.StringUtils;

import java.util.Random;

import static unquietcode.util.Shortcuts.out;
import static unquietcode.util.Shortcuts.outN;
import static org.junit.Assert.*;

/**
 * @author Mike Sullivan
 * @version 1.0
 *          Date: Nov 28, 2010
 */
public class MSToolsTest {

	@Test
	public void positiveSplits() {
        assertArrayEquals(NumberTools.splitDigits(0), new int[]{0});
        assertArrayEquals(NumberTools.splitDigits(1), new int[]{1});
        assertArrayEquals(NumberTools.splitDigits(2), new int[]{2});
        assertArrayEquals(NumberTools.splitDigits(3), new int[]{3});
        assertArrayEquals(NumberTools.splitDigits(4), new int[]{4});
        assertArrayEquals(NumberTools.splitDigits(5), new int[]{5});
        assertArrayEquals(NumberTools.splitDigits(6), new int[]{6});
        assertArrayEquals(NumberTools.splitDigits(7), new int[]{7});
        assertArrayEquals(NumberTools.splitDigits(8), new int[]{8});
        assertArrayEquals(NumberTools.splitDigits(9), new int[]{9});
        assertArrayEquals(NumberTools.splitDigits(10), new int[]{1,0});
        assertArrayEquals(NumberTools.splitDigits(100), new int[]{1,0,0});
        assertArrayEquals(NumberTools.splitDigits(1000), new int[]{1,0,0,0});
        assertArrayEquals(NumberTools.splitDigits(10000), new int[]{1,0,0,0,0});
        assertArrayEquals(NumberTools.splitDigits(100000), new int[]{1,0,0,0,0,0});
        assertArrayEquals(NumberTools.splitDigits(1000000), new int[]{1,0,0,0,0,0,0});
        assertArrayEquals(NumberTools.splitDigits(10000000), new int[]{1,0,0,0,0,0,0,0});
        assertArrayEquals(NumberTools.splitDigits(100000000), new int[]{1,0,0,0,0,0,0,0,0});
        assertArrayEquals(NumberTools.splitDigits(1000000000), new int[]{1,0,0,0,0,0,0,0,0,0});
        assertArrayEquals(NumberTools.splitDigits(2147483647), new int[]{2,1,4,7,4,8,3,6,4,7});
	}

    @Test
    public void negativeSplits() {
        assertArrayEquals(NumberTools.splitDigits(-0), new int[]{0});
        assertArrayEquals(NumberTools.splitDigits(-1), new int[]{1});
        assertArrayEquals(NumberTools.splitDigits(-2), new int[]{2});
        assertArrayEquals(NumberTools.splitDigits(-3), new int[]{3});
        assertArrayEquals(NumberTools.splitDigits(-4), new int[]{4});
        assertArrayEquals(NumberTools.splitDigits(-5), new int[]{5});
        assertArrayEquals(NumberTools.splitDigits(-6), new int[]{6});
        assertArrayEquals(NumberTools.splitDigits(-7), new int[]{7});
        assertArrayEquals(NumberTools.splitDigits(-8), new int[]{8});
        assertArrayEquals(NumberTools.splitDigits(-9), new int[]{9});
        assertArrayEquals(NumberTools.splitDigits(-10), new int[]{1,0});
        assertArrayEquals(NumberTools.splitDigits(-100), new int[]{1,0,0});
        assertArrayEquals(NumberTools.splitDigits(-1000), new int[]{1,0,0,0});
        assertArrayEquals(NumberTools.splitDigits(-10000), new int[]{1,0,0,0,0});
        assertArrayEquals(NumberTools.splitDigits(-100000), new int[]{1,0,0,0,0,0});
        assertArrayEquals(NumberTools.splitDigits(-1000000), new int[]{1,0,0,0,0,0,0});
        assertArrayEquals(NumberTools.splitDigits(-10000000), new int[]{1,0,0,0,0,0,0,0});
        assertArrayEquals(NumberTools.splitDigits(-100000000), new int[]{1,0,0,0,0,0,0,0,0});
        assertArrayEquals(NumberTools.splitDigits(-1000000000), new int[]{1,0,0,0,0,0,0,0,0,0});
        assertArrayEquals(NumberTools.splitDigits(-2147483647), new int[]{2,1,4,7,4,8,3,6,4,7});
        assertArrayEquals(NumberTools.splitDigits(-2147483648), new int[]{2,1,4,7,4,8,3,6,4,8});
    }

	@Test
	public void commaAdding() {
        assertEquals(NumberTools.addCommas(0), "0");
        assertEquals(NumberTools.addCommas(1), "1");
        assertEquals(NumberTools.addCommas(10), "10");
        assertEquals(NumberTools.addCommas(100), "100");
        assertEquals(NumberTools.addCommas(1000), "1,000");
        assertEquals(NumberTools.addCommas(10000), "10,000");
        assertEquals(NumberTools.addCommas(100000), "100,000");
        assertEquals(NumberTools.addCommas(1000000), "1,000,000");
        assertEquals(NumberTools.addCommas(10000000), "10,000,000");
        assertEquals(NumberTools.addCommas(100000000), "100,000,000");
        assertEquals(NumberTools.addCommas(1000000000), "1,000,000,000");
        assertEquals(NumberTools.addCommas(2147483647), "2,147,483,647");
	}

	@Test
	public void negativeCommaAdding() {
        assertEquals(NumberTools.addCommas(-0), "0");
        assertEquals(NumberTools.addCommas(-1), "-1");
        assertEquals(NumberTools.addCommas(-10), "-10");
        assertEquals(NumberTools.addCommas(-100), "-100");
        assertEquals(NumberTools.addCommas(-1000), "-1,000");
        assertEquals(NumberTools.addCommas(-10000), "-10,000");
        assertEquals(NumberTools.addCommas(-100000), "-100,000");
        assertEquals(NumberTools.addCommas(-1000000), "-1,000,000");
        assertEquals(NumberTools.addCommas(-10000000), "-10,000,000");
        assertEquals(NumberTools.addCommas(-100000000), "-100,000,000");
        assertEquals(NumberTools.addCommas(-1000000000), "-1,000,000,000");
        assertEquals(NumberTools.addCommas(-2147483647), "-2,147,483,647");
        assertEquals(NumberTools.addCommas(-2147483648), "-2,147,483,648");
	}

	//TODO all of these issues with floats being mutated before they hit the function.
	// they get truncated.
	@Test
	public void percentages() {
        assertEquals(NumberTools.percentage(100,1), "100.0%");
        assertEquals(NumberTools.percentage(100,2), "100.00%");
        assertEquals(NumberTools.percentage(100,3), "100.000%");
        assertEquals(NumberTools.percentage(100,4), "100.0000%");
        assertEquals(NumberTools.percentage(100,5), "100.00000%");
        assertEquals(NumberTools.percentage(100,10), "100.0000000000%");
        assertEquals(NumberTools.percentage(99.9,10), "99.9000000000%");
        assertEquals(NumberTools.percentage(999.0/10,10), "99.9000000000%");
        assertEquals(NumberTools.percentage(9990/100.0,10), "99.9000000000%");
        assertEquals(NumberTools.percentage(9999/100.0,10), "99.9900000000%");
        assertEquals(NumberTools.percentage(99990/1000.0,10), "99.9900000000%");

        assertEquals(NumberTools.percentage(99.999,3), "99.999%");
        assertEquals(NumberTools.percentage(99.999,2), "99.99%");
        assertEquals(NumberTools.percentage(99.999,1), "99.9%");
        assertEquals(NumberTools.percentage(99.999,0), "99%");

        assertEquals(NumberTools.percentage(1.111111111111111,15), "1.111111111111111%");
        //assertEquals(NumberTools.percentage(1.1111111111111111,16), "1.1111111111111111%");
        //assertEquals(NumberTools.percentage(1.11111111111111111,17), "1.11111111111111111%");
        //assertEquals(NumberTools.percentage(1.111111111111111111,18), "1.111111111111111111%");

        assertEquals(NumberTools.percentage(11.11111111111111,14), "11.11111111111111%");
        //assertEquals(NumberTools.percentage(11.111111111111111,15), "11.111111111111111%");

        assertEquals(NumberTools.percentage(111.11111111111111,14), "111.11111111111111%");
        //assertEquals(NumberTools.percentage(111.111111111111111,15), "111.111111111111111%");

        assertEquals(NumberTools.percentage(1111111111.1,1), "1111111111.1%");

        assertEquals(NumberTools.percentage(11111111111.1,1), "11111111111.1%");
        assertEquals(NumberTools.percentage(111111111111.1,1), "111111111111.1%");
        assertEquals(NumberTools.percentage(1111111111111.1,1), "1111111111111.1%");
        assertEquals(NumberTools.percentage(11111111111111.1,1), "11111111111111.1%");
        assertEquals(NumberTools.percentage(111111111111111.1,1), "111111111111111.1%");
        assertEquals(NumberTools.percentage(1111111111111111.1,1), "1111111111111111.1%");

        assertEquals(NumberTools.percentage(1111111111.1001,4), "1111111111.1001%");
        assertEquals(NumberTools.percentage(1111111111.1000001,7), "1111111111.1000001%");
        assertEquals(NumberTools.percentage(1111111111.1000000001,10), "1111111111.1000000001%");
    }


	@Test
	public void percentagesNegatives() {

        assertEquals(NumberTools.percentage(-100,1), "-100.0%");
        assertEquals(NumberTools.percentage(-100,2), "-100.00%");
        assertEquals(NumberTools.percentage(-100,3), "-100.000%");
        assertEquals(NumberTools.percentage(-100,4), "-100.0000%");
        assertEquals(NumberTools.percentage(-100,5), "-100.00000%");
        assertEquals(NumberTools.percentage(-100,10), "-100.0000000000%");
        assertEquals(NumberTools.percentage(-99.9,10), "-99.9000000000%");
        assertEquals(NumberTools.percentage(-999.0/10,10), "-99.9000000000%");
        assertEquals(NumberTools.percentage(-9990/100.0,10), "-99.9000000000%");
        assertEquals(NumberTools.percentage(-9999/100.0,10), "-99.9900000000%");
        assertEquals(NumberTools.percentage(-99990/1000.0,10), "-99.9900000000%");

        assertEquals(NumberTools.percentage(-99.999,3), "-99.999%");
        assertEquals(NumberTools.percentage(-99.999,2), "-99.99%");
        assertEquals(NumberTools.percentage(-99.999,1), "-99.9%");
        assertEquals(NumberTools.percentage(-99.999,0), "-99%");

        assertEquals(NumberTools.percentage(-1.111111111111111,15), "-1.111111111111111%");
        assertEquals(NumberTools.percentage(-1.1111111111111111,16), "-1.1111111111111111%");
        assertEquals(NumberTools.percentage(-1.11111111111111111,17), "-1.11111111111111111%");
        assertEquals(NumberTools.percentage(-1.111111111111111111,18), "-1.111111111111111111%");

        assertEquals(NumberTools.percentage(-11.11111111111111,14), "-11.11111111111111%");
        assertEquals(NumberTools.percentage(-11.111111111111111,15), "-11.111111111111111%");

        assertEquals(NumberTools.percentage(-111.11111111111111,14), "-111.11111111111111%");
        assertEquals(NumberTools.percentage(-111.111111111111111,15), "-111.111111111111111%");

        assertEquals(NumberTools.percentage(-1111111111.1,1), "-1111111111.1%");
        assertEquals(NumberTools.percentage(-11111111111.1,1), "-11111111111.1%");
        assertEquals(NumberTools.percentage(-111111111111.1,1), "-111111111111.1%");
        assertEquals(NumberTools.percentage(-1111111111111.1,1), "-1111111111111.1%");
        assertEquals(NumberTools.percentage(-11111111111111.1,1), "-11111111111111.1%");
        assertEquals(NumberTools.percentage(-111111111111111.1,1), "-111111111111111.1%");
        assertEquals(NumberTools.percentage(-1111111111111111.1,1), "-1111111111111111.1%");

        assertEquals(NumberTools.percentage(-1111111111.1001,4), "-1111111111.1001%");
        assertEquals(NumberTools.percentage(-1111111111.1000001,7), "-1111111111.1000001%");
        assertEquals(NumberTools.percentage(-1111111111.1000000001,10), "-1111111111.1000000001%");
	}
}
