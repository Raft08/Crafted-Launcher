package be.raft.launcher.ui;

import be.raft.launcher.ui.window.SplashScreen;
import com.jthemedetecor.OsThemeDetector;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LauncherApplication extends Application {
    public static final Image ICON = new Image("assets/img/icon.png");
    public static final String CSS = "assets/css/launcher.css";
    public static final OsThemeDetector THEME_DETECTOR = OsThemeDetector.getDetector();

    @Override
    public void start(Stage unused) {
        this.determineTheme();

        SplashScreen splashScreen = new SplashScreen();


    }

    private void determineTheme() {
        // Automatic System Theme
        THEME_DETECTOR.registerListener(isDark -> {
            if (System.getProperty("crafted.ui.theme.selection").equals("manual"))
                return;

            this.updateTheme(isDark);
        });
        this.updateTheme(THEME_DETECTOR.isDark());
    }

    public void updateTheme(boolean isDark) {
        if (isDark)
            System.setProperty("crafted.ui.theme", "dark");
        else
            System.setProperty("crafted.ui.theme", "light");
    }
}
