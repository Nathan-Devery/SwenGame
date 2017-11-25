package model.gameObjects;

import java.awt.Point;

import java.awt.Rectangle;

import general.Stage;
import resources.ImgResources;

/**
 * Created by Huon Fraser and claytohann
 */
public abstract class GameObject {
	/***
	 * Returns various changes in the players health,
	 * depending on the object type that the car collides with.
	 *
	 * E.g. coin collision increase money, tree collision decreases HP.
	 *
	 * @param c is the car that is colliding with the object.
	 */
	public abstract void collide(Car c);



	/***
	 * Returns the map key list. See mapKeyList in files for values.
	 *
	 * @return ID key value
	 */
	public abstract String getID();



	/***
	 * Returns Point location of the object.
	 *
	 * @return point of origin of specified object.
	 */
	public abstract Point getOrigin();



	/***
	 * Returns 50 x 50 pixel bounding box of each object.
	 * All objects are 50 x 50 in size.
	 *
	 * @return Rectangle bounding box of specified object.
	 */
	public abstract Rectangle getBoundingBox();



	/***
	 * Returns the ENUM which represents the object.
	 * See ImgResources for various object types.
	 *
	 * @param s Stage the game is on - Snow, Desert, Forest, Sea
	 * @return Image ENUM that represents the object
	 */
	public abstract ImgResources getImageEnum(Stage s);



	/***
	 * Returns whether the car can go through the object
	 *
	 * @return boolean isCollidable
	 */
	public abstract boolean isCollidable();



	/***
	 * Returns whether after the collision if the object is destroyed.
	 *
	 * @return boolean isDestroyable
	 */
	public abstract boolean isDestroyable();

	

	/***
	 * To string method for console
	 */
	public abstract String toString();


}