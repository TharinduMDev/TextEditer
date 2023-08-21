package lk.ijse.dep11;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainFormController {
    public MenuItem mnExit;
    public MenuItem mnUserGuide;
    public MenuItem mnAboutUs;

    public void mExitOnAction(ActionEvent actionEvent) {

        System.exit(0);
    }

    public void mnUserGuideOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("/view/UserGuideForm.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(root.getScene().getWindow());
        stage.setScene(scene);
        stage.setTitle("User Guide");
        stage.centerOnScreen();
        stage.setResizable(true);
        stage.show();
    }

    public void mnAboutUsOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("/view/AboutUsForm.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        Stage stage = new Stage(StageStyle.TRANSPARENT);
        root.setBackground(Background.fill(Color.TRANSPARENT));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(root.getScene().getWindow());
        stage.setScene(scene);
        stage.setTitle("About Us");
        stage.centerOnScreen();
        stage.setResizable(true);
        stage.show();

    }
}
