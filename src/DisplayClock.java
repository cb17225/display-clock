import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * The Class DisplayClock.
 */
public class DisplayClock extends Application {
  
  /**
   * Organizes the window.
   *
   * @param primaryStage the primary stage
   */
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    // Create a clock and a label
    ClockPane clock = new ClockPane();
    String timeString = clock.getHour() + ":" + clock.getMinute() 
      + ":" + clock.getSecond();
    Label lblCurrentTime = new Label(timeString);

    // Place clock and label in border pane
    BorderPane pane = new BorderPane();
    pane.setCenter(clock);
    pane.setBottom(lblCurrentTime);
    BorderPane.setAlignment(lblCurrentTime, Pos.TOP_CENTER);
    
    Timeline t = new Timeline(new KeyFrame(Duration.millis(10), ae -> {
    	clock.setCurrentTime();
    	lblCurrentTime.setText(clock + "");
    }));
    
    t.setCycleCount(Animation.INDEFINITE);
	t.play();
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 250, 250);
    primaryStage.setTitle("DisplayClock"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }

  /**
   * Opens the window and starts the clock.
   *
   * @param args the arguments
   */
  public static void main(String[] args) {
    launch(args);
  }
}