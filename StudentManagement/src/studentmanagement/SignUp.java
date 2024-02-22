package studentmanagement;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignUp {

	private JFrame frame;
	private JTextField userNameUField;
	private JTextField passwordUField;
	private JTextField userNameIField;
	private JPasswordField passwordIField;
	
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
					SignUp window = new SignUp();
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
	public SignUp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 1036, 617);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel signUpTitle = new JLabel("SignUp");
		signUpTitle.setFont(new Font("Arial", Font.BOLD, 30));
		signUpTitle.setBounds(199, 77, 194, 60);
		frame.getContentPane().add(signUpTitle);
		
		JLabel userNameU = new JLabel("User Name: ");
		userNameU.setFont(new Font("Arial", Font.BOLD, 20));
		userNameU.setBounds(79, 194, 171, 47);
		frame.getContentPane().add(userNameU);
		
		JLabel passwordU = new JLabel("Password: ");
		passwordU.setFont(new Font("Arial", Font.BOLD, 20));
		passwordU.setBounds(79, 270, 171, 47);
		frame.getContentPane().add(passwordU);
		
		userNameUField = new JTextField();
		userNameUField.setBounds(265, 205, 225, 31);
		frame.getContentPane().add(userNameUField);
		userNameUField.setColumns(10);
		
		passwordUField = new JTextField();
		passwordUField.setColumns(10);
		passwordUField.setBounds(265, 281, 225, 31);
		frame.getContentPane().add(passwordUField);
		
		JButton signUpButton = new JButton("Sign Up");
		signUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = userNameUField.getText();
				String password = passwordUField.getText();
				
				if(userName.equals("") || password.equals("")) {
					JOptionPane.showMessageDialog(signUpButton, "User name or password cannot be empty!");
					return;
				}
				
				try {
					Class.forName(DRIVER_CLASS_NAME);
					Connection con = DriverManager.getConnection(DATABASE_URL, USER_NAME, PASSWORD);
					PreparedStatement st = con.prepareStatement("insert into signupdetails values(?, ?)");
					st.setString(1, userName);
					st.setString(2, password);
					st.executeUpdate();
					
					JOptionPane.showMessageDialog(signUpButton, "SignUp Done.");
					
				} catch(Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		signUpButton.setFont(new Font("Arial", Font.BOLD, 20));
		signUpButton.setBounds(173, 425, 171, 47);
		frame.getContentPane().add(signUpButton);
		
		JLabel lblSignin = new JLabel("SignIn");
		lblSignin.setFont(new Font("Arial", Font.BOLD, 30));
		lblSignin.setBounds(672, 77, 194, 60);
		frame.getContentPane().add(lblSignin);
		
		JLabel lblNewLabel_1_2 = new JLabel("User Name: ");
		lblNewLabel_1_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1_2.setBounds(561, 194, 171, 47);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Password: ");
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1_1_1.setBounds(561, 270, 171, 47);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		userNameIField = new JTextField();
		userNameIField.setColumns(10);
		userNameIField.setBounds(729, 205, 225, 31);
		frame.getContentPane().add(userNameIField);
		
		passwordIField = new JPasswordField();
		passwordIField.setBounds(729, 281, 225, 31);
		frame.getContentPane().add(passwordIField);
		
		JButton signInButton = new JButton("Sign In");
		signInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = userNameIField.getText();
				String password = passwordIField.getText();
				
				try {
					Class.forName(DRIVER_CLASS_NAME);
					Connection con = DriverManager.getConnection(DATABASE_URL, USER_NAME, PASSWORD);
					
					Statement st = con.createStatement();
					
					ResultSet rs = st.executeQuery("select password from signupdetails where username='"+userName+"'");
					rs.next();
					String pass = rs.getString(1);
					
					if(pass.equals(password)) {
						JOptionPane.showMessageDialog(signInButton, "Sign In Done");
						return;
					}
					
					JOptionPane.showMessageDialog(signUpButton, "Invalid details.");
					
				} catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		signInButton.setFont(new Font("Arial", Font.BOLD, 20));
		signInButton.setBounds(672, 425, 171, 47);
		frame.getContentPane().add(signInButton);
	}
}
