package tests.model;

import map.TerrainMap;
import model.terrain.Scenery;
import model.terrain.Road;
import model.terrain.Terrain;
import model.terrain.Unreachable;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/***
 *
 * @author fraserhuon
 *
 */
public class TestTerrainMap {
	TerrainMap terrainMap;
	String fileName1 = "src/files/testTerrainMap.txt";
	String saveFileName1 = "src/files/saveTerrainMap.txt";
	@Before
	public void initialise() {
		Terrain[][] array = new Terrain[3][3];
		array[0][0]= null;
		array[0][1]= null;
		array[0][2]= null;
		array[1][0]= null;
		array[1][1]= null;
		array[1][2]= null;
		array[2][0]= null;
		array[2][1]= null;
		array[2][2]= new Road();
		terrainMap = new TerrainMap(array);

	}

	@Test
	public void test1() {
		assertEquals(3, terrainMap.getHeight());
		assertEquals(3, terrainMap.getWidth());

		assertEquals(null,terrainMap.getTerrainAt(0, 0));
		assertEquals(null,terrainMap.getTerrainAt(0, 1));
		assertEquals(null,terrainMap.getTerrainAt(0, 2));
		assertEquals(null,terrainMap.getTerrainAt(1, 0));
		assertEquals(null,terrainMap.getTerrainAt(1, 1));
		assertEquals(null,terrainMap.getTerrainAt(1, 2));
		assertEquals(null,terrainMap.getTerrainAt(2, 0));
		assertEquals(null,terrainMap.getTerrainAt(2, 1));
		assert(terrainMap.getTerrainAt(2,2) instanceof Road);
	}



}
