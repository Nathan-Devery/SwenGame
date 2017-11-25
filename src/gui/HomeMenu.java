package gui;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import model.IModel;
import resources.ImgResources;

/***
 *This is the home menu of the game.
 *
 * @author deverynathan
 *
 */
public class HomeMenu extends AbstractMenu{
	IModel model;

	//Easier access
	private int width = View.WIDTH;
	private int height = View.HEIGHT;

	//Animation related
	Thread animation;
	static int greenLevel = 255;	//Decrements for more night look
	Color skyColor = new Color(0, greenLevel, 255);

	public HomeMenu(IModel model){
		this.model = model;
		startAnimation();
		addButtons();
	}

	@Override
	public void paintComponent(Graphics _g) {
	    super.paintComponent(_g);
	    Graphics2D g = (Graphics2D) _g;

	    g.setColor(skyColor);
	    g.fillRect(0, 0, width, height);

	    g.setColor(Color.WHITE);
	    //Put mountain draw here if don't want mountain overlap text
	    drawCenteredString(g, "Sea to Summit", new Rectangle(0, 0, width, height/3), new Font(Font.SANS_SERIF, 3, 80));
	    g.drawImage(ImgResources.MOUNTAINS.img, 0, 0, width, height, null);
	    drawButtons(g);
	}

	private void startAnimation(){
		//Background colour thread
		animation = new Thread(() -> {
				boolean darken = true;
				while(true){
					if(greenLevel == 255){
						darken = true;
					}else if(greenLevel == 0){
						darken = false;
					}

					int decrement = 5;	//Only multiples of 5
					if(darken && greenLevel - decrement >= 0){
						greenLevel -= decrement;
					}else if(greenLevel + decrement <= 255){
						greenLevel += decrement;
					}
					skyColor = new Color(0, greenLevel, 255);
					this.repaint();
					try{
						Thread.sleep(100);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		);
		animation.start();
	}

	private void addButtons(){
		int buttonHeight = (height * 2/3) / 6;
		//TODO: More button functions
		buttons.add(new Button("Play", width/3, height/3, width/3, buttonHeight, null,
				() -> {
					View.setPane(new StageMenu(model));
					animation.stop();	//TODO: Nathan change this
				}
		));

		buttons.add(new Button("Car Selection", width/3, height/3 + (int)(buttonHeight * 1.5), width/3, buttonHeight, null,
				() -> {
					View.setPane(new CarMenu(model));
					animation.stop();	//TODO: Nathan change this
				}
		));

		buttons.add(new Button("Load Game", width/3, height/3 + (int)(buttonHeight * 3), width/3, buttonHeight, null,
				() -> {
					//View.setPane(new LoadMenu(model));
					View.controller.load();
					View.controller.ping();
					animation.stop();	//TODO: cNathan hange this
				}
		));
	}
}
