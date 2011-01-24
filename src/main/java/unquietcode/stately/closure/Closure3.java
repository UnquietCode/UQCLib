package unquietcode.stately.closure;

import unquietcode.stately.closure.view.Closure3View;

/**
 * @author Benjamin Fagin
 * @version 01-23-2011
 */
public interface Closure3<Z, A,B,C> extends ClosureInterfaceBase<Z> {
	Z run(A p1, B p2, C p3);
	Closure3View<Z, A,B,C> getView();
}
