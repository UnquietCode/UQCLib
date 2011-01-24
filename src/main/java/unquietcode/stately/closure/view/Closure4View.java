package unquietcode.stately.closure.view;

/**
 * @author  Benjamin Fagin
 * @version 01-23-2011
 */
public interface Closure4View<Z, A,B,C,D> extends ClosureViewBase<Z> {
	Z run(A p1, B p2, C p3, D p4);
	Class[] getArgumentTypes();
}
