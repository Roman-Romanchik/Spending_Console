package org.aren_rend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    public static void main(String[] args) {
		launch(args);
    }

	@Override
	public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/main_menu.fxml")));
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}