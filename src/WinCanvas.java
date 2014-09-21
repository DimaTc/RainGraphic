import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.Random;


public class WinCanvas extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 320;
	public static final int HEIGHT = 240;
	public static final int SCALE = 2;
	
	private static Dimension dim = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
	
	private boolean running;
	private Thread thread;
	private Handler hnd ;
	private Random rand;
	
	private int fps = 0;
	private int ups = 0;
	
	private int AMOUNT = 150;
	
	public WinCanvas(){
		setPreferredSize(dim);
		setMaximumSize(dim);
		setMinimumSize(dim);
		hnd = new Handler();
		rand = new Random();
		
		for(int i = 0; i < AMOUNT; i++){
			int tSize = 18+ rand.nextInt(20);
			hnd.addObject(new Tiplets(rand.nextInt(dim.width), rand.nextInt(dim.height),tSize, tSize));
		}
		hnd.orderlist();
	}
	
	public synchronized void start(){
		if(running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running)return;
		running = false;
		try{
			thread.join();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public void run(){
		long lastTime = System.nanoTime();
		double ns = 1000000000 / 60;
		double delta = 0;
		long timer = System.currentTimeMillis();
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			while(delta >= 1){
				update();
				delta--;
				ups++;
			}
			fps++;
			draw();
			if(System.currentTimeMillis() - 1000 >= timer){
				timer += 1000;
				System.out.println(fps + " || " + ups);
				ups = fps = 0;
			}
		}
	}
	
	public void update(){
		hnd.update();
		
	}
	
	public void draw(){
		BufferStrategy bs =  getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		Graphics2D g = (Graphics2D)bs.getDrawGraphics();
		//
		g.setColor(Color.yellow);
		g.fillRect(0, 0, dim.width, dim.height);
		hnd.draw(g);
		//
		bs.show();
		g.dispose();
	}
}
