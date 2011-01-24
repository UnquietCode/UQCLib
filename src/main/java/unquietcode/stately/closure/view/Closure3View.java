package unquietcode.stately.closure.view;

/**
 * @author  Benjamin Fagin
 * @version 01-23-2011
 */
public interface Closure3View<Z, A,B,C> extends ClosureViewBase<Z> {
	Z run(A p1, B p2, C p3);
	Class[] getArgumentTypes();
}
