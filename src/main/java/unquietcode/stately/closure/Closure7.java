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
 * @author Ben
 * @version 0.1
 *          Date: Dec 10, 2010
 */
public abstract class Closure7<Z, A,B,C,D,E,F,G> extends ClosureBase<Z> {
	public abstract Z run(A p1, B p2, C p3, D p4, E p5, F p6, G p7);

	public Closure7(Object...args) {
		super(args);
	}

	@SuppressWarnings("unchecked")
	public Closure<Z> toClosure() {
		ClosureBase base = this;
		while (base.depth > 0)
			base = (ClosureBase) base.arg(1);

		Closure closure = new Closure(base) {
			Closure7 c7 = (Closure7) arg(1);

			@Override
			public Z run(Object...args) {
				return (Z) c7.run(args[0], args[1], args[2], args[3], args[4], args[5], args[6]);
			}
		};

		closure.depth = base.depth + 1;
		closure.setExpectedArgs(7);
		return closure;
	}
}
