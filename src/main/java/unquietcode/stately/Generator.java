/*******************************************************************************
 Copyright 2009-2011 Benjamin Fagin

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.


 Read the included LICENSE.TXT for more information.
 ******************************************************************************/

package unquietcode.stately;

import unquietcode.stately.closure.AbstractClosure0;
import unquietcode.stately.closure.view.Closure0View;
import unquietcode.stately.closure.view.ClosureView;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;



// goal: a wrapper st any closure can be put in



public class Generator<Z> implements Iterator<Z> {
	private final Closure0View<Z> t0;
	private final ClosureView<Z> tN;
	private final boolean zero;

	public Generator(Closure0View<Z> closure) {
		t0 = closure;
		tN = null;
		zero = true;
	}

	public Generator(ClosureView<Z> closure) {
		tN = closure;
		t0 = null;
		zero = false;
	}

	public Object custom(Object...args) {
		return null;
	}


	public boolean hasNext() {
		return true;
	}

	public final Z next() {
		if (zero)
			return t0.run();
		else
			return tN.run();
	}

	// sorry, can't touch
	public final void remove() {
		throw new UnsupportedOperationException();
	}
}












/*
*//**
 * @author Ben
 * @version 0.1
 * Date: Dec 7, 2010
 *//*
@SuppressWarnings("unchecked")
public abstract class Generator<T> {

	abstract T continuation(Object...args);
	abstract Object[] initializeParams();

	private Object methodParams[];
	private T nextValue;
	private boolean isGood = false;


	public Generator() {
		methodParams = initializeParams();
		//generate first
	}



	protected T generate(Object...args) {
		T retval = (T) args[0];

		if (args.length == 1)
			methodParams = new Object[0];
		else {
			methodParams = new Object[args.length-1];
			System.arraycopy(args, 1, methodParams, 1, args.length-1);
		}

		nextValue = (T) args[0];
		return null;
	}


	public boolean hasNext() {
		return isGood;
	}

	public T next() {
		if (!isGood)
			throw new NoSuchElementException();

		T retval = nextValue;
		nextValue = generate(methodParams);
		return retval;
	}*/
