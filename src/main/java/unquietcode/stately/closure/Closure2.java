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
public abstract class Closure2<Z, A,B> extends ClosureBase<Z> implements SafeClosure2 {
	public abstract Z run(A p1, B p2);

	public Closure2(Object...args) {
		super(args);
	}

	public final Class[] getArgumentTypes() {
		return this.getArgumentTypes(Closure2.class);
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

		Closure closure = new Closure(base) {
			Closure2 c2 = (Closure2) arg(1);

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
