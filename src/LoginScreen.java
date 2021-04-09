import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginScreen {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	String mode;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen window = new LoginScreen(Constants.mode_agent);
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
	public LoginScreen(String mode)
	{
		this.mode = mode;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 656, 484);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(245, 91, 84));
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel.setBounds(141, 105, 134, 35);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(141, 162, 134, 35);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(317, 105, 134, 35);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		// Login button
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{

				System.out.println("Username "+textField.getText());
				String username = textField.getText();
				String password = new String(passwordField.getPassword());
				System.out.println("Mode : "+mode);
				System.out.println("Password "+password);
				try
				{
					Backend backend = new Backend(Constants.databaseUserName,Constants.databasePassword,Constants.databaseName);
					boolean loginResult = backend.checkLogin(username,password,mode);
					backend.closeDatabase();
					if(mode.equalsIgnoreCase(Constants.mode_admin) && loginResult)
					{
						System.out.println("Successfully logged in :)");
						new AlertBoxScreen("Login Successful");
						frame.dispose();
						System.out.println("Going to open Administrator Screen");
						// Call Admin Class
					}
					else if(mode.equalsIgnoreCase(Constants.mode_agent) && loginResult)
					{
						System.out.println("Successfully logged in :)");
						new AlertBoxScreen("Login Successful");
						frame.dispose();
						System.out.println("Going to open Agent screen for agent id "+username);
						// Call Agent Class
					}
					else
					{
						System.out.println("Invalid login details");
						new AlertBoxScreen("Invalid Login details");
					}
				}
				catch (Exception exception)
				{
					new AlertBoxScreen("Error in Connection with Database");
				}

				
			}
		});
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(249, 281, 134, 35);
		btnNewButton.setBackground(new Color(100,245,205));
		frame.getContentPane().add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(317, 162, 134, 35);
		frame.getContentPane().add(passwordField);
		
		JLabel lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_2.setBounds(266, 10, 134, 67);
		frame.getContentPane().add(lblNewLabel_2);
	}
}
