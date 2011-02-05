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

package unquietcode.stately.closure;

import unquietcode.stately.closure.view.ClosureView;

import java.util.*;

/**
 * @author  Benjamin Fagin
 * @version 12-31-2010
 *
 *
 * Chains are a series of linked ClosureView objects. Execution starts at the first and proceeds to the end.
 * Each closure is left in charge of how to handle the inputs and outputs. Because each closure outputs a single
 * object, arrays of objects can be returned, which will be fed to the next closure as varargs. Only the last closure
 * need return the 'correct' return type, as specified in the type parameter (optional, of course).
 *
 * All of the normal concerns apply: No concurrency out of the box, mutable objects passed as arguments could produce
 * unexpected results, etc.
 *
 * It should be pointed out that by default no validation occurs here whatsoever. Null closures will throw a Java NPE
 * when they are executed. The "expectedArguments" value is ignored.
 *
 * However, enabling validation will wrap these errors in an unchecked @see{ClosureException}.
 *
 */
public class Chain<Z> implements Iterable<ClosureView<Z>> {
	private	LinkedList<ClosureView<Z>> chain = new LinkedList<ClosureView<Z>>();
	private boolean validate = false;

	/**
	 * Takes a series of ClosureView elements and combines them into a "chain".
	 *
	 * @param closures
	 */
	public Chain(ClosureView<Z>...closures) {
		for (ClosureView<Z> c : closures) {
			chain.add(c);
		}
	}

	/**
	 * Takes a series of Chain elements and combines them into a new Chain.
	 *
	 * @param chains
	 */
	public Chain(Chain<Z>...chains) {
		for (Chain<Z> c : chains)
			for (ClosureView<Z> cv : c.chain)
				chain.addLast(cv);
	}

	public void setValidation(boolean validate) {
		this.validate = validate;
	}

	public boolean isValidated() {
		return validate;
	}

	/**
	 * Prepends a series of closures to the chain.
	 *
	 * @param   closures    ClosureView objects to prepend.
	 * @return  this Chain, with the newly added closures
	 */
	public Chain<Z> prepend(ClosureView<Z>...closures) {
		for (int i=closures.length-1; i >= 0; --i) {
			chain.addFirst(closures[i]);
		}

		return this;
	}

	/**
	 * Appends a series of closures to the chain.
	 *
	 * @param   closures    ClosureView objects to append.
	 * @return  this Chain, with the newly added closures
	 */
	public Chain<Z> append(ClosureView<Z>...closures) {
		for (ClosureView<Z> closure : closures) {
			chain.addLast(closure);
		}

		return this;
	}

	/*
	 * Inserts a ClosureView into the desired location.
	 *
	 */
	public Chain<Z> insert(int i, ClosureView<Z> closure) {
		if (i < 0 || i > chain.size())
			throw new IndexOutOfBoundsException("Invalid index position.");

		chain.add(i, closure);
		return this;
	}

	/*
	 * Inserts a ClosureView into the desired location.
	 *
	 */
	public Chain<Z> insert(int i, Chain<Z> chain) {
		if (i < 0 || i > chain.size())
			throw new IndexOutOfBoundsException("Invalid index position.");

		for (int j=chain.chain.size()-1; j >= 0; --j) {
			this.chain.add(i, chain.chain.get(j));
		}

		return this;
	}

	/*
	 * Inserts a ClosureView into the desired location.
	 *
	 */
	public Chain<Z> remove(int i) {
		if (i < 0 || i > chain.size())
			throw new IndexOutOfBoundsException("Invalid index position.");

		chain.remove(i);
		return this;
	}

	/*
	 * Inserts a ClosureView into the desired location.
	 *
	 */
	public ClosureView<Z> get(int i) {
		if (i < 0 || i > chain.size())
			throw new IndexOutOfBoundsException("Invalid index position.");

		return chain.get(i);
	}

	/**
	 * Prepends a series of closures to the chain.
	 *
	 * @param   chains    ClosureView objects to prepend.
	 * @return  this Chain, with the newly added closures
	 */
	public Chain<Z> prepend(Chain<Z>...chains) {
		for (int i = chains.length-1; i >= 0; --i) {
			Chain<Z> c = chains[i];

			for (int j = c.chain.size()-1; j >= 0; --j) {
				chain.addFirst(c.chain.get(j));
			}
		}

		return this;
	}

	/**
	 * Appends a series of closures to the chain.
	 *
	 * @param   chains    ClosureView objects to append.
	 * @return  this Chain, with the newly added closures
	 */
	public Chain<Z> append(Chain<Z>...chains) {
		for (Chain<Z> c : chains)
			for (ClosureView<Z> cv : c.chain)
				chain.addLast(cv);

		return this;
	}

	/**
	 * Execute the chain, starting from the first to the last.
	 *
	 * @param args
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Z run(Object...args) {
		Object result = null;

		if (validate) {
			result = args;
			for (ClosureView<Z> closure : chain) {
				if (closure == null)
					throw new ClosureException("Closure is null and will not execute!");

				try {
					result = closure.run(args);
	
					if (result.getClass().isArray()) {
						args = (Object[]) result;
					} else {
						args = new Object[]{result};
					}
				} catch (Exception ex) {
					throw new ClosureException("Error while executing closure.", ex);
				}
			}
		} else {
			for (ClosureView<Z> closure : chain) {
				result = closure.run(args);

				if (result.getClass().isArray()) {
					args = (Object[]) result;
				} else {
					args = new Object[]{result};
				}
			}
		}

		if (validate) {
			try {
				return (Z) result;
			} catch (Exception ex) {
				throw new ClosureException("Could not cast result.", ex);
			}
		} else {
			return (Z) result;
		}
	}

	public int size() {
		return chain.size();
	}

	public Iterator<ClosureView<Z>> iterator() {
		return chain.iterator();
	}
}
