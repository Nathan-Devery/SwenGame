package model;

import java.util.Observer;
import java.util.Set;
import general.CarType;
import general.Direction;
import general.Stage;
import general.State;

/***
 * Interface for Model
 *
 * @author deverynath
 *
 */
public interface IModel{

	public void restart();

	/**
	 * Return the state the game is from: MENU,RUNNING,PAUSE
	 * @return
	 */
	public State getState();

	/**
	 * Set the state of the game to s
	 * @param s
	 */
	public void setState(State s);

	/**
	 * Return the current Gamestate class
	 * @return
	 */
	public IGameState getGameState();

	/**
	 * Ping the game
	 */
	public void ping();

	/**
	 * Move the player car in the given direction
	*/
	public void movePlayerCar(Direction d);


	/**
	 * Load a predifed stage
	 * @param s
	 */
	public void load(Stage s);

	/**
	 * This is to ensure IModel implementations that extend Observable can have observers
	 * @param o
	 */
	public void addObserver(Observer o);

	/**
	 *Set the games money to m
	 * @param m
	 */
	public void setMoney(int m);

	/**
	 * Return the amount of money the player has
	 * @return
	 */
	public int getMoney();

	/**
	 * Load a save game
	 * @param s
	 */
	public void loadSave(String s);


	/**
	 * Return the set of stages that are unlocked
	 * @return
	 */
	public Set<Stage> getUnlockedStages();

	/**
	 * Return the set of cars that are unlocked
	 * @return
	 */
	public Set<CarType> getUnlockedCars();

	/**
	 * Unlock the CarType given by ct
	 * @param ct
	 */
	public void unlockCar(CarType ct);

	/**
	 * Save the game state to files given by the String s
	 * @param s
	 */
	public void save(String s);

	/**
	 * Set the current car to the CarType given by ct,
	 * Current implementations should change the cars strategy pattern
	 * @param ct
	 */
	public void setCurrentCar(CarType ct);

	/**
	 * Returns the current car type
	 * @return
	 */
	public CarType getCarType();

	/**
	 * Unlock the stage given by s
	 * @param s
	 */
	public void unlockStage(Stage s);

	/***
	 * Handle finished a stage.
	 *
	 * Different actions are taken for each stage
	 * If the last stage is completed, the State is set to VICTORY
	 * Else the next stage is unlocked
	 * @param s
	 */
	public void finishStage(Stage s);

}
