package com.cerner.view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Label;

public class PatientLocator {

	protected Shell shlPatientLocator;
	private Text txtSearchPatient;
	private Text text;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			PatientLocator window = new PatientLocator();
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
		shlPatientLocator.open();
		shlPatientLocator.layout();
		while (!shlPatientLocator.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlPatientLocator = new Shell();
		shlPatientLocator.setMinimumSize(new Point(700, 500));
		shlPatientLocator.setText("Patient Locator");
		shlPatientLocator.setSize(450, 300);
		
		Composite composite = new Composite(shlPatientLocator, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(187, 221, 255));
		composite.setBounds(27, 21, 624, 39);
		
		txtSearchPatient = new Text(composite, SWT.BORDER);
		txtSearchPatient.setEnabled(false);
		txtSearchPatient.setEditable(false);
		txtSearchPatient.setText("Search Patient :");
		txtSearchPatient.setBounds(10, 10, 263, 21);
		
		Label lblSearchPatientBy = new Label(shlPatientLocator, SWT.NONE);
		lblSearchPatientBy.setBounds(29, 92, 100, 15);
		lblSearchPatientBy.setText("Search Patient By:");
		
		Button btnCheckButton = new Button(shlPatientLocator, SWT.CHECK);
		btnCheckButton.setBounds(135, 91, 34, 16);
		btnCheckButton.setText("Id");
		
		Button btnCheckButton_1 = new Button(shlPatientLocator, SWT.CHECK);
		btnCheckButton_1.setBounds(175, 91, 85, 16);
		btnCheckButton_1.setText("First Name");
		
		text = new Text(shlPatientLocator, SWT.BORDER);
		text.setBounds(282, 89, 153, 21);
		
		Button btnView = new Button(shlPatientLocator, SWT.NONE);
		btnView.setBounds(586, 377, 75, 25);
		btnView.setText("Modify");
		
		Button btnView_1 = new Button(shlPatientLocator, SWT.NONE);
		btnView_1.setText("View");
		btnView_1.setBounds(586, 346, 75, 25);
		
		Button btnDelete = new Button(shlPatientLocator, SWT.NONE);
		btnDelete.setText("Delete");
		btnDelete.setBounds(586, 408, 75, 25);
		
		Button btnNewButton = new Button(shlPatientLocator, SWT.NONE);
		btnNewButton.setBounds(551, 87, 100, 25);
		btnNewButton.setText("View All Patient");
		
		Button btnNewButton_1 = new Button(shlPatientLocator, SWT.NONE);
		btnNewButton_1.setBounds(441, 87, 75, 25);
		btnNewButton_1.setText("Search");

	}

}
