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
	 * Sorts the array of the Dataset in descending order.
	 */
	public void sort() {
		//insertion sort
		for (int i = 1; i < arr.size(); i++) {
			int j = i - 1;
			float value = arr.get(i);
			while (j >= 0 && arr.get(j) < value) {
				arr.set(j+1, arr.get(j));
				j--;
			}
			arr.set(j + 1, value);
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
	 * 
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
	 * 
	 * @return the string used to be printed into the big display log
	 */
	public String analyzeData() {
		//TODO: May need to figure out where error handling is needed.
		
		//REMEMBER TO ADD HISTORY LOG 
		
		String analysis = "Mean: " + findMean() + "\n";
		analysis = analysis + "Median: " + findMedian() + "\n";
		analysis = analysis + "Mode: " + findMode() + "\n";
		analysis = analysis + "High: " + arr.get(0) + "\n";   //since sorted gets largest value
		analysis = analysis + "Low: " + arr.get(arr.size() - 1) + "\n";  //since sorted gets smallest value
		return analysis;
	}
	
	/**
	 * Finds and returns the median. 
	 * 
	 * @return
	 */
	private float findMedian() {
		
		//REMEMBER TO ADD HISTORY LOG 
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
	 * Calculates and returns mean of filled data set
	 * 
	 * @return value of the mean of this data set
	 */
	private float findMean()
	{
		
		//REMEMBER TO ADD HISTORY LOG 
		
		float mean = (float) 0.0;
		float sum = (float) 0.0;
		
		for(int i = 0; i < arr.size(); i++)  //loops through each item in dataset
		{
			sum = sum + arr.get(i);
		}
		
		mean = sum/arr.size();
		
		return mean;
	}
	
	/**
     * Calculates mode of current data set
     * 
     * @return the mode of the array
     */
    private float findMode()    //Part of the logic of this method relies on the dataset being sorted and having at least 1 item
    {
    	//REMEMBER TO ADD HISTORY LOG 
    	
        float mode = arr.get(0);
        int amount = 1;  //amount 'mode' appears in dataset

        for(int i = 0; i < arr.size(); i++)
        {
            float current = arr.get(i); 
            if(arr.get(i + 1) == current)   //if next item is equal to current item
            {
                //Going to go into a nested loop to find # of times this data value occurs in dataset.
                 
                int nestedIndex = i;           //index of miniloop looking at, initializes to beginning element  
                int nestedAmount = 1;          //will get compared to 'amount' to see if larger
                while(nestedIndex != arr.size() - 1 && current == arr.get(nestedIndex + 1))   //runs if nestedIndex is not last index in arrayList and if next item is same as current
                {
                    //shifts values to next item over in arraylist
                    current = arr.get(nestedIndex + 1);
                    nestedIndex++;
                    nestedAmount++;
                }
                
                //If the series of items that occured in nested is larger than the previous largest series
                if(nestedAmount > amount)
                {
                    //Sets new mode and amount
                    mode = current;
                    amount = nestedAmount;
                }

                i = nestedIndex;   //goes to where the nested loop left off.

            }
            
        }
        return mode;
    }
	
    
	public String displayData() {

		return "";
	}
	
	public String printColumns() {
		
		return "";
	}
	
	/**
	 * add values to array if they are all within bounds.
	 * @param arrList - ArrayList of values from input file 
	 * @return true if they were added, false if not added
	 */
	public boolean appendData(ArrayList<Float> arrList) {
		Boolean outOfBoundsFlag = checkForOutOfBounds(arrList);
		//add the values if NONE are out of bounds
		if (outOfBoundsFlag == false) {
			for (int i = 0; i < arrList.size(); i++) {
				arr.add(arrList.get(i));
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
	 * 
	 * @return true if add, false if not
	 */
	public boolean addValue(float value) {
		Boolean outOfBoundsFlag = false;
		
		if (value > upperBound || value < lowerBound) {
			outOfBoundsFlag = true;
		}
		else {
			arr.add(value);
			sort();
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

	public String showDistribution(int upperBound) {
		
		return "";
	}
	
	/**
	 * Creates a veritical ascii bar graph of all the data entries with in the dataset.
	 * 
	 * @return formatted string of ascii bar graph.
	 */
	public String displayGraph() {
		
		
		int[] bins = binCreator();  //bins holds number of data points with in each 10%
		
		String graph = " 0% to   9%| ";
		
		//For formatting to be correct I handle 0%-9% and 90%-100% seperately
		
		for(int i = 0; i < bins[0]; i++)  //loops for # of times there are values in bin 0-9%
		{
			graph = graph + "*";          
		}
		graph = graph + "\n";
		
		//Handles all bins from 10%-19% to 80% - 89%
		for(int i = 1; i <= 8; i++)
		{
			graph = graph + i + "0% to  " + i + "9%| ";  //creates the string on left part of graph like "10% - 19%"
			
			for(int j = 0; j < bins[i]; j++)
			{
				graph = graph + "*";
			}
			graph = graph + "\n";
		}
		
		
		//Handles bin 90% to 100%
		graph = graph + "90% to 100%| ";
		for(int i = 0; i < bins[9]; i++)
		{
			graph = graph + "*";
		}
		graph = graph + "\n";
		
		return graph;
	}
	
	/**
	 * Helper function to displayGraph. Figures out how many data values are in each 10% segment.
	 * 
	 * @return integer array of number of data points in each 10% bin
	 */
	private int[] binCreator()
	{
		int[] bins = new int[10];  //holds the number in each 10% bin. 0th bin being 0 <= point < 10
		int range = (upperBound - lowerBound)/10;
		
		for(int i = 0; i < arr.size(); i++)  //loops through every data value, starting with highest valued data point
		{

			Float val = arr.get(i);
			
			if(val >= lowerBound && val < (lowerBound + range*1)) {
				bins[0] ++;
			}
			if(val >= (lowerBound + range*1) && val < (lowerBound + range*2)) {
				bins[1] ++;
			}
			if(val >= (lowerBound + range*2) && val < (lowerBound + range*3)) {
				bins[2] ++;
			}
			if(val >= (lowerBound + range*3) && val < (lowerBound + range*4)) {
				bins[3] ++;
			}
			if(val >= (lowerBound + range*4) && val < (lowerBound + range*5)) {
				bins[4] ++;
			}
			if(val >= (lowerBound + range*5) && val < (lowerBound + range*6)) {
				bins[5] ++;
			}
			if(val >= (lowerBound + range*6) && val < (lowerBound + range*7)) {
				bins[6] ++;
			}
			if(val >= (lowerBound + range*7) && val < (lowerBound + range*8)) {
				bins[7] ++;
			}
			if(val >= (lowerBound + range*8) && val < (lowerBound + range*9)) {
				bins[8] ++;
			}
			if(val >= (lowerBound + range*9) && val < upperBound) {
				bins[9] ++;
			}
			

		}
		
		return bins;
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
	