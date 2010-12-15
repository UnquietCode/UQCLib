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
public abstract class Closure4<Z, A,B,C,D> extends ClosureBase<Z> {
	public abstract Z run(A p1, B p2, C p3, D p4);

	public Closure4(Object...args) {
		super(args);
	}

	@SuppressWarnings("unchecked")
	public Closure<Z> toClosure() {
		ClosureBase base = this;
		while (base.depth > 0)
			base = (ClosureBase) base.arg(1);

		Closure closure = new Closure(this) {
			Closure4 c4 = (Closure4) arg(1);

			@Override
			public Z run(Object...args) {
				return (Z) c4.run(args[0], args[1], args[2], args[3]);
			}
		};

		closure.depth = base.depth + 1;
		closure.setExpectedArgs(4);
		return closure;
	}
}
