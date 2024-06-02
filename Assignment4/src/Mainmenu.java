import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Mainmenu extends JFrame {

    private JFrame Mainmenu;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Mainmenu window = new Mainmenu();
                    window.Mainmenu.setSize(650, 550);
                    window.Mainmenu.setLocationRelativeTo(null);
                    window.Mainmenu.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Mainmenu() {
        initialize();
        Connection connection = connectToDatabase();
        if (connection != null) {
            JOptionPane.showMessageDialog(this, "Connected to the database successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to connect to the database.");
        }
    }

    private void initialize() {
        Mainmenu = new JFrame();
        Mainmenu.getContentPane().setBackground(SystemColor.inactiveCaption);
        Mainmenu.setTitle("Mainmenu");
        Mainmenu.setFont(new Font("Times New Roman", Font.BOLD, 12));
        Mainmenu.setBounds(100, 100, 655, 540);
        Mainmenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Mainmenu.getContentPane().setLayout(null);
        
        JLabel Label = new JLabel("Time-table Management System");
        Label.setFont(new Font("Times New Roman", Font.BOLD, 24));
        Label.setBounds(164, 246, 336, 42);
        Mainmenu.getContentPane().add(Label);
        
        JLabel label1 = new JLabel("picture");
        label1.setBackground(Color.LIGHT_GRAY);
        ImageIcon img = new ImageIcon(this.getClass().getResource("/bahria-university-logo (1).jpg"));
        label1.setIcon(img);
        label1.setBounds(208, 29, 239, 206);
        Mainmenu.getContentPane().add(label1);
        
        JButton btnNewButton = new JButton(" Teacher Wise Time-table");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TeacherLogin teacherlogin = new TeacherLogin();
                Mainmenu.setVisible(false);
                teacherlogin.frame.setVisible(true);
            }
        });
        
        btnNewButton.setBackground(SystemColor.activeCaption);
        btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        btnNewButton.setBounds(46, 343, 236, 33);
        Mainmenu.getContentPane().add(btnNewButton);
        
        JButton btnSectionWiseTimetable = new JButton("Section Wise Time-table");
        btnSectionWiseTimetable.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 SectionLogin sectionlogin = new SectionLogin();
                 Mainmenu.setVisible(false);
                 sectionlogin.frame.setVisible(true);
        		
        	}
        });
        btnSectionWiseTimetable.setBackground(SystemColor.activeCaption);
        btnSectionWiseTimetable.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        btnSectionWiseTimetable.setBounds(46, 387, 236, 33);
        Mainmenu.getContentPane().add(btnSectionWiseTimetable);
        
        JButton btnRoomWiseTimetable = new JButton("Room Wise Time-table");
        btnRoomWiseTimetable.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RoomLogin roomlogin = new RoomLogin();
                Mainmenu.setVisible(false);
                roomlogin.frame.setVisible(true);
            }
        });
        btnRoomWiseTimetable.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        btnRoomWiseTimetable.setBackground(SystemColor.activeCaption);
        btnRoomWiseTimetable.setBounds(349, 387, 239, 33);
        Mainmenu.getContentPane().add(btnRoomWiseTimetable);
        
        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        btnExit.setForeground(SystemColor.desktop);
        btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        btnExit.setBackground(SystemColor.activeCaption);
        btnExit.setBounds(349, 431, 239, 33);
        Mainmenu.getContentPane().add(btnExit);
        
        JButton btnSearchTimetable = new JButton("Search Timetable");
        btnSearchTimetable.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SearchTimeSlot searchtimeslot = new SearchTimeSlot();
                Mainmenu.setVisible(false);
                searchtimeslot.frame.setVisible(true);
            }
        });
        btnSearchTimetable.setForeground(SystemColor.desktop);
        btnSearchTimetable.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        btnSearchTimetable.setBackground(SystemColor.activeCaption);
        btnSearchTimetable.setBounds(46, 431, 239, 33);
        Mainmenu.getContentPane().add(btnSearchTimetable);
        
        JButton btnAdminLogin = new JButton("Admin Login");
        btnAdminLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdminLogin adminlogin = new AdminLogin();
                Mainmenu.setVisible(false);
                adminlogin.frame.setVisible(true);
            }
        });
        
        btnAdminLogin.setForeground(SystemColor.desktop);
        btnAdminLogin.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        btnAdminLogin.setBackground(SystemColor.activeCaption);
        btnAdminLogin.setBounds(46, 299, 542, 33);
        Mainmenu.getContentPane().add(btnAdminLogin);
        
        JButton btnStudentWiseTimetable = new JButton("Student Wise Time-table");
        btnStudentWiseTimetable.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StudentLogin studentlogin = new StudentLogin();
                Mainmenu.setVisible(false);
                studentlogin.frame.setVisible(true);
            }
        });
        btnStudentWiseTimetable.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        btnStudentWiseTimetable.setBackground(SystemColor.activeCaption);
        btnStudentWiseTimetable.setBounds(349, 343, 239, 33);
        Mainmenu.getContentPane().add(btnStudentWiseTimetable);
    }
    
    private Connection connectToDatabase() {
        String url = "jdbc:mysql://localhost:3306/assignment4";
        String user = "root";
        String password = "12345678"; // Replace with your MySQL password

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
