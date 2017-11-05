package javaApplication;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

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

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.JButton;
import javax.swing.JFileChooser;

public class Upload_Frame extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	private JTable outputTable;
	private DefaultTableModel defaultModel;
    
    //Variables to hold changed values
    public String curriculumChange = "";

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
		
		mnFile.add(mntmSave);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: Ask if they want to save
				frame.dispose();
			}
		});
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mnFile.add(mntmOpen);
		mnFile.add(mntmExit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 74, 1155, 182);
		contentPane.add(scrollPane);
		
		outputTable = new JTable();
		scrollPane.setViewportView(outputTable);
		defaultModel = new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null},
				},
				new String[] {
					"First", "Last", "Date", "Curriculum", "Topic", "Day", "Time", 
					"Location", "Language","Sex", "Race", "Age","New","18&Under", "Zipcode"
				}
			);
		defaultModel.setRowCount(0);
		outputTable.setModel(defaultModel);
		
		JButton btnUpload = new JButton("Upload");
		btnUpload.setBounds(1008, 269, 135, 63);
		contentPane.add(btnUpload);
		
		JButton btnChangeDate = new JButton("Change Date");
		btnChangeDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(curriculumChange);
			}
		});
		btnChangeDate.setBounds(12, 301, 141, 25);
		contentPane.add(btnChangeDate);
		
		JButton btnChangeCurriculum = new JButton("Change Curriculum");
		btnChangeCurriculum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Change_Curriculum changeCurriculum = new Change_Curriculum((Upload_Frame) frame);
				changeCurriculum.setVisible(true);
				changeCurriculum.setAlwaysOnTop(true);
			}
		});
		
		btnChangeCurriculum.setBounds(12, 263, 141, 25);
		contentPane.add(btnChangeCurriculum);
		
		JButton btnChangeTopic = new JButton("Change Topic");
		btnChangeTopic.setBounds(165, 301, 141, 25);
		contentPane.add(btnChangeTopic);
		
		JButton btnChangeTime = new JButton("Change Time");
		btnChangeTime.setBounds(165, 263, 141, 25);
		contentPane.add(btnChangeTime);
		
		JButton btnChangeLanguage = new JButton("Change Language");
		btnChangeLanguage.setBounds(318, 263, 141, 25);
		contentPane.add(btnChangeLanguage);
		
		JButton btnChangeLocation = new JButton("Change Location");
		btnChangeLocation.setBounds(318, 301, 141, 25);
		contentPane.add(btnChangeLocation);
		
		//TODO: Populate screen with initial open of excel file
		initialOpen(filePath);
	}
	
	public void initialOpen(String filePath){
    	//Opening file and populating to JTable
		//TODO: Logic to have combo boxes and such under the table
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
	    		
	    		//Add row to the table(Check what type of row you are adding to table by checking if last value is empty)
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
						row[12],
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
}
