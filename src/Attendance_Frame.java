package javaApplication;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.ButtonGroup;
import javax.swing.text.MaskFormatter;
import javax.swing.JFormattedTextField;

public class Attendance_Frame extends JFrame {

	private JPanel contentPane;
	private JTextField fNameTF;
	private JTextField lNameTF;
	
//	MaskFormatter zipcode = createFormatter("#####");
	JFormattedTextField zipCodeFTF = new JFormattedTextField();

	
	private JTextField pleaseSpecifyRaceTF;
	private JTable outputTable;
	private JTextField ageTF;
	private JTextField pleaseSpecifySexTF;
	private JTextField numberOfKidsTF;
	
	
	MaskFormatter date = createFormatter("##/##/####");
	JFormattedTextField dateFTF = new JFormattedTextField();
	
	private final ButtonGroup newProgramButtonGroup = new ButtonGroup();
	private final ButtonGroup firstClassButtonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Attendance_Frame frame = new Attendance_Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
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
	}//createFormatter
	
	/**
	 * Create the frame.
	 */
	public Attendance_Frame() {
	
     	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1018, 651);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
//		zipcode.setPlaceholderCharacter('#');
//		zipcode.setValidCharacters("0123456789");
		zipCodeFTF.setBounds(78, 523, 177, 22);
//		zipcode.install(zipCodeFTF);
		contentPane.add(zipCodeFTF);
		
		date.setPlaceholderCharacter('D');
	    date.setValidCharacters("0123456789");
		dateFTF.setBounds(111, 364, 144, 30);
		date.install(dateFTF);
		contentPane.add(dateFTF);
		
		JLabel lblCpcaAttendance = new JLabel("PEP Attendance Sheet");
		lblCpcaAttendance.setBounds(343, 25, 330, 38);
		lblCpcaAttendance.setFont(new Font("Cambria", Font.PLAIN, 30));
		contentPane.add(lblCpcaAttendance);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(17, 303, 79, 30);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(17, 337, 79, 22);
		contentPane.add(lblLastName);
		
		JLabel lblZipcode = new JLabel("Zipcode:");
		lblZipcode.setBounds(17, 523, 61, 22);
		contentPane.add(lblZipcode);
		
		JLabel lblRace = new JLabel("Race:");
		lblRace.setBounds(17, 433, 61, 22);
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

		raceComboBox.setBounds(55, 432, 200, 27);
		raceComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "White", "African-American", "Hispanic", "Asian", "Other"}));
		contentPane.add(raceComboBox);
		
		pleaseSpecifyRaceTF = new JTextField();
		pleaseSpecifyRaceTF.setBounds(352, 433, 156, 22);
		contentPane.add(pleaseSpecifyRaceTF);
		pleaseSpecifyRaceTF.setColumns(10);
		
		pleaseSpecifyRaceTF.setVisible(false);
		
		JLabel lblSpecifyOtherRace = new JLabel("Please Specify:");
		lblSpecifyOtherRace.setBounds(267, 436, 79, 18);
		lblSpecifyOtherRace.setFont(new Font("Lucida Grande", Font.ITALIC, 10));
		contentPane.add(lblSpecifyOtherRace);
		
		lblSpecifyOtherRace.setVisible(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(17, 74, 885, 182);
		contentPane.add(scrollPane);
		
		outputTable = new JTable();
		scrollPane.setViewportView(outputTable);
		
		outputTable.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null},
				},
				new String[] {
					"FirstName", "LastName", "Sex", "Race", "Age","#18AndUnder", "Zipcode","New?", "Date"
				}
			));
	
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1010, 22);
		contentPane.add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		JLabel lblSex = new JLabel("Sex:");
		lblSex.setBounds(17, 405, 61, 16);
		contentPane.add(lblSex);
		
		JComboBox sexComboBox = new JComboBox();

		sexComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Male", "Female", "Other"}));
		sexComboBox.setBounds(55, 401, 200, 27);
		contentPane.add(sexComboBox);
		
		JLabel lblPleaseSpecifySex = new JLabel("Please Specify:");
		lblPleaseSpecifySex.setFont(new Font("Lucida Grande", Font.ITALIC, 10));
		lblPleaseSpecifySex.setBounds(267, 408, 101, 16);
		contentPane.add(lblPleaseSpecifySex);
		
		lblPleaseSpecifySex.setVisible(false);

		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setBounds(17, 467, 61, 16);
		contentPane.add(lblAge);
		
		ageTF = new JTextField();
		ageTF.setBounds(55, 462, 41, 26);
		contentPane.add(ageTF);
		ageTF.setColumns(10);
		
		pleaseSpecifySexTF = new JTextField();
		pleaseSpecifySexTF.setBounds(352, 399, 156, 28);
		contentPane.add(pleaseSpecifySexTF);
		pleaseSpecifySexTF.setColumns(10);
		
		pleaseSpecifySexTF.setVisible(false);
		
		JLabel lblNumberOfKids = new JLabel("Number Of Kids 18 Or Under:");
		lblNumberOfKids.setBounds(17, 495, 200, 16);
		contentPane.add(lblNumberOfKids);
		
		numberOfKidsTF = new JTextField();
		numberOfKidsTF.setBounds(205, 490, 50, 26);
		contentPane.add(numberOfKidsTF);
		numberOfKidsTF.setColumns(10);
		
		JLabel lblDate = new JLabel("Today's Date:");
		lblDate.setBounds(17, 371, 101, 16);
		contentPane.add(lblDate);
		
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(893, 521, 117, 29);
		
		JRadioButton rdbtnAreYouNew = new JRadioButton("This is my first class.");

		firstClassButtonGroup.add(rdbtnAreYouNew);
		rdbtnAreYouNew.setBounds(17, 268, 167, 23);
		contentPane.add(rdbtnAreYouNew);
		
		JRadioButton rdbtnNotFirstClass = new JRadioButton("This is not my first class.");
	
		firstClassButtonGroup.add(rdbtnNotFirstClass);
		rdbtnNotFirstClass.setBounds(182, 268, 200, 23);
		contentPane.add(rdbtnNotFirstClass);

		JButton btnEditTable = new JButton("Edit Table");
		JButton btnDoneEditing = new JButton("Done Editing");
	
		
		//set everything to false until a button is chosen
		fNameTF.setVisible(false);
		lNameTF.setVisible(false);
		zipCodeFTF.setVisible(false);
		pleaseSpecifyRaceTF.setVisible(false);
		ageTF.setVisible(false);
		pleaseSpecifySexTF.setVisible(false);
		numberOfKidsTF.setVisible(false);
		dateFTF.setVisible(false);
		
		lblFirstName.setVisible(false);
		lblLastName.setVisible(false);
		lblSex.setVisible(false);
		lblRace.setVisible(false);
		lblAge.setVisible(false);
		lblNumberOfKids.setVisible(false);
		lblZipcode.setVisible(false);
		lblDate.setVisible(false);
		
		sexComboBox.setVisible(false);
		raceComboBox.setVisible(false);
		
		btnDoneEditing.setVisible(false);

		btnEditTable.setBounds(914, 70, 91, 29);
		contentPane.add(btnEditTable);
		
		btnDoneEditing.setBounds(901, 98, 117, 29);
		contentPane.add(btnDoneEditing);
		
		rdbtnAreYouNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnAreYouNew.isSelected()){
					fNameTF.setVisible(true);
					lNameTF.setVisible(true);
					zipCodeFTF.setVisible(true);
					ageTF.setVisible(true);
					numberOfKidsTF.setVisible(true);
					dateFTF.setVisible(true);
					
					lblFirstName.setVisible(true);
					lblLastName.setVisible(true);
					lblSex.setVisible(true);
					lblRace.setVisible(true);
					lblAge.setVisible(true);
					lblNumberOfKids.setVisible(true);
					lblZipcode.setVisible(true);
					lblDate.setVisible(true);
					
					sexComboBox.setVisible(true);
					raceComboBox.setVisible(true);
					
				}
			}
		});
		
		rdbtnNotFirstClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fNameTF.setVisible(true);
				lNameTF.setVisible(true);
				zipCodeFTF.setVisible(false);
				ageTF.setVisible(false);
				numberOfKidsTF.setVisible(false);
				dateFTF.setVisible(true);
				pleaseSpecifySexTF.setVisible(false);
				pleaseSpecifyRaceTF.setVisible(false);
				
				lblSpecifyOtherRace.setVisible(false);
				lblPleaseSpecifySex.setVisible(false);
				lblFirstName.setVisible(true);
				lblLastName.setVisible(true);
				lblSex.setVisible(false);
				lblRace.setVisible(false);
				lblAge.setVisible(false);
				lblNumberOfKids.setVisible(false);
				lblZipcode.setVisible(false);
				lblDate.setVisible(true);
				
				sexComboBox.setVisible(false);
				raceComboBox.setVisible(false);
				
				//need to figure out still 
//				zipcode.uninstall();
			}
		});

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
		
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				DefaultTableModel table = (DefaultTableModel)outputTable.getModel();
				String yesOrNo;
				String sex;
				String race;
				
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
				
				table.addRow(new Object[] {
						fNameTF.getText(),
						lNameTF.getText(),
						sex,
						race,
						ageTF.getText(),
						numberOfKidsTF.getText(),
						zipCodeFTF.getText(),
						yesOrNo,
						dateFTF.getText()	
				});
			
				//clear all textFields after submit
				fNameTF.setText("");
				lNameTF.setText("");
				zipCodeFTF.setText("");
				ageTF.setText("");
				numberOfKidsTF.setText("");
				dateFTF.setText("");
				pleaseSpecifySexTF.setText("");
				pleaseSpecifyRaceTF.setText("");
				sexComboBox.setSelectedIndex(0);
				raceComboBox.setSelectedIndex(0);
			
			}
		});
		

		btnEditTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDoneEditing.setVisible(true);
				for(int x = 0; x < outputTable.getRowCount() - 1; x++) {
					int z = outputTable.getColumnCount();
					outputTable.isCellEditable(x, z);
				}
			}
		});
		
		btnDoneEditing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outputTable.setEnabled(false);
			}
		});
		
		contentPane.add(btnSubmit);
	
	}
}
