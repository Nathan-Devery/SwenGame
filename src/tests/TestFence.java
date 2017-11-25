package tests;

import static org.junit.Assert.*;

import java.awt.Point;
import java.awt.Rectangle;

import org.junit.Before;
import org.junit.Test;

import model.gameObjects.Car;
import model.gameObjects.Fence;
import resources.ImgResources;

/***
 * Test for game objects 
 * 
 * @author hannahclayton
 *
 */
public class TestFence {
	Point p = new Point(1,1);
	Fence f;
	Car c;

	@Before
	public void init(){
		f = new Fence(p);
		c = new Car();
	}

	@Test
	public void testCollideHP(){
		c.setHP(90);
		f.collide(c);
		assertEquals(80, c.getHP());
	}

	@Test
	public void testCollideDecelerate(){
		//TODO test deceleration for fence  
	}

	@Test
	public void testOrigin(){
		assertEquals(p, f.getOrigin() );
	}

	@Test
	public void testBoundingBox(){
		Rectangle r = new Rectangle(p.x, p.y, p.x+50, p.y+50);
		assertEquals(r, f.getBoundingBox());
	}

	@Test
	public void testID(){
		String s = "Fence";
		assertEquals(s, f.getID());
	}

	@Test
	public void testImg(){
		assertFalse(ImgResources.FENCE.equals(null));
	}

}
