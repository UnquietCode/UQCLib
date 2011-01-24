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
import unquietcode.stately.closure.view.Closure3View;
import unquietcode.stately.closure.view.ClosureView;

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
	public final Closure3View<Z, A,B,C> getView() {
		final Closure3 base = this;

		return new Closure3View() {
			public Object run(Object p1, Object p2, Object p3) {
				return base.run(p1, p2, p3);
			}

			public Class[] getArgumentTypes() {
				return base.getArgumentTypes();
			}
		};
	}

	@SuppressWarnings("unchecked")
	public final ClosureView<Z> toClosure() {
		final Closure3 base = this;

		return new ClosureView<Z>() {
			public Z run(Object...args) {
				return (Z) base.run(args[0], args[1], args[2]);
			}

			public int getExpectedArgs() {
				return 3;
			}

			public Class[] getArgumentTypes() {
				return base.getArgumentTypes();
			}
		};
	}
}
