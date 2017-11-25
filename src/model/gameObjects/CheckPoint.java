package model.gameObjects;

import java.awt.*;

import general.Stage;
import resources.ImgResources;

/***
 *
 * @author hannahclayton
 *
 *         Check Point object, checkpoint with the race that the player must
 *         pass to complete various stages and levels of the game.
 *
 *         Once passing the checkpoint the player gains HP, if it's below 50 and
 *         gets a money reward.
 *
 */
public class CheckPoint extends GameObject {
	public static final int objectSize = 50;
	private String objectName = "Type: Check Point, ID: C";
	private Point p;
	private double xProp = ImgResources.CHECKPOINT1.getScaleX();
	private double yProp = ImgResources.CHECKPOINT1.getScaleY();

	public CheckPoint(Point p) {
		this.p = p;
	}

	@Override
	public void collide(Car c) {
		int hp = c.getHP();
		if (hp < 50) { // HP when full is 100
			hp = 100;
		}
		c.setHP(hp);
		c.setFinished();

	}

	@Override
	public String getID() {
		return "C";
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
		return ImgResources.CHECKPOINT1;
	}

	@Override
	public boolean equals(Object o) throws ClassCastException {
		if (o instanceof CheckPoint) {
			CheckPoint b = (CheckPoint) o;
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
		return false;
	}

	@Override
	public String toString() {
		return objectName;
	}

}