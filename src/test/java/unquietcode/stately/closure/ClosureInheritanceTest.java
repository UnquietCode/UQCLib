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

import static unquietcode.util.Shortcuts.*;

/**
 * @author  Ben
 * @version 0.1
 * Date:    Dec 10, 2010
 */
public class ClosureInheritanceTest {
	/*@Test
	public void basic() {
		Closure2<Integer, Integer, Integer> mixer = new Closure2<Integer, Integer, Integer>() {
			int x = 10;

			@Override
			public Integer run(Integer v1, Integer v2) {
				return v1 + v2 - x;
			}
		};

		//for (Class z : mixer.getArgumentTypes()) {
		//	out(z.getName());
//		}

		Closure c = mixer;
		try {
			out(c.Run(1, 2));
			out(c.Run(1));
		} catch (Closure.ClosureException ex) {
			err(ex.getMessage());
			die(10);
		}
	}
*/

	private <T> void genclass(T...classes) {
		//for (T x :)
	}


	@SuppressWarnings("unchecked")
	@Test
	public void noArgCastTest() {
		int X = 10;

		Closure1<Integer, Integer> adder = new Closure1<Integer, Integer>(X) {
			int x = this.<Integer>a1(); //if primitive or null, will have to use this for some compilers
			Integer X = a1();   //ok
			int z = (Integer) a1();
			int y = 10;
			//int x = 10;
			
			@Override
			public Integer run(Integer v1) {
				return v1 + x;
			}
		};


		out(adder.run(10));
	}
}
