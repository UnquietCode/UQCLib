package unquietcode.stately.closure;


/**
 * @author  Benjamin Fagin
 * @version 12/12/10
 */
public abstract class Closure<Z> extends ClosureBase<Z> {
	private int expectedArgs = -1;
	private Class[] argumentTypes = null;

	public final void setExpectedArgs(Integer args) {
		if (args >= 0)
			expectedArgs = args;
		else
			expectedArgs = -1;
	}

	public final int getExpectedArgs() {
		return expectedArgs;
	}

	public final Class[] getArgumentTypes() {
		return this.getArgumentTypes(Closure.class);
	}

	public Closure(Object...args) {
		super(args);
	}

	public final Closure<Z> toClosure() {
		return this;
	}

	public abstract Z run(Object...args);
}
