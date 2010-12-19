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
 *
 */
public abstract class Closure1<Z, A> extends ClosureBase<Z> {
	public abstract Z run(A p1);

	public Closure1(Object...args) {
		super(args);
	}

	@SuppressWarnings("unchecked")
	public Closure<Z> toClosure() {
		ClosureBase base = wrapped ? (ClosureBase) this.arg(1) : this;

		Closure closure = new Closure(base) {
			Closure1 c1 = (Closure1) arg(1);

			@Override
			public Z run(Object...args) {
				return (Z) c1.run(args[0]);
			}
		};

		closure.wrapped = true;
		closure.setExpectedArgs(1);
		return closure;
	}
}
