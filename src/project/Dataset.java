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
	 * 
	 * @param arr  - the current list that holds the uploaded files value
	 * @param lowerBound
	 * @param upperBound
	 * @return TRUE = THE ARRAY CONTAINS INVALID NUMS true or false whether the array contains all valid nums. If this is not the case the GUI method that called
	 * this will empty the array
	 */
	public Boolean checkForOutOfBounds(int lowerBound, int upperBound) {
		// check for values greater than upper bound
		for (int i = 0; i < arr.size(); i++) {
			float value = arr.get(i);
			if (value > upperBound) {
				//do something
				return true;
			}
			if (value < lowerBound) {
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
	public float findMedian(ArrayList<Float> arr) {
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
	 * delete the first instance if it exists
	 * @param arr
	 * @param value
	 * @return true if we deleted the value
	 */
	public static Boolean deleteValue(ArrayList<Float> arr, float value) {
		Boolean valuePresent = false;
		
		for (int i = 0; i < arr.size(); i++) {
			if (arr.get(i) == value) {
				valuePresent = true;
				arr.remove(i);
			}
		}
		
		return valuePresent;
	}
	
	/**
	 * add value to array if it is within bounds
	 * @param arr - array to add to
	 * @param value - value to add
	 * @param upperBound - lower bound
	 * @param lowerBound - upper bound
	 * @return true if add, false if not
	 */
	public static boolean addValue(ArrayList<Float> arr, float value, int lowerBound, int upperBound) {
		Boolean outOfBoundsFlag = false;
		
		if (value > upperBound || value < lowerBound) {
			outOfBoundsFlag = true;
		}
		else {
			arr.add(value);
		}
		
		return outOfBoundsFlag;
	}
	
	public String showDistribution(ArrayList<Float> arr, int upperBound) {
		
		return "";
	}
	

	
	public String displayGraph(ArrayList<Float> arr, int upperBound) {
		return "";
	}
	
	

	
}
	