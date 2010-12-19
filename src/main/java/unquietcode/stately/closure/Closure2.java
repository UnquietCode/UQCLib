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
public abstract class Closure2<Z, A,B> extends ClosureBase<Z> {
	public abstract Z run(A p1, B p2);

	public Closure2(Object...args) {
		super(args);
	}

	@SuppressWarnings("unchecked")
	public Closure<Z> toClosure() {
		ClosureBase base = wrapped ? (ClosureBase) this.arg(1) : this;

		Closure closure = new Closure(base) {
			Closure2 c2 = (Closure2) arg(1);

			@Override
			public Z run(Object...args) {
				return (Z) c2.run(args[0], args[1]);
			}
		};

		closure.wrapped = true;
		closure.setExpectedArgs(2);
		return closure;
	}
}
