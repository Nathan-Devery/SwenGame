package tests.model;

import general.CarType;
import general.Stage;
import general.State;
import model.Model;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Huon Fraser on 10/15/2017.
 */
public class TestModel {
    Model m = null;
    @Test
    public void testFinishStage1(){
        assertEquals(1, m.getUnlockedStages().size());
        assertEquals(true,m.getUnlockedStages().contains(Stage.DESERT));
        assertEquals(false,m.getUnlockedStages().contains(Stage.FOREST));
        assertEquals(false,m.getUnlockedStages().contains(Stage.SNOW));
        m.finishStage(Stage.DESERT);
        assertEquals(2, m.getUnlockedStages().size());
        assertEquals(State.MENU,m.getState());
    }

    @Test
    public void testFinishStage2(){
        assertEquals(1, m.getUnlockedStages().size());
        assertEquals(true,m.getUnlockedStages().contains(Stage.DESERT));
        assertEquals(false,m.getUnlockedStages().contains(Stage.FOREST));
        assertEquals(false,m.getUnlockedStages().contains(Stage.SNOW));
        m.finishStage(Stage.FOREST);
        assertEquals(2, m.getUnlockedStages().size());
        assertEquals(State.MENU,m.getState());
    }
    @Test
    public void testFinishStage3(){
        assertEquals(1, m.getUnlockedStages().size());
        assertEquals(true,m.getUnlockedStages().contains(Stage.DESERT));
        assertEquals(false,m.getUnlockedStages().contains(Stage.FOREST));
        assertEquals(false,m.getUnlockedStages().contains(Stage.SNOW));
        m.finishStage(Stage.SNOW);
        assertEquals(1, m.getUnlockedStages().size());
        assertEquals(State.VICTORY,m.getState());
    }
    @Test
    public void testFinishStageAll(){
        for(Stage s : Stage.values()){
            m.finishStage(s);
        }
        assertEquals(3, m.getUnlockedStages().size());
        assertEquals(State.VICTORY,m.getState());
    }


    @Test
    public void testLoadLockedStage(){
        assertEquals(State.MENU,m.getState());
        assertEquals(false,m.getUnlockedStages().contains(Stage.FOREST));
        m.load(Stage.FOREST);
        assertEquals(State.MENU,m.getState());
    }
    @Test
    public void testLoadUnlockedStage(){
        assertEquals(State.MENU,m.getState());
        assertEquals(true,m.getUnlockedStages().contains(Stage.DESERT));
        m.load(Stage.DESERT);
        assertEquals(State.RUNNING,m.getState());
    }

    /**
     * Car1 should always be unlocked, therefore unlocking it should do nothing
     */
    @Test
    public void testUnlockCar1(){
        m.setMoney(5);
        assertEquals(5,m.getMoney());
        assertEquals(1,m.getUnlockedCars().size());
        m.unlockCar(CarType.CAR1);
        assertEquals(5, m.getMoney());
        assertEquals(1,m.getUnlockedCars().size());
    }
    @Test
    public void testUnlockCar2Valid(){
        m.setMoney(5);
        assertEquals(5,m.getMoney());
        assertEquals(1,m.getUnlockedCars().size());
        m.unlockCar(CarType.CAR2);
        assertEquals(0, m.getMoney());
        assertEquals(2,m.getUnlockedCars().size());
        assertEquals(true,m.getUnlockedCars().contains(CarType.CAR2));
    }
    @Test
    public void testUnlockCar2Invalid(){
        m.setMoney(4);
        assertEquals(4,m.getMoney());
        assertEquals(1,m.getUnlockedCars().size());
        m.unlockCar(CarType.CAR2);
        assertEquals(4, m.getMoney());
        assertEquals(1,m.getUnlockedCars().size());
        assertEquals(false,m.getUnlockedCars().contains(CarType.CAR2));
    }
    @Test
    public void testUnlockCar3Valid(){
        m.setMoney(15);
        assertEquals(15,m.getMoney());
        assertEquals(1,m.getUnlockedCars().size());
        m.unlockCar(CarType.CAR3);
        assertEquals(0, m.getMoney());
        assertEquals(2,m.getUnlockedCars().size());
        assertEquals(true,m.getUnlockedCars().contains(CarType.CAR3));
    }
    @Test
    public void testUnlockCar3Invalid(){
        m.setMoney(4);
        assertEquals(4,m.getMoney());
        assertEquals(1,m.getUnlockedCars().size());
        m.unlockCar(CarType.CAR3);
        assertEquals(4, m.getMoney());
        assertEquals(1,m.getUnlockedCars().size());
        assertEquals(false,m.getUnlockedCars().contains(CarType.CAR3));
    }
    @Test
    public void testUnlockCarsAll(){
        m.setMoney(21);
        for(CarType ct : CarType.values()){
            m.unlockCar(ct);
        }
        assertEquals(CarType.values().length,m.getUnlockedCars().size());
        assertEquals(1,m.getMoney());
    }

    /**
     * Note this isn't independantly testable, there was no time to interface and mock parser.
     * The important thing is to test that money/unlockedStages/unlocked Cars are updated
     */
    @Test
    public void testLoadSave(){
        String save = "STAGE forest\n" +
                "CAR{\n" +
                "MONEY 0\n" +
                "}\n" +
                "UNLOCKEDCARS{\n" +
                "car1\n" +
                "car2\n" +
                "}\n" +
                "UNLOCKEDSTAGES{\n" +
                "desert\n" +
                "forest\n" +
                "}\n";
        //load this save ?? how ?? do i need to write??
        //assert money, stages, cars correct


    }

    /**
     * Note this isn't independantly testable
     */
    @Test
    public void testFuelDefeat(){
       m.load(Stage.DESERT);
        m.getGameState().getCar().setFuel(0);
        m.ping();
        assertEquals(State.DEFEAT,m.getState());
    }

    /**
     * Note this isn't independantly testable
     */
    @Test
    public void testHPDefeat(){
        m.load(Stage.DESERT);
        m.getGameState().getCar().setHP(0);
        m.ping();
        assertEquals(State.DEFEAT,m.getState());
    }

    @Test
    public void testSetCurrentCarLockedNotEnoughMoney(){
        assertEquals(false,m.getUnlockedCars().contains(CarType.CAR2));
        assertEquals(0,m.getMoney());
        assertEquals(CarType.CAR1,m.getCarType());
        m.setCurrentCar(CarType.CAR2);
        assertEquals(false,m.getUnlockedCars().contains(CarType.CAR2));
        assertEquals(CarType.CAR1,m.getCarType());
    }

    @Test
    public void testSetCurrentCarLockedWithEnoughMoney(){
        assertEquals(false,m.getUnlockedCars().contains(CarType.CAR2));
        m.setMoney(5);
        assertEquals(5,m.getMoney());
        assertEquals(CarType.CAR1,m.getCarType());
        m.setCurrentCar(CarType.CAR2);
        assertEquals(true,m.getUnlockedCars().contains(CarType.CAR2));
        assertEquals(CarType.CAR2,m.getCarType());
    }

    @Test
    public void testSetCurrentCarUnlocked(){
        m.getUnlockedCars().add(CarType.CAR2);
        assertEquals(true,m.getUnlockedCars().contains(CarType.CAR2));
        m.setCurrentCar(CarType.CAR2);
        assertEquals(CarType.CAR2,m.getCarType());

    }


    @Before
    public void initiate(){
        m = new Model();
    }
}
