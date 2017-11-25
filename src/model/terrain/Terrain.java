package model.terrain;

import general.Stage;
import resources.ImgResources;

/***
 * Terrain class, this is extended by off road, scenery and unreachable terrain
 * types.
 *
 * @author claytohann
 *
 */
public abstract class Terrain {
	/***
	 * On each terrain type the speed changes.
	 * On road is the fastest, followed by off road then scenery.
	 *
	 * @return speed on a terrain type
	 */
	public abstract double getSpeedModifer();



	/***
	 * Getter method for ID.
	 *
	 * @return terrain ID
	 */
	public abstract String getID();

	/***
	 * Getter method for image ENUM.
	 *
	 * @param s stage of the game
	 * @return image for tile
	 */
	public abstract ImgResources getImageEnum(Stage s);
}
