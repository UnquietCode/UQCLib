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

import unquietcode.stately.closure.view.Closure5View;
import unquietcode.stately.closure.view.Closure6View;
import unquietcode.stately.closure.view.ClosureView;

/**
 * @author  Ben
 * @version Dec 10, 2010
 */
public abstract class AbstractClosure6<Z, A,B,C,D,E,F> extends ClosureBase<Z> implements Closure6<Z, A,B,C,D,E,F> {
	public abstract Z run(A p1, B p2, C p3, D p4, E p5, F p6);

	public AbstractClosure6(Object... args) {
		super(args);
	}

	public final Class[] getArgumentTypes() {
		return super.getArgumentTypes(AbstractClosure6.class);
	}

	@SuppressWarnings("unchecked")
	public final Closure6View<Z, A,B,C,D,E,F> getView() {
		final Closure6 base = this;

		return new Closure6View() {
			public Object run(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
				return base.run(p1, p2, p3, p4, p5, p6);
			}

			public Class[] getArgumentTypes() {
				return base.getArgumentTypes();
			}
		};
	}

	@SuppressWarnings("unchecked")
	public final ClosureView<Z> toClosure() {
		final Closure6 base = this;

		return new ClosureView<Z>() {
			public Z run(Object...args) {
				return (Z) base.run(args[0], args[1], args[2], args[3], args[4], args[5]);
			}

			public int getExpectedArgs() {
				return 6;
			}

			public Class[] getArgumentTypes() {
				return base.getArgumentTypes();
			}
		};
	}
}
