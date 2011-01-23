package unquietcode.stately.closure;


/**
 * @author  Benjamin Fagin
 * @version 12/12/10
 */
public abstract class AbstractClosure<Z> extends ClosureBase<Z> implements Closure<Z> {
	private int expectedArgs = -1;

	public final void setExpectedArgs(int args) {
		if (args >= 0)
			expectedArgs = args;
		else
			expectedArgs = -1;
}

	public final int getExpectedArgs() {
		return expectedArgs;
	}

	public final Class[] getArgumentTypes() {
		return this.getArgumentTypes(AbstractClosure.class);
	}

	public AbstractClosure(Object...args) {
		super(args);
	}

	public final AbstractClosure<Z> toClosure() {
		return this;
	}

	public abstract Z run(Object...args);
}
