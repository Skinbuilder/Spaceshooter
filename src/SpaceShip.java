import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class SpaceShip 
{
	private Integer y;
	private Integer x;
	private String shipString = "✈";
	private String hight;
	private String width;
	
	
	public SpaceShip(Integer y, Integer x)
	{
		this.y = y;
		this.x = x;
		
	}

	public void moveup(int delta) {
		this.y = this.y - delta;
	}
	
	public void movedown(int delta) {
		this.y = this.y + delta;
	}

	public void moveleft(int delta) {
		this.x = this.x - delta;
	}

	public void moveright(int delta) {
		this.x = this.x + delta;
	}
	
	public String toString() {
		return"SpaceShip("+this.x+"|"+this.y+")" ;
		
	}

	public void paint(GraphicsContext gc) {
		gc.setFont(Font.font("Verdana", 50));
		gc.fillText(shipString, this.x, this.y);
		
	}

	public Rocket fire()
	//erzeugt rakete von Position von Raumschiff
	{
		Rocket r = new Rocket(this.y, this.x, 15);
		return r;
	}
	
	public Bounds getBounds()
	{
		this.width = null;
		this.hight = null;
		Rectangle r = new Rectangle(this.x, this.y, 30, 10);
		
		return r.getBoundsInLocal();
		
	}
	public void damage() {

        this.shipString = "+++'";
    }
}
