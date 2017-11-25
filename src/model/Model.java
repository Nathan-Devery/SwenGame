package model;

import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

import general.*;
import loadAndSave.Parser;
import model.gameObjects.Car;

/***
 * Model class
 *
 * @author fraserhuon
 *
 */
public class Model extends Observable implements IModel{
	// Car playerCar
	//List<Car> AI cars.
	private State state = null; //state is the enum determining what stage the game is in
	private IGameState gameState = null; //gameState is the object representing the state of the game
	private int money;
	private Set<CarType> unlockedCars;
	private Set<Stage> unlockedStages;
	private CarType currentCarType = CarType.CAR1;

	public Model(){
		restart();
	}
	public Model(GameState o) {
		this.gameState=o;
	}

	/***
	 * Set the game back to its default state, at the menu screen
	 */
	public void restart(){
		state = State.MENU;  //Game starts at menu
		gameState = null;
		unlockedCars = new HashSet<CarType>();
		unlockedCars.add(CarType.CAR1);
		unlockedStages = new HashSet<Stage>();
		unlockedStages.add(Stage.DESERT);
		currentCarType = CarType.CAR1;
		money = 0;
	}

	/***
	 * Return the gameState object the game is currently in
	 */
	@Override
	public IGameState getGameState(){
		return this.gameState;
	}

	/**
	 * Ping the model
	 * Update money and other fields that may have changed
	 * Move the player cars and notify the object
	 */
	public void ping() {
		if(gameState!=null && state==State.RUNNING) {
				gameState.ping();
				Car c = gameState.getCar();
				if(c.hasFinished()) {
					finishStage(gameState.getStage());
				}
				this.money = gameState.getCar().getMoney();
				if(c.getFuel()<=0 || c.getHP()<=0) {
					state = State.DEFEAT;
				}
		}
		setChanged();
		notifyObservers();

	}

	/***
	 * Handle finished a stage.
	 *
	 * Different actions are taken for each stage
	 * If the last stage is completed, the State is set to VICTORY
	 * Else the next stage is unlocked
	 * @param s
	 */
	@Override
	public void finishStage(Stage s) {
		switch(s) {
			 case DESERT:
				 unlockedStages.add(Stage.FOREST);
				 break;
			 case FOREST:
				 unlockedStages.add(Stage.SNOW);
				 break;
			 case SNOW:
				 state = State.VICTORY;
				 break;
		}
	}

	/**
	 * Move the player car in the given direction
	 *
	 * How the car handles movement is determined by the car strategy
	 * and collisions are handled by the GameState
	 */
	@Override
	public void movePlayerCar(Direction d) {
		if(gameState!=null) {
			gameState.getCar().move(d);
		}
		else{
			System.out.println("Contract broken, attempting to move car on null gamestate");
		}
	}

	/***
	 *Return the state the game is currently in, where state is an enum
	 */
	@Override
	public State getState() {
		return state;
	}


	/***
	 * Load one of the standard stages
	 *
	 * After the stage is loaded the cars money and carType
	 * are updated to match that of the model
	 */
	@Override
	public void load(Stage s) {
		if(unlockedStages.contains(s)) {
			state = State.RUNNING;
			gameState = Parser.parseGameState(s.getFileName());
			gameState.getCar().setCarType(currentCarType); //after loading a stage set the car back to the one held in the model
			gameState.getCar().setMoney(money); //after loading a stage, set the cars money back to what it was
		}
	}

	/***
	 * Load a saved game given by the prefix s
	 *
	 * After loading the saved game:
	 * 	The models money is set to that of the save
	 * The unlocked Stages and Cars are set to that of the save
	 * The current car type is set to that of the save
	 */
	@Override
	public void loadSave(String s) {

		gameState = Parser.parseGameState(s);
		if(gameState!=null) {
			state = State.RUNNING;
			this.money = gameState.getCar().getMoney(); //after loading save set the models fields to that of that loaded
			this.unlockedCars = gameState.getUnlockedCars();
			this.unlockedStages = gameState.getUnlockedStages();
			this.currentCarType= gameState.getCar().getCarType();
		}
		else{
			System.out.println("Failed to load game state");
		}

	}



	/***
	 * Set the State to the given parameter
	 */
	@Override
	public void setState(State state) {
		this.state = state;
	}


	/***
	 * Set the amount of money to the given amount
	 * If the gameState isn't null update the cars money
	 */
	@Override
	public void setMoney(int n) {
		this.money=n;
		if(gameState!=null) {
				this.gameState.getCar().setMoney(n);
		}
	}


	/***
	 * Return the current amount of money
	 */
	@Override
	public int getMoney() {
		if(gameState!=null) {
			this.money=gameState.getCar().getMoney();
		}
		return money;
	}

	/***
	 * Return the set of stagse that are unlocked
	 */
	@Override
	public Set<Stage> getUnlockedStages() {
		return unlockedStages;
	}


	/***
	 * Return the set of cars that are unlocked
	 */
	@Override
	public Set<CarType> getUnlockedCars() {
		return unlockedCars;
	}



	/***
	 * Unlock a car
	 *
	 * A car is unlocked if it wasn't previously unlocked
	 * and if the player has sufficient money
	 */
	@Override
	public void unlockCar(CarType ct) {
		if(gameState!=null){//if gameState exists, update the money field to latest
			money = gameState.getCar().getMoney();
		}

		if(!unlockedCars.contains(ct)) {
			switch(ct) {
				case CAR1:
						unlockedCars.add(ct);
						break;
				case CAR2:
					if(money>=5) {
						unlockedCars.add(ct);
						money-=5;
					}
					break;
				case CAR3:
					if(money>=15) {
						unlockedCars.add(ct);
						money-=15;
					}
					break;
			}
		}

		if(gameState!=null){//if gameState exists, update the cars afterwards
			gameState.getCar().setMoney(money);
		}

	}


	/**
	 * Save the game state to files given by the String s
	 * @param s
	 */
	@Override
	public void save(String s) {
		if(gameState!=null) {
			gameState.save(s,unlockedCars,unlockedStages);
		}
	}


	/**
	 * Save the game state to files given by the String s
	 * @param s
	 */
	@Override
	public void unlockStage(Stage s) {
		unlockedStages.add(s);
	}


	/**
	 * Method to set the current car to ct
	 * If car is locked, this method calls the unlock method
	 * Following this, if the car is unlocked, sets the current car field to ct
	 * 		and updates the car if their is currently a car
	 * @param ct
	 */
	@Override
	public void setCurrentCar(CarType ct) {


		if(!unlockedCars.contains(ct)) {
			unlockCar(ct);
		}

		if(unlockedCars.contains(ct)) {
			currentCarType = ct;
		}

		if(gameState!=null) {
			gameState.getCar().setCarType(currentCarType);
		}
	}


	/***
	 * Return the current CarType
	 */
	@Override
	public CarType getCarType(){
		return currentCarType;
	}

}
