package gui;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import javax.swing.JPanel;

import general.Controller;
import general.Stage;
import general.Vector2D;
import map.IObjectMap;
import map.ITerrainMap;
import model.IModel;
import map.ObjectMap;
import map.TerrainMap;
import model.gameObjects.Car;
import model.gameObjects.GameObject;
import model.terrain.Terrain;
import npc.NpcObject;
import resources.ImgResources;


/***
 * This is the JPanel in which the game play is displayed.
 *
 * @author deverynath
 *
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel implements MouseWheelListener{

	IModel model;

	//Tiles Sizes
	Dimension imgDimension = new Dimension(50, 50);

	//Scroll wheel zoom unit change
	int scrollUnit = 5;

	//Drawing offset
	int xOffset;
	int yOffset;

	public GamePanel(IModel model){
		this.model = model;
		this.addKeyListener(new Controller(this.model));
		this.addMouseWheelListener(this);
		this.setBackground(Color.CYAN);
	}

	public void paintComponent(Graphics _g) {
	    super.paintComponent(_g);
	    Graphics2D g = (Graphics2D) _g;
	    if(model != null){
	    	if(model.getGameState() != null){
	    		drawTextures(g);
	    		drawObjects(g);
	    		drawCar(g);
	    		drawNPCs(g);
	    		drawHealth(g);
	    		drawFuel(g);
	    	}
	    }
	}

	private void drawTextures(Graphics2D g){
		ITerrainMap terrainMap = model.getGameState().getTerrainMap(); //TODO
		Stage stage = model.getGameState().getStage();

		Vector2D offset = calcOffset();

		Terrain[][] map = terrainMap.getTerrainArray();
		for(int y = 0; y < terrainMap.getHeight(); y++){
			for(int x = 0; x < terrainMap.getWidth(); x++){
				Terrain terrainObject = terrainMap.getTerrainAt(x, y);

				if(terrainObject != null){
					Image im = terrainObject.getImageEnum(stage).img;
					g.drawImage(im, (int)(imgDimension.width * x + offset.getX()),
						(int)(imgDimension.height * y + offset.getY()), imgDimension.width, imgDimension.height, null);
				}
			}
		}

	}

	private void drawObjects(Graphics2D g){
		IObjectMap objectMap = model.getGameState().getObjectMap();
		Stage stage = model.getGameState().getStage();

		Vector2D offset = calcOffset();

		GameObject[][] map = objectMap.getObjectArray();
		for(int i = 0; i < objectMap.getHeight(); i++){
			for(int j = 0; j < objectMap.getWidth(); j++){
				GameObject gameObject = objectMap.getObjectAt(j, i);

				if(gameObject.getImageEnum(stage) != null){
					Image img = gameObject.getImageEnum(stage).img;
					g.drawImage(img, (int)(imgDimension.width * j + offset.getX()),
						(int)(imgDimension.height * i + offset.getY()), imgDimension.width, imgDimension.height, null);
				}
			}
		}
	}

	private void drawNPCs(Graphics2D g){
		List<NpcObject> npcList = model.getGameState().getNpcObjects();
		
		if(npcList == null) return;

		Vector2D offset = calcOffset();

		for(NpcObject npc: npcList){
			Vector2D position = npc.getPosition();
			BufferedImage img = ImgResources.rotateImage(npc.getImageEnum().img, npc.getDirection());

			g.drawImage(img, (int)(position.getX() * imgDimension.width + offset.getX()),
					(int)(position.getY() * imgDimension.height + offset.getY()), imgDimension.width, imgDimension.height, null);
		}

	}

	/**
	 * Calculates the offset of tiles relative to the car
	 * @return
	 */
	private Vector2D calcOffset(){
		Car car = model.getGameState().getCar();
		return new Vector2D((View.WIDTH/2 - imgDimension.width/2 - car.getPosition().getX()*imgDimension.width),
				(View.HEIGHT/2 - imgDimension.height/2 - car.getPosition().getY()*imgDimension.height));
	}

	private void drawCar(Graphics2D g){
		Car car = model.getGameState().getCar();
		Stage stage = model.getGameState().getStage();
		BufferedImage img = ImgResources.rotateImage(car.getImageEnum(stage).img, car.getDirection());

		g.drawImage(img, (int)(View.WIDTH/2 - imgDimension.getWidth()/2),
				(int)(View.HEIGHT/2 - imgDimension.getHeight()/2), (int)imgDimension.getWidth(), (int)imgDimension.getHeight(), null);

	}

	private void drawHealth(Graphics2D g){
		int healthRecWidth = 200;

		g.drawImage(ImgResources.HEART.img, 5, 5, 30, 30, null);

	    double maxHealth = model.getCarType().getStrategy().getMaxHP();
	    double healthPercentage = model.getGameState().getCar().getHP() / maxHealth;

	    g.setColor(Color.RED);
	    g.fillRect(45, 10, (int)(healthRecWidth * healthPercentage), 20);

	    g.setColor(Color.BLACK);
	    g.drawRect(45, 10, healthRecWidth, 20);
	}

	private void drawFuel(Graphics2D g){
		int fuelRecWidth = 200;

		g.drawImage(ImgResources.GAS.img, 5, 40, 30, 30, null);

	    double maxFuel = model.getCarType().getStrategy().getMaxFuel();
	    double fuelPercentage = model.getGameState().getCar().getFuel() / maxFuel;

	    g.setColor(Color.GREEN.darker());
	    g.fillRect(45, 45, (int)(fuelRecWidth * fuelPercentage), 20);

	    g.setColor(Color.BLACK);
	    g.drawRect(45, 45, fuelRecWidth, 20);
	}

	//This method has been placed internally as it does not directly change the model
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if((imgDimension.getWidth() - e.getWheelRotation() * scrollUnit) > 10){
			imgDimension.setSize(imgDimension.getWidth() - e.getWheelRotation() * scrollUnit,
					imgDimension.getHeight() - e.getWheelRotation() * scrollUnit);

			revalidate();
			repaint();
		}
	}

}
