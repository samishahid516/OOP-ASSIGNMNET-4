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
    private JTextField ApplicationID;
    private JTextField TeacherName;

   
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

    
    public AddTeacher() {
        initialize();
    }

    
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(SystemColor.inactiveCaption);
        frame.setBounds(100, 100, 600, 496);
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

        ApplicationID = new JTextField();
        ApplicationID.setColumns(10);
        ApplicationID.setBounds(10, 363, 521, 25);
        frame.getContentPane().add(ApplicationID);

        TeacherName = new JTextField();
        TeacherName.setColumns(10);
        TeacherName.setBounds(10, 292, 521, 25);
        frame.getContentPane().add(TeacherName);

        JLabel lblEnterName = new JLabel("Enter Name :");
        lblEnterName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblEnterName.setBounds(10, 271, 97, 25);
        frame.getContentPane().add(lblEnterName);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        btnClose.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        btnClose.setBounds(399, 409, 132, 32);
        frame.getContentPane().add(btnClose);

        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TeacherName.setText("");
                ApplicationID.setText("");
            }
        });
        btnClear.setBounds(201, 409, 140, 32);
        frame.getContentPane().add(btnClear);

        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTeacher();
            }
        });

        btnAdd.setBounds(10, 409, 146, 32);
        frame.getContentPane().add(btnAdd);
    }

    private void addTeacher() {
        String name = TeacherName.getText();
        String applicationID = ApplicationID.getText();

        if (name.isEmpty() || applicationID.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment4", "root", "12345678");

            
            String sql = "INSERT INTO add_teacher (ApplicationID, Name) VALUES (?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, applicationID);
            pstmt.setString(2, name);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(frame, "Teacher added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            TeacherName.setText("");
            ApplicationID.setText("");

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
