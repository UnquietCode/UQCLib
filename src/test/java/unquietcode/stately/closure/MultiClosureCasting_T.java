/*
 * Copyright $YEAR$ Benjamin Fagin
 *
 * This work is licensed under the Common Development and Distribution License (CDDL), Version 1.0
 *
 * Read the included LICENSE.TXT for more information.
 *
 * --------------------------------------------------------------------
 */

package unquietcode.stately.closure;

import org.junit.Test;

import static unquietcode.util.Shortcuts.out;


/**
 * @author  Benjamin Fagin
 * @version Dec 11, 2010
 */
//TODO finish work on this
public class MultiClosureCasting_T {
/*
	@Test
	public void downcast() {
		AbstractMultiClosure<String, String, String, Void, Void, Void, Void> helloMaker =
		new AbstractMultiClosure<String, String, String, Void, Void, Void, Void>() {

			@Override public String run(String p1) {
				return "hello " + p1;
			}

			@Override public String run(String p1, String p2) {
				return "hello " + p1 + " and " + p2;
			}

			@Override public String run(Object... args) {
				StringBuilder sb = new StringBuilder();
				sb.append("hello to");

				int lcv = 0;
				for (Object o : args) {
					String s = (String) o;
					sb.append(" ").append(s);

					if (++lcv != args.length)
						sb.append(", and");
				}

				return sb.toString();
			}
		};

		out(helloMaker.run("Bob"));
		out(helloMaker.run("Allan", "Susan"));
		out(helloMaker.run("Jennifer", "Alexander", "Ryan", "Chris"));
		out();

		// We can try to get a more constrained versions of our helloMaker
		AbstractClosure1<String, String> singles = helloMaker.getClosure1();
		out(singles.run("Steven"));

		// Of course, you don't have to type them if you don't want to.
		AbstractClosure2 doubles = helloMaker.getClosure2();
		out(doubles.run("Tabitha", "Aragorn"));

		//we didn't define a 3 argument version, but we did define a vararg, so it will work
		AbstractClosure3<String, String, String, String> fallback = helloMaker.getClosure3();
		out(fallback.run("Arnold", "Julie", "Marissa"));
		//however, if we did not define a vararg version, we would have gotten an exception at runtime
	}

	@Test
	public void mcTest() {
		AbstractMultiClosure<String, String, String, Void, Void, Void, Void> helloMaker =
		new AbstractMultiClosure<String, String, String, Void, Void, Void, Void>() {

			@Override public String run(String p1) {
				this.wrapped = false; //TODO this is a problem
				return "hello " + p1;
			}

			@Override public String run(String p1, String p2) {
				return "hello " + p1 + " and " + p2;
			}
		};

		for (int i = -1; i < 8; ++i) {
			out(i + " : " + helloMaker.isImplemented(i));
		}

		//helloMaker.run(1,2,3,4,5); //exception test
	}
*/


}
