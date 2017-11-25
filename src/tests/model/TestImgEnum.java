package tests.model;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import resources.ImgResources;

/***
 * External testing for Andrjia's code.
 *
 * @author hannahclayton
 *
 */
public class TestImgEnum {
	@Test
	public void testCAR1(){
		assertFalse(ImgResources.CAR1.equals(null));
	}

	@Test
	public void testCAR2(){
		assertFalse(ImgResources.CAR2.equals(null));
	}

	@Test
	public void testCAR3(){
		assertFalse(ImgResources.CAR3.equals(null));
	}

	@Test
	public void testSNOW(){
		assertFalse(ImgResources.SNOW.equals(null));
	}

	@Test
	public void testGRASS(){
		assertFalse(ImgResources.GRASS.equals(null));
	}

	@Test
	public void testDESERT(){
		assertFalse(ImgResources.DESERT.equals(null));
	}

	@Test
	public void testFENCE(){
		assertFalse(ImgResources.FENCE.equals(null));
	}

	@Test
	public void testBARRIER(){
		assertFalse(ImgResources.BARRIER.equals(null));
	}

	@Test
	public void testBOLT(){
		assertFalse(ImgResources.BOLT.equals(null));
	}

	@Test
	public void testCOIN(){
		assertFalse(ImgResources.COIN.equals(null));
	}

	@Test
	public void testTREE(){
		assertFalse(ImgResources.TREE.equals(null));
	}

	@Test
	public void testDIRTROAD(){
		assertFalse(ImgResources.DIRTROAD.equals(null));
	}

	@Test
	public void testFOREST(){
		assertFalse(ImgResources.FOREST.equals(null));
	}

	@Test
	public void testHILLS(){
		assertFalse(ImgResources.HILLS.equals(null));
	}

	@Test
	public void testCHECKPOINT1(){
		assertFalse(ImgResources.CHECKPOINT1.equals(null));
	}

	@Test
	public void testCHECKPOINT2(){
		assertFalse(ImgResources.CHECKPOINT2.equals(null));
	}

	@Test
	public void testCURVE1(){
		assertFalse(ImgResources.CURVE1.equals(null));	}

	@Test
	public void testCURVE2(){
		assertFalse(ImgResources.CURVE2.equals(null));
	}

	@Test
	public void testCURVE3(){
		assertFalse(ImgResources.CURVE3.equals(null));
	}

	@Test
	public void testCURVE4(){
		assertFalse(ImgResources.CURVE4.equals(null));
	}

	@Test
	public void testROCK(){
		assertFalse(ImgResources.ROCK.equals(null));
	}

	@Test
	public void testMAIN(){
		assertFalse(ImgResources.MAIN.equals(null));
	}

	@Test
	public void testFIGHTER(){
		assertFalse(ImgResources.FIGHTER.equals(null));
	}
}
