package unquietcode.util;

import java.util.TimeZone;

/**
 * @author Ben
 * @version 0.1
 * Date: 12/12/10
 */
public class Time {
	public int hours;
	public int minutes;
	public int seconds;
	public int milliseconds;

	public enum Period {
		AM, PM, Millitary;
	}

	public Time() {
		this(0, 0, 0, 0, Period.Millitary, TimeZone.getDefault());
	}

	public Time(int hours, int minutes, int seconds) {
		this(hours, minutes, seconds, 0, Period.Millitary, TimeZone.getDefault());
	}

	public Time(int hours, int minutes, int seconds, Period period) {
		this(hours, minutes, seconds, 0, period, TimeZone.getDefault());
	}

	public Time(int hours, int minutes, int seconds, TimeZone timezone) {
		this(hours, minutes, seconds, 0, Period.Millitary, timezone);
	}

	public Time(int hours, int minutes, int seconds, Period period, TimeZone timezone) {
		this(hours, minutes, seconds, 0, period, timezone);
	}

	public Time(int hours, int minutes, int seconds, int milliseconds) {
		this(hours, minutes, seconds, milliseconds, Period.Millitary, TimeZone.getDefault());
	}

	public Time(int hours, int minutes, int seconds, int milliseconds, Period period) {
		this(hours, minutes, seconds, milliseconds, period, TimeZone.getDefault());
	}

	public Time(int hours, int minutes, int seconds, int milliseconds, TimeZone timezone) {
		this(hours, minutes, seconds, milliseconds, Period.Millitary, timezone);
	}

	public Time(int hours, int minutes, int seconds, int milliseconds, Period period, TimeZone timezone) {
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
		this.milliseconds = milliseconds;
	}

	//== Final ==//
	public static class Final {
		public int hours;
		public int minutes;
		public int seconds;
		public int milliseconds;
		public final Period period;
		public final TimeZone timezone;

		public Final(int hours, int minutes, int seconds) {
			this(hours, minutes, seconds, 0, Period.Millitary, TimeZone.getDefault());
		}
	
		public Final(int hours, int minutes, int seconds, Period period) {
			this(hours, minutes, seconds, 0, period, TimeZone.getDefault());
		}
	
		public Final(int hours, int minutes, int seconds, TimeZone timezone) {
			this(hours, minutes, seconds, 0, Period.Millitary, timezone);
		}
	
		public Final(int hours, int minutes, int seconds, Period period, TimeZone timezone) {
			this(hours, minutes, seconds, 0, period, timezone);
		}
	
		public Final(int hours, int minutes, int seconds, int milliseconds) {
			this(hours, minutes, seconds, milliseconds, Period.Millitary, TimeZone.getDefault());
		}
	
		public Final(int hours, int minutes, int seconds, int milliseconds, Period period) {
			this(hours, minutes, seconds, milliseconds, period, TimeZone.getDefault());
		}
	
		public Final(int hours, int minutes, int seconds, int milliseconds, TimeZone timezone) {
			this(hours, minutes, seconds, milliseconds, Period.Millitary, timezone);
		}
	
		public Final(int hours, int minutes, int seconds, int milliseconds, Period period, TimeZone timezone) {
			this.hours = hours;
			this.minutes = minutes;
			this.seconds = seconds;
			this.milliseconds = milliseconds;
			this.period = period;
			this.timezone = timezone;
		}
	}

	//== Clock ==//
	public static final class Clock {
		public Period period;
		public TimeZone timezone;

		//all of the constructors
		
	}


	//methods to, like, make final. add finals(but return regular)


	//operations
	public Time add(Time other) {
		return add(other, TimeZone.getTimeZone("UTC"));
	}

	public Time add(Time other, TimeZone timezone) {
		//a little more complicated. Have to retreive their times into UTC and return in desired timezone)

		Time t = new Time(hours+other.hours, minutes+other.minutes, seconds+other.seconds);
		t.milliseconds += other.milliseconds;
		return  null;
	}


}
