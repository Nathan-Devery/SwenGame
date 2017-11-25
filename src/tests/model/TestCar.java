package tests.model;


import general.CarType;
import general.Vector2D;
import model.gameObjects.Car;
import org.junit.Before;
import org.junit.Test;
import resources.ImgResources;

import java.awt.*;
import java.util.Vector;

import static org.junit.Assert.assertEquals;


/**
 * Created by Huon Fraser on 9/27/2017.
 */
public class TestCar {
    private Vector2D initPos = new Vector2D(0,0);
    private double terminalVelocity;
    Car c = null;

    /**
     * Initate car with velocity 0,0 facing north
     * Accelerating car give velocity (0,-1)
     */
    @Test
    public void testAccelerateFromZero(){
        assertEquals(new Vector2D(0,0),c.getVelocity());
        assertEquals(0,c.getDirection());
        c.accelerate(1);
        assertEquals(new Vector2D(0,-((double)1)/ImgResources.imgDimension),c.getVelocity());
    }
    @Test
    public void testDecelerateFromZero(){
        assertEquals(new Vector2D(0,0),c.getVelocity());
        c.decelerate(1);
        assertEquals(new Vector2D(0,0),c.getVelocity());
    }

    /**
     * Initate car with velocity 0,0 facing north
     * Accelerating car give velocity (0,-1)
     */
    @Test
    public void testAccelerateFromTerminal(){
        for(int i  =0; i < terminalVelocity*ImgResources.imgDimension; i++){
            c.accelerate(1);
        }
        assertEquals(new Vector2D(0,-terminalVelocity),c.getVelocity());


        c.accelerate(1);
        assertEquals(new Vector2D(0,-terminalVelocity),c.getVelocity());
    }
    @Test
    public void testDecelerateFromTerminal(){
        for(int i  =0; i < terminalVelocity*ImgResources.imgDimension; i++){
            c.accelerate(1);
        }
        assertEquals(new Vector2D(0,-terminalVelocity),c.getVelocity());
        c.decelerate(1);
        assertEquals(new Vector2D(0,-terminalVelocity+1.0/ ImgResources.imgDimension),c.getVelocity());
    }

    //methods to test acceleration, declerations when rotated in other directions
    @Test
    public void testAccelerateAlongXAxis(){
        c.turnRight(90);
        assertEquals(new Vector2D(0,0),c.getVelocity());
        assertEquals(90, c.getDirection());
        c.accelerate(1);
        assertEquals(new Vector2D(1.0/ImgResources.imgDimension,0),c.getVelocity());
        c.decelerate(1);
        assertEquals(new Vector2D(0,0),c.getVelocity());

    }

    @Test
    public void testAccelerateAlongXYAxis(){
        c.turnRight(45);
        assertEquals(45,c.getDirection());
        assertEquals(new Vector2D(0,0),c.getVelocity());
        c.accelerate(Math.sqrt(2));
        assertEquals(new Vector2D(1.0/ImgResources.imgDimension,-1.0/ImgResources.imgDimension),c.getVelocity());
        c.decelerate(Math.sqrt(2));
        assertEquals(new Vector2D(0,0),c.getVelocity());
    }

    @Test
    public void testRotateRightVelocityZero(){
        c.turnRight(90);
        assertEquals(90,(int)Math.round(c.getDirection()));
        assertEquals(new Vector2D(0,0),c.getVelocity());

    }
    @Test
    public void testRotateLeftVelocityZero(){
        c.turnLeft(90);
        assertEquals(270,(int)Math.round(c.getDirection()));
        assertEquals(new Vector2D(0,0),c.getVelocity());
    }
    @Test
    public void testRotateRightVelocity0ne(){
        c.accelerate(1);
        c.turnRight(90);
        assertEquals(90,(int)Math.round(c.getDirection()));
        assertEquals(new Vector2D(1.0/ImgResources.imgDimension,0),c.getVelocity());

    }
    @Test
    public void testRotateLeftVelocity0ne(){
        c.accelerate(1);
        c.turnLeft(90);
        assertEquals(270,(int)Math.round(c.getDirection()));
        assertEquals(new Vector2D(-1.0/ImgResources.imgDimension,0),c.getVelocity());
    }

    @Test
    public void testPingVelocityZero(){
        Vector2D posBefore = c.getPosition();
        c.ping();
        assertEquals(posBefore,c.getPosition());
    }
    @Test
    public void testPingVelocityOne(){
        Vector2D posBefore = c.getPosition();
        c.accelerate(1);
        c.ping();
        Vector2D expectedPosAfter = new Vector2D(posBefore.getX(),posBefore.getY()-1.0/ImgResources.imgDimension);
        assertEquals(expectedPosAfter,c.getPosition());

    }

   /* @Test
    public void testParsePlayerCar(){
        String s = ("player\n" +
                "1,1\n" +
                "0\n" +
                "50\n" +
                "51\n" +
                "52");

        Car c = MapParser.parseCar();
        assertEquals("player", c.getID());
        assertEquals(new Vector2D(1,1), c.getPosition());
        assertEquals(0, c.getDirection());
        assertEquals(50, c.getFuel());
        assertEquals(51, c.getHP());
        assertEquals(52, c.getMoney());
    } */


    @Test
    public void testBoostLength1(){

        c.accelerate(1);
        c.boost(1);
        c.ping();
        assertEquals(new Vector2D(0,-0.04),c.getPosition());
        c.ping();
        assertEquals(new Vector2D(0,-0.06),c.getPosition());
    }
    @Test
    public void testBoostLength0(){

        c.accelerate(1);
        c.boost(0);
        c.ping();
        assertEquals(new Vector2D(0,-0.02),c.getPosition());
    }

    @Test
    public void testFuel(){
        assertEquals(c.getStrategy().getMaxFuel(),c.getFuel());
        for(int i = 0; i <Car.FUELINT; i++){
            c.ping();
            if(i<Car.FUELINT-1){
                assertEquals(100,c.getFuel());
            }
        }
        assertEquals(c.getStrategy().getMaxFuel()-1,c.getFuel());
    }

    @Test
    public void testCollision(){

        Car other = new Car();
        assertEquals(100,other.getHP());
        c.collide(other);
        assertEquals(90,other.getHP());
        assertEquals(100,c.getHP());
    }

    @Test
    public void testSetCarType(){
        c.setCarType(CarType.CAR2);
        assertEquals(CarType.CAR2,c.getCarType());
    }

    @Test
    public void testDecelerationFacingNorth(){
        c.accelerate(0.5);
        c.ping();
        assertEquals(new Vector2D(0,-0.5/50), c.getPosition());
        assertEquals(new Vector2D(0,-0.5/50),c.getVelocity());
        c.decelerate(1);
        assertEquals(new Vector2D(0,0),c.getVelocity());
    }
    @Test
    public void testDecelerationFacingSouth(){
        c.turnRight(180);
        assertEquals(180,c.getDirection());
        c.accelerate(0.5);
        c.ping();
        assertEquals(new Vector2D(0,0.5/50), c.getPosition());
        assertEquals(new Vector2D(0,0.5/50),c.getVelocity());
        c.decelerate(1);
        assertEquals(new Vector2D(0,0),c.getVelocity());
    }
    @Test
    public void testDecelerationFacingEast(){
        c.turnRight(90);
        assertEquals(90,c.getDirection());
        c.accelerate(0.5);
        c.ping();
        assertEquals(new Vector2D(0.5/50,0), c.getPosition());
        assertEquals(new Vector2D(0.5/50,0),c.getVelocity());
        c.decelerate(1);
        assertEquals(new Vector2D(0,0),c.getVelocity());
    }
    @Test
    public void testDecelerationFacingWest(){
        c.turnLeft(90);
        assertEquals(270,c.getDirection());
        c.accelerate(0.5);
        c.ping();
        assertEquals(new Vector2D(-0.5/50,0), c.getPosition());
        assertEquals(new Vector2D(-0.5/50,0),c.getVelocity());
        c.decelerate(1);
        assertEquals(new Vector2D(0,0),c.getVelocity());
    }

    @Test
    public void testBoundingBox(){
        Rectangle carRect = c.getBoundingBox();
        Rectangle expected = new Rectangle(-25,-25,50,50);
        assertEquals(expected.x,carRect.x);
        assertEquals(expected.y,carRect.y);
        assertEquals(expected.width,carRect.width);
        assertEquals(expected.height,carRect.height);
    }



    @Before
    public void initiateNewCar(){
        c =  new Car(CarType.CAR1, new Vector2D(0,0), new Vector2D(0,0),0,100,100,0);
        terminalVelocity = c.getStrategy().getTerminalVelocity();
    }

}
