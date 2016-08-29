import config.Settings;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;


/**
 * Created by Oscar on 2016-06-30.
 */
public class Main extends Application implements KeyListener {

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX(primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() - 900);
        primaryStage.setY(0);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Reddit News");
        this.primaryStage = primaryStage;
        update(this.primaryStage);
    }

    public static void update(Stage primaryStage){

        String[] subreddits = Settings.readFile();
        Scene scene = new Scene(Layout.setUpGrid(subreddits[0], subreddits[1]), 900, 2000);
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}