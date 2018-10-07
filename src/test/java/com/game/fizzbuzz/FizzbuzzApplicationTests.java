package com.game.fizzbuzz;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.game.fizzbuzz.controller.MainController;


/**
 * The Fizz-Buzz's test class used to realize tests
 * @author Jose Márquez
 * @version 1.0.0
 * @date 7-10-18
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class FizzbuzzApplicationTests {
	
	@Autowired
	private MainController mainCtrl= new MainController();

	/**
    * @name= getNumLimitsTest()
    * @author= Jose Ortega
    * @version = 1.0
    * @description = This function is used to test the getNumLimits method from Rest Controller
    * @date= 07-10-2018
    */
	@Test
	public void getNumLimitsTest() {
		
		HashMap<String, Integer> numLimits= mainCtrl.getNumLimits();
		assertNotNull("Correct: The response wasn't null",numLimits);
		assertTrue("Correct: The response was correct (receiving 2 elements)",numLimits.size()==2);
		assertFalse("Error: The response wasn't correct (not receiving 2 element)",numLimits.size()!=2);
		
	}
	
	
	/**
    * @name= getRunGameTest()
    * @author= Jose Ortega
    * @version = 1.0
    * @description = This function is used to test the getRunGame method from Rest Controller
    * @date= 07-10-2018
    */
	@Test
	public void getRunGameTest() {
		
		ArrayList<String> sequenceOk = mainCtrl.runGame(50);
		assertNotNull("Correct: The response wasn't null",sequenceOk);
		assertTrue("Correct: The response was correct (receiving elements)",sequenceOk.size()>0);
		assertFalse("Error: The response wasn't correct (not receiving elements)",sequenceOk.size()==0);
		
		ArrayList<String> sequenceKo = mainCtrl.runGame(-100);
		assertNull("Correct: The response was null",sequenceKo);
		
	}

}
