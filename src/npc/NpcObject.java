package npc;

import general.Stage;
import general.Vector2D;
import model.gameObjects.Car;
import model.gameObjects.GameObject;
import resources.ImgResources;

import java.awt.*;
/***
 * Npc Object
 *
 * @author fraserhuon
 *
 */
public class NpcObject extends GameObject{
	private int direction=0;
	private Vector2D position = new Vector2D(0,0);
	private double velocity =1;
	private int attack = 1;


	/***
	 * Custom constuctor for NPC object
	 * @param position
	 * @param velocity
	 * @param direction
	 * @param attack
	 */
	public NpcObject(Vector2D position, double velocity, int direction, int attack) {
		this.position=position;
		this.velocity=velocity/50;
		this.direction=direction;
		this.attack=attack;
	//	System.out.println(velocity);
	}

	/***
	 * Default constructo for NPC, initialising at 0,0
	 */
	public NpcObject(){
		position = new Vector2D(0,0);
		velocity = 0.5;
		direction = 0;
	}


	/***
	 * Returns various changes in the players health,
	 * depending on the object type that the car collides with.
	 *
	 * E.g. coin collision increase money, tree collision decreases HP.
	 *

	 * @param c
	is the car that is colliding with the object.
	 */
	@Override
	public void collide(Car c) {
		c.setHP(c.getHP()-attack);
	}

	/***
	 * Returns the map key list. See mapKeyList in files for values.
	 *
	 * @return ID key value
	 */
	@Override
	public String getID() {
		return null;
	}

	/***
	 * Returns the ENUM which represents the object.
	 * See ImgResources for various object types.
	 *
	 * @param s Stage the game is on - Snow, Desert, Forest, Sea
	 * @return Image ENUM that represents the object
	 */
	@Override
	public ImgResources getImageEnum(Stage s) {
		return null;
	}

	/***
	 * Returns whether the car can go through the object
	 *
	 * @return boolean isCollidable
	 */
	@Override
	public boolean isCollidable() {
		return false;
	}

	/***
	 * Returns whether after the collision if the object is destroyed.
	 *
	 * @return boolean isDestroyable
	 */
	@Override
	public boolean isDestroyable() {
		return false;
	}

	/***
	 * To string method for console
	 */
	@Override
	public String toString() {
		return null;
	}


	/***
	 * Return the ImgEnum associated with this object
	 * @return
	 */
	public ImgResources getImageEnum() {
		return ImgResources.FIGHTER;
	}

	/***
	 * @return The position on the map this object occupies
	 */
	public Vector2D getPosition() {
		return position;
	}

	/***
	 * The direction this object is facing where
	 * 0=NORTH,90=EAST
	 * @return
	 */
	public int getDirection() {
		return this.direction;
	}

	/***
	 * Return the velocity of this object
	 * The number is returned as number of pixels per second
	 * @return
	 */
	public double getVelocity(){
		return velocity*50;
	}

	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle(getOrigin().x*50-25,getOrigin().y*50-25,50,50);
	}

	@Override
	public Point getOrigin() {
		return new Point((int)Math.round(position.getX()),(int)Math.round(position.getY()));
	}

	/***
	 * @return The amount of hp that a car loses when it collides with this npc object
	 */
	public int getAttack() {
		return this.attack;
	}

	/***
	 *Update the cars position
	 *The npc should move towards the car with a velocity of velocity
	 * @param c, The car to interact with
	 */
	public void ping(Car c) {
		Vector2D carPosition = c.getPosition();
		Vector2D difference = carPosition.minus(this.position);
		Vector2D scaled = difference.getUnitVector().scalarMultiply(velocity);
		position = position.add(scaled);
		direction = scaled.getDirection();
	}

}
