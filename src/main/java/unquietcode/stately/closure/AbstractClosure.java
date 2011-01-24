package unquietcode.stately.closure;


import unquietcode.stately.closure.view.ClosureView;

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

	public AbstractClosure(Object...args) {
		super(args);
	}

	public final ClosureView<Z> getView() {
		return this.toClosure();
	}

	@SuppressWarnings("unchecked")
	public final ClosureView<Z> toClosure() {
		final Closure base = this;

		return new ClosureView<Z>() {
			public Z run(Object...args) {
				return (Z) base.run(args);
			}

			public int getExpectedArgs() {
				return base.getExpectedArgs();
			}
		};
	}

	public abstract Z run(Object...args);
}
