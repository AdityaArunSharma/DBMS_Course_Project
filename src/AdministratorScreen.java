import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdministratorScreen {

	private JFrame frame;


	/**
	 * Create the application.
	 */
	public AdministratorScreen() {
		initialize();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new AdministratorScreen();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 105, 180));
		frame.setBounds(100, 100, 1020, 762);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Administrator");
		lblNewLabel.setFont(new Font("Ink Free", Font.BOLD, 50));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(334, 33, 327, 96);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Add property");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(148, 0, 211));
		btnNewButton.setBounds(361, 179, 280, 50);
		frame.getContentPane().add(btnNewButton);

		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new AddPropertyScreen();
			}
		});


		
		JButton btnNewButton_1 = new JButton("Add Agent");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new AddAgentScreen();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(148, 0, 211));
		btnNewButton_1.setBounds(361, 270, 280, 50);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Agent Sales Report");
		btnNewButton_2.setBackground(new Color(148, 0, 211));
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_2.setBounds(361, 361, 280, 50);
		frame.getContentPane().add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new SelectAgentScreen();
			}
		});
		
		JButton btnNewButton_3 = new JButton("Available Properties For Rent");
		btnNewButton_3.setBackground(new Color(148, 0, 211));
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_3.setBounds(361, 455, 280, 50);
		frame.getContentPane().add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					Backend backend = new Backend(Constants.databaseUserName,Constants.databasePassword,Constants.databaseName);
					backend.admin_availablePropertiesForRent();
					backend.closeDatabase();
				}
				catch (Exception exception)
				{
					new AlertBoxScreen("Error in connecting to Database");
				}
			}
		});
		
		JButton btnNewButton_4 = new JButton("Available Properties For Sale");
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_4.setForeground(new Color(255, 255, 255));
		btnNewButton_4.setBackground(new Color(148, 0, 211));
		btnNewButton_4.setBounds(361, 547, 280, 50);
		frame.getContentPane().add(btnNewButton_4);
		btnNewButton_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					Backend backend = new Backend(Constants.databaseUserName,Constants.databasePassword,Constants.databaseName);
					backend.admin_availablePropertiesForSell();
					backend.closeDatabase();
				}
				catch (Exception exception)
				{
					new AlertBoxScreen("Error in connecting to DataBase");
				}
			}
		});
		
		JButton btnNewButton_5 = new JButton("Add Owner");
		btnNewButton_5.setBackground(new Color(148, 0, 211));
		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_5.setForeground(new Color(255, 255, 255));
		btnNewButton_5.setBounds(361, 638, 280, 50);
		frame.getContentPane().add(btnNewButton_5);
		btnNewButton_5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new AddOwnerScreen();
			}
		});
	}
}
