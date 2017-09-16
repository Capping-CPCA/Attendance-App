import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class Attendance_Frame extends JFrame {

	private JPanel contentPane;
	private JTextField fNameTF;
	private JTextField lNameTF;
	private JTextField textField_2;
	private JTextField specifyOtherRaceTF;
	private JTable outputTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Attendance_Frame frame = new Attendance_Frame();
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
	public Attendance_Frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1010, 639);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCapcAttendance = new JLabel("CAPC Attendance Sheet");
		lblCapcAttendance.setBounds(337, 16, 330, 38);
		lblCapcAttendance.setFont(new Font("Cambria", Font.PLAIN, 30));
		contentPane.add(lblCapcAttendance);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(17, 399, 79, 30);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(17, 441, 79, 22);
		contentPane.add(lblLastName);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setBounds(17, 509, 61, 22);
		contentPane.add(lblAge);
		
		JLabel lblNewToProgram = new JLabel("Are you new to the program?");
		lblNewToProgram.setBounds(17, 543, 189, 22);
		contentPane.add(lblNewToProgram);
		
		JLabel lblRace = new JLabel("Race:");
		lblRace.setBounds(17, 475, 61, 22);
		contentPane.add(lblRace);
		
		JRadioButton rdbtnNewToProgramYes = new JRadioButton("Yes");
		rdbtnNewToProgramYes.setBounds(205, 542, 141, 23);
		contentPane.add(rdbtnNewToProgramYes);
		
		JRadioButton rdbtnNewToProgramNo = new JRadioButton("No");
		rdbtnNewToProgramNo.setBounds(205, 567, 141, 23);
		contentPane.add(rdbtnNewToProgramNo);
		
		fNameTF = new JTextField();
		fNameTF.setBounds(91, 399, 164, 30);
		contentPane.add(fNameTF);
		fNameTF.setColumns(10);
		
		lNameTF = new JTextField();
		lNameTF.setBounds(91, 437, 164, 30);
		contentPane.add(lNameTF);
		lNameTF.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(55, 507, 46, 26);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(875, 566, 117, 29);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(btnSubmit);
		
		JComboBox raceComboBox = new JComboBox();
		raceComboBox.setBounds(55, 474, 204, 27);
		raceComboBox.setModel(new DefaultComboBoxModel(new String[] {"White", "African-American", "Hispanic", "Asian", "Other"}));
		contentPane.add(raceComboBox);
		
		specifyOtherRaceTF = new JTextField();
		specifyOtherRaceTF.setBounds(337, 471, 156, 30);
		contentPane.add(specifyOtherRaceTF);
		specifyOtherRaceTF.setColumns(10);
		
		JLabel lblSpecifyOtherRace = new JLabel("Please Specify:");
		lblSpecifyOtherRace.setBounds(260, 478, 102, 18);
		lblSpecifyOtherRace.setFont(new Font("Lucida Grande", Font.ITALIC, 10));
		contentPane.add(lblSpecifyOtherRace);
		
		JLabel lblInfoMessage = new JLabel("Please fill out the information below:");
		lblInfoMessage.setBounds(17, 365, 305, 22);
		lblInfoMessage.setFont(new Font("Lucida Grande", Font.ITALIC, 15));
		contentPane.add(lblInfoMessage);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(18, 63, 974, 278);
		contentPane.add(scrollPane);
		
		outputTable = new JTable();
		scrollPane.setViewportView(outputTable);
	}
}
