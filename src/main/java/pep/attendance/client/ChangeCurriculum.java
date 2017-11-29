/**
 * PEP Capping 2017 Algozzine's Class
 *
 * This class is the pop up that appears when you select to change the curriculum within
 * the upload frame. Once selecting the curriculum, the JTable on the Upload Frame will populate 
 * with the new curriculum
 *
 * @author Sami Ellougani, Carlie Maxwell
 * @copyright 2017 Marist College
 * @version 0.1.0
 * @since 0.1.0
 */
package pep.attendance.client; 

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ChangeCurriculum extends JFrame {

    private JPanel contentPane;
    private ChangeCurriculum frame;

    private Properties preDefinedFields = new Properties();

    /**
     * Create the frame.
     */
    public ChangeCurriculum(UploadFrame uploadFrame) {
        /*
         * Load our predefined fields so that we don't have to query the CPCA network every time
         */
        try {
            this.preDefinedFields.load(new FileInputStream("./resources/sync.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

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

        //Cancel button triggers the pop up open boolean in the uploadFrame to false, allowing the upload frame to open other pop ups
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
                this.preDefinedFields.getProperty("curricula").split(",")
            )
        );
        comboBox.setBounds(133, 108, 167, 22);
        contentPane.add(comboBox);

        JLabel lblChangeCurriculum = new JLabel("Change Curriculum");
        lblChangeCurriculum.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblChangeCurriculum.setBounds(109, 13, 204, 43);
        contentPane.add(lblChangeCurriculum);

        //Change button triggers data validation on click
        //If successful, the pop up open boolean in the uploadFrame is set to false, allowing the upload frame to open other pop ups
        JButton btnChange = new JButton("Change");
        btnChange.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(comboBox.getSelectedItem().toString() == "Choose"){
                    lblChangeCurriculum.setForeground(Color.RED);
                } else {
                    uploadFrame.curriculumChange = comboBox.getSelectedItem().toString();
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