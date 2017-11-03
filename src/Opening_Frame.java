package javaApplication;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import com.alee.laf.WebLookAndFeel;

public class Opening_Frame extends JFrame {

    private JPanel contentPane;
    private static Attendance_Frame attendance_frame;
    private static Facilitator_Frame facilitator_frame;
    private static Opening_Frame opening_frame;
    private JDatePickerImpl datePicker;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	WebLookAndFeel.install();
                    Opening_Frame frame = new Opening_Frame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Opening_Frame() {
        opening_frame = this;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        //TODO: FIX MINOR NULL POINTER EXCEPTIONS WITH THE TWO BUTTONS
        JButton btnUploadAttendanceSheet = new JButton("Upload Past Attendance");
        btnUploadAttendanceSheet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                  //TODO: IF TRUE - OPEN FILE FINDER HERE
//                if(Authenticator.authenticate() == true) {
                    //Open file allowing only excel files
                    JFileChooser chooser = new JFileChooser();
                    FileNameExtensionFilter filter = new FileNameExtensionFilter(
                            "Excel Files (.xlsx)", "xlsx");
                    chooser.setFileFilter(filter);
                    int returnVal = chooser.showOpenDialog(getParent());
                    //When the correct type of file is selected, then read the excel file and populate the table
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                    	//TODO: Open up new frame for just opening a file, sending the file path that you found
                        String filePath = chooser.getSelectedFile().getAbsolutePath();
                        System.out.println(filePath);
                        opening_frame.dispose();
                    }
//                } else {
//                    JOptionPane.showMessageDialog(null, "You are not connected to the server");
//                }
            }
        });


        btnUploadAttendanceSheet.setBounds(26, 111, 201, 29);
        contentPane.add(btnUploadAttendanceSheet);

        JButton btnTakeAttendanceNow = new JButton("Take Attendance Now");
        btnTakeAttendanceNow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                facilitator_frame = new Facilitator_Frame();
                facilitator_frame.setVisible(true);
                opening_frame.dispose();
            }
        });
        btnTakeAttendanceNow.setBounds(235, 111, 181, 29);
        contentPane.add(btnTakeAttendanceNow);
    }
}