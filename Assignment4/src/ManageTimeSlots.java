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
    private JTextField textField;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_7;
    private JTable timetableTable;
    private JTextField textField_1;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/assignment4";
    private static final String USER = "root";
    private static final String PASS = "12345678"; 
    
    private JTextField textField_9;
    private JTextField textField_10;
    private JTextField textField_11;

    /**
     * Launch the application.
     */
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

    /**
     * Create the application.
     */
    public ManageTimeSlots() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
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

        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(97, 526, 76, 25);
        frame.getContentPane().add(textField);

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

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(290, 526, 64, 25);
        frame.getContentPane().add(textField_2);

        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(166, 569, 134, 25);
        frame.getContentPane().add(textField_3);

        textField_4 = new JTextField();
        textField_4.setColumns(10);
        textField_4.setBounds(471, 526, 65, 25);
        frame.getContentPane().add(textField_4);

        textField_5 = new JTextField();
        textField_5.setColumns(10);
        textField_5.setBounds(673, 526, 179, 25);
        frame.getContentPane().add(textField_5);

        JLabel lblEnterName_3_1 = new JLabel("Enter Teacher ID :");
        lblEnterName_3_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblEnterName_3_1.setBounds(322, 568, 113, 25);
        frame.getContentPane().add(lblEnterName_3_1);

        JLabel lblEnterName_3_2 = new JLabel("Enter Room :");
        lblEnterName_3_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblEnterName_3_2.setBounds(556, 568, 87, 25);
        frame.getContentPane().add(lblEnterName_3_2);

        textField_6 = new JTextField();
        textField_6.setColumns(10);
        textField_6.setBounds(439, 569, 97, 25);
        frame.getContentPane().add(textField_6);

        textField_7 = new JTextField();
        textField_7.setColumns(10);
        textField_7.setBounds(636, 569, 87, 25);
        frame.getContentPane().add(textField_7);

        JButton btnNewButton_2 = new JButton("Close");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnNewButton_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        btnNewButton_2.setBounds(755, 630, 164, 32);
        frame.getContentPane().add(btnNewButton_2);

        JButton btnNewButton_1 = new JButton("Clear");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        btnNewButton_1.setBounds(515, 630, 170, 32);
        frame.getContentPane().add(btnNewButton_1);

        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		addRow();
        	}
        });
        
        
        btnAdd.setBounds(265, 630, 164, 32);
        frame.getContentPane().add(btnAdd);

        JButton btnRemove = new JButton("Remove");
        btnRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeRow();
            }
        });

        btnRemove.setBounds(27, 630, 164, 32);
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

        textField_1 = new JTextField();
        textField_1.setBounds(337, 120, 146, 25);
        frame.getContentPane().add(textField_1);
        textField_1.setColumns(10);

        JButton btnShowTimeSlots = new JButton("Show TimeSlots");
        btnShowTimeSlots.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sectionID = textField_1.getText().trim(); // Retrieve the SectionID from textField_1

                if (!sectionID.isEmpty()) {
                    fetchAndPopulateTimetable(sectionID); // Call the method to fetch and populate the timetable
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter a Section Id", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnShowTimeSlots.setBounds(493, 120, 146, 25);
        frame.getContentPane().add(btnShowTimeSlots);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(29, 254, 940, 200);
        frame.getContentPane().add(scrollPane);

        timetableTable = new JTable();
        scrollPane.setViewportView(timetableTable);
        
        JLabel lblEnterName_3_1_1 = new JLabel("Enter Section ID :");
        lblEnterName_3_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblEnterName_3_1_1.setBounds(862, 525, 113, 25);
        frame.getContentPane().add(lblEnterName_3_1_1);
        
        textField_10 = new JTextField();
        textField_10.setColumns(10);
        textField_10.setBounds(973, 526, 70, 25);
        frame.getContentPane().add(textField_10);
        
        JLabel lblEnterName_3_1_1_1 = new JLabel("Enter Section ID :");
        lblEnterName_3_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblEnterName_3_1_1_1.setBounds(739, 568, 113, 25);
        frame.getContentPane().add(lblEnterName_3_1_1_1);
        
        textField_11 = new JTextField();
        textField_11.setColumns(10);
        textField_11.setBounds(864, 568, 179, 25);
        frame.getContentPane().add(textField_11);
    
    }
    private void addRow() {
        // Retrieve data from text fields
        String dayOfWeek = textField.getText();
        String startTime = textField_2.getText();
        String teacherName = textField_3.getText();
        String endTime = textField_4.getText();
        String courseName = textField_5.getText();
        String teacherID = textField_6.getText();
        String roomID = textField_7.getText();
        String studentID = textField_11.getText();
        String sectionID = textField_10.getText();

        // Add row to the table
        DefaultTableModel model = (DefaultTableModel) timetableTable.getModel();
        model.addRow(new Object[]{teacherName, courseName, dayOfWeek, startTime, endTime, roomID});

        // Insert into the MySQL database
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
                // Verify if SectionID exists in the sections table
                String sqlVerify = "SELECT * FROM sections WHERE SectionID = ?";
                try (PreparedStatement stmtVerify = conn.prepareStatement(sqlVerify)) {
                    stmtVerify.setString(1, sectionID);
                    ResultSet rsVerify = stmtVerify.executeQuery();

                    if (rsVerify.next()) {
                        // SectionID is valid, proceed to fetch timetable
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
        textField.setText("");
        textField_2.setText("");
        textField_3.setText("");
        textField_4.setText("");
        textField_5.setText("");
        textField_6.setText("");
        textField_7.setText("");
        textField_1.setText("");
        textField_11.setText("");
        textField_10.setText("");
        
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
            // Populate text fields with data from the selected row
        	textField.setText(model.getValueAt(selectedRow, 2).toString()); // Day Of Week
            textField_2.setText(model.getValueAt(selectedRow, 3).toString()); // Start Time
            textField_3.setText(model.getValueAt(selectedRow, 0).toString()); // Teacher Name
            textField_4.setText(model.getValueAt(selectedRow, 4).toString()); // End Time
            textField_5.setText(model.getValueAt(selectedRow, 1).toString()); // Course Name
            textField_7.setText(model.getValueAt(selectedRow, 5).toString()); // Room ID
        }
    }
}
