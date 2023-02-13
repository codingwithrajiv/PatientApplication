package com.cerner.view;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.springframework.util.StringUtils;

import com.cerner.model.Addresses;
import com.cerner.model.ContactNoDetails;
import com.cerner.model.Patient;
import com.cerner.patientApplicationUI.HttpRestClient;
import com.cerner.validation.FieldValidation;

public class PatientCreation {

	protected Shell PatientManagement;
	static private Text lastNameText;
	static private Text firstNameText;
	static private Text tele1;
	static private Text tele2;
	static private Text tele3;
	static private Text countryCode1;
	static private Text countryCode2;
	static private Text countryCode3;
	static private Text streetText_1;
	static private Text cityText1;
	static private Text stateText_1;
	static private Text zipText1;
	static private Text countryText1;
	static private Text streetText_2;
	static private Text cityText2;
	static private Text stateText_2;
	static private Text zipText2;
	static private Text countryText2;
	static private DateTime dateOfBirthText;
	static private Button maleRadioButton;
	static private Button femaleRadioButton;
	static private Button othersRadioButton;
	static private Button btnModify;
	static private Button btnSave;
	static private Button refreshButton;
	static private Combo typeOfTelephone_1;
	static private Combo typeOfTelephone_2;
	static private Combo typeOfTelephone_3;
	static private Combo addressTypeCombo;
	static private Combo addressTypeCombo2;
	static private Text patientId;
	FieldValidation fieldValidation = new FieldValidation();

	/**
	 * Launch the application.
	 * 
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
		createContents(display);
		PatientManagement.open();
		PatientManagement.layout();
		while (!PatientManagement.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * 
	 * @param display
	 */
	protected void createContents(Display display) {

		PatientManagement = new Shell();
		PatientManagement.setMinimumSize(new Point(700, 500));
		PatientManagement.setMaximumSize(new Point(2000, 1500));
		PatientManagement.setSize(450, 300);
		PatientManagement.setText("Welcome to Patient Management System");

		Composite composite = new Composite(PatientManagement, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(187, 221, 255));
		composite.setBounds(32, 10, 624, 39);

		Label pagetTitle = new Label(composite, SWT.NONE);
		pagetTitle.setFont(SWTResourceManager.getFont("Microsoft Himalaya", 21, SWT.NONE));
		pagetTitle.setBackground(SWTResourceManager.getColor(224, 255, 255));
		pagetTitle.setBounds(10, 10, 193, 22);
		pagetTitle.setText("Patient Entry Page");
	
		Button patientLocatorButton = new Button(composite, SWT.NONE);
		patientLocatorButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new PatientLocator(display);
			}
		});
		patientLocatorButton.setBounds(387, 6, 227, 25);
		patientLocatorButton.setText("Patient Locator ");

		Label firstNameLabel = new Label(PatientManagement, SWT.NONE);
		firstNameLabel.setBounds(32, 89, 69, 15);
		firstNameLabel.setText("First Name*");

		firstNameText = new Text(PatientManagement, SWT.BORDER);
		firstNameText.setBounds(153, 86, 90, 21);

		Label lastNameLabel = new Label(PatientManagement, SWT.NONE);
		lastNameLabel.setBounds(298, 89, 69, 15);
		lastNameLabel.setText("Last Name*");

		lastNameText = new Text(PatientManagement, SWT.BORDER);
		lastNameText.setBounds(429, 86, 90, 21);

		Label PatientILabel = new Label(PatientManagement, SWT.NONE);
		PatientILabel.setBounds(613, 55, 55, 15);
		PatientILabel.setText("Pateint Id:");

		patientId = new Text(PatientManagement, SWT.BORDER);
		patientId.setEnabled(false);
		patientId.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		patientId.setBounds(613, 76, 48, 21);

		refreshButton = new Button(PatientManagement, SWT.NONE);
		refreshButton.setBounds(589, 354, 90, 24);
		refreshButton.setText("Refresh");

		Label genderLabel = new Label(PatientManagement, SWT.NONE);
		genderLabel.setBounds(32, 125, 55, 16);
		genderLabel.setText("Gender*");

		maleRadioButton = new Button(PatientManagement, SWT.RADIO);
		maleRadioButton.setBounds(153, 124, 55, 16);
		maleRadioButton.setText("Male");

		femaleRadioButton = new Button(PatientManagement, SWT.RADIO);
		femaleRadioButton.setBounds(215, 124, 55, 16);
		femaleRadioButton.setText("Female");

		othersRadioButton = new Button(PatientManagement, SWT.RADIO);
		othersRadioButton.setBounds(286, 124, 55, 16);
		othersRadioButton.setText("Others");

		Label dateOfBirthLabel = new Label(PatientManagement, SWT.NONE);
		dateOfBirthLabel.setBounds(32, 166, 82, 15);
		dateOfBirthLabel.setText("Date Of Birth*");

		dateOfBirthText = new DateTime(PatientManagement, SWT.BORDER);
		dateOfBirthText.setBounds(153, 157, 90, 24);

		Label contactNoDetails = new Label(PatientManagement, SWT.NONE);
		contactNoDetails.setBounds(32, 202, 113, 15);
		contactNoDetails.setText("Contact No. Details:");

		typeOfTelephone_1 = new Combo(PatientManagement, SWT.NONE);
		typeOfTelephone_1.setBounds(32, 223, 121, 23);
		typeOfTelephone_1.setText("Type of telephone");

		typeOfTelephone_1.add("Residence", 0);
		typeOfTelephone_1.add("Office", 1);
		typeOfTelephone_1.add("Others", 2);

		typeOfTelephone_2 = new Combo(PatientManagement, SWT.NONE);
		typeOfTelephone_2.setBounds(32, 252, 121, 23);
		typeOfTelephone_2.setText("Type of telephone");

		typeOfTelephone_2.add("Residence", 0);
		typeOfTelephone_2.add("Office", 1);
		typeOfTelephone_2.add("Others", 2);

		typeOfTelephone_3 = new Combo(PatientManagement, SWT.NONE);
		typeOfTelephone_3.setBounds(32, 281, 121, 23);
		typeOfTelephone_3.setText("Type of telephone");

		typeOfTelephone_3.add("Residence", 0);
		typeOfTelephone_3.add("Office", 1);
		typeOfTelephone_3.add("Others", 2);

		Label countyCodeLabel = new Label(PatientManagement, SWT.NONE);
		countyCodeLabel.setText("Country Code:");
		countyCodeLabel.setBounds(203, 202, 77, 15);

		countryCode1 = new Text(PatientManagement, SWT.BORDER);
		countryCode1.setBounds(215, 223, 55, 21);
		countryCode1.setTextLimit(3);

		countryCode2 = new Text(PatientManagement, SWT.BORDER);
		countryCode2.setBounds(215, 252, 55, 21);
		countryCode2.setTextLimit(3);

		countryCode3 = new Text(PatientManagement, SWT.BORDER);
		countryCode3.setBounds(215, 283, 55, 21);
		countryCode3.setTextLimit(3);

		Label teleNoLabel = new Label(PatientManagement, SWT.NONE);
		teleNoLabel.setText("Telephone No:");
		teleNoLabel.setBounds(342, 202, 82, 15);

		tele1 = new Text(PatientManagement, SWT.BORDER);
		tele1.setBounds(336, 223, 99, 21);
		tele1.setTextLimit(10);

		tele2 = new Text(PatientManagement, SWT.BORDER);
		tele2.setBounds(336, 252, 99, 21);
		tele2.setTextLimit(10);

		tele3 = new Text(PatientManagement, SWT.BORDER);
		tele3.setBounds(336, 281, 99, 21);
		tele3.setTextLimit(10);

		Label addressDetailLabel = new Label(PatientManagement, SWT.NONE);
		addressDetailLabel.setText("Addresses Details:");
		addressDetailLabel.setBounds(32, 327, 99, 15);

		addressTypeCombo = new Combo(PatientManagement, SWT.NONE);
		addressTypeCombo.setBounds(32, 355, 99, 23);
		addressTypeCombo.setText("Address Type");

		addressTypeCombo.add("Residence", 0);
		addressTypeCombo.add("Office", 1);
		addressTypeCombo.add("Others", 2);

		streetText_1 = new Text(PatientManagement, SWT.BORDER);
		streetText_1.setBounds(153, 355, 76, 21);

		cityText1 = new Text(PatientManagement, SWT.BORDER);
		cityText1.setBounds(235, 355, 76, 21);

		Label houseNoLabel = new Label(PatientManagement, SWT.NONE);
		houseNoLabel.setText("Street:");
		houseNoLabel.setBounds(160, 327, 69, 15);

		Label streetLabel = new Label(PatientManagement, SWT.NONE);
		streetLabel.setText("City:");
		streetLabel.setBounds(257, 327, 41, 15);

		Label cityLabel = new Label(PatientManagement, SWT.NONE);
		cityLabel.setText("State:");
		cityLabel.setBounds(336, 327, 33, 15);

		stateText_1 = new Text(PatientManagement, SWT.BORDER);
		stateText_1.setBounds(317, 355, 76, 21);

		Label zipCodeLabel = new Label(PatientManagement, SWT.NONE);
		zipCodeLabel.setText("Zip Code:");
		zipCodeLabel.setBounds(405, 327, 55, 15);

		zipText1 = new Text(PatientManagement, SWT.BORDER);
		zipText1.setBounds(399, 355, 61, 21);
		zipText1.setTextLimit(8);

		Label statelabel = new Label(PatientManagement, SWT.NONE);
		statelabel.setText("Country:");
		statelabel.setBounds(478, 327, 55, 15);

		countryText1 = new Text(PatientManagement, SWT.BORDER);
		countryText1.setBounds(466, 355, 76, 21);

		addressTypeCombo2 = new Combo(PatientManagement, SWT.NONE);
		addressTypeCombo2.setBounds(32, 386, 99, 23);
		addressTypeCombo2.setText("Address Type");

		addressTypeCombo2.add("Residence", 0);
		addressTypeCombo2.add("Office", 1);
		addressTypeCombo2.add("Others", 2);

		streetText_2 = new Text(PatientManagement, SWT.BORDER);
		streetText_2.setBounds(153, 386, 76, 21);

		cityText2 = new Text(PatientManagement, SWT.BORDER);
		cityText2.setBounds(235, 386, 76, 21);

		stateText_2 = new Text(PatientManagement, SWT.BORDER);
		stateText_2.setBounds(317, 386, 76, 21);

		zipText2 = new Text(PatientManagement, SWT.BORDER);
		zipText2.setBounds(399, 386, 61, 21);
		zipText2.setTextLimit(8);

		countryText2 = new Text(PatientManagement, SWT.BORDER);
		countryText2.setBounds(466, 386, 76, 21);

		/**
		 * Below is the listener for refresh button.
		 * 
		 */

		refreshButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				System.out.println("Refresh Button Clicked");

				femaleRadioButton.setSelection(false);
				maleRadioButton.setSelection(false);
				othersRadioButton.setSelection(false);
				dateOfBirthText.setMonth(0);
				dateOfBirthText.setDay(0);
				dateOfBirthText.setYear(0);

				firstNameText.setText("");
				lastNameText.setText("");
				tele2.setText("");
				tele1.setText("");
				tele3.setText("");
				typeOfTelephone_1.setText("Type of telephone");
				typeOfTelephone_2.setText("Type of telephone");
				typeOfTelephone_3.setText("Type of telephone");
				addressTypeCombo.setText("Address Type");
				addressTypeCombo2.setText("Address Type");
				countryCode1.setText("");
				countryCode2.setText("");
				countryCode3.setText("");
				streetText_1.setText("");
				cityText1.setText("");
				stateText_1.setText("");
				zipText1.setText("");
				countryText1.setText("");
				streetText_2.setText("");
				cityText2.setText("");
				stateText_2.setText("");
				zipText2.setText("");
				countryText2.setText("");
				patientId.setText("");
              //enable all field in UI.
				femaleRadioButton.setEnabled(true);
				maleRadioButton.setEnabled(true);
				othersRadioButton.setEnabled(true);
				dateOfBirthText.setEnabled(true);
				typeOfTelephone_1.setEnabled(true);
				typeOfTelephone_2.setEnabled(true);
				typeOfTelephone_3.setEnabled(true);
				firstNameText.setEditable(true);
				lastNameText.setEditable(true);
				tele1.setEditable(true);
				tele2.setEditable(true);
				tele3.setEditable(true);
				countryCode1.setEditable(true);
				countryCode2.setEditable(true);
				countryCode3.setEditable(true);
				streetText_1.setEditable(true);
				cityText1.setEditable(true);
				stateText_1.setEditable(true);
				zipText1.setEditable(true);
				countryText1.setEditable(true);
				streetText_2.setEditable(true);
				cityText2.setEditable(true);
				stateText_2.setEditable(true);
				zipText2.setEditable(true);
				countryText2.setEditable(true);
				btnModify.setEnabled(false);
				btnSave.setEnabled(true);
				addressTypeCombo.setEnabled(true);
				addressTypeCombo2.setEnabled(true);

			}
		});

		btnModify = new Button(PatientManagement, SWT.NONE);
		btnModify.setText("Modify");
		btnModify.setBounds(589, 386, 90, 24);
		btnModify.setEnabled(false);

		/**
		 * Below is the listener for Modify button.
		 * 
		 */

		btnModify.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				System.out.println("If Modify Button Clicked");

				String patientIdentifier = patientId.getText();
				String firstName = firstNameText.getText();
				String lastName = lastNameText.getText();

				Calendar calendar = Calendar.getInstance();
				int year = dateOfBirthText.getYear();
				int month = dateOfBirthText.getMonth();
				int day = dateOfBirthText.getDay();
				
				calendar.set(year, month, day);
				Date dateOfBirth = calendar.getTime();

				String gender = "";
				if (maleRadioButton.getSelection()) {
					gender = "Male";
				} else if (femaleRadioButton.getSelection()) {
					gender = "Female";
				} else if (othersRadioButton.getSelection()) {
					gender = "Others";
				}

				System.out.println("Gender  in modify " + gender);
				System.out.println("Date Of Birth " + dateOfBirth);

				String typeOfTele1 = typeOfTelephone_1.getText();
				String contryCode1 = countryCode1.getText();
				String teleNo1 = tele1.getText();

				String typeOfTele2 = typeOfTelephone_2.getText();
				String contryCode2 = countryCode2.getText();

				String teleNo2 = tele2.getText();

				String typeOfTele3 = typeOfTelephone_3.getText();
				String contryCode3 = countryCode3.getText();

				String teleNo3 = tele3.getText();

				String addressType1 = addressTypeCombo.getText();
				String street1 = streetText_1.getText();
				String city1 = cityText1.getText();
				String state1 = stateText_1.getText();
				String zipCode1 = zipText1.getText();
				String country1 = countryText1.getText();

				String addressType2 = addressTypeCombo2.getText();
				String street2 = streetText_2.getText();
				String city2 = cityText2.getText();
				String state2 = stateText_2.getText();
				String zipCode2 = zipText2.getText();
				String country2 = countryText2.getText();

				boolean isAllFieldDataValid = validationForCreateUpdate(firstName, lastName, gender, dateOfBirth,
						typeOfTele1, contryCode1, teleNo1, typeOfTele2, contryCode2, teleNo2, typeOfTele3, contryCode3,
						teleNo3, addressType1, street1, city1, state1, zipCode1, country1, addressType2, street2, city2,
						state2, zipCode2, country2);

				if (isAllFieldDataValid) {

					Patient patient = createPatientObject(patientIdentifier, firstName, lastName, gender, dateOfBirth,
							typeOfTele1, contryCode1, teleNo1, typeOfTele2, contryCode2, teleNo2, typeOfTele3,
							contryCode3, teleNo3, addressType1, street1, city1, state1, zipCode1, country1,
							addressType2, street2, city2, state2, zipCode2, country2);

					try {
						
						HttpResponse<String> response = HttpRestClient.updatePatient(patient);
						System.out.println("Response of update" + response);
						if (response.statusCode() == 200) {
							System.out.println("Updated the data");
							btnModify.setEnabled(false);
							createDialogueBoxSuccess(PatientManagement, "Patient Sucessfully Updated.");
						} else {
							createDialogueBox(PatientManagement, "Failed to Update Data.");
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("End of Modify Method.");

				}
			}
		});

		btnSave = new Button(PatientManagement, SWT.NONE);
		btnSave.setBounds(589, 416, 90, 24);
		btnSave.setText("Save");
		
		/**
		 * Below is the listener for Save button.
		 * 
		 */	
		btnSave.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				System.out.println("If Save Button Clicked");
				String patientIdentifier = patientId.getText();
				String firstName = firstNameText.getText();
				String lastName = lastNameText.getText();

				Calendar calendar = Calendar.getInstance();
				int year = dateOfBirthText.getYear();
				int month = dateOfBirthText.getMonth();
				int day = dateOfBirthText.getDay();
				calendar.set(year, month, day);
				Date dateOfBirth = calendar.getTime();

				String gender = "";
				if (maleRadioButton.getSelection()) {
					gender = "Male";
				} else if (femaleRadioButton.getSelection()) {
					gender = "Female";
				} else if (othersRadioButton.getSelection()) {
					gender = "Others";
				}

				System.out.println("Gender in Modify  " + gender);
				System.out.println("Date Of Birth in Modify " + dateOfBirth);

				String typeOfTele1 = typeOfTelephone_1.getText();
				String contryCode1 = countryCode1.getText();
				String teleNo1 = tele1.getText();

				String typeOfTele2 = typeOfTelephone_2.getText();
				String contryCode2 = countryCode2.getText();

				String teleNo2 = tele2.getText();

				String typeOfTele3 = typeOfTelephone_3.getText();
				String contryCode3 = countryCode3.getText();

				String teleNo3 = tele3.getText();

				String addressType1 = addressTypeCombo.getText();
				String street1 = streetText_1.getText();
				String city1 = cityText1.getText();
				String state1 = stateText_1.getText();
				String zipCode1 = zipText1.getText();
				String country1 = countryText1.getText();

				String addressType2 = addressTypeCombo2.getText();
				System.out.println("ADDDDD2" + addressType2);
				String street2 = streetText_2.getText();
				String city2 = cityText2.getText();
				String state2 = stateText_2.getText();
				String zipCode2 = zipText2.getText();
				String country2 = countryText2.getText();

				boolean isAllFieldDataValid = validationForCreateUpdate(firstName, lastName, gender, dateOfBirth,
						typeOfTele1, contryCode1, teleNo1, typeOfTele2, contryCode2, teleNo2, typeOfTele3, contryCode3,
						teleNo3, addressType1, street1, city1, state1, zipCode1, country1, addressType2, street2, city2,
						state2, zipCode2, country2);

				if (isAllFieldDataValid) {

					Patient patient = createPatientObject(patientIdentifier, firstName, lastName, gender, dateOfBirth,
							typeOfTele1, contryCode1, teleNo1, typeOfTele2, contryCode2, teleNo2, typeOfTele3,
							contryCode3, teleNo3, addressType1, street1, city1, state1, zipCode1, country1,
							addressType2, street2, city2, state2, zipCode2, country2);

					try {
						Patient Patientresponse = HttpRestClient.savePatient(patient);
						if (Patientresponse.getPatientId() != null) {
							patientId.setText(Patientresponse.getPatientId().toString());
							btnSave.setEnabled(false);
							createDialogueBoxSuccess(PatientManagement,
									"Patient Sucessfully Added, Patient Id- " + Patientresponse.getPatientId());

							femaleRadioButton.setEnabled(false);
							maleRadioButton.setEnabled(false);
							othersRadioButton.setEnabled(false);
							dateOfBirthText.setEnabled(false);
							typeOfTelephone_1.setEnabled(false);
							typeOfTelephone_2.setEnabled(false);
							typeOfTelephone_3.setEnabled(false);
							firstNameText.setEditable(false);
							lastNameText.setEditable(false);
							tele1.setEditable(false);
							tele2.setEditable(false);
							tele3.setEditable(false);
							countryCode1.setEditable(false);
							countryCode2.setEditable(false);
							countryCode3.setEditable(false);
							streetText_1.setEditable(false);
							cityText1.setEditable(false);
							stateText_1.setEditable(false);
							zipText1.setEditable(false);
							countryText1.setEditable(false);
							streetText_2.setEditable(false);
							cityText2.setEditable(false);
							stateText_2.setEditable(false);
							zipText2.setEditable(false);
							countryText2.setEditable(false);
							btnModify.setEnabled(false);
							btnSave.setEnabled(false);
							addressTypeCombo.setEnabled(false);
							addressTypeCombo2.setEnabled(false);

						} else {
							createDialogueBox(PatientManagement, "Failed to Save Patient Data.");
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("End of Save Method.");

				}
			}

		});
      }
	
	/**
	 * Common method for pop up Dialogue Box with Error Message.
	 * 
	 */

	public static void createDialogueBox(Shell shell, String message) {
		MessageBox msgBox = new MessageBox(shell, SWT.ICON_ERROR);
		msgBox.setText("ERROR");
		msgBox.setMessage(message);
		int buttonId = msgBox.open();
		switch (buttonId) {
		case SWT.YES:
		case SWT.NO:
			break;
		case SWT.CANCEL:
		}

	}
	
	/**
	 * Common method for pop up Dialogue Box with Success Message.
	 * 
	 */
	public static void createDialogueBoxSuccess(Shell shell, String message) {
		MessageBox msgBox = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
		msgBox.setText("SUCCESS");
		msgBox.setMessage(message);
		int buttonId = msgBox.open();
		switch (buttonId) {
		case SWT.YES:
		case SWT.NO:
			break;
		case SWT.CANCEL:
		}

	}
	/**
	 * Method to populate the data to UI, this is called from patient locator page when we click on View and Modify
	 * 
	 */
	public void populatePatientForm(Patient patient, boolean isModify) {

		System.out.println("inside Popullate Form");
		lastNameText.setText(patient.getLastName().toString());
		firstNameText.setText(patient.getFirstName().toString());
		patientId.setText(patient.getPatientId().toString());
		java.util.Date patienDobUtil = patient.getDateOfBirth();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(patienDobUtil);
		dateOfBirthText.setDay(calendar.get(Calendar.DATE));
		dateOfBirthText.setMonth(calendar.get(Calendar.MONTH));
		dateOfBirthText.setYear(calendar.get(Calendar.YEAR));

		System.out.println("patienDobUtil" + patienDobUtil);

		if ("Female".equalsIgnoreCase(patient.getGender())) {
			femaleRadioButton.setSelection(true);
		} else if ("Male".equalsIgnoreCase(patient.getGender())) {
			maleRadioButton.setSelection(true);
		} else if ("Others".equalsIgnoreCase(patient.getGender())) {
			othersRadioButton.setSelection(true);
		}

		if (patient.getContactNoDetails().size() > 0) {
			typeOfTelephone_1.setText(patient.getContactNoDetails().get(0).getTeleType().toString());
			countryCode1.setText(patient.getContactNoDetails().get(0).getCountyCode().toString());
			tele1.setText(patient.getContactNoDetails().get(0).getTeleNo().toString());
		}
		if (patient.getContactNoDetails().size() > 1) {
			typeOfTelephone_2.setText(patient.getContactNoDetails().get(1).getTeleType().toString().toString());
			countryCode2.setText(patient.getContactNoDetails().get(1).getCountyCode().toString());
			tele2.setText(patient.getContactNoDetails().get(1).getTeleNo().toString());

		}
		System.out.println("Size of Contact Details" + patient.getContactNoDetails().size());
		if (patient.getContactNoDetails().size() > 2) {
			typeOfTelephone_3.setText(patient.getContactNoDetails().get(2).getTeleType().toString());
			countryCode3.setText(patient.getContactNoDetails().get(2).getCountyCode().toString());
			tele3.setText(patient.getContactNoDetails().get(2).getTeleNo().toString());
		}

		System.out.println("Size of Address" + patient.getAddresses().size());

		if (patient.getAddresses().size() > 0) {
			System.out.println("Size>1");
			addressTypeCombo.setText(patient.getAddresses().get(0).getAddressType().toString());
			streetText_1.setText(patient.getAddresses().get(0).getStreetAddress().toString());
			cityText1.setText(patient.getAddresses().get(0).getCity().toString());
			stateText_1.setText(patient.getAddresses().get(0).getState().toString());
			zipText1.setText(patient.getAddresses().get(0).getZipCode().toString());
			countryText1.setText(patient.getAddresses().get(0).getCountry().toString());
		}

		if (patient.getAddresses().size() > 1) {
			System.out.println("Size>2");
			addressTypeCombo2.setText(patient.getAddresses().get(1).getAddressType().toString());
			streetText_2.setText(patient.getAddresses().get(1).getStreetAddress().toString());
			cityText2.setText(patient.getAddresses().get(1).getCity().toString());
			stateText_2.setText(patient.getAddresses().get(1).getState().toString());
			zipText2.setText(patient.getAddresses().get(1).getZipCode().toString());
			countryText2.setText(patient.getAddresses().get(1).getCountry().toString());
		}

		if (isModify) {
			femaleRadioButton.setEnabled(true);
			maleRadioButton.setEnabled(true);
			othersRadioButton.setEnabled(true);
			dateOfBirthText.setEnabled(true);
			typeOfTelephone_1.setEnabled(true);
			typeOfTelephone_2.setEnabled(true);
			typeOfTelephone_3.setEnabled(true);
			firstNameText.setEditable(true);
			lastNameText.setEditable(true);
			tele2.setEditable(true);
			tele1.setEditable(true);
			tele3.setEditable(true);
			countryCode1.setEditable(true);
			countryCode2.setEditable(true);
			countryCode3.setEditable(true);
			streetText_1.setEditable(true);
			cityText1.setEditable(true);
			stateText_1.setEditable(true);
			zipText1.setEditable(true);
			countryText1.setEditable(true);
			streetText_2.setEditable(true);
			cityText2.setEditable(true);
			stateText_2.setEditable(true);
			zipText2.setEditable(true);
			countryText2.setEditable(true);
			btnModify.setEnabled(true);
			btnSave.setEnabled(false);
			addressTypeCombo.setEnabled(true);
			addressTypeCombo2.setEnabled(true);
		} else {
			femaleRadioButton.setEnabled(false);
			maleRadioButton.setEnabled(false);
			othersRadioButton.setEnabled(false);
			dateOfBirthText.setEnabled(false);
			typeOfTelephone_1.setEnabled(false);
			typeOfTelephone_2.setEnabled(false);
			typeOfTelephone_3.setEnabled(false);
			firstNameText.setEditable(false);
			lastNameText.setEditable(false);
			tele1.setEditable(false);
			tele2.setEditable(false);
			tele3.setEditable(false);
			countryCode1.setEditable(false);
			countryCode2.setEditable(false);
			countryCode3.setEditable(false);
			streetText_1.setEditable(false);
			cityText1.setEditable(false);
			stateText_1.setEditable(false);
			zipText1.setEditable(false);
			countryText1.setEditable(false);
			streetText_2.setEditable(false);
			cityText2.setEditable(false);
			stateText_2.setEditable(false);
			zipText2.setEditable(false);
			countryText2.setEditable(false);
			btnModify.setEnabled(false);
			btnSave.setEnabled(false);
			addressTypeCombo.setEnabled(false);
			addressTypeCombo2.setEnabled(false);
		}

	}
	
	/**
	 * Method to create Parent Object by setting data from fields from UI after validation
	 * 
	 */
	private Patient createPatientObject(String patientId, String firstName, String lastName, String gender,
			Date dateOfBirth, String typeOfTele1, String contryCode1, String teleNo1, String typeOfTele2,
			String contryCode2, String teleNo2, String typeOfTele3, String contryCode3, String teleNo3,
			String addressType1, String street1, String city1, String state1, String zipCode1, String country1,
			String addressType2, String street2, String city2, String state2, String zipCode2, String country2) {

		Patient patient = new Patient();

		int patientInt = 0;

		if (patientId.isBlank() || patientId.isEmpty() || patientId.equals(null)) {
			System.out.println("No Patient ID, Create object");

		} else {
			patientInt = Integer.parseInt(patientId);
			if ((patientInt > 0))
				patient.setPatientId(Long.parseLong(patientId));
		}

		patient.setFirstName(firstName);
		patient.setLastName(lastName);
		patient.setGender(gender);

		System.out.println("date OF birth" + dateOfBirth);

		patient.setDateOfBirth(dateOfBirth);

		List<Addresses> addressList = new ArrayList<>();

		Addresses address = new Addresses();

		address.setAddressType(addressType1);
		address.setStreetAddress(street1);
		address.setState(state1);
		address.setCity(city1);
		address.setZipCode(zipCode1);
		address.setCountry(country1);

		addressList.add(address);

		if (addressType2.equalsIgnoreCase("Address Type")) {
			System.out.println("Not Saving Address 2 details");
			Addresses address2 = new Addresses();
			address2.setAddressType("");
			address2.setStreetAddress("");
			address2.setState("");
			address2.setCity("");
			address2.setZipCode("");
			address2.setCountry("");
			addressList.add(address2);
		} else {
			System.out.println("Saving Address 2 details");
			Addresses address2 = new Addresses();
			address2.setAddressType(addressType2);
			address2.setStreetAddress(street2);
			address2.setState(state2);
			address2.setCity(city2);
			address2.setZipCode(zipCode2);
			address2.setCountry(country2);
			addressList.add(address2);
		}

		List<ContactNoDetails> teleList = new ArrayList<>();

		ContactNoDetails contactNoDetails = new ContactNoDetails();
		contactNoDetails.setCountyCode(contryCode1);
		contactNoDetails.setTeleNo(teleNo1);
		contactNoDetails.setTeleType(typeOfTele1);
		teleList.add(contactNoDetails);

		if (typeOfTele2.equalsIgnoreCase("Type of telephone")) {
			System.out.println("Not Saving Tele 2 details");
			ContactNoDetails contactNoDetails2 = new ContactNoDetails();
			contactNoDetails2.setCountyCode("");
			contactNoDetails2.setTeleNo("");
			contactNoDetails2.setTeleType("");
			teleList.add(contactNoDetails2);
		} else {
			System.out.println("Saving Tele 2 details");
			ContactNoDetails contactNoDetails2 = new ContactNoDetails();
			contactNoDetails2.setCountyCode(contryCode2);
			contactNoDetails2.setTeleNo(teleNo2);
			contactNoDetails2.setTeleType(typeOfTele2);
			teleList.add(contactNoDetails2);
		}

		if (typeOfTele3.equalsIgnoreCase("Type of telephone")) {
			System.out.println("Not Saving Tele 3 details");
			ContactNoDetails contactNoDetails3 = new ContactNoDetails();
			contactNoDetails3.setCountyCode("");
			contactNoDetails3.setTeleNo("");
			contactNoDetails3.setTeleType("");
			teleList.add(contactNoDetails3);
		} else {
			System.out.println("Saving Tele 3 details");
			ContactNoDetails contactNoDetails3 = new ContactNoDetails();
			contactNoDetails3.setCountyCode(contryCode3);
			contactNoDetails3.setTeleNo(teleNo3);
			contactNoDetails3.setTeleType(typeOfTele3);
			teleList.add(contactNoDetails3);
		}

		patient.setContactNoDetails(teleList);

		patient.setAddresses(addressList);

		return patient;
	}
	/**
	 * Method to validate the fields from UI 
	 * 
	 */
	private boolean validationForCreateUpdate(String firstName, String lastName, String gender, Date dateOfBirth,
			String typeOfTele1, String contryCode1, String teleNo1, String typeOfTele2, String contryCode2,
			String teleNo2, String typeOfTele3, String contryCode3, String teleNo3, String addressType1, String street1,
			String city1, String state1, String zipCode1, String country1, String addressType2, String street2,
			String city2, String state2, String zipCode2, String country2) {

		boolean isAllFieldDataValid = true;

		//Validation for first name 

		if (StringUtils.isEmpty(firstName)) {
			isAllFieldDataValid = false;

			createDialogueBox(PatientManagement, "Please Enter First Name");
			return isAllFieldDataValid;

		}

		if (!fieldValidation.validateOnlyAlphabet(firstName)) {
			isAllFieldDataValid = false;
			createDialogueBox(PatientManagement, "Please Enter Valid First Name");
			return isAllFieldDataValid;
		}

		//Validation for Last Name
		
		if (StringUtils.isEmpty(lastName)) {
			isAllFieldDataValid = false;
			createDialogueBox(PatientManagement, "Please Enter Last Name");
			return isAllFieldDataValid;
		} else if (!fieldValidation.validateOnlyAlphabet(lastName)) {
			isAllFieldDataValid = false;
			createDialogueBox(PatientManagement, "Please Enter Valid Last Name");
			return isAllFieldDataValid;
		}

		//Validation for Gender

		if (StringUtils.isEmpty(gender)) {
			isAllFieldDataValid = false;
			createDialogueBox(PatientManagement, "Please Enter Your Gender");
			return isAllFieldDataValid;
		}

		//Validation for Date Of Birth

		if (StringUtils.isEmpty(dateOfBirth)) {
			isAllFieldDataValid = false;
			createDialogueBox(PatientManagement, "Please Enter Your Date Of Birth");
			return isAllFieldDataValid;
		}

		//Validation for Telephone

		if (typeOfTele1.equalsIgnoreCase("Type of telephone")) {
			isAllFieldDataValid = false;
			createDialogueBox(PatientManagement, "Please Enter Atleast one Telephone Number");
			return isAllFieldDataValid;
		} else if (StringUtils.isEmpty(contryCode1)) {
			isAllFieldDataValid = false;
			createDialogueBox(PatientManagement, "Please Enter Country Code");
			return isAllFieldDataValid;
		} else if (!fieldValidation.validateOnlyNumeric(contryCode1)) {
			isAllFieldDataValid = false;
			createDialogueBox(PatientManagement, "Please Enter valid Country Code");
			return isAllFieldDataValid;
		} else if (StringUtils.isEmpty(teleNo1)) {
			isAllFieldDataValid = false;
			createDialogueBox(PatientManagement, "Enter Telephone no.");
			return isAllFieldDataValid;
		} else if (!fieldValidation.validateOnlyNumeric(teleNo1)) {
			isAllFieldDataValid = false;
			createDialogueBox(PatientManagement, "Please Enter valid telephone no.");
			return isAllFieldDataValid;
		}

		// Validation for Telephone row2

		if (typeOfTele2.equalsIgnoreCase("Residence") || typeOfTele2.equalsIgnoreCase("Office")
				|| typeOfTele2.equalsIgnoreCase("Others")) {

			if (StringUtils.isEmpty(contryCode2)) {
				isAllFieldDataValid = false;
				createDialogueBox(PatientManagement, "Please Enter Country Code for row 2");
				return isAllFieldDataValid;
			} else if (!fieldValidation.validateOnlyNumeric(contryCode2)) {
				isAllFieldDataValid = false;
				createDialogueBox(PatientManagement, "Please Enter valid Country Code for row 2");
				return isAllFieldDataValid;
			} else if (StringUtils.isEmpty(teleNo2)) {
				isAllFieldDataValid = false;
				createDialogueBox(PatientManagement, "Enter Telephone no. for row 2");
				return isAllFieldDataValid;
			} else if (!fieldValidation.validateOnlyNumeric(teleNo2)) {
				isAllFieldDataValid = false;
				createDialogueBox(PatientManagement, "Please Enter valid telephone no. for row 2");
				return isAllFieldDataValid;
			}
		}
		// Validation for telephone for 3rdrow

		if (typeOfTele3.equalsIgnoreCase("Residence") || typeOfTele3.equalsIgnoreCase("Office")
				|| typeOfTele3.equalsIgnoreCase("Others")) {

			if (StringUtils.isEmpty(contryCode3)) {
				isAllFieldDataValid = false;
				createDialogueBox(PatientManagement, "Please Enter Country Code for row 3");
				return isAllFieldDataValid;
			} else if (!fieldValidation.validateOnlyNumeric(contryCode3)) {
				isAllFieldDataValid = false;
				createDialogueBox(PatientManagement, "Please Enter valid Country Code for row 3");
				return isAllFieldDataValid;
			} else if (StringUtils.isEmpty(teleNo3)) {
				isAllFieldDataValid = false;
				createDialogueBox(PatientManagement, "Enter Telephone no. for row 3");
				return isAllFieldDataValid;
			} else if (!fieldValidation.validateOnlyNumeric(teleNo3)) {
				isAllFieldDataValid = false;
				createDialogueBox(PatientManagement, "Please Enter valid telephone no.");
				return isAllFieldDataValid;
			}
		}

		//Validation for Address row1

		if (addressType1.equalsIgnoreCase("Address Type")) {
			isAllFieldDataValid = false;
			createDialogueBox(PatientManagement, "Please Enter Atleast one Address");
			return isAllFieldDataValid;
		} else if (StringUtils.isEmpty(street1)) {
			isAllFieldDataValid = false;
			createDialogueBox(PatientManagement, "Please Enter Street");
			return isAllFieldDataValid;
		} else if (!fieldValidation.validateOnlyAlphabet(street1)) {
			isAllFieldDataValid = false;
			createDialogueBox(PatientManagement, "Please Enter Valid Street");
			return isAllFieldDataValid;
		} else if (StringUtils.isEmpty(city1)) {
			isAllFieldDataValid = false;
			createDialogueBox(PatientManagement, "Please Enter City");
			return isAllFieldDataValid;
		} else if (!fieldValidation.validateOnlyAlphabet(city1)) {
			isAllFieldDataValid = false;
			createDialogueBox(PatientManagement, "Please Enter Valid City");
			return isAllFieldDataValid;
		} else if (StringUtils.isEmpty(state1)) {
			isAllFieldDataValid = false;
			createDialogueBox(PatientManagement, "Please Enter State");
			return isAllFieldDataValid;
		} else if (!fieldValidation.validateOnlyAlphabet(state1)) {
			isAllFieldDataValid = false;
			createDialogueBox(PatientManagement, "Please Enter Valid State");
			return isAllFieldDataValid;
		} else if (StringUtils.isEmpty(zipCode1)) {
			isAllFieldDataValid = false;
			createDialogueBox(PatientManagement, "Please Enter ZipCode");
			return isAllFieldDataValid;
		} else if (!fieldValidation.validateOnlyNumeric(zipCode1)) {
			isAllFieldDataValid = false;
			createDialogueBox(PatientManagement, "Please Enter Valid ZipCode");
			return isAllFieldDataValid;
		} else if (StringUtils.isEmpty(country1)) {
			isAllFieldDataValid = false;
			createDialogueBox(PatientManagement, "Please Enter Country");
			return isAllFieldDataValid;
		} else if (!fieldValidation.validateOnlyAlphabet(country1)) {
			isAllFieldDataValid = false;
			createDialogueBox(PatientManagement, "Please Enter Valid Country");
			return isAllFieldDataValid;
		}

		//Validation for Address row 2

		if (addressType2.equalsIgnoreCase("Residence") || addressType2.equalsIgnoreCase("Office")
				|| addressType2.equalsIgnoreCase("Others")) {

			if (StringUtils.isEmpty(city2)) {
				isAllFieldDataValid = false;
				createDialogueBox(PatientManagement, "Please Enter City in 2nd row");
				return isAllFieldDataValid;
			} else if (!fieldValidation.validateOnlyAlphabet(city2)) {
				isAllFieldDataValid = false;
				createDialogueBox(PatientManagement, "Please Enter Valid City in 2nd row");
				return isAllFieldDataValid;
			} else if (StringUtils.isEmpty(state2)) {
				isAllFieldDataValid = false;
				createDialogueBox(PatientManagement, "Please Enter State in 2nd row");
				return isAllFieldDataValid;
			} else if (!fieldValidation.validateOnlyAlphabet(state2)) {
				isAllFieldDataValid = false;
				createDialogueBox(PatientManagement, "Please Enter Valid State in 2nd row");
				return isAllFieldDataValid;
			} else if (StringUtils.isEmpty(zipCode2)) {
				isAllFieldDataValid = false;
				createDialogueBox(PatientManagement, "Please Enter ZipCode in 2nd row");
				return isAllFieldDataValid;
			} else if (!fieldValidation.validateOnlyNumeric(zipCode2)) {
				isAllFieldDataValid = false;
				createDialogueBox(PatientManagement, "Please Enter Valid ZipCode in 2nd row");
				return isAllFieldDataValid;
			} else if (StringUtils.isEmpty(country2)) {
				isAllFieldDataValid = false;
				createDialogueBox(PatientManagement, "Please Enter Country in 2nd row");
				return isAllFieldDataValid;
			} else if (!fieldValidation.validateOnlyAlphabet(country2)) {
				isAllFieldDataValid = false;
				createDialogueBox(PatientManagement, "Please Enter Valid Country in 2nd row");
				return isAllFieldDataValid;
			}
		}

		return isAllFieldDataValid;
	}
}
