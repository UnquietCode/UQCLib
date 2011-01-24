package unquietcode.stately.closure;

import unquietcode.stately.closure.view.ClosureView;

/**
 * @author Benjamin Fagin
 * @version 01-23-2011
 */
public interface Closure<Z> extends ClosureInterfaceBase<Z> {
	Z run(Object...args);
	int getExpectedArgs();
	void setExpectedArgs(int args);
	ClosureView<Z> getView();
}
