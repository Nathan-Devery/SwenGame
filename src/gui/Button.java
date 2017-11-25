
package gui;

import java.awt.Color;

import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.function.Function;
import resources.ImgResources;

/***
 * Defines a button for the menu
 *
 * @author Nathan
 *
 */
@SuppressWarnings("serial")
public class Button extends Component{

	private String text;
	private int x;
	private int y;
	private Rectangle rectangle;
	private boolean selected = false;	//Hover highlight
	private boolean pressed = false;	//Press highlight
	private boolean disabled = false;
	private boolean permSelected = false; 	//Make button stay highlighted
	BufferedImage image;
	Runnable buttonFunction;

	/***
	 *
	 * @param text Text inside button
	 * @param x	x coordinate of drawing
	 * @param y y coordinate of drawing
	 * @param width
	 * @param height
	 * @Param image to display, can be null for none
	 * @param buttonFunction The function for execution on button press
	 */
	public Button(String text, int x, int y, int width, int height, BufferedImage image, Runnable buttonFunction){
		this.text = text;
		this.x = x;
		this.y = y;
		this.rectangle = new Rectangle(x, y, width, height);
		this.image = image;
		this.buttonFunction = buttonFunction;
	}

	/***
	 * Draws button
	 * @param g
	 */
	public void draw(Graphics2D g){
		Color oldColor = g.getColor();	//Stores current color state

		if(selected){
			g.setColor(Color.CYAN);
		}else if(permSelected){
			g.setColor(Color.YELLOW);
		}else{
			g.setColor(Color.GRAY);
		}

		if(pressed) g.setColor(Color.CYAN.darker());
		g.fillRect(x, y, (int)rectangle.getWidth(), (int)rectangle.getHeight());

		g.setColor(Color.WHITE);
		g.drawRect(x, y, (int)rectangle.getWidth(), (int)rectangle.getHeight());

		//Draw image
		double width = rectangle.getWidth();
		double height = rectangle.getHeight();

		if(image != null){
			g.drawImage(image, (int)(rectangle.getX() + width/5), (int)(rectangle.getY() + width/5),
					(int)((rectangle.getWidth() / 5) * 3), (int)((rectangle.getHeight() / 5) * 3), null);
		}

		if(disabled){
			g.setColor(Color.RED);
			AbstractMenu.drawCenteredString(g, "X", rectangle, new Font(Font.SANS_SERIF, 3, 80));
		}

		g.setColor(Color.WHITE);
		//Draw string
		AbstractMenu.drawCenteredString(g, text, rectangle, new Font(Font.SANS_SERIF, 3, 20));

		g.setColor(oldColor);	//Put state back
	}

	public boolean contains(Point point){
		return rectangle.contains(point);
	}

	/***
	 * Highlights button for mouse over
	 * @param selected
	 */
	public void setSelected(boolean selected){
		this.selected = selected;
	}

	public void setPressed(boolean pressed){
		this.pressed = pressed;
	}

	public boolean getPressed(){
		return pressed;
	}

	public void press(){
		buttonFunction.run();
	}

	public void setDisabled(boolean choice){
		this.disabled = choice;
	}

	public void setPermSelected(boolean selection){
		this.permSelected = selection;
	}
}
