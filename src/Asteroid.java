import java.util.Random;

import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

public class Asteroid
{
	private Integer y;
	private Integer x;
	private Integer speed;
	private String Asteroid = "*";
	
	public Asteroid(Integer y, Integer x, Integer speed)
	{
		this.y = y;
		this.x = x;
		this.speed = speed;
	}
	
	public void move() {
		this.x -= this.speed;
	}
	
	public String toString() {
		return"Asteroid("+this.x+"|"+this.y+")" ;
	}
	public void paint(GraphicsContext gc) {
		gc.setFont(Font.font("Verdana", 15));
		gc.fillText(this.Asteroid, this.x, this.y);
		
	}
	public boolean isVisible() {
		return(this.x>0);
	}

	public void reposition(int WIDTH, int HIGHT)
	{
		Random rnd = new Random();
		this.x = WIDTH+rnd.nextInt(WIDTH);
		this.y = rnd.nextInt(HIGHT);
		this.speed = 1+ rnd.nextInt(5);
	}
	public Bounds getBounds() {

        Circle c = new Circle(this.x, this.y, 10);
        return c.getBoundsInLocal();
    }
}
