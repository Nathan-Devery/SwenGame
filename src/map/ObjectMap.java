package map;

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import loadAndSave.Parser;
import model.gameObjects.GameObject;
import model.gameObjects.NullGameObject;

/***
 * ObjectMap class representing a 2-dimensional array of GameObjects
 *
 * @author fraserhuon
 *
 */
public class ObjectMap implements IObjectMap {
	private final GameObject[][] objectMap;


	/***
	 * Constructor for Object Map, taking a 2D dimension array of GameObjects
	 * @param map
	 */
	public ObjectMap(GameObject[][] map) {
		this.objectMap = map;
	}

	/***
	 * Return the object at the given point
	 */
	@Override
	public GameObject getObjectAt(int x, int y) {
		return objectMap[y][x];
	}

	/***
	 * Remove the object at the given point and replace it with a NullGameObject
	 */
	@Override
	public void removeObjectAt(int x, int y) {
		objectMap[y][x]=new NullGameObject(new Point(x,y));
	}

	/***
	 * Returns the 2D array of GameObjects
	 */
	@Override
	public GameObject[][] getObjectArray() {
		return objectMap;
	}

	/***
	 * Returns the width of this ObjectMap
	 */
	@Override
	public int getWidth() {
		return objectMap[0].length;
	}

	/***
	 * returns the height of this ObjectMap
	 */
	@Override
	public int getHeight() {
		return objectMap.length;
	}



}


