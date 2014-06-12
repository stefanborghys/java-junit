package be.seriousbusiness.java.junit.number;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * JUnit parameterized test example.</br>
 * </br>
 * This example is inspired upon Kevin Hunter's blog about JUnit parameterized tests.</br>
 * source: http://www.silverbaytech.com/2013/01/21/junit-tricks-part-1-parameterized-tests/
 * @author seriousbusiness
 *
 */
//1. Add @RunWith annotation to JUnit test class
@RunWith(value=Parameterized.class) 
public class TestBigDecimalNumber {
	private final BigDecimal number,minimum,maximum;
	private final boolean isBetweenOrEqual;
	
	/**
	 * TestBigDecimalNumber parameterized constructor.
	 * @param number the number to under test
	 * @param minimum the minimum value
	 * @param maximum the maximum value
	 * @param isBetweenOrEqual the result to expect
	 */
	// 2. Add a parameterized constructor which receives and stores the values under test
	public TestBigDecimalNumber(final BigDecimal number,final BigDecimal minimum, final BigDecimal maximum,final boolean isBetweenOrEqual){
		this.number=number;
		this.minimum=minimum;
		this.maximum=maximum;
		this.isBetweenOrEqual=isBetweenOrEqual;
	}
	
	/**
	 * Returns the parameters for every boundary to test.
	 * @return a collection of an array of Objects containing the parameterized constructor parameters
	 */
	// 3. Add a public static method returning a collection of an array of parameter Objects,
	// don't forget to annotate this method with @Parameters!
	@Parameters
	public static Collection<Object[]> getParameters(){
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
	 */
	// 4. Implement a test using the parameters.
	@Test
	public void testIsBetweenOrEqual(){
		Assert.assertEquals(isBetweenOrEqual,new BigDecimalNumber().isBetweenOrEqual(number,minimum,maximum));
	}
}
