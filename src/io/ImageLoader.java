package io;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Provides functions to load images from the computer.
 * 
 * @author Gage Davidson
 */
public class ImageLoader {
	
	/**
	 * Cannot be instantiated.
	 */
	private ImageLoader() {
	}
	
	/**
	 * Loads the image from the given filepath.
	 * @param filepath path to the image
	 * @return loaded image
	 * @throws IOException if something goes wrong
	 */
	public static BufferedImage loadImage(String filepath) throws IOException {
		return loadImage(new File(filepath));
	}
	
	/**
	 * Loads the given image file.
	 * @param file image file to load
	 * @return loaded image
	 * @throws IOException if something goes wrong
	 */
	public static BufferedImage loadImage(File file) throws IOException {
		return ImageIO.read(file);
	}
}
