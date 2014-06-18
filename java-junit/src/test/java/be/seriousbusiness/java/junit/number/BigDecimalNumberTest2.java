package be.seriousbusiness.java.junit.number;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

/**
 * JUnit Params project test example.</br>
 * </br>
 * This example is inspired upon Kevin Hunter's blog post about JUnit parameterized tests.</br>
 * source: http://www.silverbaytech.com/2013/01/21/junit-tricks-part-1-parameterized-tests/
 * @author seriousbusiness
 *
 */
// 1. Add RunWith annotation using JUnitParamsRunner class as parameter
// The project can be found on: https://code.google.com/p/junitparams/
@RunWith(JUnitParamsRunner.class)
public class BigDecimalNumberTest2 {
	
	/**
	 * Returns the parameters for every boundary to test.
	 * @return a collection or array of an array of Objects containing the parameterized constructor parameters
	 */
	// 2. Add a method returning a collection or array of object arrays containing the parameter values
	protected Collection<Object[]> getParameters(){
		final BigDecimal minimum=new BigDecimal(5.66),maximum=new BigDecimal(18.37);
		final Collection<Object[]> parameters=new ArrayList<Object[]>();
		parameters.add(new Object[]{new BigDecimal(5.65),minimum,maximum,false}); // To low!
		parameters.add(new Object[]{minimum,minimum,maximum,true}); // Minimum
		parameters.add(new Object[]{new BigDecimal(5.67),minimum,maximum,true}); // Between
		parameters.add(new Object[]{new BigDecimal(10.50),minimum,maximum,true}); // Between
		parameters.add(new Object[]{new BigDecimal(18.36),minimum,maximum,true}); // Between
		parameters.add(new Object[]{maximum,minimum,maximum,true}); // Maximum
		parameters.add(new Object[]{new BigDecimal(18.38),minimum,maximum,false}); // To big!
		return parameters;
	}
	
	/**
	 * Test {@link BigDecimalNumber} isBetweenOrEqual method implementation</br>
	 * by trying out all boundaries.
	 * @param number the number to under test
	 * @param minimum the minimum value
	 * @param maximum the maximum value
	 * @param isBetweenOrEqual isBetweenOrEqual the result to expect
	 */
	@Test
	@Parameters(method="getParameters") // Attention! import of junitparams.Parameters, not org.junit.runners.Parameterized.Parameters
	// 3. Add Parameters annotation to Test method
	public void testIsBetweenOrEqual(final BigDecimal number,final BigDecimal minimum, final BigDecimal maximum,final boolean isBetweenOrEqual){
		Assert.assertEquals(isBetweenOrEqual,new BigDecimalNumber().isBetweenOrEqual(number,minimum,maximum));
	}

}
