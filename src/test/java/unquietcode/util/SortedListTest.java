package unquietcode.util;

import org.junit.Test;
import unquietcode.util.SortedList;


/**
 * @author Ben
 * @version 1.0
 *          Date: Nov 28, 2010
 */
public class SortedListTest {

	@Test
	public void standardTest() {
		SortedList<String> list = new SortedList<String>(SortedList.DefaultComparator);

		list.addAll("one", "two", "three", "four", "five");

		for (String x : list) {
			System.out.println(x);
		}
	}
}
