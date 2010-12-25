/*
 * Copyright $YEAR$ Benjamin Fagin
 *
 * This work is licensed under the Common Development and Distribution License (CDDL), Version 1.0
 *
 * Read the included LICENSE.TXT for more information.
 *
 * --------------------------------------------------------------------
 */

package unquietcode.enumb;

import org.junit.Test;

import static unquietcode.util.Shortcuts.out;

/**
 * @author Ben
 * @version 1.0
 *          Date: Dec 2, 2010
 */
public class EnumAttributeSet_TX {

	private static enum StringAttribute implements EnumAttributeSet.Castable<String> {
		ONE("one"),
		TWO("two"),
		THREE("three");

		final String Data;
		StringAttribute(String data) {
			Data = data;
		}

		public String cast(Object o) {
			return (String) o;
		}
	}

	private enum PriorityNames {
		High("URGENT"),
		Medium("Important"),
		Low("pass");

		final String mData;
		PriorityNames(String data) {
			mData = data;
		}

		public @Override String toString() {
			return mData;
		}
	}

	private enum Dimensions implements EnumAttributeSet.Castable<Integer> {
		length, width, height, depth;

		public Integer cast(Object o) {
			return (Integer) o;
		}
	}


	private static enum State {
		on, off;
	}

	private static enum Colors {
		Red, Blue, Green;
	}

	@Test
	public void mainTest() {
		EnumAttributeSet eat = new EnumAttributeSet();

		eat.addAttribute(Colors.Blue);
		eat.addAttribute(State.on);
		eat.addData(Dimensions.length, 4);

		System.out.println("Am I red? " + eat.is(Colors.Red));
		System.out.println("Am I blue? " + eat.is(Colors.Blue));
//		System.out.println("My length is " + Dimensions.length.cast(eat.getData(Dimensions.length)));
		System.out.println("My length is " + eat.getData(Dimensions.length));
	}


	@Test
	public void ComponentsTest() {
		EnumAttributeSet eat = new EnumAttributeSet();

		eat.setProperty(PriorityNames.High);
		eat.addAttribute(State.on);

		out("Am I on? " + eat.is(State.on));
		out("What is my priority? " + eat.getProperty(PriorityNames.class));
		out("What is the name of my priority? " + eat.getProperty(PriorityNames.class).name());
		out("Do I have a priority? " + eat.hasProperty(PriorityNames.class));
		out("Is my priority low? " + eat.is(PriorityNames.Low));
		out("Is my priority high? " + eat.is(PriorityNames.High));
		out("");
		
		eat.setProperty(PriorityNames.Low);
		out("Is my priority high? " + eat.is(PriorityNames.High));
		out("Is my priority low? " + eat.is(PriorityNames.Low));
		out("What is the name of my priority? " + eat.getProperty(PriorityNames.class).name());
	}


	@Test
	public void enumConstants() {
		EnumAttributeSet eat = new EnumAttributeSet();

		Object list[] = eat.getClass().getEnumConstants();
		out(list);
		out(eat.getClass().isEnum());
		out(eat.getClass().isEnum());

	}

	//to use Colors.Blue is to ask "Are you blue?". To use Appearence.Color(blue) is to ask "What
	//color are you?"
}

/*
	So a few different patterns.


	In one, you add enum instances as single attributes. (boolean)

	Int another, you map enums to values, and the user is responsible for casting them.
	The interface is a helpful tool, but nonbinding.

	In the third, enums are collections, and a single instance is recorded. Again, the user is
	responsible for knowing what to do with it.


	Put another way:
		1. Attributes, boolean attributes
		2. Data, enum labeled data (like width, height)
		3. Properties, single enums of an enum class.

	Normally
	+ attributes will overwrite properties if its another member of the class
		+ eg, State is ON, but then try adding attribute State.OFF. Now it has both as attributes but neither as property
	+ data will function similarly.
	+ adding a property will remove all attributes of the class
		+ eg, has attributes Color.RED and Color.GREEN. Set property Color to GREEN and now it only has attribute Color.GREEN
	+ 
*/