package org.aren_rend;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class Main extends Application {
    private ConfigurableApplicationContext context;

    public static void main(String[] args) {
		launch(args);
    }

    @Override
    public void init() {
        this.context = new SpringApplicationBuilder(Main.class).run();
    }

	@Override
	public void start(Stage stage) {
		try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main_menu.fxml"));
            loader.setControllerFactory(context::getBean);

            Scene scene = new Scene(loader.load());

			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

    @Override
    public void stop() {
        context.close();
        Platform.exit();
    }
}