package general;

import model.carStrategies.*;

/***
 * ENUM returns the car type
 * enum values are associated with a string and a strategy
 *
 * @author huonfraser
 *
 */
public enum CarType {
	CAR1("car1", new Car1Strategy()),
	CAR2("car2", new Car2Strategy()),
	CAR3("car3", new Car3Strategy());

	public final String s;
	public final AbstractCarStrategy strategy;

	CarType(String s, AbstractCarStrategy strategy){
		this.s=s;
		this.strategy=strategy;
	}

	/***
	 * @return The String associated with this enum value
	 */
	public String getString() {
		return s;
	}

	/***
	 * @return The strategy associated with this enum
	 */
	public AbstractCarStrategy getStrategy() {
		return strategy;
	}
}
