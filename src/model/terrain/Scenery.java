package model.terrain;

import general.Stage;
import resources.ImgResources;

/***
 * Scenery texture class - contains forest tiles, desert tiles and snow tiles.
 * Slow the speed of the player when on scenery tiles.
 *
 * @author claytohann
 *
 */
public class Scenery extends Terrain {

	@Override
	public double getSpeedModifer() {
		return 0.4;
	}

	@Override
	public ImgResources getImageEnum(Stage s) {
		switch (s) {
			case DESERT:
				return ImgResources.DESERT;
			case FOREST:
				return ImgResources.GRASS;
			case SNOW:
				return ImgResources.SNOW;
			default:
				return ImgResources.GRASS;
		}
	}

	@Override
	public boolean equals(Object o) throws ClassCastException {
		if(o instanceof Scenery) {
			return true;
		}
		return false;
	}

	@Override
	public String getID() {
		return "0";
	}
}
