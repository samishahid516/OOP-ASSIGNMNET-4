import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JLabel;
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

public class StudentTimetable {

    JFrame frame;
    private JTable NameTable;
    private JTable EnrollmentTable;
    private JTable SectionTable;
    private JTable table;

   
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
               
                    StudentTimetable window = new StudentTimetable("Your_Enrollment_ID_Here");
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

   
    public StudentTimetable(String enrollmentID) {
        initialize(enrollmentID);
    }

   
    private void initialize(String enrollmentID) {
        frame = new JFrame();
        frame.getContentPane().setBackground(SystemColor.inactiveCaption);
        frame.setBounds(100, 100, 871, 664);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel label1 = new JLabel("picture");
        ImageIcon img = new ImageIcon(this.getClass().getResource("/bahria-university-logo (1).jpg"));
        label1.setIcon(img);
        label1.setBackground(SystemColor.lightGray);
        label1.setBounds(316, 11, 239, 206);
        frame.getContentPane().add(label1);

        JLabel lblStudentTimetable = new JLabel("Student Timetable");
        lblStudentTimetable.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lblStudentTimetable.setBounds(342, 217, 194, 42);
        frame.getContentPane().add(lblStudentTimetable);

        JLabel lblStudentName = new JLabel("Student Name :");
        lblStudentName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblStudentName.setBounds(22, 264, 97, 25);
        frame.getContentPane().add(lblStudentName);

        JLabel lblStudentEnrollmentId = new JLabel(" Enrollment ID :");
        lblStudentEnrollmentId.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblStudentEnrollmentId.setBounds(22, 296, 127, 25);
        frame.getContentPane().add(lblStudentEnrollmentId);

        JLabel lblStudentSection = new JLabel("Section :");
        lblStudentSection.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblStudentSection.setBounds(22, 327, 97, 25);
        frame.getContentPane().add(lblStudentSection);

        JButton btnClose = new JButton("Close");
        btnClose.setBounds(356, 585, 124, 33);
        frame.getContentPane().add(btnClose);

        NameTable = new JTable();
        NameTable.setBounds(133, 264, 162, 19);
        frame.getContentPane().add(NameTable);

        EnrollmentTable = new JTable();
        EnrollmentTable.setBounds(133, 296, 162, 19);
        frame.getContentPane().add(EnrollmentTable);

        SectionTable = new JTable();
        SectionTable.setBounds(133, 327, 162, 19);
        frame.getContentPane().add(SectionTable);
        
        JScrollPane TimeTable = new JScrollPane();
        TimeTable.setBounds(22, 363, 814, 211);
        frame.getContentPane().add(TimeTable);
        
        table = new JTable();
        TimeTable.setViewportView(table);

        
        loadData(enrollmentID);
    }

    private void loadData(String enrollmentID) {
        String url = "jdbc:mysql://localhost:3306/assignment4";
        String username = "root";
        String password = "12345678";
        String query = "SELECT Name, EnrollmentID, Section FROM add_student WHERE EnrollmentID = ?";
        String queryTimetable = "SELECT TeacherName, CourseName, DayOfWeek, StartTime, EndTime, SectionID, RoomID FROM timetable WHERE StudentID = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, enrollmentID);
            ResultSet rs = stmt.executeQuery();

            DefaultTableModel model1 = new DefaultTableModel(new Object[]{"Student Name"}, 0);
            DefaultTableModel model2 = new DefaultTableModel(new Object[]{"Enrollment ID"}, 0);
            DefaultTableModel model3 = new DefaultTableModel(new Object[]{"Section"}, 0);
            DefaultTableModel model = new DefaultTableModel(new Object[]{"TeacherName", "CourseName", "DayOfWeek", "StartTime", "EndTime", "RoomID", "SectionID"}, 0);

            if (rs.next()) {
                String studentName = rs.getString("Name");
                String enrollmentId = rs.getString("EnrollmentID");
                String section = rs.getString("Section");

                model1.addRow(new Object[]{studentName});
                model2.addRow(new Object[]{enrollmentId});
                model3.addRow(new Object[]{section});
            }

            NameTable.setModel(model1);
            EnrollmentTable.setModel(model2);
            SectionTable.setModel(model3);
            
            try (PreparedStatement stmtTimetable = conn.prepareStatement(queryTimetable)) {
                stmtTimetable.setString(1, enrollmentID);
                ResultSet rsTimetable = stmtTimetable.executeQuery();


                while (rsTimetable.next()) {
                    String teacherName = rsTimetable.getString("TeacherName");
                    String courseName = rsTimetable.getString("CourseName");
                    String dayOfWeek = rsTimetable.getString("DayOfWeek");
                    String startTime = rsTimetable.getString("StartTime");
                    String endTime = rsTimetable.getString("EndTime");
                    String roomID = rsTimetable.getString("RoomID");
                    String sectionID = rsTimetable.getString("SectionID");

                    
                    model.addRow(new Object[]{teacherName, courseName, dayOfWeek, startTime, endTime, roomID, sectionID});
                }
            }

           
            table.setModel(model);
            table.getColumnModel().getColumn(1).setPreferredWidth(180); 

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
