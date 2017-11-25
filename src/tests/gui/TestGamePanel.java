package tests.gui;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.junit.Test;

import general.Stage;
import gui.GamePanel;
import gui.HomeMenu;
import gui.LoadMenu;
import gui.View;
import map.ObjectMap;
import model.gameObjects.Bolt;
import model.gameObjects.Car;
import model.gameObjects.Coin;
import model.gameObjects.Fence;
import model.gameObjects.GameObject;
import model.gameObjects.NullGameObject;
import model.gameObjects.Rock;
import model.gameObjects.Tree;
import model.terrain.Terrain;
import resources.ImgResources;
import model.terrain.*;


public class TestGamePanel {

	MockModel model = new MockModel();
	MockGameState gameState = new MockGameState();
	int displayDuration = 3000;

	public TestGamePanel(){
		model.setGameState(gameState);
	}
	
	//Test naming convention: name_StateUnderTest_ExpectedBehavior
	
	//Test stages change
	@Test
	public void desertStage_desert_desert() {
		MockTerrainMap terrainMap = new MockTerrainMap(createMap(10, 10));
		model.setTerrainMap(terrainMap);
		
		MockObjectMap objectMap = new MockObjectMap(createObjectMap(10,10));
		model.setObjectMap(objectMap);
		
		gameState.setStage(Stage.DESERT);
		
		displayAndKill();
	}
	
	@Test
	public void snowStage_snow_snowMap() {
		MockTerrainMap terrainMap = new MockTerrainMap(createMap(10, 10));
		model.setTerrainMap(terrainMap);
		
		MockObjectMap objectMap = new MockObjectMap(createObjectMap(10,10));
		model.setObjectMap(objectMap);
		
		gameState.setStage(Stage.SNOW);
		
		displayAndKill();
	}
	
	@Test
	public void forestStage_forest_forestMap() {
		MockTerrainMap terrainMap = new MockTerrainMap(createMap(10, 10));
		model.setTerrainMap(terrainMap);
		
		MockObjectMap objectMap = new MockObjectMap(createObjectMap(10,10));
		model.setObjectMap(objectMap);
		
		gameState.setStage(Stage.FOREST);
		
		displayAndKill();
	}
	
	@Test
	public void drawObjectMap_Barrals_drawnBarrels() {
		MockTerrainMap terrainMap = new MockTerrainMap(createMap(10, 10));
		model.setTerrainMap(terrainMap);
		
		MockObjectMap objectMap = new MockObjectMap(createObjectMap(10,10));
		objectMap.setObjectAt(0, 0, new Bolt(new Point(0, 0)));
		
		model.setObjectMap(objectMap);
		
		gameState.setStage(Stage.FOREST);
		
		displayAndKill();
	}
	
	@Test
	public void removeGass_lowGass_HalfGass() {
		gameState.getCar().setFuel(10);
		
		MockTerrainMap terrainMap = new MockTerrainMap(createMap(10, 10));
		model.setTerrainMap(terrainMap);
		
		MockObjectMap objectMap = new MockObjectMap(createObjectMap(10,10));
		objectMap.setObjectAt(0, 0, new Bolt(new Point(0, 0)));
		
		model.setObjectMap(objectMap);
		
		gameState.setStage(Stage.FOREST);
		
		displayAndKill();
	}
	
	@Test
	public void health_lowHealth_lowHealthDisplayed() {
		gameState.getCar().setHP(10);
		
		MockTerrainMap terrainMap = new MockTerrainMap(createMap(10, 10));
		model.setTerrainMap(terrainMap);
		
		MockObjectMap objectMap = new MockObjectMap(createObjectMap(10,10));
		objectMap.setObjectAt(0, 0, new Bolt(new Point(0, 0)));
		
		model.setObjectMap(objectMap);
		
		gameState.setStage(Stage.FOREST);
		
		displayAndKill();
	}
	
	@Test
	public void moveCar_carmoved_movedCarDisplayed() {
		gameState.getCar().setPosition(new Point(2, 3));
		
		MockTerrainMap terrainMap = new MockTerrainMap(createMap(10, 10));
		model.setTerrainMap(terrainMap);
		
		MockObjectMap objectMap = new MockObjectMap(createObjectMap(10,10));
		objectMap.setObjectAt(0, 0, new Bolt(new Point(0, 0)));
		
		model.setObjectMap(objectMap);
		
		gameState.setStage(Stage.FOREST);
		
		displayAndKill();
	}
	
	@Test
	public void fence_fenceDispayed_fence() {
		gameState.getCar().setPosition(new Point(2, 3));
		
		MockTerrainMap terrainMap = new MockTerrainMap(createMap(10, 10));
		model.setTerrainMap(terrainMap);
		
		MockObjectMap objectMap = new MockObjectMap(createObjectMap(10,10));
		objectMap.setObjectAt(0, 0, new Fence(new Point(0, 0)));
		
		model.setObjectMap(objectMap);
		
		gameState.setStage(Stage.FOREST);
		
		displayAndKill();
	}
	
	@Test
	public void coin_coinDispayed_coin() {
		gameState.getCar().setPosition(new Point(2, 3));
		
		MockTerrainMap terrainMap = new MockTerrainMap(createMap(10, 10));
		model.setTerrainMap(terrainMap);
		
		MockObjectMap objectMap = new MockObjectMap(createObjectMap(10,10));
		objectMap.setObjectAt(0, 0, new Coin(new Point(0, 0)));
		
		model.setObjectMap(objectMap);
		
		gameState.setStage(Stage.FOREST);
		
		displayAndKill();
	}
	
	@Test
	public void rock_rockDispayed_rock() {
		gameState.getCar().setPosition(new Point(2, 3));
		
		MockTerrainMap terrainMap = new MockTerrainMap(createMap(10, 10));
		model.setTerrainMap(terrainMap);
		
		MockObjectMap objectMap = new MockObjectMap(createObjectMap(10,10));
		objectMap.setObjectAt(0, 0, new Rock(new Point(0, 0)));
		
		model.setObjectMap(objectMap);
		
		gameState.setStage(Stage.FOREST);
		
		displayAndKill();
	}
	
	@Test
	public void tree_treeDispayed_tree() {
		gameState.getCar().setPosition(new Point(2, 3));
		
		MockTerrainMap terrainMap = new MockTerrainMap(createMap(10, 10));
		model.setTerrainMap(terrainMap);
		
		MockObjectMap objectMap = new MockObjectMap(createObjectMap(10,10));
		objectMap.setObjectAt(0, 0, new Tree(new Point(0, 0)));
		
		model.setObjectMap(objectMap);
		
		gameState.setStage(Stage.FOREST);
		
		displayAndKill();
	}

	private void displayAndKill(){
		JFrame frame = new JFrame();

		frame.setPreferredSize(new Dimension(View.WIDTH, View.HEIGHT));
		frame.setResizable(false);
	    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    frame.pack();
	    frame.setVisible(true);

	    GamePanel panel = new GamePanel(model);
	    frame.setContentPane(panel);
		panel.repaint();
		panel.revalidate();	//Appears to be a requirement for new panel assignment
		panel.requestFocusInWindow();	//New panels must request focus

		try{
			Thread.sleep(displayDuration);	//3 second
		}catch(Exception e){

		}
	}
	
	/***
	 * Creates a tile map with length and height with specified tile
	 * @param width
	 * @param height
	 * @param tile
	 */
	private Terrain[][] createMap(int width, int height){
		Terrain[][] terrainMap = new Terrain[width][height];
	
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				terrainMap[i][j] = new Scenery();
			}
		}
		return terrainMap;
	}
	
	private GameObject[][] createObjectMap(int width, int height){
		GameObject[][] objectMap = new GameObject[width][height];
	
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				objectMap[i][j] = new NullGameObject(new Point(i, j));
			}
		}
		return objectMap;
	}

}
