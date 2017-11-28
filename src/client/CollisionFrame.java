package javaApplication;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

public class CollisionFrame extends JFrame {

    private static JPanel contentPane;
    private static CollisionFrame frame;
    private static JRadioButton newButton;
    private static int x=24, y=60, width=500, height=23;
    private final static ButtonGroup collisionButtonGroup = new ButtonGroup();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new CollisionFrame();
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
    public CollisionFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 580, 435);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(457, 378, 117, 29);
        contentPane.add(btnSubmit);

        JLabel lblTwoOrMore = new JLabel("Which person did you want to add?");
        lblTwoOrMore.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        lblTwoOrMore.setBounds(122, 6, 368, 35);
        contentPane.add(lblTwoOrMore);

        //instead of 5 do Array.length
        for(int i=0; i<5; i++) {
            createJRadioButton();
        }
    }

    public static void createJRadioButton() {
        newButton = new JRadioButton("FirstName LastName Race Sex Birthday");
        newButton.setBounds(x, y, width, height);
        contentPane.add(newButton);
        collisionButtonGroup.add(newButton);
        System.out.println(y);
        y+=30;
    }
}
