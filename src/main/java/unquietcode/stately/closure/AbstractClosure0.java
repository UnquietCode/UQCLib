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

import unquietcode.stately.closure.view.Closure0View;
import unquietcode.stately.closure.view.ClosureView;

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
	public final Closure0View<Z> getView() {
		final Closure0 base = this;

		return new Closure0View() {
			public Object run() {
				return base.run();
			}

			public Class[] getArgumentTypes() {
				return base.getArgumentTypes();
			}
		};
	}

	@SuppressWarnings("unchecked")
	public final ClosureView<Z> toClosure() {
		final Closure0 base = this;

		return new ClosureView<Z>() {
			public Z run(Object...args) {
				return (Z) base.run();
			}

			public int getExpectedArgs() {
				return 0;
			}

			public Class[] getArgumentTypes() {
				return base.getArgumentTypes();
			}
		};
	}
}
