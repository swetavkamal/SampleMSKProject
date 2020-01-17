package utilsClass;

import java.util.Random;

/**
 * 
 * This class has the Method which will take the min and max integer value
 * And generate the random number between them
 * @author swetavk
 *
 */


public class RandomDataGenerator {
	
	public int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

}
