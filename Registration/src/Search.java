import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Search extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtName;
	private JTextField txtEmail;
	private JTextField txtGender;
	private JTextField txtMobile;
	private JTextField txtAge;
	private JLabel lblNewLabel_6;

	/**
	 * Launch the application.
	 */
	static Search frame = new Search();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Search() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 541, 555);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Student ID:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(78, 118, 95, 17);
		contentPane.add(lblNewLabel);
		
		txtID = new JTextField();
		txtID.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtID.setBounds(229, 118, 96, 19);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/users","root","admin");
					PreparedStatement pst;
					ResultSet rs;
					pst=con.prepareStatement("select name, email, mobile, age, gender from registration where id=?");
					int id = Integer.parseInt(txtID.getText());
					
					pst.setInt(1, id) ;
					ResultSet rs1 = pst.executeQuery();
					
					
					if(rs1.next()==false) {
						JOptionPane.showMessageDialog(btnNewButton, "sorry record not found!");
						txtName.setText("");
						txtEmail.setText("");
						txtMobile.setText("");
						txtAge.setText("");
						txtGender.setText("");
						txtID.requestFocus();
					}
					else {
						
						txtName.setText(rs1.getString("name"));
						txtEmail.setText(rs1.getString("email"));
						txtGender.setText(rs1.getString("gender"));
						txtMobile.setText(rs1.getString("mobile"));
						txtAge.setText(rs1.getString("age"));
						
					}
					
					
					
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(205, 177, 85, 21);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Name:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(78, 227, 95, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Email:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(78, 275, 95, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Phone No.:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(78, 374, 95, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Gender:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(78, 329, 95, 13);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Age:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setBounds(78, 418, 95, 19);
		contentPane.add(lblNewLabel_5);
		
		txtName = new JTextField();
		txtName.setBounds(229, 226, 248, 19);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(229, 274, 248, 19);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtGender = new JTextField();
		txtGender.setBounds(229, 328, 114, 19);
		contentPane.add(txtGender);
		txtGender.setColumns(10);
		
		txtMobile = new JTextField();
		txtMobile.setBounds(229, 373, 166, 19);
		contentPane.add(txtMobile);
		txtMobile.setColumns(10);
		
		txtAge = new JTextField();
		txtAge.setBounds(229, 420, 114, 19);
		contentPane.add(txtAge);
		txtAge.setColumns(10);
		
		lblNewLabel_6 = new JLabel("Search Data");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel_6.setBounds(193, 38, 138, 32);
		contentPane.add(lblNewLabel_6);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				RegistrationForm regForm = new RegistrationForm();
				regForm.setVisible(true);
			}
		});
		btnHome.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnHome.setBounds(205, 473, 85, 21);
		contentPane.add(btnHome);
	}
}
