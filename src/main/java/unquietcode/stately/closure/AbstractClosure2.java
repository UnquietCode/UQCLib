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

import unquietcode.stately.closure.view.Closure1View;
import unquietcode.stately.closure.view.Closure2View;
import unquietcode.stately.closure.view.ClosureView;

/**
 * @author  Benjamin Fagin
 * @version Dec 10, 2010
 */
public abstract class AbstractClosure2<Z, A,B> extends ClosureBase<Z> implements Closure2<Z, A,B> {
	public abstract Z run(A p1, B p2);

	public AbstractClosure2(Object...args) {
		super(args);
	}

	public final Class[] getArgumentTypes() {
		return super.getArgumentTypes(AbstractClosure2.class);
	}

	public Void run(String hello, String world) {
		return null;
	}

	public void curry(int i, String world) {
		//nothing
	}

	@SuppressWarnings("unchecked")
	public final Closure2View<Z, A,B> getView() {
		final Closure2 base = this;

		return new Closure2View() {
			public Object run(Object p1, Object p2) {
				return base.run(p1, p2);
			}

			public Class[] getArgumentTypes() {
				return base.getArgumentTypes();
			}
		};
	}

	@SuppressWarnings("unchecked")
	public final ClosureView<Z> toClosure() {
		final Closure2 base = this;

		return new ClosureView<Z>() {
			public Z run(Object...args) {
				return (Z) base.run(args[0], args[1]);
			}

			public int getExpectedArgs() {
				return 2;
			}

			public Class[] getArgumentTypes() {
				return base.getArgumentTypes();
			}
		};
	}
}
