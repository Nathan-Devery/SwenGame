package map;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import loadAndSave.Parser;
import model.terrain.Terrain;

/***
 * Terrain Map class representing a 2-dimensional array of terrain objects
 *
 * @author fraserhuon
 *
 */
public class TerrainMap implements ITerrainMap{
// 67 height 41 wide

	private final Terrain[][] terrainMap; //format is [height][width]

	/***
	 * Constructor for TerrainMap, taking a 2 dimensional array of Terrain objects
	 * @param map
	 */
	public TerrainMap(Terrain[][] map) {
			this.terrainMap = map;

	}





	@Override
	/***
	 * Return the Terrain object at the coordinate given
	 */
	public Terrain getTerrainAt(int x, int y) {
		return terrainMap[y][x];
	}

	/***
	 *Return the 2dimension array
	 */
	@Override
	public Terrain[][] getTerrainArray(){
		return terrainMap;
	}

	/***
	 * Return the width of the Terrain Map
	 */
	public int getWidth() {
		return terrainMap[0].length;
	}

	/***
	 * Return the height of the TerrainMap
	 */
	public int getHeight() {
		return terrainMap.length;
	}


}
