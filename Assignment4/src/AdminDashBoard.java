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

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the application.
	 */
	public AdminDashBoard() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
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
		
		JButton btnDisplayTeacher = new JButton("View Teacher");
		btnDisplayTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewTeachers viewteachers = new ViewTeachers ();
	     		   AdminDashBoard.setVisible(false);
	     		  viewteachers.frame.setVisible(true); 
				
			}
		});
		
		btnDisplayTeacher.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnDisplayTeacher.setBackground(SystemColor.activeCaption);
		btnDisplayTeacher.setBounds(44, 416, 242, 33);
		frame.getContentPane().add(btnDisplayTeacher);
		
		JButton btnDisplayStudents = new JButton("View Students");
		btnDisplayStudents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewStudents viewstudents = new ViewStudents();
	     		   AdminDashBoard.setVisible(false);
	     		  viewstudents.frame.setVisible(true); 
			}
		});
		
		btnDisplayStudents.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnDisplayStudents.setBackground(SystemColor.activeCaption);
		btnDisplayStudents.setBounds(334, 416, 234, 33);
		frame.getContentPane().add(btnDisplayStudents);
		
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
		
		JButton btnAddTimeslot = new JButton("Manage TimeSlots");
		btnAddTimeslot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageTimeSlots managetimeslots = new ManageTimeSlots();
     		   AdminDashBoard.setVisible(false);
     		  managetimeslots.frame.setVisible(true);
			}
		});
		
		btnAddTimeslot.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnAddTimeslot.setBackground(SystemColor.activeCaption);
		btnAddTimeslot.setBounds(44, 281, 524, 33);
		frame.getContentPane().add(btnAddTimeslot);
		
		JButton btnExit = new JButton("Log Out");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		btnExit.setForeground(SystemColor.desktop);
		btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnExit.setBackground(SystemColor.activeCaption);
		btnExit.setBounds(177, 460, 269, 33);
		frame.getContentPane().add(btnExit);
		frame.setBounds(100, 100, 641, 545);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	protected static void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}

}
