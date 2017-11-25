package model.gameObjects;

import java.awt.Point;
import java.awt.Rectangle;

import general.Stage;
import resources.ImgResources;

/***
 *
 * @author hannahclayton
 *
 *
 *         Coin object. On collision the player gains money On use player loses
 *         money
 */
public class Coin extends GameObject {
	public static final int objectSize = 50; // 50 x 50 pixels for an image
	private String objectName = "Type: Coin, ID: N";
	private Point p;
	private double xProp = ImgResources.COIN.getScaleX();
	private double yProp = ImgResources.COIN.getScaleY();

	public Coin(Point p) {
		this.p = p;
	}

	public void use(Car c) {
		int money = c.getMoney();
		money -= 1;
		c.setMoney(money);
	}

	@Override
	public void collide(Car c) {
		int money = c.getMoney();
		money += 2;
		c.setMoney(money);
	}

	@Override
	public String getID() {
		return "N";
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
		return ImgResources.COIN;
	}

	@Override
	public boolean equals(Object o) throws ClassCastException {
		if (o instanceof Coin) {
			Coin b = (Coin) o;
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
