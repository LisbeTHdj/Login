package Login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 637, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 235));
		panel.setBounds(0, 0, 621, 315);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Clientes Registrados");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setBounds(246, 11, 147, 14);
		panel.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 51, 588, 188);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Arial", Font.PLAIN, 11));
		

		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.setBackground(new Color(255, 215, 0));
		btnNuevo.setFont(new Font("Arial", Font.BOLD, 13));
		btnNuevo.setBounds(39, 265, 108, 39);
		panel.add(btnNuevo);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setFont(new Font("Arial", Font.BOLD, 13));
		btnActualizar.setBackground(new Color(255, 215, 0));
		btnActualizar.setBounds(168, 265, 129, 39);
		panel.add(btnActualizar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Arial", Font.BOLD, 13));
		btnEliminar.setBackground(new Color(255, 215, 0));
		btnEliminar.setBounds(317, 265, 108, 39);
		panel.add(btnEliminar);
		
		JButton btnCerrarSeccion = new JButton("Cerrar Seccion");
		btnCerrarSeccion.setFont(new Font("Arial", Font.BOLD, 13));
		btnCerrarSeccion.setBackground(new Color(255, 215, 0));
		btnCerrarSeccion.setBounds(447, 265, 150, 39);
		panel.add(btnCerrarSeccion);
		
		   // Cargar datos de la tabla al abrir la ventana
        cargarDatosTabla();

        // ActionListener para el botón "Nuevo"
        btnNuevo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del nuevo usuario:", "Nuevo Usuario", JOptionPane.QUESTION_MESSAGE);
                 String apellido = JOptionPane.showInputDialog(null, "Ingrese el apellido del nuevo usuario:", "Nuevo Usuario", JOptionPane.QUESTION_MESSAGE);
                 String correo = JOptionPane.showInputDialog(null, "Ingrese el correo del nuevo usuario:", "Nuevo Usuario", JOptionPane.QUESTION_MESSAGE);
                 String usuario = JOptionPane.showInputDialog(null, "Ingrese el nombre de usuario del nuevo usuario:", "Nuevo Usuario", JOptionPane.QUESTION_MESSAGE);
                 String contraseña = JOptionPane.showInputDialog(null, "Ingrese la contraseña del nuevo usuario:", "Nuevo Usuario", JOptionPane.QUESTION_MESSAGE);

                 if (nombre != null && apellido != null && correo != null && usuario != null && contraseña != null) {
                     try {
                         String JDBC_URL = "jdbc:mysql://localhost:3306/Registro";
                         String JDBC_USER = "root";
                         String JDBC_PASSWORD = "12345@Abc";
                         Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

                         String sql = "INSERT INTO Usuarios (Nombre, Apellido, Correo, Usuario, Contraseña) VALUES (?, ?, ?, ?, ?)";
                         PreparedStatement statement = connection.prepareStatement(sql);
                         statement.setString(1, nombre);
                         statement.setString(2, apellido);
                         statement.setString(3, correo);
                         statement.setString(4, usuario);
                         statement.setString(5, contraseña);

                         int rowsInserted = statement.executeUpdate();
                         if (rowsInserted > 0) {
                             JOptionPane.showMessageDialog(null, "Nuevo usuario agregado correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                             cargarDatosTabla(); // Actualizar la tabla después de agregar un nuevo usuario
                         } else {
                             JOptionPane.showMessageDialog(null, "No se pudo agregar el nuevo usuario", "Error", JOptionPane.ERROR_MESSAGE);
                         }

                         connection.close(); // Cerrar la conexión
                     } catch (SQLException ex) {
                         ex.printStackTrace();
                         JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                     }
                 } else {
                     JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos para agregar un nuevo usuario", "Advertencia", JOptionPane.WARNING_MESSAGE);
                 }
            }
        });

        // ActionListener para el botón "Actualizar"
        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	  cargarDatosTabla(); 
            	  JOptionPane.showMessageDialog(null, "Actualización completa", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }
            }
        );

        // ActionListener para el botón "Eliminar"
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	// Obtener la fila seleccionada
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    // Obtener el ID del usuario a eliminar (suponiendo que el ID está en la primera columna de la tabla)
                    Object idObject = table.getValueAt(selectedRow, 0);
                    if (idObject instanceof Integer) {
                        int id = (Integer) idObject;
                        try {
                            String JDBC_URL = "jdbc:mysql://localhost:3306/Registro";
                            String JDBC_USER = "root";
                            String JDBC_PASSWORD = "12345@Abc";
                            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

                            String sql = "DELETE FROM Usuarios WHERE id = ?";
                            PreparedStatement statement = connection.prepareStatement(sql);
                            statement.setInt(1, id);

                            int rowsDeleted = statement.executeUpdate();
                            if (rowsDeleted > 0) {
                                JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                                cargarDatosTabla(); // Actualizar la tabla después de eliminar el usuario
                            } else {
                                JOptionPane.showMessageDialog(null, "No se pudo eliminar el usuario", "Error", JOptionPane.ERROR_MESSAGE);
                            }

                            connection.close(); // Cerrar la conexión
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo obtener el ID del usuario", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para eliminar", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // ActionListener para el botón "Cerrar Sesión"
        btnCerrarSeccion.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent e) {
            	
            	  // Cerrar la ventana actual
                dispose();

                // Mostrar la ventana de inicio de sesión
                Logeo loginFrame = new Logeo();
                loginFrame.setVisible(true);
				
			}

			
        });
	}

	private void cargarDatosTabla() {
		  try {
		        String JDBC_URL = "jdbc:mysql://localhost:3306/Registro";
		        String JDBC_USER = "root";
		        String JDBC_PASSWORD = "12345@Abc";
		        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

		        String sql = "SELECT ID, Nombre, Apellido, Correo, Usuario FROM Usuarios";
		        PreparedStatement statement = connection.prepareStatement(sql);
		        ResultSet result = statement.executeQuery();

		        // Crear un modelo de tabla para almacenar los resultados
		        DefaultTableModel model = new DefaultTableModel();
		        model.addColumn("ID");
		        model.addColumn("Nombre");
		        model.addColumn("Apellido");
		        model.addColumn("Correo");
		        model.addColumn("Usuario");

		        while (result.next()) {
		            // Obtener los datos de cada fila y agregarlos al modelo de la tabla
		            Object[] row = new Object[5]; // Solo 5 columnas: ID, Nombre, Apellido, Correo, Usuario
		            row[0] = result.getInt("id");
		            row[1] = result.getString("Nombre");
		            row[2] = result.getString("Apellido");
		            row[3] = result.getString("Correo");
		            row[4] = result.getString("Usuario");
		            model.addRow(row);
		        }

		        // Asignar el modelo de la tabla al JTable
		        table.setModel(model);
		        connection.close(); // Cerrar la conexión
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		        JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	
        }
	}}
		
		
	
