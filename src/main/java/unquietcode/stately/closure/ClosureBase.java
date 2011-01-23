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

import com.googlecode.gentyref.GenericTypeReflector;
import unquietcode.stately.closure.view.ClosureView;
import unquietcode.stately.closure.view.ClosureViewBase;

import java.lang.reflect.*;
import java.util.Arrays;

/**
 * @author  Benjamin Fagin
 * @version Dec 7, 2010
 */
abstract class ClosureBase<Z> {
	private Object arguments[];
	boolean wrapped = false;

	protected ClosureBase(Object...args) {
		if (args == null)
			arguments = new Object[] {null};
		else
			arguments = args;
	}

	// to get passed in variables
	@SuppressWarnings("unchecked")
	protected final <A> A a1() {
		return (A) arg(1);
	}

	@SuppressWarnings("unchecked")
	protected final <A> A a2() {
		return (A) arg(2);
	}

	@SuppressWarnings("unchecked")
	protected final <A> A a3() {
		return (A) arg(3);
	}

	@SuppressWarnings("unchecked")
	protected final <A> A a4() {
		return (A) arg(4);
	}

	@SuppressWarnings("unchecked")
	protected final <A> A arg(int var) {
		try {
			return (A) arguments[--var];
		} catch (ArrayIndexOutOfBoundsException ex) {
			throw new ClosureException("invalid argument index: " + var);
		}
	}

	protected final Object[] getArguments() {
		return Arrays.copyOf(arguments, arguments.length);
	}


	public final boolean isWrapped() {
		return wrapped;
	}

	@SuppressWarnings("unchecked")
	public final void curry(int var, Object replacement) {
		var -= 1;
		ClosureBase closure = wrapped ? (ClosureBase) this.arg(1) : this;
		Field f[] = closure.getClass().getDeclaredFields();

		if (var >= f.length) {
			throw new ClosureException("invalid field ("+ (var+1) +")");
		}

		Field field = f[var];
		String name = field.getName();

		//TODO this might be compiler dependant, so need a better way!
		if (name.replaceAll("^this\\$[0-9]+", "").equals("")) {
			throw new ClosureException("invalid field ("+ (var+1) +")");
		}

		//make accessible
		if (!field.isAccessible())
			field.setAccessible(true);

		try {
			field.set(closure, replacement);
		} catch (IllegalAccessException ex) {
			throw new ClosureException("Could not access field to change.", ex);
		} catch (IllegalArgumentException ex) {
			throw new ClosureException("Invalid object type for replacement.", ex);
		}
	}

	protected final <T extends ClosureBase> Class[] getArgumentTypes(Class<T> clazz) {
		Type baseType = GenericTypeReflector.getExactSuperType((Type) this.getClass(), clazz);

		if (baseType instanceof Class<?>) {
			// raw class, type parameters not known
			return new Class[]{};
		}

		ParameterizedType pBaseType = (ParameterizedType) baseType;
		Type types[] = pBaseType.getActualTypeArguments();

		if (types.length <= 1) {
			return new Class[]{};
		}

		Class classes[] = new Class[types.length-1];
		for (int i=1; i < types.length; ++i) {
			classes[i-1] = (Class) types[i];
		}

		return classes;
	}

	public abstract ClosureView<Z> toClosure();
	public abstract Class[] getArgumentTypes();
//	public abstract <T extends ClosureViewBase<Z>> T getView();

	//TODO generate a "signature" version of the argument types array
}
