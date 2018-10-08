package com.game.fizzbuzz.thread;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;

import com.game.fizzbuzz.model.Game;
import com.game.fizzbuzz.util.WriteFile;


/**
 * The thread (Callable) is used to generate the sequence and return it
 * 
 * @author Jose M�rquez
 * @version 1.0.0
 * @date 5-10-18
 */

public class GenerateSequenceThread implements Callable<ArrayList<String>>{
	
	private Game fizzBuzzGame;
	
	@Value("${threadUseExceptionMessage}")
	private String threadUseExceptionMessage;
	
	public GenerateSequenceThread(Game fizzBuzzGame) {
		this.fizzBuzzGame = fizzBuzzGame;
	}

	@Override
	public ArrayList<String> call() throws Exception {
		
		ArrayList<String> sequence = new ArrayList<String>();
		
		try {
			//Get the sequence
			sequence=fizzBuzzGame.generateGame();
			//Write the sequence into the File
			WriteFile wf = new WriteFile();
			//Send true to write the sequence all in line or false to write an element per line
			wf.write(sequence,false);
		}catch(Exception ex) {
			Logger.getLogger(getClass().getName()).log(Level.WARNING,threadUseExceptionMessage + "\n" + ex.getMessage());
			sequence=null;
		}
		
		return sequence;
	}

}
