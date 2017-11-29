/**
 * PEP Capping 2017 Algozzine's Class
 *
 * This class is what allows the facilitator to edit and then upload the Attendance Sheet
 * This frame calls the other change_* frames to change fields within the JTable
 * The user can save, edit, reopen, and upload from this frame
 *
 * @author Sami Ellougani, Carlie Maxwell
 * @copyright 2017 Marist College
 * @version 0.1.0
 * @since 0.1.0
 */

package pep.attendance.client;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.util.GregorianCalendar;
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
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdatepicker.impl.JDatePickerImpl;
import pep.attendance.server.DatabaseManager;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

public class UploadFrame extends JFrame {

    private JPanel contentPane;
    private JFrame frame;
    private JTable outputTable;
    private DefaultTableModel defaultModel;
    private MyTableModel model = new MyTableModel();

    //Variables to hold changed values from other frames here
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
    public UploadFrame(String filePath) {
        frame = this;
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 1221, 374);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblCpcaAttendance = new JLabel("PEP Attendance Sheet");
        lblCpcaAttendance.setBounds(405, 23, 330, 38);
        lblCpcaAttendance.setFont(new Font("Cambria", Font.PLAIN, 30));
        contentPane.add(lblCpcaAttendance);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 1215, 22);
        contentPane.add(menuBar);

        JMenu mnFile = new JMenu("File");
        menuBar.add(mnFile);

        //Saves the file into an excel sheet when the "Save" menu item is selected
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

                //TODO: Install
                try (FileOutputStream outputStream = new FileOutputStream("./" + filePath)) {
                    workbook.write(outputStream);
                } catch (IOException e1){
                    System.out.println("IOException: " + e1.getMessage());
                }
            }
        });

        mnFile.add(mntmSave);

        //Close the frame when the menu item "Exit" is selected
        JMenuItem mntmExit = new JMenuItem("Exit");
        mntmExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //TODO: Ask if they want to save
                frame.dispose();
                System.exit(0);
            }
        });

        //Open a new excel file when the menu item "Open" is selected
        JMenuItem mntmOpen = new JMenuItem("Open");
        mntmOpen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //TODO: Install
                JFileChooser chooser = new JFileChooser("./");
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "Excel Files (.xlsx)", "xlsx");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(getParent());
                //When the correct type of file is selected, then read the excel file and populate the table
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    //TODO: Open up new frame for just opening a file, sending the file path that you found
                    String tempFilePath = chooser.getSelectedFile().getAbsolutePath();
                    model.setRowCount(0);
                    clearStore();
                    initialOpen(tempFilePath);
                }
            }
        });
        mnFile.add(mntmOpen);
        mnFile.add(mntmExit);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 74, 1215, 182);
        contentPane.add(scrollPane);

        outputTable = new JTable();
        scrollPane.setViewportView(outputTable);
        outputTable.setModel(model);

        //TODO: Database stuff here
        JButton btnUpload = new JButton("Upload");
        btnUpload.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String date = (String) outputTable.getValueAt(0, 2);
                String[] dateArray = date.split(" ");
                String year = dateArray[5];
                //Logic to get month
                String month = "";
                if(dateArray[1].equals("Jan")) {
                    month = "01";
                } else if(dateArray[1].equals("Feb")) {
                    month = "02";
                } else if(dateArray[1].equals("Mar")) {
                    month = "03";
                } else if(dateArray[1].equals("Apr")) {
                    month = "04";
                } else if(dateArray[1].equals("May")) {
                    month = "05";
                } else if(dateArray[1].equals("Jun")) {
                    month = "06";
                } else if(dateArray[1].equals("Jul")) {
                    month = "07";
                } else if(dateArray[1].equals("Aug")) {
                    month = "08";
                } else if(dateArray[1].equals("Sep")) {
                    month = "09";
                } else if(dateArray[1].equals("Oct")) {
                    month = "10";
                } else if(dateArray[1].equals("Nov")) {
                    month = "11";
                } else if(dateArray[1].equals("Dec")) {
                    month = "12";
                }
                String day = dateArray[2];

                String startTime = (String) outputTable.getValueAt(0,6);
                String amOrPm = startTime.substring(startTime.length()-2, startTime.length());
                String time;
                if(amOrPm.equals("pm")){
                    if(startTime.length() == 6){
                        int oldHour = Integer.parseInt(startTime.substring(0, 1));
                        int newHour = oldHour + 12;
                        String hour = String.valueOf(newHour);
                        String minutes = startTime.substring(2, 4);
                        String seconds = "00";
                        time = hour + ":" + minutes + ":" + seconds;
                    } else {
                        int oldHour = Integer.parseInt(startTime.substring(0, 2));
                        int newHour = oldHour + 12;
                        String hour = String.valueOf(newHour);
                        String minutes = startTime.substring(3, 5);
                        String seconds = "00";
                        time = hour + ":" + minutes + ":" + seconds;
                    }
                } else {
                    if(startTime.length() == 6){
                        String hour = startTime.substring(0, 1);
                        String minutes = startTime.substring(2, 4);
                        String seconds = "00";
                        time = hour + ":" + minutes + ":" + seconds;
                    } else {
                        String hour = startTime.substring(0, 2);
                        String minutes = startTime.substring(3, 5);
                        String seconds = "00";
                        time = hour + ":" + minutes + ":" + seconds;
                    }
                }
                String fullDate = year + "-" + month + "-" + day + " " + time;

                for (int i = 0; i < outputTable.getRowCount(); i++) {

                    // Lets go ahead and execute the queries
                    DatabaseManager myManager = new DatabaseManager();

                    /* START OF ParticipantID CHECK
                    first we need to check that the person exists in the database

                    what we want to do is check for duplicates and open the "collision
                    frame" for the user to resolve the conflict if there are duplicates
                     */
                    int personId = -1;
                    PreparedStatement personStmt = null;
                    try {
                        personStmt = myManager
                                .getConnection()
                                .prepareStatement(
                                        "SELECT People.peopleID " +
                                                "FROM People " +
                                                "WHERE People.firstName = ? AND " +
                                                "People.lastName = ? LIMIT 1"
                                );
                        personStmt.setString(1, (String) outputTable.getValueAt(i, 0));
                        personStmt.setString(2, (String) outputTable.getValueAt(i, 1));

                        ResultSet results = personStmt.executeQuery();

                        // we need to let the user decide which participant is attending if there are multiple with
                        // the same name
                        int rowCount = 0;
                        if (results.last()) {
                            rowCount = results.getRow();

                            // not rs.first() because the rs.next() below will move on, missing the first element
                            results.beforeFirst();
                        }

                        // we do this here, because it is impossible given
                        // the current system to detect duplicates on its
                        // own
                        if (rowCount > 1) {
                            CollisionFrame collisionFrame = new CollisionFrame();
                            collisionFrame.setVisible(true);

                            // empty while loop
                            while (collisionFrame.matchedParticipantID < 0) {}

                            personId = collisionFrame.matchedParticipantID;
                        } else {
                            while (results.next()) {
                                personId = results.getInt(1);
                            }
                        }


                        System.err.println("person ID: " + personId);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    /* END OF ParticipantID CHECK */

                    /* START OF ClassName CHECK
                    now we need the class name
                     */
                    int classId = -1;
                    PreparedStatement classStmt = null;
                    try {
                        classStmt = myManager
                                .getConnection()
                                .prepareStatement(
                                        "SELECT Classes.classId " +
                                                "FROM Classes " +
                                                "WHERE Classes.topicName = ?;"
                                );
                        System.err.println((String) outputTable.getValueAt(i, 4));
                        classStmt.setString(1, (String) outputTable.getValueAt(i, 4));

                        ResultSet results = classStmt.executeQuery();

                        if (results.next())
                            classId = results.getInt(1);

                        System.err.println("class ID: " + classId);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    /* END OF ClassName CHECK */

                    /* START OF CurriculumName CHECK
                    get curriculum name
                     */
                    int curriculumId = -1;
                    PreparedStatement curriculumStmt = null;
                    try {
                        curriculumStmt = myManager
                                .getConnection()
                                .prepareStatement(

                                        "SELECT Curricula.curriculumID " +
                                                "FROM Curricula " +
                                                "WHERE Curricula.curriculumName = ?;"
                                );
                        curriculumStmt.setString(1, (String) outputTable.getValueAt(i, 3));

                        ResultSet results = curriculumStmt.executeQuery();

                        if (results.next())
                            curriculumId = results.getInt(1);

                        System.err.println("curriculum ID: " + curriculumId);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    /* END OF CurriculumName CHECK */

                    // now we need to get the facilitator ID
                    int instructorId = -1;
                    PreparedStatement facilitatorStmt = null;
                    try {
                        facilitatorStmt = myManager
                                .getConnection()
                                .prepareStatement(
                                        "SELECT People.peopleID " +
                                                "FROM People " +
                                                "WHERE People.firstName = ? AND " +
                                                "People.lastName = ? LIMIT 1"
                                );

                        String[] instructorFullName = ((String) outputTable.getValueAt(i, 15)).split(" ");
                        facilitatorStmt.setString(1, instructorFullName[0]);
                        facilitatorStmt.setString(2, instructorFullName[1]);


                        ResultSet results = facilitatorStmt.executeQuery();

                        if (results.next())
                            instructorId = results.getInt(1);
                        System.err.println("instructor ID: " + instructorId);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    /* END OF FacilitatorID CHECK */

                    // now lets call the stored procedure
                    try {
                        CallableStatement procStmt = myManager
                                .getConnection()
                                .prepareCall(
                                        " { call attendanceInsert( " +
                                                "attendanceParticipantID := ?::INT, " +
                                                "attendantAge := ?::INT, " +
                                                "attendanceParticipantRace := ?::RACE, " +
                                                "attendanceParticipantSex := ?::SEX, " +
                                                "attendanceSite := ?::TEXT, " +
                                                "attendanceFacilitatorID := ?::INT, " +
                                                "attendanceClassID := ?::INT, " +
                                                "attendanceDate := ?::TIMESTAMP, " +
                                                "attendanceCurriculumID := ?::INT, " +
                                                "attendanceComments := ?::TEXT, " +
                                                "attendanceNumChildren := ?::INT, " +
                                                "isAttendanceNew := ?::BOOLEAN, " +
                                                "attendanceParticipantZipCode := ?::VARCHAR(5), " +
                                                "classOfferingLang := ?::TEXT) } "
                                );


                        // if person Id is < 0 throw error
                        // participantID
                        procStmt.setInt(1, personId);
                        System.out.println(personId);
                        // age
                        procStmt.setInt(2, Integer.parseInt((String) outputTable.getValueAt(i, 11)));
                        System.out.println(Integer.parseInt((String) outputTable.getValueAt(i, 11)));
                        // race
                        procStmt.setString(3, (String) outputTable.getValueAt(i, 10));
                        System.out.println((String) outputTable.getValueAt(0, 10));
                        // sex
                        procStmt.setString(4, (String) outputTable.getValueAt(i, 9));
                        System.out.println((String) outputTable.getValueAt(0, 9));
                        // site
                        procStmt.setString(5, (String) outputTable.getValueAt(i, 3));
                        System.out.println((String) outputTable.getValueAt(0, 3));
                        // facilitator
                        procStmt.setInt(6, instructorId);
                        System.out.println(instructorId);
                        // class id
                        procStmt.setInt(7, classId);
                        System.out.println(classId);
                        // date
                        procStmt.setString(8, fullDate);
                        System.out.println(fullDate);
                        // curriculum
                        procStmt.setInt(9, curriculumId);
                        System.out.println(curriculumId);
                        // comments
                        procStmt.setString(10, "");
                        System.out.println("comments is nothing");
                        // num children
                        procStmt.setInt(11, Integer.parseInt((String) outputTable.getValueAt(i, 13)));
                        System.out.println((String) outputTable.getValueAt(0, 13));
                        // is new
                        procStmt.setBoolean(12, (boolean) outputTable.getValueAt(i, 12));
                        System.out.println((boolean) outputTable.getValueAt(0, 12));
                        // zip code
                        procStmt.setString(13, (String) outputTable.getValueAt(i, 14));
                        System.out.println((String) outputTable.getValueAt(0, 14));
                        // language
                        procStmt.setString(14, (String) outputTable.getValueAt(i, 8));
                        System.out.println((String) outputTable.getValueAt(0, 8));


                        boolean isExec = procStmt.execute();
                        procStmt.close();
                    } catch (SQLException err) {
                        err.printStackTrace();
                    }

                }
            }
        });
        btnUpload.setBounds(1068, 269, 135, 63);
        contentPane.add(btnUpload);

        //Trigger the ChangeDate frame
        JButton btnChangeDate = new JButton("Change Date");
        btnChangeDate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!popUpOpen){
                    ChangeDate changeDate = new ChangeDate((UploadFrame) frame);
                    changeDate.setVisible(true);
                    changeDate.setAlwaysOnTop(true);
                    popUpOpen = true;
                }
            }
        });
        btnChangeDate.setBounds(12, 301, 141, 25);
        contentPane.add(btnChangeDate);

        //Trigger the ChangeCurriculum frame
        JButton btnChangeCurriculum = new JButton("Change Curriculum");
        btnChangeCurriculum.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(!popUpOpen){
                    ChangeCurriculum changeCurriculum = new ChangeCurriculum((UploadFrame) frame);
                    changeCurriculum.setVisible(true);
                    changeCurriculum.setAlwaysOnTop(true);
                    popUpOpen = true;
                }
            }
        });

        btnChangeCurriculum.setBounds(12, 263, 141, 25);
        contentPane.add(btnChangeCurriculum);

        //Trigger the ChangeTopic frame
        JButton btnChangeTopic = new JButton("Change Topic");
        btnChangeTopic.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!popUpOpen){
                    ChangeTopic changeTopic = new ChangeTopic((UploadFrame) frame);
                    changeTopic.setVisible(true);
                    changeTopic.setAlwaysOnTop(true);
                    popUpOpen = true;
                }
            }
        });
        btnChangeTopic.setBounds(165, 301, 141, 25);
        contentPane.add(btnChangeTopic);

        //Trigger the ChangeTime frame
        JButton btnChangeTime = new JButton("Change Time");
        btnChangeTime.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!popUpOpen){
                    ChangeTime changeTime = new ChangeTime((UploadFrame) frame);
                    changeTime.setVisible(true);
                    changeTime.setAlwaysOnTop(true);
                    popUpOpen = true;
                }
            }
        });
        btnChangeTime.setBounds(165, 263, 141, 25);
        contentPane.add(btnChangeTime);

        //Trigger the ChangeLanguage frame
        JButton btnChangeLanguage = new JButton("Change Language");
        btnChangeLanguage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!popUpOpen){
                    ChangeLanguage changeLanguage = new ChangeLanguage((UploadFrame) frame);
                    changeLanguage.setVisible(true);
                    changeLanguage.setAlwaysOnTop(true);
                    popUpOpen = true;
                }
            }
        });
        btnChangeLanguage.setBounds(318, 263, 141, 25);
        contentPane.add(btnChangeLanguage);

        //Trigger the ChangeLocation frame
        JButton btnChangeLocation = new JButton("Change Location");
        btnChangeLocation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!popUpOpen){
                    ChangeLocation changeLocation = new ChangeLocation((UploadFrame) frame);
                    changeLocation.setVisible(true);
                    changeLocation.setAlwaysOnTop(true);
                    popUpOpen = true;
                }
            }
        });
        btnChangeLocation.setBounds(318, 301, 141, 25);
        contentPane.add(btnChangeLocation);

        //Trigger the Change_Reset frame
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
        btnReset.setBounds(921, 269, 135, 63);
        contentPane.add(btnReset);

        //Populate screen with initial open of excel file
        initialOpen(filePath);
    }

    //This is the initial formatting of the JTable for this frame
    //This takes in the filePath that was given from the Opening_Frame and populates the JTable from the excel sheet
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
                String[] row = new String[16];
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
                        row[14],
                        row[15]
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

    //This function changes the fields in the JTable if anything was selected from any of the Change Frames
    //For example, if I change the date, there will be a date cached in the dateChange variable, which will trigger the conditional to change the columns to the new date
    public void redoFields(){
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

    //Sets up the sex column in the JTable to be a dropdown
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

    //Sets up the race column in the JTable to be a dropdown
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

    //Open source code to allow a combo box to be in a JTable cell
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

    //Cleares the variables cached from previous change pop ups
    public void clearStore(){
        curriculumChange = "";
        dateChange = null;
        timeChange = "";
        topicChange = "";
        languageChange = "";
        locationChange = "";
    }
}