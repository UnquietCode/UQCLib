package unquietcode.stately.closure;

import unquietcode.stately.closure.view.Closure5View;

/**
 * @author Benjamin Fagin
 * @version 01-23-2011
 */
public interface Closure5<Z, A,B,C,D,E> extends ClosureInterfaceBase<Z> {
	Z run(A p1, B p2, C p3, D p4, E p5);
	Closure5View<Z, A,B,C,D,E> getView();
}
