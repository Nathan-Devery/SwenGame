package model;

import java.awt.*;
import java.util.List;
import java.util.Set;

import general.CarType;
import general.Stage;
import map.IObjectMap;
import map.ITerrainMap;
import model.gameObjects.Car;
import model.gameObjects.GameObject;
import npc.NpcObject;

/***
 * Interface for Game State
 *
 * @author fraserhuon
 *
 */
public interface IGameState {
	/***
	 * Determines if the player has collided with an object on the map.
	 *
	 * @param r
	 * @return object that player collided with
	 */
	public GameObject detectCollisions(Rectangle r);



	/***
	 * Updates all the game objects position based on the player car velocity.
	 */
	public void ping();



	/***
	 * Determines the terrain map the player is on.
	 *
	 * @return terrain map
	 */
	public ITerrainMap getTerrainMap();



	/***
	 * Determine the object map and distribution of objects on the display screen.
	 *
	 * @return object map
	 */
	public IObjectMap getObjectMap();



	/***
	 * Getter method for the stage the player is on.
	 *
	 * @return current stage
	 */
	public Stage getStage();



	/***
	 * Getter method for the car the player has.
	 *
	 * @return current car
	 */
	public Car getCar();



	/***
	 * Getter method for getting NPC Objects, these objects follow the car around.
	 *
	 * @return list of AI cars
	 */
	public List<NpcObject> getNpcObjects();


	/***
	 * @return Set of CarTypes which are unlocked
	 */
	public Set<CarType> getUnlockedCars();

	/***
	 *
	 * @return Set of stages which are unlocked
	 */
	public Set<Stage> getUnlockedStages();

	public void save(String s, Set<CarType> unlockedCars, Set<Stage> unlockedStages);

}
