package unquietcode.stately.closure;

/**
 * @author Benjamin Fagin
 * @version 01-23-2011
 */
public interface Closure<Z> extends ClosureInterfaceBase<Z> {
	Z run(Object...args);
	int getExpectedArgs();
	void setExpectedArgs(int args);
}
