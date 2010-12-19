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

import java.lang.reflect.Field;

/**
 * @author  Benjamin Fagin
 * @version Dec 7, 2010
 */
abstract class ClosureBase<Z> {
	Object arguments[];
	protected boolean wrapped = false;

	protected ClosureBase(Object...args) {
		if (args == null)
			arguments = new Object[] {null};
		else
			arguments = args;
	}

	// to get passed in variables
	@SuppressWarnings("unchecked")
	protected <A> A a1() {
		return (A) arg(1);
	}

	@SuppressWarnings("unchecked")
	public <A> A a2() {
		return (A) arg(2);
	}

	@SuppressWarnings("unchecked")
	public <A> A a3() {
		return (A) arg(3);
	}

	@SuppressWarnings("unchecked")
	public <A> A a4() {
		return (A) arg(4);
	}

	@SuppressWarnings("unchecked")
	public <A> A arg(int var) {
		try {
			return (A) arguments[--var];
		} catch (ArrayIndexOutOfBoundsException ex) {
			throw new ClosureException("invalid argument index: " + var);
		}
	}


	public boolean isWrapped() {
		return wrapped;
	}


	public Class[] getArgumentTypes() {
		// get the array of passed in arguments
		Class retval[] = new Class[arguments.length];

		for (int i=0; i < arguments.length; ++i)
			retval[i] = arguments[i].getClass();

		return retval;
	}

	@SuppressWarnings("unchecked")
	public void curry(int var, Object replacement) {
		ClosureBase closure = wrapped ? (ClosureBase) this.arg(1) : this;
		var -= 1;
		Field f[] = closure.getClass().getDeclaredFields();

		//proper number of fields
		if (var >= f.length) {
			throw new ClosureException("invalid field ("+ (var+1) +")");
		}

		Field field = f[var];
		String name = field.getName();

		//TODO this might be compiler independant, so need a better way!
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

	public static class ClosureException extends RuntimeException {
		public ClosureException(String message) {
			super(message);
		}

		public ClosureException(String message, Throwable ex) {
			super(message, ex);
		}
	}

	public abstract Closure<Z> toClosure();
	//public abstract MultiClosure<Z> toMultiClosure(); //todo how to do type params
}
