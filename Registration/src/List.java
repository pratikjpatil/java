import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;


import javax.swing.JTable;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

public class List extends JFrame {

	private JPanel contentPane;
	private JTable tblData;

	/**
	 * Launch the application.
	 */
	static List frame = new List();
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
	public List() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 856, 637);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Show List");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/users","root","admin");
					Statement st = con.createStatement();
					String query="select * from registration";
					ResultSet rs=st.executeQuery(query);
					ResultSetMetaData rsmd=rs.getMetaData();
					DefaultTableModel model=(DefaultTableModel) tblData.getModel();
							
					int cols=rsmd.getColumnCount();
					String colName[] = new String[cols];
						
						for(int i=0; i<cols; i++)
							colName[i] = rsmd.getColumnName(i+1);
						model.setColumnIdentifiers(colName);
						String id, name, address,gender,age,mobile,email;
						while(rs.next()) {
							id=rs.getString(1);
							name=rs.getString(2);
							address=rs.getString(3);
							gender=rs.getString(4);
							age=rs.getString(5);
							mobile=rs.getString(6);
							email=rs.getString(7);
							String row[] = {id, name, address,gender,age,mobile,email};
							model.addRow(row);
						}
						st.close();
						con.close();
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(675, 71, 96, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 104, 770, 445);
		contentPane.add(scrollPane);
		
		tblData = new JTable();
		tblData.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		tblData.setFillsViewportHeight(true);
		tblData.setSize(new Dimension(1, 1));
		tblData.setAutoCreateRowSorter(true);
		tblData.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollPane.setViewportView(tblData);
		
		JButton btnNewButton_1 = new JButton("Home");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrationForm reg = new RegistrationForm();
				frame.setVisible(false);
				frame.dispose();
				reg.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton_1.setBounds(39, 71, 74, 21);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Registered Students List");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(326, 22, 211, 29);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_2 = new JButton("Clear");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) tblData.getModel();
				model.setRowCount(0);
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_2.setBounds(724, 569, 85, 21);
		contentPane.add(btnNewButton_2);
	}
}
