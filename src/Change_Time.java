package javaApplication;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Change_Time extends JFrame {

	private JPanel contentPane;
	private Change_Time frame;

	/**
	 * Create the frame.
	 */
	public Change_Time(Upload_Frame uploadFrame) {
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
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Choose", "10:00am", "11:00am", "11:30am", "12:30pm", "1:00pm", "1:30pm", "2:00pm", "4:30pm", "6:00pm"}));
		comboBox.setBounds(133, 108, 167, 22);
		contentPane.add(comboBox);
		
		JLabel lblChangeTime = new JLabel("Change Time");
		lblChangeTime.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblChangeTime.setBounds(151, 13, 149, 43);
		contentPane.add(lblChangeTime);
		
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
