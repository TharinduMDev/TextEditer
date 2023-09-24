package lk.ijse.dep11;

import com.sun.glass.ui.CommonDialogs;
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
import java.lang.reflect.AnnotatedArrayType;
import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
//
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save the file");
        fileChooser.setInitialFileName("Untitled Document.txt");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Text Files (*.txt, *.html)", "*.txt", "*.html"));
        File file = fileChooser.showSaveDialog(txtTextEditor.getScene().getWindow());
        currentFile = file;
        if(file == null)
            return;
        saveText(currentFile);
    }

    public void mnSaveOnAction(ActionEvent actionEvent) throws IOException {
        if(currentFile == null){
            mnSaveAsOnAction(actionEvent);
        }else {
            saveText(currentFile);
        }
    }

    public void saveText(File file) throws IOException {
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(os);
            try {
                String htmlText1 = "<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: &quot;&quot;;\">";
                String htmlText2 = "</span></p></body></html>";
                Pattern pat = Pattern.compile("</span></p><p><span style=\"font-family: &quot;&quot;;\">");
                String text = txtTextEditor.getHtmlText().substring(htmlText1.length(),txtTextEditor.getHtmlText().length()-htmlText2.length());
                Matcher matcher = pat.matcher(text);
                while (matcher.find()) {

                    text = text.substring(0,matcher.start())+ text.substring(matcher.end(),text.length());
                }
                System.out.println(text);
                bos.write(text.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                bos.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String text = "";
        String read1 = "";
        while ((read1 = bufferedReader.readLine()) != null){
//            System.out.println(read1);
            text += read1+"</span></p><p><span style=\"font-family: &quot;&quot;;\">";

        }
        System.out.println(text);
        txtTextEditor.setHtmlText(text);
        bufferedReader.close();
    }
}
