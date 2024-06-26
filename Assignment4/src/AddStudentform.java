import java.awt.EventQueue;


import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddStudentform {

    JFrame frame;
    private JTextField StudentName;
    private JTextField EnrollmentID;
    private JTextField Section;

   
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddStudentform window = new AddStudentform();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    
    public AddStudentform() {
        initialize();
    }

    
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(SystemColor.inactiveCaption);
        frame.getContentPane().setLayout(null);

        JLabel label1 = new JLabel("picture");
        ImageIcon img = new ImageIcon(this.getClass().getResource("/bahria-university-logo (1).jpg"));
        label1.setIcon(img);
        label1.setBackground(Color.LIGHT_GRAY);
        label1.setBounds(164, 11, 239, 206);
        frame.getContentPane().add(label1);

        JLabel lblAddStudent = new JLabel("Add Student");
        lblAddStudent.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lblAddStudent.setBounds(213, 228, 139, 42);
        frame.getContentPane().add(lblAddStudent);

        JLabel lblEnterName = new JLabel("Enter Name :");
        lblEnterName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblEnterName.setBounds(10, 274, 97, 25);
        frame.getContentPane().add(lblEnterName);

        JLabel lblEnterEnrollmentId = new JLabel("Enter Enrollment ID : :");
        lblEnterEnrollmentId.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblEnterEnrollmentId.setBounds(10, 334, 128, 25);
        frame.getContentPane().add(lblEnterEnrollmentId);

        JLabel lblEnterSection = new JLabel("Enter Section :");
        lblEnterSection.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblEnterSection.setBounds(10, 387, 97, 25);
        frame.getContentPane().add(lblEnterSection);

        StudentName = new JTextField();
        StudentName.setColumns(10);
        StudentName.setBounds(10, 298, 521, 25);
        frame.getContentPane().add(StudentName);

        EnrollmentID = new JTextField();
        EnrollmentID.setColumns(10);
        EnrollmentID.setBounds(10, 355, 521, 25);
        frame.getContentPane().add(EnrollmentID);

        Section = new JTextField();
        Section.setColumns(10);
        Section.setBounds(10, 411, 521, 25);
        frame.getContentPane().add(Section);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnClose.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        btnClose.setBounds(399, 447, 132, 32);
        frame.getContentPane().add(btnClose);

        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StudentName.setText("");
                EnrollmentID.setText("");
                Section.setText("");
            }
        });
        btnClear.setBounds(206, 447, 146, 32);
        frame.getContentPane().add(btnClear);

        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        btnAdd.setBounds(10, 447, 146, 32);
        frame.getContentPane().add(btnAdd);
        frame.setBounds(100, 100, 585, 538);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addStudent() {
        String name = StudentName.getText();
        String enrollmentID = EnrollmentID.getText();
        String section = Section.getText();

        if (name.isEmpty() || enrollmentID.isEmpty() || section.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment4", "root", "12345678");
            String sql = "INSERT INTO add_student (EnrollmentID, Name, Section) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, enrollmentID);
            pstmt.setString(2, name);
            pstmt.setString(3, section);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(frame, "Student added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            StudentName.setText("");
            EnrollmentID.setText("");
            Section.setText("");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error adding student to the database", "Error", JOptionPane.ERROR_MESSAGE);
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
