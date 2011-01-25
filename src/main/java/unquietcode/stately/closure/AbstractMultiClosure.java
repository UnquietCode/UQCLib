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

import com.sun.corba.se.spi.ior.ObjectKey;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import unquietcode.stately.closure.view.*;

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

	public final MultiClosureView<Z> getView() {
		final MultiClosure<Z> base = this;

		return new MultiClosureView<Z>() {
			public Z run() {
				return base.run();
			}

			public Z run(Object p1) {
				return base.run(p1);
			}

			public Z run(Object p1, Object p2) {
				return base.run(p1,p2);
			}

			public Z run(Object p1, Object p2, Object p3) {
				return base.run(p1,p2,p3);
			}

			public Z run(Object p1, Object p2, Object p3, Object p4) {
				return base.run(p1,p2,p3,p4);
			}

			public Z run(Object p1, Object p2, Object p3, Object p4, Object p5) {
				return base.run(p1,p2,p3,p4,p5);
			}

			public Z run(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
				return base.run(p1,p2,p3,p4,p5,p6);
			}

			public Z run(Object...args) {
				return base.run(args);
			}

			public Closure0View<Z> toClosure0() {
				return base.toClosure0();
			}

			public Closure1View toClosure1() {
				return base.toClosure1();
			}

			public Closure2View toClosure2() {
				return base.toClosure2();
			}

			public Closure3View toClosure3() {
				return base.toClosure3();
			}

			public Closure4View toClosure4() {
				return base.toClosure4();
			}

			public Closure5View toClosure5() {
				return base.toClosure5();
			}

			public Closure6View toClosure6() {
				return base.toClosure6();
			}

			public ClosureView<Z> toClosure() {
				return base.toClosure();
			}

			public boolean isImplemented(int x) {
				return base.isImplemented(x);
			}
		};
	}

	public final Closure0View<Z> toClosure0() {
		if (!isImplemented(0))
			return null;

		final MultiClosure<Z> base = this;

		return new Closure0View<Z>() {
			public Z run() {
				return base.run();
			}
		};
	}

	public final Closure1View toClosure1() {
		if (!isImplemented(1))
			return null;

		final MultiClosure base = this;

		return new Closure1View() {
			public Object run(Object p1) {
				return base.run(p1);
			}

			public Class[] getArgumentTypes() {
				return new Class[]{Object.class};
			}
		};
	}

	public final Closure2View toClosure2() {
		if (!isImplemented(2))
			return null;

		final MultiClosure base = this;

		return new Closure2View() {
			public Object run(Object p1, Object p2) {
				return base.run(p1, p2);
			}

			public Class[] getArgumentTypes() {
				return new Class[]{Object.class, Object.class};
			}
		};
	}

	public final Closure3View toClosure3() {
		if (!isImplemented(3))
			return null;

		final MultiClosure base = this;

		return new Closure3View() {
			public Object run(Object p1, Object p2, Object p3) {
				return base.run(p1, p2, p3);
			}

			public Class[] getArgumentTypes() {
				return new Class[]{Object.class, Object.class, Object.class};
			}
		};
	}

	public final Closure4View toClosure4() {
		if (!isImplemented(4))
			return null;

		final MultiClosure base = this;

		return new Closure4View() {
			public Object run(Object p1, Object p2, Object p3, Object p4) {
				return base.run(p1, p2, p3, p4);
			}

			public Class[] getArgumentTypes() {
				return new Class[]{Object.class, Object.class, Object.class, Object.class};
			}
		};
	}

	public final Closure5View toClosure5() {
		if (!isImplemented(5))
			return null;

		final MultiClosure base = this;

		return new Closure5View() {
			public Object run(Object p1, Object p2, Object p3, Object p4, Object p5) {
				return base.run(p1, p2, p3, p4, p5);
			}

			public Class[] getArgumentTypes() {
				return new Class[]{Object.class, Object.class, Object.class, Object.class, Object.class};
			}
		};
	}

	public final Closure6View toClosure6() {
		if (!isImplemented(6))
			return null;

		final MultiClosure base = this;

		return new Closure6View() {
			public Object run(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
				return base.run(p1, p2, p3, p4, p5, p6);
			}

			public Class[] getArgumentTypes() {
				return new Class[]{Object.class, Object.class, Object.class, Object.class, Object.class, Object.class};
			}
		};
	}

	public final ClosureView<Z> toClosure() {
		if (!isImplemented(MAX_PARAMS + 1))
			return null;

		final MultiClosure<Z> base = this;

		return new ClosureView<Z>() {
			public Z run(Object...args) {
				return base.run(args);
			}

			public int getExpectedArgs() {
				return -1;
			}
		};
	}

	public static MultiClosure makeMultiClosure(final Closure0 c0, final Closure1 c1, final Closure2 c2,
	                                            final Closure3 c3, final Closure4 c4, final Closure5 c5,
	                                            final Closure6 c6, final Closure cX) {
		return new AbstractMultiClosure() {

			public final boolean isImplemented(int x) {
				if (cX != null)
					return true;

				switch (x) {
					case 0: return c0 != null;
					case 1: return c1 != null;
					case 2: return c2 != null;
					case 3: return c3 != null;
					case 4: return c4 != null;
					case 5: return c5 != null;
					case 6: return c6 != null;
					default: return false;
				}
			}

			public Object run() {
				if (c0 == null)
					throw new NotImplementedException();
				else
					return c0.run();
			}

			public Object run(Object p1) {
				if (c1 == null)
					throw new NotImplementedException();
				else
					return c1.run(p1);
			}

			public Object run(Object p1, Object p2) {
				if (c2 == null)
					throw new NotImplementedException();
				else
					return c2.run(p1, p2);
			}

			public Object run(Object p1, Object p2, Object p3) {
				if (c3 == null)
					throw new NotImplementedException();
				else
					return c3.run(p1, p2, p3);
			}

			public Object run(Object p1, Object p2, Object p3, Object p4) {
				if (c4 == null)
					throw new NotImplementedException();
				else
					return c4.run(p1, p2, p3, p4);
			}

			public Object run(Object p1, Object p2, Object p3, Object p4, Object p5) {
				if (c5 == null)
					throw new NotImplementedException();
				else
					return c5.run(p1, p2, p3, p4, p5);
			}

			public Object run(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
				if (c6 == null)
					throw new NotImplementedException();
				else
					return c6.run(p1, p2, p3, p4, p5, p6);
			}

			public Object run(Object...args) {
				if (cX == null)
					throw new NotImplementedException();
				else
					return cX.run(args);
			}
		};
	}
}
