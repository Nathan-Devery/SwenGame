package general;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import model.IModel;

/***
 * Handles the key event from the text field and MVC communication.
 *
 * @author nathandevery
 */
public class Controller implements KeyListener{

	Map<Integer, Direction> keyMap = new HashMap<>();
    private IModel model;

    String saveString = "save";
    
    /**
     * The model to control
     * @param m
     */
    public Controller(IModel m){
        this.model = m;
        initializeKeyMap();
    }

	@Override
	public void keyTyped(KeyEvent e) {


	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			if(model.getState() == State.RUNNING){
				model.setState(State.PAUSED);
				return;
			}else{
	
				model.setState(State.RUNNING);
				return;
			}
		}

		model.movePlayerCar(keyMap.get(e.getKeyCode()));
		model.getGameState().getCar();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//keyDown = false;
	}

	/***
	 * Makes the model load in the stage provided
	 * @param stage
	 */
	public void startStage(Stage stage){
		model.load(stage);
	}

	private void initializeKeyMap(){
		keyMap.put(KeyEvent.VK_UP, Direction.UP);
		keyMap.put(KeyEvent.VK_RIGHT, Direction.RIGHT);
		keyMap.put(KeyEvent.VK_DOWN, Direction.DOWN);
		keyMap.put(KeyEvent.VK_LEFT, Direction.LEFT);
	}

	public void setCar(CarType car) {
		model.setCurrentCar(car);
	}
	
	/**
	 * Calls the save method
	 */
	public void save(){
		model.save(saveString);
	}

	/**
	 * Loads the predefined save
	 */
	public void load(){
		model.loadSave(saveString);
	}
	
	/**
	 * Pigns model
	 */
	public void ping(){
		model.ping();
	}
}
