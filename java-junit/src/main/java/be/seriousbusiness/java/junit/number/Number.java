package be.seriousbusiness.java.junit.number;

/**
 * Generic description of a number.
 * @author seriousbusiness
 *
 * @param <T>
 */
public interface Number<T> {
	/**
	 * Check if a number is between or equal to a minimum and maximum value.
	 * @param number the number to check
	 * @param minimum the minimum value
	 * @param maximum the maximum value
	 * @return <code>true</code> when the number is greater or equal to the minimum</br>
	 * and smaller or equal than the maximum.
	 */
	boolean isBetweenOrEqual(final T number,final T minimum,final T maximum);
}
