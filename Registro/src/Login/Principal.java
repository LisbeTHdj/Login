package Login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.SwingConstants;

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
		
		table = new JTable();
		table.setBounds(23, 51, 588, 188);
		panel.add(table);
		
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
	}
}
