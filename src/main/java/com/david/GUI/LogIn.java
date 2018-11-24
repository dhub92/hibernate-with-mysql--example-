package com.david.GUI;

import com.david.hibernateMethods.*;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;

import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
/**
 * Create a Login UI for the user to login and can access to the Manage Computer UI
 * @author David Moreno
 * @version 1.0
 * @since 1.0
 *
 */
public class LogIn {

	protected Shell shell;
	private Text textUsuario;
	private Text textContrasenia;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LogIn window = new LogIn();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(545, 104);
		shell.setText("Autenticación");
		shell.setLayout(new GridLayout(5, false));
			
		
		CLabel lblUsuario = new CLabel(shell, SWT.NONE);
		lblUsuario.setText("Usuario");
		
		textUsuario = new Text(shell, SWT.BORDER);
		GridData gd_textUsuario = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_textUsuario.widthHint = 111;
		textUsuario.setLayoutData(gd_textUsuario);
		
		
		CLabel lblContrasenia = new CLabel(shell, SWT.NONE);
		lblContrasenia.setText("Contrase\u00F1a");
		
		textContrasenia = new Text(shell, SWT.SINGLE | SWT.BORDER  | SWT.PASSWORD);
		textContrasenia.setEchoChar('*');
		textContrasenia.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	
		
		Button btnIngresar = new Button(shell, SWT.NONE);
		btnIngresar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				CrudMethods crudMethods = new CrudMethods();
				if(crudMethods.logIn(textUsuario.getText().toString(), textContrasenia.getText().toString()) == 0) {
					ManageComputerFrame frame = new ManageComputerFrame();
					frame.setVisible(true);
					shell.setVisible(false);
				}else {
					JOptionPane.showMessageDialog(new Frame(), "El usuario no existe ", "Error de ingreso", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnIngresar.setText("Ingresar");
		

	}

}
