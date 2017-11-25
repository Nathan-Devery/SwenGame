package tests;

import static org.junit.Assert.*;


import java.awt.Point;
import java.awt.Rectangle;

import org.junit.Before;
import org.junit.Test;

import model.gameObjects.Car;
import model.gameObjects.CheckPoint;
import resources.ImgResources;

/***
 * Test for game objects 
 * 
 * @author hannahclayton
 *
 */
public class TestCheckPoint {
	Point p = new Point(1,1);
	CheckPoint cp;
	Car c;

	@Before
	public void init(){
		c = new Car();
		cp = new CheckPoint(p);
	}

	@Test
	public void testCollideHP(){
		c.setHP(40);
		cp.collide(c);
		assertEquals(50, c.getHP());
	}

	@Test
	public void testCollideMoney(){
		c.setMoney(100);
		cp.collide(c);
		assertEquals(110, c.getMoney());
	}


	@Test
	public void testOrigin(){
		assertEquals(p, cp.getOrigin() );
	}

	@Test
	public void testBoundingBox(){
		Rectangle r = new Rectangle(p.x, p.y, p.x+50, p.y+50);
		assertEquals(r, cp.getBoundingBox());
	}

	@Test
	public void testID(){
		String s = "Check Point";
		assertEquals(s, cp.getID());
	}

	@Test
	public void testImg(){
		assertFalse(ImgResources.CHECKPOINT1.equals(null));
		assertFalse(ImgResources.CHECKPOINT2.equals(null));
	}

	@Test
	public void testObjectSize(){
		assertEquals(cp.objectSize, 50);
	}

}

