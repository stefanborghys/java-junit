java-junit
==========

A collection of JUnit testing framework possibilities.

# Parameterized JUnit test

An example of a parameterized JUnit test can be found in TestBigDecimalNumber  

Steps:  
1. add **@RunWith** annotation to JUnit test class  
2. add a **parameterized constructor** which receives and stores the values under test  
3. add a public **static method** returning a collection of object arrays containing the parameter values for the constructor.  Don't forget to annotate this method with **@Parameters**!  
4. **implement** a **test** using the parameters received by the constructor  
  
Benefits:
- compared to looping a single test with parameters, all tests are always executed!  
- every test is independent (result of previous benefit)  
  
source: [http://www.silverbaytech.com/2013/01/21/junit-tricks-part-1-parameterized-tests/](http://www.silverbaytech.com/2013/01/21/junit-tricks-part-1-parameterized-tests/)
