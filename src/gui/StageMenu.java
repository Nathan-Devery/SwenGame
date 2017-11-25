package gui;

import java.awt.Font;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;

import general.Stage;
import general.State;
import model.IModel;
import resources.ImgResources;

/***
 * The stage menu displays which stage the player can play.
 * These are the hills, desert and snow stages.
 *
 * @author deverynathan
 *
 */
@SuppressWarnings("serial")
public class StageMenu extends AbstractMenu{

	IModel model;

	//Easier access
	private int width = View.WIDTH;
	private int height = View.HEIGHT;

	//Car buttons
	Button button1;
	Button button2;
	Button button3;

	//Car button map
	Map<Stage, Button> stageButtonMap = new HashMap<>();

	public StageMenu(IModel model){
		this.model = model;
		initStageButtons();
		initButtonCarMap();
		disableButtons();
		addButtons();
	}

	@Override
	public void paintComponent(Graphics _g) {
	    super.paintComponent(_g);
	    Graphics2D g = (Graphics2D) _g;
	    drawCenteredString(g, "Select Stage", new Rectangle(0, 0, width, height/3), new Font(Font.SANS_SERIF, 3, 80));
	    //highLightSelected();
	    drawButtons(g);
	}


	private void initStageButtons(){
		int buttonWidth = width/4;
		int buttonHeight = height/3;
		int gap = (width - buttonWidth * 3 ) / 4;

		button1 = new Button("Stage1", gap, height/3, buttonWidth, buttonHeight, ImgResources.HILLS.img,
				() -> {
					View.controller.startStage(Stage.DESERT);
					View.controller.ping();
				});

		button2 = new Button("Stage2", buttonWidth + gap * 2, height/3, buttonWidth, buttonHeight, ImgResources.GRASS.img,
				() -> {
					View.controller.startStage(Stage.FOREST);
					View.controller.ping();
					}
				);

		button3 = new Button("Stage3", buttonWidth * 2 + gap * 3, height/3, buttonWidth, buttonHeight, ImgResources.FOREST.img,
				() -> {
					View.controller.startStage(Stage.SNOW);
					View.controller.ping();
					}
				);
	}

	private void initButtonCarMap(){
		stageButtonMap.put(Stage.DESERT, button1);
		stageButtonMap.put(Stage.FOREST, button2);
		stageButtonMap.put(Stage.SNOW, button3);
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
		for(Stage stage: model.getUnlockedStages()){
			stageButtonMap.get(stage).setDisabled(false);
		}
	}

}
