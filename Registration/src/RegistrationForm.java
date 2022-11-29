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
import java.awt.event.ActionEvent;

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
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrationForm frame = new RegistrationForm();
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
		setBounds(100, 100, 675, 732);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(62, 80, 96, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAddress.setBounds(62, 181, 96, 26);
		contentPane.add(lblAddress);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGender.setBounds(62, 288, 96, 26);
		contentPane.add(lblGender);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAge.setBounds(62, 352, 96, 26);
		contentPane.add(lblAge);
		
		JLabel lblMobileNo = new JLabel("Mobile No. :");
		lblMobileNo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMobileNo.setBounds(62, 415, 96, 26);
		contentPane.add(lblMobileNo);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmail.setBounds(62, 467, 96, 26);
		contentPane.add(lblEmail);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtName.setBounds(216, 86, 184, 19);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JTextArea txtAddr = new JTextArea();
		txtAddr.setWrapStyleWord(true);
		txtAddr.setLineWrap(true);
		txtAddr.setFont(new Font("Monospaced", Font.BOLD, 13));
		txtAddr.setBounds(216, 184, 283, 73);
		contentPane.add(txtAddr);
		
		JRadioButton rbMale = new JRadioButton("Male");
		rbMale.setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonGroup.add(rbMale);
		rbMale.setBounds(209, 293, 103, 21);
		contentPane.add(rbMale);
		
		JRadioButton rbFemale = new JRadioButton("Female");
		rbFemale.setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonGroup.add(rbFemale);
		rbFemale.setBounds(364, 293, 103, 21);
		contentPane.add(rbFemale);
		
		txtAge = new JTextField();
		txtAge.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtAge.setBounds(216, 352, 184, 19);
		contentPane.add(txtAge);
		txtAge.setColumns(10);
		
		txtMob = new JTextField();
		txtMob.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtMob.setBounds(216, 421, 184, 19);
		contentPane.add(txtMob);
		txtMob.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtEmail.setBounds(216, 473, 184, 19);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JButton btnValidate = new JButton("Register");
		btnValidate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnValidate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/users","root","admin");
					String query="insert into registration values(?,?,?,?,?,?)";
					PreparedStatement ps=con.prepareStatement(query);
					ps.setString(1, txtName.getText());
					ps.setString(2, txtAddr.getText());
					if(rbMale.isSelected()) 
						ps.setString(3, rbMale.getText());
					else
						ps.setString(3, rbFemale.getText());
					ps.setInt(4, Integer.parseInt(txtAge.getText()));
					ps.setString(5, txtMob.getText());
					ps.setString(6, txtEmail.getText());
					
					
					int i=ps.executeUpdate();
					JOptionPane.showMessageDialog(btnValidate, i+"Record added successfully!");
					ps.close();
					con.close();
					
					} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnValidate.setBounds(112, 608, 96, 26);
		contentPane.add(btnValidate);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtName.setText("");
				txtAddr.setText("");
				txtAge.setText("");
				txtMob.setText("");
				txtEmail.setText("");
				buttonGroup.clearSelection();
			}
		});
		btnReset.setBounds(494, 608, 96, 26);
		contentPane.add(btnReset);
		
		JLabel lblNewLabel_1 = new JLabel("Student Registration Form");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(216, 27, 261, 31);
		contentPane.add(lblNewLabel_1);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnSearch.setBounds(245, 608, 96, 26);
		contentPane.add(btnSearch);
		
		JLabel lblNewLabel_2 = new JLabel("Student ID:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(62, 134, 96, 13);
		contentPane.add(lblNewLabel_2);
		
		txtStudentID = new JTextField();
		txtStudentID.setBounds(216, 133, 184, 19);
		contentPane.add(txtStudentID);
		txtStudentID.setColumns(10);
		
		JButton btnNewButton = new JButton("Update Data");
		btnNewButton.setBounds(371, 608, 96, 25);
		contentPane.add(btnNewButton);
	}
}
