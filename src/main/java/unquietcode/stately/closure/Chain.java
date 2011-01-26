package unquietcode.stately.closure;

import unquietcode.stately.closure.view.ClosureView;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
public class Chain<Z> {
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

	public void setValidation(boolean validate) {
		this.validate = validate;
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
		for (int i=0; i < closures.length; ++i) {
			chain.addLast(closures[i]);
		}

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
			for (ClosureView<Z> closure : chain) {
				if (closure == null)
					throw new ClosureException("Closure is null and will not execute!");

				try {
					result = closure.run(result); //TODO implement as below
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
}
