import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;

public class Paddle extends Cylinder{
	private double x;
	private double y;
	private double z;
	private double r = 10;
	
	public Paddle(int x, int y, int z, double radius) {
		super(40,radius);
		this.x = x;
		this.y = y;
		this.z = z;
		this.setMaterial(new PhongMaterial(Color.AQUA));
	}
	
	
	public void init() {
		this.relocate(x, y);
	}


	public void move(double dir) {
		double xpos = this.getTranslateX() + dir;
        
		if(xpos > -470 && xpos < 420) {
			this.setTranslateX(xpos);
		}
		
	}
	
	public void setX(double x) {
		this.x = x; 
		System.out.println("Mouse x =" + x);
	}
}