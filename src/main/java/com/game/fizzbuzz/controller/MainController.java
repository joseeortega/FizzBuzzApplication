package com.game.fizzbuzz.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.game.fizzbuzz.model.Game;
import com.game.fizzbuzz.thread.GenerateSequenceThread;

/**
 * The Fizz-Buzz's main controllers based on Rest's entry points
 * @author Jose Márquez
 * @version 1.0.0
 * @date 4-10-18
 */

@SpringBootApplication
@RestController
public class MainController {

	@Autowired
	private Game fizzBuzzGame = new Game();
	
	@Value("${threadCallExceptionMessage}")
	private String threadCallExceptionMessage;
	
	@Value("${numEinsteinOutOfRangeExceptionMessage}")
	private String numEinsteinOutOfRangeExceptionMessage;
	
	/**
    * @name= getNumMax()
    * @author= Jose Ortega
    * @version = 1.0
    * @description = This function is used as entry point in order to return the max number limit
    * @date= 04-10-2018
    * @return numMax(Integer)
    */
	@GetMapping("/numMax")
	public Integer getNumMax() {
		return fizzBuzzGame.getMaxNum();
	}
	
	
	/**
    * @name= getNumLimits()
    * @author= Jose Ortega
    * @version = 1.0
    * @description = This function is used as entry point in order to return a hashmap with the limit numbers
    * @date= 04-10-2018
    * @return numLimits(HashMap<String, Integer>)
    */
	@GetMapping("/numLimits")
	public HashMap<String, Integer> getNumLimits() {

		return fizzBuzzGame.getNumLimits();
	}
	
	
	/**
    * @name= runGame()
    * @author= Jose Ortega
    * @version = 1.0
    * @description = This function is used as entry point in order to return the sequence generated using the numRandom received
    * @date= 04-10-2018
    * @param = numRandom (Integer) generated in the client
    * @return sequence(ArrayList<String>)
    */
	@PostMapping("/run")
	public ArrayList<String> runGame(@RequestBody Integer numRandom) {
		
		ArrayList<String> sequence = new ArrayList<String>();
		
		//Validate numRandom received
		if(numRandom>=fizzBuzzGame.getMinNum() && numRandom<=fizzBuzzGame.getMaxNum()) {
			
			//Set the numRandom 
			fizzBuzzGame.setNumEinstein(numRandom);
			try {
				
				//Call the sequence's thread
				ExecutorService executor = Executors.newCachedThreadPool();
			    Future<ArrayList<String>> futureCall = executor.submit(new GenerateSequenceThread(fizzBuzzGame));
			    sequence=futureCall.get();
			} catch (Exception ex) {
				Logger.getLogger(getClass().getName()).log(Level.WARNING,threadCallExceptionMessage + "\n" + ex.getMessage());
				sequence=null;
			}
			
		}else {
			Logger.getLogger(getClass().getName()).log(Level.WARNING,numEinsteinOutOfRangeExceptionMessage);
			sequence=null;
		}
		
		return sequence;

	}
}
