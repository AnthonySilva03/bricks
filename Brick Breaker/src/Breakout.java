import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Breakout extends Application {

	Stage window;
	
	Circle dot;
	Rectangle player;
	
	double dotVelX;
	double dotVelY;
	
	double maxAngle = 75;
	
	double dotSpeed = .0000002;
	
	int score = 0;
	
	@Override
	public void start(Stage mainStage) throws Exception {
		window = mainStage;
		
		Group root = new Group();
		Scene scene = new Scene(root, 1280, 720);
		
		scene.setFill(Color.BLACK);
		
		window.setTitle("Breakout");
		
		window.setScene(scene);
		
		player = new Rectangle(560, 680, 160, 20);
		player.setFill(Color.BLUE);
		
		dot = new Circle(640, 600, 10, Color.AQUA);
		
		root.getChildren().addAll(player, dot);
		
		List<Rectangle> bricks = new ArrayList<Rectangle>();
		
		for (int i = 0; i < 20; i++) {
			for (int j = 1; j < 5; j++) {
				Rectangle rect = new Rectangle(i*64, j*60, 64, 60);
				rect.setStroke(Color.BLACK);
				rect.setFill(Color.RED);
				
				root.getChildren().add(rect);
				bricks.add(rect);
			}
		}
		
		List<String> codes = new ArrayList<String>();

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();

				if (!codes.contains(code)) {
					codes.add(code);
				}
			}
		});

		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();

				codes.remove(code);
			}
		});
		
		dotVelY = .00000005;
		
		Text scoreText = new Text("Score: " + score);
		scoreText.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 20));
		scoreText.setFill(Color.WHITE);
		scoreText.setStroke(Color.WHITE);
		scoreText.setX(scene.getWidth()-scoreText.getLayoutBounds().getWidth());
		scoreText.setY(scoreText.getLayoutBounds().getHeight());
		
		root.getChildren().add(scoreText);
		new AnimationTimer() {
			
			long lnt = System.nanoTime();
			
			@Override
			public void handle(long cnt) {
				long elapsed = cnt - lnt;
				lnt = cnt;
				
				scoreText.setText("Score: " + score);
				scoreText.setX(scene.getWidth()-scoreText.getLayoutBounds().getWidth());
				
				if (dot.getCenterX()-dot.getRadius()-4 <= 0)
					dotVelX=Math.abs(dotVelX);
					
				if(dot.getCenterX()+dot.getRadius()+4 >= scene.getWidth())
					dotVelX = Math.abs(dotVelX)*-1;
				
				if (dot.getCenterY()-dot.getRadius()-4 <= 0)
					dotVelY *= -1;
				
				if (dot.getCenterY()+dot.getRadius() >= scene.getHeight())
					this.stop();
				
				if (player.getBoundsInLocal().intersects(dot.getBoundsInLocal())) {
					double relIntersectX = ((player.getX()+player.getWidth())/2)-dot.getCenterX();
					
					double normRelIntersectX = (relIntersectX/(player.getWidth()/2));
					double bounceAngle = normRelIntersectX * maxAngle;
					System.out.println(bounceAngle);
					dotVelX = dotSpeed * Math.cos(bounceAngle);
					dotVelY = dotSpeed * Math.sin(bounceAngle);
				}
				
				for (Rectangle brick : bricks) {
					if (brick.getBoundsInLocal().intersects(dot.getBoundsInLocal())) {
						//--[This Physics doesn't work]--//
//						double relIntersectX = ((brick.getX()+brick.getWidth())/2)-dot.getCenterX();
//						
//						double normRelIntersectX = (relIntersectX/(brick.getWidth()/2));
//						
//						double relIntersectY = ((brick.getY()+brick.getHeight())/2)-dot.getCenterY();
//						
//						double normRelIntersectY = (relIntersectY/(brick.getHeight()/2));
//						
//						double bounceAngle = normRelIntersectX*normRelIntersectY*maxAngle;
//						
//						dotVelX = ((normRelIntersectX==1||normRelIntersectX==-1)?(dotVelX*-1):(dotSpeed*Math.cos(bounceAngle)));
//						
//						dotVelY = ((normRelIntersectY == 1 || normRelIntersectY == -1)?(dotVelY*-1):(dotSpeed*Math.sin(bounceAngle)));
						
						//--[Implement Working Physics]--//
						if (dot.getCenterX() <= brick.getX() || dot.getCenterX() >= brick.getX()+brick.getWidth())
							dotVelX *= -1;
						
						if (dot.getCenterY() <= brick.getY() || dot.getCenterY() >= brick.getY()+brick.getHeight())
							dotVelY *= -1;
						
						bricks.remove(brick);
						root.getChildren().remove(brick);
						
						score += 100;
						dotSpeed += .00000001;
						
						if (bricks.isEmpty())
							this.stop();
						
						break;
					}
				}
				
				if (codes.contains("LEFT")) {
					if (player.getX()>0) {
						player.setX(player.getX()-3);
					}
				}
				if (codes.contains("RIGHT")) {
					if (player.getX()+player.getWidth()<scene.getWidth()) {
						player.setX(player.getX()+3);
					}
				}
				
				update(elapsed);
			}
			
		}.start();
		
		window.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void update(long time) {
		dot.setCenterX(dot.getCenterX() + (dotVelX*time));
		dot.setCenterY(dot.getCenterY() + (dotVelY*time));
	}

}