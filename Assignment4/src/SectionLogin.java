import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class SectionLogin {

    JFrame frame;
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/assignment4";
    private static final String USER = "root";
    private static final String PASS = "12345678";
    private JTextField EnterRoom;

   
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SectionLogin window = new SectionLogin();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    
    public SectionLogin() {
        initialize();
    }

    
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(SystemColor.inactiveCaption);
        frame.setBounds(100, 100, 482, 431);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel label1 = new JLabel("picture");
        ImageIcon img = new ImageIcon(this.getClass().getResource("/bahria-university-logo (1).jpg"));
        label1.setIcon(img);
        label1.setBackground(Color.LIGHT_GRAY);
        label1.setBounds(123, 11, 239, 206);
        frame.getContentPane().add(label1);

        JLabel lblSectionLogin = new JLabel("Section Login");
        lblSectionLogin.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lblSectionLogin.setBounds(174, 218, 153, 42);
        frame.getContentPane().add(lblSectionLogin);

        JLabel lblEnterRoomababa = new JLabel("Enter Room (2-A,2-B,4-A,4-B,8-A)");
        lblEnterRoomababa.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblEnterRoomababa.setBounds(10, 261, 202, 25);
        frame.getContentPane().add(lblEnterRoomababa);

        EnterRoom = new JTextField();
        EnterRoom.setColumns(10);
        EnterRoom.setBounds(10, 287, 433, 25);
        frame.getContentPane().add(EnterRoom);

        JButton btnClose = new JButton("Close");
        btnClose.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        btnClose.setBounds(331, 342, 112, 32);
        frame.getContentPane().add(btnClose);

        JButton btnClear = new JButton("Clear");
        btnClear.setBounds(166, 342, 117, 32);
        frame.getContentPane().add(btnClear);

        JButton btnView = new JButton("View");
        btnView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                
                String SectionId = EnterRoom.getText().trim();

                
                if (!SectionId.isEmpty()) {
                    try {
                      
                        Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);

                        
                        String sql = "SELECT * FROM sections WHERE SectionID = ?";
                        PreparedStatement statement = connection.prepareStatement(sql);
                        statement.setString(1, SectionId);

                        
                        ResultSet resultSet = statement.executeQuery();

                        
                        if (resultSet.next()) {
                            
                            SectionTimetable sectionTimetable = new SectionTimetable(SectionId);

                            frame.setVisible(false);
                            sectionTimetable.frame.setVisible(true);
                        } else {
                           
                            JOptionPane.showMessageDialog(frame, "Invalid Section Id", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                       
                        resultSet.close();
                        statement.close();
                        connection.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace(); 
                    }
                } else {
                    
                    JOptionPane.showMessageDialog(frame, "Please enter a Section Id", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnView.setBounds(10, 342, 117, 32);
        frame.getContentPane().add(btnView);
    }
}
