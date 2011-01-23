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
public abstract class AbstractClosure7<Z, A,B,C,D,E,F,G> extends ClosureBase<Z> implements Closure7<Z, A,B,C,D,E,F,G> {
	public abstract Z run(A p1, B p2, C p3, D p4, E p5, F p6, G p7);

	public AbstractClosure7(Object... args) {
		super(args);
	}

	public final Class[] getArgumentTypes() {
		return this.getArgumentTypes(AbstractClosure7.class);
	}

	@SuppressWarnings("unchecked")
	public final Closure<Z> toClosure() {
		ClosureBase base = wrapped ? (ClosureBase) this.arg(1) : this;

		AbstractClosure closure = new AbstractClosure(base) {
			AbstractClosure7 c7 = (AbstractClosure7) arg(1);

			@Override
			public Z run(Object...args) {
				return (Z) c7.run(args[0], args[1], args[2], args[3], args[4], args[5], args[6]);
			}
		};

		closure.wrapped = true;
		closure.setExpectedArgs(7);
		return closure;
	}
}
