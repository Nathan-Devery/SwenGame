package tests;

import static org.junit.Assert.*;



import java.awt.Point;
import java.awt.Rectangle;

import org.junit.Before;
import org.junit.Test;

import model.gameObjects.Bolt;
import model.gameObjects.Car;
import resources.ImgResources;

/***
 * Test for game objects 
 * 
 * @author hannahclayton
 *
 */
public class TestBolt {
	Point p = new Point(1,1);
	Bolt b;
	Car c = new Car();

	@Before
	public void init(){
		b = new Bolt(p);
		c = new Car();
	}

	@Test
	public void testCollideHP(){
		c.setHP(80);
		b.collide(c);					//doesnt reach collide
		assertEquals(85, c.getHP());
	}

	@Test
	public void testCollideMoney(){
		c.setMoney(100);
		b.collide(c);
		assertEquals(101, c.getMoney());
	}

	@Test
	public void testCollideAccelerate(){
		//TODO acceleration test for bolt
	}

	@Test
	public void testOrigin(){
		assertEquals(p, b.getOrigin() );
	}

	@Test
	public void testBoundingBox(){
		Rectangle r = new Rectangle(p.x, p.y, p.x+50, p.y+50);
		assertEquals(r, b.getBoundingBox());
	}

	@Test
	public void testID(){
		String s = "Bolt";
		assertEquals(s, b.getID());
	}

	@Test
	public void testImg(){
		assertFalse(ImgResources.BOLT.equals(null));
	}

}
