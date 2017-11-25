package model.gameObjects;

import java.awt.*;

import general.Stage;
import resources.ImgResources;

/***
 *
 * @author hannahclayton
 *
 *         Bolt object, on collision the car gains HP and money.
 *
 *         Booster object.
 *
 */
public class Bolt extends GameObject {
	public static final int objectSize = 50;
	private String objectName = "Type: Bolt, ID: E";
	private Point p;
	private double xProp = ImgResources.BOLT.getScaleX();
	private double yProp = ImgResources.BOLT.getScaleY();

	public Bolt(Point p) {
		this.p = p;
	}

	@Override
	public void collide(Car c) {
		int hp = c.getHP();
		if (hp < 50) { // less that 50 top it up
			int add = 100 - hp;
			hp += add;
		}
		c.setHP(hp);
		int money = c.getMoney();
		money += 1;
		c.setMoney(money);
		c.boost(30);
		int fuel = c.getFuel();
		if(fuel < 100){
			fuel += 50;
		}
		c.setFuel(fuel);
	}

	@Override
	public String getID() {
		return "E";
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
		return ImgResources.BOLT;
	}

	@Override
	public boolean equals(Object o) throws ClassCastException {
		if (o instanceof Bolt) {
			Bolt b = (Bolt) o;
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
