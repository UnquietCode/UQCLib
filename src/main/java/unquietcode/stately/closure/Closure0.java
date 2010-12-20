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
public abstract class Closure0<Z> extends ClosureBase<Z> {
	public abstract Z run();

	public Closure0(Object...args) {
		super(args);
	}
	
	@SuppressWarnings("unchecked")
	public Closure<Z> toClosure() {
		ClosureBase base = wrapped ? (ClosureBase) this.arg(1) : this;

		Closure closure = new Closure(base) {
			Closure0 c0 = (Closure0) arg(1);

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
