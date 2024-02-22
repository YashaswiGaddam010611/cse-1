package studentmanagement;
   

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import java.sql.Statement;

public class Registration {

	private JFrame frame;
	private JTextField idField;
	private JTextField nameField;
	private JTextField marksField;
	
	private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/studentregistration";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "Yashaswi9@";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registration window = new Registration();
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
	public Registration() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 971, 564);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registration");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel.setBounds(386, 60, 198, 58);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Id: ");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1.setBounds(239, 177, 168, 45);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Name: ");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(239, 233, 168, 45);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Marks: ");
		lblNewLabel_1_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1_2.setBounds(239, 289, 168, 45);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		idField = new JTextField();
		idField.setBounds(386, 179, 223, 30);
		frame.getContentPane().add(idField);
		idField.setColumns(10);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(386, 248, 223, 30);
		frame.getContentPane().add(nameField);
		
		marksField = new JTextField();
		marksField.setColumns(10);
		marksField.setBounds(386, 304, 223, 30);
		frame.getContentPane().add(marksField);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id =  Integer.parseInt(idField.getText());
				String name = nameField.getText();
				int marks = Integer.parseInt(marksField.getText());
				System.out.println(id+" "+name+" "+marks);
				
				String query = "insert into student values (?, ?, ?)";
				
				try {
					//loading the Driver class
					Class.forName(Registration.DRIVER_CLASS_NAME);
					
					//establishing connection
					Connection con = DriverManager.getConnection(Registration.DATABASE_URL, Registration.USER_NAME, Registration.PASSWORD);
					
					//creating prepared statement
					PreparedStatement st = con.prepareStatement(query);
					st.setInt(1, id);
					st.setString(2, name);
					st.setInt(3, marks);
					
					//executing query
					st.executeUpdate();
					
					JOptionPane.showMessageDialog(btnNewButton, "Registration Done.");
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(btnNewButton, "Something went wrong please try after sometime or contack to the service provide.");
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton.setBounds(406, 409, 156, 45);
		frame.getContentPane().add(btnNewButton);
	}
}