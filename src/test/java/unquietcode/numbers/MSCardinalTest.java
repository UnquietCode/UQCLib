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
import unquietcode.numbers.Cardinal;


import static org.junit.Assert.assertEquals;


import java.util.Random;

/**
 * @author Mike Sullivan
 * @version 1.0
 */
public class MSCardinalTest {

    
    @Test
    public void testScaling() {
        assertEquals(Cardinal.toCardinal(1000000), "one million");
        assertEquals(Cardinal.toCardinal(10000000), "ten million");
        assertEquals(Cardinal.toCardinal(100000000), "one hundred million");
        assertEquals(Cardinal.toCardinal(1000000000), "one billion");        
    }
    
    @Test
    public void testNegativeScaling() {
	    assertEquals(Cardinal.toCardinal(-1000000000), "negative one billion");
        assertEquals(Cardinal.toCardinal(-100000000), "negative one hundred million");
        assertEquals(Cardinal.toCardinal(-10000000), "negative ten million");
        assertEquals(Cardinal.toCardinal(-1000000), "negative one million");
    }


    @Test
    public void testScaling2() {
        assertEquals(Cardinal.toCardinal(1000000001), "one billion one");
        assertEquals(Cardinal.toCardinal(1000000010), "one billion ten");
        assertEquals(Cardinal.toCardinal(1000000100), "one billion one hundred");
        assertEquals(Cardinal.toCardinal(1000001000), "one billion one thousand");
        assertEquals(Cardinal.toCardinal(1000010000), "one billion ten thousand");
        assertEquals(Cardinal.toCardinal(1000100000), "one billion one hundred thousand");
        assertEquals(Cardinal.toCardinal(1001000000), "one billion one million");
        assertEquals(Cardinal.toCardinal(1010000000), "one billion ten million");
        assertEquals(Cardinal.toCardinal(1100000000), "one billion one hundred million");
    }

    @Test
    public void testScaling3() {
        assertEquals(Cardinal.toCardinal(1001001001), "one billion one million one thousand one");
        assertEquals(Cardinal.toCardinal(1000001001), "one billion one thousand one");
        assertEquals(Cardinal.toCardinal(1001000001), "one billion one million one");
        assertEquals(Cardinal.toCardinal(1001001001), "one billion one million one thousand one");
        assertEquals(Cardinal.toCardinal(1001001000), "one billion one million one thousand");
    }

    @Test
    public void testEdges() {
        String positive = Cardinal.toCardinal(2147483647);
        String negative = Cardinal.toCardinal(-2147483647);       
        assertEquals(negative, "negative " + positive);
        assertEquals(Cardinal.toCardinal(-2147483648), "negative two billion one hundred forty-seven million four hundred eighty-three thousand six hundred forty-eight");
    }

}
