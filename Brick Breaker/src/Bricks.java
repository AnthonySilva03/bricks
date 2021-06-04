import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;

public class Bricks {
	private List<Box> bricks = new ArrayList<Box>();
    private Image lightPiece;
    PhongMaterial lightPieceMaterial = new PhongMaterial();
	
	
	public Bricks() {
        lightPiece = new Image(getClass().getResource("/images/grad3.jpg").toString());
        lightPieceMaterial.setDiffuseMap(lightPiece);
		
	}
	
	public void init() {
	for(double y = 0; y < 3; y++) {
			for(double x = 0; x < 9; x++ ) {
	    		createBrick(x, y,0.0);
			}
		}
	}
	
	private void createBrick(double x, double y, double z) {
		Box brick = new Box(105, 40, 20);
		brick.relocate(x-460+(105*x),y-400+(40*y));
//		brick.setTranslateX(x-500);
//		brick.setTranslateY(y-800);
		brick.setMaterial(lightPieceMaterial);
	    bricks.add(brick);
	}
	
	public List<Box> getBricks() {
		return bricks;
	}
}