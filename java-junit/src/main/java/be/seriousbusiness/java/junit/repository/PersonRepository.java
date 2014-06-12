package be.seriousbusiness.java.junit.repository;

import java.util.HashSet;
import java.util.Set;

import be.seriousbusiness.java.junit.entity.Person;

/**
 * The PersonRepository is responsible for managing the storage of persons with an unique email address.</br>
 * This implementation is just an in-memory storage container used to explain the use of the JUnit TestRule.
 * @author seriousbusiness
 *
 */
public class PersonRepository {
	private Set<Person> persons;
	private int newId;
	
	public PersonRepository(){
		persons=new HashSet<Person>();
		newId=1;
	}
	
	/**
	 * Check if an email is already used by another person.
	 * @param email
	 * @return <code>true</code> when the email address already exists.
	 */
	private boolean emailExists(final String email){
		for(final Person person : persons){
			if(person.getEmail().equals(email)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Save a person into the repository.</br>
	 * A person's email has to be unique!
	 * @param person the person to save.
	 * @throws IllegalArgumentException when a person with the same email already exists.
	 */
	public void save(final Person person) throws IllegalArgumentException{
		if(person!=null){
			if(emailExists(person.getEmail())){
				throw new IllegalArgumentException();
			}else{
				person.setId(String.valueOf(newId++));
				persons.add(person);
			}
		}
	}
	
	/**
	 * Delete a person from the repository.
	 * @param person
	 */
	public void delete(final Person person){
		if(person!=null){
			persons.remove(person);
		}
	}
	
	public void deleteAll(){
		persons.clear();
	}
	
	public int count(){
		return persons.size();
	}

}
