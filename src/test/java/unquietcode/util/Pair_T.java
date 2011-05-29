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

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author  Benjamin Fagin
 * @version 02-05-2011
 */
@SuppressWarnings("unchecked")
public class Pair_T {
	@Test
	public void sort() {
		Pair<Integer, String> p1 = new Pair<Integer, String>(5, "good");
		Pair<Integer, String> p2 = new Pair<Integer, String>(1, "morning");
		Pair<Integer, String> p3 = new Pair<Integer, String>(7, "to");
		Pair<Integer, String> p4 = new Pair<Integer, String>(4, "you");

		Pair<String, String> p5 = new Pair<String, String>("is", "different");

		// sort by first
		Pair<Integer, String> pairs[] = new Pair[]{p4, p2, p1, p3};
		Arrays.sort(pairs, new Pair.Comparator1to1<Integer>());
		Assert.assertArrayEquals(new Object[]{p2, p4, p1, p3}, pairs);

		for (Pair p : pairs) {
			System.out.println(p.second);
		}

		System.out.println();

		// sort by second
		ArrayList<Pair<?, String>> plist = new ArrayList<Pair<?, String>>();
		plist.addAll(Arrays.asList(pairs));
		plist.add(p5);
		Collections.sort(plist, new Pair.Comparator2to2<String>());
		Assert.assertArrayEquals(new Object[]{p5, p1, p2, p3, p4}, plist.toArray());

		for (Pair p : plist) {
			System.out.println(p.second);
		}

	}
}
