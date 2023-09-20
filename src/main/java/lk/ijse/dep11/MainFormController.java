package lk.ijse.dep11;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.text.Document;
import java.io.*;
import java.util.Optional;

public class MainFormController {
    public MenuItem mnExit;
    public MenuItem mnUserGuide;
    public MenuItem mnAboutUs;
    public MenuItem mnNew;
    public AnchorPane root;
    public MenuItem mnSave;
    public HTMLEditor txtTextEditor;
    public MenuItem mnSaveAs;
    public  File currentFile = null;
    public MenuItem mnOpen;

    public void mExitOnAction(ActionEvent actionEvent) throws IOException {
        mnNewOAction(actionEvent);
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

    public void mnNewOAction(ActionEvent actionEvent) throws IOException {
        Optional<ButtonType> optButton = new Alert(Alert.AlertType.CONFIRMATION,"Do you want to save current file?",ButtonType.YES,ButtonType.NO,ButtonType.CANCEL).showAndWait();
        if(optButton.isEmpty() || optButton.get() == ButtonType.CANCEL){
            return;
        }
        if(optButton.get() == ButtonType.NO){
            txtTextEditor.setHtmlText("");
        }
        mnSaveOnAction(actionEvent);
        txtTextEditor.setHtmlText("");

    }

    public void mnSaveAsOnAction(ActionEvent actionEvent) throws  IOException{
//        System.out.println(txtTextEditor.);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save the file");
        fileChooser.setInitialFileName("Untitled Document.txt");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Text Files (*.txt, *.html)", "*.txt", "*.html"));
        File file = fileChooser.showSaveDialog(txtTextEditor.getScene().getWindow());
        if(file == null)
            return;

        FileOutputStream os = new FileOutputStream(file);
        BufferedOutputStream bos = new BufferedOutputStream(os);
        //Todo
        Document doc = (Document) Jsoup.parse(String.format(txtTextEditor.getHtmlText()));
       // Elements elements = doc.getAllElements();
//
//        StringBuilder plainText = new StringBuilder();
//
//        // Iterate through the elements and extract their text
//        for (Element element : elements) {
//            String text = element.ownText(); // Get the text of this element (excluding child elements)
//            if (!text.isEmpty()) {
//                plainText.append(text).append("\n");
//            }
//        }

//        String htmlContent = ;
//
//        bos.write(extractTextFromHTML(txtTextEditor.getHtmlText()));
        bos.close();


    }

    public void mnSaveOnAction(ActionEvent actionEvent) throws IOException {
        if(currentFile == null){
            mnSaveAsOnAction(actionEvent);
        }else {
            FileOutputStream os = new FileOutputStream(currentFile);
            BufferedOutputStream bos = new BufferedOutputStream(os);
            //Todo
            bos.close();
        }
    }

    public void mnOpenOnAction(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All text files(*.txt,*.html)","*.txt","*.html"));
        fileChooser.setTitle("Select file");
        File file = fileChooser.showOpenDialog(txtTextEditor.getScene().getWindow());
        if(file == null){
            return;
        }
        currentFile = file;
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        byte[] buffer = new byte[1024];
        int read = -1;
        while ((read = bis.read(buffer))!= -1){
            txtTextEditor.setHtmlText(new String(buffer,0,read));
        }
        bis.close();
    }
}
