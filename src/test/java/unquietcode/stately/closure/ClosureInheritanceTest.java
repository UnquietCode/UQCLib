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
import unquietcode.stately.closure.view.ClosureView;

import static unquietcode.util.Shortcuts.*;

/**
 * @author  Ben
 * @version 0.1
 * Date:    Dec 10, 2010
 */
public class ClosureInheritanceTest {
	@Test
	public void basic() {
		AbstractClosure2<Integer, Integer, Integer> mixer = new AbstractClosure2<Integer, Integer, Integer>() {
			int x = 10;



			public Integer run(Integer v1, Integer v2) {
				return v1 + v2 - x;
			}
		};

		ClosureView c = mixer.toClosure();
		out("expects " + c.getExpectedArgs() + " arguments");
		out("types are ");
		for (Class clazz : mixer.getArgumentTypes()) {
			outN(clazz.getName());
		}
		out("\n");

		try {
			out(c.run(20, 30)); // 40
		//	out(c.run(1));      // fail!
		} catch (ClosureException ex) {
			err(ex.getMessage());
			die(10);
		}
	}
}
