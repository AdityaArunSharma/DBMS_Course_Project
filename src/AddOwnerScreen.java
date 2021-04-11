import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddOwnerScreen {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the application.
	 */
	public AddOwnerScreen() {
		initialize();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new AddOwnerScreen();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(153, 214, 255));
		frame.setBounds(100, 100, 1163, 763);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add Owner");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 55));
		lblNewLabel.setBounds(444, 39, 329, 77);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBackground(new Color(240, 240, 240));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(382, 231, 88, 22);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(563, 226, 250, 45);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Contact No.");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(359, 316, 137, 31);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(563, 315, 250, 45);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Property ID");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_3.setBounds(359, 396, 163, 40);
		frame.getContentPane().add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setBackground(new Color(255, 255, 255));
		textField_2.setBounds(563, 400, 250, 45);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String name = textField.getText();
				String contact_no = textField_1.getText();
				String property_id = textField_2.getText();
//				System.out.println("name "+name);
//				System.out.println("contact no "+contact_no);
//				System.out.println("property id "+property_id);
				try
				{
					Backend backend = new Backend(Constants.databaseUserName,Constants.databasePassword,Constants.databaseName);
					boolean additionSuccessful = backend.admin_addOwner(name,contact_no,property_id);
					backend.closeDatabase();
					if(additionSuccessful)
					{
						new AlertBoxScreen("Added new Owner Successfully");
					}
					else
					{
						new AlertBoxScreen("Error in adding new owner, check input again!");
					}
				}
				catch (Exception exception)
				{
					new AlertBoxScreen("Error in connecting to Database");
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton.setBackground(new Color(0, 0, 205));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(491, 576, 150, 70);
		frame.getContentPane().add(btnNewButton);
	}
}
