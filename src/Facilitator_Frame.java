package javaApplication;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.alee.laf.WebLookAndFeel;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.awt.event.ActionEvent;

import com.alee.laf.WebLookAndFeel;

public class Facilitator_Frame extends JFrame {

	private JPanel contentPane;
	private static Attendance_Frame attendance_frame;
	private static Facilitator_Frame facilitator_frame;
	private JComboBox instructorNameComboBox;
	private JComboBox topicComboBox;
	private JComboBox startTimeComboBox;
	private JComboBox locationComboBox;
	private JDatePickerImpl datePicker;
	private JLabel lblInstructorName;
	private JLabel lblTopicOfClass;
	private JLabel lblDate;
	private JLabel lblStartTime;
	private JLabel lblLocationOfClass;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WebLookAndFeel.install ();
					facilitator_frame = new Facilitator_Frame();
					facilitator_frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public Facilitator_Frame() {
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
		
		instructorNameComboBox.setModel(new DefaultComboBoxModel(new String[] {"Choose", "Carlie", "Sami"}));
		instructorNameComboBox.setBounds(182, 81, 211, 27);
		contentPane.add(instructorNameComboBox);
		
		topicComboBox.setModel(new DefaultComboBoxModel(new String[] {"Choose", "Happy", "Healthy"}));
		topicComboBox.setBounds(182, 120, 211, 27);
		contentPane.add(topicComboBox);
		
		//Calender for date implementation
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(182, 167, 211, 25);
		contentPane.add(datePicker);
	
		startTimeComboBox.setModel(new DefaultComboBoxModel(new String[] {"Choose", "11:00", "12:00"}));
		startTimeComboBox.setBounds(182, 205, 211, 27);
		contentPane.add(startTimeComboBox);
		
		
		locationComboBox.setModel(new DefaultComboBoxModel(new String[] {"Choose", "Foxrun", "Poughkeepsie"}));
		locationComboBox.setBounds(182, 244, 211, 27);
		contentPane.add(locationComboBox);
		
		JLabel lblPepFacilitatorSetup = new JLabel("PEP FACILITATOR SET-UP");
		lblPepFacilitatorSetup.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblPepFacilitatorSetup.setBounds(165, 36, 258, 16);
		contentPane.add(lblPepFacilitatorSetup);
		
		JButton btnProceed = new JButton("Proceed");
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean validated = validation();
				if(validated){
					String instructorName = instructorNameComboBox.getSelectedItem().toString();
					int topicIndex = topicComboBox.getSelectedIndex();
					int day = datePicker.getModel().getDay();
					int month = datePicker.getModel().getMonth();
					int year = datePicker.getModel().getYear();
					String startTime = startTimeComboBox.getSelectedItem().toString();
					String location = locationComboBox.getSelectedItem().toString();
					attendance_frame = new Attendance_Frame(instructorName, topicIndex, day, month, year, startTime, location);
					attendance_frame.setVisible(true);
					facilitator_frame.dispose();
				}
			}
		});

		btnProceed.setBounds(447, 360, 117, 29);
		contentPane.add(btnProceed);
		
		lblInstructorName = new JLabel("Instructor Name:");
		lblInstructorName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInstructorName.setBounds(64, 87, 109, 14);
		contentPane.add(lblInstructorName);
		
		lblTopicOfClass = new JLabel("Topic of Class:");
		lblTopicOfClass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTopicOfClass.setBounds(64, 124, 109, 14);
		contentPane.add(lblTopicOfClass);
		
		lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDate.setBounds(64, 167, 46, 14);
		contentPane.add(lblDate);
		
		lblStartTime = new JLabel("Start Time:");
		lblStartTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStartTime.setBounds(64, 209, 83, 14);
		contentPane.add(lblStartTime);
		
		lblLocationOfClass = new JLabel("Location of Class:");
		lblLocationOfClass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLocationOfClass.setBounds(64, 250, 117, 14);
		contentPane.add(lblLocationOfClass);
	}
	
	
	//Returns a boolean indicating if any fields are not filled
	public boolean validation() {
		boolean flag = true;
		clearColors();
		
		//Add additional specific formatted text fields here
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
		
		return flag;
	}
	
	//Clears all the colors of the labels in the form
	public void clearColors(){
		lblInstructorName.setForeground(Color.BLACK);
		lblTopicOfClass.setForeground(Color.BLACK);
		lblDate.setForeground(Color.BLACK);
		lblStartTime.setForeground(Color.BLACK);
		lblLocationOfClass.setForeground(Color.BLACK);
	}
}