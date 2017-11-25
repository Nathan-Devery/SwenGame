package tests.gui;

import map.ITerrainMap;
import model.terrain.*;

/***
 * Mock Terrain Map for testing
 *
 */
public class MockTerrainMap implements ITerrainMap {
	Terrain[][] map;
	
	public MockTerrainMap(Terrain[][] map){
		this.map = map;
	}
	
	@Override
	public Terrain getTerrainAt(int x, int y) {
		return  map[y][x];
	}

	@Override
	public Terrain[][] getTerrainArray() {
		return map;
	}

	@Override
	public int getWidth() {
		return 2;
	}

	@Override
	public int getHeight() {
		return 2;
	}

}
