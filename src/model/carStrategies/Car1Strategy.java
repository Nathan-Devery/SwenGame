package model.carStrategies;

import general.Direction;
import model.gameObjects.Car;
import resources.ImgResources;

/***
 * Car strategy class for the first car available to the player.
 * This is the slowest car available.
 *
 * @author fraserhuon
 *
 */
public class Car1Strategy extends AbstractCarStrategy {

	/***
	 * Move car, standard rates
	 */
	@Override
	public void move(Direction d, Car car) {
		if(d==Direction.LEFT){
			car.turnLeft(10);
		}else if(d==Direction.RIGHT){
			car.turnRight(10);
		}else if(d==Direction.UP){
			car.accelerate(1);
		}else if(d==Direction.DOWN){
			car.decelerate(1);
		}
	}

	/***
	 * Use fuel at the standard rate
	 */
	@Override
	public void useFuel(Car c) {
		c.setFuel(c.getFuel()-1);
	}

	/***
	 * This is the slowest car
	 */
	@Override
	public double getTerminalVelocity() {
		return 0.06;
	}

	/***
	 * Associate this strategy with CAR1
	 */
	@Override
	public ImgResources getImageEnum() {
		return ImgResources.CAR1;
	}

	/***
	 * This car has the smallest fuel tank
	 */
	@Override
	public int getMaxFuel() {
		return 70;
	}

	/***
	 * This car has the lowest hp
	 */
	@Override
	public int getMaxHP() {
		return 100;
	}
}
