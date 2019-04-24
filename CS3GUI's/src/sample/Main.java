package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.ProgressBar;
import javafx.scene.shape.Rectangle;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        Button button = new Button("Kill me");
        ProgressBar bar = new ProgressBar();
        bar.setTranslateY(50);
        bar.setProgress(-1);

        Rectangle rec = new Rectangle(-50,50,100,100);
        StackPane epic = new StackPane();
        epic.getChildren().add(rec);
        epic.getChildren().add(button);
        epic.getChildren().add(bar);
        primaryStage.setScene(new Scene(epic, 300, 275));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
