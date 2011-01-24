package unquietcode.stately.closure;

import unquietcode.stately.closure.view.Closure1View;

/**
 * @author Benjamin Fagin
 * @version 01-23-2011
 */
public interface Closure1<Z, A> extends ClosureInterfaceBase<Z> {
	Z run(A p1);
	Closure1View<Z, A> getView();
}
