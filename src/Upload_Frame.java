package javaApplication;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class Upload_Frame extends JFrame {

	private JPanel contentPane;
	private JFrame Upload_Frame;
	private JTable outputTable;

	/**
	 * Create the frame.
	 */
	public Upload_Frame(String filePath) {
		Upload_Frame = this;
		setResizable(false);
     	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1161, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCpcaAttendance = new JLabel("PEP Attendance Sheet");
		lblCpcaAttendance.setBounds(405, 23, 330, 38);
		lblCpcaAttendance.setFont(new Font("Cambria", Font.PLAIN, 30));
		contentPane.add(lblCpcaAttendance);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1173, 22);
		contentPane.add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		
		mnFile.add(mntmSave);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: Ask if they want to save
				Upload_Frame.dispose();
			}
		});
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mnFile.add(mntmOpen);
		mnFile.add(mntmExit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 74, 1155, 182);
		contentPane.add(scrollPane);
		
		outputTable = new JTable();
		scrollPane.setViewportView(outputTable);
		DefaultTableModel defaultModel = new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null},
				},
				new String[] {
					"First", "Last", "Date", "Curriculum", "Topic", "Day", "Time", 
					"Location", "Language","Sex", "Race", "Age","New","18&Under", "Zipcode"
				}
			);
		defaultModel.setRowCount(0);
		outputTable.setModel(defaultModel);
		
		JButton btnUpload = new JButton("Upload");
		btnUpload.setBounds(1033, 294, 110, 38);
		contentPane.add(btnUpload);
	}
}
