package tests.model;

import map.ObjectMap;
import model.gameObjects.*;
import org.junit.Before;
import org.junit.Test;

import loadAndSave.Saver;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
/***
 *
 * @author fraserhuon
 *
 */
public class TestObjectMap {
	ObjectMap objectMap;
	String fileName1 = "src/files/testObjectMap.txt";
	String saveFileName1 = "src/files/saveObjectMap.txt";
	@Before
	public void initialise() {
		GameObject[][] array = new GameObject[3][3];
		array[0][0]= null;
		array[0][1]= null;
		array[0][2]= null;
		array[1][0]= null;
		array[1][1]= null;
		array[1][2]= null;
		array[2][0]= null;
		array[2][1]= null;
		array[2][2]= new Bolt(new Point(2,2));
		objectMap = new ObjectMap(array);
	}

	@Test
	public void test1() {
		assertEquals(3, objectMap.getHeight());
		assertEquals(3, objectMap.getWidth());

		assertEquals(null,objectMap.getObjectAt(0, 0));
		assertEquals(null,objectMap.getObjectAt(0, 1));
		assertEquals(null,objectMap.getObjectAt(0, 2));
		assertEquals(null,objectMap.getObjectAt(1, 0));
		assertEquals(null,objectMap.getObjectAt(1, 1));
		assertEquals(null,objectMap.getObjectAt(1, 2));
		assertEquals(null,objectMap.getObjectAt(2, 0));
		assertEquals(null,objectMap.getObjectAt(2, 1));
		assertEquals(objectMap.getObjectAt(2,2), new Bolt(new Point(2,2)));
	}

	@Test
	public void removeObjectMap() {
		assertEquals(new Bolt(new Point(2,2)),objectMap.getObjectAt(2, 2));
		objectMap.removeObjectAt(2, 2);
		assertEquals(new NullGameObject(new Point(2,2)), objectMap.getObjectAt(2, 2));
	}



}
