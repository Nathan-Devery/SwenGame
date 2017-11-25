package model.terrain;

import general.Stage;
import resources.ImgResources;

/***
 * Unreachable tiles, these are tiles the player cannot reach.
 * To ensure the player stays on the map.
 *
 * @author andrijadjorovic
 *
 */
public class Unreachable extends Terrain {

	@Override
	public double getSpeedModifer() {
		return 1;
	}

	@Override
	public ImgResources getImageEnum(Stage s) {
		switch (s) {
			case DESERT:
				return ImgResources.HILLS;
			case FOREST:
				return ImgResources.FOREST;
			default:
				return ImgResources.FOREST;
		}
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Unreachable) {
			return true;
		}
		return false;
	}

	@Override
	public String getID() {
		return "U";
	}
}
