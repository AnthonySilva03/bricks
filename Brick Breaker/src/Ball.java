import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.util.Duration;

public class Ball extends Sphere{
	private double x;
	private double y;
	private double z;
    private double deltaX = 3;
    private double deltaY = 3;
	private Timeline timeLine;
	
	
	public Ball(int x, int y, int z, double radius) {
		super(radius);
		this.x = x;
		this.y = y;
		this.z = z;
		timeLine = new Timeline();
		this.setMaterial(new PhongMaterial(Color.BLUE));
	}
	
	
	public void init() {
		relocate(x, y);
//		this.setTranslateX(x);
//		this.setTranslateY(x);
//        timeLine = new Timeline(new KeyFrame(Duration.millis(5), e -> mvBall()));
//		timeLine.setCycleCount(Timeline.INDEFINITE);
		
	}

//	public void dragged(double mouseX, double mouseY) {
//		System.out.println("x = " + x + " y = " + y + " mouseX = " + mouseX + " mouseY = " + mouseY);
//        this.setTranslateX(mouseX);
//        this.setTranslateY(mouseY);
//		
//	}
	
//	public void mvBall() {
//		if(getLayoutX() < -440 || getLayoutX() > 430) {
//			deltaX *= -1;
//		}
//		if(getLayoutY() < -440 || getLayoutY() > 430) {
//			deltaY *= -1;
//		}
//		
//		setLayoutX(getLayoutX() + deltaX);
//		setLayoutY( getLayoutY() + deltaY);
//		System.out.println("x = " + getLayoutX() + " y = " + getLayoutY());
//		
//	}

	public double getDeltaX() {
		return deltaX;
	}


	public void setDeltaX(double deltaX) {
		this.deltaX = deltaX;
	}


	public double getDeltaY() {
		return deltaY;
	}


	public void setDeltaY(double deltaY) {
		this.deltaY = deltaY;
	}


	public void picked() {
		timeLine.play();
		
	}
}