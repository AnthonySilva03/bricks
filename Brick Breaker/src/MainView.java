import java.util.ArrayList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainView extends Application {
    private PerspectiveCamera camera;
    private final double sceneWidth = 1000;
    private final double sceneHeight = 1000;
    private final Rotate rotateX = new Rotate(40, Rotate.X_AXIS);
    private final Rotate rotateY = new Rotate(1, Rotate.Y_AXIS);
    private Timeline timeLine;
    Text scoreText;
	Scene scene;
	int score = 0;
	
    @Override
    public void start(Stage stage) {
        
        newGame(stage);
    }

    private void newGame(Stage stage) {
    	Group root = new Group();
    	Group shapes = new Group();
        Ball ball = new Ball(0,100,0,sceneWidth/40);
        ball.init();
        Paddle paddle = new Paddle(0,400,0,sceneWidth/40);
        paddle.init();
        GameBoarder boarder = new GameBoarder(sceneWidth,sceneHeight,30);
        boarder.init();
        Bricks bricks = new Bricks();
        bricks.init();
        score = 0;
        scoreText = new Text("Score: " + score);
        scoreText.setY(-510);
        scoreText.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 20));
		scoreText.setFill(Color.WHITE);
		scoreText.setStroke(Color.WHITE);
		scene = new Scene(root, sceneWidth, sceneHeight, true, SceneAntialiasing.BALANCED);
        scene.setFill(Color.BLACK);
		List<String> codes = new ArrayList<String>();

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();

				if (!codes.contains(code)) {
					codes.add(code);
				}
				
				if (code.equals("SPACE")) {
					timeLine.play();
				}
				
				if (code.equals("R")) {
					newGame(stage);
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
		
	//	List<Node> br = new ArrayList<Node>();
	//	br.addAll(bricks.getBricks().getChildren());
    	shapes.getChildren().addAll(ball,paddle);
        shapes.getChildren().addAll(boarder.getBoarder().getChildren());
        shapes.getChildren().addAll(bricks.getBricks());
        root.getChildren().add(shapes);
        root.getChildren().add(scoreText);
        

        timeLine = new Timeline(new KeyFrame(Duration.millis(5), new EventHandler<ActionEvent>() {

            double dx = 2;
            double dy = 2;
            double vx =1;
            double vy =1;
            int o = 0;
            Node hitBrick = null;
            
            @Override
            public void handle(final ActionEvent t) {  		
        		ball.setLayoutX(ball.getLayoutX() + (dx*vy));
        		ball.setLayoutY(ball.getLayoutY() + dy*vx);
        		o++;
        		if (codes.contains("LEFT")) {
					paddle.move(-3);
				}
				if (codes.contains("RIGHT")) {
					paddle.move(3);
				}
				if (ball.getBoundsInParent().getCenterY() > 400) {
					timeLine.stop();
					scoreText.setText("GAME OVER!! Press R to restart");
				}
        		for (Node n : shapes.getChildren()) {
        			if(!(n instanceof Ball)) {
        				Bounds item = n.getBoundsInParent();
        				Bounds circ = ball.getBoundsInParent();
        				if(circ.intersects(item)) {
//        					System.out.println("---------------------------------------------------Itiration value =  " + o);
//        					double th = Math.toDegrees(90) + Math.toDegrees(Math.atan((item.getCenterY()-circ.getCenterY())/(item.getCenterX()-circ.getCenterX())));
//        					System.out.println("atan = "+ th + " sin = " + Math.sin(th) + "  cos = " + Math.cos(th));
        					System.out.println("ball x:" + circ.getCenterX() +"  y= " + circ.getCenterY() + circ);
        					System.out.println("item x:" + item.getCenterX() +"  y= " + item.getCenterY() + item);    
//        					double dh = circ.getHeight()/2 + item.getHeight()/2;
//        					double xh = Math.abs(item.getCenterY()) -Math.abs(circ.getCenterY());
//        					double dw = circ.getWidth()/2 + item.getWidth()/2;
//        					double xw = Math.abs(item.getCenterX()) -Math.abs(circ.getCenterX());
//        					if(dh%xh < 4 && Math.abs(dh/xh) <=1.5) {
//        						dy*= -1;
//        						System.out.println("y fliped" + "dh = " + dh + " xh = " + xh);
//        					}
//        					if(dw%xw < 4 && Math.abs(dw/xw) <=1.5) {
//        						dx*= -1;
//        						System.out.println("x fliped"  + "dw = " + dw + " xw = " + xw);
//        					}
        					
        					if(Math.abs(circ.getMinX()-item.getMaxX()) <=3 || Math.abs(circ.getMaxX()-item.getMinX()) <=3) dx *= -1;
        					if(Math.abs(circ.getMinY()-item.getMaxY()) <=3 || Math.abs(circ.getMaxY()-item.getMinY()) <=3) dy *= -1;
        					System.out.println("new Y = " +dy +" new X = "+dx);
        					System.out.println("bouncing shape " +n);
        					if(bricks.getBricks().contains(n)) {
            					System.out.println("bouncing shape remove " +n);
            					hitBrick = n;
        						score += 10;
        						scoreText.setText("Score: " + score);
        					}
//        					System.out.println("dw = " +dw+ " xw = " +xw);
//        					System.out.println("dh = " +dh+ " xh = " +xh);
        					if (score == 270) {
        						scoreText.setText("YOU WIN!!");
        						timeLine.stop();
        					}
        				}
        			}
        		}
        		if(hitBrick != null) {
					shapes.getChildren().remove(hitBrick);
					bricks.getBricks().remove(hitBrick);
					hitBrick = null;
        		}
            }
        }));

        timeLine.setCycleCount(Timeline.INDEFINITE);

        camera = new PerspectiveCamera(true);
        camera.setVerticalFieldOfView(false);
        camera.setNearClip(0.1);
        camera.setFarClip(100000.0);
        camera.getTransforms().addAll (rotateX, rotateY, new Translate(0, 0, -2500));
        scene.setCamera(camera);


        stage.setTitle("Brick Breaker");
        stage.setScene(scene);
        stage.show();
		
	}

	public static void main(String[] args) {
        launch(args);
    }
    
}