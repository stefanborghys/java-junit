package be.seriousbusiness.java.junit.repository;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.seriousbusiness.java.junit.entity.Person;

/**
 * JUnit TestRule implementation example.</br>
 * This example is inspired upon Kevin Hunter's blog post about JUnit Rules.</br>
 * </br>
 * source: http://www.silverbaytech.com/2013/01/28/junit-tricks-part-2-junit-rules/
 * @author seriousbusiness
 *
 */
public class TestPersonRepository2 {
	private static final Logger LOGGER=LoggerFactory.getLogger(TestPersonRepository2.class);
	private PersonRepository personRepository;
	
	/**
	 * The constructor creates a new PersonRepository used by all tests.
	 */
	public TestPersonRepository2(){
		personRepository=new PersonRepository();
	}
	
	/**
	 * After every ran test a check is performed to see if the repository is in a neutral state.</br>
	 * Test data should never pollute our PersonRepository.
	 */
	@After
	public void after(){
		Assert.assertEquals("The repository should be empty.",0,personRepository.count());
	}
	
	/**
	 * Use of a custom PersonRepositoryTestRule.
	 */
	@Rule
	public PersonRepositoryTestRule personRepositoryTestRule=new PersonRepositoryTestRule();
	
	/**
	 * Tries to save two persons with the same email address.</br>
	 */
	@Test
	@TestConfigurationAnnotation("testSaveUniqueEmail")
	public void testSaveUniqueEmail(){
		LOGGER.debug("BEGIN - testSaveUniqueEmail()");
		final Person filip=new Person();
		filip.setEmail("filip@test.com");
		filip.setFirstName("filip");
		filip.setLastName("janssens");
		personRepository.save(filip);
		final Person mark=new Person();
		mark.setEmail("filip@test.com");
		mark.setFirstName("mark");
		mark.setLastName("hendrixks");
		personRepository.save(mark); // throws an IllegalArgumentException
		fail("A person with an already used email cannot be saved.");
	}
	
	/**
	 * A TestRule implementation wraps around the execution of a single test.</br>
	 * It gives control over the things we can do right before and after a test.</br>
	 * This for example makes it possible to anticipate the outcome of a test and react on it.
	 * @author seriousbusiness
	 *
	 */
	private class PersonRepositoryTestRule implements TestRule{

		public Statement apply(final Statement base,final Description description) {
			return new Statement(){

				@Override
				public void evaluate() throws Throwable {
					LOGGER.debug("BEGIN - PersonRepositoryTestRule.apply({},{}).evaluate()",base,description);
					try{
						final TestConfigurationAnnotation testConfigurationAnnotation=description.getAnnotation(TestConfigurationAnnotation.class);
						final String value=testConfigurationAnnotation.value();
						LOGGER.debug("The tests @TestConfigurationAnnotation value is: {}",value);
						base.evaluate();
					}catch(final Throwable e){
						// The IllegalArgumentException is caught 
						// so we can bring the PersonRepository in a neutral state.
						personRepository.deleteAll();
					}
					LOGGER.debug("END - PersonRepositoryTestRule.apply({},{}).evaluate()",base,description);
				}
				
			};
		}
		
	}

}
