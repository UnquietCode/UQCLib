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
public abstract class AbstractMultiClosure<Z> extends ClosureBase<Z> implements MultiClosure<Z> {
	private static final int MAX_PARAMS = 6;                // for now, it's set at 6
	boolean isImplemented[] = new boolean[MAX_PARAMS+2];    // 0-6, and then 7 is vararg

	@Retention(RetentionPolicy.RUNTIME)
	private @interface Original { }

	public AbstractMultiClosure(Object... args) {
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

		//method order isn't guaranteed, so afterwards make sure we know what's actually possible
		//also note though that this is just what's possible, so not what are actually overridded. Maybe better to
		//preserve that elsewhere
		/*for (int i=0; i <= MAX_PARAMS; ++i)
			isImplemented[i] |= isImplemented[MAX_PARAMS];*/
		//TODO ...what?

	}

	@Original
	public Z run() {
		return run(new Object[]{});
	}
	@Original
	public Z run(Object p1) {
		return run(new Object[]{p1});
	}
	@Original
	public Z run(Object p1, Object p2) {
		return run(new Object[]{p1, p2});
	}
	@Original
	public Z run(Object p1, Object p2, Object p3) {
		return run(new Object[]{p1, p2, p3});
	}
	@Original
	public Z run(Object p1, Object p2, Object p3, Object p4) {
		return run(new Object[]{p1, p2, p3 ,p4});
	}
	@Original
	public Z run(Object p1, Object p2, Object p3, Object p4, Object p5) {
		return run(new Object[]{p1, p2, p3 ,p4, p5});
	}
	@Original
	public Z run(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
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

	public final Class[] getArgumentTypes() {
		return super.getArgumentTypes(AbstractMultiClosure.class);
	}

	@SuppressWarnings("unchecked")
	public Closure0 toClosure0() {
		Closure0 closure = new AbstractClosure0(this) {
			AbstractMultiClosure mc = (AbstractMultiClosure) a1();

			public Object run() {
				return mc.run();
			}
		};

		return closure;
	}

	//wrapper methods
	@SuppressWarnings("unchecked")
	public Closure1 toClosure1() {
		Closure1 closure = new AbstractClosure1(this) {
			AbstractMultiClosure mc = (AbstractMultiClosure) a1();

			public Object run(Object p1) {
				return mc.run(p1);
			}
		};

		return closure;
	}

	@SuppressWarnings("unchecked")
	public Closure2 toClosure2() {
		Closure2 closure = new AbstractClosure2(this) {
			AbstractMultiClosure mc = (AbstractMultiClosure) a1();

			public Object run(Object p1, Object p2) {
				return mc.run(p1, p2);
			}
		};

		return closure;
	}
	@SuppressWarnings("unchecked")
	public Closure3 toClosure3() {
		Closure3 closure = new AbstractClosure3(this) {
			AbstractMultiClosure mc = (AbstractMultiClosure) a1();

			public Object run(Object p1, Object p2, Object p3) {
				return mc.run(p1, p2, p3);
			}
		};

		return closure;
	}
	@SuppressWarnings("unchecked")
	public Closure4 toClosure4() {
		Closure4 closure = new AbstractClosure4(this) {
			AbstractMultiClosure mc = (AbstractMultiClosure) a1();

			public Object run(Object p1, Object p2, Object p3, Object p4) {
				return mc.run(p1, p2, p3, p4);
			}
		};

		return closure;
	}

	@SuppressWarnings("unchecked")
	public Closure5 toClosure5() {
		Closure5 closure = new AbstractClosure5(this) {
			AbstractMultiClosure mc = (AbstractMultiClosure) a1();

			public Object run(Object p1, Object p2, Object p3, Object p4, Object p5) {
				return mc.run(p1, p2, p3, p4, p5);
			}
		};

		return closure;
	}

	@SuppressWarnings("unchecked")
	public Closure6 toClosure6() {
		Closure6 closure = new AbstractClosure6(this) {
			AbstractMultiClosure mc = (AbstractMultiClosure) a1();

			public Object run(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
				return mc.run(p1, p2, p3, p4, p5, p6);
			}
		};

		return closure;
	}

	@SuppressWarnings("unchecked")
	public Closure<Z> toClosure() {
		Closure closure = new AbstractClosure(this) {
			AbstractMultiClosure mc = (AbstractMultiClosure) a1();

			public Object run(Object...params) {
				return mc.run(params);
			}
		};

		return closure;
	}

/*	//TODO maybe a future idea, not feeling it now
	public static <z, a extends ,b,c,d,e,f> MultiClosure<z, a,b,c,d,e,f> makeMultiClosure(U...closures) {
		// need to find out if more than one type of closure is present
		Map<Integer, AbstractClosure> map = new HashMap<Integer, AbstractClosure>();

		for (U c : closures) {
			//if c.
		}

		return null;
	}*/
}
