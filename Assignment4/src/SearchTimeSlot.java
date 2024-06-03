import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class SearchTimeSlot {

    JFrame frame;
    private JTextField EnterDay;
    private JTextField EnterStartTime;
    private JTable table;
    private DefaultTableModel tableModel;

   
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SearchTimeSlot window = new SearchTimeSlot();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

   
    public SearchTimeSlot() {
        initialize();
    }

    
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(SystemColor.inactiveCaption);
        frame.setBounds(100, 100, 777, 573);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JLabel label1 = new JLabel("picture");
        ImageIcon img = new ImageIcon(this.getClass().getResource("/bahria-university-logo (1).jpg"));
        label1.setIcon(img);
        label1.setBackground(Color.LIGHT_GRAY);
        label1.setBounds(268, 11, 239, 206);
        frame.getContentPane().add(label1);
        
        JLabel lblSearchTimeslot = new JLabel("Search TimeSlot");
        lblSearchTimeslot.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lblSearchTimeslot.setBounds(307, 225, 194, 42);
        frame.getContentPane().add(lblSearchTimeslot);
        
        JLabel lblEnterName = new JLabel("Enter Day :");
        lblEnterName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblEnterName.setBounds(38, 268, 97, 25);
        frame.getContentPane().add(lblEnterName);
        
        EnterDay = new JTextField();
        EnterDay.setColumns(10);
        EnterDay.setBounds(130, 269, 194, 25);
        frame.getContentPane().add(EnterDay);
        
        JLabel lblEnterApplicationId = new JLabel("Enter Start Time :");
        lblEnterApplicationId.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblEnterApplicationId.setBounds(412, 268, 112, 25);
        frame.getContentPane().add(lblEnterApplicationId);
        
        EnterStartTime = new JTextField();
        EnterStartTime.setColumns(10);
        EnterStartTime.setBounds(537, 269, 194, 25);
        frame.getContentPane().add(EnterStartTime);
        
        JButton btnNewButton_2 = new JButton("Close");
        btnNewButton_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        btnNewButton_2.setBounds(585, 542, 0, 32);
        frame.getContentPane().add(btnNewButton_2);
        
        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(285, 495, 184, 32);
        frame.getContentPane().add(btnSearch);
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchTimeSlot();
            }
        });
        
        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EnterStartTime.setText(null);
                EnterDay.setText(null);
                tableModel.setRowCount(0);
            }
        });
        
        btnClear.setBounds(38, 495, 167, 32);
        frame.getContentPane().add(btnClear);
        
        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        btnClose.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        btnClose.setBounds(563, 495, 167, 32);
        frame.getContentPane().add(btnClose);
        
        tableModel = new DefaultTableModel(new Object[]{"CourseName", "DayOfWeek", "StartTime", "EndTime", "RoomID", "SectionID"}, 0);
        
        JScrollPane TimeTable = new JScrollPane();
        TimeTable.setBounds(38, 315, 693, 169);
        frame.getContentPane().add(TimeTable);

        table = new JTable(tableModel);
        TimeTable.setViewportView(table);
        table.setBackground(Color.WHITE);
    }

    private void searchTimeSlot() {
        String day = EnterDay.getText();
        String startTime = EnterStartTime.getText();
        
        try {
            Connection connection = getConnection();
            String query = "SELECT CourseName, DayOfWeek, StartTime, EndTime, RoomID, SectionID FROM timetable WHERE DayOfWeek = '" + day + "' AND StartTime = '" + startTime + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            tableModel.setRowCount(0); 
            
            while (resultSet.next()) {
                String courseName = resultSet.getString("CourseName");
                String dayOfWeek = resultSet.getString("DayOfWeek");
                String startTimeResult = resultSet.getString("StartTime");
                String endTimeResult = resultSet.getString("EndTime");
                String roomID = resultSet.getString("RoomID");
                String sectionID = resultSet.getString("SectionID");
                tableModel.addRow(new Object[]{courseName, dayOfWeek, startTimeResult, endTimeResult, roomID, sectionID});
            }
            
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/assignment4";
        String username = "root";
        String password = "12345678";
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }
}
