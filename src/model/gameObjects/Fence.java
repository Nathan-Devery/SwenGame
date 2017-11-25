package model.gameObjects;

import java.awt.*;

import general.Stage;
import resources.ImgResources;

/***
 * @author hannahclayton
 *
 *         Fence object, on collision the player loses HP and speed slows.
 *
 */
public class Fence extends GameObject {
	private String objectName = "Type: Fence, ID: F";
	private Point p;
	private double xProp = ImgResources.FENCE.getScaleX();
	private double yProp = ImgResources.FENCE.getScaleY();

	public Fence(Point p) {
		this.p = p;
	}

	@Override
	public void collide(Car c) {
		int hp = c.getHP();
		hp -= 1;
		c.setHP(hp);
		c.decelerate(100);
	}

	@Override
	public String getID() {
		return "F";
	}

	@Override
	public Point getOrigin() {
		return p;
	}

	@Override
	public Rectangle getBoundingBox() {
		int xOffset = (int) (ImgResources.imgDimension / 2 * xProp);
		int yOffset = (int) (ImgResources.imgDimension / 2 * yProp);
		Rectangle bounds = new Rectangle(p.x * ImgResources.imgDimension - xOffset,
				p.y * ImgResources.imgDimension - yOffset, ImgResources.imgDimension, ImgResources.imgDimension);
		return bounds;
	}

	@Override
	public ImgResources getImageEnum(Stage s) {
		return ImgResources.FENCE;
	}

	@Override
	public boolean equals(Object o) throws ClassCastException {
		if (o instanceof Fence) {
			Fence b = (Fence) o;
			return p.getX() == b.getOrigin().getX() && p.getY() == b.getOrigin().getY();
		}
		return false;

	}

	@Override
	public boolean isCollidable() {
		return true;
	}

	@Override
	public boolean isDestroyable() {
		return true;
	}

	@Override
	public String toString() {
		return objectName;
	}
}
