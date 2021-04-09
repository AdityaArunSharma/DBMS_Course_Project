import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JPanel;

public class UserPageScreen {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UserPageScreen window = new UserPageScreen();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public UserPageScreen() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Microsoft Himalaya", Font.ITALIC, 24));
		frame.getContentPane().setBackground(Color.CYAN);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setPreferredSize(new Dimension (100,100));
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Select User");
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.CYAN);
		panel_1.setForeground(Color.WHITE);
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("ADMIN");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
		btnNewButton.setBackground(Color.YELLOW);
		btnNewButton.setBounds(163, 49, 102, 37);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("AGENT");
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnNewButton_1.setBackground(Color.YELLOW);
		btnNewButton_1.setBounds(163, 141, 102, 37);
		panel_1.add(btnNewButton_1);




		JButton btnNewButton_2 = new JButton("CUSTOMER");
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnNewButton_2.setBackground(Color.YELLOW);
		btnNewButton_2.setBounds(163, 235, 102, 37);
		panel_1.add(btnNewButton_2);
		frame.setBounds(100, 100, 450,450 );
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("Frontend : Admin Mode Selected");
				new LoginScreen(Constants.mode_admin);
				frame.dispose();
			}
		});

		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("Frontend : Agent Mode Selected");
				new LoginScreen(Constants.mode_agent);
				frame.dispose();
			}
		});

		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("Frontend : Customer Mode Selected");
				// Call Customer Screen


				frame.dispose();
			}
		});





	}
}
