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
import unquietcode.stately.closure.view.Closure1View;
import unquietcode.stately.closure.view.Closure2View;
import unquietcode.stately.closure.view.Closure3View;
import unquietcode.stately.closure.view.ClosureView;

import static unquietcode.util.Shortcuts.out;


/**
 * @author  Benjamin Fagin
 * @version Dec 11, 2010
 */
//TODO finish work on this
@SuppressWarnings("unchecked")
public class MultiClosureCasting_T {
	@Test
	public void downcast() {
		// blah blah multiclosures
		// Note that here the @Override annotation is required, as the class does not require explicitly
		// implementing the methods. It's pick and choose.

		MultiClosure<String> helloMaker = new AbstractMultiClosure() {
			String greeting = "Hello";

			public @Override Object run(Object p1) {
				return greeting + " " + p1;
			}

			public @Override Object run(Object p1, Object p2) {
				return greeting + " " + p1 + " and " + p2;
			}

			public @Override Object run(Object...args) {
				StringBuilder sb = new StringBuilder();
				sb.append(greeting).append(" to");

				int lcv = 0;
				for (Object o : args) {
					String s = (String) o;
					sb.append(" ").append(s);

					if (lcv == args.length-2)
						sb.append(", and");
					else if (lcv < args.length-1)
						sb.append(",");

					++lcv;
				}

				return sb.toString();
			}
		};

		out(helloMaker.run("Bob"));
		out(helloMaker.run("Allan", "Susan"));
		out(helloMaker.run("Jennifer", "Alexander", "Ryan", "Chris"));
		out();


		// We can try to get a more constrained versions of our helloMaker
		Closure1View<String, String> singles = helloMaker.toClosure1();
		out(singles.run("Steven"));

		// Of course, you don't have to type them if you don't want to.
		Closure2View doubles = helloMaker.toClosure2();
		out(doubles.run("Tabitha", "Aragorn"));

		// We didn't define a 3 argument version, but we did define a vararg, so it will work.
		Closure3View<String, String, String, String> fallback = helloMaker.toClosure3();
		out(fallback.run("Arnold", "Julie", "Marissa"));

		// However, if we did not define a vararg version, we would have gotten an exception at runtime!

		// We can curry the MultiClosure too.
		out();
		helloMaker.curry(1, "Goodbye");
		out(helloMaker.run("Bob", "Allan", "Susan", "Jennifer", "Alexander", "Ryan", "Chris"));

		// Remember though that the views are affected.
		out();
		ClosureView closure = helloMaker.toClosure();
		helloMaker.curry(1, "Welcome back");
		out(closure.run("Bob", "Allan", "Susan", "Jennifer", "Alexander", "Ryan", "Chris"));
	}

	@Test
	public void mcTest() {
		AbstractMultiClosure<String> helloMaker =
		new AbstractMultiClosure<String>() {

			public String run(String p1) {
				return "hello " + p1;
			}

			public String run(String p1, String p2) {
				return "hello " + p1 + " and " + p2;
			}
		};

		for (int i = -1; i < 8; ++i) {
			out(i + " : " + helloMaker.isImplemented(i));
		}

		//helloMaker.run(1,2,3,4,5); //exception test
	}



}
