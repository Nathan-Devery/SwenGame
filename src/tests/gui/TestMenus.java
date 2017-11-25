package tests.gui;

import java.awt.Dimension;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.junit.Test;

import gui.GamePanel;
import gui.HomeMenu;
import gui.LoadMenu;
import gui.StageMenu;
import gui.CarMenu;
import gui.View;
import model.IModel;
import model.Model;

public class TestMenus {
	
	int displayDuration = 3000;
	IModel model = new Model();
	
	@Test
	public void showHome() {
		displayAndKill(new HomeMenu(model));
	}
	
	@Test
	public void showLoad() {
		displayAndKill(new LoadMenu(model));
	}
	
	@Test
	public void showStage() {
		displayAndKill(new StageMenu(model));
	}
	
	@Test
	public void showUpgrade() {
		displayAndKill(new CarMenu(model));
	}
	
	
	private void displayAndKill(JPanel panel){
		JFrame frame = new JFrame();

		frame.setPreferredSize(new Dimension(View.WIDTH, View.HEIGHT));
		frame.setResizable(false);
	    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    frame.pack();
	    frame.setVisible(true);

	    frame.setContentPane(panel);
		panel.repaint();
		panel.revalidate();	//Appears to be a requirement for new panel assignment

		try{
			Thread.sleep(displayDuration);	//3 second
		}catch(Exception e){

		}
	}
}
