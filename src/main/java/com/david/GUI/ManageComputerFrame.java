package com.david.GUI;

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.transaction.Transactional.TxType;

import com.david.entities.Computador;
import com.david.entities.DetallePieza;
import com.david.entities.Pieza;
import com.david.hibernateMethods.CrudMethods;


import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
/**
 * Create UI for user can manage a computer like create computer with pieces, delete computer, update computer and get computers costs
 * @author David Moreno
 * @version 1.0
 * @since 1.0
 */
public class ManageComputerFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtSerie;
	private JTextField txtDescripcion;
	private JTextField txtFechaEnsamblaje;
	private JButton btnConfirmar;
	private JButton btnEliminarPieza;
	private JLabel lblPiezas;
	
	private CrudMethods crudMethods;
	private JTextField txtValor;
	private List<DetallePieza> listDetallePieza;
	private JComboBox comboBoxPiezas;
	private String searchSerial;
	private List<String> selectedPieces;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageComputerFrame frame = new ManageComputerFrame();
					frame.setLocationRelativeTo(null);
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
	public ManageComputerFrame() {
		listDetallePieza =new ArrayList<DetallePieza>();
		selectedPieces =new ArrayList<String>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 421);
		setTitle("Administración de computadoras");
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		crudMethods = new CrudMethods();
		
		JLabel lblSerie = new JLabel("Serie:");
		lblSerie.setBounds(99, 13, 46, 16);
		contentPane.add(lblSerie);
		
		JLabel lblDescipcion = new JLabel("Descipcion:");
		lblDescipcion.setBounds(67, 42, 78, 16);
		contentPane.add(lblDescipcion);
		
		JLabel lblFechaDeEnsamblaje = new JLabel("Fecha de Ensamblaje");
		lblFechaDeEnsamblaje.setBounds(12, 71, 133, 16);
		contentPane.add(lblFechaDeEnsamblaje);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setBounds(408, 13, 97, 25);
		contentPane.add(btnIngresar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(525, 13, 97, 25);
		contentPane.add(btnEliminar);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(408, 62, 97, 25);
		contentPane.add(btnActualizar);
		
		JButton btnCostos = new JButton("Costos");
		btnCostos.setBounds(525, 62, 97, 25);
		contentPane.add(btnCostos);
		
		txtSerie = new JTextField();
		txtSerie.setBounds(145, 10, 116, 22);
		contentPane.add(txtSerie);
		txtSerie.setColumns(10);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(145, 39, 116, 22);
		contentPane.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		txtFechaEnsamblaje = new JTextField();
		txtFechaEnsamblaje.setBounds(145, 68, 116, 22);
		contentPane.add(txtFechaEnsamblaje);
		txtFechaEnsamblaje.setColumns(10);
		
		Panel panel = new Panel();
		panel.setBackground(SystemColor.controlHighlight);
		panel.setBounds(10, 122, 647, 242);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblIngreseLaPiezas = new JLabel("Ingrese la piezas del computador con su valor:");
		lblIngreseLaPiezas.setBounds(12, 13, 334, 16);
		panel.add(lblIngreseLaPiezas);
		
		comboBoxPiezas = new JComboBox();
		comboBoxPiezas.setModel(new DefaultComboBoxModel(fillComboBox(comboBoxPiezas).toArray()));
			
		comboBoxPiezas.setBounds(146, 42, 134, 22);
		panel.add(comboBoxPiezas);
		
		txtValor = new JTextField();
		txtValor.setBounds(338, 42, 82, 22);
		panel.add(txtValor);
		txtValor.setColumns(10);
		
		JButton btnAniadir = new JButton("A\u00F1adir Pieza");
		btnAniadir.setBounds(432, 41, 143, 25);
		panel.add(btnAniadir);
		
		JLabel lblValor = new JLabel("Valor ($) :");
		lblValor.setBounds(338, 13, 70, 16);
		panel.add(lblValor);
		
		btnEliminarPieza = new JButton("Eliminar Pieza");
		btnEliminarPieza.setBounds(432, 75, 143, 25);
		panel.add(btnEliminarPieza);
		
		JLabel lblPiezasSeleccionadas = new JLabel("Piezas seleccionadas:");
		lblPiezasSeleccionadas.setBounds(12, 129, 134, 16);
		panel.add(lblPiezasSeleccionadas);
		
		lblPiezas = new JLabel("Ninguna");
		lblPiezas.setBounds(146, 129, 489, 16);
		panel.add(lblPiezas);
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBackground(new Color(0, 255, 0));
		btnConfirmar.setEnabled(false);
		btnConfirmar.setBounds(299, 62, 97, 25);
		contentPane.add(btnConfirmar);
		
		
		/*Event handling for buttons*/
		btnIngresar.addActionListener(new ButtonClicListener());
		btnEliminar.addActionListener(new ButtonClicListener());
		btnActualizar.addActionListener(new ButtonClicListener());
		btnCostos.addActionListener(new ButtonClicListener());
		btnAniadir.addActionListener(new ButtonClicListener());
		btnConfirmar.addActionListener(new ButtonClicListener());
		btnEliminarPieza.addActionListener(new ButtonClicListener());
	}
	/**
	 * Fill a comboBox with DB data 
	 * @param comboBox
	 * @return List<String>
	 */
	public List<String> fillComboBox(JComboBox comboBox) {
		List<String> nombrePiezas = new ArrayList<String>();
		
		for(Pieza pieza:crudMethods.getPieces())
			nombrePiezas.add(pieza.getNombrePieza());
		
		return nombrePiezas;
	}
	/**
	 * Clear the fields of this class 
	 */
	public void clearTextFields() {
		txtSerie.setText("");
		txtDescripcion.setText("");
		txtFechaEnsamblaje.setText("");
		txtValor.setText("");
		listDetallePieza.clear();
	}
	/**
	 * Verify if the textfields txtSerie, txtDescripcion and txtFechaEnsamblaje are empty
	 * @return true if the text fields are empty or false if they are not
	 */
	public boolean isTxtFieldsEmpty() {
		boolean empty =false;
		String computerSerial = txtSerie.getText().toString();
		String description = txtDescripcion.getText().toString();
		String assemblyDate = txtFechaEnsamblaje.getText().toString();
			
		if(computerSerial.equals("") && description.equals("") && assemblyDate.equals("")) {
			if(Integer.parseInt(computerSerial)<0 && Integer.parseInt(assemblyDate)<0) {
				empty = true;
			}
				
		}
					
		return empty;
	}
	/**
	 * Show the computer pieces selected
	 */
	public void showPieces() {
		String aux = "";
		for(DetallePieza dp:listDetallePieza)
			aux+=dp.getDetalleDePieza()+", ";
		
		lblPiezas.setText(aux);
	}
	/**
	 * Delete the computer pieces from the List listDetallePieza.
	 */
	public void deletePieces() {
		listDetallePieza.remove(listDetallePieza.size()-1).toString();
		showPieces();
	}
	/**
	 * Implements a ActionListener interface for handle the buttons action events
	 * @author David Moreno
	 * @version 1.0
	 * @since 1.0
	 */
	private class ButtonClicListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String command = e.getActionCommand();
			System.out.println("Botón presionado: "+command);
			
			if(command.equals("Actualizar")) {
				
				searchSerial = JOptionPane.showInputDialog("Ingrese la serie del computador a buscar");
				
				if(searchSerial != null && !searchSerial.equals("") ) {
					Computador oldComputer = crudMethods.searchComputer(Integer.parseInt(searchSerial));
					if(oldComputer != null) {
						txtSerie.setText(String.valueOf(oldComputer.getSerieComputador()));
						txtFechaEnsamblaje.setText(String.valueOf(oldComputer.getFechaEnsamblajeComputador()));
						txtDescripcion.setText(oldComputer.getDescripcionComputador());
						btnConfirmar.setEnabled(true);
					}else {
						JOptionPane.showMessageDialog(new Frame(), "El computador asociado a este serial "+searchSerial+" no existe", "Serial no válido",JOptionPane.WARNING_MESSAGE);
					}
					
				}else {
					JOptionPane.showMessageDialog(new Frame(), "Ingrese un serial válido.", "Serial no válido",JOptionPane.WARNING_MESSAGE);
				}
				
			}else if(command.equals("Ingresar")) {
				if(listDetallePieza.size() > 0 && listDetallePieza != null) {
					
					if(crudMethods.searchComputer(Integer.parseInt(txtSerie.getText().toString())) == null ) {
						crudMethods.newComputer(Integer.parseInt(txtSerie.getText().toString()),
								txtDescripcion.getText().toString(),
								Integer.parseInt(txtFechaEnsamblaje.getText().toString()),
								listDetallePieza);
						clearTextFields();
						JOptionPane.showMessageDialog(new Frame(), "Computador ingresado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
						
					}else {
						JOptionPane.showMessageDialog(new Frame(), "El computador que trata de ingresar ya existe", "No se puede ingresar el computador", JOptionPane.ERROR_MESSAGE);
					}
					
				}else {
					JOptionPane.showMessageDialog(new Frame(), "Eliga al menos una pieza", "No hay piezas seleccionada", JOptionPane.WARNING_MESSAGE);
				}
				
			}else if(command.equals("Costos")) {
				String computerCosts = "";
				
				if(crudMethods.getCosts()!= null || crudMethods.getCosts().size()!=0) {
					computerCosts = "Computadoras: \n";
					for(DetallePieza detallePieza: crudMethods.getCosts()) {
						computerCosts +="Computadora: "+detallePieza.getDetalleDePieza()+"\n"+
										"Costo: "+detallePieza.getValorPiezaDetalle()+"\n";
												
					}
					JOptionPane.showMessageDialog(new JFrame(), computerCosts, "Costos de computadora",JOptionPane.INFORMATION_MESSAGE);
				}else {
					computerCosts = "No hay computadoras en el sistema";
					JOptionPane.showMessageDialog(new JFrame(), computerCosts, "Costos de computadora",JOptionPane.INFORMATION_MESSAGE);
				}
					
						
			}else if(command.equals("Eliminar")) {
				if(txtSerie.getText().toString().equals("")) {
					JOptionPane.showMessageDialog(new Frame(), "No se puede eliminar computador porque el serial ingresado está vacio", "Información", JOptionPane.ERROR_MESSAGE);
				}else if(Integer.parseInt(txtSerie.getText().toString())>0){
					
					if(crudMethods.deleteComputer(crudMethods.searchComputer(Integer.parseInt(txtSerie.getText().toString())))) {
						JOptionPane.showMessageDialog(new JFrame(), "Computador eliminado con éxito", "Computadora eliminada",JOptionPane.INFORMATION_MESSAGE);
						clearTextFields();
					}else {
						JOptionPane.showMessageDialog(new Frame(), "No se puede eliminar computador porque el serial ingresado no existe", "Información", JOptionPane.ERROR_MESSAGE);
					}
									
				}else {
					JOptionPane.showMessageDialog(new Frame(), "Ingrese números mayores a 0", "Información", JOptionPane.ERROR_MESSAGE);
				}
				
			}else if(command.equals("Añadir Pieza")) {
				if(txtValor.getText().toString().equals("")) {
					JOptionPane.showMessageDialog(new Frame(), "No se puede añadir la pieza porque el precio está vacio", "Información", JOptionPane.ERROR_MESSAGE);
					
				}else if(Integer.parseInt(txtValor.getText().toString())>0) {
					int valor = Integer.parseInt(txtValor.getText().toString());
					listDetallePieza.add(new DetallePieza(comboBoxPiezas.getSelectedItem().toString(),valor));
					showPieces();
				}else {
					JOptionPane.showMessageDialog(new Frame(), "Ingrese número mayores a cero", "Información", JOptionPane.ERROR_MESSAGE);
				}
								
				
			}else if(command.equals("Confirmar")) {
				Computador newComputer = new Computador(Integer.parseInt(txtSerie.getText().toString()),
						txtDescripcion.getText().toString(),
						Integer.parseInt(txtFechaEnsamblaje.getText().toString()));
				
				if(crudMethods.updateComputer(newComputer,Integer.parseInt(searchSerial))) {
					JOptionPane.showMessageDialog(new Frame(), "Computador actualizado", "Información", JOptionPane.INFORMATION_MESSAGE);
					clearTextFields();
					btnConfirmar.setEnabled(false);
				}else {
					JOptionPane.showMessageDialog(new Frame(), "El computador no pudo ser actualizado", "Información", JOptionPane.ERROR_MESSAGE);
					clearTextFields();
				}
			}else if(command.equals("Eliminar Pieza")) {
				if(listDetallePieza.size()>0) {
					deletePieces();
				}else {
					lblPiezas.setText("Ninguna");
				}
				
			}			
			
		}
		
	}
}
