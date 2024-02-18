package be.raft.launcher.ui.panel;

import be.raft.launcher.ui.Text;
import be.raft.launcher.ui.window.SplashScreen;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class SplashPanel extends GridPane {
    private final Image img;
    private final ProgressBar progressBar;
    private final Label statusLabel;

    public SplashPanel(Image img) {
        this.img = img;
        this.progressBar = new ProgressBar();
        this.statusLabel = new Label(Text.translate("splash.loading.generic"));

        this.initialize();
    }


    private void initialize() {
        this.setBackground(new Background(new BackgroundImage(this.img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(
                        this.img.getWidth(),
                        this.img.getHeight(),
                        true,
                        true,
                        true,
                        true
                )
        )));

        Label title = new Label(Text.translate("splash.title"));
        title.setTranslateY(100);
        title.setFont(Text.getFont("Avenir-Black", 50));
        title.setStyle("-fx-padding: 20;");

        // We add 20 and offset it a bit to make sure the round borders of the bar aren't visible.
        // Precision isn't required on the progress bar (first or last 5%) aren't really important.
        // It's only to send a visual feedback to the user that the application didn't stop loading.
        this.progressBar.setTranslateY(140);
        this.progressBar.setMinWidth(SplashScreen.WIDTH + 20);
        this.progressBar.setTranslateX(-10);

        this.statusLabel.setTranslateY(165);
        this.statusLabel.setFont(Text.getFont("Avenir-Heavy", 20));
        this.statusLabel.setStyle("-fx-padding: 25;");


        this.getChildren().addAll(title, this.progressBar, this.statusLabel);
    }

    public void setProgress(double progress) {
        this.progressBar.setProgress(progress);
    }

    public double getProgress() {
        return this.progressBar.getProgress();
    }

    public DoubleProperty progressProperty() {
        return this.progressBar.progressProperty();
    }

    public void updateStatus(String loadingStatusKey) {
        this.statusLabel.setText(Text.translate(loadingStatusKey));
    }
}
