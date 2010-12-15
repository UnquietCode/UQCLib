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
 * Date: Dec 10, 2010
 */
public abstract class Closure0<Z> extends ClosureBase<Z> {
	public abstract Z run();

	public Closure0(Object...args) {
		super(args);
	}
	
	@SuppressWarnings("unchecked")
	public Closure<Z> toClosure() {
		ClosureBase base = this;
		while (base.depth > 0)
			base = (ClosureBase) base.arg(1);

		Closure closure = new Closure(base) {
			Closure0 c0 = (Closure0) arg(1);

			@Override
			public Z run(Object...args) {
				return (Z) c0.run();
			}
		};

		closure.depth = base.depth + 1;
		closure.setExpectedArgs(0);
		return closure;
	}
}
