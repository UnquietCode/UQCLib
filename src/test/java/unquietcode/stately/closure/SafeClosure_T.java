package unquietcode.stately.closure;

import org.junit.Test;

import static unquietcode.util.Shortcuts.out;

/**
 * @author  Benjamin Fagin
 * @version 01-22-2011
 */
public class SafeClosure_T {
	@Test
	public void basicTest() {
		SafeClosure2 c2 = new Closure2() {
			@Override
			public Object run(Object p1, Object p2) {
				return "hello";
			}
		};





	}

	@Test
	public void featureProvide() {
		SafeClosure2 c2 = new Closure2() {
			@Override
			public Object run(Object p1, Object p2) {
				return "hello";
			}
		};

		// normal
		out(c2.run("Hello", "world."));
		c2.curry(1, "Goodbye");     // Hello world.
		c2.curry(1, "world!");		// Goodbye world!
	}


}
