package model.terrain;

import general.Stage;
import resources.ImgResources;

/***
 * Off Road Textures class.
 *
 * @author claytohann
 *
 */
public class OffRoad extends Terrain {

	@Override
	public double getSpeedModifer() {
		return 0.8;
	}

	@Override
	public String getID() {
		return "O";
	}

	@Override
	public ImgResources getImageEnum(Stage stage) {
		switch (stage) {
			default:
				return ImgResources.DIRTROAD;
		}
	}

	@Override
	public boolean equals(Object o){
		return o instanceof  OffRoad;
	}
}