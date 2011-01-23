package unquietcode.stately.closure;

/**
 * @author Benjamin Fagin
 * @version 01-23-2011
 */
public interface Closure3<Z, A,B,C> extends ClosureInterfaceBase<Z> {
	void curry(int var, Object replacement);
	Class[] getArgumentTypes();
}
