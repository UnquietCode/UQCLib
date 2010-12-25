package unquietcode.enumb;

import org.junit.Assert;
import org.junit.Test;

import static unquietcode.util.Shortcuts.die;
import static unquietcode.util.Shortcuts.out;

/**
 * @author  Benjamin Fagin
 * @version 12-23-2010
 */
public class EnumStateMachine_T {
	EnumStateMachine esm;

	enum State {
		Ready, Running, Paused, Stopping, Stopped, Finished
	}

	@Test
	public void basic() {
		esm = new EnumStateMachine(State.Ready);
		esm.addTransitions(State.Ready, State.Running, State.Finished);
		esm.addTransitions(State.Running, State.Paused, State.Stopping);
		esm.addTransitions(State.Paused, State.Running, State.Stopping);
		esm.addTransitions(State.Stopping, State.Stopped);
		esm.addTransitions(State.Stopped, State.Finished);
		esm.addTransitions(State.Finished, State.Ready, null);

		String one = esm.toString();
		out(one + "\n");
		EnumStateMachine esm2 = null;

		try {
			esm2 = new EnumStateMachine(one);
		} catch (EnumStateMachine.ParseException ex) {
			System.err.println(ex);
			die(1);
		}

		String two = esm2.toString();
		out(two);

	   Assert.assertTrue(esm.equals(esm2));
	}

	@Test
	public void transitionTest() {
		esm = new EnumStateMachine(State.Ready);
		esm.addTransitions(State.Ready, State.Running, State.Finished);
		esm.addTransitions(State.Running, State.Paused, State.Stopping);
		esm.addTransitions(State.Paused, State.Running, State.Stopping);
		esm.addTransitions(State.Stopping, State.Stopped);
		esm.addTransitions(State.Stopped, State.Finished);
		esm.addTransitions(State.Finished, State.Ready, null);

		esm.transition(State.Running);
		esm.transition(State.Paused);
	//	esm.transition(State.Paused);   // exception!
		esm.transition(State.Running);
		esm.transition(State.Stopping);
	//	esm.transition(State.Finished); // exception!
		esm.transition(State.Stopped);
		esm.transition(State.Finished);
		esm.transition(null);
	//	esm.transition(State.Ready);    // exception!
		esm.reset();
		Assert.assertTrue(esm.state() == State.Ready);
	}
}
