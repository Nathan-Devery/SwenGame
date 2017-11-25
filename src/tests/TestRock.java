package tests;

import static org.junit.Assert.*;

import java.awt.Point;
import java.awt.Rectangle;

import org.junit.Before;
import org.junit.Test;

import general.Vector2D;
import model.gameObjects.Car;
import model.gameObjects.Rock;
import resources.ImgResources;

/***
 * Test for game objects
 * 
 * @author hannahclayton
 *
 */
public class TestRock {
	Point p = new Point(1,1);
	Rock r;
	Car c;

	@Before
	public void init(){
		c = new Car();
		r = new Rock(p);
	}

	@Test
	public void testCollideDeceleration(){
		r.collide(c);
		assertEquals(c.getVelocity(), new Vector2D(0,0));
	}

	@Test
	public void testOrigin(){
		assertEquals(p, r.getOrigin() );
	}

	@Test
	public void testBoundingBox(){
		Rectangle rec = new Rectangle(p.x, p.y, p.x+50, p.y+50);
		assertEquals(rec, r.getBoundingBox());
	}

	@Test
	public void testID(){
		String s = "R";
		assertEquals(s, r.getID());
	}

	@Test
	public void testImg(){
		assertFalse(ImgResources.ROCK.equals(null));
	}

}
