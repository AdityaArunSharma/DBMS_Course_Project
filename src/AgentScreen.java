import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AgentScreen {

	private JFrame frame;
	String agent_profile;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the application.
	 */
	public AgentScreen(String agent_profile)
	{
		this.agent_profile = agent_profile;
		initialize();
		frame.setVisible(true);
	}


	public static void main(String[] args) {
		new AgentScreen("1901099");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(124, 252, 0));
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Agent");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(187, 35, 263, 84);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Profile");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{

				new AgentProfileScreen(agent_profile);
			}
		});
		
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btnNewButton.setBounds(213, 168, 209, 42);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Properties Assigned");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					 Backend backend =  new Backend(Constants.databaseUserName,Constants.databasePassword,Constants.databaseName);
					 backend.agent_propertiesAssigned(agent_profile);
					backend.closeDatabase();
				}
				catch (Exception exception)
				{
					new AlertBoxScreen("Error in connecting to Database");
				}
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btnNewButton_1.setBounds(213, 234, 209, 42);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Properties Rented");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
//				System.out.println("Properties");
				try
				{
					Backend backend =  new Backend(Constants.databaseUserName,Constants.databasePassword,Constants.databaseName);
					backend.agent_propertiesRented(agent_profile);
					backend.closeDatabase();
				}
				catch (Exception exception)
				{
					new AlertBoxScreen("Error in connecting to Database");
				}
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btnNewButton_2.setBounds(213, 298, 209, 42);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Add Property");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new AddPropertyAgentScreen(agent_profile);
			}
		});
		btnNewButton_3.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btnNewButton_3.setBounds(213, 360, 209, 42);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Properties Sold");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					Backend backend =  new Backend(Constants.databaseUserName,Constants.databasePassword,Constants.databaseName);
					backend.agent_propertiesSold(agent_profile);
					backend.closeDatabase();
				}
				catch (Exception exception)
				{
					new AlertBoxScreen("Error in connecting to Database");
				}
			}
		});
		btnNewButton_4.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btnNewButton_4.setBounds(213, 423, 209, 42);
		frame.getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Clients");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					Backend backend =  new Backend(Constants.databaseUserName,Constants.databasePassword,Constants.databaseName);
					backend.agent_clients(agent_profile);
					backend.closeDatabase();
				}
				catch (Exception exception)
				{
					new AlertBoxScreen("Error in connecting to Database");
				}
			}
		});
		btnNewButton_5.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btnNewButton_5.setBounds(213, 491, 209, 42);
		frame.getContentPane().add(btnNewButton_5);
		
	
		
		frame.setBounds(100, 100, 655, 665);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
