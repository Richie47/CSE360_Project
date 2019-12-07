package project;

import java.awt.*;	//several functions used to build the GUI
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

import javax.swing.*; //several functions used to build the GUI
import javax.swing.filechooser.FileNameExtensionFilter;


public class Design {
	
	ErrorReporter ErrorLog = new ErrorReporter();
	private Dataset ca = new Dataset(ErrorLog);

	JFrame appMain; //central app


	/**
	 * Create the application.
	 */
	public Design() {
		createApp();
	}

	/**
	 * Initialize the contents of the application
	 */
	private void createApp() {
		
		ErrorReporter newError = new ErrorReporter();
		Dataset ds = new Dataset(ErrorLog);
		
			
		appMain = new JFrame("DR.CALLISS AMAZING ANALYTICS");
		appMain.setBounds(100, 100, 800, 600);
		appMain.setLocationRelativeTo(null);
		appMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		appMain.getContentPane().setLayout(null);
		
		JPanel mainPanel = new JPanel();
		// create text area
		JScrollPane scrollPane = new JScrollPane();
		JTextArea textArea = new JTextArea(50,200);
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		String lower, upper;
		lower = JOptionPane.showInputDialog(null, "Enter lower range (default = 0)", "Set Boundaries", JOptionPane.PLAIN_MESSAGE);
		upper = JOptionPane.showInputDialog(null, "Enter upper range (default = 100)", "Set Boundaries", JOptionPane.PLAIN_MESSAGE);
		int lowerBound = 0;
		int upperBound = 100;

		if(ds.isNumeric(lower) && ds.isNumeric(upper)) {
			lowerBound = Integer.parseInt(lower);
			upperBound = Integer.parseInt(upper);
			if(ds.setBoundaries(lowerBound, upperBound)) {
				textArea.append("Lower and upper bounds updated to " + lowerBound + " and " + upperBound + "\n");
			}
			else {
				textArea.append("Error bounds, using default settings 0-100\n");
			}
			
		}

	
		
		/**
		 * Upload Button
		 * 
		 * we create the button and add an action listener that allows user to upload a file
		 * from there we filter to make sure it's a .txt or .csv
		 * 
		 * if the file is not either these extensions we simply reject the file and report the error in the giant textbook, however if it's valid we have two cases:
		 * 
		 * CASE .TXT:
		 * if that is met, then for .txt we go line by line and extract the current number, then make sure it's within the range of the lower and upper bound
		 * if that is met, then we add the number to the ArrayList inputList, and repeat
		 * 
		 * CASE .TXT ERRORS:
		 * If at any point a value is NOT met within the bounds, we need to clear the ArrayList, then report that the file is invalid due to the current integer in the giant textbox
		 *
		 *
		 *CASE .CSV
		 * just separate by comma every line and do something similar above.
		 */

		JButton btnUploadFile = new JButton("Upload File");
		btnUploadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				// Get file 
				JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt", "text", "csv");
				fc.setFileFilter(filter);
				String parsed = "";
				if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					
					// get selected file
					File file = fc.getSelectedFile();
									
					String typeFile = ds.getExtension(file.getName());
					
					
					switch (typeFile) {
					case "txt":
						parsed = ds.parseTxt(file);
						break;
					case "csv":
						parsed = ds.parseCsv(file);
						break;
					default:
						//here for error part
						return;
					}
					
					textArea.append(parsed + "\n");
					textArea.append(ds.printArray() + "\n");
				}
				else {
					textArea.append("Data was not added due to unknown error\n");
				}
			}
		}
		);
			
		JButton btnSetBounds = new JButton("SET BOUNDS");
	
		
	
	    
	    Dimension preferredSize = appMain.getSize();
	    mainPanel.setPreferredSize(preferredSize);
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints matrix = new GridBagConstraints();
		matrix.insets = new Insets(15, 5, 15, 5); //pads room inbetween each button
		
		//add top row buttons
		mainPanel.add(btnSetBounds, matrix);

		btnSetBounds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String lower, upper;
				lower = JOptionPane.showInputDialog(null, "Enter lower range (default = 0)", "Set Boundaries", JOptionPane.PLAIN_MESSAGE);
				upper = JOptionPane.showInputDialog(null, "Enter upper range (default = 100)", "Set Boundaries", JOptionPane.PLAIN_MESSAGE);
				int lowerBound = 0;
				int upperBound = 100;
		
				if(ds.isNumeric(lower) && ds.isNumeric(upper)) {
					lowerBound = Integer.parseInt(lower);
					upperBound = Integer.parseInt(upper);
					if(ds.setBoundaries(lowerBound, upperBound)) {
						textArea.append("Lower and upper bounds updated to " + lowerBound + " and " + upperBound + "\n");
					}
					else {
						textArea.append("Error bounds, using default settings 0-100\n");
					}
					
					
				}
		
				textArea.append("Lower and upper bounds updated to " + lowerBound + " and " + upperBound + "\n");
				
			}
		});
		mainPanel.add(btnUploadFile, matrix);
	
		JButton btnErrorReport = new JButton("ERROR REPORT");
		btnErrorReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String error = ErrorLog.getErrorHistory();
				textArea.append("-----------ERROR REPORT-----------------------------------------------\n" + error);
			}});
		
		mainPanel.add(btnErrorReport);
		

		
		JPanel header = new JPanel(new GridLayout(1,0));
		//set up GridBagLayout, using x and y coordinates to represent each location of the space allocated for the layout (the center of the screen)
		matrix.gridx = 1;
		matrix.gridy = 0;
		matrix.weightx = 1;
		matrix.fill = GridBagConstraints.HORIZONTAL;
		
		mainPanel.add(header, matrix);
		
		
		// add constraints
		matrix.gridx = 1;
		matrix.gridy = 1;
		matrix.weightx = 1;
		matrix.gridheight = 9;
		matrix.fill = GridBagConstraints.BOTH;
		mainPanel.add(scrollPane, matrix);
		
		// button constraints
		matrix.gridheight = 1;
		matrix.weightx = 0;
		matrix.gridx = 0;
		matrix.anchor = GridBagConstraints.WEST;
		matrix.fill = GridBagConstraints.HORIZONTAL;
		

		
		// ADD BUTTON CODE
		JButton btnAddValue = new JButton("Add Value");
		btnAddValue.setSize(60, 20);
		btnAddValue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String response;
				response = JOptionPane.showInputDialog(null, "Enter value to add", "Add Value", JOptionPane.PLAIN_MESSAGE);
				boolean isValid = ds.isNumeric(response);
				if(isValid) {
					boolean isAdded = ds.addValue(Float.parseFloat(response));
					if(isAdded) {
						textArea.append("Value {" + response + "} was not added\n");
					}
					else {
						textArea.append("Value {" + response + "} added successfully\n");
					}
					}
				
				else {
					textArea.append("{" + response + "} is not a valid float value. Will not be added\n" );
				}
				
			} }
		);
		matrix.gridx = 0;
		matrix.gridy = 1;
		mainPanel.add(btnAddValue, matrix);
		
		// DELETE BUTTON CODE
		JButton btnDelValue = new JButton("Delete Value");
		btnDelValue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String response;
				response = JOptionPane.showInputDialog(null, "Enter value to delete", "Delete Value", JOptionPane.PLAIN_MESSAGE);
				
				boolean isValid = ds.isNumeric(response);
				if(isValid) {
					boolean isDeleted = ds.deleteValue(Float.parseFloat(response));
					if(isDeleted) {
						textArea.append("Value {" + response+ "} was found and deleted!\n");
					}
				
				}
				
				else {
					textArea.append("Value {" + response + "} was not valid, will not be added\n");
				}
			}
		});
		matrix.gridy = 2;
		mainPanel.add(btnDelValue, matrix);
		
		// APPEND DATA FROM FILE CODE
		JButton btnAppend = new JButton("Append From File");
		btnAppend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
					// Get file 
					JFileChooser fc = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt", "text", "csv");
					fc.setFileFilter(filter);
					String parsed = "";
					
					if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
						
						// get selected file
						File file = fc.getSelectedFile();
										
						String typeFile = ds.getExtension(file.getName());
		
						ArrayList<Float> list;
						switch (typeFile) {
						case "txt":
							parsed = ds.parseTxt(file);
							break;
						case "csv":
							parsed = ds.parseCsv(file);
							break;
						default:
							//here for error part
							return;
						}
						
						
						
						textArea.append(parsed);
					
				}
					else {
						textArea.append("File was not of an appropiate extension type, please try again\n");
					}
			}}
		
		);

	
		matrix.gridy = 3;
		mainPanel.add(btnAppend, matrix);
		
		
		// EXPORT REPORT BUTTON CODE
		JButton btnExport = new JButton("Export Report");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String confirmation = ds.createReport();
				textArea.append(confirmation + "\n");
			}
			}
		);
	
		matrix.gridy = 4;
		mainPanel.add(btnExport, matrix);
		/**
		// START OVER BUTTON CODE
		JButton btnStartOver = new JButton("Start Over");
		btnStartOver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
			}
		});
		matrix.gridy = 6;
		mainPanel.add(btnStartOver, matrix);
		**/
		
		matrix.gridx = 2; //display right side buttons
		
		
		// DISPLAY DATA BUTTON CODE
		JButton btnDisplayData = new JButton("Display Data");
		btnDisplayData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					String data = ds.displayData();
					textArea.append(data + "\n");
			}
		});
		matrix.gridy = 1;
		
		mainPanel.add(btnDisplayData, matrix);

		// ANALYZE DATA BUTTON CODE
		JButton btnAnalyzeData = new JButton("Analyze Data");
		btnAnalyzeData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
					String data = ds.analyzeData();
					textArea.append(data + "\n");
			}
		});

		matrix.gridy = 2;
		mainPanel.add(btnAnalyzeData, matrix);
		
		// DISPLAY GRAPH BUTTON CODE
		JButton btnDisplayGraph = new JButton("Display Graph");
		btnDisplayGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
					String data = ds.displayGraph();
					textArea.append(data + "\n");
			}
		});
		matrix.gridy = 3;
		mainPanel.add(btnDisplayGraph, matrix);
				
		// SHOW DISTRIBUTION BUTTON CODE
		JButton btnShowDistribution = new JButton("Show Distribution");
		btnShowDistribution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
					String data = ds.showDistribution();
					textArea.append(data + "\n");
			}
		});
		matrix.gridy = 4;
		mainPanel.add(btnShowDistribution, matrix);
		
		JButton btnClearScreen = new JButton("Clear Screen");

		btnClearScreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});
		matrix.gridy = 6;

		matrix.anchor = GridBagConstraints.SOUTHEAST;
		matrix.fill = GridBagConstraints.NONE;
		mainPanel.add(btnClearScreen, matrix);
		
		int pad = 15;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    mainPanel.setSize(screenSize.width, screenSize.height);
		appMain.setLayout(new BorderLayout(pad, pad));
		appMain.add(mainPanel);
	  

	}
	
}
