package unquietcode.annotations;

import org.junit.Test;
import unquietcode.annotations.Warning;

/**
 * @author Ben
 * @version 0.1
 *          Date: 12/11/10
 */
public class WarningTest {

	@Test
	public void tryIt() {
		dontUse(5);
		System.err.println("told you!");
	}

	@Warning("Don't use this!")
	public void dontUse(int val) {
		System.err.println("value is: " + val);
	}
}
