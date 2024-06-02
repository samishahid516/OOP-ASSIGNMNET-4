import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveStudent {

    JFrame frame;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RemoveStudent window = new RemoveStudent();
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
    public RemoveStudent() {
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
        label1.setBounds(122, 11, 239, 206);
        frame.getContentPane().add(label1);
        
        JLabel lblRemoveStudent = new JLabel("Remove Student");
        lblRemoveStudent.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lblRemoveStudent.setBounds(151, 213, 190, 42);
        frame.getContentPane().add(lblRemoveStudent);
        
        JLabel lblApplicationId = new JLabel("Enter Application ID to remove :");
        lblApplicationId.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblApplicationId.setBounds(21, 260, 197, 25);
        frame.getContentPane().add(lblApplicationId);
        
        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(21, 284, 433, 25);
        frame.getContentPane().add(textField);
        
        JButton btnNewButton_2 = new JButton("Close");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnNewButton_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        btnNewButton_2.setBounds(349, 339, 112, 32);
        frame.getContentPane().add(btnNewButton_2);
        
        JButton btnNewButton_1 = new JButton("Clear");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setText(null);
            }
        });
        btnNewButton_1.setBounds(182, 339, 117, 32);
        frame.getContentPane().add(btnNewButton_1);
        
        JButton btnNewButton = new JButton("Remove");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle remove functionality here
                String applicationID = textField.getText().trim();

                if (applicationID.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter Application ID.");
                    return;
                }

                try {
                    // Establish connection to your database
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment4", "root", "12345678");

                    // Prepare SQL statement to delete the student from the database
                    String sql = "DELETE FROM add_student WHERE EnrollmentID = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, applicationID);

                    // Execute the statement
                    int rowsAffected = statement.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Student removed successfully.");
                        textField.setText(""); // Clear the text field after successful removal
                    } else {
                        JOptionPane.showMessageDialog(null, "No student found with the given Application ID.");
                    }

                    // Close the statement and connection
                    statement.close();
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: Unable to remove student.");
                }
            }
        });
        
        btnNewButton.setBounds(21, 339, 117, 32);
        frame.getContentPane().add(btnNewButton);
        frame.setBounds(100, 100, 485, 419);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
