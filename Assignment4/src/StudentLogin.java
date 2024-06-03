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
    private JTextField EnterEnrollmentID;
    private Connection connection;

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

   
    public StudentLogin() {
        initialize();
        // Establish database connection
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment4", "root", "12345678");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(SystemColor.inactiveCaption);
        frame.setBounds(100, 100, 512, 438);
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

        EnterEnrollmentID = new JTextField();
        EnterEnrollmentID.setColumns(10);
        EnterEnrollmentID.setBounds(30, 298, 433, 25);
        frame.getContentPane().add(EnterEnrollmentID);

        JButton btnView = new JButton("View");
        btnView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String enrollmentID = EnterEnrollmentID.getText().trim(); 
           
                boolean enrollmentExists = checkEnrollmentID(enrollmentID);
                if (enrollmentExists) {
               
                    StudentTimetable studentTimetable = new StudentTimetable(enrollmentID);
                    frame.setVisible(false); 
                    studentTimetable.frame.setVisible(true); 
                } else {
                  
                    JOptionPane.showMessageDialog(frame, "Enrollment ID not found", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnView.setBounds(30, 353, 117, 32);
        frame.getContentPane().add(btnView);

        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EnterEnrollmentID.setText(null);
            }
        });

        btnClear.setBounds(196, 353, 117, 32);
        frame.getContentPane().add(btnClear);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });

        btnClose.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        btnClose.setBounds(351, 353, 112, 32);
        frame.getContentPane().add(btnClose);
    }


    private boolean checkEnrollmentID(String enrollmentID) {
        try {
            String query = "SELECT * FROM add_student WHERE EnrollmentID=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, enrollmentID);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); 
        } catch (SQLException e) {
            e.printStackTrace();
            return false; 
        }
    }
}
