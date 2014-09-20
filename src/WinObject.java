import java.awt.Graphics2D;


public abstract class WinObject {
	protected int x, y, width, height;
	protected int xVel, yVel;
	protected int size;
	
	public WinObject(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	public abstract void update();
	public abstract void draw(Graphics2D g);
	public void setxVel(int vel){
		this.xVel = vel;
	}
	public void setyVel(int vel){
		this.yVel = vel;
	}
	
	public abstract boolean isOut();
	
}
