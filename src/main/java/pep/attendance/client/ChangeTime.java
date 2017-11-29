/**
 * PEP Capping 2017 Algozzine's Class
 *
 * This class is the popup that appears when you select to change the time within the upload frame. 
 * Once the time is selected, the JTable on the upload frame will populate with the new time.
 *
 * @author Sami Ellougani
 * @copyright 2017 Marist College
 * @version 0.1.0
 * @since 0.1.0
 */

package pep.attendance.client;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ChangeTime extends JFrame {

    private JPanel contentPane;
    private ChangeTime frame;

    /**
     * Create the frame.
     */
    public ChangeTime(UploadFrame uploadFrame) {
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

        //Closes the change_time popup
        JButton btnCancel = new JButton("Close");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                uploadFrame.popUpOpen = false;
                frame.dispose();
            }
        });
        btnCancel.setBounds(12, 223, 97, 25);
        contentPane.add(btnCancel);

        JComboBox comboBox = new JComboBox();
        comboBox.setModel(
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
        comboBox.setBounds(133, 108, 167, 22);
        contentPane.add(comboBox);

        JLabel lblChangeTime = new JLabel("Change Time");
        lblChangeTime.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblChangeTime.setBounds(151, 13, 149, 43);
        contentPane.add(lblChangeTime);

        //Changes the value on the upload frame to the newly selected item from the popup
        //The popup open boolean is set to false, allowing the upload frame to open other popups
        JButton btnChange = new JButton("Change");
        btnChange.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(comboBox.getSelectedItem().toString() == "Choose"){
                    lblChangeTime.setForeground(Color.RED);
                } else {
                    uploadFrame.timeChange = comboBox.getSelectedItem().toString();
                    uploadFrame.popUpOpen = false;
                    uploadFrame.redoFields();
                    frame.dispose();
                }
            }
        });
        btnChange.setBounds(325, 223, 97, 25);
        contentPane.add(btnChange);

        JLabel lblSelect = new JLabel("Select:");
        lblSelect.setBounds(78, 111, 56, 16);
        contentPane.add(lblSelect);
    }
}