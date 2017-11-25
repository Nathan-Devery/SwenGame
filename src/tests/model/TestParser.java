package tests.model;

import static org.junit.Assert.*;

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

import org.junit.Test;

import general.CarType;
import general.Stage;
import general.Vector2D;
import loadAndSave.Parser;
import map.*;
import model.IGameState;
import model.gameObjects.*;
import model.terrain.*;
import npc.NpcObject;

/***
 * 
 * @author fraserhuon
 *
 */
public class TestParser {
	private TerrainMap terrainMap;
	private ObjectMap objectMap;

	@Test
	public void testTerrainLoad1() {

		File file = new File("src/files/testTerrainMap.txt");

		terrainMap = Parser.parseTerrainMap(file);
		assertEquals(new Unreachable(),terrainMap.getTerrainAt(0, 0));
		assertEquals(new Scenery(),terrainMap.getTerrainAt(1, 0));
		assertEquals(new Scenery(),terrainMap.getTerrainAt(2, 0));
		assertEquals(new Road(),terrainMap.getTerrainAt(0, 1));
		assertEquals(new Scenery(),terrainMap.getTerrainAt(1, 1));
		assertEquals(new Unreachable(),terrainMap.getTerrainAt(2, 1));
	}

	@Test
	public void testObjectLoad1() {
		File file = new File("src/files/testObjectMap.txt");
		objectMap = Parser.parseObjectMap(file);
		assertEquals(new CheckPoint(new Point(0,0)),objectMap.getObjectAt(0, 0));
		assertEquals(new Bolt(new Point(1,0)),objectMap.getObjectAt(1, 0));
		assertEquals(new Fence(new Point(2,0)),objectMap.getObjectAt(2, 0));
		assertEquals(new Rock(new Point(0,1)),objectMap.getObjectAt(0, 1));
		assertEquals(new Tree(new Point(1,1)),objectMap.getObjectAt(1, 1));
		assertEquals(new NullGameObject(new Point(2,1)),objectMap.getObjectAt(2, 1));


	}

	@Test
	public void testParseTerrainFromString() {
		assertEquals(new OffRoad(), Parser.parseTerrain("O"));
		assertEquals(new Road(), Parser.parseTerrain("R"));
		assertEquals(new Scenery(), Parser.parseTerrain("S"));
		assertEquals(new Unreachable(), Parser.parseTerrain("U"));
		assertEquals(new Scenery(), Parser.parseTerrain("E"));
	}

	@Test
	public void testParseObjectFromString() {
		Point p = new Point(3,3);
		assertEquals(new Barrier(p), Parser.parseGameObject("B", 3, 3));
		assertEquals(new Bolt(p), Parser.parseGameObject("E", 3, 3));
		assertEquals(new CheckPoint(p), Parser.parseGameObject("C", 3, 3));
		assertEquals(new Coin(p), Parser.parseGameObject("N", 3, 3));
		assertEquals(new Fence(p), Parser.parseGameObject("F", 3, 3));
		assertEquals(new NullGameObject(p), Parser.parseGameObject("a", 3, 3));
		assertEquals(new Rock(p), Parser.parseGameObject("R", 3, 3));
		assertEquals(new Tree(p), Parser.parseGameObject("T", 3, 3));
	}

	@Test
	public void testParseStage() {

		String save = "desert forest snow null";

		Scanner scanner = new Scanner(save);
		scanner.useDelimiter("\\s+|(?=[{}(),;])|(?<=[{}(),;])");
		assertEquals(Stage.DESERT,Parser.parseStage(scanner));
		assertEquals(Stage.FOREST,Parser.parseStage(scanner));
		assertEquals(Stage.SNOW,Parser.parseStage(scanner));
		assertEquals(null,Parser.parseStage(scanner));
		scanner.close();
	}

	@Test
	public void testParseCarType() {
		String save = "car1 car2 car3 null";
		Scanner scanner = new Scanner(save);
		scanner.useDelimiter("\\s+|(?=[{}(),;])|(?<=[{}(),;])");
		assertEquals(CarType.CAR1,Parser.parseCarType(scanner));
		assertEquals(CarType.CAR2,Parser.parseCarType(scanner));
		assertEquals(CarType.CAR3,Parser.parseCarType(scanner));
		assertEquals(null,Parser.parseStage(scanner));
		scanner.close();
	}
	@Test
	public void testParseVector() {
		String save = "0,0";
		Scanner scanner = new Scanner(save);
		scanner.useDelimiter("\\s+|(?=[{}(),;])|(?<=[{}(),;])");
		assertEquals(new Vector2D(0,0), Parser.parseNextVector(scanner));

	}

	@Test
	public void testParseUnlockedStages() {
		String save = "{\n"
				+ "forest\n"
				+ "desert\n"
				+ "}";
		Scanner scanner = new Scanner(save);
		scanner.useDelimiter("\\s+|(?=[{}(),;])|(?<=[{}(),;])");
		Set<Stage> stages = Parser.parseUnlockedStages(scanner);
		assertEquals(true ,stages.contains(Stage.FOREST));
		assertEquals(true, stages.contains(Stage.DESERT));
		assertEquals(false,stages.contains(Stage.SNOW));
		assertEquals(2,stages.size());
		scanner.close();
	}
	@Test
	public void testParseUnlockedCars() {
		String save = "{\n"
				+ "car2\n"
				+ "car3\n"
				+ "}";
		Scanner scanner = new Scanner(save);
		scanner.useDelimiter("\\s+|(?=[{}(),;])|(?<=[{}(),;])");
		Set<CarType> stages = Parser.parseUnlockedCars(scanner);
		assertEquals(true ,stages.contains(CarType.CAR2));
		assertEquals(true, stages.contains(CarType.CAR3));
		assertEquals(false,stages.contains(CarType.CAR1));
		assertEquals(2,stages.size());
		scanner.close();
	}

	@Test
	public void testParseNPC1() {
		String save = "{\n"
				+ "POSITION 2,2 \n"
				+ "DIRECTION 12\n"
				+ "ATTACK 44\n"
				+ "VELOCITY 3\n"
				+ "}";
		Scanner scanner = new Scanner(save);
		scanner.useDelimiter("\\s+|(?=[{}(),;])|(?<=[{}(),;])");
		NpcObject npc = Parser.parseNPCObject(scanner);
		assertEquals(new Vector2D(2,2),npc.getPosition());
		assertEquals(12,npc.getDirection());
		assertEquals(44, npc.getAttack());
		assertEquals(3,npc.getVelocity(),0.001);

	}

	@Test
	public void testParseCar1() {
		String save = "{\n" +
				"ID car1\n" +
				"POSITION 10,12\n" +
				"VELOCITY 1,1\n" +
				"DIRECTION 90\n" +
				"FUEL 100\n" +
				"HP 99\n" +
				"MONEY 1\n" +
				"}";

		Scanner scanner = new Scanner(save);
		scanner.useDelimiter("\\s+|(?=[{}(),;])|(?<=[{}(),;])");
		Car car = Parser.parseCar(scanner);
		assertEquals(new Vector2D(10,12),car.getPosition());
		assertEquals(new Vector2D(1,1),car.getVelocity());
		assertEquals(90, car.getDirection());
		assertEquals(100,car.getFuel(),0.001);
		assertEquals(99,car.getHP(),0.001);
		assertEquals(1,car.getMoney());

	}

	@Test
	public void testParseGameState() {
		IGameState gs = Parser.parseGameState("desert");
		assertEquals(Stage.DESERT,gs.getStage());
		assertEquals(CarType.CAR1,gs.getCar().getCarType());
		assertFalse(gs.getTerrainMap()==null);
		assertFalse(gs.getObjectMap()==null);
	}
}
