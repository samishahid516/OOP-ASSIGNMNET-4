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
    private JTextField EnterEnrollmentID;

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

   
    public RemoveStudent() {
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
        label1.setBounds(122, 11, 239, 206);
        frame.getContentPane().add(label1);
        
        JLabel lblRemoveStudent = new JLabel("Remove Student");
        lblRemoveStudent.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lblRemoveStudent.setBounds(151, 213, 190, 42);
        frame.getContentPane().add(lblRemoveStudent);
        
        JLabel lblApplicationId = new JLabel("Enter EnrollmentID to remove :");
        lblApplicationId.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblApplicationId.setBounds(21, 260, 197, 25);
        frame.getContentPane().add(lblApplicationId);
        
        EnterEnrollmentID = new JTextField();
        EnterEnrollmentID.setColumns(10);
        EnterEnrollmentID.setBounds(21, 284, 433, 25);
        frame.getContentPane().add(EnterEnrollmentID);
        
        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnClose.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        btnClose.setBounds(342, 320, 112, 32);
        frame.getContentPane().add(btnClose);
        
        JButton BtnClear = new JButton("Clear");
        BtnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EnterEnrollmentID.setText(null);
            }
        });
        BtnClear.setBounds(182, 320, 117, 32);
        frame.getContentPane().add(BtnClear);
        
        JButton btnRemove = new JButton("Remove");
        btnRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle remove functionality here
                String applicationID = EnterEnrollmentID.getText().trim();

                if (applicationID.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter Application ID.");
                    return;
                }

                try {
                   
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment4", "root", "12345678");

                    
                    String sql = "DELETE FROM add_student WHERE EnrollmentID = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, applicationID);

                   
                    int rowsAffected = statement.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Student removed successfully.");
                        EnterEnrollmentID.setText(""); 
                    } else {
                        JOptionPane.showMessageDialog(null, "No student found with the given Application ID.");
                    }

                 
                    statement.close();
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: Unable to remove student.");
                }
            }
        });
        
        btnRemove.setBounds(21, 320, 117, 32);
        frame.getContentPane().add(btnRemove);
        frame.setBounds(100, 100, 485, 399);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
