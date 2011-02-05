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

package unquietcode.stately.closure;

import org.junit.Test;
import unquietcode.stately.closure.view.Closure1View;
import static unquietcode.util.Shortcuts.out;

/**
 * @author  Benjamin Fagin
 * @version 01-23-2011
 */
public class ClosureCasting_X {
	// Concerning closures and casting and security.
	// Closures can be cast back to AbastractClosure most of the time. This is ok, since the Closure interface
	// is intended to serve the developer as a convenience to block unwanted methods.
	// The views address security. Because they aren't implemented directly, they will always have to be created,
	// meaning it is impossible to get a closure back which can be modified from a view.

	// Conclusion: Leave it the way it is. AC is the base, C is the convenience wrapper, and CV is the secure wrapper.



	@Test
	public void reverse() {
		Closure1<Integer, Integer> magnify = new AbstractClosure1<Integer, Integer>() {
			public Integer run(Integer val) {
				return 10 * val;
			}
		};

		// This is legal because AC1 implements C1.
		AbstractClosure1 reverse = (AbstractClosure1) magnify;
		out(reverse.run(10));
	}

	@Test
	public void noReverseView() {
		Closure1View<Integer, Integer> magnify = new AbstractClosure1<Integer, Integer>() {
			public Integer run(Integer val) {
				return 10 * val;
			}
		}.getView();

		//AbstractClosure1 reverse = (AbstractClosure1) magnify;    // won't work (of course)
		//Closure1 reverse = (Closure1) magnify;                    // as well, not possible
		//
	}

	@Test
	public void fullList() {
		Closure0 c = new AbstractClosure0() {
			public Object run() {
				return 1;
			}
		};

		// TODO all permutations of casting between closureN, closure, abstractClosure, abstractClosur

		//AbstractClosure1 reverse = (AbstractClosure1) magnify;    // won't work (of course)
		//Closure1 reverse = (Closure1) magnify;                    // as well, not possible
		//
	}
}
