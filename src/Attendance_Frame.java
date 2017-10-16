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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Attendance_Frame extends JFrame {
	private JPanel contentPane;
	private JTextField fNameTF;
	private JTextField lNameTF;
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
	public Attendance_Frame() {
     	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1033, 651);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		zipCodeFTF.setBounds(77, 544, 178, 22);
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
		lblZipcode.setBounds(17, 544, 61, 22);
		contentPane.add(lblZipcode);
		
		JLabel lblRace = new JLabel("Race:");
		lblRace.setBounds(17, 458, 61, 22);
		contentPane.add(lblRace);
		
		fNameTF = new JTextField();
		fNameTF.setBounds(91, 303, 164, 30);
		contentPane.add(fNameTF);
		fNameTF.setColumns(10);
		
		lNameTF = new JTextField();
		lNameTF.setBounds(91, 333, 164, 30);
		contentPane.add(lNameTF);
		lNameTF.setColumns(10);
		
		JLabel lblClass = new JLabel("Class:");
		lblClass.setBounds(17, 402, 61, 16);
		contentPane.add(lblClass);
		
		JComboBox classDayComboBox = new JComboBox();
		classDayComboBox.setModel(new DefaultComboBoxModel(new String[] {"Day", "Monday", "Tuesday", "Wednesday", "Thursday"}));
		classDayComboBox.setBounds(54, 398, 117, 27);
		contentPane.add(classDayComboBox);
		
		JComboBox classTimeComboBox = new JComboBox();
		classTimeComboBox.setModel(new DefaultComboBoxModel(new String[] {"Start Time", "10:00am", "11:00am", "11:30am", "12:30am", "1:00pm", "1:30pm", "2:00pm", "4:30pm", "6:00pm"}));
		classTimeComboBox.setBounds(176, 398, 117, 27);
		contentPane.add(classTimeComboBox);
		
		JComboBox classLocationComboBox = new JComboBox();
		classLocationComboBox.setModel(new DefaultComboBoxModel(new String[] {"Location", "Poughkeepsie", "Florence Manor", "Fishkill", "ITAP", "Cornerstone", "Meadow Run", "Fox Run"}));
		classLocationComboBox.setBounds(305, 398, 203, 27);
		contentPane.add(classLocationComboBox);
		
		JComboBox classLanguageComboBox = new JComboBox();
		classLanguageComboBox.setModel(new DefaultComboBoxModel(new String[] {"Language", "English", "Spanish"}));
		classLanguageComboBox.setBounds(520, 398, 129, 27);
		contentPane.add(classLanguageComboBox);
		
		JComboBox raceComboBox = new JComboBox();
		raceComboBox.setBounds(54, 453, 200, 27);
		raceComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "White", "African-American", "Hispanic", "Asian", "Other"}));
		contentPane.add(raceComboBox);
		
		pleaseSpecifyRaceTF = new JTextField();
		pleaseSpecifyRaceTF.setBounds(352, 454, 156, 22);
		contentPane.add(pleaseSpecifyRaceTF);
		pleaseSpecifyRaceTF.setColumns(10);
		pleaseSpecifyRaceTF.setVisible(false);
		
		JLabel lblSpecifyOtherRace = new JLabel("Please Specify:");
		lblSpecifyOtherRace.setBounds(264, 457, 79, 18);
		lblSpecifyOtherRace.setFont(new Font("Lucida Grande", Font.ITALIC, 10));
		contentPane.add(lblSpecifyOtherRace);
		lblSpecifyOtherRace.setVisible(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 74, 1006, 182);
		contentPane.add(scrollPane);
		
		outputTable = new JTable();
		scrollPane.setViewportView(outputTable);
		outputTable.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null},
				},
				new String[] {
					"First", "Last", "Date", "Day", "Time", 
					"Location", "Language","Sex", "Race", "Age","18&Under", "Zipcode","New"
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
		lblSex.setBounds(17, 430, 61, 16);
		contentPane.add(lblSex);
		
		JComboBox sexComboBox = new JComboBox();
		sexComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Male", "Female", "Other"}));
		sexComboBox.setBounds(54, 426, 200, 27);
		contentPane.add(sexComboBox);
		
		JLabel lblPleaseSpecifySex = new JLabel("Please Specify:");
		lblPleaseSpecifySex.setFont(new Font("Lucida Grande", Font.ITALIC, 10));
		lblPleaseSpecifySex.setBounds(264, 431, 101, 16);
		contentPane.add(lblPleaseSpecifySex);
		lblPleaseSpecifySex.setVisible(false);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setBounds(17, 488, 34, 16);
		contentPane.add(lblAge);
		
		ageTF = new JTextField();
		ageTF.setBounds(55, 483, 41, 26);
		contentPane.add(ageTF);
		ageTF.setColumns(10);
		
		pleaseSpecifySexTF = new JTextField();
		pleaseSpecifySexTF.setBounds(352, 424, 156, 28);
		contentPane.add(pleaseSpecifySexTF);
		pleaseSpecifySexTF.setColumns(10);
		pleaseSpecifySexTF.setVisible(false);
		
		JLabel lblNumberOfKids = new JLabel("Number Of Kids 18 Or Under:");
		lblNumberOfKids.setBounds(17, 516, 178, 16);
		contentPane.add(lblNumberOfKids);
		
		numberOfKidsTF = new JTextField();
		numberOfKidsTF.setBounds(204, 511, 50, 26);
		contentPane.add(numberOfKidsTF);
		numberOfKidsTF.setColumns(10);
		
		JLabel lblDate = new JLabel("Today's Date:");
		lblDate.setBounds(17, 371, 101, 16);
		contentPane.add(lblDate);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(893, 542, 117, 29);
		contentPane.add(btnSubmit);
		
		JRadioButton rdbtnAreYouNew = new JRadioButton("This is my first class.");
		firstClassButtonGroup.add(rdbtnAreYouNew);
		rdbtnAreYouNew.setBounds(17, 268, 167, 23);
		contentPane.add(rdbtnAreYouNew);
		
		JRadioButton rdbtnNotFirstClass = new JRadioButton("This is not my first class.");
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
		dateFTF.setVisible(false);
		
		lblFirstName.setVisible(false);
		lblLastName.setVisible(false);
		lblSex.setVisible(false);
		lblRace.setVisible(false);
		lblAge.setVisible(false);
		lblNumberOfKids.setVisible(false);
		lblZipcode.setVisible(false);
		lblDate.setVisible(false);
		lblClass.setVisible(false);
		
		sexComboBox.setVisible(false);
		raceComboBox.setVisible(false);
		classDayComboBox.setVisible(false);
		classTimeComboBox.setVisible(false);
		classLocationComboBox.setVisible(false);
		classLanguageComboBox.setVisible(false);

		//If new participant - display all information
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
					lblClass.setVisible(true);
					
					sexComboBox.setVisible(true);
					raceComboBox.setVisible(true);
					classDayComboBox.setVisible(true);
					classTimeComboBox.setVisible(true);
					classLocationComboBox.setVisible(true);
					classLanguageComboBox.setVisible(true);
				}
			}
		});
		
		//If not new participant - display lName, fName, date, and classroom information
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
				lblClass.setVisible(true);
				
				sexComboBox.setVisible(false);
				raceComboBox.setVisible(false);
				classDayComboBox.setVisible(true);
				classTimeComboBox.setVisible(true);
				classLocationComboBox.setVisible(true);
				classLanguageComboBox.setVisible(true);				
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
				DefaultTableModel table = (DefaultTableModel)outputTable.getModel();
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
				
				table.addRow(new Object[] {
						fName,
						lName,
						dateFTF.getText(),
						classDayComboBox.getSelectedItem(),
						classTimeComboBox.getSelectedItem(),
						classLocationComboBox.getSelectedItem(),
						classLanguageComboBox.getSelectedItem(),
						sex,
						race,
						ageTF.getText(),
						numberOfKidsTF.getText(),
						zipCodeFTF.getText(),
						yesOrNo
				});
			
				//Clear all textFields after submit for the next participant
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
				classDayComboBox.setSelectedIndex(0);
				classTimeComboBox.setSelectedIndex(0);
				classLocationComboBox.setSelectedIndex(0);
				classLanguageComboBox.setSelectedIndex(0);
				
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
		if(fNameTF.getText().isEmpty()){
			returnValue = false;
			
		} else {
			if(!validateStringField(fNameTF)){
				returnValue = false;
			}
		}
		return true;
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
}
