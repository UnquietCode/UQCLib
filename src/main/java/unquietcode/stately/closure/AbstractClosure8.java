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

import unquietcode.stately.closure.view.Closure8View;
import unquietcode.stately.closure.view.ClosureView;

/**
 * @author  Benjamin Fagin
 * @version Dec 10, 2010
 */
public abstract class AbstractClosure8<Z, A,B,C,D,E,F,G,H> extends ClosureBase<Z> implements Closure8<Z, A,B,C,D,E,F,G,H> {
	public abstract Z run(A p1, B p2, C p3, D p4, E p5, F p6, G p7, H p8);

	public AbstractClosure8(Object...args) {
		super(args);

	}

	public final Class[] getArgumentTypes() {
		return super.getArgumentTypes(AbstractClosure8.class);
	}

	@SuppressWarnings("unchecked")
	public final Closure8View<Z, A,B,C,D,E,F,G,H> getView() {
		final Closure8 base = this;

		return new Closure8View() {
			public Object run(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
				return base.run(p1, p2, p3, p4, p5, p6, p7, p8);
			}

			public Class[] getArgumentTypes() {
				return base.getArgumentTypes();
			}
		};
	}

	@SuppressWarnings("unchecked")
	public final ClosureView<Z> toClosure() {
		final Closure8 base = this;

		return new ClosureView<Z>() {
			public Z run(Object...args) {
				return (Z) base.run(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7]);
			}

			public int getExpectedArgs() {
				return 8;
			}

			public Class[] getArgumentTypes() {
				return base.getArgumentTypes();
			}
		};
	}
}
