package ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.jtattoo.plaf.smart.SmartLookAndFeel;

import dao.SingletonDao;
import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;
import de.javasoft.util.java2d.Synthetica2DUtils;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Properties;
import java.awt.event.ActionEvent;

public class auth extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 	Properties props = new Properties();
			            props.put("logoString", ""); 
			            SmartLookAndFeel.setCurrentTheme(props);
			            UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());      
			            auth frame = new auth();

					Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
					frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		}); 
	}

	/**
	 * Create the frame.
	 */
	public auth() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 391, 196);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("username");
		lblUsername.setBounds(33, 37, 65, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(33, 79, 65, 14);
		contentPane.add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(122, 30, 176, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(122, 72, 176, 28);
		contentPane.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("admin") && passwordField.getText().equals("admin")) {
					//auth.this.dispatchEvent(new WindowEvent(auth.this, WindowEvent.WINDOW_CLOSING)); 
					
							try {
								application frame = new application();  
								setVisible(false); //you can't see me!
								dispose();
							} catch (Exception e1) { 
								e1.printStackTrace();
							}
					
				}else{
					JOptionPane.showMessageDialog(auth.this, "login pass incoreecte");
				}
			}
		});
		btnLogin.setBounds(122, 111, 89, 28);
		contentPane.add(btnLogin);
	}

}
