package model.carStrategies;

import general.Direction;
import model.gameObjects.Car;
import resources.ImgResources;

/***
 * Car strategy for the second car available to the player.
 * This car is faster and has more fuel and hp than car1
 *
 * @author fraserhuon
 *
 */
public class Car2Strategy extends AbstractCarStrategy{

	/***
	 * Move this car
	 * Car 2 accelerates/brakes faster than other cars
	 */
	@Override
	public void move(Direction d, Car car) {
		if(d==Direction.LEFT){
			car.turnLeft(10);
		} else if(d==Direction.RIGHT){
			car.turnRight(10);
		} else if(d==Direction.UP){
			car.accelerate(2);
		} else if(d==Direction.DOWN){
			car.decelerate(2);
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
	 * Medium top speed
	 */
	@Override
	public double getTerminalVelocity() {
		return 0.08;
	}

	/***
	 * Medium max fuel
	 */
	@Override
	public int getMaxFuel() {
		return 100;
	}

	/***
	 * Medium max hp
	 */
	@Override
	public int getMaxHP() {
		return 150;
	}

	/***
	 * This car has image CAR2
	 */
	@Override
	public ImgResources getImageEnum() {
		return ImgResources.CAR2;
	}
}
