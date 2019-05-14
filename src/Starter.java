
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Starter extends Application implements EventHandler<KeyEvent>
{
	SpaceShip t = new SpaceShip(300,200);
	List<Asteroid> asteroiden = new ArrayList<>();
	List<Rocket> raketen = new ArrayList<>();
	int WIDTH = 500;
	int HIGHT = 600;
	/*public static void main(String[] args)
	{
		// TODO Auto-generated method stub

		SpaceShip s = new SpaceShip(50,50,"Ritti");
		System.out.println(s);
		
		s.movedown(10);
		System.out.println(s);
		
		s.moveleft(10);
		System.out.println(s);
		
		s.moveup(10);
		System.out.println(s);
		
		s.moveright(10);
		System.out.println(s);
		
	
	}	*/
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		
		
		
		primaryStage.setTitle("SpaceShooter - Diewald");
		
		Group root = new Group();
		
		Canvas canvas = new Canvas (500,600);
		canvas.setFocusTraversable(true);
		canvas.setOnKeyPressed(this);
		root.getChildren().add(canvas);
		
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		
		Random rnd = new Random();
		for(int i = 0; i < 25; i++) {
			
			int x = rnd.nextInt(WIDTH);
			int y = rnd.nextInt(HIGHT);
			int s = 1 + rnd.nextInt(4);
			
			Asteroid a = new Asteroid(x,y, s);
			asteroiden.add(a);
		}
		for(int i = 0; i < 25; i++) {
			
			
			Rocket r = new Rocket(i*HIGHT/5, 0,  3);
			raketen.add(r);
		}
		
		
		
		new AnimationTimer() 
        {

            @Override
            public void handle(long now) {
            	canvas.getGraphicsContext2D().clearRect(0, 0, 500, 600);
                t.paint(canvas.getGraphicsContext2D());
                
                List<Asteroid> Loeschen = new ArrayList<>();
                for(Asteroid ast :asteroiden) {
                	ast.paint(canvas.getGraphicsContext2D());
                    ast.move();
                    if(ast.isVisible() == false) {
                        Loeschen.add(ast);
                    }
                }
                
                //Tatsächlice die Raketen löschen
                asteroiden.removeAll(Loeschen);
                
                for(Asteroid ast : Loeschen) {
	                int x = rnd.nextInt(WIDTH)+WIDTH;
	    			int y = rnd.nextInt(HIGHT);
	    			int s = 1 + rnd.nextInt(4);
	                asteroiden.add(new Asteroid(y,x,s));
                }
                
                // Raketen weiterbewegen und löschen
                for(Rocket r : raketen) {
               	 	r.paint(canvas.getGraphicsContext2D());
                    r.move();
                }
                List<Rocket> zuLoeschendeRaketen = new ArrayList<>();
                for(Rocket r : raketen) {
                	if(r.isVisible() == false) {
                		zuLoeschendeRaketen.add(r);
                	}
                }
                raketen.removeAll(zuLoeschendeRaketen);
                
                // Zustammestoßerkennung Rakete va. Raumschiff
                for(Asteroid a : asteroiden) {
                    if(a.getBounds().intersects(t.getBounds())) {
                    	t.damage();
                    }
                }
            }

        }.start();
		t.movedown(20);
		
	}
	@Override
	public void handle(KeyEvent event) {
		 
		 System.out.println(event.getCode().getName());
		 int accelerator = 1;
		 
		 if(event.isShiftDown()) {
			 accelerator = 2;
		 }
		 
		 System.out.println(event.getCode().getName());
		 switch(event.getCode().getName())
		 {
		 case "Up":
			 t.moveup(10*accelerator);
			 break;
		 
		 case "Down":
			 t.movedown(10*accelerator);
			 break;
	 	 
		 case "Left":
			 t.moveleft(10*accelerator);
			 break;
		 
         case "Right":
        	 t.moveright(10*accelerator);
        	 break;
        	 
         case "Space":
        	 Rocket r = t.fire();
        	 raketen.add(r);
        	 break;
 	 	 }
	}
}

