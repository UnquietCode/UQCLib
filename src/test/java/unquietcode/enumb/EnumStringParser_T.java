package unquietcode.enumb;

/**
 *
 * What a strange class of words those are. For addition we say "plus", and the symbol for addition gets
 * called by the action. So "plus", "minus", "times", "divided by", the symbols are the colloquial, not the
 * mathematical operation.
 *
 * @author  Benjamin Fagin
 * @version 12-23-2010
 */


import org.junit.Test;

import java.lang.reflect.Field;

import static unquietcode.util.Shortcuts.out;
import static unquietcode.util.Shortcuts.outN;
import unquietcode.enumb.EnumStringParser.Value;

/**
 * Five ways you can associate a string with an enum!
 */
public class EnumStringParser_T {
	/*enum Symbol {
		// 1. override everyone's toString method
		PLUS {
			public @Override String toString() {
				return "+";
			}
		},

		// 2. use a constuctor that stores a string
		MINUS("-"),

		// 3. name the enum what you want it to be
		x,

		// 4. use an annotation to keep the string
		@Value("/")
		DIVIDE;


		// (for #2)
		final String string;

		Symbol() {
			string = null;
		}

		Symbol(String s) {
			string = s;
		}
	}

	// (for #4)
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	@interface Value {
		String value(); // no default means required
	}

	// 5. use a map
	static Map<Symbol, String> map = new HashMap<Symbol, String>();
	static {
		map.put(Symbol.PLUS, "+");
		map.put(Symbol.MINUS, "-");
		map.put(Symbol.x, "*");
		map.put(Symbol.DIVIDE, "/");
	}


	@Test
	public void test() {
		StringBuilder sb = new StringBuilder();
		sb.append("2").append(Symbol.PLUS)                  // #1
		  .append("4").append(Symbol.MINUS.string)          // #2
		  .append("7").append(Symbol.x)                     // #3
		  .append("12").append(extractValue(Symbol.DIVIDE)) // #4
		  .append("20").append(map.get(Symbol.MINUS))       // #5
		  .append("33");

		String expected = "2+4-7x12/20-33";
		String actual = sb.toString();

		outN("expected:\t");
		out(expected);
		outN("actual:\t\t");
		out(actual);
		outN("equals?\t\t");
		out(actual.equals(expected));
	}
*/
	static String extractValue(Enum e) {
		Class clazz = e.getClass();
		if (!clazz.isEnum()) { return null; }

		Value name;
		try {
			Field field = clazz.getField(e.name());
			name = field.getAnnotation(Value.class);

			if (name == null)
				return null;
		} catch (Exception ex) {
			return null;
		}

		return name.value();
	}


	// now a test of parsing using the enums

	enum Token {
		@Value("{")
		SET_START,

		@Value("}")
		SET_END,

		@Value("|")
		DIVIDER,

		@Value(":")
		NAME_END,

		@Value(",")
		COMMA;
	}



	/*
		something like a getString(end), getArray(end, delimiter), chomp(end);

		 so here would look like
		    getString(DIVIDER)
		    getString(NAME_END)
		    chomp(SET_START)
		    getArray(SET_END, COMMA)
		    chomp(DIVIDER) //if applicable, but should clear out the buffer otherwise, right?

		 be able to append to the end of the input stream. Takes a String as input, or StringBuffer (but copies the string)
	 */


	@Test
	public void ruleTest() {
		String expected = "initial | state1 : {transition1, transition2} | state2 : {transition3, transition4}";
		String input = " initial | state1 : {transition1, transition2} | state2 : { transition3, transition4 }  ";
		EnumStringParser parser = new EnumStringParser(input);

		parser.setRules().optional(Token.DIVIDER).startLoop().required(Token.NAME_END).required(Token.SET_START)
			  .repeating(Token.COMMA).required(Token.SET_END).optionalIfEnd(Token.DIVIDER).endLoop();
	}

	@Test
	public void machineTest() {
		String expected = "initial | state1 : {transition1, transition2} | state2 : {transition3, transition4}";
		String input = " initial | state1 : {transition1, transition2}|state2 : { transition3, transition4 }  ";
		EnumStringParser parser = new EnumStringParser(input);
		StringBuilder sb = new StringBuilder();

		String initial = parser.getString(Token.DIVIDER);
		if (initial != null) {
			sb.append(initial).append(" ").append(name(Token.DIVIDER)).append(" ");
		}

		while (true) {
			String name = parser.getString(Token.NAME_END);
			parser.chomp(Token.SET_START);
			String elements[] = parser.getStrings(Token.SET_END, Token.COMMA);
			parser.chomp(Token.DIVIDER);

			// reverse
			sb.append(name).append(" ").append(name(Token.NAME_END)).append(" ");
			sb.append(name(Token.SET_START));

			int i = 1;
			for (String e : elements) {
				sb.append(e);

				if (i++ != elements.length) {
					sb.append(name(Token.COMMA));
				}
			}

			sb.append(name(Token.SET_END));

			if (!parser.isEmpty()) {
				sb.append(" ").append(name(Token.DIVIDER)).append(" ");
			} else {
				break;
			}
		}

		String actual = sb.toString();
		outN("expected:\t");
		out(expected);
		outN("actual:\t\t");
		out(actual);
		outN("equals?\t\t");
		out(actual.equals(expected));
	}


	@Test
	public void parsing() {
		String expected = "initial | state1 : {transition1, transition2} | state2 : {transition3, transition4}";
		StringBuffer buffer = new StringBuffer(expected);
		StringBuilder sb = new StringBuilder();

		String initial = match(buffer, Token.DIVIDER);
		if (initial != null) {
			sb.append(initial).append(" ").append(name(Token.DIVIDER)).append(" ");
		}

		// except inside of the parser, if the result is null when it's not optional, then it's a parse exception
		// so the concept of optional needs to be implemented.

		// how about, required is false by default, so will return nulls
		// but if required = true then will throw parse exception

		// maybe a rule builder, like .optional(DIVIDER).optional(NAME_END).... etc/ (though how to track the logic of if no colon then empty set if that were the case, (or even allow it?)


		String buf = buffer.toString().trim();
		while (buf.length() > 0) {
			String name = match(buffer, Token.NAME_END);
			matchCheck(buffer, Token.SET_START);
			String set = match(buffer, Token.SET_END);
			assert(set != null); // would result in ??
			String elements[] = set.split(name(Token.COMMA));
			match(buffer, Token.DIVIDER);

			// reverse
			sb.append(name).append(" ").append(name(Token.NAME_END)).append(" ");
			sb.append(name(Token.SET_START));

			int i = 1;
			for (String e : elements) {
				sb.append(e);

				if (i++ != elements.length) {
					sb.append(name(Token.COMMA));
				}
			}

			sb.append(name(Token.SET_END));
			buf = buffer.toString().trim();
			
			if (buf.length() > 0) {
				sb.append(" ").append(name(Token.DIVIDER)).append(" ");
			}
		}

		// print results
		String actual = sb.toString();
		outN("expected:\t");
		out(expected);
		outN("actual:\t\t");
		out(actual);
		outN("equals?\t\t");
		out(actual.equals(expected));

	}


	//remove this I think
	public String name(Enum e) {
		return extractValue(e);
	}

	public void matchCheck(StringBuffer buffer, Token token) {
		String x = match(buffer, token);    //why did this have to be split to function? TODO look into
		assert(x == null || x.isEmpty());
	}

	public String match(StringBuffer buffer, Token token) {
		String buf = buffer.toString();
		String name = name(token);

		int index = buffer.indexOf(name);
		if (index == -1) {
			return null;
		}

		String front = buffer.substring(0, index);
		buffer.delete(0, index+name.length());
		return front.trim();
	}

}
