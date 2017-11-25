package main;

import general.Stage;
import model.Model;

/***
 * Launcher for testing purposes
 * @author fraserhuon
 *
 */
public class TestGame {

	public static void main (String args[]) {
		Model m = new Model();
		m.load(Stage.FOREST);
	}

}
