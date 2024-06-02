import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomTimetable {

    JFrame frame;
    private JTable roomTable;
    private JTable timetableTable;

    public RoomTimetable(String roomId) {
        initialize(roomId);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RoomTimetable window = new RoomTimetable("4-17");
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initialize(String roomId) {
        frame = new JFrame();
        frame.getContentPane().setBackground(SystemColor.inactiveCaption);
        frame.setBounds(100, 100, 747, 641);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel label1 = new JLabel("picture");
        ImageIcon img = new ImageIcon(this.getClass().getResource("/bahria-university-logo (1).jpg"));
        label1.setIcon(img);
        label1.setBackground(Color.LIGHT_GRAY);
        label1.setBounds(257, 11, 239, 206);
        frame.getContentPane().add(label1);

        JLabel lblStudentTimetable = new JLabel("Room Timetable");
        lblStudentTimetable.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lblStudentTimetable.setBounds(287, 219, 194, 42);
        frame.getContentPane().add(lblStudentTimetable);

        // Table for room information
        roomTable = new JTable();
        roomTable.setBounds(78, 270, 145, 19);
        frame.getContentPane().add(roomTable);

        JButton btnNewButton = new JButton("Close");
        btnNewButton.setBounds(319, 545, 124, 33);
        frame.getContentPane().add(btnNewButton);

        JLabel lblRoom = new JLabel("Room :");
        lblRoom.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblRoom.setBounds(25, 264, 52, 25);
        frame.getContentPane().add(lblRoom);

        // Table for timetable information
        timetableTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(timetableTable);
        scrollPane.setBounds(35, 300, 675, 216);
        frame.getContentPane().add(scrollPane);

        // Fetch and display room and timetable information
        displayRoomInfo(roomId);
        displayTimetable(roomId);
    }

    private void displayRoomInfo(String roomId) {
        DefaultTableModel roomTableModel = new DefaultTableModel(new Object[]{"Room ID"}, 0);
        // Fetch room information from the database and populate the table
        // Make sure to handle exceptions properly
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment4", "root", "12345678");
             PreparedStatement stmt = conn.prepareStatement("SELECT RoomID FROM rooms WHERE RoomID = ?")) {

            stmt.setString(1, roomId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String roomID = rs.getString("RoomID");
                roomTableModel.addRow(new Object[]{roomID});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        roomTable.setModel(roomTableModel);
    }

    private void displayTimetable(String roomId) {
        DefaultTableModel timetableTableModel = new DefaultTableModel(new Object[]{"TeacherName","CourseName", "DayOfWeek", "StartTime", "EndTime", "SectionID"}, 0);
        // Fetch timetable information from the database and populate the table
        // Make sure to handle exceptions properly
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment4", "root", "12345678");
             PreparedStatement stmtTimetable = conn.prepareStatement("SELECT TeacherName, CourseName, DayOfWeek, StartTime, EndTime,  SectionID FROM timetable WHERE RoomID = ?")) {

            stmtTimetable.setString(1, roomId);
            ResultSet rsTimetable = stmtTimetable.executeQuery();

            while (rsTimetable.next()) {
                String teacherName = rsTimetable.getString("TeacherName");
                String courseName = rsTimetable.getString("CourseName");
                String dayOfWeek = rsTimetable.getString("DayOfWeek");
                String startTime = rsTimetable.getString("StartTime");
                String endTime = rsTimetable.getString("EndTime");
                String sectionID = rsTimetable.getString("SectionID");
                timetableTableModel.addRow(new Object[]{teacherName, courseName, dayOfWeek, startTime, endTime, sectionID});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        timetableTable.setModel(timetableTableModel);
    }
}
