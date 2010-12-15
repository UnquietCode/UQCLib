/*
 * Copyright $YEAR$ Benjamin Fagin
 *
 * This work is licensed under the Common Development and Distribution License (CDDL), Version 1.0
 *
 * Read the included LICENSE.TXT for more information.
 *
 * --------------------------------------------------------------------
 */

package unquietcode.test.util.closure;

import org.junit.Test;
import unquietcode.stately.closure.Closure;
import unquietcode.stately.closure.Closure1;

import static unquietcode.util.Shortcuts.out;
//todo void
/**
 * @author Benjamin Fagin
 * Date: Dec 11, 2010
 */
public class ClosureFeatures {
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
		//Only the return type needs to be specified, if you're willing to keep track
		//of the necessary inputs and their types
		Closure<Closure> adderFactory = new Closure<Closure>() {

			public Closure run(Object...args) {
				return new Closure<Integer>(args[0]) {
					Integer base = a1();

					//if you don't override this an exception will be thrown
					public @Override Integer run(Object...args) {
						return base + (Integer) args[0];
					}
				};
			}
		};

		//get a few adders and try them out
		Closure add10 = adderFactory.run(10);
		Closure add5 = adderFactory.run(5);
		Closure sub1 = adderFactory.run(-1);

		//you are responsible for passing in the correct type and number of arguments
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
}
