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
public abstract class AbstractClosure3<Z, A,B,C> extends ClosureBase<Z> implements Closure3<Z, A,B,C>{
	public abstract Z run(A p1, B p2, C p3);

	public AbstractClosure3(Object... args) {
		super(args);
	}

	public final Class[] getArgumentTypes() {
		return super.getArgumentTypes(AbstractClosure3.class);
	}

	@SuppressWarnings("unchecked")
	public final Closure<Z> toClosure() {
		ClosureBase base = wrapped ? (ClosureBase) this.arg(1) : this;
		
		AbstractClosure closure = new AbstractClosure(base) {
			AbstractClosure3 c3 = (AbstractClosure3) arg(1);

			@Override
			public Z run(Object...args) {
				return (Z) c3.run(args[0], args[1], args[2]);
			}
		};

		closure.wrapped = true;
		closure.setExpectedArgs(3);
		return closure;
	}
}
