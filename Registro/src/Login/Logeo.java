package Login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.sql.Connection;

public class Logeo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField jpassClave;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Logeo frame = new Logeo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Logeo() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 363, 244);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 250));
		panel.setBounds(0, 0, 347, 212);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 17));
		lblNewLabel.setBounds(146, 11, 61, 21);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario:");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(31, 64, 61, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Contraseña:");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(31, 89, 76, 14);
		panel.add(lblNewLabel_2);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setFont(new Font("Arial", Font.BOLD, 14));
		btnIngresar.setBackground(new Color(255, 215, 0));
		btnIngresar.addActionListener(new ActionListener() {
			//Logica o Funcionalidad de boton ingresar
			public void actionPerformed(ActionEvent e) {
				 String usuario = txtUsuario.getText();
			        String contraseña = new String(jpassClave.getPassword());

			        if (usuario.isEmpty() || contraseña.isEmpty()) {
			            JOptionPane.showMessageDialog(null, "Error: Todos los campos son obligatorios.", "Incompleto", JOptionPane.ERROR_MESSAGE);
			            return;
			        }

			        try {
			            String JDBC_URL = "jdbc:mysql://localhost:3306/Registro";
			            String JDBC_USER = "root";
			            String JDBC_PASSWORD = "12345@Abc";
			            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

			            String sql = "SELECT * FROM Usuarios WHERE Usuario = ? AND Contraseña = ?";
			            PreparedStatement statement = connection.prepareStatement(sql);
			            statement.setString(1, usuario);
			            statement.setString(2, contraseña);
			            ResultSet result = statement.executeQuery();

			            if (result.next()) {
			                JOptionPane.showMessageDialog(null, "Bienvenido, " + usuario + "!", "Acceso permitido", JOptionPane.INFORMATION_MESSAGE);
			                dispose(); // se Cierra ventana al ingresar
			                Principal principalFrame = new Principal();
			                principalFrame.setVisible(true);
			            } else {
			                JOptionPane.showMessageDialog(null, "Error: Usuario o contraseña incorrectos.", "Acceso denegado", JOptionPane.ERROR_MESSAGE);
			            }

			        } catch (SQLException ex) {
			            ex.printStackTrace();
			            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			        }}
		});
		btnIngresar.setBounds(46, 161, 109, 23);
		panel.add(btnIngresar);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setFont(new Font("Arial", Font.BOLD, 14));
		btnRegistrar.setBackground(new Color(255, 215, 0));
		btnRegistrar.setBounds(187, 161, 109, 23);
		panel.add(btnRegistrar);
		
		//Logica o funcionalidad de boton registrar
		btnRegistrar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        dispose(); // Cierra la ventana actual
		        Registrar registrarFrame = new Registrar();
		        registrarFrame.setVisible(true);
		    }
		});
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(94, 61, 202, 20);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		jpassClave = new JPasswordField();
		jpassClave.setBounds(104, 86, 192, 20);
		panel.add(jpassClave);
		
		
	}
}
