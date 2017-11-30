/**
 * PEP Capping 2017 Algozzine's Class
 *
 * Displays the option of either uploading past attendance or taking attendance now for a current class
 *
 * @author Carlie Maxwell
 * @copyright 2017 Marist College
 * @version 0.1.0
 * @since 0.1.0
 */

package pep.attendance.client;

import com.alee.laf.*;
import org.apache.directory.api.ldap.model.exception.LdapException;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import pep.attendance.server.Authenticator;
import pep.attendance.server.DatabaseManager;

import javax.naming.NamingException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Properties;

public class OpeningFrame extends JFrame {

    private JPanel contentPane;
    private static pep.attendance.client.AttendanceFrame attendance_frame;
    private static FacilitatorFrame facilitator_frame;
    private static OpeningFrame opening_frame;
    private JDatePickerImpl datePicker;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    WebLookAndFeel.install();
                    opening_frame = new OpeningFrame();
                    opening_frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public OpeningFrame() {
        /*
        We must initialize the DatabaseManager here because we want
        to not only update the `.properties` files, but have the DB
        manager as a "global"
         */
    	try{
    		DatabaseManager myManager = new DatabaseManager();
    		myManager.updateProperties();
    		System.out.println("Connected");
    	} catch(Exception e) {
    		System.out.println("Not connected");
    	}

        opening_frame = this;
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        //If the application is connected to the in-house pep.attendance.server, execute
        //Opens up a File Finder directly where the attendance excel sheets are located
        //Once a file is chosen, the upload_frame is populated and displayed with the excel sheet information
        //If you are not connected to the pep.attendance.server, it will display a message stating that you are not
        JButton btnUploadAttendanceSheet = new JButton("Upload Past Attendance");
        btnUploadAttendanceSheet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if(Authenticator.authenticate() == true) {
                        //Open file allowing only excel files
                        //TODO: Install
                        JFileChooser chooser = new JFileChooser("./");
                        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                                "Excel Files (.xlsx)", "xlsx");
                        chooser.setFileFilter(filter);
                        int returnVal = chooser.showOpenDialog(getParent());
                        //When the correct type of file is selected, then read the excel file and populate the table
                        if (returnVal == JFileChooser.APPROVE_OPTION) {
                            //TODO: Open up new frame for just opening a file, sending the file path that you found
                            String filePath = chooser.getSelectedFile().getAbsolutePath();
                            opening_frame.dispose();
                            Authenticator.connection.close();
                            UploadFrame uploadFrame = new UploadFrame(filePath);
                            uploadFrame.setVisible(true);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "You are not connected to the pep.attendance.server");
                    }
                } catch (HeadlessException | NamingException | LdapException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        btnUploadAttendanceSheet.setBounds(12, 111, 201, 29);
        contentPane.add(btnUploadAttendanceSheet);

        //Takes you to the facilitator_frame, which allows the facilitator of the class to pre-set up universal variables about the class
        JButton btnTakeAttendanceNow = new JButton("Take Attendance Now");
        btnTakeAttendanceNow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                facilitator_frame = new FacilitatorFrame();
                facilitator_frame.setVisible(true);
                opening_frame.dispose();
            }
        });
        btnTakeAttendanceNow.setBounds(239, 111, 181, 29);
        contentPane.add(btnTakeAttendanceNow);

        //Closes the opening_frame
        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                opening_frame.dispose();
            }
        });
        btnClose.setBounds(176, 215, 97, 25);
        contentPane.add(btnClose);
    }
}
