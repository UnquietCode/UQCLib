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
 * Date: Dec 10, 2010
 */
public abstract class Closure5<Z, A,B,C,D,E> extends ClosureBase<Z> {
	public abstract Z run(A p1, B p2, C p3, D p4, E p5);

	public Closure5(Object...args) {
		super(args);
	}

	@SuppressWarnings("unchecked")
	public Closure<Z> toClosure() {
		ClosureBase base = this;
		while (base.depth > 0)
			base = (ClosureBase) base.arg(1);

		Closure closure = new Closure(base) {
			Closure5 c5 = (Closure5) arg(1);

			@Override
			public Z run(Object...args) {
				return (Z) c5.run(args[0], args[1], args[2], args[3], args[4]);
			}
		};

		closure.depth = base.depth + 1;
		closure.setExpectedArgs(5);
		return closure;
	}
}
