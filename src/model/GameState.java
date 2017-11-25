package model;

import java.awt.*;
import java.util.List;
import java.util.Set;

import general.*;
import loadAndSave.Saver;
import map.IObjectMap;
import map.ITerrainMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import model.gameObjects.Car;
import model.gameObjects.GameObject;
import model.gameObjects.NullGameObject;
import npc.NpcObject;
import resources.ImgResources;

/***
 * Holds a representation of a stage and the progress within it.
 *
 * @author fraserhuon
 *
 */
public class GameState implements IGameState{
	private Car playerCar; //player controlled car
	private List<NpcObject> AIObjects; //AI objects
	private ITerrainMap terrainMap; //map of terrain
	private IObjectMap objectMap; //map of objects
	private Stage stage = Stage.DESERT;
	private Model m;
	private Set<CarType> unlockedCars = new HashSet<CarType>();
	private Set<Stage> unlockedStages = new HashSet<Stage>();
	private List<NpcObject> NpcObjects = new ArrayList<NpcObject>();

	public GameState(Stage s, ITerrainMap terrainMap,
					 IObjectMap object, Car playerCar, Set<CarType> unlockedCars, Set<Stage> unlockedStages, List<NpcObject> NpcObjects){
		this.playerCar = playerCar;
		this.AIObjects=AIObjects;
		this.terrainMap=terrainMap;
		this.objectMap=object;
		this.stage=s;
		this.m = m;
		this.unlockedCars=unlockedCars;
		this.unlockedStages=unlockedStages;
		this.NpcObjects = NpcObjects;
	}



	/**
	 * Saves a game state to the given file
     *
     * Terrain and object maps handle their own save
     *
     * Meta Data file gets written:
     *      Car
     *      Set of Unlocked Cars
     *      Set of Unlocked Stages
	 * @param s
	 */
	public void save(String s, Set<CarType> unlockedCars, Set<Stage> unlockedStages) {//maybe this should be file
		try{
			Saver.saveTerrainMap("src/files/"+s+"TerrainMap.txt",terrainMap);
			Saver.saveObjectMap("src/files/"+s+"ObjectMap.txt",objectMap);
			String fileName =("src/files/" +s+"MetaData.txt");
			Saver.saveGameState(fileName, unlockedStages,  unlockedCars,this);
		}catch(IOException e) {}
	}




	/***
	 * Return the first object in the array that the object collides with
	 *
	 * @return
	 */
	public GameObject detectCollisions(Rectangle carBox) {
		int width = objectMap.getWidth();
		int height = objectMap.getHeight();
		for(int y =0; y < height; y++) {
			for(int x=0; x<width; x++) {
				GameObject o = objectMap.getObjectAt(x, y);
				if(!(o instanceof NullGameObject)) {
					Rectangle oBox = o.getBoundingBox();
						if (oBox.intersects(carBox)) {
							return o;
						}
				}
			}
		}
		return new NullGameObject(new Point(0,0));
	}


	/***
	 * Ping all cars in the game
	 */
	public void ping() {
		Point currentPoint = playerCar.getPosition().toPoint();
		double d = terrainMap.getTerrainAt(currentPoint.x,currentPoint.y).getSpeedModifer();
		Vector2D expectedPosition = playerCar.mockPing(d);
		Rectangle r = new Rectangle ((int)Math.round(expectedPosition.getX()*ImgResources.imgDimension-ImgResources.imgDimension/2),
				(int)Math.round(expectedPosition.getY()*ImgResources.imgDimension-ImgResources.imgDimension/2),
				ImgResources.imgDimension,ImgResources.imgDimension);
		GameObject objectOver= detectCollisions(r);
		if(objectOver.isCollidable()) {
			playerCar.ping(d);
		}
		objectOver.collide(playerCar);
		if(objectOver.isDestroyable()) {
			objectMap.removeObjectAt(objectOver.getOrigin().x,objectOver.getOrigin().y);
		}

		for(NpcObject ai: NpcObjects) {
			ai.ping(playerCar);
			if(ai.getBoundingBox().intersects(playerCar.getBoundingBox())){
				ai.collide(playerCar);
			}
		}
	}

	/***
	 * Return the car object that the player controls
	 * @return
	 */
	public Car getCar() {
		return playerCar;
	}

	/***
	 * Return the enum representing the stage the game is running
	 * @return
	 */
	public Stage getStage() {
		return stage;
	}

	/***
	 * Return the Object representing the terrain map
	 * @return
	 */
	public ITerrainMap getTerrainMap() {
		return terrainMap;
	}
	/***
	 * Return the Object representing the object map
	 * @return
	 */
	public IObjectMap getObjectMap() {
		return objectMap;
	}

	public Set<CarType> getUnlockedCars(){
        return unlockedCars;
    }
    public Set<Stage> getUnlockedStages(){
        return unlockedStages;
    }

	@Override
	public List<NpcObject> getNpcObjects() {
		return NpcObjects;
	}


}
