import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddPropertyAgentScreen {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	JComboBox comboBox;
	String agent_id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPropertyAgentScreen window = new AddPropertyAgentScreen("1901066");
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
	public AddPropertyAgentScreen(String agent_id) {
		this.agent_id = agent_id;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(64, 224, 208));
		frame.setBounds(100, 100, 878, 528);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Property ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(203, 97, 158, 37);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblAgentId = new JLabel("Agent ID");
		lblAgentId.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgentId.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAgentId.setBounds(203, 155, 158, 37);
		frame.getContentPane().add(lblAgentId);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDate.setBounds(203, 222, 158, 37);
		frame.getContentPane().add(lblDate);
		
		JLabel lblPropertyType = new JLabel("Property Type");
		lblPropertyType.setHorizontalAlignment(SwingConstants.CENTER);
		lblPropertyType.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPropertyType.setBounds(203, 286, 158, 37);
		frame.getContentPane().add(lblPropertyType);
		
		JLabel lblBuyersName = new JLabel("Buyer's Name");
		lblBuyersName.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuyersName.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblBuyersName.setBounds(203, 340, 158, 37);
		frame.getContentPane().add(lblBuyersName);
		
		textField = new JTextField();
		textField.setBounds(431, 97, 158, 37);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(431, 155, 158, 37);
		frame.getContentPane().add(textField_1);
		textField_1.setText(agent_id);
		textField_1.setEditable(false);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(431, 222, 158, 37);
		frame.getContentPane().add(textField_2);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(431, 340, 158, 37);
		frame.getContentPane().add(textField_4);
		
		JLabel lblNewLabel_1 = new JLabel("Add Property");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(313, 10, 234, 73);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String property_id = textField.getText();
				String date = textField_2.getText();
				String property_type = (String) comboBox.getItemAt(comboBox.getSelectedIndex());
				String buyer_name = textField_4.getText();
//				System.out.println("property id "+property_id);
//				System.out.println("data "+date);
//				System.out.println("property type "+property_type);
//				System.out.println("buyer name "+buyer_name);

				try
				{
					Backend backend =  new Backend(Constants.databaseUserName,Constants.databasePassword,Constants.databaseName);
					boolean additionSuccessful = backend.agent_addProperty(property_id,agent_id,date,property_type,buyer_name);
					backend.closeDatabase();
					if(additionSuccessful)
					{
						new AlertBoxScreen("Added new property to agent profile successfully");
					}
					else
					{
						new AlertBoxScreen("Error in adding new property to agent profile, check input again");
					}
				}
				catch (Exception exception)
				{
					new AlertBoxScreen("Error in connecting to Database");
				}



			}
		});
		btnNewButton.setBackground(new Color(169, 169, 169));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnNewButton.setBounds(358, 413, 145, 44);
		frame.getContentPane().add(btnNewButton);
		
		comboBox = new JComboBox();
		comboBox.setToolTipText("");
		comboBox.setEditable(true);
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 15));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"--select--", "sell", "rent"}));
		comboBox.setSelectedIndex(0);
		comboBox.setMaximumRowCount(3);
		comboBox.setBounds(431, 286, 158, 37);
		frame.getContentPane().add(comboBox);
	}
}
