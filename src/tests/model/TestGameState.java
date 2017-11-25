package tests.model;

import general.Stage;
import map.ITerrainMap;
import model.gameObjects.*;
import tests.gui.MockObjectMap;
import tests.gui.MockTerrainMap;
import model.GameState;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;


/**
 *
 * @auther fraserhuon
 */
public class TestGameState {
	GameState gs = null;

	@Test
	public void testLoadMetaDataOnly() {
		//GameState gs = GameState.load(Stage.DESERT.getFileName());
		//assertEquals(0,gs.getAICars().size());
		//assertEquals(null,gs.getCar());
	//	Car c = gs.getCar();
	}

	@Test
	public void testCollisionNotNull(){
		Rectangle r = new Rectangle(0,0,50,50);
		GameObject collisionObject = gs.detectCollisions(r);
		assertEquals(gs.getObjectMap().getObjectAt(0,0),collisionObject);
		assert(collisionObject instanceof Rock);
	}

	@Test
	public void testCollisionNull(){
		Rectangle r = new Rectangle(52,52,50,50);
		GameObject collisionObject = gs.detectCollisions(r);
		assertEquals(gs.getObjectMap().getObjectAt(1,1),collisionObject);
		assert(collisionObject instanceof NullGameObject);
	}

	@Test
	public void testNotDestroyableNotRemovedAfterCollision(){
		gs.ping();
		assertEquals(new Rock(new Point(0,0)),gs.getObjectMap().getObjectAt(0,0));
	}

	@Test
	public void testDestroyableRemovedAfterCollision(){
		gs.getCar().setPosition(new Point(1,1));
		assertEquals(new Coin(new Point(0,1)),gs.getObjectMap().getObjectAt(0,1));
		gs.ping();
		assertEquals(new NullGameObject(new Point(0,1)),gs.getObjectMap().getObjectAt(0,1));
	}

	@Before
	public void initiate(){
		gs = new GameState(Stage.DESERT,new MockTerrainMap(), new MockObjectMap(),new Car(),new HashSet<>(),
				new HashSet<>(),new ArrayList<>());
	}
}
