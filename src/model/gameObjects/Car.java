package model.gameObjects;

import model.carStrategies.*;
import resources.ImgResources;

import java.awt.Point;
import java.awt.Rectangle;

import general.CarType;
import general.Direction;
import general.Stage;
import general.Vector2D;

/**
 * Created by Huon Fraser on 9/21/2017.
 */
public class Car extends GameObject {
	private String objectName = "Car";
    private AbstractCarStrategy strategy;
    private Vector2D position;
    private Vector2D velocity;
    private int direction; //direction is represented as an int from 0 - 360, where  0 = north, 90 = east, 180=south, 270=west
    //need field to keep track of checkpoints

    private int fuel; //fuel is an int value from 1-100
    private int hp; //hp is an int value from 1-100
    private int money;
    private String ID;
    private CarType carType;

    public static final int  FUELINT = 50;
    private int fuelTimer = FUELINT;

    public boolean finished;

    private boolean boost;
    private int boostTimer;

    /***
     *Default Car Constructor
     */
    public Car() {
        this.carType  = CarType.CAR1;
    	ID="car";
    	position = new Vector2D(0,0);
    	velocity = new Vector2D(0,0);	//Added
        this.strategy=AbstractCarStrategy.strategyFactory(carType);
    	this.fuel = strategy.getMaxFuel()*1;
    	direction = 0;
    	this.hp = strategy.getMaxHP()*1;
    	money=0;
    }

	/***
	 * Entirely custom position
	 *
	 * @param id
	 * @param position
	 * @param direction
	 * @param fuel
	 * @param hp
	 * @param money
	 */
    public Car(CarType id, Vector2D position, Vector2D velocity, int direction, int fuel, int hp, int money){
    	this.carType=id;
        this.position=position;
        this.direction = direction;
        this.strategy=AbstractCarStrategy.strategyFactory(id);
        this.fuel = strategy.getMaxFuel()*fuel/100;
        this.hp=strategy.getMaxHP()*hp/100;
        this.money=money;
        this.velocity=velocity;
    }

    /***
     * If a car collides with another car, decrease the hitpoints of it by 10
     *
     * @param c is the car that is colliding with the object.
     */
    @Override
    public void collide(Car c) {
    	c.setHP(c.getHP()-10);
        return;
    }

    /***
     * Set the cars strategy to the given strategy
     * @param strategy
     */
    public void setStrategy(AbstractCarStrategy strategy){
        this.strategy=strategy;
    }


    /***
     * The ping command for this car
     * Currently only updates the position based on velocity
     * In future something like use fuel every 10th ping
     */
    public void ping() {

    	ping(1);
    }
    public void ping(double d) {
    	fuelTimer--;
        boostTimer--;

        if(boostTimer<0 ){
            boost=false;
        }

    	if(fuelTimer==0) {
    		fuel--;
    		fuelTimer=FUELINT;
    	}
    	position=mockPing(d);

    }

    /*** Call the move command for this car
     * Actions for each direction are determined by the strategy
     * e.g. rates of turning or acceleration
     * @param d
     */
    public void move(Direction d) {
    	strategy.move(d,this);
    }

    public Vector2D mockPing(double d){
        double posX = position.getX();
        double posY = position.getY();

        double velocityX = velocity.getX()*d;
        double velocityY = velocity.getY()*d;

        if(boost){
            velocityX*=2;
            velocityY*=2;
        }

        double newX = posX+velocityX;
        double newY = posY+velocityY;
        return new Vector2D(newX,newY);
    }

    /***
     * Increment the cars velocity by 1*scale
     * @param scale
     */
    public void accelerate(double scale){
        double radians = Math.toRadians(direction);
        double deltaX = +(1*scale*Math.sin(radians))/50;
        double deltaY = -(1*scale*Math.cos(radians))/50;

        double newVelocityX = velocity.getX()+ deltaX;
        double newVelocityY = velocity.getY()+ deltaY;
        Vector2D newVector = new Vector2D(newVelocityX,newVelocityY);
        double magnitude = newVector.getMagnitude();
        if(magnitude>strategy.getTerminalVelocity()) {
        	newVector = newVector.getUnitVector().scalarMultiply(strategy.getTerminalVelocity());
        }
        this.velocity = newVector;
    }

    /***
     * Deccrement the cars velocity by 1*scale
     * @param scale
     */
    public void decelerate(double scale){
    	 double radians = Math.toRadians(direction);
         double deltaX = +(1*scale*Math.sin(radians))/50;
         double deltaY = -(1*scale*Math.cos(radians))/50;
        double newVelocityX = velocity.getX()- deltaX;
        double newVelocityY = velocity.getY()- deltaY;

        if(direction < 90 ||  direction >270){
            if(newVelocityY>0){
                newVelocityY=0;
            }
        }
        if(direction >90 && direction <270){
            if(newVelocityY<0){
                newVelocityY=0;
            }
        }
        if(direction >0 && direction <180){
            if(newVelocityX<0){
                newVelocityX=0;
            }
        }
        if(direction >180 && direction <360){
            if(newVelocityX>0){
                newVelocityX=0;
            }
        }
        velocity = new Vector2D(newVelocityX,newVelocityY);
    }

    /***
     * Rotate the car left by the given number of degrees
     * @param degrees
     */
    public void turnLeft(int degrees){

        direction = (int)Math.round(direction-degrees);
        velocity = velocity.rotate(Math.toRadians(-degrees));
        direction = direction %360;
        if(direction<0){
            direction+=360;
        }
    }
    /***
     * Rotate the car right by the given number of degrees
     * @param degrees
     */
    public void turnRight(int degrees){
        direction = (int)Math.round(direction+degrees);

        velocity = velocity.rotate(Math.toRadians(+degrees));
        direction = direction %360;
        if(direction<0){
            direction+=360;
        }
    }

    /***
     *Returns the position the car is currently centered upon
     *Values correspond to pixels from top left corner
     * @return
     */
    public Vector2D getPosition(){
        return position;
    }
    /***
     *Returns the velocity of the car in the x and y axes
     *Values correspond to delta position every time ping is called
     * @return
     */
    public Vector2D getVelocity(){
        return velocity;
    }
    /***
     * Returns the current fuel level of the car
     * 100 represents full, while 0 represents empty
     * @return
     */
    public int getFuel(){
        return fuel;
    }

    @Override
    public String getID() {
        return carType.getString();
    }

    /***
     * Returns the direction the car is facing in degrees
     * 90 represents North, values increasing anticlockwise
     * This is currently broken due to pixels vs traditional coordinates
     * @return
     */
    public int getDirection(){
        return direction;
    }
    /***
     * Returns the current hit points of the car
     * 100 represents full health, while 0 represents destroyed
     */
    public int getHP(){
        return hp;

    }
    public void setHP(int hp) {
    	this.hp= Math.min(hp,strategy.getMaxHP());
    }
    /***
     * Returns the amount of money the car/player has
     */
    public int getMoney(){
        return money;
    }
    public void setMoney(int money) {
    	this.money=money;
    }

    /***
     * Decrement the amount of fuel
     * The amount decremented is determined by the strategy pattern
     */
    public void useFuel() {
    	strategy.useFuel(this);
    }


    /***
     * Set the amount of fuel for the car to the amount specified
     * @param d
     */
    public void setFuel(int f) {
    	this.fuel= Math.min(f,strategy.getMaxFuel());
    }

	/***
	 * Set the amount of fuel for the car to the amount specified
	 *
	 * @param d
	 */
	public void setFuel(double d) {
		this.fuel= (int)Math.round(Math.min(d,strategy.getMaxFuel()));
	}

	/***
	 * @return The rounded location coordinate as a Point object
	 */
	@Override
	public Point getOrigin() {
		return new Point((int) Math.round(position.getX()), (int) Math.round(position.getY()));
	}

	/***
	 * @return The Rectangle representing the area of the map which the car is
	 *         in
	 */
	@Override
	public Rectangle getBoundingBox() {
		int tileSize = ImgResources.imgDimension;
		double xProp = strategy.getImageEnum().getScaleY();
		double yProp = strategy.getImageEnum().getScaleY();
		return new Rectangle(getOrigin().x *tileSize - (int)Math.round(tileSize*xProp),
				getOrigin().y *tileSize - (int)Math.round(tileSize*yProp),
				(int)Math.round(tileSize*xProp), (int)Math.round(tileSize*yProp));
	}

	/***
	 * @return The type of the car, where CarType is an enum
	 */
	public CarType getCarType() {
		return carType;
	}

	/***
	 * Return the enum representing the image associated with this object
	 *
	 * @param s
	 *            Stage the game is on - Snow, Desert, Forest
	 * @return
	 */
	@Override
	public ImgResources getImageEnum(Stage s) {
		return strategy.getImageEnum();
	}

	/***
	 * @return the strategy that determines the cars behaviour
	 */
	public AbstractCarStrategy getStrategy() {
		return strategy;
	}

	/***
	 * Set this cars CarType to the parameter given and update the strategy
	 *	Also updates fuel and hp so that they are the same percentage of
	 *	the strategies maximum
	 * @param carType
	 */
	public void setCarType(CarType carType) {
		this.carType = carType;
		int oldMaxHP = strategy.getMaxHP();
		int oldMaxFuel = strategy.getMaxFuel();
		this.strategy = AbstractCarStrategy.strategyFactory(carType);
		fuel*=strategy.getMaxFuel()/oldMaxFuel;
		hp*=strategy.getMaxHP()/oldMaxHP;
	}

	@Override
	public boolean isCollidable() {
		return false;
	}

	@Override
	public boolean isDestroyable() {
		return true;
	}

	/***
	 * Return whether the car has finished the stage or not
	 *
	 * @return
	 */
	public boolean hasFinished() {
		return this.finished;
	}

	/***
	 * Set the cars finished stage to true
	 */
	public void setFinished() {
		this.finished = true;
	}

	@Override
	public String toString() {
		return this.objectName;
	}

	/***
	 * Boost the car (doubles speed for now) for the number of pings given by
	 * the parameter
	 *
	 * @param boostLength
	 */
	public void boost(int boostLength) {
		boost = true;
		this.boostTimer = boostLength;
	}

	/***
	 * Set the cars position to the given point
	 *
	 * @param p
	 */
	public void setPosition(Point p) {
		this.position = new Vector2D(p.getX(), p.getY());
	}

}
