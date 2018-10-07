/**
 * @name: Game.js
 * @description: class for FizzBuzzGame
 * @date: 2018-05-10
 * @author: Jose Ortega
 * @version: 1.0
 * @properties: maxNum: Max num to generate minNum: Min num to generate
 *              numEinstein: The num thinked by Einstein
 * @methods: getters and setters. getNumEinstein():int
 */

class Game {

  // CONSTRUCTOR
  constructor(maxNum, minNum,numEinstein) {
	this.maxNum = maxNum;
	this.minNum = minNum;
	this.numEinstein = numEinstein;
  }
  // SETTERS & GETTERS
  setMaxNum(maxNum){
	this.maxNum = maxNum;
  }
  getMaxNum(){
	return this.maxNum;
  }
  setMinNum(minNum){
	this.minNum = minNum;
  }
  getMinNum(){
	return this.minNum;
  }
  setNumEinstein(numEinstein){
	this.numEinstein = numEinstein;
  }
  getNumEinstein(){
	return this.numEinstein;
  }
  
  // METHODS
  /**
	 * @name= generateEinsteinNumber()
	 * @author= Jose Ortega
	 * @version = 1.0
	 * @description = This function is used to generate the Einstein's number
	 * @date= 2018-05-10
	 */
  generateEinsteinNumber(){
	  try {
		  //Generate random number including Max and Min
		  this.setNumEinstein(Math.floor(Math.random() * (this.maxNum - this.minNum + 1)) + this.minNum);
	  } catch (e) {
		  this.numEinstein=this.minNum;
		  console.log("Error occurred in Game (method -> generateEinsteinNumber) exception");
	  }
  }
  
}
