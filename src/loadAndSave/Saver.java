package loadAndSave;

import map.IObjectMap;
import map.ITerrainMap;
import model.IGameState;
import model.gameObjects.Car;
import npc.NpcObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import general.CarType;
import general.Stage;

/**
 * Created by Huon Fraser on 10/15/2017.
 *
 * Saver contains methods for saving a game of Sea To Summit Rally Cross
 *	Accompanies parser
 */
public class Saver {

	/***
	 * Save a game state to files with a prefix designated by Parser
	 * @param fileName
	 * @param unlockedStages
	 * @param unlockedCars
	 * @param gs
	 * @throws IOException
	 */
    public static void saveGameState(String fileName, Set<Stage> unlockedStages, Set<CarType> unlockedCars,
                              IGameState gs) throws IOException{
        StringBuilder builder = new StringBuilder();
        builder.append("STAGE ");
        builder.append(gs.getStage().getFileName());
        builder.append("\n");


        Saver.saveCar(gs.getCar(), builder);
        Saver.saveUnlockedCars(unlockedCars,builder);
        Saver.saveUnlockedStages(unlockedStages,builder);

        for(NpcObject npc: gs.getNpcObjects()) {
        	Saver.saveNPC(npc,builder);
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(builder.toString());
        writer.close();
    }

    /***
     * Create a string containing information on the Car c, and append it to
     * the builder
     * @param c
     * @param builder
     */
    public static void saveCar(Car c, StringBuilder builder) {
        builder.append("CAR{\n");
        builder.append("ID ");
        builder.append(c.getCarType().getString());
        builder.append("\n");

        builder.append("POSITION ");
        builder.append(Math.round(c.getPosition().getX()) + "," + Math.round(c.getPosition().getY()));
        builder.append("\n");

        builder.append("VELOCITY ");
        builder.append(Math.round(c.getVelocity().getX()) + "," + Math.round(c.getVelocity().getY()));
        builder.append("\n");

        builder.append("DIRECTION ");
        builder.append(c.getDirection());
        builder.append("\n");

        builder.append("FUEL ");
        builder.append(c.getFuel()*100/c.getStrategy().getMaxFuel());
        builder.append("\n");

        builder.append("HP ");
        builder.append(c.getHP()*100/c.getStrategy().getMaxHP());
        builder.append("\n");

        builder.append("MONEY ");
        builder.append(c.getMoney());
        builder.append("\n");

        builder.append("}\n");
    }
    /***
     * Create a string containing information on the set of unlocked cars, and append it to
     * the builder
     * @param c
     * @param builder
     */
    public static void saveUnlockedCars(Set<CarType> cars, StringBuilder builder) {
        builder.append("UNLOCKEDCARS{\n");
        for(CarType c: cars) {
            builder.append(c.getString());
            builder.append("\n");
        }
        builder.append("}\n");

    }

    /***
     * Create a string containing information on the unlockedStages, and append it to
     * the builder
     * @param c
     * @param builder
     */
    public static void saveUnlockedStages(Set<Stage> stages, StringBuilder builder) {
        builder.append("UNLOCKEDSTAGES{\n");
        for(Stage stage: stages) {
            builder.append(stage.getFileName());
            builder.append("\n");
        }
        builder.append("}\n");
    }

    /***
     * Create a string containing information on the Object Map, and save it to its
     * own file
     * @param c
     */
    public static void saveObjectMap(String s, IObjectMap map) throws IOException {
        StringBuilder builder = new StringBuilder();
        builder.append(map.getWidth());
        builder.append(" ");
        builder.append(map.getHeight());
        builder.append("\n");
        for (int i = 0; i < map.getHeight(); i++)// for row
        {
            for (int j = 0; j < map.getWidth(); j++)// for column
            {
                builder.append(map.getObjectAt(j,i).getID());// append to the output string
                if (j < map.getWidth() - 1) {// if not last row element
                    builder.append(" ");// then add comma}
                }
            }
            if(i < map.getHeight()-1) {
                builder.append("\n");// append new line at the end of the row
            }
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(s));
        writer.write(builder.toString());// save the string representation of the board
        writer.close();
    }

    /***
     * Create a string contaiing information on the TerrainMap, and save it to its own file
     * @param fileName
     * @param map
     * @throws IOException
     */
    public static void saveTerrainMap(String fileName, ITerrainMap map) throws IOException {
        StringBuilder builder = new StringBuilder();
        builder.append(map.getWidth());
        builder.append(" ");
        builder.append(map.getHeight());
        builder.append("\n");
        for (int i = 0; i < map.getHeight(); i++)// for row
        {
            for (int j = 0; j < map.getWidth(); j++)// for column
            {
                builder.append(map.getTerrainAt(j, i).getID());// append to the output string
                if (j < map.getWidth() - 1) {// if not last row element
                    builder.append(" ");// then add comma
                }
            }
            if (i < map.getHeight() - 1) {
                builder.append("\n");// append new line at the end of the row
            }
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(builder.toString());// save the string representation of the board
        writer.close();
    }


    /***
     * Create a string containing information on an NPC, and append it to
     * the builder
     * @param c
     * @param builder
     */
    public static void saveNPC(NpcObject npc, StringBuilder builder){
        builder.append("NPC{\n");

        builder.append("POSITION ");
        builder.append(Math.round(npc.getPosition().getX()) + "," + Math.round(npc.getPosition().getY()));
        builder.append("\n");

        builder.append("VELOCITY ");
        builder.append(Math.round(npc.getVelocity()));
        builder.append("\n");

        builder.append("DIRECTION ");
        builder.append(npc.getDirection());
        builder.append("\n");

        builder.append("ATTACK ");
        builder.append(npc.getAttack());
        builder.append("\n");

        builder.append("}\n");
    }




}
