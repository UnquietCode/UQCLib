package unquietcode.stately.closure;

import unquietcode.stately.closure.view.Closure6View;

/**
 * @author Benjamin Fagin
 * @version 01-23-2011
 */
public interface Closure6<Z, A,B,C,D,E,F> extends ClosureInterfaceBase<Z> {
	Z run(A p1, B p2, C p3, D p4, E p5, F p6);
	Closure6View<Z, A,B,C,D,E,F> getView();
}
