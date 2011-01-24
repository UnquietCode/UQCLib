package unquietcode.stately.closure;

import org.junit.Test;
import unquietcode.stately.closure.view.Closure0View;
import unquietcode.stately.closure.view.Closure1View;
import unquietcode.stately.closure.view.Closure2View;
import unquietcode.stately.closure.view.ClosureView;

import static unquietcode.util.Shortcuts.out;
import static unquietcode.util.Shortcuts.outN;

/**
 * @author Benjamin Fagin
 * @version 01-23-2011
 */
public class ClosureViews_T {
	@Test
	public void basicViews() {
		// Views are closures shielded by an interface. They are linked to their parent closure,
		// however they cannot modify it nor retrieve it. Being views, if the original closure
		// is modified, the view will be modified. This goes for currying as well as mutable
		// arguments.

		Closure1<Integer, Integer> magnify = new AbstractClosure1<Integer, Integer>() {
			public Integer run(Integer val) {
				return 10 * val;
			}
		};

		// get a view
		Closure1View<Integer, Integer> view1 = magnify.getView();
		out(view1.run(12));  // 12 * 10 = 120

		// of course, typing is not required
		Closure1View view2 = magnify.getView();
		out(view2.run(15));  // 12 * 10 = 120

		// All closure classes can also return a ClosureView for unification purposes.
		ClosureView view3 = magnify.toClosure();
		out("\nExpected arguments: " + view3.getExpectedArgs());
		out(view3.run(4));              // 4 * 10 = 40
		out(view3.run(4, 8, 12, 20));   // additional arguments are ignored
	}

	@Test
	public void instaView() {
		// If the closure isn't needed, just leverage Java's syntax to ditch it.
		// Remember though, the reference to the original closure is lost. No modifications
		// can be made (except inside). This is also a good way to ensure that the view
		// will never be adversely affected by the parent closure being mutated.
		// This is NOT a defense against mutable arguments!

		Closure1View<Integer, Integer> view1 = (new AbstractClosure1<Integer, Integer>() {
			public Integer run(Integer val) {
				return 10 * val;
			}
		}).getView();

		out(view1.run(12));  // 12 * 10 = 120
	}

	@Test
	public void fibonacciGenerator() {
		// Just to clarify, while currying can be done from within, it is slow
		// and definitely the wrong way to do it.

		Closure0View<Long> fibonacci = (new AbstractClosure0<Long>() {
			long a = 0;
			long b = 1;
			long c;

			public Long run() {
				long temp = a;
				c = a + b;
				a = b;              // The correct way.
				this.curry(2, c);   // Why oh why would you do this?!

				return temp;
			}
		}).getView();

		for (int i=0; i < 20; ++i) {
			outN(fibonacci.run());
			outN(", ");
		}
	}

	@Test
	public void curryViewExample() {
		// Views are tied to specific closures. If the original parent closure is modified, the
		// view will respond accordingly (or perhaps the better term is 'unknowingly').
		// Therefore it is best not to consider a view as immutable.

		// Views will also reflect changes to mutable arguments in the original closure.
		String greeting[] = {"Hello"};

		Closure0<String> helloBot = new AbstractClosure0<String>(greeting) {
			String name = "Bob";

			public String run() {
				Object x = a1();
				System.err.println(x.getClass());

				//String front[] = a1();
				//return front[0] + " " + name;
				return "";
			}
		};

		// using a view
		Closure0View<String> view = helloBot.getView();
		out(view.run());      // Hello Bob

		// curried
		greeting[0] = "Goodbye";
		helloBot.curry(1, "Alice");
		out(view.run());      // Goodbye Alice
	}

}
