package tests;

import static org.junit.Assert.*;


import java.awt.Point;
import java.awt.Rectangle;

import org.junit.Before;
import org.junit.Test;

import model.gameObjects.Car;
import model.gameObjects.Tree;
import resources.ImgResources;

/***
 * Test for game objects
 * 
 * @author hannahclayton
 *
 */
public class TestTree {
	Point p = new Point(1,1);
	Tree t;
	Car c;

	@Before
	public void init(){
		c = new Car();
		t = new Tree(p);
	}

	@Test
	public void testCollideHP100(){
		assertEquals(100, c.getHP());
		t.collide(c);
		assertEquals(90, c.getHP());
	}

	@Test
	public void testCollideHP20(){
		c.setHP(20);
		assertEquals(20, c.getHP());
		t.collide(c);
		assertEquals(15, c.getHP());
	}

	@Test
	public void testOrigin(){
		assertEquals(p, t.getOrigin() );
	}

	@Test
	public void testBoundingBox(){
		Rectangle r = new Rectangle(p.x, p.y, p.x+50, p.y+50);
		assertEquals(r, t.getBoundingBox());
	}

	@Test
	public void testID(){
		String s = "T";
		assertEquals(s, t.getID());
	}

	@Test
	public void testImg(){
		assertFalse(ImgResources.TREE.equals(null));
	}

}
