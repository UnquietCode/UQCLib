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

/**
 * @author Ben
 * @version 0.1
 *          Date: Dec 10, 2010
 */
public class varargTest {
	@Test
	public void varargTestX() {
		System.out.println(tryit());                     // 0
		System.out.println(tryit(1));                    // 1
		System.out.println(tryit(1, 2));                 // 2
	  //System.out.println(tryit(null));                 // NullPointerException
		System.out.println(tryit(new Object[] {}));      // 0
		System.out.println(tryit(new Object[] {null}));  // 1

		System.out.println(catchNull(null));             // 1
		System.out.println(catchNull(null, null));		 // 2

		System.out.println(whoGetsIt());
		System.out.println(whoGetsIt(null));
		System.out.println(whoGetsIt(new Object[] {}));
	}

	private String whoGetsIt() {
		return "empty";
	}

	private String whoGetsIt(Object...args) {
		return "varags";
	}


	private String tryit(Object...args) {
		return args.length + "";
	}

	private String catchNull(Object...args) {
		if (args == null)
			return 1 + "";
		else
			return args.length + "";
	}
}
