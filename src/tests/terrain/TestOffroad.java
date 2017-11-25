package tests.terrain;

import static org.junit.Assert.*;

import org.junit.Test;

import general.Stage;
import model.terrain.*;
import resources.ImgResources;

public class TestOffroad {

	//Test naming convention: name_StateUnderTest_ExpectedBehavior
	@Test
	public void speedMofider_NewObject_point5() {
		Terrain road = new model.terrain.OffRoad();
		assertEquals(road.getSpeedModifer(), 0.5, 0.000009);
	}
	
	@Test
	public void getImageEnum_Snow_SnowEnum() {
		Terrain road = new model.terrain.OffRoad();
		assertEquals(road.getImageEnum(Stage.SNOW), ImgResources.SNOW);
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
		
	
}
