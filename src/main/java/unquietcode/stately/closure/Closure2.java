package unquietcode.stately.closure;

import unquietcode.stately.closure.view.Closure2View;

/**
 * @author Benjamin Fagin
 * @version 01-23-2011
 */
public interface Closure2<Z, A,B> extends ClosureInterfaceBase<Z> {
	Z run(A p1, B p2);
	Closure2View<Z, A,B> getView();
}
