package be.seriousbusiness.java.junit.business;

import java.util.Calendar;

public class Person {
	private Calendar age;
	private String firstName;
	private String lastName;
	
	/**
	 * Set the age.
	 * @param age
	 * @throws IllegalArgumentException when the age is <code>null</code> or in the future.
	 */
	public void setAge(final Calendar age) throws IllegalArgumentException{
		if(age==null){
			throw new IllegalArgumentException("The age is null");
		}else if(age.after(Calendar.getInstance())){
			throw new IllegalArgumentException("The age is in the future");
		}else{
			this.age=age;
		}
	}
	
	/**
	 * Returns a clone of the age.
	 * @return age clone
	 */
	public Calendar getAge(){
		return age==null ? null : (Calendar)age.clone();
	}
	
	/**
	 * Set the first name.
	 * @param firstName
	 * @throws IllegalArgumentException when the first name is <code>null</code> or empty.
	 */
	public void setFirstName(final String firstName) throws IllegalArgumentException{
		if(firstName==null){
			throw new IllegalArgumentException("The first name is null");
		}else if(firstName.isEmpty()){
			throw new IllegalArgumentException("The first name is empty");
		}else{
			this.firstName=firstName.trim().toLowerCase();
		}
	}
	
	/**
	 * Retrieve the lower-case first name.
	 * @return lower-case first name
	 */
	public String getFirstName(){
		return firstName;
	}
	
	/**
	 * Set the last name.
	 * @param lastName
	 * @throws IllegalArgumentException when the last name is <code>null</code> or empty.
	 */
	public void setLastName(final String lastName) throws IllegalArgumentException{
		if(lastName==null){
			throw new IllegalArgumentException("The last name is null");
		}else if(lastName.isEmpty()){
			throw new IllegalArgumentException("The last name is empty");
		}else{
			this.lastName=lastName.trim().toLowerCase();
		}
	}
	
	/**
	 * Retrieve the lower-case last name.
	 * @return lower-case last name
	 */
	public String getLastName(){
		return lastName;
	}
	

}
