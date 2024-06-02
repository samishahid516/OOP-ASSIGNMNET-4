import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JOptionPane;

public class AddTeacher {

    JFrame frame;
    private JTextField textField;
    private JTextField textField_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddTeacher window = new AddTeacher();
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
    public AddTeacher() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(SystemColor.inactiveCaption);
        frame.setBounds(100, 100, 600, 520);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel label1 = new JLabel("picture");
        ImageIcon img = new ImageIcon(this.getClass().getResource("/bahria-university-logo (1).jpg"));
        label1.setIcon(img);
        label1.setBackground(Color.LIGHT_GRAY);
        label1.setBounds(183, 11, 239, 206);
        frame.getContentPane().add(label1);

        JLabel lblAddTeacher = new JLabel("Add Teacher");
        lblAddTeacher.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lblAddTeacher.setBounds(224, 228, 140, 42);
        frame.getContentPane().add(lblAddTeacher);

        JLabel lblEnterApplicationId = new JLabel("Enter Application ID :");
        lblEnterApplicationId.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblEnterApplicationId.setBounds(10, 340, 132, 25);
        frame.getContentPane().add(lblEnterApplicationId);

        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(10, 363, 521, 25);
        frame.getContentPane().add(textField);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(10, 292, 521, 25);
        frame.getContentPane().add(textField_1);

        JLabel lblEnterName = new JLabel("Enter Name :");
        lblEnterName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblEnterName.setBounds(10, 271, 97, 25);
        frame.getContentPane().add(lblEnterName);

        JButton btnNewButton_2 = new JButton("Close");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        btnNewButton_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        btnNewButton_2.setBounds(397, 428, 132, 32);
        frame.getContentPane().add(btnNewButton_2);

        JButton btnNewButton_1 = new JButton("Clear");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField_1.setText("");
                textField.setText("");
            }
        });
        btnNewButton_1.setBounds(201, 428, 140, 32);
        frame.getContentPane().add(btnNewButton_1);

        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTeacher();
            }
        });

        btnAdd.setBounds(10, 428, 146, 32);
        frame.getContentPane().add(btnAdd);
    }

    private void addTeacher() {
        String name = textField_1.getText();
        String applicationID = textField.getText();

        if (name.isEmpty() || applicationID.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Establish connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment4", "root", "12345678");

            // Insert teacher into the database
            String sql = "INSERT INTO add_teacher (ApplicationID, Name) VALUES (?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, applicationID);
            pstmt.setString(2, name);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(frame, "Teacher added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            textField_1.setText("");
            textField.setText("");

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error adding teacher to the database", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
