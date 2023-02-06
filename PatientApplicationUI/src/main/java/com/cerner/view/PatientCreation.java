package com.cerner.view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.graphics.Point;
import org.eclipse.wb.swt.SWTResourceManager;

public class PatientCreation {

	protected Shell shlWelcomeToPatient;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text txtPatientEntryForm;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Text text_7;
	private Text text_8;
	private Text text_10;
	private Text text_9;
	private Text text_11;
	private Text text_12;
	private Text text_13;
	private Text text_14;
	private Text text_15;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			PatientCreation window = new PatientCreation();
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
		shlWelcomeToPatient.open();
		shlWelcomeToPatient.layout();
		while (!shlWelcomeToPatient.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlWelcomeToPatient = new Shell();
		shlWelcomeToPatient.setMinimumSize(new Point(700, 500));
		shlWelcomeToPatient.setMaximumSize(new Point(0, 0));
		shlWelcomeToPatient.setSize(450, 300);
		shlWelcomeToPatient.setText("Welcome to Patient Management System");
		
		text = new Text(shlWelcomeToPatient, SWT.BORDER);
		text.setBounds(429, 86, 90, 21);
		
		Button btnNewButton = new Button(shlWelcomeToPatient, SWT.NONE);
		btnNewButton.setBounds(571, 418, 90, 24);
		btnNewButton.setText("Save");
		
		Label lblNewLabel = new Label(shlWelcomeToPatient, SWT.NONE);
		lblNewLabel.setBounds(32, 89, 59, 15);
		lblNewLabel.setText("First Name");
		
		Label lblNewLabel_1 = new Label(shlWelcomeToPatient, SWT.NONE);
		lblNewLabel_1.setBounds(32, 166, 82, 15);
		lblNewLabel_1.setText("Date Of Birth");
		
		DateTime dateTime = new DateTime(shlWelcomeToPatient, SWT.BORDER);
		dateTime.setBounds(153, 157, 90, 24);
		
		text_1 = new Text(shlWelcomeToPatient, SWT.BORDER);
		text_1.setBounds(153, 86, 90, 21);
		
		Label lblNewLabel_3 = new Label(shlWelcomeToPatient, SWT.NONE);
		lblNewLabel_3.setBounds(298, 89, 55, 15);
		lblNewLabel_3.setText("Last Name");
		
		Label lblNewLabel_4 = new Label(shlWelcomeToPatient, SWT.NONE);
		lblNewLabel_4.setBounds(32, 125, 55, 15);
		lblNewLabel_4.setText("Gender");
		
		Button btnRadioButton = new Button(shlWelcomeToPatient, SWT.RADIO);
		btnRadioButton.setBounds(153, 124, 41, 16);
		btnRadioButton.setText("Male");
		
		Button btnFemale = new Button(shlWelcomeToPatient, SWT.RADIO);
		btnFemale.setBounds(215, 124, 55, 16);
		btnFemale.setText("Female");
		
		Button btnOthers = new Button(shlWelcomeToPatient, SWT.RADIO);
		btnOthers.setBounds(286, 124, 55, 16);
		btnOthers.setText("Others");
		
		Group grpCurrentAddress = new Group(shlWelcomeToPatient, SWT.NONE);
		grpCurrentAddress.setText("Current Address");
		grpCurrentAddress.setBounds(32, 262, 240, 180);
		
		Label lblNewLabel_6 = new Label(grpCurrentAddress, SWT.NONE);
		lblNewLabel_6.setBounds(24, 29, 55, 15);
		lblNewLabel_6.setText("House no.");
		
		Label lblNewLabel_7 = new Label(grpCurrentAddress, SWT.NONE);
		lblNewLabel_7.setText("Street ");
		lblNewLabel_7.setBounds(24, 53, 55, 15);
		
		Label lblNewLabel_8 = new Label(grpCurrentAddress, SWT.NONE);
		lblNewLabel_8.setBounds(24, 77, 55, 15);
		lblNewLabel_8.setText("City");
		
		Label lblNewLabel_9 = new Label(grpCurrentAddress, SWT.NONE);
		lblNewLabel_9.setBounds(24, 101, 55, 15);
		lblNewLabel_9.setText("State ");
		
		Label lblZipCode = new Label(grpCurrentAddress, SWT.NONE);
		lblZipCode.setBounds(24, 125, 55, 15);
		lblZipCode.setText("Zip Code");
		
		Label lblCountry = new Label(grpCurrentAddress, SWT.NONE);
		lblCountry.setBounds(24, 149, 55, 15);
		lblCountry.setText("Country ");
		
		text_4 = new Text(grpCurrentAddress, SWT.BORDER);
		text_4.setBounds(131, 26, 76, 21);
		
		text_5 = new Text(grpCurrentAddress, SWT.BORDER);
		text_5.setBounds(131, 50, 76, 21);
		
		text_6 = new Text(grpCurrentAddress, SWT.BORDER);
		text_6.setBounds(131, 98, 76, 21);
		
		text_7 = new Text(grpCurrentAddress, SWT.BORDER);
		text_7.setBounds(131, 146, 76, 21);
		
		text_8 = new Text(grpCurrentAddress, SWT.BORDER);
		text_8.setBounds(131, 122, 76, 21);
		
		text_10 = new Text(grpCurrentAddress, SWT.BORDER);
		text_10.setBounds(131, 74, 76, 21);
		
		text_2 = new Text(shlWelcomeToPatient, SWT.BORDER);
		text_2.setBounds(153, 199, 96, 21);
		
		Label lblNewLabel_5 = new Label(shlWelcomeToPatient, SWT.NONE);
		lblNewLabel_5.setBounds(298, 202, 113, 15);
		lblNewLabel_5.setText("Alternate Mobile no.");
		
		text_3 = new Text(shlWelcomeToPatient, SWT.BORDER);
		text_3.setBounds(429, 199, 90, 21);
		
		Composite composite = new Composite(shlWelcomeToPatient, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(187, 221, 255));
		composite.setBounds(27, 10, 624, 39);
		
		txtPatientEntryForm = new Text(composite, SWT.BORDER);
		txtPatientEntryForm.setEnabled(false);
		txtPatientEntryForm.setEditable(false);
		txtPatientEntryForm.setText("Patient Entry Form:");
		txtPatientEntryForm.setBounds(10, 10, 263, 21);
		
		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		btnNewButton_1.setBounds(387, 6, 227, 25);
		btnNewButton_1.setText("Patient Locator ");
		
		Label lblNewLabel_2 = new Label(shlWelcomeToPatient, SWT.NONE);
		lblNewLabel_2.setBounds(32, 202, 76, 15);
		lblNewLabel_2.setText("Mobile No.");
		
		Group grpPermanentAddress = new Group(shlWelcomeToPatient, SWT.NONE);
		grpPermanentAddress.setText("Permanent Address");
		grpPermanentAddress.setBounds(298, 262, 240, 180);
		
		Label lblNewLabel_6_1 = new Label(grpPermanentAddress, SWT.NONE);
		lblNewLabel_6_1.setText("House no.");
		lblNewLabel_6_1.setBounds(24, 29, 55, 15);
		
		Label lblNewLabel_7_1 = new Label(grpPermanentAddress, SWT.NONE);
		lblNewLabel_7_1.setText("Street ");
		lblNewLabel_7_1.setBounds(24, 53, 55, 15);
		
		Label lblNewLabel_8_1 = new Label(grpPermanentAddress, SWT.NONE);
		lblNewLabel_8_1.setText("City");
		lblNewLabel_8_1.setBounds(24, 77, 55, 15);
		
		Label lblNewLabel_9_1 = new Label(grpPermanentAddress, SWT.NONE);
		lblNewLabel_9_1.setText("State ");
		lblNewLabel_9_1.setBounds(24, 101, 55, 15);
		
		Label lblZipCode_1 = new Label(grpPermanentAddress, SWT.NONE);
		lblZipCode_1.setText("Zip Code");
		lblZipCode_1.setBounds(24, 125, 55, 15);
		
		Label lblCountry_1 = new Label(grpPermanentAddress, SWT.NONE);
		lblCountry_1.setText("Country ");
		lblCountry_1.setBounds(24, 149, 55, 15);
		
		text_9 = new Text(grpPermanentAddress, SWT.BORDER);
		text_9.setBounds(131, 26, 76, 21);
		
		text_11 = new Text(grpPermanentAddress, SWT.BORDER);
		text_11.setBounds(131, 50, 76, 21);
		
		text_12 = new Text(grpPermanentAddress, SWT.BORDER);
		text_12.setBounds(131, 98, 76, 21);
		
		text_13 = new Text(grpPermanentAddress, SWT.BORDER);
		text_13.setBounds(131, 146, 76, 21);
		
		text_14 = new Text(grpPermanentAddress, SWT.BORDER);
		text_14.setBounds(131, 122, 76, 21);
		
		text_15 = new Text(grpPermanentAddress, SWT.BORDER);
		text_15.setBounds(131, 74, 76, 21);
		
		Button btnModify = new Button(shlWelcomeToPatient, SWT.NONE);
		btnModify.setText("Modify");
		btnModify.setBounds(571, 388, 90, 24);

	}
}
