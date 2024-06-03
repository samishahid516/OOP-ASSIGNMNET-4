import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JScrollPane;

public class ViewStudents {

    JFrame frame;
    private JTable table;

   
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewStudents window = new ViewStudents();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

   
    public ViewStudents() {
        initialize();
        displayStudents();
    }

    
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(SystemColor.inactiveCaption);
        frame.getContentPane().setLayout(null);

        JLabel label1 = new JLabel("picture");
        ImageIcon img = new ImageIcon(this.getClass().getResource("/bahria-university-logo (1).jpg"));
        label1.setIcon(img);
        label1.setBackground(Color.LIGHT_GRAY);
        label1.setBounds(168, 11, 239, 206);
        frame.getContentPane().add(label1);

        JLabel lblStudentLogin = new JLabel("Student List");
        lblStudentLogin.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lblStudentLogin.setBounds(218, 219, 147, 42);
        frame.getContentPane().add(lblStudentLogin);
        
        JScrollPane StudentListTable = new JScrollPane();
        StudentListTable.setBounds(32, 258, 504, 179);
        frame.getContentPane().add(StudentListTable);

        table = new JTable();
        StudentListTable.setViewportView(table);
        table.setBackground(SystemColor.activeCaptionBorder);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnClose.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        btnClose.setBounds(206, 448, 147, 32);
        frame.getContentPane().add(btnClose);
        frame.setBounds(100, 100, 586, 530);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void displayStudents() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment4", "root", "12345678");
            statement = connection.createStatement();      
            String sql = "SELECT * FROM add_student";
            resultSet = statement.executeQuery(sql);
                       
            java.sql.ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            DefaultTableModel model = (DefaultTableModel) table.getModel();
            String[] colNames = new String[columnCount];
            for (int i = 0; i < columnCount; i++) {
                colNames[i] = metaData.getColumnName(i + 1);
            }
            model.setColumnIdentifiers(colNames);

            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    rowData[columnIndex - 1] = resultSet.getObject(columnIndex);
                }
                model.addRow(rowData);
            }

            table.setModel(model);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {     
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
