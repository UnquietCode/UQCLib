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

/**
 * @author Benjamin Fagin
 * Date: Dec 10, 2010
 */
public abstract class Closure3<Z, A,B,C> extends ClosureBase<Z> {
	public abstract Z run(A p1, B p2, C p3);

	public Closure3(Object...args) {
		super(args);
	}

	@SuppressWarnings("unchecked")
	public Closure<Z> toClosure() {
		ClosureBase base = this;
		while (base.depth > 0)
			base = (ClosureBase) base.arg(1);
		
		Closure closure = new Closure(base) {
			Closure3 c3 = (Closure3) arg(1);

			@Override
			public Z run(Object...args) {
				return (Z) c3.run(args[0], args[1], args[2]);
			}
		};

		closure.depth = base.depth + 1;
		closure.setExpectedArgs(3);
		return closure;
	}
}
