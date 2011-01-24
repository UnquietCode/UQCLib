package unquietcode.stately.closure;

import unquietcode.stately.closure.view.*;

/**
 * @author  Benjamin Fagin
 * @version 01-23-2011
 */
public interface MultiClosure<Z> extends ClosureInterfaceBase<Z> {
	Z run();
	Z run(Object p1);
	Z run(Object p1, Object p2);
	Z run(Object p1, Object p2, Object p3);
	Z run(Object p1, Object p2, Object p3, Object p4);
	Z run(Object p1, Object p2, Object p3, Object p4, Object p5);
	Z run(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6);
	Z run(Object...args);

	Closure0View<Z> toClosure0();
	Closure1View toClosure1();
	Closure2View toClosure2();
	Closure3View toClosure3();
	Closure4View toClosure4();
	Closure5View toClosure5();
	Closure6View toClosure6();

	MultiClosureView<Z> getView();
	boolean isImplemented(int x);
}

