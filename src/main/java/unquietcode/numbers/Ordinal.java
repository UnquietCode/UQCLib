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

package unquietcode.numbers;


/**
 * The Ordinal class allows for simple representation of numbers as ordinals.
 * The default display is short (1st, 2nd, 3rd, 4th, etc).
 * The long display can be enabled in the constructor (first, second, third, etc).
 *
 * @author Ben Fagin
 */
public class Ordinal {
	//constants
	private static final String suffix[] = {"th", "st", "nd", "rd", "th"};
	private static final String longSuffix[] = {"th", "first", "second", "third", "fourth",
												"fifth", "sixth", "seventh", "eighth",
												"ninth", "tenth", "eleventh", "twelfth"};
	private static final String tens[] = {"th", "tenth", "twentieth", "thirtieth", "fortieth",
										  "fiftieth", "sixtieth", "seventieth", "eightieth",
										  "ninetieth"};

	//object data
	private final boolean displayLong;
	private final int value;
	private final String shortString;
	private final String longString;

	
	//constructors
	public Ordinal(int number) {
		this(number, false);
	}

	public Ordinal(int number, boolean displayLong) {
		if (number < 0)
			throw new IllegalArgumentException("number must be positive");

		value = number;
		this.displayLong = displayLong;
		shortString = setShort(value);
		longString = setLong(value);
	}

	//string builders
	private static String setLong(int x) {
		String ret = Cardinal.toCardinal(x);
		x %= 100;
		
		int p = ret.lastIndexOf('-');
		if (p == -1)
			p = ret.lastIndexOf(' ');

		if (x >= 1 && x <= 12)
			ret = ret.substring(0, p+1) + longSuffix[x];
		else if (x <= 19)
			ret += suffix[4];
		else {
			int Z = x % 10;
			
			if (Z == 0)
				ret = ret.substring(0, p+1) + tens[x / 10];
			else
				ret = ret.substring(0, p+1) + longSuffix[Z];
		}
		
		return ret;
	}

	private static String setShort(int x) {
		String ret = "";
		ret += x;
		x %= 100;
		
		if (x >= 4  &&  x <= 20) {
			ret += suffix[4];
		} else {
			x %= 10;
			if (x > 4)
				x = 4;
			
			ret += suffix[x];
		}
		
		return ret;
	}

	//getters
	public String getShort() { return shortString; }
	public String getLong() { return longString; }
	public int getValue() { return value; }
	
	public @Override String toString() {
		if (displayLong)
			return longString;
		else
			return shortString;
	}
	
	//static methods
	public static String toOrdinal(int number) {
		return setShort(number);
	}
	
	public static String toOrdinal(int number, boolean displayLong) {
		if (number < 0)
			throw new IllegalArgumentException("number must be positive");
		
		if (displayLong)
			return setLong(number);
		else
			return setShort(number);
	}
}