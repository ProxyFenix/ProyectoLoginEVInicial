package paqueteLogin;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginClase {

	private JFrame frame;
	private JPasswordField campoPasswd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginClase window = new LoginClase();
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
	public LoginClase() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel usuarioLabel = new JLabel("USUARIO:");
		usuarioLabel.setBounds(26, 34, 113, 28);
		usuarioLabel.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(usuarioLabel);
		
		
		JLabel passwordLabel = new JLabel("CONTRASE\u00D1A:");
		passwordLabel.setHorizontalAlignment(SwingConstants.LEFT);
		passwordLabel.setBounds(26, 119, 113, 28);
		panel.add(passwordLabel);
		
		campoPasswd = new JPasswordField();
		campoPasswd.setBounds(26, 158, 128, 20);
		panel.add(campoPasswd);
		
		final JTextPane usuarioText = new JTextPane();
		usuarioText.setBounds(26, 71, 113, 20);
		panel.add(usuarioText);
		
		JButton botonLogin = new JButton("Logeate porfa");
		botonLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Cogemos los datos de los campos
				String uname = usuarioText.getText();
				String pad = campoPasswd.getText();
				
				//Conectamos la base
				try {
					Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/evinicial","root","123456");
					PreparedStatement st = (PreparedStatement) connection.prepareStatement("Select usuarios, password from evinicial.usuarios where usuarios=? and password=?");
					//Hacemos que tengan que ser iguales los campos
					st.setString(1, uname);
					st.setString(2, pad);
					ResultSet rs = st.executeQuery();
					//Ejecutamos y comprobamos
					if (rs.next()) {
						System.out.println("Enhorabuena, estás mamadisimo");
					}
					else {
						System.out.println("Te mamaste vaquero...");
					}
				} catch (SQLException sqlException) {
					sqlException.printStackTrace();
				}
									
			}
		});
		
		botonLogin.setBounds(163, 227, 123, 23);
		panel.add(botonLogin);
		
		
		

		
		
		//ImagenPokachu, Pokachu
		try {
			String path = "https://i.gyazo.com/e0e0e7278d3d827a077073b734d3493c.png";
			URL url = new URL(path);
			BufferedImage image = ImageIO.read(url);			
			JLabel imagenPokachu = new JLabel();			
			imagenPokachu.setBounds(240, 41, 169, 154);
			Image dimg = image.getScaledInstance(imagenPokachu.getWidth(), imagenPokachu.getHeight(),Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(dimg);
			imagenPokachu.setIcon(imageIcon);
			panel.add(imagenPokachu);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}
}
