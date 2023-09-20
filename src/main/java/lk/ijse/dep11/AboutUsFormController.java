package lk.ijse.dep11;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class AboutUsFormController {


    public AnchorPane rootAboutUs;
    public Button btnClose;
    public ImageView aboutRoot;
    private double xOffset = 0;
    private double yOffset = 0;


//

    public void rootAboutUsOnMouseDragged(MouseEvent event) {
        Stage Stage = (Stage) rootAboutUs.getScene().getWindow();
//        FadeTransition fadeTransition = new FadeTransition(Duration.millis(700),rootAboutUs);
//        fadeTransition.setFromValue(0);
//        fadeTransition.setToValue(1);
//        fadeTransition.playFromStart();

        Stage.setX(event.getScreenX() - xOffset);
        Stage.setY(event.getScreenY() - yOffset);
    }

    public void rootAboutUsOnMousePressed(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    public void btnCloseOnAction(ActionEvent actionEvent) {
        Stage Stage = (Stage) rootAboutUs.getScene().getWindow();
        Stage.close();
    }
}
