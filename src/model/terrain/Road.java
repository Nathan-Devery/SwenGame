package model.terrain;

import general.Stage;
import resources.ImgResources;

/***
 * Road terrain class, this return the grey road on the map.
 *
 * @author fraserhuon
 *
 */
public class Road extends Terrain{

    public Road(){

    }

	@Override
	public double getSpeedModifer() {
		return 1;
	}


	@Override
	public ImgResources getImageEnum(Stage stage) {
		return ImgResources.MAIN;
	}

	@Override
	public boolean equals(Object o) throws ClassCastException {
		if(o instanceof Road) {
			return true;
		}
		return false;
	}

	@Override
	public String getID() {
		return "R";
	}

}