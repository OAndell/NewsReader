import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;

import java.awt.*;
import java.net.URL;

/**
 * Created by Oscar on 2016-07-06.
 */
public class Layout {

    public static GridPane setUpGrid(String subreddit1, String subreddit2){
        GridPane grid = new GridPane();

        grid.setPadding(new Insets(0,0,0,0));
        grid.setVgap(8);
        grid.setHgap(10);

        GridPane gridLeft = setupSubGrid(grid, subreddit2);
        GridPane.setConstraints(gridLeft,0,0);
        GridPane gridRight = setupSubGrid(grid, subreddit1);
        GridPane.setConstraints(gridLeft,1,0);

        //BackgroundImage
        grid.setStyle("-fx-background-image: url('graphics/background4.jpg')");

        //exitbutton
        Label exit = new Label("X");
        GridPane.setConstraints(exit,0,0);
        exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Platform.exit();
            }
        });
        //gridRight.getChildren().add(exit);
        grid.getChildren().addAll(gridLeft, gridRight);
        return grid;
    }

    private static GridPane setupSubGrid(GridPane mainGrid, String subreddit){
        GridPane subGrid = new GridPane();
        subGrid.setPadding(new Insets(18,0,0,0));
        subGrid.setVgap(8);
        subGrid.setHgap(10);

        Text title = new Text("r/" + subreddit);
        GridPane.setConstraints(title,1,1);
        title.setFont(javafx.scene.text.Font.font("Verdana", FontPosture.ITALIC, 30));
        Label underscore = new Label();
        underscore.setStyle("-fx-background-color: #000000;");
        underscore.setMaxSize(420,1);
        underscore.setFont(javafx.scene.text.Font.font("Verdana", FontPosture.ITALIC, 1));
        GridPane.setConstraints(underscore,1,2);
        //breadtext
        RedditScraper scraper = new RedditScraper(subreddit);
        boolean hasDownloaded = false;
        while (!hasDownloaded) {
            if(scraper.scrape()){
                for (int i = 0; i < scraper.getOutList().size(); i++) {
                    subGrid.getChildren().add(createLabel(1, 3 + i, scraper.getOutList().get(i), scraper.getLinkList().get(i)));
                }
                subGrid.getChildren().addAll(title, underscore);
                hasDownloaded = true;
            }else{
                try {
                    Thread.sleep(50000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        return subGrid;
    }

    private static Label createLabel(int x, int y, String text, final String link){
        Label label = new Label();
        label.setMaxSize(420, 1000);
        label.setWrapText(true);
        label.setStyle("-fx-font: 16 arial;" + "-fx-background-color: #F3F1F1;" + "-fx-border-color: #C9C5C5;");
        label.setText(text);
        GridPane.setConstraints(label, x, y);
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    Desktop.getDesktop().browse(new URL(link).toURI());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return label;
    }
}
