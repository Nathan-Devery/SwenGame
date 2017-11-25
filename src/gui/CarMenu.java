package gui;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;

import model.IModel;
import resources.ImgResources;
import general.CarType;

/***
 * Menu for car choice, once a player reaches higher levels the car's unlock and
 * can be used on the game map.
 *
 * @author deverynath
 *
 */
@SuppressWarnings("serial")
public class CarMenu extends AbstractMenu{

	IModel model;

	//Easier access
	private int width = View.WIDTH;
	private int height = View.HEIGHT;

	//Car buttons
	Button button1;
	Button button2;
	Button button3;

	//Speed rectangle
	int speedRecWidth = 250;

	//Car button map
	Map<CarType, Button> carButtonMap = new HashMap<>();

	public CarMenu(IModel model){
		this.model = model;
		initCarButtons();
		initButtonCarMap();
		//disableButtons();
		addButtons();
	}

	@Override
	public void paintComponent(Graphics _g) {
	    super.paintComponent(_g);
	    Graphics2D g = (Graphics2D) _g;
	    drawCenteredString(g, "Car Selection", new Rectangle(0, 0, width, height/3), new Font(Font.SANS_SERIF, 3, 80));
	    highLightSelected();
	    drawButtons(g);

	    //Speed display
	    g.setFont(new Font(Font.SANS_SERIF, 3, 40));
	    g.drawString("Speed", 155, (height/3) * 2 + 80);

	    g.setColor(Color.RED);
	    g.fillRect(300, (height/3) * 2 + 50, (int)((model.getCarType().getStrategy().getTerminalVelocity() / 0.15) * speedRecWidth)
	    		, 30);

	    g.setColor(Color.WHITE);
	    g.drawRect(300, (height/3) * 2 + 50, speedRecWidth, 30);

	    g.setColor(Color.BLACK);
	    g.drawRect(300, (height/3) * 2 + 50, speedRecWidth, 30);

	    //Coins display
	    g.drawImage(ImgResources.COIN.img, 675, (int)(height/3) * 2 + 15,
				100, 100, null);

	    g.drawString(String.valueOf(model.getMoney()), 650, (height/3) * 2 + 80);

	}

	private void initCarButtons(){
		int buttonWidth = width/4;
		int buttonHeight = height/3;
		int gap = (width - buttonWidth * 3 ) / 4;

		button1 = new Button("$0", gap, height/3, buttonWidth, buttonHeight, ImgResources.CAR1.img,
				() -> {
					View.controller.setCar(CarType.CAR1);
				});

		button2 = new Button("$5", buttonWidth + gap * 2, height/3, buttonWidth, buttonHeight, ImgResources.CAR2.img,
				() -> {
					View.controller.setCar(CarType.CAR2);
					}
				);

		button3 = new Button("$15", buttonWidth * 2 + gap * 3, height/3, buttonWidth, buttonHeight, ImgResources.CAR3.img,
				() -> {
					View.controller.setCar(CarType.CAR3);
					}
				);
	}

	private void initButtonCarMap(){
		carButtonMap.put(CarType.CAR1, button1);
		carButtonMap.put(CarType.CAR2, button2);
		carButtonMap.put(CarType.CAR3, button3);
	}


	//Highlights the button selected
	private void highLightSelected(){
		unHighlightButtons();
		carButtonMap.get(model.getCarType()).setPermSelected(true);
	}

	private void unHighlightButtons(){
		button1.setPermSelected(false);
		button2.setPermSelected(false);
		button3.setPermSelected(false);
	}

	private void addButtons(){
		buttons.add(button1);
		buttons.add(button2);
		buttons.add(button3);


		buttons.add(new Button("Back", 0, height - width/13, width/10, height/15, null,
				() -> View.setPane(new HomeMenu(model))));
	}

	/***
	 * Disables the buttons for the cars which are not unlocked
	 */
	private void disableButtons(){
		button1.setDisabled(true);
		button2.setDisabled(true);
		button3.setDisabled(true);

		//Unlock the unlocked
		for(CarType type: model.getUnlockedCars()){
			carButtonMap.get(type).setDisabled(false);
		}
	}

}
