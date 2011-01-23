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
public abstract class AbstractClosure2<Z, A,B> extends ClosureBase<Z> implements Closure2<Z, A,B> {
	public abstract Z run(A p1, B p2);

	public AbstractClosure2(Object... args) {
		super(args);
	}

	public final Class[] getArgumentTypes() {
		return this.getArgumentTypes(AbstractClosure2.class);
	}

	public Void run(String hello, String world) {
		return null;
	}

	public void curry(int i, String world) {
		//nothing
	}

	@SuppressWarnings("unchecked")
	public final Closure<Z> toClosure() {
		ClosureBase base = wrapped ? (ClosureBase) this.arg(1) : this;

		AbstractClosure closure = new AbstractClosure(base) {
			AbstractClosure2 c2 = (AbstractClosure2) arg(1);

			public Z run(Object...args) {
				if (args.length != 2)
					throw new ClosureException("Invalid number of arguments. Expected 2");
				
				return (Z) c2.run(args[0], args[1]);
			}
		};

		closure.wrapped = true;
		closure.setExpectedArgs(2);
		return closure;
	}
}
