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
public abstract class AbstractClosure4<Z, A,B,C,D> extends ClosureBase<Z> implements Closure4<Z, A,B,C,D> {
	public abstract Z run(A p1, B p2, C p3, D p4);

	public AbstractClosure4(Object... args) {
		super(args);
	}

	public final Class[] getArgumentTypes() {
		return this.getArgumentTypes(AbstractClosure4.class);
	}

	@SuppressWarnings("unchecked")
	public final Closure<Z> toClosure() {
		ClosureBase base = wrapped ? (ClosureBase) this.arg(1) : this;

		AbstractClosure closure = new AbstractClosure(this) {
			AbstractClosure4 c4 = (AbstractClosure4) arg(1);

			@Override
			public Z run(Object...args) {
				return (Z) c4.run(args[0], args[1], args[2], args[3]);
			}
		};

		closure.wrapped = true;
		closure.setExpectedArgs(4);
		return closure;
	}
}
