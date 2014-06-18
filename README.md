java-junit
==========

A collection of JUnit testing framework possibilities using JUnit 4.

# Parameterized test

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
  
# Parameterized test using JUnit Params  
  
An example of a parameterized JUnit test using JUnit Params can be found in TestBigDecimalNumber2
  
Steps:  
1. add **@RunWith** annotation using JUnitParamsRunner class as parameter  
2. add a method returning a collection or array of object arrays containing the parameter values  
3. add **@Parameters** annotation to Test method (attention! import junitparams.Parameters)  
  
JUnit Params project: [https://code.google.com/p/junitparams/](https://code.google.com/p/junitparams/)  
source: [http://www.silverbaytech.com/2013/01/21/junit-tricks-part-1-parameterized-tests/](http://www.silverbaytech.com/2013/01/21/junit-tricks-part-1-parameterized-tests/)  
  
# @Rule and TestRule implementation  
  
An example of a JUnit @Rule and TestRule implementation can be found in TestPersonRepository

source: [http://www.silverbaytech.com/2013/01/28/junit-tricks-part-2-junit-rules/](http://www.silverbaytech.com/2013/01/28/junit-tricks-part-2-junit-rules/)  
JUnit rule info: [https://github.com/junit-team/junit/wiki/Rules](https://github.com/junit-team/junit/wiki/Rules)
  
## ErrorCollector Rule  
  
This rule can be used to catch multiple errors in one test and print them out in one failure trace.  
A handy feature when you don't want your test to stop after the first failure.  
An example of this can be found in TestPerson.  
  
JUnit rule info: [https://github.com/junit-team/junit/wiki/Rules](https://github.com/junit-team/junit/wiki/Rules)
  
## ExpectedException Rule  
  
This rule can be used to expect specific exception and messages to be thrown during the test.  
An example can be found in TestPerson2.  
  
JUnit rule info: [https://github.com/junit-team/junit/wiki/Rules](https://github.com/junit-team/junit/wiki/Rules)  
  
## ExternalResource Rule  
  
This rule can be used to set up an external resource like a socket, server, database before a test and tear it down afterwards.  
  
JUnit rule info: [https://github.com/junit-team/junit/wiki/Rules](https://github.com/junit-team/junit/wiki/Rules)  
