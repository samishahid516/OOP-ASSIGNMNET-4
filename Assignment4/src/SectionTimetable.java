import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class SectionTimetable {

    public JFrame frame;
    private JTable sectionTable;
    private JTable timetableTable;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/assignment4";
    private static final String USER = "root";
    private static final String PASS = "12345678";

    public SectionTimetable(String SectionID) {
        initialize(SectionID);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SectionTimetable window = new SectionTimetable("2-A");
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initialize(String SectionID) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(SystemColor.inactiveCaption);
        frame.setBounds(100, 100, 758, 641);
        frame.getContentPane().setLayout(null);

        JLabel label1 = new JLabel("picture");
        ImageIcon img = new ImageIcon(this.getClass().getResource("/bahria-university-logo (1).jpg"));
        label1.setIcon(img);
        label1.setBounds(262, 11, 239, 206);
        frame.getContentPane().add(label1);

        JLabel lblSectionTimetable = new JLabel("Section Timetable");
        lblSectionTimetable.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lblSectionTimetable.setBounds(292, 219, 194, 42);
        frame.getContentPane().add(lblSectionTimetable);

        JLabel lblRoomID = new JLabel("Section ID :");
        lblRoomID.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblRoomID.setBounds(18, 253, 75, 25);
        frame.getContentPane().add(lblRoomID);

        sectionTable = new JTable();
        sectionTable.setBounds(103, 259, 145, 19);
        frame.getContentPane().add(sectionTable);

        fetchAndPopulateSections(SectionID);

        JButton btnClose = new JButton("Close");
        btnClose.setBounds(324, 545, 124, 33);
        frame.getContentPane().add(btnClose);

        JScrollPane TimeTable = new JScrollPane();
        TimeTable.setBounds(30, 319, 693, 206);
        frame.getContentPane().add(TimeTable);

        timetableTable = new JTable();
        TimeTable.setViewportView(timetableTable);

        fetchAndPopulateTimetable(SectionID);
    }

    private void fetchAndPopulateSections(String SectionID) {
        DefaultTableModel sectionTableModel = new DefaultTableModel(new Object[]{"Section ID"}, 0);

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmtSection = conn.prepareStatement("SELECT SectionID FROM sections WHERE SectionID = ?")) {

            stmtSection.setString(1, SectionID);
            ResultSet rsSection = stmtSection.executeQuery();

            while (rsSection.next()) {
                String roomID = rsSection.getString("SectionID");
                sectionTableModel.addRow(new Object[]{roomID});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sectionTable.setModel(sectionTableModel);
    }

    private void fetchAndPopulateTimetable(String SectionID) {
        DefaultTableModel timetableTableModel = new DefaultTableModel(new Object[]{"TeacherName", "CourseName", "DayOfWeek", "StartTime", "EndTime", "RoomID"}, 0);

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmtTimetable = conn.prepareStatement("SELECT TeacherName, CourseName, DayOfWeek, StartTime, EndTime,  RoomID FROM timetable WHERE SectionID = ?")) {

            stmtTimetable.setString(1, SectionID);
            ResultSet rsTimetable = stmtTimetable.executeQuery();

            while (rsTimetable.next()) {
                String teacherName = rsTimetable.getString("TeacherName");
                String courseName = rsTimetable.getString("CourseName");
                String dayOfWeek = rsTimetable.getString("DayOfWeek");
                String startTime = rsTimetable.getString("StartTime");
                String endTime = rsTimetable.getString("EndTime");
                String roomID = rsTimetable.getString("RoomID");
                timetableTableModel.addRow(new Object[]{teacherName, courseName, dayOfWeek, startTime, endTime, roomID});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        timetableTable.setModel(timetableTableModel);
    }
}
