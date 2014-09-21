import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;


public class Tiplets extends WinObject{
	
	private Random rand;
	private boolean outOfBounds = false;
	private int STARTING_SPEED = 1;
	
	public Tiplets(int x, int y, int width, int height){
		super(x,y,width,height);
		rand = new Random();
		randSpeed();
	}
	
	public void draw(Graphics2D g){
		switch(size){
		case 0:
			g.setColor(new Color(0xFF4545));
			break;
		case 1:
			g.setColor(new Color(0xaa5555));
			break;
		case 2:
			g.setColor(Color.red);
			break;
		default: 
			g.setColor(Color.yellow);
			break;
		}
		int[] px = {x , x  + width, x + width };
		int[] py = {y   , y - height / 2, y + height / 2 };
		g.fillPolygon(px,py,3);
		g.setColor(Color.black);
	}
	
	public void randSpeed(){
		if(width > 25){
			STARTING_SPEED = 1;
			size = 2;
		}
		if(width > 20 && width <= 25){
			STARTING_SPEED = 2;
			size = 1;
		
		}
		if(width <= 20){
			STARTING_SPEED = 4;
			size = 0;
		}
		setyVel(STARTING_SPEED + rand.nextInt(4));
		
	}
	
	public void update(){
		if(x + xVel > WinCanvas.WIDTH * WinCanvas.SCALE+ width|| x + xVel < 0 - width) outOfBounds = true;
		this.x += xVel;
		if(y + yVel > WinCanvas.HEIGHT * WinCanvas.SCALE+ height|| y + yVel < 0 - width) outOfBounds = true;
		this.y += yVel;
		
		if(outOfBounds){
			y = -width;
			x = rand.nextInt(WinCanvas.WIDTH * WinCanvas.SCALE);
			outOfBounds = false;
			randSpeed();
		}
	}
	
	public boolean isOut(){
		return outOfBounds;
	}
}
