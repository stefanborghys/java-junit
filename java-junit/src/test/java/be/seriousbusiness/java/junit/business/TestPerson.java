package be.seriousbusiness.java.junit.business;

import java.util.Calendar;

import org.hamcrest.CoreMatchers;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import be.seriousbusiness.java.junit.business.Person;

/**
 * Example implementation for the JUnit ErrorCollector Rule.</br>
 * This rule can be used to catch multiple errors in one test and print them out in one failure trace.</br>
 * A handy feature when you don't want your test to stop after the first failure.</br>
 * </br>
 * JUnit info: https://github.com/junit-team/junit/wiki/Rules
 * @author seriousbusiness
 *
 */
public class TestPerson {
	/**
	 * Collect multiple errors in one test method.
	 */
	@Rule
	public ErrorCollector errorCollector=new ErrorCollector();
	
	/**
	 * Creates a new Person with incorrect age, first name and last name.</br>
	 * All exceptions are caught and add to the trace of errors.
	 */
	@Ignore
	@Test
	public void testSetIncorrectAgeFirstNameLastName(){
		final Person person=new Person();
		Calendar age=Calendar.getInstance();
		age.add(Calendar.DATE,1);
		try{
			person.setAge(age);
		}catch(final IllegalArgumentException e){
			// The age is in the future!
			errorCollector.addError(e);
		}
		try{
			person.setFirstName(null);
		}catch(final IllegalArgumentException e){
			// The first name is null!
			errorCollector.addError(e);
		}
		try{
			person.setLastName("");
		}catch(final IllegalArgumentException e){
			// The last name is empty!
			errorCollector.addError(e);
		}
		errorCollector.checkThat("The first name should be null.",person.getFirstName(),CoreMatchers.nullValue());
		errorCollector.checkThat("The last name should be null.",person.getLastName(),CoreMatchers.nullValue());
		errorCollector.checkThat("The age should be null.",person.getAge(),CoreMatchers.nullValue());

		/*
		errorCollector.apply(base, description);
		errorCollector.checkThat(reason, value, matcher);
		errorCollector.checkSucceeds(callable);
		*/
	}
	
	/**
	 * Creates a new Person with correct age, first name and last name.</br>
	 * Afterwards we check if all values are correctly stored by retrieving them.
	 */
	@Test
	public void testSetAgeFirstNameLastName(){
		final Person person=new Person();
		Calendar age=Calendar.getInstance();
		try{
			person.setAge(age);
		}catch(final IllegalArgumentException e){
			errorCollector.addError(e);
		}
		try{
			person.setFirstName("Erik ");
		}catch(final IllegalArgumentException e){
			errorCollector.addError(e);
		}
		try{
			person.setLastName("Diddens");
		}catch(final IllegalArgumentException e){			
			errorCollector.addError(e);
		}
		errorCollector.checkThat("The first name should not be null.",person.getFirstName(),CoreMatchers.notNullValue());
		errorCollector.checkThat("The last name should not be null.",person.getLastName(),CoreMatchers.notNullValue());
		errorCollector.checkThat("The age should not be null.",person.getAge(),CoreMatchers.notNullValue());
		errorCollector.checkThat("The first name should not be null.",person.getFirstName(),CoreMatchers.is("erik"));
		errorCollector.checkThat("The last name should not be null.",person.getLastName(),CoreMatchers.is("diddens"));
		errorCollector.checkThat("The age should not be null.",person.getAge(),CoreMatchers.equalTo(age));
	}
	
	/**
	 * Creates a new Person with upper-case first name</br>
	 * and checks if a lower-case first name is returned.
	 */
	@Test
	public void testSetFirstName(){
		final Person person=new Person();
		person.setFirstName("JENS");
		errorCollector.checkThat("The first name should be lower-case.",person.getFirstName(),CoreMatchers.is("jens"));
	}

}
