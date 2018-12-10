package model;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for doing all the testing work.
 * Verifies and test classes adding the results to it's own list.
 * @UserID - tfy17jfo
 * @date - 2018-11-21
 * @version 1.0
 * @author Jakob Fridesjö
 */
class UnitTester {
	int fail = 0;
	int success = 0;
	int exceptionFail = 0;
	private Method[] testClassMethods;
	private Method setUp = null;
	private Method tearDown = null;
	private Class<?> testClass;
	private Constructor<?>[] testClassConstructor;
	private Object classInstance;
	List<String> results = new ArrayList<String>();



	/**
	 * Constructor for UnitTester
	 * @param className
	 * @throws ClassNotFoundException
	 */
    UnitTester(String className) throws ClassNotFoundException{
		testClass = Class.forName(className);
		testClassMethods = testClass.getDeclaredMethods();
		testClassConstructor = testClass.getDeclaredConstructors();
	}

	/**
	 * Verifies the class
	 */
    void verifyTheClass() {
		implementsInterface(testClass, se.umu.cs.unittest.TestClass.class);
		canBeInstantiated();
		hasSetUp();
		hasTearDown();
		hasVoidConstructor();
	}
	
	/**
	 * Tests the class
	 */
    void testTheClass() {
		try {
		    classInstance = testClass.newInstance();
		    for (Method m : testClassMethods) {
		    	if (m.getName().startsWith("test") && 
		    		m.getReturnType() == boolean.class && 
		    		m.getParameterCount() == 0) {
		    		testMethod(m);
		    	}
		    }
		} catch (Exception e) {
		    results.add("Could not test the class\n");
		}
	}
	
	/**
	 * Checks if class can be instantiated.
	 * @return boolean
	 */
	private boolean canBeInstantiated(){
		if (testClass.isInterface()) {
			results.add("Class cannot be instantiated\n");
			return false;
		}
		return true;
	}
	
	/**
	 * Checks if the class has any setUp method
	 * @return boolean
	 */
	private boolean hasSetUp() {
		for (Method m : testClassMethods) {
			if (m.getName().equals("setUp")) {
				setUp = m;
				return true;
			}
		}
		results.add("Class has no setUp method\n");
		return false;
	}
	
	/**
	 * Checks if the class has any tearDown method
	 * @return boolean
	 */
	private boolean hasTearDown() {
		for (Method m : testClassMethods) {
			if (m.getName().equals("tearDown")) {
				tearDown = m;
				return true;
			}
		}
		results.add("Class has no tearDown method\n");
		return false;
	}
	
	/**
	 * Checks if the class has any void constructor.
	 * @return boolean
	 */
	private boolean hasVoidConstructor() {
		for (Constructor<?> c : testClassConstructor) {
			if (c.getParameterCount() == 0) {
				return true;
			}
		}
		results.add("Class has no constructor with no parameters\n");
		return false;
	}
	
	/**
	 * Tests individual methods by invoking setUp if possible,
	 * invoking the method and the invoking tearDown if possible.
	 * @param m Method to test.
	 */
	private void testMethod(Method m){
		try {
			if (setUp != null) {
				setUp.invoke(classInstance);
			}
			if ((boolean)m.invoke(classInstance)) {
				results.add(m.getName() + ": SUCCESS\n");
				success++;
			} else {
				results.add(m.getName() + ": FAIL\n");
				fail++;
			}
			if (tearDown != null) {
				tearDown.invoke(classInstance);
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			results.add(m.getName() + ": FAIL, " + e.getCause() + "\n");
			exceptionFail++;
		}
	}
	
	/**
	 * Checks if class implements specified interface.
	 * @param object to check
	 * @param iFace interface to verify against
	 * @return boolean
	 */
	private boolean implementsInterface(Object object, Class<?> iFace){
		if (iFace.isAssignableFrom(testClass)) {
			return true;
		}
		results.add("Interface not implemented\n");
		return false;
	}
}
