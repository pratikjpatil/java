import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.UIManager;

public class RegistrationForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtAge;
	private JTextField txtMob;
	private JTextField txtEmail;
	private JTextField txtStudentID;

	/**
	 * Launch the application.
	
	 */
	
	static RegistrationForm frame = new RegistrationForm();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//RegistrationForm frame = new RegistrationForm();
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
	public RegistrationForm() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\prati\\Downloads\\userImg.png"));
		setTitle("Registration Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 732);
		contentPane = new JPanel();
		contentPane.setForeground(UIManager.getColor("Button.light"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(138, 82, 96, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblAddress = new JLabel("Address :");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAddress.setBounds(138, 193, 96, 26);
		contentPane.add(lblAddress);
		
		JLabel lblGender = new JLabel("Gender :");
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGender.setBounds(138, 290, 96, 26);
		contentPane.add(lblGender);
		
		JLabel lblAge = new JLabel("Age :");
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAge.setBounds(138, 348, 96, 26);
		contentPane.add(lblAge);
		
		JLabel lblMobileNo = new JLabel("Mobile No. :");
		lblMobileNo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMobileNo.setBounds(138, 408, 96, 26);
		contentPane.add(lblMobileNo);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmail.setBounds(138, 469, 96, 26);
		contentPane.add(lblEmail);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtName.setBounds(293, 87, 184, 19);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JTextArea txtAddr = new JTextArea();
		txtAddr.setWrapStyleWord(true);
		txtAddr.setLineWrap(true);
		txtAddr.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtAddr.setBounds(293, 186, 283, 73);
		contentPane.add(txtAddr);
		
		JRadioButton rbMale = new JRadioButton("Male");
		rbMale.setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonGroup.add(rbMale);
		rbMale.setBounds(293, 293, 103, 21);
		contentPane.add(rbMale);
		
		JRadioButton rbFemale = new JRadioButton("Female");
		rbFemale.setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonGroup.add(rbFemale);
		rbFemale.setBounds(448, 293, 103, 21);
		contentPane.add(rbFemale);
		
		txtAge = new JTextField();
		txtAge.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtAge.setBounds(293, 353, 184, 19);
		contentPane.add(txtAge);
		txtAge.setColumns(10);
		
		txtMob = new JTextField();
		txtMob.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtMob.setBounds(293, 413, 184, 19);
		contentPane.add(txtMob);
		txtMob.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtEmail.setBounds(293, 474, 184, 19);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBackground(UIManager.getColor("Button.background"));
		btnRegister.setForeground(UIManager.getColor("Button.foreground"));
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/users","root","admin");
					String query="insert into registration values(?,?,?,?,?,?,?)";
					PreparedStatement ps=con.prepareStatement(query);
					ps.setString(1, txtName.getText());
					ps.setInt(2, Integer.parseInt(txtStudentID.getText()));
					ps.setString(3, txtAddr.getText());
					if(rbMale.isSelected()) 
						ps.setString(4, rbMale.getText());
					else
						ps.setString(4, rbFemale.getText());
					ps.setInt(5, Integer.parseInt(txtAge.getText()));
					ps.setString(6, txtMob.getText());
					ps.setString(7, txtEmail.getText());
					
					
					int i=ps.executeUpdate();
					JOptionPane.showMessageDialog(btnRegister, i+"Record added successfully!");
					ps.close();
					con.close();
					
					} catch (Exception e1) {
					// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(btnRegister, "Record already exists!");
					e1.printStackTrace();
				}
			}
		});
		btnRegister.setBounds(154, 552, 96, 26);
		contentPane.add(btnRegister);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setForeground(UIManager.getColor("Button.foreground"));
		btnReset.setBackground(UIManager.getColor("Button.background"));
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtName.setText("");
				txtStudentID.setText("");
				txtAddr.setText("");
				txtAge.setText("");
				txtMob.setText("");
				txtEmail.setText("");
				buttonGroup.clearSelection();
			}
		});
		btnReset.setBounds(403, 552, 96, 26);
		contentPane.add(btnReset);
		
		JLabel lblNewLabel_1 = new JLabel("Student Registration Form");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(216, 27, 261, 31);
		contentPane.add(lblNewLabel_1);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setForeground(new Color(255, 255, 255));
		btnSearch.setBackground(new Color(128, 128, 128));
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				Search srch = new Search();
				srch.setVisible(true);
			}
		});
		btnSearch.setBounds(433, 623, 96, 26);
		contentPane.add(btnSearch);
		
		JLabel lblNewLabel_2 = new JLabel("Student ID :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(138, 136, 96, 13);
		contentPane.add(lblNewLabel_2);
		
		txtStudentID = new JTextField();
		txtStudentID.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtStudentID.setBounds(293, 134, 184, 19);
		contentPane.add(txtStudentID);
		txtStudentID.setColumns(10);
		
		JButton btnNewButton = new JButton("Update Data");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(128, 128, 128));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(110, 624, 96, 25);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("+91");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(257, 409, 26, 26);
		contentPane.add(lblNewLabel_3);
		
		JButton btnList = new JButton("Show list");
		btnList.setForeground(new Color(255, 255, 255));
		btnList.setBackground(new Color(128, 128, 128));
		btnList.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				String sql="select * from registration";
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users","root","admin");
					Statement st = conn.createStatement();
					ResultSet rs = st.executeQuery(sql);
					while(rs.next()) {
						System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6));
					}
				}
				catch(SQLException ex) {
					ex.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
		
			}});
		
		btnList.setBounds(272, 623, 96, 26);
		contentPane.add(btnList);
	
}
}
