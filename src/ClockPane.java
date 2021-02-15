import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import java.lang.Math;


/**
 * The Class ClockPane.
 */
public class ClockPane extends Pane {
	
	/** The current hour. */
	private double hour;
	
	/** The current minute. */
	private double minute;
	
	/** The current second. */
	private double second;
	
	
	/**
	 * Instantiates a new clock pane.
	 */
	public ClockPane() {
		setCurrentTime();
	}

	/**
	 * Instantiates a new clock pane.
	 *
	 * @param hour the hour
	 * @param minute the minute
	 * @param second the second
	 */
	public ClockPane(double hour, double minute, double second) {
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}

	/**
	 * Gets the hour.
	 *
	 * @return the hour
	 */
	public double getHour() {
		return hour;
	}
	
	/**
	 * Sets the hour.
	 *
	 * @param hour the new hour
	 */
	public void setHour(int hour) {
		this.hour = hour;
		paintClock();
	}

	/**
	 * Gets the minute.
	 *
	 * @return the minute
	 */
	public double getMinute() {
		return minute;
	}
	
	/**
	 * Sets the minute.
	 *
	 * @param minute the new minute
	 */
	public void setMinute(int minute) {
		this.minute = minute;
		paintClock();
	}
	
	/**
	 * Gets the second.
	 *
	 * @return the second
	 */
	public double getSecond() {
		return second;
	}
	
	/**
	 * Sets the second.
	 *
	 * @param second the new second
	 */
	public void setSecond(int second) {
		this.second = second;
		paintClock();
	}

	
	/**
	 * Sets the current time.
	 */
	public void setCurrentTime() {
		Calendar calendar = new GregorianCalendar();
		
		// rigid movement (>>>MUST USE INTEGER TYPE FOR INSTANCE VARIABLES<<<)
				
		//		this.second = calendar.get(Calendar.SECOND);
		//		this.minute = calendar.get(Calendar.MINUTE);
		//		this.hour = calendar.get(Calendar.HOUR_OF_DAY);
		//		paintClock();
		
		// smooth movement (uses UNIX time)
		
		long time = calendar.getTimeInMillis();
		double totalDays = time / 86400000.0;
		String daysString = totalDays + "";
		String fractionOfCurrentDay = daysString.substring(daysString.indexOf('.'));
		
		this.hour = Double.parseDouble(fractionOfCurrentDay) * 24 - 7;
		this.minute = Double.parseDouble(fractionOfCurrentDay) * 1440 - 420;
		this.second = Double.parseDouble(fractionOfCurrentDay) * 86400 - 25200;
		paintClock();
	}
	
	/**
	 * Paint the clock.
	 */
	private void paintClock() {
		// Code the clock based upon getWidth(), getHeight();
		double clockRadius = 0.8 * 0.5 * Math.min(getWidth(), getHeight());
		double centerX = getWidth() / 2;
		double centerY = getHeight() / 2;
		
		Circle circle = new Circle(centerX, centerY, clockRadius);
		circle.setFill(Color.WHITE);
		circle.setStroke(Color.BLACK);
		
		// don't touch this code...
		Text t1 = new Text(centerX-5,centerY-clockRadius+12,"12");
		Text t2 = new Text(centerX-clockRadius+3,centerY+5,"9");
		Text t3 = new Text(centerX+clockRadius-10,centerY+3,"3");
		Text t4 = new Text(centerX-3,centerY+clockRadius-3,"6");
		
                // sLength is the length of the second hand
		double sLength = 0.8 * clockRadius;
		// You need to calculate the X,Y values of the endpoint
		
		double secondX = sLength * Math.sin((second / 60.0) * 2 * Math.PI) + centerX;
		double secondY = -1 * sLength * Math.cos((second / 60.0) * 2 * Math.PI) + centerY;
		Line sLine = new Line(centerX,centerY,secondX,secondY);
		sLine.setStroke(Color.RED); // adjust your own color here
		
                // mLength is the length of the minute hand
		double mLength = 0.65 * clockRadius;
		// You need to calculate the X,Y values of the endpoint
		double minuteX = mLength * Math.sin((minute / 60.0) * 2 * Math.PI) + centerX;
		double minuteY = -1 * mLength * Math.cos((minute / 60.0) * 2 * Math.PI) + centerY;
		Line mLine = new Line(centerX,centerY,minuteX,minuteY);
		mLine.setStroke(Color.BLUE); // adjust your own color here
		
                // hLength is the length of the hour hand
		double hLength = 0.5 * clockRadius;
		// You need to calculate the X,Y values of the endpoint
		double hourX = hLength * Math.sin((hour / 12.0) * 2 * Math.PI) + centerX;
		double hourY = -1 * hLength * Math.cos((hour / 12.0) * 2 * Math.PI) + centerY;
		Line hLine = new Line(centerX,centerY,hourX,hourY);
		hLine.setStroke(Color.GREEN); // adjust your own color here...
		
		getChildren().clear();
		getChildren().addAll(circle,t1,t2,t3,t4,sLine,mLine,hLine);
	}
	
	/**
	 * Sets the width.
	 *
	 * @param width the new width
	 */
	// The next two methods of BorderPane are overridden because we need to force a repaint of the clock
	@Override 
	public void setWidth(double width) {
		super.setWidth(width);
		paintClock();
	}
	
	/**
	 * Sets the height.
	 *
	 * @param height the new height
	 */
	@Override
	public void setHeight(double height) {
		super.setHeight(height);
		paintClock();
	}
	
	/**
	 * Returns the time in a more readable form.
	 *
	 * @return the string
	 */
	public String toString() {
		Calendar calendar = new GregorianCalendar();
		// use this for military time: 
		
		// return (calendar.get(Calendar.HOUR) + 12) + ":" + calendar.get(Calendar.MINUTE) 
	    // + ":" + calendar.get(Calendar.SECOND);
		
		// otherwise, use this:
		return calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) 
	      + ":" + calendar.get(Calendar.SECOND);
	}
	
}
