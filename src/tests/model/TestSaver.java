package tests.model;

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import general.*;
import loadAndSave.Saver;
import map.*;
import model.gameObjects.*;
import model.terrain.*;
import npc.NpcObject;

/***
 * 
 * @author fraserhuon
 *
 */
public class TestSaver {
	TerrainMap terrainMap;
	ObjectMap objectMap;
	String saveFileName1 = "src/files/testSaver.txt";
	@Test
	public void testTerrainSave1() { //assumes testLoad1 saves
		try {

			Terrain[][] terrainMap = new Terrain[2][3];
			terrainMap[0][0]= new Unreachable();
			terrainMap[0][1]= new Scenery();
			terrainMap[0][2]= new Scenery();
			terrainMap[1][0]= new Road();
			terrainMap[1][1]= new Scenery();
			terrainMap[1][2]= new OffRoad();
			ITerrainMap terrainMapObject = new TerrainMap(terrainMap);

			Saver.saveTerrainMap(saveFileName1,terrainMapObject);

			Scanner scanner = new Scanner(new File(saveFileName1));
			scanner.useDelimiter("");
			StringBuilder builder = new StringBuilder();
			while(scanner.hasNext()) {
				builder.append(scanner.next());
			}

			String expected = "3 2\n" +
					"U 0 0\n" +
					"R 0 O";
			System.out.println(expected);
			System.out.println(builder.toString());
			assertEquals(expected,builder.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Test
	public void testObjectSave1() { //assumes testLoad1 saves
		try {
			GameObject[][] terrainMap = new GameObject[2][3];
			terrainMap[0][0]= new Barrier(new Point(0,0));
			terrainMap[0][1]= new NullGameObject(new Point(1,0));
			terrainMap[0][2]= new Coin(new Point(2,0));
			terrainMap[1][0]= new Bolt(new Point(0,1));
			terrainMap[1][1]= new Fence(new Point(1,1));
			terrainMap[1][2]= new Rock(new Point(2,1));
			IObjectMap objectMap = new ObjectMap(terrainMap);

			Saver.saveObjectMap(saveFileName1,objectMap);

			Scanner scanner = new Scanner(new File(saveFileName1));
			scanner.useDelimiter("");
			StringBuilder builder = new StringBuilder();
			while(scanner.hasNext()) {
				builder.append(scanner.next());
			}

			String expected = "3 2\n" +
					"B 0 N\n" +
					"E F R";
			System.out.println(expected);
			System.out.println(builder.toString());
			assertEquals(expected,builder.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testNPCSave() {
		NpcObject npc = new NpcObject(new Vector2D(1,1),2,5,7);
		StringBuilder builder = new StringBuilder();
		Saver.saveNPC(npc, builder);
		String expected = "NPC{\n"+
				"POSITION 1,1\n"+
				"VELOCITY 2\n" +
				"DIRECTION 5\n" +
				"ATTACK 7\n" +
				"}\n";

		assertEquals(expected,builder.toString());

	}

	@Test
	public void testCarSave() {
		Car car = new Car(CarType.CAR2, new Vector2D(1,1), new Vector2D(2,2),2,5,7,9);

		StringBuilder builder = new StringBuilder();
		Saver.saveCar(car, builder);
		String expected = "CAR{\n"+
				"ID car2\n" +
				"POSITION 1,1\n"+
				"VELOCITY 2,2\n" +
				"DIRECTION 2\n" +
				"FUEL 4\n" +
				"HP 6\n" +
				"MONEY 9\n"+
				"}\n";

		assertEquals(expected,builder.toString());

	}
}
