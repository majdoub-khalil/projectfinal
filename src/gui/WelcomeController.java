package gui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class WelcomeController {

    @FXML
    private Button sign;
    @FXML
    private Button login;
    @FXML
    private Text welcometxt;
    @FXML
    private ComboBox<String> languagebox;

    public void initialize() {
     
        languagebox.getItems().addAll("FR", "ENG");
       
        languagebox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("FR")) {
                welcometxt.setText("Bonjour, chère utilisateur");
            } else if (newValue.equals("ENG")) {
                welcometxt.setText("IHAVE BEEN TRANSLATED");
            }
        });
    }

    @FXML
    private void sign(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("interfacelogin.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void login(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void slectlanguage(ActionEvent event) {
      
    }
}
