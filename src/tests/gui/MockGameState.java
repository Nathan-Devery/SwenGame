package tests.gui;

import java.awt.Rectangle;
import java.util.List;
import java.util.Set;

import general.CarType;
import general.Stage;
import map.IObjectMap;
import map.ITerrainMap;
import model.IGameState;
import model.Model;
import model.gameObjects.Car;
import model.gameObjects.GameObject;
import npc.NpcObject;

public class MockGameState implements IGameState{

	private Car playerCar = new Car();
	private List<NpcObject> AIObjects; //AI objects
	private ITerrainMap terrainMap;
	private IObjectMap objectMap;
	private Stage stage = Stage.DESERT;
	
	@Override
	public GameObject detectCollisions(Rectangle r) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ping() {
		// TODO Auto-generated method stub
	}

	@Override
	public ITerrainMap getTerrainMap() {
		return terrainMap;
	}

	@Override
	public IObjectMap getObjectMap() {
		return objectMap;
	}
	
	public void setObjectMap(IObjectMap map){
		this.objectMap = map;
	}
	
	public void setTerrainMap(ITerrainMap map){
		this.terrainMap = map;
	}

	@Override
	public Stage getStage() {
		return stage;
	}

	@Override
	public Car getCar() {
		return playerCar;
	}

	@Override
	public List<NpcObject> getNpcObjects() {
		return AIObjects;
	}

	/***
	 * Ignore, implementation makes redundant
	 */
	@Override
	public Set<CarType> getUnlockedCars() {
		return null;
	}

	/***
	 * Ignore, implementation makes redundant
	 */
	@Override
	public Set<Stage> getUnlockedStages() {
		return null;
	}

	@Override
	public void save(String s, Set<CarType> unlockedCars, Set<Stage> unlockedStages) {
		// TODO Auto-generated method stub
	}
	
	public void setStage(Stage stage){
		this.stage = stage;
	}
}
