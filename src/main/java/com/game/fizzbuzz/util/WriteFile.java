package com.game.fizzbuzz.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;

/**
 * This is a helper class used to generate / write files
 * @author Jose Ortega
 * @version 1.0.0
 * @date 5-10-18
 */
public class WriteFile {
	
	@Value("${writeFileExceptionMessage}")
	private String writeFileExceptionMessage;
	
	@Value("${createFileExceptionMessage}")
	private String createFileExceptionMessage;
	
	@Value("${vectorToStringExceptionMessage}")
	private String vectorToStringExceptionMessage;
	
	public WriteFile(){}
	
	/**
    * @name= write()
    * @author= Jose Ortega
    * @version = 1.0
    * @description = This function is used write the elements (sequence) in the file. Formatted all in line or element per line
    * @date= 06-10-2018
    * @param = elements: sequence to write
    * @param = allInLIne: boolean / true if you want to write the sequence all in line or false if you want each element in one line
    */
	public void write(ArrayList<String> elements, boolean allInLine) {
		try {
			
			//Generate and get the file
			File file = generateFile();
			
			if (file != null) {
				BufferedWriter bufferw;
				bufferw = new BufferedWriter(new FileWriter(file));
				
				//Depending on boolean received ( write the sequence all in line / element per line )
				if(allInLine) {
					bufferw.write(elementsToLine(elements));
				}else {
					for (int i = 0; i < elements.size(); i++) {
						bufferw.write(elements.get(i));
						bufferw.newLine();
					}	
				}
				
				//Close the file				
				bufferw.close();
			}
		} catch (Exception ex) {
			Logger.getLogger(getClass().getName()).log(Level.WARNING,writeFileExceptionMessage + "\n" + ex.getMessage());
			
		}
    }

	/**
    * @name= generateFile()
    * @author= Jose Ortega
    * @version = 1.0
    * @description = This function is used to generate the file
    * @date= 06-10-2018
    * @return = file: File
    */
	private File generateFile() {
		File file = null;
		try {
			
			//Path file
			File pathToSaveFile = new File("src/main/resources/results/");
			String fullPath = pathToSaveFile.getAbsolutePath();
			
			//Name file
			UUID id = UUID.randomUUID();
			Date date = new Date();
			DateFormat hourdateFormat = new SimpleDateFormat("HH-mm-ss_dd-MM-yyyy");
			String nameFile = hourdateFormat.format(date)+id;
			
			//Create file
			file = new File(fullPath+"/"+nameFile+".txt");
		} catch (Exception ex) {
			Logger.getLogger(getClass().getName()).log(Level.WARNING,createFileExceptionMessage + "\n" + ex.getMessage());
			file = null;
		}
		
		return file;
	}
	
	/**
    * @name= elementsToLine()
    * @author= Jose Ortega
    * @version = 1.0
    * @description = This function is used to generate the line (string) using all elements received
    * @date= 06-10-2018
    * @param = elements: all elements sequence
    * @return = line: String
    */
	private String elementsToLine(ArrayList<String> elements) {
		String line="";
		
		try {
			for (int i = 0; i < elements.size(); i++) {
				line+=i!=elements.size()-1?elements.get(i)+", ":elements.get(i);
			}	
		}catch(Exception ex) {
			Logger.getLogger(getClass().getName()).log(Level.WARNING,vectorToStringExceptionMessage + "\n" + ex.getMessage());
			line = "";
		}
		
		return line;
	}
}