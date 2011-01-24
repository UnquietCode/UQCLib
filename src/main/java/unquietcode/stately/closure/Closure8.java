package unquietcode.stately.closure;

import unquietcode.stately.closure.view.Closure8View;

/**
 * @author Benjamin Fagin
 * @version 01-23-2011
 */
public interface Closure8<Z, A,B,C,D,E,F,G,H> extends ClosureInterfaceBase<Z> {
	Z run(A p1, B p2, C p3, D p4, E p5, F p6, G p7, H p8);
	Closure8View<Z, A,B,C,D,E,F,G,H> getView();
	Class[] getArgumentTypes();
}
