package javaApplication;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

public class Upload_Frame extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	private JTable outputTable;
	private DefaultTableModel defaultModel;
	private MyTableModel model = new MyTableModel();
    
    //Variables to hold changed values
    public String curriculumChange = "";
    public JDatePickerImpl dateChange = null;
    public String timeChange = "";
    public String topicChange = "";
    public String languageChange = "";
    public String locationChange = "";
    public boolean popUpOpen = false;
    
	/**
	 * Create the frame.
	 */
	public Upload_Frame(String filePath) {
		frame = this;
		setResizable(false);
     	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1161, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCpcaAttendance = new JLabel("PEP Attendance Sheet");
		lblCpcaAttendance.setBounds(405, 23, 330, 38);
		lblCpcaAttendance.setFont(new Font("Cambria", Font.PLAIN, 30));
		contentPane.add(lblCpcaAttendance);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1173, 22);
		contentPane.add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		        //Curriculum
		        Cell curriculumCell = headerRow.createCell(3);
		        curriculumCell.setCellValue("Curriculum");
		        //Topic
		        Cell topicCell = headerRow.createCell(4);
		        topicCell.setCellValue("Topic");
		        //Day
		        Cell dayCell = headerRow.createCell(5);
		        dayCell.setCellValue("Day");
		        //Time
		        Cell timeCell = headerRow.createCell(6);
		        timeCell.setCellValue("Time");
		        //Location
		        Cell locationCell = headerRow.createCell(7);
		        locationCell.setCellValue("Location");
		        //Language
		        Cell languageCell = headerRow.createCell(8);
		        languageCell.setCellValue("Language");
		        //Sex
		        Cell sexCell = headerRow.createCell(9);
		        sexCell.setCellValue("Sex");
		        //Race
		        Cell raceCell = headerRow.createCell(10);
		        raceCell.setCellValue("Race");
		        //Age
		        Cell ageCell = headerRow.createCell(11);
		        ageCell.setCellValue("Age");
		        //New
		        Cell newCell = headerRow.createCell(12);
		        newCell.setCellValue("New");
		        //18&Under
		        Cell ageUnderCell = headerRow.createCell(13);
		        ageUnderCell.setCellValue("18 & Under");
		        //Zip
		        Cell zipCodeCell = headerRow.createCell(14);
		        zipCodeCell.setCellValue("Zipcode");
		        
		        
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
		        			//Check box for yes or no
		        			if(tableColumnCount == 12){
		        				if(outputTable.getValueAt(tableRowCount, tableColumnCount).equals(true)){
		        					tempCell.setCellValue("Yes");
		        				}else{
		        					tempCell.setCellValue("No");
		        				}
		        			} else {
		        				tempCell.setCellValue(outputTable.getValueAt(tableRowCount, tableColumnCount).toString());
		        			}
		        		}
		        		tableColumnCount++;
		        		excelColumnCount++;
		        	}
		        	tableRowCount++;
		        	excelRowCount++;
		        }
		        
		        JFileChooser fileChooser = new JFileChooser();
		        FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "Excel Files (.xlsx)", "xlsx");
				fileChooser.setFileFilter(filter);
				int returnVal = fileChooser.showSaveDialog(getParent());
		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		        	File file = fileChooser.getSelectedFile();
			        try (FileOutputStream outputStream = new FileOutputStream(file.getAbsolutePath()+".xlsx")) {
			            workbook.write(outputStream);
			        } catch (IOException e1){
			        	System.out.println("IOException: " + e1.getMessage());
			        }
		        }
			}
		});
		
		mnFile.add(mntmSave);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: Ask if they want to save
				frame.dispose();
			}
		});
		
		//TODO: Not implemented yet, going to ask algozzine what I should do
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnFile.add(mntmOpen);
		mnFile.add(mntmExit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 74, 1155, 182);
		contentPane.add(scrollPane);
		
		outputTable = new JTable();
		scrollPane.setViewportView(outputTable);
		outputTable.setModel(model);
		
		//TODO: Database stuff here
		JButton btnUpload = new JButton("Upload");
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUpload.setBounds(1008, 269, 135, 63);
		contentPane.add(btnUpload);
		
		JButton btnChangeDate = new JButton("Change Date");
		btnChangeDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!popUpOpen){
					Change_Date changeDate = new Change_Date((Upload_Frame) frame);
					changeDate.setVisible(true);
					changeDate.setAlwaysOnTop(true);
					popUpOpen = true;
				}
			}
		});
		btnChangeDate.setBounds(12, 301, 141, 25);
		contentPane.add(btnChangeDate);
		
		JButton btnChangeCurriculum = new JButton("Change Curriculum");
		btnChangeCurriculum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!popUpOpen){
					Change_Curriculum changeCurriculum = new Change_Curriculum((Upload_Frame) frame);
					changeCurriculum.setVisible(true);
					changeCurriculum.setAlwaysOnTop(true);
					popUpOpen = true;
				}
			}
		});
		
		btnChangeCurriculum.setBounds(12, 263, 141, 25);
		contentPane.add(btnChangeCurriculum);
		
		JButton btnChangeTopic = new JButton("Change Topic");
		btnChangeTopic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!popUpOpen){
					Change_Topic changeTopic = new Change_Topic((Upload_Frame) frame);
					changeTopic.setVisible(true);
					changeTopic.setAlwaysOnTop(true);
					popUpOpen = true;
				}
			}
		});
		btnChangeTopic.setBounds(165, 301, 141, 25);
		contentPane.add(btnChangeTopic);
		
		JButton btnChangeTime = new JButton("Change Time");
		btnChangeTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!popUpOpen){
					Change_Time changeTime = new Change_Time((Upload_Frame) frame);
					changeTime.setVisible(true);
					changeTime.setAlwaysOnTop(true);
					popUpOpen = true;
				}
			}
		});
		btnChangeTime.setBounds(165, 263, 141, 25);
		contentPane.add(btnChangeTime);
		
		JButton btnChangeLanguage = new JButton("Change Language");
		btnChangeLanguage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!popUpOpen){
					Change_Language changeLanguage = new Change_Language((Upload_Frame) frame);
					changeLanguage.setVisible(true);
					changeLanguage.setAlwaysOnTop(true);
					popUpOpen = true;
				}
			}
		});
		btnChangeLanguage.setBounds(318, 263, 141, 25);
		contentPane.add(btnChangeLanguage);
		
		JButton btnChangeLocation = new JButton("Change Location");
		btnChangeLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!popUpOpen){
					Change_Location changeLocation = new Change_Location((Upload_Frame) frame);
					changeLocation.setVisible(true);
					changeLocation.setAlwaysOnTop(true);
					popUpOpen = true;
				}
			}
		});
		btnChangeLocation.setBounds(318, 301, 141, 25);
		contentPane.add(btnChangeLocation);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!popUpOpen){
					model.setRowCount(0);
					clearStore();
					initialOpen(filePath);
				}
			}
		});
		btnReset.setBounds(861, 269, 135, 63);
		contentPane.add(btnReset);
		
		//Populate screen with initial open of excel file
		initialOpen(filePath);
	}
	
	public void initialOpen(String filePath){
    	//Opening file and populating to JTable
    	try {
    		//Get Table ready
    		setupSexColumn(outputTable, outputTable.getColumnModel().getColumn(9));
    		setupRaceColumn(outputTable, outputTable.getColumnModel().getColumn(10));
    		
    		//Open excel file and workbook
	    	FileInputStream excelFile = new FileInputStream(new File(filePath));
	    	Workbook workbook = new XSSFWorkbook(excelFile);
	    	Sheet datatypeSheet = workbook.getSheetAt(0);
	    	Iterator<Row> iterator = datatypeSheet.iterator();
	    	
	    	//If this is false, you skip over the header row of the excel sheet
	    	boolean headerRow = false;
	    	while(iterator.hasNext()){
	    		//Array and count to hold string values in each cell
	    		String[] row = new String[15];
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
	    		
	    		boolean yesOrNo;
	    		if(row[12].equals("Yes")){
	    			yesOrNo = true;
	    		}else{
	    			yesOrNo = false;
	    		}
	    		
	    		//Add row to the table(Check what type of row you are adding to table by checking if last value is empty)
    			model.addRow(new Object[] {
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
						yesOrNo,
						row[13],
						row[14]
				});
	    	}
	    	workbook.close();
	    } catch (FileNotFoundException e) {
	    	//TODO: If file was not found, do a pop up
	    	System.out.println("File not found");
	    } catch (IOException e) {
	    	//TODO: If something goes wrong with IO, do a pop up
	    	System.out.println("IOException");
	    }
	}
	
	public void redoFields(){
		//TODO: Iterate through each row and column and check if there are anything in the changed variables, if so change column
		int row = 0;
		while(row < outputTable.getRowCount()){
			int column = 0;
			while(column < outputTable.getColumnCount()){
				//Change values in row if something is in the store
				if(column == 2 && dateChange != null){
					String day = getDayString(dateChange);
					outputTable.setValueAt(dateChange.getModel().getValue().toString(), row, column);
					outputTable.setValueAt(day, row, 5);
				}else if(column == 3 && curriculumChange != ""){
					outputTable.setValueAt(curriculumChange, row, column);
				}else if(column == 4 && topicChange != ""){
					outputTable.setValueAt(topicChange, row, column);
				}else if(column == 6 && timeChange != ""){
					outputTable.setValueAt(timeChange, row, column);
				}else if(column == 7 && locationChange != ""){
					outputTable.setValueAt(locationChange, row, column);
				}else if(column == 8 && languageChange != ""){
					outputTable.setValueAt(languageChange, row, column);
				}
				column++;
			}
			row++;
		}
	}
	
	public void setupSexColumn(JTable table, TableColumn sexColumn){
		//Set up the editor for the sport cells.
	    JComboBox comboBox = new JComboBox();
	    DefaultComboBoxModel model = new DefaultComboBoxModel();
	    model.addElement("Male");
	    model.addElement("Female");
	    comboBox.setModel(model);
	    sexColumn.setCellEditor(new DefaultCellEditor(comboBox));
	    
	    model = new DefaultComboBoxModel();
	    model.addElement("Male");
	    model.addElement("Female");
	    //Set up tool tips for the sport cells.
	    ComboBoxTableCellRenderer renderer = new ComboBoxTableCellRenderer();
	    renderer.setModel(model);
	    sexColumn.setCellRenderer(renderer);
	}
	
	public void setupRaceColumn(JTable table, TableColumn raceColumn){
		//Set up the editor for the sport cells.
	    JComboBox comboBox = new JComboBox();
	    DefaultComboBoxModel model = new DefaultComboBoxModel();
	    model.addElement("White");
	    model.addElement("African-American");
	    model.addElement("Hispanic");
	    model.addElement("Asian");
	    model.addElement("Other");
	    comboBox.setModel(model);
	    raceColumn.setCellEditor(new DefaultCellEditor(comboBox));
	    
	    model = new DefaultComboBoxModel();
	    model.addElement("White");
	    model.addElement("African-American");
	    model.addElement("Hispanic");
	    model.addElement("Asian");
	    model.addElement("Other");
	    //Set up tool tips for the sport cells.
	    ComboBoxTableCellRenderer renderer = new ComboBoxTableCellRenderer();
	    renderer.setModel(model);
	    raceColumn.setCellRenderer(renderer);
	    raceColumn.setMinWidth(110);
	}
	
	public class ComboBoxTableCellRenderer extends JComboBox implements TableCellRenderer {
	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	        setSelectedItem(value);
	        return this;
	    }
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
	
	public void clearStore(){
		curriculumChange = "";
	    dateChange = null;
	    timeChange = "";
	    topicChange = "";
	    languageChange = "";
	    locationChange = "";
	}
}
