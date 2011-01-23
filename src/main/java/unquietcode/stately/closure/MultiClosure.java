package unquietcode.stately.closure;

/**
 * @author  Benjamin Fagin
 * @version 01-23-2011
 */
public interface MultiClosure<Z, A,B,C,D,E,F> extends ClosureInterfaceBase<Z> {
	public Z run();
	public Z run(A p1);
	public Z run(A p1, B p2);
	public Z run(A p1, B p2, C p3);
	public Z run(A p1, B p2, C p3, D p4);
	public Z run(A p1, B p2, C p3, D p4, E p5);
	public Z run(A p1, B p2, C p3, D p4, E p5, F p6);
	public Z run(Object...args);

	void curry(int var, Object replacement);
	Closure<Z> toClosure();
}

