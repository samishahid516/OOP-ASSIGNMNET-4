import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherTimetable {

    JFrame frame;
    private JTable table_1;
    private JTable table_2;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Pass Application ID as a parameter
                    TeacherTimetable window = new TeacherTimetable("Your_Application_ID_Here");
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public TeacherTimetable(String applicationId) {
        initialize(applicationId);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize(String applicationId) {
        frame = new JFrame();
        frame.getContentPane().setBackground(SystemColor.inactiveCaption);
        frame.setBounds(100, 100, 766, 692);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel label1 = new JLabel("picture");
        ImageIcon img = new ImageIcon(this.getClass().getResource("/bahria-university-logo (1).jpg"));
        label1.setIcon(img);
        label1.setBackground(Color.LIGHT_GRAY);
        label1.setBounds(254, 11, 239, 206);
        frame.getContentPane().add(label1);

        JLabel lblStudentTimetable = new JLabel("Teacher Timetable");
        lblStudentTimetable.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lblStudentTimetable.setBounds(284, 219, 194, 42);
        frame.getContentPane().add(lblStudentTimetable);

        JLabel lblStudentName = new JLabel("Teacher Name :");
        lblStudentName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblStudentName.setBounds(22, 264, 97, 25);
        frame.getContentPane().add(lblStudentName);

        JLabel lblStudentEnrollmentId = new JLabel("Application ID :");
        lblStudentEnrollmentId.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblStudentEnrollmentId.setBounds(22, 296, 127, 25);
        frame.getContentPane().add(lblStudentEnrollmentId);

        JButton btnNewButton = new JButton("Close");
        btnNewButton.setBounds(316, 567, 124, 33);
        frame.getContentPane().add(btnNewButton);

        table_1 = new JTable();
        table_1.setBounds(131, 270, 145, 19);
        frame.getContentPane().add(table_1);

        table_2 = new JTable();
        table_2.setBounds(131, 301, 145, 19);
        frame.getContentPane().add(table_2);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(22, 338, 720, 206);
        frame.getContentPane().add(scrollPane);
        
        table = new JTable();
        scrollPane.setViewportView(table);

        // Load data based on provided Application ID
        loadData(applicationId);
    }

    private void loadData(String applicationId) {
        String url = "jdbc:mysql://localhost:3306/assignment4";
        String username = "root";
        String password = "12345678";
        String query = "SELECT Name, ApplicationID FROM add_teacher WHERE ApplicationID = ?";
        String queryTimetable = "SELECT CourseName, DayOfWeek, StartTime, EndTime, SectionID, RoomID FROM timetable WHERE TeacherID = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, applicationId);
            ResultSet rs = stmt.executeQuery();

            DefaultTableModel model1 = new DefaultTableModel(new Object[]{"Teacher Name"}, 0);
            DefaultTableModel model2 = new DefaultTableModel(new Object[]{"Application ID"}, 0);
            DefaultTableModel model = new DefaultTableModel(new Object[]{"CourseName", "DayOfWeek", "StartTime", "EndTime", "RoomID", "SectionID"}, 0);

            if (rs.next()) {
                String teacherName = rs.getString("Name");
                String applicationID = rs.getString("ApplicationID");

                model1.addRow(new Object[]{teacherName});
                model2.addRow(new Object[]{applicationID});
            }

            table_1.setModel(model1);
            table_2.setModel(model2);

            try (PreparedStatement stmtTimetable = conn.prepareStatement(queryTimetable)) {
                stmtTimetable.setString(1, applicationId);
                ResultSet rsTimetable = stmtTimetable.executeQuery();

                // Add timetable information to the table
                while (rsTimetable.next()) {
                    String courseName = rsTimetable.getString("CourseName");
                    String dayOfWeek = rsTimetable.getString("DayOfWeek");
                    String startTime = rsTimetable.getString("StartTime");
                    String endTime = rsTimetable.getString("EndTime");
                    String roomID = rsTimetable.getString("RoomID");
                    String sectionID = rsTimetable.getString("SectionID");

                    // Add the row to the table model
                    model.addRow(new Object[]{courseName, dayOfWeek, startTime, endTime, roomID, sectionID});
                }
            }

            // Set the model to the table
            table.setModel(model);
            table.getColumnModel().getColumn(0).setPreferredWidth(180);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}