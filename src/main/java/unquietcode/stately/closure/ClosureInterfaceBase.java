package unquietcode.stately.closure;

import unquietcode.stately.closure.view.ClosureView;

/**
 * @author  Benjamin Fagin
 * @version 01-23-2011
 */
public interface ClosureInterfaceBase<Z> {
	void curry(int var, Object replacement);
	ClosureView<Z> toClosure();
}
