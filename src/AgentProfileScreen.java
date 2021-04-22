import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;

public class AgentProfileScreen {

	public JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	String agent_profile;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public AgentProfileScreen(String agent_profile)
	{
		this.agent_profile = agent_profile;
		initialize();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new AgentProfileScreen("1901099");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 182, 193));
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Agent Profile");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 35));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(130, 49, 359, 93);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Agent ID");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(74, 236, 124, 38);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Agent Name");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(74, 304, 124, 38);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Agent Email");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(74, 372, 124, 38);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Agent Password");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(74, 439, 124, 38);
		frame.getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Agent Contact Number");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(60, 506, 154, 38);
		frame.getContentPane().add(lblNewLabel_5);

		
		textField = new JTextField();
		textField.setBounds(235, 239, 299, 36);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(235, 306, 299, 38);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(235, 374, 299, 38);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(235, 441, 299, 38);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);





		setProfile();
		
		JButton btnNewButton = new JButton("Get Profile");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(textField.getText());
				System.out.println(textField_1.getText());
				System.out.println(textField_2.getText());
				System.out.println(textField_3.getText());
			}
		});
		btnNewButton.setVisible(false);
		textField.setEditable(false);
		textField_1.setEditable(false);
		textField_2.setEditable(false);
		textField_3.setEditable(false);

		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		btnNewButton.setBackground(new Color(245, 245, 220));
		btnNewButton.setBounds(278, 519, 158, 38);
		frame.getContentPane().add(btnNewButton);
		frame.setBounds(100, 100, 681, 648);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setProfile()
	{
		Backend backend = null;
		try
		{
			backend = new Backend(Constants.databaseUserName,Constants.databasePassword,Constants.databaseName);
			Agent agent = backend.agent_agentProfile(agent_profile);
			backend.closeDatabase();
			if(agent==null)
			{
				new AlertBoxScreen("Cannot retrieve information from database :(");
			}
			textField.setText(agent.agentID);
			textField_1.setText(agent.name);
			textField_2.setText(agent.email);
			textField_3.setText(agent.password);

			JComboBox cb=new JComboBox(agent.contactNumber.toArray(new String[0]));
			cb.setBounds(235, 505,158,38);
			frame.add(cb);

		} catch (Exception exception)
		{
			new AlertBoxScreen("Error in connecting to Database");
		}

	}

}
