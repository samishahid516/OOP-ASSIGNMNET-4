import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentLogin {

    JFrame frame;
    private JTextField textField;
    private Connection connection;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StudentLogin window = new StudentLogin();
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
    public StudentLogin() {
        initialize();
        // Establish database connection
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment4", "root", "12345678");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(SystemColor.inactiveCaption);
        frame.setBounds(100, 100, 512, 454);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblApplicationId = new JLabel("Enrollment ID");
        lblApplicationId.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblApplicationId.setBounds(30, 262, 97, 25);
        frame.getContentPane().add(lblApplicationId);

        JLabel label1 = new JLabel("picture");
        ImageIcon img = new ImageIcon(this.getClass().getResource("/bahria-university-logo (1).jpg"));
        label1.setIcon(img);
        label1.setBackground(Color.LIGHT_GRAY);
        label1.setBounds(134, 11, 239, 206);
        frame.getContentPane().add(label1);

        JLabel lblStudentLogin = new JLabel("Student Login");
        lblStudentLogin.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lblStudentLogin.setBounds(176, 219, 164, 42);
        frame.getContentPane().add(lblStudentLogin);

        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(30, 298, 433, 25);
        frame.getContentPane().add(textField);

        JButton btnNewButton = new JButton("View");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String enrollmentID = textField.getText().trim(); // Retrieve the enrollment ID from the text field
                // Query the database to check if the enrollment ID exists
                boolean enrollmentExists = checkEnrollmentID(enrollmentID);
                if (enrollmentExists) {
                    // If enrollment ID exists, show the form
                    StudentTimetable studentTimetable = new StudentTimetable(enrollmentID);
                    frame.setVisible(false); // Hide the current login frame
                    studentTimetable.frame.setVisible(true); // Show the student timetable form
                } else {
                    // If enrollment ID doesn't exist, show an error message
                    JOptionPane.showMessageDialog(frame, "Enrollment ID not found", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnNewButton.setBounds(30, 353, 117, 32);
        frame.getContentPane().add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Clear");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setText(null);
            }
        });

        btnNewButton_1.setBounds(196, 353, 117, 32);
        frame.getContentPane().add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("Close");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });

        btnNewButton_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        btnNewButton_2.setBounds(351, 353, 112, 32);
        frame.getContentPane().add(btnNewButton_2);
    }

    // Method to check if the enrollment ID exists in the database
    private boolean checkEnrollmentID(String enrollmentID) {
        try {
            String query = "SELECT * FROM add_student WHERE EnrollmentID=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, enrollmentID);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Returns true if enrollment ID exists, false otherwise
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Error occurred, return false
        }
    }
}
