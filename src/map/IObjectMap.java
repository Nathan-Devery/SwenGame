package map;

import model.gameObjects.GameObject;

/***
 * Object Map interface
 *
 * @author fraserhuon
 *
 */
public interface IObjectMap {
	/***
	 * Getter method that return at object at a specific location.
	 *
	 * @param x
	 * @param y
	 * @return point location
	 */
	public GameObject getObjectAt(int x, int y);

	/***
	 * Getter method for object array
	 *
	 * @return object array
	 */
	public GameObject[][] getObjectArray();

	/***
	 * Getter method for width of the array
	 *
	 * @return width of array
	 */
	public int getWidth();

	/***
	 * Getter method for height of the array
	 *
	 * @return height of array
	 */
	public int getHeight();

	/***
	 * Remove the object at the given point in the array
	 * @param x
	 * @param y
	 */
	public void removeObjectAt(int x, int y);

}
