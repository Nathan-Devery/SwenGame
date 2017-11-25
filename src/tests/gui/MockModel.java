package tests.gui;

import general.*;
import map.IObjectMap;
import map.ITerrainMap;
import map.TerrainMap;
import model.GameState;
import model.IGameState;
import model.IModel;
import model.gameObjects.Car;
import model.terrain.*;

import java.util.Observer;

import java.util.Set;

/***
 * Mock model for testing purposes
 *
 * @author deverynath
 *
 */
public class MockModel implements IModel{

	private State state = State.RUNNING; 
	private MockGameState gameState = new MockGameState();
	private int money = 15;
	private Set<CarType> unlockedCars;
	private Set<Stage> unlockedStages;
	private CarType currentCarType = CarType.CAR1;

	@Override
	public void setMoney(int m) {
		money = m;
	}

	@Override
	public int getMoney() {
		return money;
	}

	@Override
	public void loadSave(String s) {

	}

	@Override
	public Set<Stage> getUnlockedStages() {
		return null;
	}

	@Override
	public Set<CarType> getUnlockedCars() {
		return null;
	}

	public void restart() {

	}

	public void setState(State state){
		this.state = state;
	}

	public State getState() {
		return state;
	}

	public void ping() {
	}

	public void movePlayerCar(Direction d) {

	}

	public void run() {
		
	}

	@Override
	public void load(Stage s) {

	}

	@Override
	public void addObserver(Observer o) {

	}

	public IGameState getGameState(){
		return this.gameState;
	}


	public void load(String s) {

	}

	@Override
	public void unlockCar(CarType ct) {

	}

	@Override
	public void save(String s) {

	}

	@Override
	public void setCurrentCar(CarType ct) {
		this.currentCarType = ct;
	}

	@Override
	public void unlockStage(Stage s) {
		this.unlockedStages.add(s);
	}

	@Override
	public CarType getCarType() {
		return this.currentCarType;
	}

	@Override
	public void finishStage(Stage s) {
		// TODO Auto-generated method stub
		
	}
	
	public void setGameState(MockGameState gameState){
		this.gameState = gameState;
	}
	
	//Easier access methods
	public void setTerrainMap(ITerrainMap map){
		this.gameState.setTerrainMap(map);
	}
	
	public void setObjectMap(IObjectMap map){
		this.gameState.setObjectMap(map);
	}

}
