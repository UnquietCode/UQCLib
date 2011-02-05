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

public class ShinyNumber {
	private final int value;
	
	public ShinyNumber(int number) {
		value = number;
	}
	
	public Ordinal getOrdinal() {
		return getOrdinal(false);
	}
	
	public Ordinal getOrdinal(boolean displayLong) {
		return new Ordinal(value, displayLong);
	}
	
	public String toOrdinal() {
		return toOrdinal(false);
	}
	
	public String toOrdinal(boolean displayLong) {
		return Ordinal.toOrdinal(value, displayLong);
	}
	
	public Cardinal getCardinal() {
		return new Cardinal(value);
	}
	
	public String toCardinal() {
		return Cardinal.toCardinal(value);
	}

	public int getValue() {
		return value;
	}

	public @Override String toString() {
		return Integer.toString(value);
	}
}