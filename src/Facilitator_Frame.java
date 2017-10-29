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
//	private JFormattedTextField dateFTF;
	private JComboBox startTimeComboBox;
	private JComboBox locationComboBox;
	private JDatePickerImpl datePicker;
	
	
	MaskFormatter date = createFormatter("##/##/####");
	
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

	public MaskFormatter createFormatter(String s) {
	     MaskFormatter formatter = null;
	     try {
	          formatter = new MaskFormatter(s);
	         } 
	     catch (java.text.ParseException exc) {
		          System.err.println("formatter is bad: " + exc.getMessage());
		          System.exit(-1);
		      }
	      return formatter;
	}
	/**
	 * Create the frame.
	 */
	public Facilitator_Frame() {
		
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
		
		instructorNameComboBox.setModel(new DefaultComboBoxModel(new String[] {"Instructor Name", "Carlie", "Sami"}));
		instructorNameComboBox.setBounds(182, 81, 211, 27);
		contentPane.add(instructorNameComboBox);
		
		topicComboBox.setModel(new DefaultComboBoxModel(new String[] {"Topic of Class", "Happy", "Healthy"}));
		topicComboBox.setBounds(182, 120, 211, 27);
		contentPane.add(topicComboBox);
		
		//New Date here
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(182, 167, 211, 25);
		contentPane.add(datePicker);
	
		startTimeComboBox.setModel(new DefaultComboBoxModel(new String[] {"Start Time", "11", "12"}));
		startTimeComboBox.setBounds(182, 205, 211, 27);
		contentPane.add(startTimeComboBox);
		
		
		locationComboBox.setModel(new DefaultComboBoxModel(new String[] {"Location of Class", "Foxrun", "Poughkeepsie"}));
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
					String instructorName = (String) instructorNameComboBox.getSelectedItem();
					String topic = (String)topicComboBox.getSelectedItem();
//					String currentDate = dateFTF.getText();
					String startTime = (String)startTimeComboBox.getSelectedItem();
					String location = (String)locationComboBox.getSelectedItem();
					//attendance_frame = new Attendance_Frame(instructorName, topic, currentDate, startTime, location);
					//attendance_frame.setVisible(true);
					facilitator_frame.dispose();
				}
			}
		});

		btnProceed.setBounds(447, 360, 117, 29);
		contentPane.add(btnProceed);
	}
	
	public boolean validation() {
		boolean flag = true;
		
		//Add additional specific formatted text fields here
		if(instructorNameComboBox.getSelectedItem().equals("Instructor Name")){
			instructorNameComboBox.setForeground(Color.RED);
			flag = false;
		}
		
		if(topicComboBox.getSelectedItem().equals("Topic of Class")){
			topicComboBox.setForeground(Color.RED);
			flag = false;
		}
		
		if(datePicker.getModel().getValue() == null){
			datePicker.setForeground(Color.RED);
			flag = false;
		}
		
		if(startTimeComboBox.getSelectedItem().equals("Start Time")){
			startTimeComboBox.setForeground(Color.RED);
			flag = false;
		}
		
		if(locationComboBox.getSelectedItem().equals("Location of Class")){
			locationComboBox.setForeground(Color.RED);
			flag = false;
		}
		
		return flag;
	}
	
	
}