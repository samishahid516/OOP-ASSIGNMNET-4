import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class RoomLogin {

    JFrame frame;
    private JTextField EnterRoom;


    private static final String DB_URL = "jdbc:mysql://localhost:3306/assignment4";
    private static final String USER = "root";
    private static final String PASS = "12345678";

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RoomLogin window = new RoomLogin();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public RoomLogin() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(SystemColor.inactiveCaption);
        frame.setBounds(100, 100, 492, 410);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel label1 = new JLabel("picture");
        ImageIcon img = new ImageIcon(this.getClass().getResource("/bahria-university-logo (1).jpg"));
        label1.setIcon(img);
        label1.setBackground(SystemColor.LIGHT_GRAY);
        label1.setBounds(121, 11, 239, 206);
        frame.getContentPane().add(label1);

        JLabel lblEnterRoom = new JLabel("Enter Room (4-17,4-18,4-19)");
        lblEnterRoom.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblEnterRoom.setBounds(10, 254, 175, 25);
        frame.getContentPane().add(lblEnterRoom);

        JLabel lblRoomlogin = new JLabel("Room Login");
        lblRoomlogin.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lblRoomlogin.setBounds(176, 212, 148, 42);
        frame.getContentPane().add(lblRoomlogin);

        EnterRoom = new JTextField();
        EnterRoom.setColumns(10);
        EnterRoom.setBounds(10, 290, 433, 25);
        frame.getContentPane().add(EnterRoom);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnClose.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        btnClose.setBounds(331, 326, 112, 32);
        frame.getContentPane().add(btnClose);

        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EnterRoom.setText(null);
            }
        });
        btnClear.setBounds(166, 326, 117, 32);
        frame.getContentPane().add(btnClear);

        JButton btnView = new JButton("View");
        btnView.addActionListener(new ActionListener() {
        	
            public void actionPerformed(ActionEvent e) {
                
                String roomId = EnterRoom.getText().trim();

                if (!roomId.isEmpty()) {
                    try {
                       
                        Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);

                  
                        String sql = "SELECT * FROM rooms WHERE RoomID = ?";
                        PreparedStatement statement = connection.prepareStatement(sql);
                        statement.setString(1, roomId);

                        ResultSet resultSet = statement.executeQuery();

                  
                        if (resultSet.next()) {
                           
                        
                        	RoomTimetable roomTimetable = new RoomTimetable(roomId);

                            frame.setVisible(false);
                            roomTimetable.frame.setVisible(true);
                        } else {
                
                            JOptionPane.showMessageDialog(frame, "Invalid room ID", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        // Close database resources
                        resultSet.close();
                        statement.close();
                        connection.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace(); 
                    }
                } else {
                    
                    JOptionPane.showMessageDialog(frame, "Please enter a room ID", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnView.setBounds(10, 326, 117, 32);
        frame.getContentPane().add(btnView);
    }
}
