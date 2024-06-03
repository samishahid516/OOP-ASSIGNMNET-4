import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class TeacherLogin {

    public JFrame frame;
    private JTextField EnterApplicationID;
    private Connection connection;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TeacherLogin window = new TeacherLogin();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TeacherLogin() {
        initialize();
        connectToDatabase();
    }

    private void connectToDatabase() {
        try {
           
            String url = "jdbc:mysql://localhost:3306/assignment4";
            String user = "root";
            String password = "12345678";

            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Database connection failed: " + e.getMessage());
        }
    }

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(SystemColor.inactiveCaption);
        frame.setBounds(100, 100, 486, 458);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel label1 = new JLabel("picture");
        ImageIcon img = new ImageIcon(this.getClass().getResource("/bahria-university-logo (1).jpg"));
        label1.setIcon(img);
        label1.setBackground(Color.LIGHT_GRAY);
        label1.setBounds(114, 11, 239, 206);
        frame.getContentPane().add(label1);

        JLabel lblApplicationId = new JLabel("Application ID");
        lblApplicationId.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblApplicationId.setBounds(10, 262, 97, 25);
        frame.getContentPane().add(lblApplicationId);

        EnterApplicationID = new JTextField();
        EnterApplicationID.setColumns(10);
        EnterApplicationID.setBounds(10, 298, 433, 25);
        frame.getContentPane().add(EnterApplicationID);

        JLabel lblTeacherLogin = new JLabel("Teacher Login");
        lblTeacherLogin.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lblTeacherLogin.setBounds(140, 215, 165, 42);
        frame.getContentPane().add(lblTeacherLogin);

        JButton btnView = new JButton("View");
        btnView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String applicationId = EnterApplicationID.getText();
                if (validateApplicationId(applicationId)) {
                	
                    TeacherTimetable teacherTimetable = new TeacherTimetable(applicationId);
                    frame.setVisible(false);
                    teacherTimetable.frame.setVisible(true); 
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid Application ID. Please try again.");
                }
            }
        });
        
        btnView.setBounds(10, 372, 117, 32);
        frame.getContentPane().add(btnView);

        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EnterApplicationID.setText("");
            }
        });
        btnClear.setBounds(176, 372, 117, 32);
        frame.getContentPane().add(btnClear);

        JButton bntClose = new JButton("Close");
        bntClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        bntClose.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        bntClose.setBounds(331, 372, 112, 32);
        frame.getContentPane().add(bntClose);
    }

    private boolean validateApplicationId(String applicationId) {
        boolean isValid = false;
        try {
        	
            String query = "SELECT * FROM add_teacher WHERE ApplicationID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, applicationId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                isValid = true; 
            }

            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error validating Application ID: " + e.getMessage());
        }

        return isValid;
    }
}
