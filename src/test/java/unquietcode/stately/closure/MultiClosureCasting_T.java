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

					if (lcv == args.length)
						sb.append(", and"); //TODO fix
					else if (++lcv != args.length)
						sb.append(",");
				}

				return sb.toString();
			}
		};

		out(helloMaker.run("Bob"));
		out(helloMaker.run("Allan", "Susan"));
		out(helloMaker.run("Jennifer", "Alexander", "Ryan", "Chris"));
		out();


		// We can try to get a more constrained versions of our helloMaker
		Closure1<String, String> singles = helloMaker.toClosure1();
		out(singles.run("Steven"));

		// Of course, you don't have to type them if you don't want to.
		Closure2 doubles = helloMaker.toClosure2();
		out(doubles.run("Tabitha", "Aragorn"));

		// We didn't define a 3 argument version, but we did define a vararg, so it will work.
		Closure3<String, String, String, String> fallback = helloMaker.toClosure3();
		out(fallback.run("Arnold", "Julie", "Marissa"));

		// However, if we did not define a vararg version, we would have gotten an exception at runtime!

		// We can even curry the sdfsd
		out();
		helloMaker.curry(1, "Goodbye");
		Closure closure = helloMaker.toClosure();
		out(closure.run("Bob", "Allan", "Susan", "Jennifer", "Alexander", "Ryan", "Chris"));
		out(helloMaker.run("Bob", "Allan", "Susan", "Jennifer", "Alexander", "Ryan", "Chris"));

		//TODO so is this the way it should be?
		// The expectation should be that currying a closure will only affect that closure.
		// right now it alters the base.
		//TODO test this on closure3 -> closure for example, though I suspect the same result.

		out();
		helloMaker.curry(1, "Welcome back");
		out(closure.run("Bob", "Allan", "Susan", "Jennifer", "Alexander", "Ryan", "Chris"));
		out(helloMaker.run("Bob", "Allan", "Susan", "Jennifer", "Alexander", "Ryan", "Chris"));
	}

	@Test
	public void mcTest() {
		AbstractMultiClosure<String> helloMaker =
		new AbstractMultiClosure<String>() {

			public String run(String p1) {
				this.wrapped = false; //TODO this is a problem
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
