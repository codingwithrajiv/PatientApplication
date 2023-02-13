package com.cerner.view;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import com.cerner.model.Patient;
import com.cerner.patientApplicationUI.HttpRestClient;
import com.cerner.validation.FieldValidation;

public class PatientLocator {

	protected Shell shlPatientLocator;
	private Text searchTextField;
	public Display display;
	private Table table;
	static Patient patientResult;
	static List<Patient> patientResultList;
	boolean tableLoadIndicator = false;
	boolean tableLoadIndicatorList = false;
	FieldValidation fieldValidation = new FieldValidation();
	PatientCreation patientCreation = new PatientCreation();
	/**
	 * Open the window.
	 */
	public void open(Display display) {
		this.display = display;
		createContents(display);
		shlPatientLocator.open();
		while (!shlPatientLocator.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	public PatientLocator(Display display) {
		open(display);
	}

	/**
	 * Create contents of the window.
	 * 
	 * @param display2
	 */
	protected void createContents(Display display2) {

		shlPatientLocator = new Shell(display);
		shlPatientLocator.setMinimumSize(new Point(700, 500));
		shlPatientLocator.setText("Patient Locator");
		shlPatientLocator.setSize(450, 300);

		Composite composite = new Composite(shlPatientLocator, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(187, 221, 255));
		composite.setBounds(27, 21, 624, 39);

		Label pageTitle = new Label(composite, SWT.NONE);
		pageTitle.setFont(SWTResourceManager.getFont("Microsoft Himalaya", 21, SWT.NONE));
		pageTitle.setBackground(SWTResourceManager.getColor(224, 255, 255));
		pageTitle.setBounds(10, 10, 202, 22);
		pageTitle.setText("Patient Locator Page");

		Label SearchPatientByLabel = new Label(shlPatientLocator, SWT.NONE);
		SearchPatientByLabel.setBounds(29, 92, 100, 15);
		SearchPatientByLabel.setText("Search Patient By:");

		searchTextField = new Text(shlPatientLocator, SWT.BORDER);
		searchTextField.setBounds(282, 89, 153, 21);

		Button radioButtonId = new Button(shlPatientLocator, SWT.RADIO);
		radioButtonId.setBounds(135, 91, 31, 16);
		radioButtonId.setText("Id");

		Button radioButtonFirstName = new Button(shlPatientLocator, SWT.RADIO);
		radioButtonFirstName.setBounds(172, 91, 90, 16);
		radioButtonFirstName.setText("First Name");

		Button btnView = new Button(shlPatientLocator, SWT.NONE);
		btnView.setText("View");
		btnView.setBounds(586, 346, 75, 25);
		btnView.setEnabled(false);

		/**
		 * Listener for View Button
		 * 
		 */
		btnView.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("View button clicked");
				Patient patient = null;
				TableItem[] item = table.getSelection();
				for (TableItem itemTable : item) {
				       System.out.println(itemTable.getText(0));

					try {
						System.out.println("Patient ID  " + Long.valueOf(itemTable.getText(0)));

						patient = HttpRestClient.findPatientById(Long.valueOf(itemTable.getText(0)));
						patientCreation.populatePatientForm(patient, false);
						shlPatientLocator.close();
					} catch (NumberFormatException | IOException | InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		Button btnModify = new Button(shlPatientLocator, SWT.NONE);
		btnModify.setBounds(586, 377, 75, 25);
		btnModify.setText("Modify");
		btnModify.setEnabled(false);

		/**
		 * Listener for Modify Button
		 * 
		 */
		btnModify.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Modify button clicked");
				TableItem[] item = table.getSelection();
				for (TableItem itemTable : item) {
					System.out.println("before itemTable.getText(0) ");
					System.out.println(itemTable.getText(0));
					System.out.println(itemTable.getText(1));

					try {
						Patient patient = HttpRestClient.findPatientById(new Long(itemTable.getText(0)));
						patientCreation.populatePatientForm(patient, true);
						shlPatientLocator.close();
					} catch (NumberFormatException | IOException | InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		Button btnDelete = new Button(shlPatientLocator, SWT.NONE);
		btnDelete.setText("Delete");
		btnDelete.setBounds(586, 408, 75, 25);
		btnDelete.setEnabled(false);

		/**
		 * Listener for Delete Button
		 * 
		 */
		btnDelete.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Delete button clicked");
				TableItem[] item = table.getSelection();
				for (TableItem itemTable : item) {
					System.out.println("before itemTable.getText(0) ");
					System.out.println(itemTable.getText(0));
					System.out.println(itemTable.getText(1));

					try {

						HttpResponse<String> response = HttpRestClient.deletePatient(new Long(itemTable.getText(0)));

						if (response.statusCode() == 200) {
							System.out.println("Deleted the data");
							btnModify.setEnabled(false);
							createDialogueBoxMsg(shlPatientLocator, "Patient Sucessfully Deleted.");

						} else {
							createDialogueBox(shlPatientLocator, "Failed to Delete Data.");
						}

						shlPatientLocator.close();
					} catch (NumberFormatException | IOException | InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		Button searchButton = new Button(shlPatientLocator, SWT.NONE);
		searchButton.setBounds(441, 87, 75, 25);
		searchButton.setText("Search");
		/**
		 * Listener for Search Button
		 * 
		 */
		searchButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				// Vadiation for searchTextField

				btnDelete.setEnabled(false);
				btnModify.setEnabled(false);
				btnView.setEnabled(false);

				boolean isValidation = true;

				// if no Radio button selected.
				if (radioButtonId.getSelection() == false && radioButtonFirstName.getSelection() == false) {
					System.out.println("No button has been selected");
					isValidation = false;
					createDialogueBox(shlPatientLocator, "Please Select one of Option from Search By");
				}

				else if (searchTextField.getText() == null || searchTextField.getText().isEmpty()
						|| searchTextField.getText().equalsIgnoreCase("")) {

					isValidation = false;
					createDialogueBox(shlPatientLocator, "Please Enter search criteria");

				}

				else if (radioButtonId.getSelection()) {

					if (!fieldValidation.validateOnlyNumeric(searchTextField.getText())) {
						isValidation = false;
						createDialogueBox(shlPatientLocator, "Please enter valid ID");
					}
				}

				else if (radioButtonFirstName.getSelection()) {

					if (!fieldValidation.validateOnlyAlphabet(searchTextField.getText())) {
						isValidation = false;
						createDialogueBox(shlPatientLocator, "Please enter valid First Name");
					}
				}

				if (isValidation) {
					if (radioButtonId.getSelection()) {
						System.out.println("We are searching by ID " + searchTextField.getText());
						try {
							long iInLong = Long.parseLong(searchTextField.getText());

							patientResult = HttpRestClient.findPatientById(iInLong);

							tableLoadIndicator = true;

						} catch (IOException e1) {
							System.out.println("Inside catch");
							/*
							 * if (table != null && table.getItemCount() > 0) {
							 * System.out.println("Inside Table Clear"); table.clearAll();
							 * table.removeAll(); TableColumn[] column = table.getColumns(); for (int i = 0;
							 * i <= column.length - 1; i++) { column[i].dispose(); } TableItem[] items =
							 * table.getItems(); for (int i = 0; i <= items.length - 1; i++) {
							 * items[i].dispose(); } }
							 */
							tableLoadIndicator = false;
							createDialogueBoxMsg(shlPatientLocator, "No Search Result Found");
							e1.printStackTrace();
						} catch (InterruptedException e1) {

							e1.printStackTrace();
						} catch (NumberFormatException e1) {
							e1.printStackTrace();
						}
					} else if (radioButtonFirstName.getSelection()) {

						System.out.println("We are calling by First Name" + searchTextField.getText());
						try {
							List<Patient> getPatientByNameList = HttpRestClient
									.findPatientByName(searchTextField.getText());
							patientResultList = getPatientByNameList;

							displayTableFromList(patientResultList, btnDelete, btnModify, btnView);
						} catch (IOException e1) {
							createDialogueBoxMsg(shlPatientLocator, "No Search Result Found");
							e1.printStackTrace();
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
					}
				}
				

				if (tableLoadIndicator) {
					
					if (table != null && table.getItemCount() > 0) {
						System.out.println("Inside Table Clear");
						table.clearAll();
						table.removeAll();
						TableColumn[] column = table.getColumns();
						for (int i = 0; i <= column.length - 1; i++) {
							column[i].dispose();
						}
						TableItem[] items = table.getItems();
						for (int i = 0; i <= items.length - 1; i++) {
							items[i].dispose();
						}
					} else {
						table = new Table(shlPatientLocator, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
					}
					table.setBounds(23, 146, 540, 274);
					table.setHeaderVisible(true);
					table.setLinesVisible(true);
					GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
					data.heightHint = 200;
					table.setLayoutData(data);
					String[] titles = { "Patient Id", "First Name", "Last Name", "Gender", "Date Of Birth",
							"Telephone Id1", "Telephone Type1", "Telephone Country Code1", "Telephone Number1",
							"Telephone Id2", "Telephone Type2", "Telephone Country Code2", "Telephone Number2",
							"Telephone Id3", "Telephone Type3", "Telephone Country Code3", "Telephone Number3",
							"Address Id1", "Address Type1", "Street1", "City1", "State1", "Zip Code1", "Country1",
							"Address Id2", "Address Type2", "Street2", "City2", "State2", "Zip Code2", "Country2", };
					for (int i = 0; i < titles.length; i++) {
						TableColumn column = new TableColumn(table, SWT.NONE);
						column.setText(titles[i]);
						table.getColumn(i).pack();
					}

					TableItem loadItem = new TableItem(table, SWT.NONE);
					// set data in table
					loadItem.setText(0, patientResult.getPatientId().toString());
					loadItem.setText(1, patientResult.getFirstName().toString());
					loadItem.setText(2, patientResult.getLastName().toString());
					loadItem.setText(3, patientResult.getGender().toString());
					loadItem.setText(4, patientResult.getDateOfBirth().toString());

					if (patientResult.getContactNoDetails().size() > 0) {
						loadItem.setText(5, patientResult.getContactNoDetails().get(0).getId().toString());
						loadItem.setText(6, patientResult.getContactNoDetails().get(0).getTeleType().toString());
						loadItem.setText(7, patientResult.getContactNoDetails().get(0).getCountyCode().toString());
						loadItem.setText(8, patientResult.getContactNoDetails().get(0).getTeleNo().toString());
					}
					if (patientResult.getContactNoDetails().size() > 1) {
						loadItem.setText(9, patientResult.getContactNoDetails().get(1).getId().toString());
						loadItem.setText(10,
								patientResult.getContactNoDetails().get(1).getTeleType().toString().toString());
						loadItem.setText(11, patientResult.getContactNoDetails().get(1).getCountyCode().toString());
						loadItem.setText(12, patientResult.getContactNoDetails().get(1).getTeleNo().toString());

					}
					System.out.println("Size of contact details" + patientResult.getContactNoDetails().size());
					if (patientResult.getContactNoDetails().size() > 2) {
						loadItem.setText(13, patientResult.getContactNoDetails().get(2).getId().toString());
						loadItem.setText(14, patientResult.getContactNoDetails().get(2).getTeleType().toString());
						loadItem.setText(15, patientResult.getContactNoDetails().get(2).getCountyCode().toString());
						loadItem.setText(16, patientResult.getContactNoDetails().get(2).getTeleNo().toString());
					}
					if (patientResult.getAddresses().size() > 0) {
						loadItem.setText(17, patientResult.getAddresses().get(0).getId().toString());
						loadItem.setText(18, patientResult.getAddresses().get(0).getAddressType().toString());
						loadItem.setText(19, patientResult.getAddresses().get(0).getStreetAddress().toString());
						loadItem.setText(20, patientResult.getAddresses().get(0).getCity().toString());
						loadItem.setText(21, patientResult.getAddresses().get(0).getState().toString());
						loadItem.setText(22, patientResult.getAddresses().get(0).getZipCode().toString());
						loadItem.setText(23, patientResult.getAddresses().get(0).getCountry().toString());
					}

					if (patientResult.getAddresses().size() > 1) {
						loadItem.setText(24, patientResult.getAddresses().get(1).getId().toString());
						loadItem.setText(25, patientResult.getAddresses().get(1).getAddressType().toString());
						loadItem.setText(26, patientResult.getAddresses().get(1).getStreetAddress().toString());
						loadItem.setText(27, patientResult.getAddresses().get(1).getCity().toString());
						loadItem.setText(28, patientResult.getAddresses().get(1).getState().toString());
						loadItem.setText(29, patientResult.getAddresses().get(1).getZipCode().toString());
						loadItem.setText(30, patientResult.getAddresses().get(1).getCountry().toString());
					}

					table.addListener(SWT.Selection, new Listener() {
						@Override
						public void handleEvent(Event event) {
							System.out.println("Selected a Row");
							TableItem selectedItem = (TableItem) event.item;
							if (selectedItem != null) {
								
								btnDelete.setEnabled(true);
								btnModify.setEnabled(true);
								btnView.setEnabled(true);	
								}
							}
                      	});

					for (int i = 0; i < titles.length; i++) {
						table.getColumn(i).pack();
					}

				}
			}

		});
		
		Button viewAllPatientButton = new Button(shlPatientLocator, SWT.NONE);
		viewAllPatientButton.setBounds(551, 87, 100, 25);
		viewAllPatientButton.setText("View All Patient");
		/**
		 * Listener for ViewAllPatient Button
		 * 
		 */
		viewAllPatientButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				btnDelete.setEnabled(false);
				btnModify.setEnabled(false);
				btnView.setEnabled(false);

				try {
					List<Patient> getAllPatientList = HttpRestClient.findAllPatient();
					displayTableFromList(getAllPatientList, btnDelete, btnModify, btnView);
				} catch (IOException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	

	}
	/**
	 * Common method for pop up Dialogue Box with Failure Message.
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
	public static void createDialogueBoxMsg(Shell shell, String message) {
		MessageBox msgBox = new MessageBox(shell);
		msgBox.setText("Message");
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
	 * Common method for search By name and search all patient which returns List. Below method will iterate and set data. 
	 * 
	 */

	private void displayTableFromList(List<Patient> getPatientByNameList, Button btnDelete, Button btnModify,
			Button btnView) {
		if (table != null && table.getItemCount() > 0) {
			table.clearAll();
			table.removeAll();
			TableColumn[] column = table.getColumns();
			for (int i = 0; i <= column.length - 1; i++) {
				column[i].dispose();
			}
			TableItem[] items = table.getItems();
			for (int i = 0; i <= items.length - 1; i++) {
				items[i].dispose();
			}
		} else {
			table = new Table(shlPatientLocator, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		}
		
		table.setBounds(23, 146, 540, 274);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.heightHint = 200;
		table.setLayoutData(data);
		String[] titles = { "Patient Id", "First Name", "Last Name", "Gender", "Date Of Birth", "Telephone Id1",
				"Telephone Type1", "Telephone Country Code1", "Telephone Number1", "Telephone Id2", "Telephone Type2",
				"Telephone Country Code2", "Telephone Number2", "Telephone Id3", "Telephone Type3",
				"Telephone Country Code3", "Telephone Number3", "Address Id1", "Address Type1", "Street1", "City1",
				"State1", "Zip Code1", "Country1", "Address Id2", "Address Type2", "Street2", "City2", "State2",
				"Zip Code2", "Country2", };
		for (int i = 0; i < titles.length; i++) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(titles[i]);
			table.getColumn(i).pack();
		}

		// set data in table

		for (Patient patientResult : getPatientByNameList) {
			TableItem loadItem = new TableItem(table, SWT.NONE);
			try {
				loadItem.setText(new String[] {

						patientResult.getPatientId().toString(), patientResult.getFirstName().toString(),
						patientResult.getLastName().toString(), patientResult.getGender().toString(),
						patientResult.getDateOfBirth().toString(),

						patientResult.getContactNoDetails().get(0) != null
								? patientResult.getContactNoDetails().get(0).getId().toString()
								: "",
						patientResult.getContactNoDetails().get(0) != null
								? patientResult.getContactNoDetails().get(0).getTeleType().toString()
								: "",
						patientResult.getContactNoDetails().get(0) != null
								? patientResult.getContactNoDetails().get(0).getCountyCode().toString()
								: "",
						patientResult.getContactNoDetails().get(0) != null
								? patientResult.getContactNoDetails().get(0).getTeleNo().toString()
								: "",

						patientResult.getContactNoDetails().get(1) != null
								? patientResult.getContactNoDetails().get(1).getId().toString()
								: "",
						patientResult.getContactNoDetails().get(1) != null
								? patientResult.getContactNoDetails().get(1).getTeleType().toString()
								: "",
						patientResult.getContactNoDetails().get(1) != null
								? patientResult.getContactNoDetails().get(1).getCountyCode().toString()
								: "",
						patientResult.getContactNoDetails().get(1) != null
								? patientResult.getContactNoDetails().get(1).getTeleNo().toString()
								: "",

						patientResult.getContactNoDetails().get(2) != null
								? patientResult.getContactNoDetails().get(2).getId().toString()
								: "",
						patientResult.getContactNoDetails().get(2) != null
								? patientResult.getContactNoDetails().get(2).getTeleType().toString()
								: "",
						patientResult.getContactNoDetails().get(2) != null
								? patientResult.getContactNoDetails().get(2).getCountyCode().toString()
								: "",
						patientResult.getContactNoDetails().get(2) != null
								? patientResult.getContactNoDetails().get(2).getTeleNo().toString()
								: "",

						patientResult.getAddresses().get(0) != null
								? patientResult.getAddresses().get(0).getId().toString()
								: "",
						patientResult.getAddresses().get(0) != null
								? patientResult.getAddresses().get(0).getAddressType().toString()
								: "",
						patientResult.getAddresses().get(0) != null
								? patientResult.getAddresses().get(0).getStreetAddress().toString()
								: "",
						patientResult.getAddresses().get(0) != null
								? patientResult.getAddresses().get(0).getCity().toString()
								: "",
						patientResult.getAddresses().get(0) != null
								? patientResult.getAddresses().get(0).getState().toString()
								: "",
						patientResult.getAddresses().get(0) != null
								? patientResult.getAddresses().get(0).getZipCode().toString()
								: "",
						patientResult.getAddresses().get(0) != null
								? patientResult.getAddresses().get(0).getCountry().toString()
								: "",

						patientResult.getAddresses().get(1) != null
								? patientResult.getAddresses().get(1).getId().toString()
								: "",
						patientResult.getAddresses().get(1) != null
								? patientResult.getAddresses().get(1).getAddressType().toString()
								: "",
						patientResult.getAddresses().get(1) != null
								? patientResult.getAddresses().get(1).getStreetAddress().toString()
								: "",
						patientResult.getAddresses().get(1) != null
								? patientResult.getAddresses().get(1).getCity().toString()
								: "",
						patientResult.getAddresses().get(1) != null
								? patientResult.getAddresses().get(1).getState().toString()
								: "",
						patientResult.getAddresses().get(1) != null
								? patientResult.getAddresses().get(1).getZipCode().toString()
								: "",
						patientResult.getAddresses().get(1) != null
								? patientResult.getAddresses().get(1).getCountry().toString()
								: "",

				});

			} catch (Exception e) {
				e.printStackTrace();
			}
			table.addListener(SWT.Selection, new Listener() {
				@Override
				public void handleEvent(Event event) {
					TableItem selectedItem = (TableItem) event.item;
					if (selectedItem != null) {
						System.out.println("Selected a Row");
						btnDelete.setEnabled(true);
						btnModify.setEnabled(true);
						btnView.setEnabled(true);

					}

				}

			});

			for (int i = 0; i < titles.length; i++) {
				table.getColumn(i).pack();
			}

		}

	}
}
