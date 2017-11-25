package model.carStrategies;

import general.Direction;
import model.gameObjects.Car;
import resources.ImgResources;

/***
 * Car strategy for the third car available to the player.
 *This is the fastest, healthiest,largest fuel tank car in the game
 *
 * @author fraserhuon
 *
 */
public class Car3Strategy extends AbstractCarStrategy{
	/***
	 * Move the car
	 * This car has more sensitive steering
	 */
	@Override
	public void move(Direction d, Car car) {
		if(d==Direction.LEFT){
			car.turnLeft(15);
		}else if(d==Direction.RIGHT){
			car.turnRight(15);
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
	 * This car has the highest top speed
	 */
	@Override
	public double getTerminalVelocity() {
		return 0.15;
	}

	/***
	 * Associate this strategy with img CAR3
	 */
	@Override
	public ImgResources getImageEnum() {
		return ImgResources.CAR3;
	}

	/***
	 * This car has the largest fuel tank
	 */
	@Override
	public int getMaxFuel() {
		return 200;
	}
	/***
	 * This strategy has the highest hp
	 */
	@Override
	public int getMaxHP() {
		return 200;
	}
}
