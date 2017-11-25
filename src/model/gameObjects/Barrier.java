package model.gameObjects;

import java.awt.Point;
import java.awt.Rectangle;

import general.Stage;
import resources.ImgResources;

public class Barrier extends GameObject {
	public static final int objectSize = 50; // 50 x 50 pixels for an image
	private String objectName = "Type: Barrier, ID: F";
	private Point p;
	private double xProp = ImgResources.BARRIER.getScaleX();
	private double yProp = ImgResources.BARRIER.getScaleY();

	public Barrier(Point p) {
		this.p = p;
	}

	@Override
	public void collide(Car c) {
		int hp = c.getHP();
		hp -= 1;
		c.setHP(hp);
	}

	@Override
	public String getID() {
		return "B";
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
		return ImgResources.BARRIER;
	}

	@Override
	public boolean equals(Object o) throws ClassCastException {
		if (o instanceof Barrier) {
			Barrier b = (Barrier) o;
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
