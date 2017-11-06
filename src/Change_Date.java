package javaApplication;

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

public class Change_Date extends JFrame {

	private JPanel contentPane;
	private Change_Date frame;
	private JDatePickerImpl datePicker;

	/**
	 * Create the frame.
	 */
	public Change_Date(Upload_Frame uploadFrame) {
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
