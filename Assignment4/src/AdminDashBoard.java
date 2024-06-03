import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminDashBoard {

	JFrame frame;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminDashBoard window = new AdminDashBoard();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public AdminDashBoard() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.inactiveCaption);
		frame.getContentPane().setLayout(null);
		
		JLabel label1 = new JLabel("Admin Dashboard");
		ImageIcon img = new ImageIcon(this.getClass().getResource("/bahria-university-logo (1).jpg"));
        label1.setIcon(img);
		label1.setBackground(Color.LIGHT_GRAY);
		label1.setBounds(204, 11, 242, 206);
		frame.getContentPane().add(label1);
		
		JLabel lblAdminDashboaed = new JLabel("Admin Dashboard");
		lblAdminDashboaed.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblAdminDashboaed.setBounds(232, 228, 206, 42);
		frame.getContentPane().add(lblAdminDashboaed);
		
		JButton btnViewTeacher = new JButton("View Teacher");
		btnViewTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewTeachers viewteachers = new ViewTeachers ();
	     		   AdminDashBoard.setVisible(false);
	     		  viewteachers.frame.setVisible(true); 
				
			}
		});
		
		btnViewTeacher.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnViewTeacher.setBackground(SystemColor.activeCaption);
		btnViewTeacher.setBounds(44, 416, 242, 33);
		frame.getContentPane().add(btnViewTeacher);
		
		JButton btnViewStudents = new JButton("View Students");
		btnViewStudents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewStudents viewstudents = new ViewStudents();
	     		   AdminDashBoard.setVisible(false);
	     		  viewstudents.frame.setVisible(true); 
			}
		});
		
		btnViewStudents.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnViewStudents.setBackground(SystemColor.activeCaption);
		btnViewStudents.setBounds(334, 416, 234, 33);
		frame.getContentPane().add(btnViewStudents);
		
		JButton btnAddTeachers = new JButton("Add Teachers");
		btnAddTeachers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddTeacher addteacher = new AddTeacher();
	     		   AdminDashBoard.setVisible(false);
	     		  addteacher.frame.setVisible(true);
				
			}
		});
		
		btnAddTeachers.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnAddTeachers.setBackground(SystemColor.activeCaption);
		btnAddTeachers.setBounds(44, 328, 242, 33);
		frame.getContentPane().add(btnAddTeachers);
		
		JButton btnAddStudents = new JButton("Add Students");
		btnAddStudents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStudentform addstudentForm = new AddStudentform();
	     		   AdminDashBoard.setVisible(false);
	     		  addstudentForm.frame.setVisible(true);
			}
		});
		
		btnAddStudents.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnAddStudents.setBackground(SystemColor.activeCaption);
		btnAddStudents.setBounds(334, 328, 234, 33);
		frame.getContentPane().add(btnAddStudents);
		
		JButton btnRemoveTeacher = new JButton("Remove Teacher");
		btnRemoveTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveTeacher removeteacher = new RemoveTeacher();
	     		   AdminDashBoard.setVisible(false);
	     		  removeteacher.frame.setVisible(true);
			}
		});
		
		btnRemoveTeacher.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnRemoveTeacher.setBackground(SystemColor.activeCaption);
		btnRemoveTeacher.setBounds(44, 372, 242, 33);
		frame.getContentPane().add(btnRemoveTeacher);
		
		JButton btnRemoveStudent = new JButton("Remove Student");
		btnRemoveStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveStudent removestudent = new RemoveStudent();
	     		   AdminDashBoard.setVisible(false);
	     		  removestudent.frame.setVisible(true);
			}
		});
		
		btnRemoveStudent.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnRemoveStudent.setBackground(SystemColor.activeCaption);
		btnRemoveStudent.setBounds(334, 372, 234, 33);
		frame.getContentPane().add(btnRemoveStudent);
		
		JButton btnManageTimeSlots = new JButton("Manage TimeSlots");
		btnManageTimeSlots.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageTimeSlots managetimeslots = new ManageTimeSlots();
     		   AdminDashBoard.setVisible(false);
     		  managetimeslots.frame.setVisible(true);
			}
		});
		
		btnManageTimeSlots.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnManageTimeSlots.setBackground(SystemColor.activeCaption);
		btnManageTimeSlots.setBounds(44, 281, 524, 33);
		frame.getContentPane().add(btnManageTimeSlots);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		btnLogOut.setForeground(SystemColor.desktop);
		btnLogOut.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnLogOut.setBackground(SystemColor.activeCaption);
		btnLogOut.setBounds(177, 460, 269, 33);
		frame.getContentPane().add(btnLogOut);
		frame.setBounds(100, 100, 641, 545);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	protected static void setVisible(boolean b) {
		
		
	}

}
