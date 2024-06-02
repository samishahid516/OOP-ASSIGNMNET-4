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
    private JTextField textField;

    // Define SQL connection parameters
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
        frame.setBounds(100, 100, 492, 449);
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

        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(10, 290, 433, 25);
        frame.getContentPane().add(textField);

        JButton btnNewButton_2 = new JButton("Close");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnNewButton_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        btnNewButton_2.setBounds(331, 352, 112, 32);
        frame.getContentPane().add(btnNewButton_2);

        JButton btnNewButton_1 = new JButton("Clear");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setText(null);
            }
        });
        btnNewButton_1.setBounds(164, 352, 117, 32);
        frame.getContentPane().add(btnNewButton_1);

        JButton btnNewButton = new JButton("View");
        btnNewButton.addActionListener(new ActionListener() {
        	
            public void actionPerformed(ActionEvent e) {
                // Retrieve the room ID entered by the user
                String roomId = textField.getText().trim();

                // Check if the room ID is not empty
                if (!roomId.isEmpty()) {
                    try {
                        // Establish database connection
                        Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);

                        // Prepare SQL query with a parameterized statement
                        String sql = "SELECT * FROM rooms WHERE RoomID = ?";
                        PreparedStatement statement = connection.prepareStatement(sql);
                        statement.setString(1, roomId);

                        // Execute query and retrieve results
                        ResultSet resultSet = statement.executeQuery();

                        // Check if the result set has any rows (i.e., valid room ID)
                        if (resultSet.next()) {
                            // If room ID is valid, display the timetable
                        
                        	RoomTimetable roomTimetable = new RoomTimetable(roomId);

                            frame.setVisible(false);
                            roomTimetable.frame.setVisible(true);
                        } else {
                            // If room ID is invalid, display an error message
                            JOptionPane.showMessageDialog(frame, "Invalid room ID", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        // Close database resources
                        resultSet.close();
                        statement.close();
                        connection.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace(); // Handle database errors
                    }
                } else {
                    // If room ID is empty, prompt the user to enter a room ID
                    JOptionPane.showMessageDialog(frame, "Please enter a room ID", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnNewButton.setBounds(10, 352, 117, 32);
        frame.getContentPane().add(btnNewButton);
    }
}
