package javaApplication;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.ButtonGroup;
import javax.swing.text.MaskFormatter;
import javax.swing.JFormattedTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

//You need to add external Jars to your build path for these excel packages
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//JDatePicker Jar
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.alee.laf.WebLookAndFeel;


public class Attendance_Frame extends JFrame {
	private JPanel contentPane;
	//This hash map is used later on in data validation
	//The key is the text field, and the value is the label that corresponds to that field
	private HashMap<Object, JLabel> fieldLabelMap = new HashMap<Object, JLabel>();
	private HashMap<Object, JLabel> fieldLabelMap2 = new HashMap<Object, JLabel>();
	private JTextField fNameTF;
	private JTextField lNameTF;
	JFormattedTextField zipCodeFTF = new JFormattedTextField();
	private JTextField pleaseSpecifyRaceTF;
	private JTable outputTable;
	private JTextField ageTF;
	private JTextField pleaseSpecifySexTF;
	private JTextField numberOfKidsTF;
	
	private JLabel lblFirstName;
	private JLabel lblLastName;
	private JLabel lblZipcode;
	private JLabel lblRace;
	private JLabel lblSpecifyOtherRace;
	private JLabel lblSex;
	private JLabel lblPleaseSpecifySex;
	private JLabel lblAge;
	private JLabel lblNumberOfKids;
	private JLabel lblZip;
	private static Attendance_Frame frame;
	
	private JRadioButton rdbtnAreYouNew;
	private JRadioButton rdbtnNotFirstClass;
	
	private final ButtonGroup newProgramButtonGroup = new ButtonGroup();
	private final ButtonGroup firstClassButtonGroup = new ButtonGroup();
	private JMenuItem mntmOpen;
	private String instructorName;

	/**
	 * Use to create mask for date, zipcode, ect.
	 */
	public MaskFormatter createFormatter(String s) {
	     MaskFormatter formatter = null;
	     try {
	          formatter = new MaskFormatter(s);
	         } 
	     catch (java.text.ParseException exc) {
		          System.err.println("formatter is bad: " + exc.getMessage());
		          System.exit(-1);
		      }
	      return formatter;
	}
	
	/**
	 * Create the frame.
	 */
	public Attendance_Frame(String instructor, String topic, JDatePickerImpl datePicker, String startTime, String location, String language) {
		getDayString(datePicker);
		instructorName = instructor;
		frame = this;
		WebLookAndFeel.install ();
		setResizable(false);
     	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1033, 574);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		zipCodeFTF.setBounds(77, 495, 178, 22);
		contentPane.add(zipCodeFTF);
		
		
		JLabel lblCpcaAttendance = new JLabel("PEP Attendance Sheet");
		lblCpcaAttendance.setBounds(343, 25, 330, 38);
		lblCpcaAttendance.setFont(new Font("Cambria", Font.PLAIN, 30));
		contentPane.add(lblCpcaAttendance);
		
		lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(17, 303, 79, 30);
		contentPane.add(lblFirstName);
		
		lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(17, 337, 79, 22);
		contentPane.add(lblLastName);
		
		lblZipcode = new JLabel("Zipcode:");
		lblZipcode.setBounds(17, 495, 61, 22);
		contentPane.add(lblZipcode);
		
		lblRace = new JLabel("Race:");
		lblRace.setBounds(17, 402, 61, 22);
		contentPane.add(lblRace);
		
		fNameTF = new JTextField();
		fNameTF.setBounds(91, 303, 164, 30);
		contentPane.add(fNameTF);
		fNameTF.setColumns(10);
		
		lNameTF = new JTextField();
		lNameTF.setBounds(91, 333, 164, 30);
		contentPane.add(lNameTF);
		lNameTF.setColumns(10);
		
		JComboBox raceComboBox = new JComboBox();
		raceComboBox.setBounds(55, 400, 200, 27);
		raceComboBox.setModel(new DefaultComboBoxModel(new String[] {"Choose Race", "White", "African-American", "Hispanic", "Asian", "Other"}));
		contentPane.add(raceComboBox);
		
		pleaseSpecifyRaceTF = new JTextField();
		pleaseSpecifyRaceTF.setBounds(352, 402, 156, 22);
		contentPane.add(pleaseSpecifyRaceTF);
		pleaseSpecifyRaceTF.setColumns(10);
		pleaseSpecifyRaceTF.setVisible(false);
		
		lblSpecifyOtherRace = new JLabel("Please Specify:");
		lblSpecifyOtherRace.setBounds(264, 402, 79, 18);
		lblSpecifyOtherRace.setFont(new Font("Lucida Grande", Font.ITALIC, 10));
		contentPane.add(lblSpecifyOtherRace);
		lblSpecifyOtherRace.setVisible(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 74, 1006, 182);
		contentPane.add(scrollPane);
		
		outputTable = new JTable();
		scrollPane.setViewportView(outputTable);
		//TODO: Rearrange the header cells to have ZipCode and 18&Under rearranged
		DefaultTableModel defaultModel = new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null},
				},
				new String[] {
					"First", "Last", "Date", "Topic", "Day", "Time", 
					"Location", "Language","Sex", "Race", "Age","18&Under", "Zipcode","New"
				}
			);
		defaultModel.setRowCount(0);
		outputTable.setModel(defaultModel);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1032, 22);
		contentPane.add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		
		//TODO: Fix arrangement of header cells, along with adding topic
		//Save to excel file
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				XSSFWorkbook workbook = new XSSFWorkbook();
		        XSSFSheet sheet = workbook.createSheet("Attendance");
		        
		        //Created header for excel sheet
		        Row headerRow = sheet.createRow(0);
		        //First Name
		        Cell firstNameCell = headerRow.createCell(0);
		        firstNameCell.setCellValue("First Name");
		        //Last Name
		        Cell lastNameCell = headerRow.createCell(1);
		        lastNameCell.setCellValue("Last Name");
		        //Date
		        Cell dateCell = headerRow.createCell(2);
		        dateCell.setCellValue("Date");
		        //Day
		        Cell dayCell = headerRow.createCell(3);
		        dayCell.setCellValue("Day");
		        //Time
		        Cell timeCell = headerRow.createCell(4);
		        timeCell.setCellValue("Time");
		        //Location
		        Cell locationCell = headerRow.createCell(5);
		        locationCell.setCellValue("Location");
		        //Language
		        Cell languageCell = headerRow.createCell(6);
		        languageCell.setCellValue("Language");
		        //Sex
		        Cell sexCell = headerRow.createCell(7);
		        sexCell.setCellValue("Sex");
		        //Race
		        Cell raceCell = headerRow.createCell(8);
		        raceCell.setCellValue("Race");
		        //Age
		        Cell ageCell = headerRow.createCell(9);
		        ageCell.setCellValue("Age");
		        //18&Under
		        Cell ageUnderCell = headerRow.createCell(10);
		        ageUnderCell.setCellValue("18 & Under");
		        //Zip
		        Cell zipCodeCell = headerRow.createCell(11);
		        zipCodeCell.setCellValue("Zipcode");
		        //New
		        Cell newCell = headerRow.createCell(12);
		        newCell.setCellValue("New");
		        
		        
		        //Logic for writing to columns here under the header
		        int excelRowCount = 1;
		        int tableRowCount = 0;
		        while(tableRowCount < outputTable.getRowCount()){
		        	int tableColumnCount = 0;
		        	int excelColumnCount = 0;
		        	Row tempRow = sheet.createRow(excelRowCount);
		        	while(tableColumnCount < outputTable.getColumnCount()){
		        		Cell tempCell = tempRow.createCell(excelColumnCount);
		        		if(outputTable.getValueAt(tableRowCount, tableColumnCount) != null){
		        			tempCell.setCellValue(outputTable.getValueAt(tableRowCount, tableColumnCount).toString());
		        		}
		        		tableColumnCount++;
		        		excelColumnCount++;
		        	}
		        	tableRowCount++;
		        	excelRowCount++;
		        }
		        
		        try (FileOutputStream outputStream = new FileOutputStream("Attendance_Test.xlsx")) {
		            workbook.write(outputStream);
		        } catch (IOException e){
		        	System.out.println("IOException: " + e.getMessage());
		        }
			}
		});
		
		mnFile.add(mntmSave);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		mntmOpen = new JMenuItem("Open");
		
		//Utilizes JFileChooser API which is a GUI to select files and get file path
		//TODO: Fix open with topic
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Open file allowing only excel files
				JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "Excel Files (.xlsx)", "xlsx");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(getParent());
			    //When the correct type of file is selected, then read the excel file and populate the table
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	String filePath = chooser.getSelectedFile().getAbsolutePath();
			    	//Opening file and populating to JTable
			    	try {
			    		//Clear table
			    		defaultModel.setRowCount(0);
			    		
			    		//Open excel file and workbook
				    	FileInputStream excelFile = new FileInputStream(new File(filePath));
				    	Workbook workbook = new XSSFWorkbook(excelFile);
				    	Sheet datatypeSheet = workbook.getSheetAt(0);
				    	Iterator<Row> iterator = datatypeSheet.iterator();
				    	
				    	//If this is false, you skip over the header row of the excel sheet
				    	boolean headerRow = false;
				    	while(iterator.hasNext()){
				    		//Array and count to hold string values in each cell
				    		String[] row = new String[13];
				    		int cellCount = 0;
				    		
				    		//Get row in excel sheet
				    		Row currentRow = iterator.next();
				    		
				    		//Skip over header row
				    		if(headerRow == false){
				    			headerRow = true;
				    			continue;
				    		}
				    		
				    		//Iterate through each cell in excel sheet
				    		Iterator<Cell> cellIterator = currentRow.iterator();
				    		while(cellIterator.hasNext()){
				    			Cell currentCell = cellIterator.next();
				    			String cellString = currentCell.getStringCellValue();
				    			if(!cellString.isEmpty()){
				    				row[cellCount] = cellString;
				    				cellCount++;
				    			}
				    		}
				    		
				    		//Add row to the table(Check what type of row you are adding to table by checking if last value is empty)
				    		if(row[12] == null){
				    			defaultModel.addRow(new Object[] {
										row[0],
										row[1],
										row[2],
										row[3],
										row[4],
										row[5],
										row[6]
								});
				    		} else {
				    			defaultModel.addRow(new Object[] {
										row[0],
										row[1],
										row[2],
										row[3],
										row[4],
										row[5],
										row[6],
										row[7],
										row[8],
										row[9],
										row[10],
										row[11],
										row[12]
								});
				    		}
				    	}
				    	
				    } catch (FileNotFoundException e) {
				    	//TODO: If file was not found, do a pop up
				    	System.out.println("File not found");
				    } catch (IOException e) {
				    	//TODO: If something goes wrong with IO, do a pop up
				    	System.out.println("IOException");
				    }
			    }
			}
		});
		mnFile.add(mntmOpen);
		mnFile.add(mntmExit);
		
		lblSex = new JLabel("Sex:");
		lblSex.setBounds(17, 372, 61, 16);
		contentPane.add(lblSex);
		
		JComboBox sexComboBox = new JComboBox();
		sexComboBox.setModel(new DefaultComboBoxModel(new String[] {"Choose Sex", "Male", "Female", "Other"}));
		sexComboBox.setBounds(55, 367, 200, 27);
		contentPane.add(sexComboBox);
		
		lblPleaseSpecifySex = new JLabel("Please Specify:");
		lblPleaseSpecifySex.setFont(new Font("Lucida Grande", Font.ITALIC, 10));
		lblPleaseSpecifySex.setBounds(264, 373, 101, 16);
		contentPane.add(lblPleaseSpecifySex);
		lblPleaseSpecifySex.setVisible(false);
		
		lblAge = new JLabel("Age:");
		lblAge.setBounds(17, 437, 34, 16);
		contentPane.add(lblAge);
		
		ageTF = new JTextField();
		ageTF.setBounds(55, 432, 41, 26);
		contentPane.add(ageTF);
		ageTF.setColumns(10);
		
		pleaseSpecifySexTF = new JTextField();
		pleaseSpecifySexTF.setBounds(352, 366, 156, 28);
		contentPane.add(pleaseSpecifySexTF);
		pleaseSpecifySexTF.setColumns(10);
		pleaseSpecifySexTF.setVisible(false);
		
		lblNumberOfKids = new JLabel("Number Of Kids 18 Or Under:");
		lblNumberOfKids.setBounds(17, 466, 178, 16);
		contentPane.add(lblNumberOfKids);
		
		numberOfKidsTF = new JTextField();
		numberOfKidsTF.setBounds(205, 461, 50, 26);
		contentPane.add(numberOfKidsTF);
		numberOfKidsTF.setColumns(10);
		
		JButton btnSubmit = new JButton("Add Attendee");
		btnSubmit.setBounds(741, 488, 117, 29);
		contentPane.add(btnSubmit);
		
		rdbtnAreYouNew = new JRadioButton("This is my first class.");
		firstClassButtonGroup.add(rdbtnAreYouNew);
		rdbtnAreYouNew.setBounds(17, 268, 167, 23);
		contentPane.add(rdbtnAreYouNew);
		
		rdbtnNotFirstClass = new JRadioButton("This is not my first class.");
		firstClassButtonGroup.add(rdbtnNotFirstClass);
		rdbtnNotFirstClass.setBounds(182, 268, 200, 23);
		contentPane.add(rdbtnNotFirstClass);
	
		//Set everything to invisible until an initial radio button is chosen
		fNameTF.setVisible(false);
		lNameTF.setVisible(false);
		zipCodeFTF.setVisible(false);
		pleaseSpecifyRaceTF.setVisible(false);
		ageTF.setVisible(false);
		pleaseSpecifySexTF.setVisible(false);
		numberOfKidsTF.setVisible(false);
		
		lblFirstName.setVisible(false);
		lblLastName.setVisible(false);
		lblSex.setVisible(false);
		lblRace.setVisible(false);
		lblAge.setVisible(false);
		lblNumberOfKids.setVisible(false);
		lblZipcode.setVisible(false);
		
		sexComboBox.setVisible(false);
		raceComboBox.setVisible(false);
		
		//Add text fields and labels into two hash maps, the first one holds all fields, the second one only holds half of them	
		fieldLabelMap.put(fNameTF, lblFirstName);
		fieldLabelMap.put(lNameTF, lblLastName);
		fieldLabelMap.put(sexComboBox, lblSex);
		fieldLabelMap.put(raceComboBox, lblRace);
		fieldLabelMap.put(pleaseSpecifySexTF, lblPleaseSpecifySex);
		fieldLabelMap.put(pleaseSpecifyRaceTF, lblSpecifyOtherRace);
		fieldLabelMap.put(zipCodeFTF, lblZipcode);
		
		fieldLabelMap2.put(fNameTF, lblFirstName);
		fieldLabelMap2.put(lNameTF, lblLastName);
		fieldLabelMap2.put(sexComboBox, lblSex);
		fieldLabelMap2.put(raceComboBox, lblRace);
		fieldLabelMap2.put(pleaseSpecifySexTF, lblPleaseSpecifySex);
		fieldLabelMap2.put(pleaseSpecifyRaceTF, lblSpecifyOtherRace);
		
		JButton btnUpload = new JButton("Upload");
		btnUpload.setBounds(870, 488, 117, 29);
		contentPane.add(btnUpload);

		//If new participant - display all information
		rdbtnAreYouNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnAreYouNew.isSelected()){
					fNameTF.setVisible(true);
					lNameTF.setVisible(true);
					zipCodeFTF.setVisible(true);
					ageTF.setVisible(true);
					numberOfKidsTF.setVisible(true);
					
					lblFirstName.setVisible(true);
					lblLastName.setVisible(true);
					lblSex.setVisible(true);
					lblRace.setVisible(true);
					lblAge.setVisible(true);
					lblNumberOfKids.setVisible(true);
					lblZipcode.setVisible(true);
					
					sexComboBox.setVisible(true);
					raceComboBox.setVisible(true);
					
					//Clear the fields on change and label colors
					clearFields();
					clearColors();
				}
			}
		});
		
		//If not new participant - display lName, fName, date, and classroom information
		rdbtnNotFirstClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNumberOfKids.setVisible(false);
				lblZipcode.setVisible(false);
				
				fNameTF.setVisible(true);
				lNameTF.setVisible(true);
				zipCodeFTF.setVisible(true);
				ageTF.setVisible(true);
				numberOfKidsTF.setVisible(true);
				
				lblFirstName.setVisible(true);
				lblLastName.setVisible(true);
				lblSex.setVisible(true);
				lblRace.setVisible(true);
				lblAge.setVisible(true);
				
				sexComboBox.setVisible(true);
				raceComboBox.setVisible(true);
				
				//Clear the fields on change and label colors
				clearFields();
				clearColors();
			}
		});

		//If "Other" option is chosen from Sex Combobox, display the Other textfield
		sexComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(sexComboBox.getSelectedItem().equals("Other")){
					lblPleaseSpecifySex.setVisible(true);
					pleaseSpecifySexTF.setVisible(true);
				} else {
					lblPleaseSpecifySex.setVisible(false);
					pleaseSpecifySexTF.setVisible(false);
				}
			}				
		});
		
		//If "Other" option is chosen from Race combobox, display the Other textfield
		raceComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(raceComboBox.getSelectedItem().equals("Other")){
					lblSpecifyOtherRace.setVisible(true);
					pleaseSpecifyRaceTF.setVisible(true);
				} else {
					lblSpecifyOtherRace.setVisible(false);
					pleaseSpecifyRaceTF.setVisible(false);
				}
			}
		});
		
		//Add newly created strings/direct input as a new row in JTable
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!rdbtnAreYouNew.isSelected() && !rdbtnNotFirstClass.isSelected()){
					return;
				}
				clearColors();
				if(!validateData()){
					return;
				}
				String yesOrNo;
				String sex;
				String classroom;
				String race;
				String fName;
				String lName;
				
				//Auto-capitalization of first and last name
				fName = fNameTF.getText().substring(0, 1).toUpperCase() + fNameTF.getText().substring(1, fNameTF.getText().length());
				lName = lNameTF.getText().substring(0, 1).toUpperCase() + lNameTF.getText().substring(1, lNameTF.getText().length());

				if(sexComboBox.getSelectedItem().equals("Other")){
					sex = pleaseSpecifySexTF.getText();
				} else {
					sex = sexComboBox.getSelectedItem().toString();
				}
				
				if(raceComboBox.getSelectedItem().equals("Other")){
					race = pleaseSpecifyRaceTF.getText();
				} else {
					race = raceComboBox.getSelectedItem().toString();
				}
				
				if(rdbtnAreYouNew.isSelected()){
					yesOrNo = "Yes";
				}else{
					yesOrNo = "No";
				}
				
				String day = getDayString(datePicker);
				
				//TODO: Fix the row that you are adding based on parameters given to the class
				if(rdbtnAreYouNew.isSelected()){
					defaultModel.addRow(new Object[] {
							fName,
							lName,
							datePicker.getModel().getValue().toString(),
							topic,
							day,
							startTime,
							location,
							language,
							sex,
							race,
							ageTF.getText(),
							numberOfKidsTF.getText(),
							zipCodeFTF.getText(),
							yesOrNo
					});
				} else {
					defaultModel.addRow(new Object[] {
							fName,
							lName,
							datePicker.getModel().getValue().toString(),
							topic,
							day,
							startTime,
							location,
							language,
							sex,
							race,
							ageTF.getText(),
							null,
							null,
							"No"
					});
				}			
				//Clear all textFields after submit for the next participant
				clearFields();
			}
		});
		
		//Make the JTable editable if in focus
		scrollPane.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				for(int x = 0; x < outputTable.getRowCount() - 1; x++) {
					int z = outputTable.getColumnCount();
					outputTable.isCellEditable(x, z);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				outputTable.setEnabled(false);
			}
		});
	}
	
	//Validate the data fields throughout the application
	//If anything isn't correct, highlight things in red and return false boolean
	public boolean validateData(){
		boolean returnValue = true;
		//Validation is different if the "Are you new" radio button is selected
		if(rdbtnAreYouNew.isSelected()){
			for(Object key : fieldLabelMap.keySet()){
				JLabel label = fieldLabelMap.get(key);
				
				//Checks all text fields to see if they meet specific criteria
				if(key.getClass().equals(JTextField.class)){
					JTextField textField = (JTextField) key;
					if(textField.isVisible()){
						if(textField.getText().isEmpty()){
							label.setForeground(Color.RED);
							returnValue = false;
						} else {
							if(!validateStringField(textField)){
								returnValue = false;
								label.setForeground(Color.RED);
							}
						}
					}
				}
				
				//Checks all combo box's to see if they meet specific criteria
				if(key.getClass().equals(JComboBox.class)){
					JComboBox comboBox = (JComboBox) key;
					if(comboBox.isVisible()){
						String defaultStr = comboBox.getItemAt(0).toString();
						if(comboBox.getSelectedItem().toString().equals(defaultStr)){
							label.setForeground(Color.RED);
							returnValue = false;
						}
					}
				}
			}
			
			//Add additional specific formatted text fields here			
			if(zipCodeFTF.getText().isEmpty()){
				lblZipcode.setForeground(Color.RED);
				returnValue = false;
			}
			
			if(ageTF.getText().isEmpty() || !validateIntegerField(ageTF)){
				lblAge.setForeground(Color.RED);
				returnValue = false;
			}
			
			if(numberOfKidsTF.getText().isEmpty() || !validateIntegerField(numberOfKidsTF)){
				lblNumberOfKids.setForeground(Color.RED);
				returnValue = false;
			}
		} else {
			for(Object key : fieldLabelMap2.keySet()){
				JLabel label = fieldLabelMap2.get(key);
				//Checks all text fields to see if they meet specific criteria
				if(key.getClass().equals(JTextField.class)){
					JTextField textField = (JTextField) key;
					if(textField.isVisible()){
						if(textField.getText().isEmpty()){
							label.setForeground(Color.RED);
							returnValue = false;
						} else {
							if(!validateStringField(textField)){
								returnValue = false;
								label.setForeground(Color.RED);
							}
						}
					}
				}				
				//Checks all combo box's to see if they meet specific criteria
				if(key.getClass().equals(JComboBox.class)){
					JComboBox comboBox = (JComboBox) key;
					if(comboBox.isVisible()){
						String defaultStr = comboBox.getItemAt(0).toString();
						if(comboBox.getSelectedItem().toString().equals(defaultStr)){
							label.setForeground(Color.RED);
							returnValue = false;
						}
					}
				}
			}
			if(ageTF.getText().isEmpty() || !validateIntegerField(ageTF)){
				lblAge.setForeground(Color.RED);
				returnValue = false;
			}
		}
			
		return returnValue;
	}
	
	//Checks if field has any integers
	public boolean validateStringField(JTextField field){
		String fieldString = field.getText();
		for(int i = 0; i < fieldString.length(); i++){
			if(Character.isDigit(fieldString.charAt(i))){
				return false;
			}
		}
		return true;
	}
	
	//Checks if field has any strings
	public boolean validateIntegerField(JTextField field){
		String fieldString = field.getText();
		for(int i = 0; i < fieldString.length(); i++){
			if(!Character.isDigit(fieldString.charAt(i))){
				return false;
			}
		}
		return true;
	}
	
	//Clears all the colors of the labels
	public void clearColors(){
		for(Object key : fieldLabelMap.keySet()){
			JLabel label = fieldLabelMap.get(key);
			label.setForeground(Color.BLACK);
		}
		//These two are not in the hash map
		lblAge.setForeground(Color.BLACK);
		lblNumberOfKids.setForeground(Color.BLACK);
	}
	
	//Clears all fields in the form
	public void clearFields(){
		//Clear all textFields after submit for the next participant
		for(Object key : fieldLabelMap.keySet()){			
			//Checks all text fields to see if they meet specific criteria
			if(key.getClass().equals(JTextField.class)){
				JTextField textField = (JTextField) key;
				textField.setText("");
			}
			
			//Checks all combo box's to see if they meet specific criteria
			if(key.getClass().equals(JComboBox.class)){
				JComboBox comboBox = (JComboBox) key;
				comboBox.setSelectedIndex(0);
			}
		}
		zipCodeFTF.setText("");
		ageTF.setText("");
		numberOfKidsTF.setText("");
	}
	
	//Get day from datePicker
	public String getDayString(JDatePickerImpl datePicker){
		int day = datePicker.getModel().getDay();
		int month = datePicker.getModel().getMonth();
		int year = datePicker.getModel().getYear();
		GregorianCalendar c = new GregorianCalendar(year, month, day);
		String[] weekdays = new DateFormatSymbols().getWeekdays(); // Get day names
		String weekday = weekdays[c.get(c.DAY_OF_WEEK)];
		return weekday;
	}
}
