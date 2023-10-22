package gui;

import edu.esprit.entities.Personne;
import edu.esprit.services.PersonneCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {

    @FXML
    private TextField emaillog;
    @FXML
    private PasswordField mdplogin;
    @FXML
    private Button loginn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Vous pouvez initialiser des éléments si nécessaire
    }

    @FXML
    private void connecter() {
        String emailValue = emaillog.getText();
        String passwordValue = mdplogin.getText();
        
        // Vous pouvez ajouter du code pour vérifier si l'email et le mot de passe sont corrects
        PersonneCRUD PC = new PersonneCRUD();
        try {
            Personne personne = PC.authentifier(emailValue, passwordValue);
            
            if (personne != null) {
                // L'email et le mot de passe sont corrects
                String message = "Welcome, " + personne.getNom() + "!";
                afficherMessageInformation(message);
            } else {
                // L'email ou le mot de passe est incorrect
                afficherMessageErreur("Erreur", "Email ou mot de passe incorrect");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            afficherMessageErreur("Erreur", "Erreur de base de données");
        }
    }

    public void afficherMessageErreur(String titre, String contenu) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }

    public void afficherMessageInformation(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
