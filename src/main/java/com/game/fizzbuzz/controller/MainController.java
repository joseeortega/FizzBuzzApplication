package com.game.fizzbuzz.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.game.fizzbuzz.model.Game;
import com.game.fizzbuzz.thread.GenerateSequenceThread;

/**
 * The Fizz-Buzz's main controllers based on Rest's entry points
 * 
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

	/**
	 * This function is used as entry point in order to return the max number limit
	 * @return numMax(Integer)
	 */
	@GetMapping("/numMax")
	public Integer getNumMax() {
		return fizzBuzzGame.getMaxNum();
	}

	/**
	 * This function is used as entry point in order to return a hashmap with the limit numbers
	 * @return numLimits(HashMap<String, Integer>)
	 */
	@GetMapping("/numLimits")
	public HashMap<String, Integer> getNumLimits() {

		return fizzBuzzGame.getNumLimits();
	}

	/**
	 * This function is used as entry point in order to return the sequence generated using the numRandom received
	 * @param = numRandom (Integer) generated in the client
	 * @return sequence(ArrayList<String>)
	 */
	@PostMapping("/run")
	public ArrayList<String> runGame(@RequestBody Integer numRandom) {
		
		ArrayList<String> sequence = new ArrayList<String>();
		
		fizzBuzzGame.setNumEinstein(numRandom);
		try {
			ExecutorService executor = Executors.newCachedThreadPool();
		    Future<ArrayList<String>> futureCall = executor.submit(new GenerateSequenceThread(fizzBuzzGame));
		    sequence=futureCall.get(); // Here the thread will be blocked 
		} catch (Exception ex) {
			Logger.getLogger(getClass().getName()).log(Level.WARNING,threadCallExceptionMessage + "\n" + ex.getMessage());
		}
		
		return sequence;

	}
}
