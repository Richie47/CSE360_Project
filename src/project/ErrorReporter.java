package project;


//not sure how we would like to structure this
public class ErrorReporter {
	
	private String errorHistory = "";
	
	public String createError(String errorString, String lineNumber) {
		String error = errorString + lineNumber;
		appendToHistory(error);
		
		return error; 
	}
	
	public String createError(String errorString) {
		String error = errorString;
		appendToHistory(error);
		
		return error;
	}
	
	public void appendToHistory(String Error) {
		errorHistory = errorHistory + Error + "\n";
	}
	
	public String getErrorHistory() {
		return errorHistory;
	}
	
}
