package tests;

import general.Direction;
import model.gameObjects.Car;
import model.gameObjects.carStrategies.Car1Strategy;
import general.Vector2D;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Huon Fraser on 9/28/2017.
 */
public class TestPlayerStrategy {
    private Vector2D initPos = new Vector2D(0,0);
    Car c = null;
    @Test
    public void TestMoveLeft(){
        assertEquals(initPos, c.getPosition());
        c.move(Direction.LEFT);
        assertEquals(new Vector2D(0,0),c.getVelocity());
        assertEquals(120,c.getDirection());
    }
    @Test
    public void TestMoveRight(){
        assertEquals(initPos, c.getPosition());
        c.move(Direction.RIGHT);
        assertEquals(new Vector2D(0,0),c.getVelocity());
        assertEquals(60,c.getDirection());
    }
    @Test
    public void TestMoveUp(){
        assertEquals(initPos, c.getPosition());
        c.move(Direction.UP);
        assertEquals(new Vector2D(0,1),c.getVelocity());
        assertEquals(90,c.getDirection());
    }
    @Test
    public void TestMoveDown(){
        assertEquals(initPos, c.getPosition());
        c.move(Direction.DOWN);
        assertEquals(new Vector2D(0,0),c.getVelocity());
        assertEquals(90,c.getDirection());
    }

    @Before
    public void initiateNewCar(){
       c =  new Car("player", new Vector2D(0,0),90,100,100,0);
    }


}
