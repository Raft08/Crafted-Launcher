package be.raft.launcher;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CraftedLauncher extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new GridPane());
        stage.setScene(scene);
        stage.setTitle("Crafted Launcher");

        stage.show();
    }
}
