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

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static unquietcode.util.Shortcuts.out;

/**
 * @author Benjamin Fagin
 * @version Dec 11, 2010
 */
public class ClosureFeatures_T {
	@Test
	public void inputTypes() {
		final String string = "hello";  // a final variable is accessible inside
		int X = 10;                     // not accessible, but can still be passed in
		final int dummyX = X;           // this will work

		Closure1<Integer, Integer> adder = new Closure1<Integer, Integer>(X) {  // <-passing in a variable
			int x = this.<Integer>a1(); // will have to use this somtimes for some compilers
			int y = (Integer) a1();	    // this will also work
			Integer z = a1();           // how it should always work, ideally
			int base = 12;              // of course, this always works

			public Integer run(Integer p1) {
				// access the final variables
				out(string + " " + dummyX);

				// can also get that X from the passed in variable
				out(string + " " + a1());

				// which can also be looked up by argument number
				out(string + " " + arg(1));

				// or from our declared ones
				out(string + " " + x);
				out(string + " " + y);
				out(string + " " + z);

				// and we also get our parameter value
				out(string + " " + p1);

				// then we return an Integer
				return base + p1 + (Integer) a1() + x + y + z;
			}
		};

		// Now we can run it!
		out(adder.run(5));
	}

	@Test
	public void factoryTest() {
		// create a closure that acts as a factory of adders

		Closure1<Closure1<Integer, Integer>, Integer> adderFactory = new Closure1<Closure1<Integer, Integer>, Integer>() {

			public Closure1<Integer, Integer> run(Integer p1) {
				Closure1<Integer, Integer> generated = new Closure1<Integer, Integer>(p1) {
					Integer base = a1();

					public Integer run(Integer p1) {
						return base + p1;
					}
				};

				return generated;
			}
		};

		// get a few adders and try them out
		Closure1<Integer, Integer> add10 = adderFactory.run(10);
		Closure1<Integer, Integer> add5 = adderFactory.run(5);
		Closure1<Integer, Integer> sub1 = adderFactory.run(-1);

		int test = 50;
		out(add10.run(test));
		out(add5.run(test));
		out(sub1.run(test));
	}

	@Test
	public void untypedFactoryTest() {
		// Only the return type needs to be specified, if you're willing to keep track
		// of the necessary inputs and their types

		Closure<Closure> adderFactory = new Closure<Closure>() {

			public Closure run(Object...args) {
				return new Closure<Integer>(args[0]) {
					Integer base = a1();

					// if you don't override this an exception will be thrown
					public @Override Integer run(Object...args) {
						return base + (Integer) args[0];
					}
				};
			}
		};

		// get a few adders and try them out
		Closure add10 = adderFactory.run(10);
		Closure add5 = adderFactory.run(5);
		Closure sub1 = adderFactory.run(-1);

		// you are responsible for passing in the correct type and number of arguments
		int test = 50;
		out(add10.run(test));
		out(add5.run(test));
		out(sub1.run(test));
	}

	@Test
	public void curryTest() {
		// we'll again use the adderFactory example

		Closure1<Closure1<Integer, Integer>, Integer> adderFactory = new Closure1<Closure1<Integer, Integer>, Integer>() {
			int declaredValue1 = 1;
			int declaredValue2 = 10;

			@Override
			public Closure1<Integer, Integer> run(Integer p1) {
				return new Closure1<Integer, Integer>(p1) {
					Integer declaredValue3 = a1();
					
					public @Override Integer run(Integer p1) {
						// note that if we had used a1() down here, the value would not have been curried
						return declaredValue1 + declaredValue2 + declaredValue3 + p1;
					}
				};
			}
		};

		Closure1 tarragon = adderFactory.run(100);  // will add 1 + 10 + 100 + x
		out(tarragon.run(5));                       // which gives us 116

		// the returned adder has 1 field, declaredValue3
		
		tarragon.curry(1, 110);                     // change to add 1 + 10 + 110 + x instead
		out(tarragon.run(5));                       // now we get 126!

		// we can change the factory too, though

		adderFactory.curry(1, 15);
		adderFactory.curry(2, 20);
		Closure1 cardamom = adderFactory.run(100);  // will add 15 + 20 + 100 + x
		out(cardamom.run(5));                       // which gives us 140
	}

	@Test
	public void methodCallingTest() {
		// Call a method from within the closure.

		String string = "The way I used to be";

		Closure1<String, String> closure = new Closure1<String,String>(string) {

			public String run(String a1) {
				return modify(a1) + ".";
			}
		};

		out(string);
		out("\nis not the same as\n");
		out(closure.run(string));
	}

	private String modify(String input) {
		return input.substring(0, 10).replace("T", "t") + "am";
	}

	@Test
	@SuppressWarnings("unchecked")
	public void changingTest() {
		// Unless you pass in a copy, your object can be mutated.

		Map<Integer, Character> map = new HashMap<Integer, Character>();
		map.put(0, 'A');
		map.put(1, 'B');

		Closure<Integer> encoder = new Closure<Integer>(map) {

			public Integer run(Object...args) {
				char x = ((Map<Integer, Character>)a1()).get(0);
				char y = ((Map<Integer, Character>)a1()).get(1);

				return (x + y) % (Integer) args[0];
			}
		};

		int AB = encoder.run(5);
		map.put(1, 'C');
		int AC = encoder.run(5);

		out("AB code: " + AB);
		out("AC code: " + AC);
		out();
		
		// However, the initial values can be stored in the intializer section of the closure.

		char array[] = {'D', 'E'};

		Closure<Integer> encoder2 = new Closure<Integer>(array) {
			char x = ((char[])a1())[0];
			char y = ((char[])a1())[1];

			public Integer run(Object...args) {
				return (x + y) % (Integer) args[0];
			}
		};

		int DE = encoder2.run(5);
		array[1] = 'F';
		int DF = encoder2.run(5);

		out("DE code: " + DE);
		out("DF code: " + DF + " (not really!)");
	}

	@Test
	public void helperMethods() {
		// Because these are anonymous classes, you can add in whatever methods you want.
		// They will not be accessible outside of the closure (without reflection, of course).

		Closure theDestroyer = new Closure() {
			Random gen = new Random();

			public Object run(Object...args) {
				String string = (String) args[0];
				StringBuilder sb = new StringBuilder();

				for (int i=0; i < string.length(); ++i) {
					sb.append(randomLetter(string));
				}

				return sb.toString();
			}

			private char randomLetter(String string) {
				return string.charAt(gen.nextInt(string.length()));
			}
		};

		String theString = "The original string.";
		out(theString);
		out("\n\t\t!=\n");
		out(theDestroyer.run(theString));
	}


	//TODO is the argument array useful? is there a case when it is necessary?
	@Test
	public void getArgumentTypes() {
		Integer num = 20;

		Closure closure = new Closure(1, 'a', num) {
			@Override
			public Object run(Object... args) {
				return null;
			}
		};
//		for (Class c)
	}
}
