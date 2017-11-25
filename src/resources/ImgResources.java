package resources;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/***
 * 
 * @author nathan devery
 *
 */
public enum ImgResources {
	CAR1("car_v1.png", 0.4, 0.9), 
	CAR2("car_v2.png", 0.4, 0.9), 
	CAR3("car_v3.png", 0.4, 0.9), 
	SNOW("snow.png", 1, 1), 
	GRASS("grass.png", 1, 1), 
	DIRT("dirt.png", 1, 1), 
	DESERT("desert.png", 1, 1), 
	FENCE("barrels.png", 0.6, 0.6), 
	BARRIER("barrier.png", 0.75, 0.75), 
	BOLT("bolt.png", 0.6, 0.6), 
	COIN("coin.png", 0.4, 0.4), 
	TREE("tree.png", 0.65, 0.65), 
	DIRTROAD("dirtroad.png", 1, 1), 
	FOREST("forest.png", 1, 1), 
	HILLS("hills.png", 1, 1), 
	CHECKPOINT1("checkpoint1.png", 1, 1), // start
	CHECKPOINT2("checkpoint2.png", 1, 1), // finish
	CURVE1("curve1.png", 1, 1), 
	CURVE2("curve2.png", 1, 1), 
	CURVE3("curve3.png", 1, 1), 
	CURVE4("curve4.png", 1, 1), 
	ROCK("rock.png", 0.85, 0.85), 
	MAIN("road.png", 1, 1), 
	MOUNTAINS("mountains.png", 1, 1), 
	FIGHTER("fighters.png", 1, 1), 
	HEART("heart.png", 0, 0), 
	GAS("gass.png", 0, 0);

	// Follow with more
	public static final int imgDimension = 50;
	public double scaleX;
	public double scaleY;

	public final BufferedImage img;

	ImgResources(String resourceName, double x, double y) {
		scaleX = x;
		scaleY = y;
		try {
			img = ImageIO.read(ImgResources.class.getResource(resourceName));
		} catch (IOException e) {
			throw new Error(e);
		}
	}

	/**
	 * @param d
	 * @return scaled image
	 */
	public Image scale(Dimension d) {
		Image scaled = img.getScaledInstance(d.width, d.height, Image.SCALE_DEFAULT);
		return scaled;
	}

	/***
	 * Rotates given image
	 * 
	 * @param img
	 * @param angle
	 * @return
	 */
	public static BufferedImage rotateImage(BufferedImage img, double angle) {
		AffineTransform transform = new AffineTransform();
		transform.rotate(Math.toRadians(angle), img.getWidth() / 2, img.getHeight() / 2);
		AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
		BufferedImage rotatedImg = op.filter(img, null);
		return rotatedImg;
	}

	public double getScaleX() {
		return scaleX;
	}

	public double getScaleY() {
		return scaleY;
	}

	public Rectangle drawHp(int hp) {
		Rectangle HP = new Rectangle(hp, 20);
		return HP;
	}

	public Rectangle drawFuel(int f) {
		Rectangle F = new Rectangle(f, 20);
		return F;
	}

}
