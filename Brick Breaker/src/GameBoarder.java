import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class GameBoarder {
	private double width;
	private double height;
	private double w;
	private double h;
	private double depth;
	private double s;
	private double z = 0;
	private double half = 2;
	private Group boarder = new Group();	
	private Box rightWall = new Box();
	private Box leftWall = new Box();
	private Box topWall = new Box();
	private Box bottomWall = new Box();
	private PhongMaterial  material = new PhongMaterial(Color.BLUEVIOLET);
	
	public GameBoarder(double width, double height, double depth) {
		this.width = width;
		this.height = height;
		this.depth = depth;
		this.w = width+(depth);
		this.h = height+(depth);
		this.s = depth;
		
	}
	public void init() {
		createWall(rightWall,s,h,s, new Point3D(width/half,-width/half,z));
		createWall(leftWall,s,h,s, new Point3D(-width/half,-width/half,z));
		createWall(topWall,w,s,s, new Point3D(-width/half,height/half,z));
		createWall(bottomWall,w,s,s, new Point3D(-width/half,-height/half,z));		
	}
	
	private void createWall(Box wall, double width, double height, double depth, Point3D point) {
		wall.setWidth(width);
		wall.setHeight(height);
		wall.setDepth(depth);
		wall.setMaterial(material);
		wall.relocate(point.getX(), point.getY());
//		wall.setTranslateX(point.getX());
//		wall.setTranslateY(point.getY());
		boarder.getChildren().add(wall);
	}
	
	public Group getBoarder() {
		return boarder;
	}

	
	


}