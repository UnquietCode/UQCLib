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
import unquietcode.stately.closure.view.ClosureView;

/**
 * @author  Benjamin Fagin
 * @version Dec 10, 2010
 *
 */
public abstract class AbstractClosure1<Z, A> extends ClosureBase<Z> implements Closure1<Z, A> {
	public abstract Z run(A p1);

	public AbstractClosure1(Object...args) {
		super(args);
	}

	public final Class[] getArgumentTypes() {
		return super.getArgumentTypes(AbstractClosure1.class);
	}

	@SuppressWarnings("unchecked")
	public final Closure1View<Z, A> getView() {
		final Closure1 base = this;

		return new Closure1View() {
			public Object run(Object p1) {
				return base.run(p1);
			}

			public Class[] getArgumentTypes() {
				return base.getArgumentTypes();
			}
		};
	}

	@SuppressWarnings("unchecked")
	public final ClosureView<Z> toClosure() {
		final Closure1 base = this;

		return new ClosureView<Z>() {
			public Z run(Object...args) {
				return (Z) base.run(args[0]);
			}

			public int getExpectedArgs() {
				return 1;
			}

			public Class[] getArgumentTypes() {
				return base.getArgumentTypes();
			}
		};
	}
}

