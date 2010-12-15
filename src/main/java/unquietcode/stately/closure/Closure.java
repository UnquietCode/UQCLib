package unquietcode.stately.closure;

/**
 * @author Ben
 * Date: 12/12/10
 */
public abstract class Closure<Z> extends ClosureBase<Z> {
	private int expectedArgs = -1;

	public void setExpectedArgs(Integer args) {
		if (args >= 0)
			expectedArgs = args;
		else
			expectedArgs = -1;
	}

	public int getExpectedArgs() {
		return expectedArgs;
	}

	public abstract Z run(Object...args);

	@Override
	public Closure<Z> toClosure() {
		return this;
	}

	public Closure(Object... args) {
		super(args);
	}
}
