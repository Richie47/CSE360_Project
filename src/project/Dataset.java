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
	public int lowerBound = 0; //current lower bound default 0
	public int upperBound = 100; //current upper bound default 100
	
	
	
	
	/**
	 * returns the name of file type (csv, txt)
	 * @param filename
	 * @return either csv or txt 
	 */
	public static String getExtension(String fileName) {
		int lastIndexOfDot = fileName.lastIndexOf('.');
		 
		String fileExtension = null;
		if (lastIndexOfDot > 0) {
		    fileExtension = fileName.substring(lastIndexOfDot+1);
		}
		
		return fileExtension;
	}
	
	
	
	/**
	 * parse text file,
	 * there should be one value per line
	 * 
	 * if file is valid, return array list of values
	 * else return error message that will be displayed in the error report
	 * @param file
	 */
	public String parseTxt(File file)  {
		try {
			Scanner s = new Scanner(file);
	
			int count = 0;
			while(s.hasNextLine()) {
				String curEntry = s.nextLine();
				
				if(isNumeric(curEntry) == false) {
					return "Violation at index: " + count + " this invalid character {" + curEntry + "} is not a valid float value. File rejected\n";
				}
				float next = Float.parseFloat(s.nextLine()); // otherwise we go parse
				if(checkForOutOfBounds(next) == false) {
					arr.add(next);
				}
				else {
					return "Violation on line " + count + " the number {" + next +"} is out of bounds. File has not been added\n";
				}
				count++;
			
			}
			
			if(arr.size() == 0) {
				return "File appeared to be empty, please try again\n";
			}
			
			s.close();
			return "Import from File\nData from file \"" + file.getName() + "\" has been added successfully.\n\n";
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
	public String parseCsv(File file) {
		try {
			Scanner s = new Scanner(file);
			int count = 0;
			while (s.hasNextLine()) {
				String[] data = s.nextLine().split(",");
				for (String d: data) {
					if(isNumeric(d) == false) {
						return "Violation at index: " + count + " this invalid character {" + d + "} is not a valid float value. File rejected\n";
					}
					if(checkForOutOfBounds(Float.parseFloat(d)) == false) {
						arr.add(Float.parseFloat(d));
					}
					
					else {
						//work with shashank
						arr.clear(); // clear array because of BOUNDS violation
						return "Violation at index: " + count + " the number {" + d + "} is out of bounds. File has not been added\n";
						
					}
					count++;
				}
				
			}

			s.close();
			if(arr.size() == 0) {
				return "File appeared to be empty, please try again\n";
			}
			//shashank
			return "Import from File\nData from file \"" + file.getName() + "\" has been added successfully.\n\n";
		} catch (FileNotFoundException e) {
			//error person do this part
			return "some error";
		}
	}
	
	/**
	 * 
	 * @param lowerBound
	 * @param upperBound
	 * @param list 
	 * @return TRUE = THE ARRAY CONTAINS INVALID NUMS true or false whether the array contains all valid nums. If this is not the case we must clear the array and return
	 * */
	public Boolean checkForOutOfBounds(float num) {
		// check for values greater than upper bound
		for (int i = 0; i < arr.size(); i++) {
			float value = arr.get(i);
			if (value > upperBound) {
				arr.clear();
				return true;
			}
			if (value < lowerBound) {
				arr.clear();
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * get the size of the arrayList
	 * @return the size
	 */
	
	public int getSize() {
		return arr.size();
	}
	
	
	public boolean isNumeric(String strNum) {
		if (strNum == null) {
	        return false;
	    }
	    try {
	        float f = Float.parseFloat(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
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


	/**
	 * mostly used for debugging to ensure the values are being added correctly
	 * @return the elements of the array
	 */
	public String printArray() {
		String currentAry = "";
		for(float f : arr) {
			currentAry += f + " ";
		}
		return currentAry;
	}

	
}
	