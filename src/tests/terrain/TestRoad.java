package tests.terrain;

import static org.junit.Assert.*;

import org.junit.Test;

import general.Stage;
import model.terrain.*;
import resources.ImgResources;

public class TestRoad {

	//Test naming convention: name_StateUnderTest_ExpectedBehavior
	@Test
	public void speedMofider_NewObject_point5() {
		Terrain road = new model.terrain.Road();
		assertEquals(road.getSpeedModifer(), 0.0, 0.000009);
	}
	
	@Test
	public void getImageEnum_GRASS_SnowEnum() {
		Terrain road = new model.terrain.Road();
		assertEquals(road.getImageEnum(Stage.SNOW), ImgResources.DIRTROAD);
	}
	
	//TODO Finish testing as more textures added
	/*
	@Test
	public void getImageEnum_Hills_HillsEnum() {
		Terrain road = new model.terrain.OffRoad();
		assertEquals(road.getImageEnum(Stage.HILLS), ImgResources.HILLS);
	}
	
	@Test
	public void getImageEnum_Grass_GrassEnum() {
		Terrain road = new model.terrain.OffRoad();
		assertEquals(road.getImageEnum(Stage.GRASS), ImgResources.GRASS);
	}
	
	@Test
	public void getImageEnum_Forest_ForestEnum() {
		Terrain road = new model.terrain.OffRoad();
		assertEquals(road.getImageEnum(Stage.FOREST), ImgResources.FOREST);
	}
	
	@Test
	public void getImageEnum_Desert_DesertEnum() {
		Terrain road = new model.terrain.OffRoad();
		assertEquals(road.getImageEnum(Stage.DESERT), ImgResources.DESERT);
		
	}
	*/
		
	
}
