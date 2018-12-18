package display;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * A display which can be used to get keyboard and mouse
 * input as well as provide a canvas to draw to.
 * 
 * @author Gage Davidson
 */
public abstract class Window {
	
	private JPanel panel;
	
	/**
	 * Creates a fullscreen display.
	 * @param title title of the window
	 */
	public Window(String title) {
		initPanel(Toolkit.getDefaultToolkit().getScreenSize());
		addInput();
		
		JFrame frame = new JFrame(title);
		frame.add(panel);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	/**
	 * Creates a windowed display.
	 * @param width width of the display in pixels
	 * @param height height of the display in pixels
	 * @param title title of the window
	 */
	public Window(int width, int height, String title) {
		initPanel(new Dimension(width, height));
		addInput();
		
		JFrame frame = new JFrame(title);
		frame.add(panel);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	private void initPanel(Dimension dim) {
		panel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				draw(g);
			}
		};
		
		panel.setPreferredSize(dim);
	}
	
	private void addInput() {
		panel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				Window.this.keyPressed(evt.getKeyCode());
			}
		});
		
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent evt) {
				Window.this.mousePressed(evt.getX(), evt.getY());
			}
		});
	}
	
	/**
	 * Queues a repaint on this display with SwingUtilities.
	 * Eventually, draw() will be called to redraw the display.
	 */
	public void repaint() {
		SwingUtilities.invokeLater(() -> panel.repaint());
	}
	
	/**
	 * Requests OS focus on the display.
	 */
	public void requestFocus() {
		panel.requestFocus();
	}
	
	/**
	 * Hides the cursor from the display.
	 */
	public void vanishCursor() {
		panel.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB),
				new Point(0, 0), "blank cursor"));
	}
	
	/**
	 * Draws on the panel.
	 * @param g Graphics to draw with
	 */
	public abstract void draw(Graphics g);
	
	/**
	 * Called when a key is pressed.
	 * @param key key pressed (according to KeyEvent.VK_xx)
	 */
	public abstract void keyPressed(int key);
	
	/**
	 * Called when the mouse is clicked.
	 * @param x x-coordinate of the click
	 * @param y y-coordinate of the click
	 */
	public abstract void mousePressed(int x, int y);
}
