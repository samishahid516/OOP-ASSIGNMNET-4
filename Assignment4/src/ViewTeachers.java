import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class ViewTeachers {

    JFrame frame;
    private JTable table;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewTeachers window = new ViewTeachers();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ViewTeachers() {
        initialize();
        displayTeachers(); // Call displayTeachers to populate the table with teacher data
    }

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(SystemColor.inactiveCaption);
        frame.setBounds(100, 100, 593, 572);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JLabel label1 = new JLabel("picture");
        ImageIcon img = new ImageIcon(this.getClass().getResource("/bahria-university-logo (1).jpg"));
        label1.setIcon(img);
        label1.setBackground(Color.LIGHT_GRAY);
        label1.setBounds(176, 11, 239, 206);
        frame.getContentPane().add(label1);
        
        JLabel lblStudentLogin = new JLabel("Teacher List"); // Changed label text to "Teacher List"
        lblStudentLogin.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lblStudentLogin.setBounds(226, 219, 147, 42);
        frame.getContentPane().add(lblStudentLogin);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(36, 272, 504, 179);
        frame.getContentPane().add(scrollPane);
        
        table = new JTable();
        scrollPane.setViewportView(table);
        table.setBackground(SystemColor.activeCaptionBorder);
        
        JButton btnNewButton_2 = new JButton("Close");
        btnNewButton_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        btnNewButton_2.setBounds(214, 462, 147, 32);
        frame.getContentPane().add(btnNewButton_2);
    }

    private void displayTeachers() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Establish connection to your database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment4", "root", "12345678");

            // Create SQL statement
            statement = connection.createStatement();

            // Execute SQL query to fetch all records from add_teacher table
            String sql = "SELECT * FROM add_teacher";
            resultSet = statement.executeQuery(sql);

            // Get metadata from ResultSet
            java.sql.ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Create a DefaultTableModel to hold the data
            DefaultTableModel model = new DefaultTableModel();

            // Add column names to the model
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                model.addColumn(metaData.getColumnLabel(columnIndex));
            }

            // Add rows to the model
            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    rowData[columnIndex - 1] = resultSet.getObject(columnIndex);
                }
                model.addRow(rowData);
            }

            // Set the model to the table
            table.setModel(model);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close result set, statement, and connection
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
