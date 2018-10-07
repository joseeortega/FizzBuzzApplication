package com.game.fizzbuzz.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * The FizzBuzz Game class
 * 
 * @author Jose Márquez
 * @version 1.0.0
 * @date 4-10-18
 */

@Component
public class Game {
	
	@Value("${maxNumber}")
	private int maxNum;
	
	@Value("${minNumber}")
	private int minNum;
	
	private int numEinstein;
	
	//Constructors
	public Game(){
		
	}
	
	public Game(int minNum) {
		this.minNum = minNum;
	}
	
	public Game(int maxNum, int minNum) {
		this.maxNum = maxNum;
		this.minNum = minNum;
	}

	public Game(int maxNum, int minNum, int numEinstein) {
		this.maxNum = maxNum;
		this.minNum = minNum;
		this.numEinstein = numEinstein;
	}

	//Methods
	public int getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}

	public int getMinNum() {
		return minNum;
	}

	public void setMinNum(int minNum) {
		this.minNum = minNum;
	}

	public int getNumEinstein() {
		return numEinstein;
	}

	public void setNumEinstein(int numEinstein) {
		this.numEinstein = numEinstein;
	}
	
	
	/**
    * @name= getNumLimits()
    * @author= Jose Ortega
    * @version = 1.0
    * @description = This function is used to generate a hashmap with the limit numbers
    * @date= 04-10-2018
    * @return numLimits(HashMap<String, Integer>)
    */
	public HashMap<String, Integer> getNumLimits() {
		
		HashMap<String, Integer> numLimits = new HashMap<String, Integer>();
		
		try {	
			numLimits.put("min",minNum);
			numLimits.put("max",maxNum);
		}catch(Exception ex) {
			Logger.getLogger(getClass().getName()).log(Level.WARNING,ex.getMessage());
			numLimits=null;
		}
		
		return numLimits;
	}
	
	
	
	/**
    * @name= generateGame()
    * @author= Jose Ortega
    * @version = 1.0
    * @description = This function is used to generate the sequence
    * @date= 04-10-2018
    * @return fizzBuzzSequence(ArrayList<String>)
    */
	public ArrayList<String> generateGame() {
		
		ArrayList<String> fizzBuzzSequence= new ArrayList<String>();
		
		try {
			for (int i = numEinstein; i <= maxNum; i++) {
				if (i % 3 == 0 || i % 5 == 0) {
					if (i % 3 == 0) {
						fizzBuzzSequence.add("fizz");
					}
					if (i % 5 == 0) {
						fizzBuzzSequence.add("buzz");
					}
				} else {
					fizzBuzzSequence.add(Integer.toString(i));
				}
			}
		}catch(Exception ex) {
			Logger.getLogger(getClass().getName()).log(Level.WARNING,ex.getMessage());
			fizzBuzzSequence=null;
		}
		
		return fizzBuzzSequence;
	}
	

}
