import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FilledJulliaSet extends Application{
	
	public static final int ITER = 50;
	public static final int NUM = 200000;
	public static double rc = 0;
	public static double ic = 0;

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Random rand = new Random();
		BorderPane main = new BorderPane();
		
		Pane pane = new Pane();
		GridPane gpane = new GridPane();
		
		main.setCenter(pane);
		main.setBottom(gpane);
		

		Line l1 = new Line(250, 0, 250, 500);
		Line l2 = new Line(0, 250, 500, 250);
		Line l3 = new Line(250, 0, 260, 10);
		Line l4 = new Line(250, 0, 240, 10);
		Line l5 = new Line(490, 240, 500, 250);
		Line l6 = new Line(490, 260, 500, 250);
		Line p1 = new Line(245, 50, 255, 50);
		Line p2 = new Line(245, 450, 255, 450);
		Line p3 = new Line(50, 245, 50, 255);
		Line p4 = new Line(450, 245, 450, 255);
		
		Text t1 = new Text(258, 55, "2");
		Text t2 = new Text(258, 455, "-2");
		Text t3 = new Text(45, 242, "-2");
		Text t4 = new Text(445, 242, "2");
		
		pane.getChildren().addAll(l1, l2, l3, l4, l5, l6, p1, p2, p3, p4);
		pane.getChildren().addAll(t1, t2, t3, t4);
		
		
		TextField tf1 = new TextField();
		TextField tf2 = new TextField();
		
		gpane.add(new Label("Real"), 1, 0);
		gpane.add(new Label("Im"), 1, 1);
		

		gpane.add(tf1, 2, 0);
		gpane.add(tf2, 2, 1);
		
		gpane.setHgap(10); 
		gpane.setVgap(10); 
		gpane.setPadding(new Insets(10, 10, 10, 10)); 
		
		pane.setOnMouseMoved(e -> {
			pane.getChildren().clear();
			pane.getChildren().addAll(l1, l2, l3, l4, l5, l6, p1, p2, p3, p4);
			pane.getChildren().addAll(t1, t2, t3, t4);
			if ((e.getX()-250)* (e.getX()-250) + (e.getY()-250) * (e.getY()-250) < 200 * 200) {
				rc = e.getX()/100 - 2.5;
				ic = -e.getY()/100 + 2.5;
				tf1.setText(String.format("%.2f", rc));
				tf2.setText(String.format("%.2f", ic));
				for (int count = 0; count < NUM; count ++) {
					double xv = rand.nextDouble() * 500;
					double yv = rand.nextDouble() * 500;
					if (check(xv/100 - 2.5, -yv/100 + 2.5)) {
						Circle c = new Circle(xv, yv, 0.25);
						c.setFill(Color.BLACK);
						pane.getChildren().add(c);
					}
				}
			}
		});
		
        Scene scene = new Scene(main, 500, 650);
        
        primaryStage.setTitle("Filled Julia Set");
        primaryStage.setScene(scene);
        primaryStage.show();
		
	}


	public static boolean check(double r, double i) {
		double rt = r;
		double it = i;
		for (int time = 0; time < ITER; time++) {
			double rtt = rt;
			double itt = it;
			rt = rtt*rtt - itt * itt + rc;
			it = 2*rtt*itt + ic;
			if (rt * rt + it * it >= 2) {
				return false;
			}
		}
		
		return true;
		
	}
	
}
