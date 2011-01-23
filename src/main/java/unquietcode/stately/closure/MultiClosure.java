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

	Closure<Z> toClosure();
	Closure0<Z> toClosure0();
	Closure1<Z, A> toClosure1();
	Closure2<Z, A,B> toClosure2();
	Closure3<Z, A,B,C> toClosure3();
	Closure4<Z, A,B,C,D> toClosure4();
	Closure5<Z, A,B,C,D,E> toClosure5();
	Closure6<Z, A,B,C,D,E,F> toClosure6();

	void curry(int var, Object replacement);
}

