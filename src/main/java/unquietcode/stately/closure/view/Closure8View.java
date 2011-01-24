package unquietcode.stately.closure.view;

/**
 * @author  Benjamin Fagin
 * @version 01-23-2011
 */
public interface Closure8View<Z, A,B,C,D,E,F,G,H> extends ClosureViewBase<Z> {
	Z run(A p1, B p2, C p3, D p4, E p5, F p6, G p7, H p8);
	Class[] getArgumentTypes();
}
