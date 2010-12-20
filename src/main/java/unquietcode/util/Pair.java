package unquietcode.util;

/**
 * @author Ben
 * @version 1.0
 * Date: Nov 25, 2010
 */
public class Pair<A, B> {
	public A first;
	public B second;

	public Pair(A first, B second) {
		this.first = first;
		this.second = second;
	}

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
		public C first;
		public D second;

		public Final(C first, D second) {
			this.first = first;
			this.second = second;
		}
	}
}

//TODO like a time and a name/value version would be nice.
//maybe even do it as Pair, with static inner classes Pair.Time, Pair.NaveValue, Pair.Shoes