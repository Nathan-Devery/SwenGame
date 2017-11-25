package gui;

import java.awt.Font;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import general.Controller;
import general.State;
import model.IModel;

/***
 * Pause menu, when player presses escape this screen is displayed.
 * Allows the player to pause, resume or return to the main menu.
 *
 * @author deverynath
 *
 */
public class EscMenu extends AbstractMenu{
	IModel model;

	//Easier access
	private int width = View.WIDTH;
	private int height = View.HEIGHT;

	public EscMenu(IModel model){
		this.model = model;
		this.addKeyListener(new Controller(model));
		addButtons();
	}

	@Override
	public void paintComponent(Graphics _g) {
	    super.paintComponent(_g);
	    Graphics2D g = (Graphics2D) _g;
	    drawCenteredString(g, "Paused", new Rectangle(0, 0, width, height/3), new Font(Font.SANS_SERIF, 3, 80));
	    drawButtons(g);
	}

	private void addButtons(){
		int buttonHeight = (height * 2/3) / 6;

		buttons.add(new Button("Main Menu", width/3, height/3, width/3, buttonHeight, null,
				() -> {
					View.setPane(new HomeMenu(model));
				}
		));

		buttons.add(new Button("Save", width/3, height/3 + (int)(buttonHeight * 1.5), width/3, buttonHeight, null,
				() -> {
					View.controller.save();
				}
		));

		buttons.add(new Button("Continue", width/3, height/3 + (int)(buttonHeight * 3), width/3, buttonHeight, null,
				() -> {
					model.setState(State.RUNNING);
					model.ping();
				}
		));
	}
}
