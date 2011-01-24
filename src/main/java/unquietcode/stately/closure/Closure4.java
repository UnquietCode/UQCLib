package unquietcode.stately.closure;

import unquietcode.stately.closure.view.Closure4View;

/**
 * @author Benjamin Fagin
 * @version 01-23-2011
 */
public interface Closure4<Z, A,B,C,D> extends ClosureInterfaceBase<Z> {
	Z run(A p1, B p2, C p3, D p4);
	Closure4View<Z, A,B,C,D> getView();
}
