package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import gui.LoadMenu.InnerClass;
import model.IModel;
import resources.ImgResources;


/***
 * Displays all the different game stages/levels the player can access.
 *
 * @author deverynathan
 *
 */
@SuppressWarnings("serial")
public class LoadMenu extends AbstractMenu{

	IModel model;

	//Easier access
	private int width = View.WIDTH;
	private int height = View.HEIGHT;

	public LoadMenu(IModel model){
		this.model = model;
		addScrollComponent();
		addButtons();
	}

	@Override
	public void paintComponent(Graphics _g) {
	    super.paintComponent(_g);
	    Graphics2D g = (Graphics2D) _g;

	    drawCenteredString(g, "Load Game", new Rectangle(0, 0, width, height/3), new Font(Font.SANS_SERIF, 3, 80));
	    drawButtons(g);
	}

	private void addButtons(){
		int buttonWidth = width/5;
		int buttonHeight = height/3;

		buttons.add(new Button("Load", width/2 - buttonWidth/3, height * 5/6 - buttonHeight/3, buttonWidth * 2/3,
				buttonHeight/3, null,
				() -> View.setPane(new LoadMenu(model))));

		buttons.add(new Button("Back", 0, height - width/13, width/10, height/15, null,
				() -> View.setPane(new HomeMenu(model))));
	}

	private void addScrollComponent(){
		this.setLayout(null);

		InnerClass[] items = new InnerClass[20];
		for(int i = 0; i < items.length; i++){
			items[i] = new InnerClass();
		}

		JList<InnerClass> list = new JList<InnerClass>(items);

		int cellWidth = width * 11/12;
		int cellHeight = height / 3;

		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setVisibleRowCount(-1);
		list.setFixedCellHeight(44);
		list.setFixedCellWidth(cellWidth);
		list.setFont(list.getFont().deriveFont(22.0f));

		//Center text
		DefaultListCellRenderer renderer = (DefaultListCellRenderer) list.getCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);

		//Create scrollPane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(list);
		scrollPane.setBounds(width/24, height/3, cellWidth, cellHeight);

		this.add(scrollPane);
	}

	//Test class
	class InnerClass {
       @Override
       public String toString(){
    	   return "Inner class String";
       }
    }

}
