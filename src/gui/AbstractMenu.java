package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

/***
 * Base class for all menu panes
 *
 * @author Nathan
 *
 */
public abstract class AbstractMenu extends JPanel implements MouseListener, MouseMotionListener{

	ArrayList<Button> buttons = new ArrayList<>();

	public AbstractMenu(){
		this.setPreferredSize(new Dimension(View.WIDTH, View.HEIGHT));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	public void mouseClicked(MouseEvent e){

	}

	//Mouse listener
	@Override
	public void mousePressed(MouseEvent e) {
		for(Button button: buttons){
			if(button.contains(new Point(e.getX(), e.getY()))){
				button.setPressed(true);
			}else{
				button.setPressed(false);
			}
		}
		this.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for(Button button: buttons){
			if(button.contains(new Point(e.getX(), e.getY())) && button.getPressed()){
				button.press();
			}else{
				button.setSelected(false);
				button.setPressed(false);
			}
		}
		this.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	//Mouse motion
	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		for(Button button: buttons){
			if(button.contains(new Point(e.getX(), e.getY()))){
				button.setSelected(true);
			}else{
				button.setSelected(false);
				button.setPressed(false);
			}
		}
		this.repaint();
	}

	protected void drawButtons(Graphics2D g){
		for(Button button: buttons){
			button.draw(g);
		}
	}

	/**
	 * Draw a String centered in the middle of a Rectangle.
	 *
	 * @param g The Graphics instance.
	 * @param text The String to draw.
	 * @param rect The Rectangle to center the text in.
	 */
	public static void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
	    // Get the FontMetrics
	    FontMetrics metrics = g.getFontMetrics(font);
	    // Determine the X coordinate for the text
	    int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
	    // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
	    int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
	    // Set the font
	    g.setFont(font);
	    // Draw the String
	    g.drawString(text, x, y);
	}
}
