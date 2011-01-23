package unquietcode.stately.closure;

/**
 * @author  Benjamin Fagin
 * @version 01-23-2011
 */
public interface MultiClosure<Z> extends ClosureInterfaceBase<Z> {
	public Z run();
	public Z run(Object p1);
	public Z run(Object p1, Object p2);
	public Z run(Object p1, Object p2, Object p3);
	public Z run(Object p1, Object p2, Object p3, Object p4);
	public Z run(Object p1, Object p2, Object p3, Object p4, Object p5);
	public Z run(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6);
	public Z run(Object...args);

	Closure0 toClosure0();
	Closure1 toClosure1();
	Closure2 toClosure2();
	Closure3 toClosure3();
	Closure4 toClosure4();
	Closure5 toClosure5();
	Closure6 toClosure6();
	Closure<Z> toClosure();

	void curry(int var, Object replacement);
}

