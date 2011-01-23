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
 * @version Dec 10, 2010
 */
public abstract class AbstractClosure0<Z> extends ClosureBase<Z> implements Closure0<Z> {
	public abstract Z run();

	public AbstractClosure0(Object...args) {
		super(args);
	}

	public final Class[] getArgumentTypes() {
		return new Class[0];
	}

	@SuppressWarnings("unchecked")
	public final Closure<Z> toClosure() {
		ClosureBase base = wrapped ? (ClosureBase) this.arg(1) : this;

		AbstractClosure closure = new AbstractClosure(base) {
			AbstractClosure0 c0 = (AbstractClosure0) arg(1);

			@Override
			public Z run(Object...args) {
				return (Z) c0.run();
			}
		};

		closure.wrapped = true;
		closure.setExpectedArgs(0);
		return closure;
	}
}
