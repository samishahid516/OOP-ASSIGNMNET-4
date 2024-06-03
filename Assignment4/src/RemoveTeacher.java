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
    private JTextField EnterApplicationID;

    
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

    
    public RemoveTeacher() {
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
        label1.setBounds(138, 11, 239, 206);
        frame.getContentPane().add(label1);

        JLabel lblApplicationId = new JLabel("Enter Application ID to remove :");
        lblApplicationId.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblApplicationId.setBounds(10, 258, 197, 25);
        frame.getContentPane().add(lblApplicationId);

        EnterApplicationID = new JTextField();
        EnterApplicationID.setColumns(10);
        EnterApplicationID.setBounds(10, 282, 433, 25);
        frame.getContentPane().add(EnterApplicationID);

        JButton btnRemove = new JButton("Remove");
        btnRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeTeacher(EnterApplicationID.getText());
            }
        });

        btnRemove.setBounds(10, 318, 117, 32);
        frame.getContentPane().add(btnRemove);

        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 EnterApplicationID.setText(null);
        	}
        });
        btnClear.setBounds(171, 318, 117, 32);
        frame.getContentPane().add(btnClear);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        	
        });
        btnClose.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        btnClose.setBounds(331, 318, 112, 32);
        frame.getContentPane().add(btnClose);

        JLabel lblRemoveTeacher = new JLabel("Remove Teacher");
        lblRemoveTeacher.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lblRemoveTeacher.setBounds(162, 216, 184, 42);
        frame.getContentPane().add(lblRemoveTeacher);
        frame.setBounds(100, 100, 488, 405);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void removeTeacher(String applicationID) {
        Connection connection = null;
        Statement statement = null;

        try {
           
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment4", "root", "12345678");

            
            statement = connection.createStatement();

           
            String sql = "DELETE FROM add_teacher WHERE ApplicationID = '" + applicationID + "'";
            int rowsAffected = statement.executeUpdate(sql);

          
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Teacher removed successfully.");
                EnterApplicationID.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "No  Teacher found with the given Application ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
           
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
