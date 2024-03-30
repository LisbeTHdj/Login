package Login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class Registrar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtCorreo;
	private JTextField txtUsuario;
	private JTextField txtContraseña;
	private Connection connection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registrar frame = new Registrar();
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
	public Registrar() {
		setTitle("Registro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 361, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 250));
		panel.setBounds(0, 0, 345, 321);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registro");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setBounds(136, 11, 72, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(27, 42, 59, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Apellido:");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(27, 83, 59, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Usuario:");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(27, 152, 59, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Contraseña:");
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(27, 187, 72, 14);
		panel.add(lblNewLabel_4);
		
		JButton btnRegistro = new JButton("Registrarse");
		btnRegistro.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		btnRegistro.setBackground(new Color(255, 215, 0));
		btnRegistro.setForeground(new Color(0, 0, 0));
		btnRegistro.setFont(new Font("Arial", Font.BOLD, 14));
		btnRegistro.setBounds(106, 248, 126, 32);
		panel.add(btnRegistro);
		
		JLabel lblNewLabel_1_1 = new JLabel("Correo:");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(27, 118, 59, 14);
		panel.add(lblNewLabel_1_1);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(88, 36, 203, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(88, 80, 203, 20);
		panel.add(txtApellido);
		txtApellido.setColumns(10);
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(88, 115, 203, 20);
		panel.add(txtCorreo);
		txtCorreo.setColumns(10);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(88, 149, 203, 20);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtContraseña = new JTextField();
		txtContraseña.setBounds(109, 180, 182, 20);
		panel.add(txtContraseña);
		txtContraseña.setColumns(10);
		
		// Conexión a la base de datos
				try {
					String JDBC_URL = "jdbc:mysql://localhost:3306/Registro";
					String JDBC_USER = "root";
					String JDBC_PASSWORD = "12345@Abc";
					connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
					System.out.println("Conexión establecida correctamente");
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("Error al conectar a la base de datos: " + e.getMessage());
				}
				
				btnRegistro.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						 String nombre = txtNombre.getText();
			                String apellido = txtApellido.getText();
			                String correo = txtCorreo.getText();
			                String usuario = txtUsuario.getText();
			                String contraseña = txtContraseña.getText();

			                if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || usuario.isEmpty() || contraseña.isEmpty()) {
			                    JOptionPane.showMessageDialog(null, "Error: Todos los campos son obligatorios.", "Incompleto", JOptionPane.ERROR_MESSAGE);
			                    return;
			                }

			                try {
			                    String sql = "INSERT INTO Usuarios (Nombre, Apellido, Correo, Usuario, Contraseña) VALUES (?, ?, ?, ?, ?)";
			                    PreparedStatement statement = connection.prepareStatement(sql);
			                    statement.setString(1, nombre);
			                    statement.setString(2, apellido);
			                    statement.setString(3, correo);
			                    statement.setString(4, usuario);
			                    statement.setString(5, contraseña);
			                    int filasInsertadas = statement.executeUpdate();
			                    if (filasInsertadas > 0) {
			                        JOptionPane.showMessageDialog(null, "Usuario registrado correctamente.", "Completado", JOptionPane.INFORMATION_MESSAGE);
			                        dispose();
			                        Logeo logeoFrame = new Logeo();
			                        logeoFrame.setVisible(true);
			                        } else {
			                        JOptionPane.showMessageDialog(null, "Error al registrar usuario.", "Error", JOptionPane.ERROR_MESSAGE);
			                    }
			                } catch (SQLException ex) {
			                    ex.printStackTrace();
			                    JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta SQL: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			                }
					}
				});	

	}

}
