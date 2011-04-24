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

package unquietcode.util;

import org.junit.Test;

import static unquietcode.util.Iterables.range;
import static org.junit.Assert.*;

/**
 * @author Mike Sullivan
 * @version 02-27-2011
 */
public class MSIterables_T {

	// [max, 0) by -1
	@Test
	public void rangeTest1() {
        testRange(Integer.MAX_VALUE, 0, -1, Integer.MAX_VALUE);
	}

	// [0, max) by 1
    @Test
    public void rangeTest2() {
        testRange(0, Integer.MAX_VALUE, 1, Integer.MAX_VALUE);
    }

	// [min, 0) by 1
    @Test
    public void rangeTest3() {
        testRange(Integer.MIN_VALUE, 0, 1, Integer.MAX_VALUE + 1L);
    }

	// [0, min) by -1
    @Test
    public void rangeTest3b() {
        testRange(0, Integer.MIN_VALUE, -1, Integer.MAX_VALUE + 1L);
    }

	// [min, max) by 1
    @Test
    public void rangeTest4() {
        testRange(Integer.MIN_VALUE, Integer.MAX_VALUE, 1, Integer.MAX_VALUE+1L + Integer.MAX_VALUE);
    }

	// [max, min) by -1
    @Test
    public void rangeTest5() {
        testRange(Integer.MAX_VALUE, Integer.MIN_VALUE, -1, Integer.MAX_VALUE + 0L + Integer.MAX_VALUE+1L);
    }

	// step too large
    @Test
    public void rangeTest6() {
        testRange(0, 100, 101, 1);
    }

	// every 3
    @Test
    public void rangeTest7() {
        testRange(0, 100, 3, 34);
    }

	// maxes
    @Test
    public void rangeTest8() {
        testRange(2147483645, Integer.MAX_VALUE, 1, 2);
        testRange(2147483645, Integer.MAX_VALUE, 2, 1);
        testRange(2147483645, Integer.MAX_VALUE, 3, 1 );
    }

	// [0, max) by 3
    @Test
    public void rangeTest9() {
	    int count = Integer.MAX_VALUE / 3;
	    if (Integer.MAX_VALUE % 3 != 0) {
		    count += 1;
	    }

        testRange(0, Integer.MAX_VALUE, 3, count);
    }

	// [0, max) by 4
    @Test
    public void rangeTest10() {
	    int count = Integer.MAX_VALUE / 4;
	    if (Integer.MAX_VALUE % 4 != 0) {
		    count += 1;
	    }

        testRange(0, Integer.MAX_VALUE, 4, count);
    }

    private void testRange(int hi, int lo, int step, long count)   {
	    long i = 0;
		for (int x : range(hi,lo,step)) { i++; }
        assertEquals(count, i);
    }
}
