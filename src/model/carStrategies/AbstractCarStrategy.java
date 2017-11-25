package model.carStrategies;

import general.CarType;
import general.Direction;
import model.gameObjects.Car;
import resources.ImgResources;

/***
 * Car strategies handle how cars interact with the map and are interacted with
 *
 * @author fraserhuon
 */
public abstract class AbstractCarStrategy {
	/***
	 * Controls the movement of the car, deceleration and acceleration changes
	 * with up, down, left, right movement.
	 *
	 * @param direction - left, right, up and down
	 * @param car object
	 */
	public abstract void move(Direction direction, Car car);

	/***
	 * Use of fuel as the car moves through the course.
	 *
	 * @param car object
	 */
	public abstract void useFuel(Car car);

	/***
	 * Getter method to return the car's terminal velocity.
	 *
	 * @return terminal velocity
	 */
	public abstract double getTerminalVelocity();

	/***
	 * Getter method for max fuel.
	 *
	 * @return car's max fuel
	 */
	public abstract int getMaxFuel();

	/***
	 * Getter method for max HP.
	 *
	 * @return max HP
	 */
	public abstract int getMaxHP();

	/***
	 * Getter method for image ENUM.
	 *
	 * @return image enum for car
	 */
	public abstract ImgResources getImageEnum();

	/***
	 * Switch case for car strategy, return the strategy that matches the car
	 * the player is using on the map.
	 *
	 * @param carType
	 * @return car strategy
	 */
	public static AbstractCarStrategy strategyFactory(CarType carType) {
		switch(carType) {
		case CAR1:
			return new Car1Strategy();
		case CAR2:
			return new Car2Strategy();
		case CAR3:
				return new Car3Strategy();
		default:
			return new Car1Strategy();
		}
	}
}
