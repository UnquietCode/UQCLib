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
public abstract class Closure8<Z, A,B,C,D,E,F,G,H> extends ClosureBase<Z> {
	public abstract Z run(A p1, B p2, C p3, D p4, E p5, F p6, G p7, H p8);

	public Closure8(Object...args) {
		super(args);

	}

	public final Class[] getArgumentTypes() {
		return this.getArgumentTypes(Closure8.class);
	}

	@SuppressWarnings("unchecked")
	public final Closure<Z> toClosure() {
		ClosureBase base = wrapped ? (ClosureBase) this.arg(1) : this;

		Closure closure = new Closure(base) {
			Closure8 c8 = (Closure8) arg(1);

			@Override
			public Z run(Object...args) {
				return (Z) c8.run(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7]);
			}
		};

		closure.wrapped = true;
		closure.setExpectedArgs(8);
		return closure;
	}
}
