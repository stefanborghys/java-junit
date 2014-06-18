package be.seriousbusiness.java.junit.business;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Example implementation for the JUnit ExpectedException Rule.</br>
 * Allows in-test specification of expected exception types and messages.</br>
 * </br>
 * JUnit rule info: https://github.com/junit-team/junit/wiki/Rules
 * @author seriousbusiness
 *
 */
public class PersonTest2 {
	@Rule
	public ExpectedException expectedException=ExpectedException.none();
	
	/**
	 * Creates a new Person with first name set <code>null</code>.</br>
	 * The test expects an IllegalArgumentException thrown with message "The first name is null".
	 */
	@Test
	public void testSetFirstNameNull(){
		final Person person=new Person();
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("The first name is null");
		// Don't forget to expect before the exception is thrown!
		person.setFirstName(null);
	}

}
