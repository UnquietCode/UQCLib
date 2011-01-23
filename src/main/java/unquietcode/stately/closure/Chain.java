package unquietcode.stately.closure;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Concept and naming from Pipes for Java.
 *
 * Normally, will run by using
 *
 *
 * @author  Benjamin Fagin
 * @version 12-31-2010
 */
public class Chain<Z> {
	List<AbstractClosure> chain = new LinkedList<AbstractClosure>();


	Chain(AbstractClosure...closures) {
		for (AbstractClosure c : closures) {
			if (c == null) { throw new IllegalArgumentException("Closures must be non-null to form a chain."); }
			chain.add(c);
		}
	}


	public Chain prepend(AbstractClosure...closures) {
		for (AbstractClosure c: closures) {
			if (c == null) {
				throw new IllegalArgumentException("Closures must be non-null to attach to a chain.");
			}
		}

		List clist = Arrays.asList(closures);
		clist.addAll(chain);
		chain = clist;
		return this;
	}

	public Chain append(AbstractClosure...closures) {
		for (AbstractClosure c: closures) {
			if (c == null) {
				throw new IllegalArgumentException("Closures must be non-null to attach to a chain.");
			}
		}

		List clist = Arrays.asList(closures);
		chain.addAll(clist);
		return this;
	}


	

	/*
	 *  chains can be combined as
	 *
	 *  chain -> chain (one to one)
	 *  chain -> chains (one to many, in parallel, "nozzle")
	 *  chains -> chain (many to one, "funnel")
	 */


	/*
	@SuppressWarnings("unchecked")
	public Z run(Object...args) {
		return (Z) c2.run(c1.run(args));
	}*/

	/*
		Closure + closure   ->  pipe, single
		Closure + pipe      ->  pipe
		pipe + closure
		pipe + pipe
		mixed
	 */
}
