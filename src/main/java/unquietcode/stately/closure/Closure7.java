package unquietcode.stately.closure;

import unquietcode.stately.closure.view.Closure7View;

/**
 * @author Benjamin Fagin
 * @version 01-23-2011
 */
public interface Closure7<Z, A,B,C,D,E,F,G> extends ClosureInterfaceBase<Z> {
	Z run(A p1, B p2, C p3, D p4, E p5, F p6, G p7);
	Closure7View<Z, A,B,C,D,E,F,G> getView();
	Class[] getArgumentTypes();
}
