

/**
 *  the design class implements the error report functions and returns the error report to the main app.
 *  
 *  @author Shashank Ginjpalli
 *  @author Wyatt Turner
 *  @author Richard McKinnon
 *  @author Rachel Ware
 *  @version 1.0
 *  
 *  @team 14
 * 
 *  Class ID: 70641
 *  Final Project
 */

package project;


//not sure how we would like to structure this
public class ErrorReporter {
	private String errorHistory = ""; // used to store the history of the errors
	
	
	/**
	 * 
	 * @param errorString
	 * @param lineNumber
	 * @return a string that can be displayed to the user
	 * 
	 * creates the ErrorString and calls the append to history function
	 * 
	 */
	public String createError(String errorString, String lineNumber) {
		String error = errorString + lineNumber + "\n";
		appendToHistory(error);
		
		return error; 
	}
	
	/**
	 * Error Function for CSV files
	 * 
	 * @param errorString
	 * @param indexNumber
	 * @param value
	 * @return
	 */
	public String createError(String errorString, String indexNumber , String value) {
		String error = errorString + " : " +indexNumber + " {" + value + "} \n"; 
		appendToHistory(error);
		
		return error; 
	}
	
	
	/**
	 * 
	 * @param errorString
	 * @return
	 * 
	 * Overloaded createError function for the errors that dont have a line number
	 */
	public String createError(String errorString) {
		String error = errorString + "\n";
		appendToHistory(error);
		
		return error;
	}
	
	/**
	 * 
	 * @param Error
	 * 
	 * Appends to the error history
	 */
	private void appendToHistory(String Error) {
		errorHistory = errorHistory + Error;
	}
	
	/**
	 * 
	 * @return string that contains the error history
	 * 
	 * 
	 */
	public String getErrorHistory() {
		return errorHistory;
	}
	
	/**
	 * clears the error history
	 */
	public void clearErrorHistory() {
		errorHistory = "";
	}
	
}

