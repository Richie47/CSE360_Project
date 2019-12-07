package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * NOTE THIS DOES NOT HAVE ALL THE FUNCTIONS REQUIRED, YOU'LL HAVE TO ADD WHAT YOU NEED AS NEEDED.
 * 
 * OUR OVERALL IDEA IS THAT THE GUI (DESIGN.JAVA) IS GOING TO CALL ONE OF THESE HELPER FUNCTIONS
 * @author Richie
 *
 */

public class Dataset {
	private ArrayList<Float> arr = new ArrayList<Float>();  //used to store extracted input
	private ErrorReporter ErrorLog; //use an ErrorReporter for this class
	private String historyLog; //used for the overall export history
	private int lowerBound = 0; //current lower bound default 0
	private int upperBound = 100; //current upper bound default 100
	
	// Array Lists in order to store the grades 
	private ArrayList<Float> dist0 = new ArrayList<Float>();
	private ArrayList<Float> dist1 = new ArrayList<Float>();
	private ArrayList<Float> dist2 = new ArrayList<Float>();
	private ArrayList<Float> dist3 = new ArrayList<Float>();
	private ArrayList<Float> dist4 = new ArrayList<Float>();
	private ArrayList<Float> dist5 = new ArrayList<Float>();
	private ArrayList<Float> dist6 = new ArrayList<Float>();
	private ArrayList<Float> dist7 = new ArrayList<Float>();
	private ArrayList<Float> dist8 = new ArrayList<Float>();
	private ArrayList<Float> dist9 = new ArrayList<Float>();
	
	
	
	/**
	 * Constructor for Dataset
	 * @param err - ErrorReporter used in Design class also used here
	 */
	public Dataset(ErrorReporter errorLog) {
		ErrorLog = errorLog;
		//arr = array;
		//sort();
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
		
		historyLog += "User Set Bounds  ----  Lower Bound: " + lowerBound + "Upper Bound: " + upperBound +"\n"; 
		
		Boolean dataInvalid = checkForOutOfBounds();
		if (dataInvalid == false) {
			//give error
			ErrorLog.createError("Previously entered data out of bounds");
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
	 * Gathers info on the data set like mean, median, mode. 
	 * 
	 * @return the string with information on dataset's statistical information
	 */
	public String analyzeData() {
		
		//check for empty dataset
		if(dataIsEmpty())
		{
			historyLog = historyLog + "Couldn't run Analysis, empty dataset.\n";
			ErrorLog.createError("Error: Failure to Analyze, no dataset");
			return "Unable to Analyze, no dateset.\n";
		}
		
		historyLog = historyLog + "Ran Analysis on Data set.\n";
		
		//TODO: May need to figure out where error handling is needed.
		float range = arr.get(0) - arr.get(arr.size() - 1);
		//REMEMBER TO ADD HISTORY LOG 
		
		String analysis = "Mean: " + findMean() + "\n";
		analysis = analysis + "Median: " + findMedian() + "\n";
		analysis = analysis + "Mode: " + findMode() + "\n";
		analysis = analysis + "Range: " + range + "\n";
		analysis = analysis + "High: " + arr.get(0) + "\n";   //since sorted gets largest value
		analysis = analysis + "Low: " + arr.get(arr.size() - 1) + "\n";  //since sorted gets smallest value
		return analysis;
	}
	
	/**
	 * Finds and returns the median. 
	 * 
	 * @return value of the median
	 */
	private float findMedian() {
		
		//REMEMBER TO ADD HISTORY LOG 
		float middle = arr.size()/2;
		//when the length is even
		if (arr.size() % 2 == 0) {
			middle = (arr.get(arr.size()/2) + arr.get(arr.size()/2 - 1))/2;;
	    } 
		//when the length is odd
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
	 * Clears dataset for when uploading new dataset.
	 * 
	 * 
	 */
	private void clearDataset()
	{
		historyLog = historyLog + "Uploading new file.\n";
		arr.clear();
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
	
    
    /**
     * Creates a formatted string displaying all the data entries in 4 columns in descending order. 
     * 
     * @return Formatted string displaying the 4 descending columns.
     */
    
    public String displayData() {
    	
    	//check for empty dataset
    			if(dataIsEmpty())
    			{
    				historyLog = historyLog + "Couldn't run Analysis, empty dataset.\n";
    				ErrorLog.createError("Error: Failure to Display, no dataset");
    				return "Could not display, no dataset.";
    			}
    	
    	historyLog = historyLog + "Displayed Data Columns.\n";
    	
        int columnLength = arr.size()/4;    //gets the size of a quarter of the columns.
        
		int remainder = arr.size()%4;       //remainder used to calculate which of 4 columns need additional value.
		
		//This is really ugly but creates variables for four columns and variables for beginning and ending index.
		List<Float> columnFirst;
		int firstEnd;  //used to tell where first column to stop (exclusive) and second column to start (inclusive)
		
		List<Float> columnSecond;
		int secondEnd;
		
		List<Float> columnThird;
		int thirdEnd;
		
		List<Float> columnFourth;
		
		
		//gets indexes for first column
		if(remainder > 0)   //if remainder than add 1 to this column's length
		{
            firstEnd = columnLength + 1;  //adding 1 to length since there was enough of a remainder.
		}
		else
		{
			firstEnd = columnLength;
		}
        columnFirst = arr.subList(0, firstEnd);
        
		//gets indexes for second column
		if(remainder > 1)   //if still another remainder than add 1 to this column's length
		{
			
            secondEnd =   firstEnd + columnLength + 1;  //gets end point from 
		}
		else
		{
			secondEnd =   firstEnd + columnLength;
        }
        
        columnSecond = arr.subList(firstEnd, secondEnd);
        
		//gets indexes for third column
		if(remainder > 2)   //if still another remainder than add 1 to this column's length
		{
			thirdEnd =   secondEnd + columnLength + 1;
		}
		else
		{
			thirdEnd =   secondEnd + columnLength;
		}
        columnThird = arr.subList(secondEnd, thirdEnd);
        
        columnFourth = arr.subList(thirdEnd, arr.size());

		//Now the actual creation of formatted string
		String toReturn = "";
		
		for(int i = 0; i < columnFirst.size(); i++)   //will loop through every value in first column (will always be longest
		{
			toReturn = toReturn + columnFirst.get(i) + "    ";
			
			if(i < columnFirst.size() - 1 || columnSecond.size() == columnFirst.size()) //if we are before last value OR column lengths are same then it is okay to look at columnSecond
			{
				toReturn = toReturn + columnSecond.get(i) + "    ";
			}
			
			if(i < columnFirst.size() - 1 || columnThird.size() == columnFirst.size()) //if not last row OR column lengths equal
			{
				toReturn = toReturn + columnThird.get(i) + "    ";
			}
			
			if(i < columnFirst.size() - 1 || columnFourth.size() == columnFirst.size())  //if not last row OR column lengths equal
			{
				toReturn = toReturn + columnFourth.get(i);
			}
			
			toReturn = toReturn + "\n";
		}
		
		return toReturn;
	}
	
	
	/**
	 * add values to array if they are all within bounds.
	 * @param arrList - ArrayList of values from input file 
	 * @return true if they were added, false if not added
	 */
    /*
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
	*/
	
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
				historyLog = historyLog + "Successfully removed " + value + " from data set.\n";
			}
		}
		
		if(!valuePresent)  //if value not found.
		{
			historyLog = historyLog + "Could not remove " + value + " from data set.\n";
			ErrorLog.createError("Error: Value not Found, failure to remove " + value);
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
			historyLog = historyLog + "Failed to add value " + value + " to data set. (Out of bounds).\n";
		}
		else {
			arr.add(value);
			historyLog = historyLog = "Added value " + value + "to data set.\n";
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

	
	/**
	 * Creates the output string of the distributions and the average 
	 * @return
	 */
	public String showDistribution() {
		
		//check for empty dataset
		if(dataIsEmpty())
		{
			historyLog = historyLog + "Couldn't run Analysis, empty dataset.\n";
			ErrorLog.createError("Error: Failure to Distribute, no dataset");
			return "Unable to show distribution, no dateset\n";
		}
		
		historyLog = historyLog + "Showed distribution of data points.\n";
		
		String output = "";
		// gets the number for the different ranges
		int[] bins = binCreator();
		// gets the average for each bracket
		Float [] average = calculateAveragePerRange();
		
		
		// creating the output table for distributions
		String averageString = "";
		
		output = output + "   Range \t# \tAverage(%) \n";
		output = output + "----------------------------------\n";
		if (average[0] == 0.0) {
			averageString = "----";
		}else {
			averageString = Float.toString(average[0]);
		}
		
		output = output + "    0% - 9%: \t" + bins[0] + "\t" + averageString + "\n";
		
		for(int i = 1; i <= 8; i ++) {
			if (average[i] == 0.0) {
				averageString = "----";
			}else {
				averageString = Float.toString(average[i]);
			}
			
			output = output +"  " + i +  "0% - " + i + "9%: \t" + bins[i] + "\t" + averageString + "\n";
		}
		
		if (average[9] == 0.0) {
			averageString = "----";
		}else {
			averageString = Float.toString(average[9]);
		} 
		
		output = output + " 90% - 100%: \t" + bins[9] + "\t" + averageString + "\n";
		
		return output;
	}
	
	/**
	 * Takes the grades from each distribution and creates an average out of them
	 * @return average array that contains average for each bracket
	 */
	
	private Float[] calculateAveragePerRange() {
		Float [] average = new Float[10];
		
		// initializing the average array to 0
		for(int i = 0; i < average.length; i ++) {
			average[i] = (float) 0;
		}
		
		// making an array of type arraylist to access the different distributions
		ArrayList [] distributions = new ArrayList[10];
		
		distributions[0] = dist0;
		distributions[1] = dist1;
		distributions[2] = dist2;
		distributions[3] = dist3;
		distributions[4] = dist4;
		distributions[5] = dist5;
		distributions[6] = dist6;
		distributions[7] = dist7;
		distributions[8] = dist8;
		distributions[9] = dist9;
		
		// used to iterate through the distributions array
		for(int list = 0; list <= 9; list ++) {
			Float sum = (float) 0;
			// used to iterate through each distribution
			for(int j = 0; j < distributions[list].size(); j ++ ) {
				ArrayList<Float> temp = new ArrayList<Float>();
				temp = distributions[list];
				Float val = temp.get(j);
				sum += val;
			}
			
			// sets the average to 0 if there is nothing in the distributions arrayList
			if(distributions[list].size() != 0) {
				average[list] = (float) (sum/(distributions[list].size()));
			}else {
				average[list] = (float) (0);
			}
		}
		
		return average;
	}
	
	
	/**
	 * Creates a veritical ascii bar graph of all the data entries with in the dataset.
	 * 
	 * @return formatted string of ascii bar graph.
	 */
	public String displayGraph() {
		
		//check for empty dataset
		if(dataIsEmpty())
		{
			historyLog = historyLog + "Couldn't run Analysis, empty dataset.\n";
			ErrorLog.createError("Error: Failure to Graph, no dataset");
			return "Unable to graph, no dataset.\n";
		}
		
		historyLog = historyLog + "Displayed graph of data set.\n";
		
		int[] bins = binCreator();  //bins holds number of data points with in each 10%
		
		String graph = " 0% to   9%| ";
		
		//For formatting to be correct I handle 0%-9% and 90%-100% seperately
		
		for(int i = 0; i < bins[0]; i++)  //loops for # of times there are values in bin 0-9%
		{
			graph = graph + "*";          
		}
		//graph = graph + "      (" + bins[0] + ")\n";
		graph = graph + "\n";
		
		//Handles all bins from 10%-19% to 80% - 89%
		for(int i = 1; i <= 8; i++)
		{
			graph = graph + i + "0% to  " + i + "9%| ";  //creates the string on left part of graph like "10% - 19%"
			
			for(int j = 0; j < bins[i]; j++)
			{
				graph = graph + "*";
			}
			//graph = graph + "      (" + bins[i] + ")\n";
			graph = graph + "\n";
		}
		
		
		//Handles bin 90% to 100%
		graph = graph + "90% to 100%| ";
		for(int i = 0; i < bins[9]; i++)
		{
			graph = graph + "*";
		}
		//graph = graph + "      (" + bins[9] + ")\n";
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
		
		
		
		for(int i = 0; i < arr.size(); i++)  //loops through every data value, starting with highest valued data point
		{

			Float val = arr.get(i);
			Float percent = (val/upperBound)*100;
			
			
			// add to the appropriate bin and the appropriate distribution array
			if(percent >= 0 && percent < 10) {
				bins[0] ++;
				dist0.add(percent);
			}
			if(percent >= 10 && percent < 20) {
				bins[1] ++;
				dist1.add(percent);
			}
			if(percent >= 20 && percent < 30) {
				bins[2] ++;
				dist2.add(percent);
			}
			if(percent >= 30 && percent < 40) {
				bins[3] ++;
				dist3.add(percent);
			}
			if(percent >= 40 && percent < 50) {
				bins[4] ++;
				dist4.add(percent);
			}
			if(percent >= 50 && percent < 60) {
				bins[5] ++;
				dist5.add(percent);
			}
			if(percent >= 60 && percent < 70) {
				bins[6] ++;
				dist6.add(percent);
			}
			if(percent >= 70 && percent < 80) {
				bins[7] ++;
				dist7.add(percent);
			}
			if(percent >= 80 && percent < 90) {
				bins[8] ++;
				dist8.add(percent);
			}
			if(percent >= 90 && percent <= 100) {
				bins[9] ++;
				dist9.add(percent);
			}
			

		}
		
		return bins;
	}
	
	/**
	 * Sends report info to the report.txt file. 
	 * 
	 * @return String on success of writing
	 */
	public String createReport() {
		
		historyLog += "User Created Report of Actions\n";
		
		try {
			PrintWriter writer = new PrintWriter("report.txt", "UTF-8");
			writer.print(historyLog);
			writer.close();
			return "Report created in report.txt";
		}catch (Exception e)
		{
			historyLog += "Failed report creation\n";
			return "Unable to create report.";
		}
		
	}
	
	
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
					ErrorLog.createError("Violation at index: " + count + " this invalid character {" + curEntry + "} is not a valid float value. File rejected");
					return "Violation at index: " + count + " this invalid character {" + curEntry + "} is not a valid float value. File rejected\n";
				}
				float next = Float.parseFloat(s.nextLine()); // otherwise we go parse
				if(checkForOutOfBounds(next) == false) {
					arr.add(next);
				}
				else {
					ErrorLog.createError("Violation at index: " + count + " the number {" + next + "} is out of bounds. File has not been added");
					return "Violation on line " + count + " the number {" + next +"} is out of bounds. File has not been added\n";
				}
				count++;
			
			}
			
			if(arr.size() == 0) {
				return "File appeared to be empty, please try again\n";
			}
			
			s.close();
			ErrorLog.createError("File appeared to be empty, please try again");
			return "Import from File\nData from file \"" + file.getName() + "\" has been added successfully.\n\n";
		} catch (FileNotFoundException e) {
		ErrorLog.createError("Error File not Found");
		return "Error File not Found";
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
						ErrorLog.createError("Violation at index: " + count + " this invalid character {" + d + "} is not a valid float value. File rejected");
						return "Violation at index: " + count + " this invalid character {" + d + "} is not a valid float value. File rejected\n";
					}
					if(checkForOutOfBounds(Float.parseFloat(d)) == false) {
						arr.add(Float.parseFloat(d));
					}
					
					else {

						arr.clear(); // clear array because of BOUNDS violation
						ErrorLog.createError("Violation at index: " + count + " the number {" + d + "} is out of bounds. File has not been added");

						return "Violation at index: " + count + " the number {" + d + "} is out of bounds. File has not been added\n";
						
					}
					count++;
				}
				
			}

			s.close();
			if(arr.size() == 0) {
				ErrorLog.createError("File appeared to be empty, please try again");
				return "File appeared to be empty, please try again\n";
			}
			return "Import from File\nData from file \"" + file.getName() + "\" has been added successfully.\n\n";
		} catch (FileNotFoundException e) {
			//error person do this part
			ErrorLog.createError("Error CSV not Found");
			return "Error CSV not Found";
		}
	}
	
	/**
	 * 
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
	
	
	/**
	 * Checks whether input can be converted to a float.
	 * 
	 * @param strNum the inputted string in question
	 * @return true if input can be turned into a float.
	 */
	public boolean isNumeric(String strNum) {
		if (strNum == null) {
			ErrorLog.createError("User Did not enter a value");
	        return false;
	    }
	    try {
	        float f = Float.parseFloat(strNum);
	    } catch (NumberFormatException nfe) {
	    	ErrorLog.createError(strNum + " is not a valid float number. Will not be Added");
	        return false;
	    }
	    return true;
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
