package display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * Extends the Window class with an FPS.
 * 
 * @author Gage Davidson
 */
public abstract class Display extends Window implements Runnable {
	
	private final int fps;
	
	/**
	 * Creates a windowed-borderless fullscreen display.
	 * @param title title of the window
	 * @param fps frames per second for the display
	 */
	public Display(String title, int fps) {
		super (title);
		this.fps = fps;
	}
	
	/**
	 * Creates a windowed display.
	 * @param width width of the display in pixels
	 * @param height height of the display in pixels
	 * @param title title of the window
	 * @param fps frames per second for the display
	 */
	public Display(int width, int height, String title, int fps) {
		super (width, height, title);
		this.fps = fps;
	}
	
	/**
	 * Starts the display.
	 */
	@Override
	public void run() {
		show();
		
		new Timer(1000 / fps, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				tick();
				repaint();
			}
		}).start();
		
		requestFocus();
	}
	
	/**
	 * A tick of the display. This is called the same number
	 * of times as draw(), but is asynchronous to draw().
	 */
	public abstract void tick();
	
	public int getFPS() { return fps; }
}
