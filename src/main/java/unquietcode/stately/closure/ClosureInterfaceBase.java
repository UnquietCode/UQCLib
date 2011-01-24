package unquietcode.stately.closure;

import unquietcode.stately.closure.view.ClosureView;

/**
 * @author  Benjamin Fagin
 * @version 01-23-2011
 */
public interface ClosureInterfaceBase<Z> {
	void curry(int var, Object replacement);
	Class[] getArgumentTypes();
	ClosureView<Z> toClosure();
}

//Q? should all closures be forced to provide a toMultiClosure method?
//Q? What's the utility of having a multiclosure with only one working?
