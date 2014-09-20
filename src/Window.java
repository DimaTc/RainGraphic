import javax.swing.JFrame;


public class Window {
	public static void main(String[] args){
		WinCanvas canvas = new WinCanvas();
		JFrame frame = new JFrame("Rain!");
		frame.add(canvas);
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		canvas.start();
	}
}
