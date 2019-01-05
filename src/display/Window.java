package display;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * A window which can be used to get keyboard and mouse
 * input as well as provide a canvas to draw to.
 * 
 * @author Gage Davidson
 */
public abstract class Window extends JPanel {
	
	private final JFrame window;
	
	/**
	 * Creates a windowed-borderless fullscreen display.
	 * @param title title of the window
	 */
	public Window(String title) {
		setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		
		window = new JFrame(title);
		window.setUndecorated(true);
		configureWindow();
	}
	
	/**
	 * Creates a windowed display.
	 * @param width width of the display in pixels
	 * @param height height of the display in pixels
	 * @param title title of the window
	 */
	public Window(int width, int height, String title) {
		setPreferredSize(new Dimension(width, height));
		
		window = new JFrame(title);
		configureWindow();
	}
	
	/**
	 * Configures the window with some standard settings. Does not
	 * make the window visible.
	 */
	private void configureWindow() {
		window.add(this);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setLocationRelativeTo(null);
	}
	
	/**
	 * Shows the window.
	 */
	@Override
	public void show() {
		window.setVisible(true);
	}
	
	/**
	 * Queues a repaint on this display with SwingUtilities.
	 * Eventually, paintComponent() will be called to redraw the display.
	 */
	@Override
	public void repaint() {
		SwingUtilities.invokeLater(() -> super.repaint());
	}
	
	/**
	 * Hides the cursor from the display.
	 */
	public void vanishCursor() {
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB),
				new Point(0, 0), "blank cursor"));
	}
	
	/**
	 * Called when the component is being repainted by Java Swing.
	 */
	@Override
	public abstract void paintComponent(Graphics g);
}
