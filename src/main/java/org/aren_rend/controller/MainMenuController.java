package org.aren_rend.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

import org.aren_rend.model.MainMenuModel;

public class MainMenuController implements Initializable {
	@FXML
	private Button addButton;
	@FXML
	private TextField fieldSpendingName;
	@FXML
	private TextField fieldSpendingPrice;
	@FXML
	private TextField fieldForOther;
	@FXML
	private CheckBox checkBoxFood;
	@FXML
	private CheckBox checkBoxSport;
	@FXML
	private CheckBox checkBoxOther;
	@FXML
	private Label labelWeekSpending;
	@FXML
	private Label labelMonthSpending;
	@FXML
	private Label labelAllSpending;
	@FXML
	private ListView<String> listViewForNotes;

	private final ObservableList<String> notes = FXCollections.observableArrayList();

	private static final Path filePath = Paths.get("/home/aren_rend/Programs/1_Intellij_Idea/Projects/Spending_Console/SaveInformation.txt");
	MainMenuModel mmm = new MainMenuModel();

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		loadData();
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
		addButton.setText(mmm.changeButtonText());
		if(checkBoxOther.isSelected()) {
			notes.add(mmm.makeNote(fieldForOther.getText(), fieldSpendingName.getText(), fieldSpendingPrice.getText()));
		} else {
			notes.add(mmm.makeNote(category, fieldSpendingName.getText(), fieldSpendingPrice.getText()));
		}

		updateSpendingSum();
	}

	private void updateSpendingSum() {
		labelAllSpending.setText(mmm.getAllSpending(notes));
		labelMonthSpending.setText(mmm.getMonthSpending(notes));
		labelWeekSpending.setText(mmm.getWeekSpending(notes));
	}
}
