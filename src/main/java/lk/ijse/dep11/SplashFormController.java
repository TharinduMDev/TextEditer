package lk.ijse.dep11;

import com.sun.tools.javac.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class SplashFormController {

    public AnchorPane splashSreenRoot;
    public Label lblLoad;

    public void  initialize() throws IOException, InterruptedException {
        Timeline timeline = new Timeline();
        KeyFrame keyFrame1 = new KeyFrame(Duration.millis(500), event ->{
            lblLoad.setText("Application is being initialized...");
        });

        KeyFrame keyFrame2 = new KeyFrame(Duration.millis(1500), event -> {
            lblLoad.setText("Setting up Tools...");
        });

        KeyFrame keyFrame3 = new KeyFrame(Duration.millis(2500), event -> {
            lblLoad.setText("Setting up UI...");
        });

        KeyFrame keyFrame4 = new KeyFrame(Duration.millis(3000), event -> {

            try {
                AnchorPane Mainroot = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
                Scene MainScene = new Scene(Mainroot);

                Stage MainStage = new Stage();
                MainStage.setScene(MainScene);
                MainStage.setTitle("Simple Text Editor - version 1.0.0");
                MainStage.setResizable(false);
                MainStage.centerOnScreen();
                MainStage.show();
                lblLoad.getScene().getWindow().hide();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        timeline.getKeyFrames().addAll(keyFrame1, keyFrame2, keyFrame3, keyFrame4);
        timeline.playFromStart();




//        Stage primaryStage;
//        primaryStage = (Stage) scene1root.getScene().getWindow();
//        primaryStage.setScene(scene2);
//        primaryStage.sizeToScene();



    }

}
