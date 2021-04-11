import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerScreen {

	public JFrame frame;
	private JTextField txtCustomer;
	private JTextField txtFindAHome;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	JComboBox cb;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerScreen window = new CustomerScreen();
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
	public CustomerScreen() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.YELLOW);
		frame.setBounds(100, 100, 1112, 824);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtCustomer = new JTextField();
		txtCustomer.setHorizontalAlignment(SwingConstants.CENTER);
		txtCustomer.setBackground(Color.YELLOW);
		txtCustomer.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 30));
		txtCustomer.setText("CUSTOMER");
		txtCustomer.setBounds(326, 59, 415, 48);
		frame.getContentPane().add(txtCustomer);
		txtCustomer.setColumns(10);
		
		txtFindAHome = new JTextField();
		txtFindAHome.setFont(new Font("Ink Free", Font.BOLD | Font.ITALIC, 25));
		txtFindAHome.setForeground(Color.RED);
		txtFindAHome.setBackground(Color.YELLOW);
		txtFindAHome.setText("Find a home you'll love!");
		txtFindAHome.setBounds(581, 135, 297, 35);
		frame.getContentPane().add(txtFindAHome);
		txtFindAHome.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Street");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setBounds(273, 268, 164, 74);
		frame.getContentPane().add(lblNewLabel);


		try {
			String streetNameList[] = new Backend(Constants.databaseUserName,Constants.databasePassword,Constants.databaseName).customer_getStreetNames();
			cb = new JComboBox(streetNameList);
			cb.setBounds(533, 286, 249, 40);
			frame.getContentPane().add(cb);
		} catch (Exception exception) {
			new AlertBoxScreen("Error in connecting to Database");
		}


//		textField = new JTextField();
//		textField.setBounds(533, 286, 249, 40);
//		frame.getContentPane().add(textField);
//		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Number Of Bedrooms");
		lblNewLabel_1.setForeground(Color.GRAY);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNewLabel_1.setBounds(151, 367, 340, 47);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(533, 372, 249, 40);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Type Of Property");
		lblNewLabel_2.setForeground(Color.GRAY);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNewLabel_2.setBounds(188, 452, 304, 48);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(533, 466, 249, 40);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Price Range");
		lblNewLabel_3.setForeground(Color.GRAY);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNewLabel_3.setBounds(228, 548, 237, 35);
		frame.getContentPane().add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("SEARCH");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String street = (String) cb.getItemAt(cb.getSelectedIndex());
				if(street.equalsIgnoreCase("#Search All Street"))
				{
					street = "";
				}
				String no_of_bedrooms = textField_1.getText();
				String type_of_property = textField_2.getText();
				String min_price = textField_3.getText();
				String max_price = textField_4.getText();
//				System.out.println("Street : "+street);
//				System.out.println("No of bedrooms : "+no_of_bedrooms);
//				System.out.println("Type of property : "+type_of_property);
//				System.out.println("Minimum price : "+min_price);
//				System.out.println("Maximum price : "+max_price);
				try
				{
					Backend backend = new Backend(Constants.databaseUserName,Constants.databasePassword,Constants.databaseName);
					backend.customer_searchProperties(street,no_of_bedrooms,type_of_property,min_price,max_price);
					backend.closeDatabase();
				}
				catch (Exception exception)
				{
					new AlertBoxScreen("Error in connecting to Database");
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setBounds(431, 663, 200, 45);
		frame.getContentPane().add(btnNewButton);
		
		textField_3 = new JTextField();
		textField_3.setBounds(534, 555, 110, 40);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(672, 555, 110, 40);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Min");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_4.setBounds(571, 605, 45, 13);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Max");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_5.setBounds(704, 605, 45, 13);
		frame.getContentPane().add(lblNewLabel_5);
	}
}
