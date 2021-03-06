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

/**
 * @author  Benjamin Fagin
 * @version Nov 28, 2010
 */
public class NumberTools {

	/**
	 * Splits the digits into an array. Does not account for negatives numbers.
	 * eg, -10 will be [1, 0], and not [-1, 0];
	 *
	 * @param num
	 * @return array of digits
	 */
	public static int[] splitDigits(int num) {
		if (num == -2147483648) {
			return new int[]{2,1,4,7,4,8,3,6,4,8};
		} else if (num < 0) {
			num *= -1;
		}

		ArrayList<Integer> digits = new ArrayList<Integer>();

		do {
			digits.add(num % 10);
			num /= 10;
		} while (num > 0);

		int[] retval = new int[digits.size()];
		for (int i=0; i < retval.length; ++i)
			retval[retval.length-i-1] = digits.get(i);

		return retval;
	}

	public static String addCommas(int num) {
		StringBuilder sb = new StringBuilder();
		int cur;
		boolean negative = false;

		if (num == -2147483648) {
			return "-2,147,483,648";
		} else if (num < 0) {
			negative = true;
			num *= -1;
		}

		while ((cur = (num % 1000)) != num) {
			if (cur < 100)
				if (cur < 10)
					sb.insert(0, ",00" + cur);
				else
					sb.insert(0, ",0" + cur);
			else
				sb.insert(0, "," + cur);

			num /= 1000;
		} sb.insert(0, cur);


		if (negative) {
			sb.insert(0, "-");
		}

		return sb.toString();
	}

//TODO need to resolve issues with precision
//	public static String percentage(double val, int places) {
//		return truncateDouble(val, places) + "%";
//	}

	public static String truncateDouble(double val, int places) {
		boolean negative = false;

		if (val == Double.NaN || val == Double.NEGATIVE_INFINITY || val == Double.MIN_VALUE) {
			return String.valueOf(val);
		} else if (val < 0) {
			val *= -1;
			negative = true;
		}

		long front = (long) val;
		String sFront = Long.toString(front);
		String rest = Double.toString(val).substring(sFront.length()+1);
		int aPlaces = places > rest.length()
					? rest.length()
					: places;
		try {
			StringBuilder sb = new StringBuilder();

			if (negative)
				sb.append("-");

			sb.append(sFront);
			if (places > 0)
				sb.append(".");
			sb.append(rest.substring(0, aPlaces));

			while (aPlaces < places) {
				sb.append("0");
				++aPlaces;			
			}

			return sb.toString();
		} catch (Exception ex) {
			throw new IllegalArgumentException("invalid number of places");
		}
	}

	public static String signum(double val, int sigfigs) {
		int front = (int) val;
		String sFront = Integer.toString(front);
		String full = Double.toString(val);

		try {
			return full.substring(0, sFront.length() + 2 + sigfigs-sFront.length());
		} catch (Exception ex) {
			throw new IllegalArgumentException("invalid number of significant digits");
		}
	}
}

