import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

public class Rocket
{

		private Integer y;
		private Integer x;
		private Integer speed;
		private String Rocket = "3==============>";
		
		public Rocket(Integer y, Integer x, Integer speed)
		{
			this.y = y;
			this.x = x;
			this.speed = speed;
		}
		
		public void move() {
			this.x += this.speed;
		}
		public String toString() {
			return"Rocket("+this.x+"|"+this.y+")" ;
		}
		public boolean isVisible() {
			return(this.x<600);
		}
		public void paint(GraphicsContext gc) {
			gc.setFont(Font.font("Verdana", 15));
			gc.fillText(this.Rocket, this.x, this.y);
			
		}
		
}
