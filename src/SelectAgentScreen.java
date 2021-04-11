import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.JButton;

public class SelectAgentScreen {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectAgentScreen window = new SelectAgentScreen();
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
	public SelectAgentScreen() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(244, 164, 96));
		frame.setBounds(100, 100, 500, 300);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Agent ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(50, 50, 232, 68);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(200, 50, 250, 50);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("CONTINUE");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton.setBackground(new Color(255, 140, 0));
		btnNewButton.setBounds(180, 150, 100, 50);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String agent_id = textField.getText();
				try
				{
					Backend backend = new Backend(Constants.databaseUserName,Constants.databasePassword,Constants.databaseName);
					List<String> agentIds = backend.admin_getAgentIDs();
					backend.closeDatabase();
					boolean foundAgentID = false;
					for(String id : agentIds)
					{
						if(id.equalsIgnoreCase(agent_id))
						{
							foundAgentID = true;
						}
					}
					if(!foundAgentID)
					{
						new AlertBoxScreen("Agent id does not exist in Database");
					}
					else
					{
						new AgentScreen(agent_id);
						frame.dispose();
					}
				} catch (Exception exception) {
					new AlertBoxScreen("Error im connecting to Database");
				}

			}
		});
	}
}
