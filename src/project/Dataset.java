package project;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * NOTE THIS DOES NOT HAVE ALL THE FUNCTIONS REQUIRED, YOU'LL HAVE TO ADD WHAT YOU NEED AS NEEDED.
 * 
 * OUR OVERALL IDEA IS THAT THE GUI (DESIGN.JAVA) IS GOING TO CALL ONE OF THESE HELPER FUNCTIONS
 * @author Richie
 *
 */

public class Dataset {
	private ArrayList<Float> arr = new ArrayList<Float>();  //used to store extracted input
	private String historyLog; //used for the overall export history
	private int lowerBound = 0; //current lower bound default 0
	private int upperBound = 100; //current upper bound default 100
	
	/*
	 * Constructor for Dataset
	 */
	public Dataset(ArrayList<Float> array) {
		arr = array;
		sort();
	}
	
	//op2
	/*
	public Dataset(File file) {
		arr = parseTxt(file);
	}*/
	
	/**
	 * parse text file,
	 * there should be one value per line
	 * 
	 * if file is valid, return array list of values
	 * else return error message that will be displayed in the error report
	 * @param file
	 */
	public ArrayList<Float> parseTxt(File file)  {
		try {
			Scanner s = new Scanner(file);
			ArrayList<Float> scores = new ArrayList<Float>();
			
			while(s.hasNextLine()) {
				float next = Float.parseFloat(s.nextLine());
				scores.add(next);
			}
			
			s.close();
			return scores;
		} catch (FileNotFoundException e) {
		//Error person do this part	
		return null;
		}
	}

	
	/**
	 * parse csv file, so extract by commas
	 * get all values
	 * 
	 * else return error message that will be displayed in the error report
	 * @param file
	 */
	public ArrayList<Float> parseCsv(File file) {
		try {
			Scanner s = new Scanner(file);
			ArrayList<Float> scores = new ArrayList<Float>();
			
			while (s.hasNextLine()) {
				String[] data = s.nextLine().split(",");
				for (String d: data) {
					scores.add(Float.parseFloat(d));
				}
			}

			s.close();
			return scores;
		} catch (FileNotFoundException e) {
			//error person do this part
			return null;
		}
	}
	
	/**
	 * Sets boundaries for Dataset
	 * @return dataInvalid - true if data is out of boundaries, false
	 * if data fits in boundaries
	 */
	public Boolean setBoundaries(int lower, int upper) {
		lowerBound = lower;
		upperBound = upper;
		Boolean dataInvalid = checkForOutOfBounds();
		if (dataInvalid == false) {
			//give error
			//delete data or no?
		}
		return dataInvalid; //or give error on return
	}
	/**
	 * @return TRUE = THE ARRAY CONTAINS INVALID NUMS true or false whether the array contains all valid nums. If this is not the case the GUI method that called
	 * this will empty the array
	 */
	public Boolean checkForOutOfBounds() {
		// check for values greater than upper bound
		for (int i = 0; i < arr.size(); i++) {
			float value = arr.get(i);
			if (value > upperBound || value < lowerBound) {
				//do something
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Overloaded method: Checks whether the values in the arrayList are in bounds
	 * 	For use in appendData method
	 * @return TRUE = THE ARRAY CONTAINS INVALID NUMS true or false whether the array contains all valid nums. If this is not the case the GUI method that called
	 * this will empty the array
	 */
	public Boolean checkForOutOfBounds(ArrayList<Float> arr) {
		// check for values greater than upper bound
		for (int i = 0; i < arr.size(); i++) {
			float value = arr.get(i);
			if (value > upperBound || value < lowerBound) {
				//do something
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * return a report as string of the data
	 * @param arr
	 * @return the string used to be printed into the big display log
	 */
	public String analyzeData(ArrayList<Float> arr) {
		//TODO: figure this out
		return "";
	}
	/**
	 * findMedian sorts the given ArrayList, then finds and returns the median. 
	 * @param arr
	 * @return
	 */
	public float findMedian() {
		Collections.sort(arr);
		float middle = arr.size()/2;
		//when the length is odd
		if (arr.size() % 2 == 1) {
			middle = (arr.get(arr.size()/2) + arr.get(arr.size()/2 - 1))/2;
	    } 
		//when the length is even
		else {
			middle = arr.get(arr.size() / 2);
	    }
		return middle;
	}
	
	/**
	 * findMode will find the mode of the given arraylist
	 * @param arr
	 * @return the mode of the array
	 */
	public float findMode(ArrayList<Float> arr) {
		//TODO: figure this out
		float mode = 0;
		
		return mode;
	}
	
	public static String displayData(ArrayList<Float> arr) {

		return "";
	}
	
	public static String printColumns(ArrayList<Float> arr) {
		
		return "";
	}
	
	/**
	 * add values to array if they are all within bounds.
	 * @param arrList - ArrayList of values from input file 
	 * @return true if they were added, false if not added
	 */
	public static boolean appendData(ArrayList<Float> arrList) {
		Boolean outOfBoundsFlag = checkForOutOfBounds(arrList);
		//add the values if NONE are out of bounds
		if (outofBoundsFlag == false) {
			for (int i = 0; i < arrList.size(); i++) {
				arr.add(arrList[i]);
			}
			sort();
		}
		
		return outOfBoundsFlag;
	}
	
	/**
	 * delete the first instance if it exists
	 * @param value
	 * @return true if we deleted the value
	 */
	public Boolean deleteValue(float value) {
		Boolean valuePresent = false;
		for (int i = 0; i < arr.size(); i++) {
			if (valuePresent == false && arr.get(i) == value) { 
				valuePresent = true;
				arr.remove(i);
			}
		}
		
		return valuePresent;
	}
	
	/**
	 * add value to array if it is within bounds
	 * @param value - value to add
	 * @param upperBound - lower bound
	 * @param lowerBound - upper bound
	 * @return true if add, false if not
	 */
	public static boolean addValue(float value) {
		Boolean outOfBoundsFlag = false;
		
		if (value > upperBound || value < lowerBound) {
			outOfBoundsFlag = true;
		}
		else {
			arr.add(value);
		}
		
		return outOfBoundsFlag;
	}
	
	/**
	 * Tells if the Dataset is empty
	 * @return true if Dataset is empty, false if Dataset has values
	 */
	public Boolean dataIsEmpty() {
		if (arr.size() == 0) {
			return true;
		}
		return false;
	}
	
	public String showDistribution(ArrayList<Float> arr, int upperBound) {
		
		return "";
	}
	

	
	public String displayGraph(ArrayList<Float> arr, int upperBound) {
		return "";
	}
	
	/**
	 * Sends report info to be made into a report file.
	 * @return historyLog - log of all actions
	 */
	public String createReport() {
		//could append certain status values to historyLog that 
		//may not have been generated by calling those methods?
		return historyLog;
	}

	
}
	