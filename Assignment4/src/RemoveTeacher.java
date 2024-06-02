import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Color;
import java.awt.SystemColor;

public class RemoveTeacher {

    JFrame frame;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RemoveTeacher window = new RemoveTeacher();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public RemoveTeacher() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(SystemColor.inactiveCaption);
        frame.getContentPane().setLayout(null);

        JLabel label1 = new JLabel("picture");
        ImageIcon img = new ImageIcon(this.getClass().getResource("/bahria-university-logo (1).jpg"));
        label1.setIcon(img);
        label1.setBackground(Color.LIGHT_GRAY);
        label1.setBounds(138, 11, 239, 206);
        frame.getContentPane().add(label1);

        JLabel lblApplicationId = new JLabel("Enter Application ID to remove :");
        lblApplicationId.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblApplicationId.setBounds(10, 258, 197, 25);
        frame.getContentPane().add(lblApplicationId);

        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(10, 282, 433, 25);
        frame.getContentPane().add(textField);

        JButton btnNewButton = new JButton("Remove");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeTeacher(textField.getText());
            }
        });

        btnNewButton.setBounds(10, 337, 117, 32);
        frame.getContentPane().add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Clear");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 textField.setText(null);
        	}
        });
        btnNewButton_1.setBounds(171, 337, 117, 32);
        frame.getContentPane().add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("Close");
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        	
        });
        btnNewButton_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        btnNewButton_2.setBounds(331, 337, 112, 32);
        frame.getContentPane().add(btnNewButton_2);

        JLabel lblRemoveTeacher = new JLabel("Remove Teacher");
        lblRemoveTeacher.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lblRemoveTeacher.setBounds(162, 216, 184, 42);
        frame.getContentPane().add(lblRemoveTeacher);
        frame.setBounds(100, 100, 488, 433);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void removeTeacher(String applicationID) {
        Connection connection = null;
        Statement statement = null;

        try {
            // Establish connection to your database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment4", "root", "12345678");

            // Create SQL statement
            statement = connection.createStatement();

            // Execute SQL DELETE statement
            String sql = "DELETE FROM add_teacher WHERE ApplicationID = '" + applicationID + "'";
            int rowsAffected = statement.executeUpdate(sql);

            // Check if any rows were affected
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Teacher removed successfully.");
                textField.setText(""); // Clear the text field after successful removal
            } else {
                JOptionPane.showMessageDialog(null, "No  Teacher found with the given Application ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close statement and connection
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
