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

import unquietcode.stately.closure.view.Closure6View;
import unquietcode.stately.closure.view.Closure7View;
import unquietcode.stately.closure.view.ClosureView;

/**
 * @author Ben
 * @version 0.1
 *          Date: Dec 10, 2010
 */
public abstract class AbstractClosure7<Z, A,B,C,D,E,F,G> extends ClosureBase<Z> implements Closure7<Z, A,B,C,D,E,F,G> {
	public abstract Z run(A p1, B p2, C p3, D p4, E p5, F p6, G p7);

	public AbstractClosure7(Object... args) {
		super(args);
	}

	public final Class[] getArgumentTypes() {
		return super.getArgumentTypes(AbstractClosure7.class);
	}

	@SuppressWarnings("unchecked")
	public final Closure7View<Z, A,B,C,D,E,F,G> getView() {
		final Closure7 base = this;

		return new Closure7View() {
			public Object run(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
				return base.run(p1, p2, p3, p4, p5, p6, p7);
			}

			public Class[] getArgumentTypes() {
				return base.getArgumentTypes();
			}
		};
	}

	@SuppressWarnings("unchecked")
	public final ClosureView<Z> toClosure() {
		final Closure7 base = this;

		return new ClosureView<Z>() {
			public Z run(Object...args) {
				return (Z) base.run(args[0], args[1], args[2], args[3], args[4], args[5], args[6]);
			}

			public int getExpectedArgs() {
				return 7;
			}

			public Class[] getArgumentTypes() {
				return base.getArgumentTypes();
			}
		};
	}
}
