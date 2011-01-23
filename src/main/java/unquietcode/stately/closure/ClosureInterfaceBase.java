package unquietcode.stately.closure;

/**
 * @author  Benjamin Fagin
 * @version 01-23-2011
 */
public interface ClosureInterfaceBase<Z> {
	void curry(int var, Object replacement);
	Class[] getArgumentTypes();
	Closure<Z> toClosure();
}
