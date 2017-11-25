package model.gameObjects;

import java.awt.Point;
import java.awt.Rectangle;

import general.Stage;
import resources.ImgResources;

/***
 *
 * @author hannahclayton
 *
 * Tree object, on collision the player loses HP.
 *
 */
public class Tree extends GameObject {
	private String objectName = "Type: Tree, ID: T";
	private Point p;
	private double xProp = ImgResources.TREE.getScaleX();
	private double yProp = ImgResources.TREE.getScaleY();

	public Tree(Point p) {
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
		return "T";
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
		return ImgResources.TREE;
	}

	@Override
	public boolean equals(Object o) throws ClassCastException {
		if (o instanceof Tree) {
			Tree b = (Tree) o;
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
