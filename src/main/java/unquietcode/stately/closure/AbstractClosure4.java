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

import unquietcode.stately.closure.view.Closure3View;
import unquietcode.stately.closure.view.Closure4View;
import unquietcode.stately.closure.view.ClosureView;

/**
 * @author Benjamin Fagin
 * Date: Dec 10, 2010
 */
public abstract class AbstractClosure4<Z, A,B,C,D> extends ClosureBase<Z> implements Closure4<Z, A,B,C,D> {
	public abstract Z run(A p1, B p2, C p3, D p4);

	public AbstractClosure4(Object... args) {
		super(args);
	}

	public final Class[] getArgumentTypes() {
		return super.getArgumentTypes(AbstractClosure4.class);
	}

	@SuppressWarnings("unchecked")
	public final Closure4View<Z, A,B,C,D> getView() {
		final Closure4 base = this;

		return new Closure4View() {
			public Object run(Object p1, Object p2, Object p3, Object p4) {
				return base.run(p1, p2, p3, p4);
			}

			public Class[] getArgumentTypes() {
				return base.getArgumentTypes();
			}
		};
	}

	@SuppressWarnings("unchecked")
	public final ClosureView<Z> toClosure() {
		final Closure4 base = this;

		return new ClosureView<Z>() {
			public Z run(Object...args) {
				return (Z) base.run(args[0], args[1], args[2], args[3]);
			}

			public int getExpectedArgs() {
				return 4;
			}

			public Class[] getArgumentTypes() {
				return base.getArgumentTypes();
			}
		};
	}
}
