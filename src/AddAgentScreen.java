import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class AddAgentScreen {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private JLabel lblAddAgent;
	private JTextField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddAgentScreen window = new AddAgentScreen();
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
	public AddAgentScreen() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(144, 238, 144));
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 30));
		frame.getContentPane().setForeground(new Color(128, 128, 0));
		frame.setBounds(100, 100, 962, 535);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Agent ID");
		lblNewLabel.setBounds(236, 107, 195, 43);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Agent Name");
		lblNewLabel_1.setBounds(236, 171, 195, 43);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Agent Email");
		lblNewLabel_2.setBounds(236, 235, 195, 43);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Agent Password");
		lblNewLabel_3.setBounds(236, 305, 195, 43);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Contact Numbers");
		lblNewLabel_4.setBounds(236, 373, 195, 43);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel_4);
		
		textField = new JTextField();
		textField.setBounds(517, 107, 206, 32);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(517, 177, 206, 32);
		textField_1.setColumns(10);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(517, 241, 206, 32);
		textField_2.setColumns(10);
		frame.getContentPane().add(textField_2);
		
		textField_4 = new JTextField();
		textField_4.setBounds(517, 379, 206, 32);
		textField_4.setColumns(10);
		frame.getContentPane().add(textField_4);
		
		lblAddAgent = new JLabel("Add Agent ");
		lblAddAgent.setBounds(405, 25, 195, 43);
		lblAddAgent.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddAgent.setFont(new Font("Tahoma", Font.BOLD, 30));
		frame.getContentPane().add(lblAddAgent);
		
		passwordField = new JTextField();
		passwordField.setBounds(517, 305, 206, 35);
		frame.getContentPane().add(passwordField);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String agent_id = textField.getText();
				String agent_name = textField_1.getText();
				String email = textField_2.getText();
				String password = passwordField.getText();
				String contacts = textField_4.getText();
				contacts = contacts.trim();
				String[] contact_array = contacts.split(",",0);
				for(int x=0;x<contact_array.length;x++)
				{
					contact_array[x] = contact_array[x].trim();
				}
				try
				{
					Backend backend = new Backend(Constants.databaseUserName,Constants.databasePassword,Constants.databaseName);
					boolean additionSuccessful = backend.admin_addAgent(agent_id,agent_name,email,password,contact_array);
					backend.closeDatabase();
					if(additionSuccessful)
					{
						new AlertBoxScreen("Added new agent successfully");
					}
					else
					{
						new AlertBoxScreen("Error in adding new agent, check input again");
					}
				}
				catch (Exception exception)
				{
					new AlertBoxScreen("Error connecting to Database");
				}
			}
		});
		btnNewButton.setBounds(423, 426, 159, 46);
		btnNewButton.setBackground(new Color(128, 128, 128));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 30));
		frame.getContentPane().add(btnNewButton);
	}
}
