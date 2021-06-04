import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class Platform extends Box{
	private double x;
	private double y;
	private double z;
	
	public Platform(double x, double y, double z) {
		super(x,y,z);
		this.x = x;
		this.y = y;
		this.z = z;
		this.setMaterial(new PhongMaterial(Color.AQUA));
	}
	
	public void Init() {
		this.setTranslateX(400);
		this.setTranslateY(500);
	}

	public void Dragged(double mouseX, double mouseY) {
		System.out.println("x = " + x + " y = " + y + " mouseX = " + mouseX + " mouseY = " + mouseY);
        this.setTranslateX(x + mouseX);
        this.setTranslateY(y + mouseY);
		
	};
	
	
}