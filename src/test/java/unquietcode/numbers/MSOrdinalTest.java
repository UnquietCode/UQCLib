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
import unquietcode.numbers.Ordinal;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * @author Mike Sullivan
 * @version 1.0
 */
public class MSOrdinalTest {

	@Test
	public void testScaling() {
        assertEquals(Ordinal.toOrdinal(1000000), "1000000th");
        assertEquals(Ordinal.toOrdinal(10000000), "10000000th");
        assertEquals(Ordinal.toOrdinal(100000000), "100000000th");
        assertEquals(Ordinal.toOrdinal(1000000000), "1000000000th");
		assertEquals(Ordinal.toOrdinal(1000002), "1000002nd");
	}

    @Test
    public void testLongScaling() {
        assertEquals(Ordinal.toOrdinal(1000000, true), "one millionth");
        assertEquals(Ordinal.toOrdinal(1000001, true), "one million first");
        assertEquals(Ordinal.toOrdinal(10000000, true), "ten millionth");
        assertEquals(Ordinal.toOrdinal(10000001, true), "ten million first");
        assertEquals(Ordinal.toOrdinal(100000000, true), "one hundred millionth");
        assertEquals(Ordinal.toOrdinal(100000001, true), "one hundred million first");
        assertEquals(Ordinal.toOrdinal(1000000000, true), "one billionth");        
        assertEquals(Ordinal.toOrdinal(1000000001, true), "one billion first");        
        assertEquals(Ordinal.toOrdinal(1000001000, true), "one billion one thousandth");
	    assertEquals(Ordinal.toOrdinal(1000002, true), "one million second");
    }

    @Test
    public void testNegativeScaling() {
        try {
            assertEquals(Ordinal.toOrdinal(-1000000), "-1000000th");
            fail("Can't be negative");
        } catch (IllegalArgumentException e)    {
            assertEquals(e.getMessage(), "number must be positive");
        }
    }

    @Test
    public void testLongNegativeScaling() {
        try {
            assertEquals(Ordinal.toOrdinal(-1000000, true), "negative one millionth");
            fail("Can't be negative");
        } catch (IllegalArgumentException e)    {
            assertEquals(e.getMessage(), "number must be positive");
        }
    }
}
