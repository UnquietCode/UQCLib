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
public abstract class AbstractClosure5<Z, A,B,C,D,E> extends ClosureBase<Z> implements Closure5<Z, A,B,C,D,E> {
	public abstract Z run(A p1, B p2, C p3, D p4, E p5);

	public AbstractClosure5(Object... args) {
		super(args);
	}

	public final Class[] getArgumentTypes() {
		return super.getArgumentTypes(AbstractClosure5.class);
	}

	@SuppressWarnings("unchecked")
	public final AbstractClosure<Z> toClosure() {
		ClosureBase base = wrapped ? (ClosureBase) this.arg(1) : this;

		AbstractClosure closure = new AbstractClosure(base) {
			AbstractClosure5 c5 = (AbstractClosure5) arg(1);

			@Override
			public Z run(Object...args) {
				return (Z) c5.run(args[0], args[1], args[2], args[3], args[4]);
			}
		};

		closure.wrapped = true;
		closure.setExpectedArgs(5);
		return closure;
	}
}
