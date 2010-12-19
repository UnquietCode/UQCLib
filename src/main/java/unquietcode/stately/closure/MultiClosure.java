/*
 * Copyright $YEAR$ Benjamin Fagin
 *
 * This work is licensed under the Common Development and Distribution License (CDDL), Version 1.0
 *
 * Read the included LICENSE.TXT for more information.
 *
 * --------------------------------------------------------------------
 */

package unquietcode.stately.closure;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

/**
 * @author  Benjamin Fagin
 * @version Dec 10, 2010
 */
public abstract class MultiClosure<Z, A,B,C,D,E,F> extends ClosureBase<Z> {
	private static final int MAX_PARAMS = 6;                //for now, it's set at 6
	boolean isImplemented[] = new boolean[MAX_PARAMS+2];  //0-6, and then 7 is vararg


	public MultiClosure(Object...args) {
		super(args);

		//set up an implemented map
		Class c = this.getClass();
		Method methods[] = c.getMethods();

		for (Method method : methods) {
			if (method.getName().equals("run")) {
				if (method.isVarArgs()) {
					isImplemented[MAX_PARAMS+1] = !method.isAnnotationPresent(Original.class);
				} else {
					//find out how many param
					Class classes[] = method.getParameterTypes();
					int number = classes == null ? -1 : classes.length;
					
					if (number < 0 || number > MAX_PARAMS) {
						continue; //not one of our methods
					}

					isImplemented[number] = !method.isAnnotationPresent(Original.class);
				}
			}
		}

		//method order isn't guarenteed, so afterwards make sure we know what's actually possible
		//also note though that this is just what's possible, so not what are actually overridded. Maybe better to
		//preserve that elsewhere
		/*for (int i=0; i <= MAX_PARAMS; ++i)
			isImplemented[i] |= isImplemented[MAX_PARAMS];*/

	}
	
	@Retention(RetentionPolicy.RUNTIME)
	private @interface Original { }

	@Original
	public Z run() {
		return run(new Object[]{});
	}
	@Original
	public Z run(A p1) {
		return run(new Object[]{p1});
	}
	@Original
	public Z run(A p1, B p2) {
		return run(new Object[]{p1, p2});
	}
	@Original
	public Z run(A p1, B p2, C p3) {
		return run(new Object[]{p1, p2, p3});
	}
	@Original
	public Z run(A p1, B p2, C p3, D p4) {
		return run(new Object[]{p1, p2, p3 ,p4});
	}
	@Original
	public Z run(A p1, B p2, C p3, D p4, E p5) {
		return run(new Object[]{p1, p2, p3 ,p4, p5});
	}
	@Original
	public Z run(A p1, B p2, C p3, D p4, E p5, F p6) {
		return run(new Object[]{p1, p2, p3 ,p4, p5, p6});
	}
	@Original
	public Z run(Object...args) {
		throw new NotImplementedException();
	}

	public boolean isImplemented(int x) {
		if (x < 0)
			return false;

		if (x > MAX_PARAMS+1)
			x = MAX_PARAMS+1;

		return isImplemented[x] || isImplemented[MAX_PARAMS+1];
	}

	//TODO will this work better, easier?
	@SuppressWarnings("unchecked")
	public <T> Closure0<T> getClosure0() {
		Closure0 closure = new Closure0(this) {
			MultiClosure mc = (MultiClosure) a1();

			@Override
			public Object run() {
				return mc.run();
			}
		};

		return closure;
	}

	//wrapper methods
	@SuppressWarnings("unchecked")
	public <T extends Closure1> T getClosure1() {
		Closure1 closure = new Closure1(this) {
			MultiClosure mc = (MultiClosure) a1();

			@Override
			public Object run(Object p1) {
				return mc.run(p1);
			}
		};

		return (T) closure;
	}

	@SuppressWarnings("unchecked")
	public <T extends Closure2> T getClosure2() {
		Closure2 closure = new Closure2(this) {
			MultiClosure mc = (MultiClosure) a1();

			@Override
			public Object run(Object p1, Object p2) {
				return mc.run(p1, p2);
			}
		};

		return (T) closure;
	}
	@SuppressWarnings("unchecked")
	public <T extends Closure3> T getClosure3() {
		Closure3 closure = new Closure3(this) {
			MultiClosure mc = (MultiClosure) a1();

			@Override
			public Object run(Object p1, Object p2, Object p3) {
				return mc.run(p1, p2, p3);
			}
		};

		return (T) closure;
	}
	@SuppressWarnings("unchecked")
	public <T extends Closure4> T getClosure4() {
		Closure4 closure = new Closure4(this) {
			MultiClosure mc = (MultiClosure) a1();

			@Override
			public Object run(Object p1, Object p2, Object p3, Object p4) {
				return mc.run(p1, p2, p3, p4);
			}
		};

		return (T) closure;
	}

	@SuppressWarnings("unchecked")
	public <T extends Closure5> T getClosure5() {
		Closure5 closure = new Closure5(this) {
			MultiClosure mc = (MultiClosure) a1();

			@Override
			public Object run(Object p1, Object p2, Object p3, Object p4, Object p5) {
				return mc.run(p1, p2, p3, p4, p5);
			}
		};

		return (T) closure;
	}

	@SuppressWarnings("unchecked")
	public <T extends Closure6> T getClosure6() {
		Closure6 closure = new Closure6(this) {
			MultiClosure mc = (MultiClosure) a1();

			@Override
			public Object run(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
				return mc.run(p1, p2, p3, p4, p5, p6);
			}
		};

		return (T) closure;
	}

	@SuppressWarnings("unchecked")
	public <T extends Closure7> T getClosure7() {
		Closure7 closure = new Closure7(this) {
			MultiClosure mc = (MultiClosure) a1();

			@Override
			public Object run(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
				return mc.run(p1, p2, p3, p4, p5, p6, p7); // this is the varargs method
			}
		};

		return (T) closure;
	}

	@SuppressWarnings("unchecked")
	public <T extends Closure8> T getClosure8() {
		Closure8 closure = new Closure8(this) {
			MultiClosure mc = (MultiClosure) a1();

			@Override
			public Object run(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
				return mc.run(p1, p2, p3, p4, p5, p6, p7, p8);  //this is the varargs method
			}
		};

		return (T) closure;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Closure> T getClosure() {
		Closure closure = new Closure(this) {
			MultiClosure mc = (MultiClosure) a1();

			@Override
			public Object run(Object...params) {
				return mc.run(params);
			}
		};

		return (T) closure;
	}
}
