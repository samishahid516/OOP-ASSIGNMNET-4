import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JScrollPane;

public class ManageTimeSlots {

    JFrame frame;
    private JTextField EnterDay;
    private JTextField EnterStartTime;
    private JTextField EnterTeacherName;
    private JTextField EnterEndTime;
    private JTextField EnterCourseName;
    private JTextField EnterTeacherID;
    private JTextField EnterRoom;
    private JTable timetableTable;
    private JTextField ModifySection;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/assignment4";
    private static final String USER = "root";
    private static final String PASS = "12345678"; 
    
    private JTextField textField_9;
    private JTextField EnterSectionID;
    private JTextField EnterStudentID;

    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ManageTimeSlots window = new ManageTimeSlots();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    
    public ManageTimeSlots() {
        initialize();
    }

   
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(SystemColor.inactiveCaption);
        frame.setBounds(100, 100, 1085, 704);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel label1 = new JLabel("picture");
        ImageIcon img = new ImageIcon(this.getClass().getResource("/bahria-university-logo (1).jpg"));
        label1.setIcon(img);
        label1.setBackground(Color.LIGHT_GRAY);
        label1.setBounds(66, 11, 239, 206);
        frame.getContentPane().add(label1);

        JLabel lblManageTimeSlots = new JLabel("Manage Time Slots");
        lblManageTimeSlots.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lblManageTimeSlots.setBounds(76, 214, 207, 42);
        frame.getContentPane().add(lblManageTimeSlots);

        JLabel lblEnterName = new JLabel("Enter Day :");
        lblEnterName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblEnterName.setBounds(27, 525, 97, 25);
        frame.getContentPane().add(lblEnterName);

        EnterDay = new JTextField();
        EnterDay.setColumns(10);
        EnterDay.setBounds(97, 526, 76, 25);
        frame.getContentPane().add(EnterDay);

        JLabel lblEnterStartTime = new JLabel("Enter Start Time :");
        lblEnterStartTime.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblEnterStartTime.setBounds(183, 525, 113, 25);
        frame.getContentPane().add(lblEnterStartTime);

        JLabel lblEnterName_2 = new JLabel("Enter Teacher Name :");
        lblEnterName_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblEnterName_2.setBounds(27, 568, 146, 25);
        frame.getContentPane().add(lblEnterName_2);

        JLabel lblEnterName_3 = new JLabel("Enter End Time :");
        lblEnterName_3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblEnterName_3.setBounds(364, 525, 97, 25);
        frame.getContentPane().add(lblEnterName_3);

        JLabel lblEnterName_4 = new JLabel("Enter Course Name :");
        lblEnterName_4.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblEnterName_4.setBounds(546, 525, 117, 25);
        frame.getContentPane().add(lblEnterName_4);

        EnterStartTime = new JTextField();
        EnterStartTime.setColumns(10);
        EnterStartTime.setBounds(290, 526, 64, 25);
        frame.getContentPane().add(EnterStartTime);

        EnterTeacherName = new JTextField();
        EnterTeacherName.setColumns(10);
        EnterTeacherName.setBounds(166, 569, 134, 25);
        frame.getContentPane().add(EnterTeacherName);

        EnterEndTime = new JTextField();
        EnterEndTime.setColumns(10);
        EnterEndTime.setBounds(471, 526, 65, 25);
        frame.getContentPane().add(EnterEndTime);

        EnterCourseName = new JTextField();
        EnterCourseName.setColumns(10);
        EnterCourseName.setBounds(673, 526, 179, 25);
        frame.getContentPane().add(EnterCourseName);

        JLabel lblEnterName_3_1 = new JLabel("Enter Teacher ID :");
        lblEnterName_3_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblEnterName_3_1.setBounds(322, 568, 113, 25);
        frame.getContentPane().add(lblEnterName_3_1);

        JLabel lblEnterName_3_2 = new JLabel("Enter Room :");
        lblEnterName_3_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblEnterName_3_2.setBounds(556, 568, 87, 25);
        frame.getContentPane().add(lblEnterName_3_2);

        EnterTeacherID = new JTextField();
        EnterTeacherID.setColumns(10);
        EnterTeacherID.setBounds(439, 569, 97, 25);
        frame.getContentPane().add(EnterTeacherID);

        EnterRoom = new JTextField();
        EnterRoom.setColumns(10);
        EnterRoom.setBounds(636, 569, 87, 25);
        frame.getContentPane().add(EnterRoom);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnClose.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        btnClose.setBounds(862, 607, 176, 32);
        frame.getContentPane().add(btnClose);

        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        btnClear.setBounds(586, 607, 179, 32);
        frame.getContentPane().add(btnClear);

        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		addRow();
        	}
        });
        
        
        btnAdd.setBounds(290, 607, 184, 32);
        frame.getContentPane().add(btnAdd);

        JButton btnRemove = new JButton("Remove");
        btnRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeRow();
            }
        });

        btnRemove.setBounds(27, 607, 179, 32);
        frame.getContentPane().add(btnRemove);

        JButton btnEdit = new JButton("Edit");
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editRow();
            }
        });

        btnEdit.setBounds(389, 465, 170, 32);
        frame.getContentPane().add(btnEdit);

        JLabel lblTimeslot = new JLabel("TimeSlot :");
        lblTimeslot.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lblTimeslot.setBounds(27, 489, 97, 25);
        frame.getContentPane().add(lblTimeslot);

        JLabel lblTimeslot_1 = new JLabel("Enter Section For Which You Want To Modify TimeSlots :");
        lblTimeslot_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lblTimeslot_1.setBounds(337, 82, 401, 25);
        frame.getContentPane().add(lblTimeslot_1);

        ModifySection = new JTextField();
        ModifySection.setBounds(337, 120, 146, 25);
        frame.getContentPane().add(ModifySection);
        ModifySection.setColumns(10);

        JButton btnShowTimeSlots = new JButton("Show TimeSlots");
        btnShowTimeSlots.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sectionID = ModifySection.getText().trim(); 

                if (!sectionID.isEmpty()) {
                    fetchAndPopulateTimetable(sectionID); 
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter a Section Id", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnShowTimeSlots.setBounds(493, 120, 146, 25);
        frame.getContentPane().add(btnShowTimeSlots);

        JScrollPane Table = new JScrollPane();
        Table.setBounds(29, 254, 940, 200);
        frame.getContentPane().add(Table);

        timetableTable = new JTable();
        Table.setViewportView(timetableTable);
        
        JLabel lblEnterName_3_1_1 = new JLabel("Enter Section ID :");
        lblEnterName_3_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblEnterName_3_1_1.setBounds(862, 525, 113, 25);
        frame.getContentPane().add(lblEnterName_3_1_1);
        
        EnterSectionID = new JTextField();
        EnterSectionID.setColumns(10);
        EnterSectionID.setBounds(973, 526, 70, 25);
        frame.getContentPane().add(EnterSectionID);
        
        JLabel lblEnterName_3_1_1_1 = new JLabel("Enter Student ID :");
        lblEnterName_3_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblEnterName_3_1_1_1.setBounds(739, 568, 113, 25);
        frame.getContentPane().add(lblEnterName_3_1_1_1);
        
        EnterStudentID = new JTextField();
        EnterStudentID.setColumns(10);
        EnterStudentID.setBounds(864, 568, 179, 25);
        frame.getContentPane().add(EnterStudentID);
    
    }
    private void addRow() {
     
        String dayOfWeek = EnterDay.getText();
        String startTime = EnterStartTime.getText();
        String teacherName = EnterTeacherName.getText();
        String endTime = EnterEndTime.getText();
        String courseName = EnterCourseName.getText();
        String teacherID = EnterTeacherID.getText();
        String roomID = EnterRoom.getText();
        String studentID = EnterStudentID.getText();
        String sectionID = EnterSectionID.getText();

        
        DefaultTableModel model = (DefaultTableModel) timetableTable.getModel();
        model.addRow(new Object[]{teacherName, courseName, dayOfWeek, startTime, endTime, roomID});

 
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "INSERT INTO timetable (DayOfWeek, StartTime, TeacherName, EndTime, CourseName, TeacherID, RoomID, StudentID, SectionID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, dayOfWeek);
            statement.setString(2, startTime);
            statement.setString(3, teacherName);
            statement.setString(4, endTime);
            statement.setString(5, courseName);
            statement.setString(6, teacherID);
            statement.setString(7, roomID);
            statement.setString(8, studentID);
            statement.setString(9, sectionID); 

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(frame, "A new row has been inserted into the timetable table.", "Success", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            ex.printStackTrace();
        }
    }

    private void fetchAndPopulateTimetable(String sectionID) {
        if (!sectionID.isEmpty()) {
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
          
                String sqlVerify = "SELECT * FROM sections WHERE SectionID = ?";
                try (PreparedStatement stmtVerify = conn.prepareStatement(sqlVerify)) {
                    stmtVerify.setString(1, sectionID);
                    ResultSet rsVerify = stmtVerify.executeQuery();

                    if (rsVerify.next()) {
                       
                        DefaultTableModel timetableTableModel = new DefaultTableModel(
                            new Object[]{"Teacher Name", "Course Name", "Day Of Week", "Start Time", "End Time", "Room ID"}, 0
                        );

                        String sqlTimetable = "SELECT TeacherName, CourseName, DayOfWeek, StartTime, EndTime, RoomID FROM timetable WHERE SectionID = ?";
                        try (PreparedStatement stmtTimetable = conn.prepareStatement(sqlTimetable)) {
                            stmtTimetable.setString(1, sectionID);
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
                        }

                        timetableTable.setModel(timetableTableModel);
                        timetableTable.getColumnModel().getColumn(0).setPreferredWidth(100);
                        timetableTable.getColumnModel().getColumn(1).setPreferredWidth(150);

                       
                    } else {
                        // Invalid SectionID
                        JOptionPane.showMessageDialog(frame, "Invalid Section ID", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please enter a Section ID", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void clearFields() {
        EnterDay.setText("");
        EnterStartTime.setText("");
        EnterTeacherName.setText("");
        EnterEndTime.setText("");
        EnterCourseName.setText("");
        EnterTeacherID.setText("");
        EnterRoom.setText("");
        ModifySection.setText("");
        EnterStudentID.setText("");
        EnterSectionID.setText("");
        
    }

    private void removeRow() {
        DefaultTableModel model = (DefaultTableModel) timetableTable.getModel();
        int selectedRow = timetableTable.getSelectedRow();
        if (selectedRow != -1) {
            String teacherName = (String) model.getValueAt(selectedRow, 0);
            String courseName = (String) model.getValueAt(selectedRow, 1);
            String dayOfWeek = (String) model.getValueAt(selectedRow, 2);
            String startTime = (String) model.getValueAt(selectedRow, 3);
            String endTime = (String) model.getValueAt(selectedRow, 4);
            String roomID = (String) model.getValueAt(selectedRow, 5);
            model.removeRow(selectedRow);
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
                String sql = "DELETE FROM timetable WHERE TeacherName=? AND CourseName=? AND DayOfWeek=? AND StartTime=? AND EndTime=? AND RoomID=?";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, teacherName);
                statement.setString(2, courseName);
                statement.setString(3, dayOfWeek);
                statement.setString(4, startTime);
                statement.setString(5, endTime);
                statement.setString(6, roomID);
                int rowsDeleted = statement.executeUpdate();
                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(frame, "Row deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void editRow() {
        DefaultTableModel model = (DefaultTableModel) timetableTable.getModel();
        int selectedRow = timetableTable.getSelectedRow();
        if (selectedRow != -1) {
   
        	EnterDay.setText(model.getValueAt(selectedRow, 2).toString()); 
            EnterStartTime.setText(model.getValueAt(selectedRow, 3).toString()); 
            EnterTeacherName.setText(model.getValueAt(selectedRow, 0).toString()); 
            EnterEndTime.setText(model.getValueAt(selectedRow, 4).toString()); 
            EnterCourseName.setText(model.getValueAt(selectedRow, 1).toString()); 
            EnterRoom.setText(model.getValueAt(selectedRow, 5).toString()); 
        }
    }
}
