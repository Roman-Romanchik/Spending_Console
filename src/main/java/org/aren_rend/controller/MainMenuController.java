package org.aren_rend.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

import org.aren_rend.model.MainMenuModel;
import org.aren_rend.utilities.Validator;
import org.springframework.stereotype.Component;

@Component
public class MainMenuController implements Initializable {
	@FXML
	private Button addButton, applyButton;
	@FXML
	private TextField fieldSpendingName, fieldSpendingPrice, fieldForOther, fieldDirectorySaveFile;
	@FXML
	private CheckBox checkBoxFood, checkBoxSport, checkBoxOther;
	@FXML
	private Label labelMonthSpending, labelAllSpending;
	@FXML
	private ListView<String> listViewForNotes;
	private static Path filePath;
	private final ObservableList<String> notes = FXCollections.observableArrayList();
	MainMenuModel mmm;
	Validator validator;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		validator = new Validator();
		mmm = new MainMenuModel();
		applyDirectory();
		loadCheckBoxes();
	}

	private void loadData() {
		try {
			List<String> lines = Files.readAllLines(filePath);
			notes.addAll(lines);
			listViewForNotes.setItems(notes);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private String category = "";

	private void loadCheckBoxes() {
		checkBoxFood.selectedProperty().addListener((obs, oldVal, newVal) -> {
			if(newVal) {
				category = "Food";
				checkBoxSport.setSelected(false);
				checkBoxSport.setDisable(true);
				checkBoxOther.setSelected(false);
				checkBoxOther.setDisable(true);
				fieldForOther.setDisable(true);
			} else {
				checkBoxSport.setDisable(false);
				checkBoxOther.setDisable(false);
				fieldForOther.setDisable(false);
			}
		});
		checkBoxSport.selectedProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal) {
				category = "Sport";
				checkBoxFood.setSelected(false);
				checkBoxFood.setDisable(true);
				checkBoxOther.setSelected(false);
				checkBoxOther.setDisable(true);
				fieldForOther.setDisable(true);
			} else {
				checkBoxFood.setDisable(false);
				checkBoxOther.setDisable(false);
				fieldForOther.setDisable(false);
			}
		});
		checkBoxOther.selectedProperty().addListener((obs, oldVal, newVal) -> {
			if(newVal) {
				checkBoxSport.setSelected(false);
				checkBoxSport.setDisable(true);
				checkBoxFood.setSelected(false);
				checkBoxFood.setDisable(true);

			} else {
				checkBoxSport.setDisable(false);
				checkBoxFood.setDisable(false);
			}
		});
	}

	@FXML
	private void addNote() {
		String fieldOther = fieldForOther.getText();
		String fieldName = fieldSpendingName.getText();
		String fieldPrice = fieldSpendingPrice.getText();
		boolean isValidFieldOther = validator.isString(fieldOther);
		boolean isValidFieldName = validator.isString(fieldName);
		boolean isValidFieldPrice = validator.isNumber(fieldPrice);
		if(isValidFieldName && isValidFieldPrice) {
			if(checkBoxOther.isSelected() && isValidFieldOther) {
				notes.add(mmm.makeNote(fieldOther, fieldName, fieldPrice, filePath));
			} else {
				notes.add(mmm.makeNote(category, fieldName, fieldPrice, filePath));
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Error");
			alert.setContentText("Wrong field parameter!");
			alert.show();
		}
		addButton.setText(mmm.changeButtonText());
		updateSpendingSum();
	}

	private void updateSpendingSum() {
		labelAllSpending.setText(mmm.getAllSpending(notes));
		labelMonthSpending.setText(mmm.getMonthSpending(notes));
	}

	@FXML
	private void applyDirectory() {
		notes.clear();
		String directory = fieldDirectorySaveFile.getText();
		filePath = Paths.get(fieldDirectorySaveFile.getPromptText());
		if(!directory.trim().isEmpty()) {
			filePath = Paths.get(directory);
		}
		loadData();
		updateSpendingSum();
		applyButton.setText("Applied!");
	}
}
