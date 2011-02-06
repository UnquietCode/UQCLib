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

import java.util.ArrayList;


public class Cardinal {
	private static final String firsts[] = {
		"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve",
		"thirteen","fourteen", "fifteen", "sixteen","seventeen", "eighteen", "nineteen"};

	private static final String tens[] = {
		null, null, "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
	
	private static final String large[] = {"", "thousand", "million", "billion", "quadrillion", "quintillion"};

	private final int theValue;
	private final String theString;

	public Cardinal(int number) {
		theValue = number;
		theString = setString(theValue);
	}

	private static String makeChunk(int num) {
		if (num == 0)
			return "zero";

		StringBuilder sb = new StringBuilder();

	    int high = num / 100;
	    int low = num % 100;

	    if (high > 0)
			sb.append(firsts[high]).append(" hundred ");

	    if (low > 0) {
			if (low < 20)
				sb.append(firsts[low]);
			else {
				int deca = low / 10;
				sb.append(tens[deca]);
				low %= 10;

				if (low > 0)
					sb.append("-").append(firsts[low]);
			}
	    }
	    
	    return sb.toString().trim();
	}

	private static String setString(int num) {
	    ArrayList<String> chunks = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();

		if (num < 0) {
			sb.append("negative ");
			num *= -1;
		} else if (num == 0)
			return "zero";

	    int i = 0;
	    int cur = num;

	    while (true) {
			if (cur < 1)
				break;

		    int mod = cur % 1000;

		    if (mod != 0) {
			    chunks.add(large[i]);
				chunks.add(makeChunk(mod));
		    }

			cur /= 1000;
			++i;
	    }

	    //This avoids situations where the bottom digits are 0 but the number
	    //is greater than 0. The catch is, large[0] == "", so it is always index 0
	    if (chunks.size() > 2 && chunks.get(1).equals("zero"))
			chunks.remove(1);

	    for (int j=chunks.size(); j > 0; --j) {
			sb.append(chunks.get(j-1)).append(" ");
	    }

	    return sb.toString().trim();
	}

	public int getValue() { return theValue; }
	public String getString() { return theString; }
	public @Override String toString() { return theString; }

	public static String toCardinal(int val) { return setString(val); }

	public static String toCardinal(double value, int sigFigs) {
		int x = (int) value;
		int y = (int) ((value - x) * (int) Math.pow(10, sigFigs));

		StringBuilder sb = new StringBuilder();
		sb.append(toCardinal(x)).append(" point");
		int digits[] = NumberTools.splitDigits(y);

		for (int digit : digits) {
			sb.append(" ").append(toCardinal(digit));
		}

		return sb.toString();
	}


	public static String toCardinal(double value) {
		return toCardinal(value, 4);
	}
	



}