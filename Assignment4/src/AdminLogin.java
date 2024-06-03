import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class AdminLogin {

    JFrame frame;
    private JTextField AdminName;
    private JTextField AdminApplicationID;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdminLogin window = new AdminLogin();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

   
    public AdminLogin() {
        initialize();
    }

   
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(SystemColor.inactiveCaption);
        frame.setBounds(100, 100, 585, 475);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblAdminLogin = new JLabel("Admin Login");
        lblAdminLogin.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lblAdminLogin.setBounds(223, 221, 146, 42);
        frame.getContentPane().add(lblAdminLogin);

        JLabel lblEnterName = new JLabel("Enter Name :");
        lblEnterName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblEnterName.setBounds(22, 253, 97, 25);
        frame.getContentPane().add(lblEnterName);

        AdminName = new JTextField();
        AdminName.setColumns(10);
        AdminName.setBounds(22, 274, 521, 25);
        frame.getContentPane().add(AdminName);

        JLabel lblEnterApplicationId = new JLabel("Enter Application ID :");
        lblEnterApplicationId.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblEnterApplicationId.setBounds(22, 322, 132, 25);
        frame.getContentPane().add(lblEnterApplicationId);

        AdminApplicationID = new JTextField();
        AdminApplicationID.setColumns(10);
        AdminApplicationID.setBounds(22, 345, 521, 25);
        frame.getContentPane().add(AdminApplicationID);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verifyUserAndMoveToDashboard();
            }
        });

        btnLogin.setBounds(21, 391, 146, 32);
        frame.getContentPane().add(btnLogin);

        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdminName.setText(null);
                AdminApplicationID.setText(null);
            }
        });

        btnClear.setBounds(214, 391, 140, 32);
        frame.getContentPane().add(btnClear);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        btnClose.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        btnClose.setBounds(411, 391, 132, 32);
        frame.getContentPane().add(btnClose);

        JLabel label1 = new JLabel("picture");
        ImageIcon img = new ImageIcon(this.getClass().getResource("/bahria-university-logo (1).jpg"));
        label1.setIcon(img);
        label1.setBackground(Color.LIGHT_GRAY);
        label1.setBounds(178, 11, 239, 206);
        frame.getContentPane().add(label1);
    }

    protected static void setVisible(boolean b) {
        // Placeholder for setting visibility
    }

    private void verifyUserAndMoveToDashboard() {
        String applicationID = AdminApplicationID.getText();
        String name = AdminName.getText();

        if (applicationID.isEmpty() || name.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment4", "root", "12345678");

            String sql = "SELECT COUNT(*) FROM admin WHERE ApplicationID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, applicationID);
            rs = pstmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                
                AdminDashBoard adminDashboard = new AdminDashBoard();
                frame.setVisible(false);
                adminDashboard.frame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(frame, "Application ID does not exist. Please enter a valid ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error connecting to the database", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}


