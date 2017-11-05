package javaApplication;

import java.awt.BorderLayout;
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

public class Change_Curriculum extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Change_Curriculum() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(12, 223, 97, 25);
		contentPane.add(btnCancel);
		
		JButton btnChange = new JButton("Change");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnChange.setBounds(325, 223, 97, 25);
		contentPane.add(btnChange);
		
		JLabel lblChangeCurriculum = new JLabel("Change Curriculum");
		lblChangeCurriculum.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblChangeCurriculum.setBounds(109, 13, 204, 43);
		contentPane.add(lblChangeCurriculum);
		
		JLabel lblSelect = new JLabel("Select:");
		lblSelect.setBounds(78, 111, 56, 16);
		contentPane.add(lblSelect);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Choose", "Florence Manor", "Fishkill", "ITAP", "Cornerstone", "Meadow Run", "Fox Run"}));
		comboBox.setBounds(133, 108, 167, 22);
		contentPane.add(comboBox);
	}
}
