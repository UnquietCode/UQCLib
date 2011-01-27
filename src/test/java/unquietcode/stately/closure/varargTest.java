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
 * @version Dec 10, 2010
 */
public class varargTest {
	@Test
	public void varargTestX() {
		out(tryit());                     // 0
		out(tryit(1));                    // 1
		out(tryit(1, 2));                 // 2
	  //out(tryit(null));                 // NullPointerException
		out(tryit(new Object[] {}));      // 0
		out(tryit(new Object[] {null}));  // 1

		out(catchNull(null));             // 1
		out(catchNull(null, null));		 // 2

		out(whoGetsIt());
		out(whoGetsIt(null));
		out(whoGetsIt(new Object[] {}));
	}

	private String tryit(Object...args) {
		return args.length + "";
	}

	private String catchNull(Object...args) {
		if (args == null)
			return 1 + " (null)";
		else
			return args.length + "";
	}

	private String whoGetsIt() {
		return "empty";
	}

	private String whoGetsIt(Object...args) {
		return "varags";
	}

	@Test
	public void arrayPassingTest() {
		String arr[] = {"Alice", "Bob"};

		out(arrayPass(arr));
		out(arrayPass(arr, "Steve"));
		out(arrayPass("Alice", "Bob"));
	}

	@Test
	public void objectArray() {
		String arr[] = {"Alice", "Bob"};
		Object x = arr;

		out(arrayPass(arr));
		out(arrayPass("work"," thing"));
		out(arrayPass(new Object[]{"Potato", "It's not a tuber!"}));
		out(arrayPass(x));
	}


	private String arrayPass(Object...args) {
		return args.length + "";
	}

}
