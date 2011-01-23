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
 * @author  Ben
 * @version Dec 10, 2010
 */
public abstract class AbstractClosure6<Z, A,B,C,D,E,F> extends ClosureBase<Z> implements Closure6<Z, A,B,C,D,E,F> {
	public abstract Z run(A p1, B p2, C p3, D p4, E p5, F p6);

	public AbstractClosure6(Object... args) {
		super(args);
	}

	public final Class[] getArgumentTypes() {
		return this.getArgumentTypes(AbstractClosure6.class);
	}

	@SuppressWarnings("unchecked")
	public final Closure<Z> toClosure() {
		ClosureBase base = wrapped ? (ClosureBase) this.arg(1) : this;

		AbstractClosure closure = new AbstractClosure(base) {
			AbstractClosure6 c6 = (AbstractClosure6) arg(1);

			@Override
			public Z run(Object...args) {
				return (Z) c6.run(args[0], args[1], args[2], args[3], args[4], args[5]);
			}
		};

		closure.wrapped = true;
		closure.setExpectedArgs(6);
		return closure;
	}
}
