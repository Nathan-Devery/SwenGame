package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import general.Controller;
import general.State;
import model.IModel;

/***
 * MVC pattern, view component.
 *
 * @author deverynathan
 *
 */
public class View implements Observer{

	private static IModel model;
	private static JFrame frame;

	public static final int WIDTH = 900;
	public static final int HEIGHT = 600;

	protected static GamePanel gamePanel;	//Stored here for efficiency
	protected static Controller controller;

	//Timer
	int refreshRate = 10;
	Timer timer = new Timer(refreshRate, (e) -> model.ping());

	// Race timer
	private long startTime = 0;
	private long stopTime = 0;
	private boolean racing = false;

	public View(IModel model){
		this.model = model;
		model.addObserver(this);
		gamePanel = new GamePanel(model);
		controller = new Controller(model);
		frame = new JFrame("Summit Climb");
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setResizable(false);
	    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    frame.pack();
	    setPane(new HomeMenu(model));	//Delete this
	    frame.setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		//TODO:: switch for game state. Set content pane to correct, repaint.

		switch (model.getState()) {
        case RUNNING: setPane(gamePanel);
        			gamePanel.revalidate();
        			gamePanel.repaint();
        			timer.start();
                 	break;
        case MENU: setPane(new HomeMenu(model));
        			timer.stop();
        			break;
        case PAUSED: setPane(new EscMenu(model));
        			timer.stop();
        			break;
        case VICTORY:
        			gamePanel.repaint();
        			JOptionPane.showMessageDialog(frame, "Stage complete!");
        			model.setState(State.MENU);
        			break;
        case DEFEAT:
        			gamePanel.repaint();
        			JOptionPane.showMessageDialog(frame, "Stage failed!");
        			model.setState(State.MENU);
        			break;
		}

	}

	protected static void setPane(JPanel panel){
		frame.setContentPane(panel);
		panel.repaint();
		panel.revalidate();	//Appears to be a requirement for new panel assignment
		panel.requestFocusInWindow();	//New panels must request focus
	}

	protected static Container getPane(){
		return frame.getContentPane();
	}

}
