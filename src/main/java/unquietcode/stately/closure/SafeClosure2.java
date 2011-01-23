package unquietcode.stately.closure;

/**
 * @author  Benjamin Fagin
 * @version 01-22-2011
 */
public interface SafeClosure2<Z, A, B> extends ClosureBase<Z> {

	Z run(A p1, B p2);

}
