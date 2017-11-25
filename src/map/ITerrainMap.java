package map;

import model.terrain.*;

/***
 * Terrain Map Interface
 *
 * @author fraserhuon
 */
public interface ITerrainMap {
	public Terrain getTerrainAt(int x, int y);

	/***
	 *
	 * @return the terrain map
	 */
	public Terrain[][] getTerrainArray();

	/***
	 *
	 * @return width of the terrain map
	 */
	public int getWidth();

	/***
	 *
	 * @return height of the terrain map
	 */
	public int getHeight();
}
