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

/**
 * @author Benjamin Fagin
 * @version 02-05-2011
 */
public class Iterables_T {

	@Test
	public void rangeTest() {
		for (int x : range(10,0,-1)) {
			System.out.println(x);
		}
	}

}
