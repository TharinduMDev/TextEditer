package lk.ijse.dep11;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AboutUsFormController {


    public AnchorPane rootAboutUs;
    private double xOffset = 0;
    private double yOffset = 0;


//

    public void rootAboutUsOnMouseDragged(MouseEvent event) {
        Stage Stage = (Stage) rootAboutUs.getScene().getWindow();
        Stage.setX(event.getScreenX() - xOffset);
        Stage.setY(event.getScreenY() - yOffset);
    }

    public void rootAboutUsOnMousePressed(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }
}
