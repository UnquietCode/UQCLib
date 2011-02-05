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

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Benjamin Fagin
 * @version 02-05-2011
 */
public class Iterables {

	/**
	 * Creates a simple iterator that can be used in a foreach loop.
	 * Can go in reverse by setting the step to a negative value.
	 *
	 * The range is as one would expect, being from [start, stop).
	 * Of course, the actual values emitted are dependent on the step.
	 *
	 * Use:
	 *      for (int x : range(0,10,1)) {
	 *			System.out.println(x);
	 *		}
	 *
	 *      or
	 *
	 *      Iterable<Integer> range = range(10, 0, -1);
	 * 		while(range.hasNext()) {
	 *		    System.out.println(range.next());
	 *	    }
	 *
	 * @param start
	 * @param stop
	 * @param step
	 * @return
	 */
	public static Iterable<Integer> range(final int start, final int stop, final int step) {
		return new Iterable<Integer>() {
			public Iterator<Integer> iterator() {
				return new Iterator<Integer>() {
					int current = start;
					boolean good = start != stop;

					public boolean hasNext() {
						return good;
					}

					public Integer next() {
						if (!good) {
							throw new NoSuchElementException();
						}

						int retval = current;
						current += step;

						if (step < 0) {
							if (current <= stop)
								good = false;
						} else {
							if (current >= stop)
								good = false;
						}

						return retval;
					}

					public void remove() {
						throw new UnsupportedOperationException();
					}
				};
			}
		};
	}
}
