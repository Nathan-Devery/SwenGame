package model.gameObjects;

import java.awt.*;

import general.Stage;
import resources.ImgResources;

/***
 *
 * @author hannahclayton
 *
 *         Rock object, is a non destructable object, when a car object collides
 *         with it the car's speed slows to 0.
 *
 */
public class Rock extends GameObject {
	private String objectName = "Type: Rock, ID: R";
	private Point p;
	private double xProp = ImgResources.ROCK.getScaleX();
	private double yProp = ImgResources.ROCK.getScaleY();

	public Rock(Point p) {
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
		return "R";
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
		return ImgResources.ROCK;
	}

	@Override
	public boolean equals(Object o) throws ClassCastException {
		if (o instanceof Rock) {
			Rock b = (Rock) o;
			return p.getX() == b.getOrigin().getX() && p.getY() == b.getOrigin().getY();
		}
		return false;

	}

	@Override
	public boolean isCollidable() {
		return false;
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
