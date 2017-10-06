import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
	public static void main(String[] args) {
		Timer timer = new Timer();
		
		Calendar c = new MyCalendar();

		System.out.println("Time at start: " + c.toString());

		c.add(Calendar.SECOND, 10);

		System.out.println("Should start at: " + c.toString());
		timer.scheduleAtFixedRate(new Task(), c.getTime(), 1000);
	}


	
}

class Task extends TimerTask {
	@Override
	public void run() {
		Calendar c = new MyCalendar();
		System.out.println("Timer ran at time: " + c.toString());
	}

}

class MyCalendar extends GregorianCalendar {
	private static final long serialVersionUID = 1351065698517341472L;

	@Override
	public String toString() {
		DateFormatSymbols symbols = new DateFormatSymbols();
		return String.format("%s %02d %d %s %02d:%02d:%02d.%03d", 
				symbols.getMonths()[get(MONTH)],
				get(DAY_OF_MONTH),
				get(YEAR),
				symbols.getWeekdays()[get(DAY_OF_WEEK)],
				get(HOUR_OF_DAY),
				get(MINUTE),
				get(SECOND),
				get(MILLISECOND));

	}
}
