package unquietcode.stately.closure;

import unquietcode.stately.closure.view.Closure0View;
import unquietcode.stately.closure.view.ClosureView;

/**
 * @author  Benjamin Fagin
 * @version 01-23-2011
 */
public interface Closure0<Z> extends ClosureInterfaceBase<Z> {
	Z run();
	Closure0View<Z> getView();
}
