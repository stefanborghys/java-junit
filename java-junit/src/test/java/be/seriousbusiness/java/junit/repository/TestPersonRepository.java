package be.seriousbusiness.java.junit.repository;


import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import be.seriousbusiness.java.junit.entity.Person;

/**
 * JUnit TestRule implementation example.</br>
 * This example is inspired upon Kevin Hunter's blog post about JUnit Rules.</br>
 * </br>
 * source: http://www.silverbaytech.com/2013/01/28/junit-tricks-part-2-junit-rules/
 * @author seriousbusiness
 *
 */
public class TestPersonRepository {
	private PersonRepository personRepository;
	
	/**
	 * The constructor creates a new PersonRepository used by all tests.
	 */
	public TestPersonRepository(){
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
	public void testSaveUniqueEmail(){
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
	
	private class PersonRepositoryTestRule implements TestRule{

		public Statement apply(final Statement base,final Description description) {
			return new Statement(){

				@Override
				public void evaluate() throws Throwable {
					try{
						base.evaluate();
					}catch(final Throwable e){
						// The IllegalArgumentException is caught 
						// so we can bring the PersonRepository in a neutral state.
						personRepository.deleteAll();
					}
				}
				
			};
		}
		
	}

}
