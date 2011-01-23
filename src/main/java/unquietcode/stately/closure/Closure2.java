package unquietcode.stately.closure;

/**
 * @author Benjamin Fagin
 * @version 01-23-2011
 */
public interface Closure2<Z, A,B> extends ClosureInterfaceBase<Z> {
	Z run(A p1, B p2);
}
