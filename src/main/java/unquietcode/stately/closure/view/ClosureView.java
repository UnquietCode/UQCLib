package unquietcode.stately.closure.view;

/**
 * @author  Benjamin Fagin
 * @version 01-23-2011
 */
public interface ClosureView<Z> extends ClosureViewBase<Z> {
	Z run(Object...args);
	int getExpectedArgs();
}
