/**
 * PEP Capping 2017 Algozzine's Class
 *
 * This frame is the set up frame for our main Attendance Application.
 * In this frame, the facilitator will choose from several fields which will then be sent
 * to the Attendance Application to store in the JTable.
 *
 * @author Sami Ellougani, Carlie Maxwell
 * @copyright 2017 Marist College
 * @version 0.1.0
 * @since 0.1.0
 */

package pep.attendance.client;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FacilitatorFrame extends JFrame {

    private JPanel contentPane;
    private static AttendanceFrame attendance_frame;
    private static FacilitatorFrame facilitator_frame;
    private JComboBox instructorNameComboBox;
    private JComboBox topicComboBox;
    private JComboBox startTimeComboBox;
    private JComboBox locationComboBox;
    private JComboBox languageComboBox;
    private JComboBox curriculumComboBox;
    private JDatePickerImpl datePicker;
    private JLabel lblInstructorName;
    private JLabel lblTopicOfClass;
    private JLabel lblDate;
    private JLabel lblStartTime;
    private JLabel lblLocationOfClass;
    private JLabel lblLanguage;
    private JLabel lblCurriculum;

    private Properties preDefinedFields = new Properties();

    /**
     * Create the frame.
     */
    public FacilitatorFrame() {
        /*
         * Load our predefined fields so that we don't have to query the CPCA network every time
         */
        try {
            this.preDefinedFields.load(new FileInputStream("./properties/sync.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        facilitator_frame = this;
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 587, 435);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        instructorNameComboBox = new JComboBox();
        topicComboBox = new JComboBox();
        startTimeComboBox = new JComboBox();
        locationComboBox = new JComboBox();

        instructorNameComboBox.setModel(
            new DefaultComboBoxModel(
                    this.preDefinedFields.getProperty("instructors").split(",")
            )
        );
        instructorNameComboBox.setBounds(182, 65, 211, 27);
        contentPane.add(instructorNameComboBox);

        topicComboBox.setModel(
            new DefaultComboBoxModel(
                    this.preDefinedFields.getProperty("topics").split(",")
            )
        );
        topicComboBox.setBounds(182, 145, 211, 27);
        contentPane.add(topicComboBox);

        //Calendar for date implementation is created here
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.setBounds(182, 192, 211, 25);
        contentPane.add(datePicker);

        startTimeComboBox.setModel(
            new DefaultComboBoxModel(
                new String[] {"Choose",
                    "10:00am",
                    "10:30am",
                    "11:00am",
                    "11:30am",
                    "12:00pm",
                    "12:30pm",
                    "1:00pm",
                    "1:30pm",
                    "2:00pm",
                    "2:30pm",
                    "3:00pm",
                    "3:30pm",
                    "4:00pm",
                    "4:30pm",
                    "5:00pm",
                    "5:30pm",
                    "6:00pm",
                    "6:30pm",
                    "7:00pm",
                    "7:30pm",
                    "8:00pm",
                    "8:30pm",
                    "9:00pm",
                    "9:30pm"}
            )
        );
        startTimeComboBox.setBounds(182, 230, 211, 27);
        contentPane.add(startTimeComboBox);


        locationComboBox.setModel(
            new DefaultComboBoxModel(
                this.preDefinedFields.getProperty("locations").split(",")
            )
        );
        locationComboBox.setBounds(182, 269, 211, 27);
        contentPane.add(locationComboBox);

        JLabel lblPepFacilitatorSetup = new JLabel("PEP FACILITATOR SET-UP");
        lblPepFacilitatorSetup.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        lblPepFacilitatorSetup.setBounds(165, 36, 258, 16);
        contentPane.add(lblPepFacilitatorSetup);

        //Checks for validation, and then creates the Attendance Frame
        //This frame is then deleted, but the fields in this frame are sent to the Attendance App
        JButton btnProceed = new JButton("Proceed");
        btnProceed.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean validated = validation();
                if(validated){
                    String instructorName = instructorNameComboBox.getSelectedItem().toString();
                    String topic = topicComboBox.getSelectedItem().toString();
                    String startTime = startTimeComboBox.getSelectedItem().toString();
                    String location = locationComboBox.getSelectedItem().toString();
                    String language = languageComboBox.getSelectedItem().toString();
                    String curriculum = curriculumComboBox.getSelectedItem().toString();
                    attendance_frame = new AttendanceFrame(instructorName, topic, datePicker, startTime, location, language, curriculum);
                    attendance_frame.setVisible(true);
                    facilitator_frame.dispose();
                }
            }
        });

        btnProceed.setBounds(447, 360, 117, 29);
        contentPane.add(btnProceed);

        lblInstructorName = new JLabel("Instructor Name:");
        lblInstructorName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblInstructorName.setBounds(64, 71, 109, 14);
        contentPane.add(lblInstructorName);

        lblTopicOfClass = new JLabel("Topic of Class:");
        lblTopicOfClass.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTopicOfClass.setBounds(64, 149, 109, 14);
        contentPane.add(lblTopicOfClass);

        lblDate = new JLabel("Date:");
        lblDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDate.setBounds(64, 192, 46, 14);
        contentPane.add(lblDate);

        lblStartTime = new JLabel("Start Time:");
        lblStartTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblStartTime.setBounds(64, 234, 83, 14);
        contentPane.add(lblStartTime);

        lblLocationOfClass = new JLabel("Location of Class:");
        lblLocationOfClass.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblLocationOfClass.setBounds(64, 275, 117, 14);
        contentPane.add(lblLocationOfClass);

        lblLanguage = new JLabel("Language:");
        lblLanguage.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblLanguage.setBounds(64, 316, 109, 23);
        contentPane.add(lblLanguage);

        languageComboBox = new JComboBox();
        languageComboBox.setModel(
            new DefaultComboBoxModel(
                new String[] {"Choose", "English", "Spanish"}
            )
        );
        languageComboBox.setBounds(182, 316, 211, 27);
        contentPane.add(languageComboBox);

        lblCurriculum = new JLabel("Curriculum:");
        lblCurriculum.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCurriculum.setBounds(64, 105, 109, 31);
        contentPane.add(lblCurriculum);

        curriculumComboBox = new JComboBox();
        curriculumComboBox.setModel(
            new DefaultComboBoxModel(
                this.preDefinedFields.getProperty("curricula").split(",")
            )
        );
        curriculumComboBox.setBounds(182, 105, 211, 27);
        contentPane.add(curriculumComboBox);
    }


    //Returns a boolean indicating if any fields are not filled
    //Also, changes the color of the label to red
    public boolean validation() {
        boolean flag = true;
        clearColors();

        if(instructorNameComboBox.getSelectedItem().equals("Choose")){
            lblInstructorName.setForeground(Color.RED);
            flag = false;
        }

        if(topicComboBox.getSelectedItem().equals("Choose")){
            lblTopicOfClass.setForeground(Color.RED);
            flag = false;
        }

        if(datePicker.getModel().getValue() == null){
            lblDate.setForeground(Color.RED);
            flag = false;
        }

        if(startTimeComboBox.getSelectedItem().equals("Choose")){
            lblStartTime.setForeground(Color.RED);
            flag = false;
        }

        if(locationComboBox.getSelectedItem().equals("Choose")){
            lblLocationOfClass.setForeground(Color.RED);
            flag = false;
        }

        if(languageComboBox.getSelectedItem().equals("Choose")){
            lblLanguage.setForeground(Color.RED);
            flag = false;
        }

        if(curriculumComboBox.getSelectedItem().equals("Choose")){
            lblCurriculum.setForeground(Color.RED);
            flag = false;
        }

        return flag;
    }

    //Clears all the colors of the labels in the form 
    //This is to reset labels to black if they weren't filled out, but got filled out
    public void clearColors(){
        lblInstructorName.setForeground(Color.BLACK);
        lblTopicOfClass.setForeground(Color.BLACK);
        lblDate.setForeground(Color.BLACK);
        lblStartTime.setForeground(Color.BLACK);
        lblLocationOfClass.setForeground(Color.BLACK);
        lblLanguage.setForeground(Color.BLACK);
        lblCurriculum.setForeground(Color.BLACK);
    }
}