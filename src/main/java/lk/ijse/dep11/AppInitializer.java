package lk.ijse.dep11;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws  Exception{

        AnchorPane root = FXMLLoader.load(getClass().getResource("/view/SplashForm.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        Stage stage = new Stage(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.setTitle("Text Editor");
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();

    }
}
