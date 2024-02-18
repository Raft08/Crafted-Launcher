package be.raft.launcher.ui.window;

import be.raft.launcher.ui.LauncherApplication;
import be.raft.launcher.ui.Text;
import be.raft.launcher.ui.panel.SplashPanel;
import com.nativejavafx.taskbar.TaskbarProgressbar;
import com.nativejavafx.taskbar.TaskbarProgressbarFactory;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SplashScreen extends Stage {
    public static final int WIDTH = 700;
    public static final int HEIGHT = 400;
    private static final Image SPLASH_IMAGE_LIGHT = new Image("assets/img/background/splashscreen_light.jpg");
    private static final Image SPLASH_IMAGE_DARK = new Image("assets/img/background/splashscreen_dark.jpg");

    private final DoubleProperty progressProperty = new SimpleDoubleProperty(0.00);

    private final TaskbarProgressbar taskbar;

    public SplashScreen() {
        this.taskbar = TaskbarProgressbarFactory.getTaskbarProgressbar(this);

        this.initialize();
    }

    private void initialize() {
        this.progressProperty.addListener(((observable, oldValue, newValue) ->
                this.taskbar.showCustomProgress((Double) newValue, TaskbarProgressbar.Type.NORMAL)));

        Image backImage;
        if (System.getProperty("crafted.ui.theme").equals("dark"))
            backImage = SPLASH_IMAGE_DARK;
        else
            backImage = SPLASH_IMAGE_LIGHT;

        SplashPanel panel = new SplashPanel(backImage);
        panel.progressProperty().bindBidirectional(this.progressProperty);

        Scene scene = new Scene(panel);
        scene.getStylesheets().add(LauncherApplication.CSS);

        this.setScene(scene);
        this.initStyle(StageStyle.TRANSPARENT);

        this.setTitle("Crafted Launcher - " + Text.translate("window.splash.title_status"));
        this.getIcons().add(LauncherApplication.ICON);

        this.setWidth(WIDTH);
        this.setHeight(HEIGHT);

        this.show();
        this.setProgress(0.5);
    }

    public void setProgress(double value) {
        this.progressProperty.set(value);
    }

    public double getProgress() {
        return this.progressProperty.get();
    }

    public double getProgressProperty() {
        return this.progressProperty.get();
    }

    public DoubleProperty progressProperty() {
        return this.progressProperty;
    }
}
