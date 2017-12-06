/**
 * PEP Capping 2017 Algozzine's Class
 *
 * Allows the participants to enter their information in order to record attendance for a specified class
 *
 * @author Carlie Maxwell and Sami Ellougani
 * @copyright 2017 Marist College
 * @version 0.1.0
 * @since 0.1.0
 */

package pep.attendance.client;

import java.awt.Color;
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
import java.text.DateFormatSymbols;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.ButtonGroup;
import javax.swing.text.MaskFormatter;
import javax.swing.JFormattedTextField;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

//You need to add external Jars to your build path for these excel packages
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//JDatePicker Jar
import org.jdatepicker.impl.JDatePickerImpl;

import com.alee.laf.WebLookAndFeel;


public class AttendanceFrame extends JFrame {

    private JPanel contentPane;
    //This hash map is used later on in data validation
    //The key is the text field, and the value is the label that corresponds to that field
    private HashMap<Object, JLabel> fieldLabelMap = new HashMap<Object, JLabel>();
    private HashMap<Object, JLabel> fieldLabelMap2 = new HashMap<Object, JLabel>();
    private JTextField fNameTF;
    private JTextField lNameTF;
    JFormattedTextField zipCodeFTF = new JFormattedTextField();
    private JTable outputTable;
    private JTextField ageTF;
    private JTextField numberOfKidsTF;

    private JLabel lblFirstName;
    private JLabel lblLastName;
    private JLabel lblZipcode;
    private JLabel lblRace;
    private JLabel lblSex;
    private JLabel lblAge;
    private JLabel lblNumberOfKids;
    private JLabel lblZip;
    private static AttendanceFrame frame;

    private JRadioButton rdbtnAreYouNew;
    private JRadioButton rdbtnNotFirstClass;

    private final ButtonGroup newProgramButtonGroup = new ButtonGroup();
    private final ButtonGroup firstClassButtonGroup = new ButtonGroup();
    private String instructorName;
    private JLabel lblNew;
    
    private Properties preDefinedFields = new Properties();

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
    public AttendanceFrame(String instructor, String topic, JDatePickerImpl datePicker, String startTime, String location, String language, String curriculum) {
        /*
         * Load our predefined fields so that we don't have to query the CPCA network every time
         */
        try {
            this.preDefinedFields.load(new FileInputStream("./resources/sync.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        getDayString(datePicker);
        instructorName = instructor;
        frame = this;
        WebLookAndFeel.install ();
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 1223, 574);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        zipCodeFTF.setBounds(70, 461, 178, 22);
        contentPane.add(zipCodeFTF);


        JLabel lblCpcaAttendance = new JLabel("PEP Attendance Sheet");
        lblCpcaAttendance.setBounds(442, 23, 330, 38);
        lblCpcaAttendance.setFont(new Font("Cambria", Font.PLAIN, 30));
        contentPane.add(lblCpcaAttendance);

        lblFirstName = new JLabel("First Name:");
        lblFirstName.setBounds(10, 269, 79, 30);
        contentPane.add(lblFirstName);

        lblLastName = new JLabel("Last Name:");
        lblLastName.setBounds(10, 303, 79, 22);
        contentPane.add(lblLastName);

        lblZipcode = new JLabel("Zipcode:");
        lblZipcode.setBounds(10, 461, 61, 22);
        contentPane.add(lblZipcode);

        lblRace = new JLabel("Race:");
        lblRace.setBounds(10, 368, 61, 22);
        contentPane.add(lblRace);

        fNameTF = new JTextField();
        fNameTF.setBounds(84, 269, 164, 30);
        contentPane.add(fNameTF);
        fNameTF.setColumns(10);

        lNameTF = new JTextField();
        lNameTF.setBounds(84, 299, 164, 30);
        contentPane.add(lNameTF);
        lNameTF.setColumns(10);

        JComboBox raceComboBox = new JComboBox();
        raceComboBox.setBounds(48, 366, 200, 27);
        raceComboBox.setModel(
                new DefaultComboBoxModel(
                        this.preDefinedFields.getProperty("races").split(",")
                )
        );
        contentPane.add(raceComboBox);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 74, 1217, 182);
        contentPane.add(scrollPane);

        outputTable = new JTable();
        scrollPane.setViewportView(outputTable);
        DefaultTableModel defaultModel = new DefaultTableModel(
                new Object[][] {
                        {null, null, null, null, null, null},
                },
                new String[] {
                        "First", "Last", "Date", "Curriculum", "Topic", "Day", "Time",
                        "Location", "Language","Sex", "Race", "Age","New","18&Under", "Zipcode", "Instructor"
                }
        ){
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            //This causes all cells to be not editable
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };

        defaultModel.setRowCount(0);
        outputTable.setModel(defaultModel);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 1227, 22);
        contentPane.add(menuBar);

        JMenu mnFile = new JMenu("File");
        menuBar.add(mnFile);

        JMenuItem mntmSave = new JMenuItem("Save");

        //Save to excel file
        //Takes the information from the JTable and populates an excel sheet
        //The excel sheet is automatically named with extension â€œDate + startTime + Topic.xlsxâ€�
        mntmSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(outputTable.getRowCount() > 0){
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
                    //Instructor Name
                    Cell instructorCell = headerRow.createCell(15);
                    instructorCell.setCellValue("Instructor");


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

                    String day = getDayString(datePicker);
                    String dayNum = String.valueOf(datePicker.getModel().getDay());
                    String month = getMonth(datePicker);
                    String year = String.valueOf(datePicker.getModel().getYear());
                    String startTimeSubStr;
                    if (startTime.length() == 6){
                        startTimeSubStr = startTime.substring(0, 1) + startTime.substring(2, 6);
                    } else {
                        startTimeSubStr = startTime.substring(0, 2) + startTime.substring(3, 7);
                    }
                    String fileName = "Attendance_" + day + "_" + month + "_" + dayNum + "_" + year + "_" + startTimeSubStr;

                    //TODO: Install
                    try (FileOutputStream outputStream = new FileOutputStream("./" + fileName + ".xlsx")) {
                        workbook.write(outputStream);

                        // we want to tell the user that everything worked out
                        // correctly to improve the UX
                        JOptionPane.showMessageDialog (null,
                                "Successfully saved attendance.");
                    } catch (IOException e){
                        System.out.println("IOException: " + e.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "There are no rows in the table to save!");
                }
            }
        });

        mnFile.add(mntmSave);

        //Closes the AttendanceFrame - The x button is disabled
        JMenuItem mntmExit = new JMenuItem("Exit");
        mntmExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                System.exit(0);
            }
        });
        mnFile.add(mntmExit);

        lblSex = new JLabel("Sex:");
        lblSex.setBounds(10, 338, 61, 16);
        contentPane.add(lblSex);

        JComboBox sexComboBox = new JComboBox();
        sexComboBox.setModel(
                new DefaultComboBoxModel(
                        this.preDefinedFields.getProperty("sexes").split(",")
                )
        );
        sexComboBox.setBounds(48, 333, 200, 27);
        contentPane.add(sexComboBox);

        lblAge = new JLabel("Age:");
        lblAge.setBounds(10, 403, 34, 16);
        contentPane.add(lblAge);

        ageTF = new JTextField();
        ageTF.setBounds(48, 398, 41, 26);
        contentPane.add(ageTF);
        ageTF.setColumns(10);

        lblNumberOfKids = new JLabel("Number Of Kids 18 Or Under:");
        lblNumberOfKids.setBounds(10, 432, 178, 16);
        contentPane.add(lblNumberOfKids);

        numberOfKidsTF = new JTextField();
        numberOfKidsTF.setBounds(198, 427, 50, 26);
        contentPane.add(numberOfKidsTF);
        numberOfKidsTF.setColumns(10);

        JButton btnSubmit = new JButton("Add Attendee");
        btnSubmit.setBounds(1088, 489, 117, 29);
        contentPane.add(btnSubmit);

        rdbtnAreYouNew = new JRadioButton("This is my first class.");
        firstClassButtonGroup.add(rdbtnAreYouNew);
        rdbtnAreYouNew.setBounds(48, 492, 145, 23);
        contentPane.add(rdbtnAreYouNew);

        rdbtnNotFirstClass = new JRadioButton("This is not my first class.");
        firstClassButtonGroup.add(rdbtnNotFirstClass);
        rdbtnNotFirstClass.setBounds(198, 492, 167, 23);
        contentPane.add(rdbtnNotFirstClass);

        //Add text fields and labels into two hash maps, the first one holds all fields, the second one only holds half of them
        fieldLabelMap.put(fNameTF, lblFirstName);
        fieldLabelMap.put(lNameTF, lblLastName);
        fieldLabelMap.put(sexComboBox, lblSex);
        fieldLabelMap.put(raceComboBox, lblRace);
        fieldLabelMap.put(zipCodeFTF, lblZipcode);

        fieldLabelMap2.put(fNameTF, lblFirstName);
        fieldLabelMap2.put(lNameTF, lblLastName);
        fieldLabelMap2.put(sexComboBox, lblSex);
        fieldLabelMap2.put(raceComboBox, lblRace);

        lblNew = new JLabel("New?");
        lblNew.setBounds(10, 496, 56, 16);
        contentPane.add(lblNew);

        //Adds newly created strings/direct input as a new row in the JTable
        //Now add Attendee
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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

                if(rdbtnAreYouNew.isSelected()){
                    yesOrNo = "Yes";
                }else{
                    yesOrNo = "No";
                }

                String day = getDayString(datePicker);
                defaultModel.addRow(new Object[] {
                        fName,
                        lName,
                        datePicker.getModel().getValue().toString(),
                        curriculum,
                        topic,
                        day,
                        startTime,
                        location,
                        language,
                        sexComboBox.getSelectedItem().toString(),
                        raceComboBox.getSelectedItem().toString(),
                        ageTF.getText(),
                        yesOrNo,
                        numberOfKidsTF.getText(),
                        zipCodeFTF.getText(),
                        instructorName
                });

                //Clear all textFields submit for the next participant
                clearFields();
            }
        });
    }

    //Validate the data fields throughout the application
    //If anything isn't correct, highlight things in red and return false boolean
    public boolean validateData(){
        boolean returnValue = true;
        //Validation is different if the "Are you new" radio button is selected
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

        if(!rdbtnNotFirstClass.isSelected() && !rdbtnAreYouNew.isSelected()){
            lblNew.setForeground(Color.RED);
            returnValue = false;
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
        //These three are not in the hash map
        lblAge.setForeground(Color.BLACK);
        lblNumberOfKids.setForeground(Color.BLACK);
        lblNew.setForeground(Color.BLACK);
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
        firstClassButtonGroup.clearSelection();
    }

    //Allows you to get the actual month i.e.Jan, Feb, Mar  
    public String getMonth(JDatePickerImpl datePicker){
        int day = datePicker.getModel().getDay();
        int month = datePicker.getModel().getMonth();
        int year = datePicker.getModel().getYear();
        GregorianCalendar c = new GregorianCalendar(year, month, day);
        String[] months = new DateFormatSymbols().getMonths(); // Get day names
        String chosenMonth = months[c.get(c.MONTH)];
        return chosenMonth;
    }

    //Allows you to get the day of the week i.e.Mon, Tues, Wed
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