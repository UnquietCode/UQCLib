package unquietcode.stately.closure.view;

/**
 * @author  Benjamin Fagin
 * @version 01-23-2011
 */
public interface Closure2View<Z, A,B> extends ClosureViewBase<Z> {
	Z run(A p1, B p2);
	Class[] getArgumentTypes();
}
