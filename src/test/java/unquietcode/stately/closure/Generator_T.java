package unquietcode.stately.closure;

import org.junit.Test;
import unquietcode.stately.Generator;
import unquietcode.stately.closure.view.Closure0View;
import unquietcode.stately.closure.view.ClosureView;

import static unquietcode.util.Shortcuts.out;
import static unquietcode.util.Shortcuts.outN;

/**
 * @author Benjamin Fagin
 * @version 01-26-2011
 */
public class Generator_T {
	@Test
	public void fullDeclare() {
		ClosureView<Integer> view = (new AbstractClosure0<Integer>() {
			int start = 0;
			int step = 2;

			public @Override Integer run() {
				int current = start;

				if (current < 0) // overflow (can that happen in Java?)
					current = 0;

				start += step;
				return current;
			}
		}).toClosure();
		Generator<Integer> gen = new Generator<Integer>(view);

		for (int i=0; i < 20; ++i) {
			outN(gen.next() + " ");
		}
	}


	@Test
	public void overridden() {
		Closure0View<Long> fibonacci = (new AbstractClosure0<Long>() {
			long a = 0;
			long b = 1;
			long c;

			public Long run() {
				long temp = a;
				c = a + b;
				a = b;
				b = c;

				return temp;
			}
		}).getView();


		// can override hasNext if desired, as well as a 'custom' method to do, well, whatever you want really
		Generator<Long> gen = new Generator<Long>(fibonacci) {
			int cur = 0;
			int max = 10;

			public @Override boolean hasNext() {
				if (cur < max) {
					cur += 1;
					return true;
				} else {
					return false;
				}
			}

			// I'll use the custom method to set the max and reset the current
			public @Override Object custom(Object...args) {
				int old = max;
				max = (Integer) args[0];
				cur = 0;
				return old;
			}
		};

		// 0 to 34
		while (gen.hasNext()) {
			outN(gen.next() + ", ");
		}

		// reset and set max to 15
		gen.custom(15);
		out();

		// 55 to 46,368
		while (gen.hasNext()) {
			outN(gen.next() + ", ");
		}
	}
}
