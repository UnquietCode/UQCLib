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
 * @author  Benjamin Fagin
 * @version  Dec 10, 2010
 */
public abstract class AbstractClosure8<Z, A,B,C,D,E,F,G,H> extends ClosureBase<Z> implements Closure8<Z, A,B,C,D,E,F,G,H> {
	public abstract Z run(A p1, B p2, C p3, D p4, E p5, F p6, G p7, H p8);

	public AbstractClosure8(Object... args) {
		super(args);

	}

	public final Class[] getArgumentTypes() {
		return super.getArgumentTypes(AbstractClosure8.class);
	}

	@SuppressWarnings("unchecked")
	public final Closure<Z> toClosure() {
		ClosureBase base = wrapped ? (ClosureBase) this.arg(1) : this;

		AbstractClosure closure = new AbstractClosure(base) {
			AbstractClosure8 c8 = (AbstractClosure8) arg(1);

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