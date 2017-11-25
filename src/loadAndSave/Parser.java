package loadAndSave;

import map.ObjectMap;
import map.TerrainMap;
import model.GameState;
import model.gameObjects.*;
import model.terrain.*;
import npc.NpcObject;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;

import general.CarType;
import general.Stage;
import general.Vector2D;

/**
 * Created by Huon Fraser on 10/13/2017.
 *
 *
 * Parser contains methods for all the parsing required in Sea to Summit RallyCross
 *	Accompanies Saver
 */
public class Parser {
    //these patterns are copied from the code provided for COMP261 Assig 4
    static Pattern NUMPAT = Pattern.compile("-?[1-9][0-9]*|0");
    static Pattern OPENPAREN = Pattern.compile("\\(");
    static Pattern CLOSEPAREN = Pattern.compile("\\)");
    static Pattern OPENBRACE = Pattern.compile("\\{");
    static Pattern CLOSEBRACE = Pattern.compile("\\}");
    static Pattern VAR = Pattern.compile("\\$[A-Za-z][A-Za-z0-9]*");


    /***
     *Parse and return a GameState object from a set of text files given by the parameter
     *
     * @param stageString. the prefix for a file name
     * @return
     */
    public static GameState parseGameState(String stageString) {
        try {
            File terrainMapFile = new File("src/files/" + stageString+ "TerrainMap.txt");
            File objectMapFile = new File("src/files/" +stageString+ "ObjectMap.txt");
            File metaDataFile = new File("src/files/" +stageString+ "MetaData.txt");

            Scanner s = new Scanner(metaDataFile);
            s.useDelimiter("\\s+|(?=[{}(),;])|(?<=[{}(),;])");
            Car car = null;
            List<NpcObject> npcs = new ArrayList<NpcObject>();
            Stage stage = null;
            String token = null;
            Set<Stage> unlockedStages = new HashSet<Stage>();
            Set<CarType> unlockedCars = new HashSet<CarType>();
            TerrainMap terrainMap= Parser.parseTerrainMap(terrainMapFile);
            ObjectMap objectMap = Parser.parseObjectMap(objectMapFile);


            while (s.hasNext()) {
                token = s.next();
                if(token.equals("CAR")){
                    car = parseCar(s);
                }else if(token.equals("NPC")){
                    npcs.add(parseNPCObject(s));
                }else if(token.equals("STAGE")){
                    stage = parseStage(s);
                }else if(token.equals("UNLOCKEDCARS")){
                    unlockedCars= parseUnlockedCars(s);
                }else if(token.equals("UNLOCKEDSTAGES")){
                    unlockedStages= parseUnlockedStages(s);
                }else{
                    throw new IOException("Invalid input file");
                }
            }
            s.close();
            return new GameState(stage,terrainMap,objectMap, car,unlockedCars,unlockedStages,npcs);

        }catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /***
     * Parse and return a car object from the given scanner
     * Assumes that the next token in scanner is the open bracket for the car data
     * @param s
     * @return
     */
    public static Set<CarType> parseUnlockedCars(Scanner s){
        Set<CarType> unlockedCars =new HashSet<>();
        String token = getNextToken(s);
        if(!token.equals("{")){
            throw new Error("Invalid set of car types, required {, instead parsed:" + token);

        }
        while(!s.hasNext("}")){
            unlockedCars.add(parseCarType(s));
        }
        token = getNextToken(s);
        if(!token.equals("}")){
            throw new Error("Invalid set of car types, required }, instead parsed:" + token);

        }
        return unlockedCars;
    }

    /***
     * Parse and return the set of unlocked stages  from the given scanner
     * Assumes the next token corresponds is the open brace for the stage data
     * @param s
     * @return
     */
    public static Set<Stage> parseUnlockedStages(Scanner s){
        Set<Stage> unlockedStages =new HashSet<>();
        String token = getNextToken(s);
        if(!token.equals("{")){
            throw new Error("Invalid set of stage types, required {, instead parsed:" + token);

        }
        while(!s.hasNext("}")){
            unlockedStages.add(parseStage(s));
        }
        token = getNextToken(s);
        if(!token.equals("}")){
            throw new Error("Invalid set of car types, required }, instead parsed:" + token);

        }
        return unlockedStages;
    }

    /***
     * Parse and return a Car object from the given scanner
     * Assumes that the following tokens belong to a car object and
     * that the first token is an open brace
     *
     * This method initiates default values which are returned if no
     * values for parameters are given
     * @param s
     * @return
     */
    public static Car parseCar(Scanner s){
        int direction = 0;
        CarType id = CarType.CAR1;
        Vector2D position = new Vector2D(0,0);
        Vector2D velocity = new Vector2D(0,0);
        int fuel = 100;
        int hp = 100;
        int money = 0;

        String token = s.next();
        if(!token.equals("{")){
            throw new Error("Expected {: instead got" + token);
        }

        //first par
        while((token = getNextToken(s)) != null){
            if(token.equals("POSITION")){
                position = parseNextVector(s);
            }else if(token.equals("VELOCITY")){
                velocity = parseNextVector(s);
            }else if(token.equals("DIRECTION")){
                direction = parseNextInt(s);
            }else if(token.equals("FUEL")){
                fuel = parseNextInt(s);
            }else if(token.equals("HP")){
                hp = parseNextInt(s);
            }else if(token.equals("MONEY")){
                money = parseNextInt(s);
            }else if(token.equals("ID")){
                id = parseCarType(s);
            }else if(token.equals("}")){
                return new Car(id,position,velocity,direction,fuel,hp,money);
            }else{
                throw new Error("Illegal car input: " + s);
            }
        }
        //if this point is reached the input file was invalid
        throw new Error("Illegal car input, car not closed");
    }

    /***
     * Parse and returns an NpcObject from the given scanner
     *  Assumes that the following tokens belong to an npc object and
     * that the first token is an open brace
     * @param s
     * @return
     */
    public static NpcObject parseNPCObject(Scanner s){
        int direction = 0;
        Vector2D position = new Vector2D(0,0);
        int velocity = 1;
        int attack = 1;

        String token = s.next();
        if(!token.equals("{")){
            throw new Error("Expected {: instead got" + token);
        }

        //first par
        while((token = getNextToken(s)) != null){
            if(token.equals("POSITION")){
                position = parseNextVector(s);
            }else if(token.equals("VELOCITY")){
                velocity = parseNextInt(s);
            }else if(token.equals("DIRECTION")){
                direction = parseNextInt(s);
            }else if(token.equals("ATTACK")){
                attack = parseNextInt(s);
            }else if(token.equals("}")){
                return new NpcObject(position,velocity,direction,attack);
            }else{
                throw new Error("Illegal npc input: " + s);
            }
        }
        throw new Error("Illegal npc input, npc not closed");
    }

    public static Stage parseStage(Scanner scanner){
        String s = getNextToken(scanner);
        if(s.equals("desert")) {
            return Stage.DESERT;
        }else if(s.equals("snow")) {
            return Stage.SNOW;
        }else if(s.equals("forest")) {
            return Stage.FOREST;
        }
        return null;
    }

    /***
     *Parses and returns a CarType from the scanner, or null
     *if the next token doesn't correspond to a CarType
     * @param scanner
     * @return
     */
    public static CarType parseCarType(Scanner scanner){
        String s = getNextToken(scanner);
        if(s.equals("car1")) {
            return CarType.CAR1;
        }else if(s.equals("car2")) {
            return CarType.CAR2;
        }else if(s.equals("car3")) {
            return CarType.CAR3;
        }
        return null;
    }



     /***
      * Return a Vector2D object
      * Expects following tokens to be of the form x,y
      * @param s
      * @return
      */
    public static Vector2D parseNextVector(Scanner s){
        int first = parseNextInt(s);
        String seperator = s.next();
        if(!seperator.equals(",")){
            throw new Error("Invalid vector inputted");
        }
        int second = parseNextInt(s);
        return new Vector2D(first,second);
    }

    /***
     * Parses and returns the next token from the scanner
     * @param s
     * @return
     */
    private static String getNextToken(Scanner s){
        String token = s.next();
        token.trim();
        return  token;
    }

    /***
     * Parses and returns the next token as an Int
     * @param s
     * @return
     */
    private static  int parseNextInt(Scanner s){
        String string = getNextToken(s);
        int i = Integer.parseInt(string);
        return i;
    }

    /***
     * Parses and returns the next token as a Double
     * @param s
     * @return
     */
    public static double parseNextDouble(Scanner s){
    	String string = getNextToken(s);
    	double i = Double.parseDouble(string);
        return i;
    }

    /***
     * Parser to parse in a game object at coordinate x,y of the given
     * object ID.
     *
     * @param id
     * @param x
     * @param y
     * @return new object, dependent of the letter parsed.
     */
    public static GameObject parseGameObject(String id, int x, int y) {
        char c = id.toCharArray()[0];

        switch(c) {
            case 'C':
                return new CheckPoint(new Point(x,y));
            case 'E':
                return new Bolt(new Point(x,y));
            case 'F':
                return new Fence(new Point(x,y));
            case 'R':
                return new Rock(new Point(x,y));
            case 'T':
                return new Tree(new Point(x,y));
            case 'B':
                return new Barrier(new Point(x,y));
            case 'N':
                return new Coin(new Point(x,y));
            default:
                return new NullGameObject(new Point(x,y));
        }
    }

    /***
     * Parser for terrain type, returning a Terrain tile based on the
     * given string
     *
     * @param string ID of tile
     * @return terrain type
     */
    public static Terrain parseTerrain(String string){
        char id = string.trim().toCharArray()[0];

        switch(id){
            case 'O':
                return new OffRoad();
            case 'R':
                return new Road();
            case 'U':
                return new Unreachable();
            case 'S':
                return new Scenery();
            default:
                return new Scenery();
        }
    }

    /***
     * Parser for CarType, returning a CarType enum value
     * based on the string given
     * @param s
     * @return
     */
    public static CarType getCarType(String s) {
        if(s.equals("car1")) {
            return CarType.CAR1;
        }else if(s.equals("car2")) {
            return CarType.CAR2;
        }else if(s.equals("car3")) {
            return CarType.CAR3;
        }
        return null;
    }


    /***
     * Parser for Stage, returning a Stage enum value
     * based on the string given
     * @param s
     * @return
     */
    public static Stage parseStage(String s) {
        if(s.equals("desert")) {
            return Stage.DESERT;
        }else if(s.equals("snow")) {
            return Stage.SNOW;
        }else if(s.equals("forest")) {
            return Stage.FOREST;
        }
        return null;
    }

    /***
     * Parse and return a new TerrainMap
     * @param file, the file to load
     * @return
     */
	public static TerrainMap parseTerrainMap(File file) {
		Scanner s = null;
		Terrain[][] map = null;
		try {
			s= new Scanner(file);
			String metaData = s.nextLine();
			String[] split = metaData.split(" ");
			if(split.length!=2) {
				throw new Error("Illegal metaData for TerrainMap:" + metaData);
			}
			int width = Integer.parseInt(split[0]);
			int height = Integer.parseInt(split[1]);

			map = new Terrain[height][width];
			int y = 0;
			while(s.hasNextLine()){
				String line = s.nextLine();
				split = line.split(" ");
				if(split.length!=width) {
					throw new RuntimeException("Invalid width specified for terrain map");
				}
				for(int x =0; x< width;x++) {
					map[y][x]= Parser.parseTerrain(split[x]);
				}
				y++;
			}
			if(y!=height) {
				throw new RuntimeException("Invalid height specified for terrain map");

			}
			return new TerrainMap(map);
		}catch(FileNotFoundException e) {
			throw new Error("Illegal Terrain Map");
		}

	}


    /***
     * Parse and return a new ObjectMap
     * @param file, the file to load
     * @return
     */
	public static ObjectMap parseObjectMap(File file) {
		Scanner s = null;
		GameObject[][] map = null;
		try {
			s= new Scanner(file);
			String metaData = s.nextLine();
			String[] split = metaData.split(" ");
			if(split.length!=2) {
				//TODO
			}
			int width = Integer.parseInt(split[0]);
			int height = Integer.parseInt(split[1]);
			map = new GameObject[height][width];
			int y = 0;
			while (s.hasNextLine()) {
				String line = s.nextLine();
				split = line.split(" ");
				if (split.length != width) {
					throw new RuntimeException("Invalid width specified for object map");
				}
				for (int x = 0; x < width; x++) {
					map[y][x] = Parser.parseGameObject(split[x], x, y);
					if(map[y][x]==null) {
					}
				}
				y++;
			}
			if (y != height) {
				throw new RuntimeException("Invalid height specified for object map");
			}
			return new ObjectMap(map);
		}catch(FileNotFoundException e) {
			throw new Error("Illegal Terrain Map");
		}

	}


}
