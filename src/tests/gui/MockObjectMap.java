package tests.gui;

import java.awt.Point;

import map.IObjectMap;
import model.gameObjects.Coin;
import model.gameObjects.GameObject;
import model.gameObjects.NullGameObject;
import model.gameObjects.Rock;

/***
 * Mock object map for testing
 *
 */
public class MockObjectMap implements IObjectMap{
	GameObject[][] map;

	public MockObjectMap(GameObject[][] map) {
		this.map = map;
	}

	@Override
	public GameObject getObjectAt(int x, int y) {
		return map[y][x];
	}
	
	public void setObjectAt(int x, int y, GameObject object){
		map[x][y] = object;
	}

	@Override
	public GameObject[][] getObjectArray() {
		return map;
	}

	@Override
	public int getWidth() {
		return 2;
	}

	@Override
	public int getHeight() {
		return 2;
	}

	@Override
	public void removeObjectAt(int x, int y) {
		map[y][x] = new NullGameObject(new Point(x,y));
	}
}
