/**
 * PEP Capping 2017 Algozzine's Class
 *
 * This class is the popup that appears when you select to change the date within the upload frame. 
 * Once the date is selected, the JTable on the upload frame will populate with the new date.
 *
 * @author Sami Ellougani
 * @copyright 2017 Marist College
 * @version 0.1.0
 * @since 0.1.0
 */

package pep.attendance.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Properties;

public class ChangeDate extends JFrame {

    private JPanel contentPane;
    private ChangeDate frame;
    private JDatePickerImpl datePicker;

    /**
     * Create the frame.
     */
    public ChangeDate(UploadFrame uploadFrame) {
        setResizable(false);
        frame = this;
        frame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowDeactivated(WindowEvent e){
                frame.toFront();
            }
        });

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //Close the change_date popup
        JButton btnCancel = new JButton("Close");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                uploadFrame.popUpOpen = false;
                frame.dispose();
            }
        });
        btnCancel.setBounds(12, 223, 97, 25);
        contentPane.add(btnCancel);

        JLabel lblChangeDate = new JLabel("Change Date");
        lblChangeDate.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblChangeDate.setBounds(146, 13, 137, 43);
        contentPane.add(lblChangeDate);

        JLabel lblSelect = new JLabel("Select:");
        lblSelect.setBounds(78, 111, 56, 16);
        contentPane.add(lblSelect);

        //Calendar for date implementation
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.setBounds(133, 108, 167, 32);
        contentPane.add(datePicker);

        //Changes the value on the upload frame to the newly selected item from the popup
        //The popup open boolean is set to false, allowing the upload frame to open other popups
        JButton btnChange = new JButton("Change");
        btnChange.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(datePicker.getModel().getValue() == null){
                    lblSelect.setForeground(Color.RED);
                } else {
                    uploadFrame.dateChange = datePicker;
                    uploadFrame.popUpOpen = false;
                    uploadFrame.redoFields();
                    frame.dispose();
                }
            }
        });

        btnChange.setBounds(325, 223, 97, 25);
        contentPane.add(btnChange);
    }
}