package main;

import javax.swing.SwingUtilities;


import gui.View;
import model.Model;
import general.CarType;
import general.Stage;
import resources.ImgResources;

/***
 *
 *Runs the game
 *
 */
public class Launcher {


	public static void main(String [ ] args){
		ImgResources.values();
		Stage.values();
		CarType.values();
		SwingUtilities.invokeLater(()->new View(new Model()));
	}
}
