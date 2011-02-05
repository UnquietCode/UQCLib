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

import java.util.Comparator;

/**
 * @author  Benjamin Fagin
 * @version Nov 25, 2010
 *
 *
 */
public class Pair<A, B> {
	public A first;
	public B second;

	public Pair(A first, B second) {
		this.first = first;
		this.second = second;
	}

	// JavaBeans properties
	public A getFirst() {
		return first;
	}

	public void setFirst(A value) {
		first = value;
	}

	public B getSecond() {
		return second;
	}

	public void setSecond(B value) {
		second = value;
	}

	// comparators
	public static class Comparator1to1<T extends Comparable<T>> implements Comparator<Pair<T,?>> {
		public int compare(Pair<T, ?> p1, Pair<T, ?> p2) {
			return p1.first.compareTo(p2.first);
		}
	}

	public static class Comparator2to2<T extends Comparable<T>> implements Comparator<Pair<?,T>> {
		public int compare(Pair<?, T> p1, Pair<?, T> p2) {
			return p1.second.compareTo(p2.second);
		}
	}


	// named static classes //
	public static class NameValue<C, D> {
		public C name;
		public D value;

		public NameValue(C name, D value) {
			this.name = name;
			this.value = value;
		}
	}

	public static class Shoes<C, D> {
		public C left;
		public D right;

		public Shoes(C left, D right) {
			this.left = left;
			this.right = right;
		}
	}

	public static class Final<C, D> {
		public final C first;
		public final D second;

		public Final(C first, D second) {
			this.first = first;
			this.second = second;
		}
	}
}