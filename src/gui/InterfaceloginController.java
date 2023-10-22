package gui;

import edu.esprit.entities.Personne;
import edu.esprit.services.PersonneCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InterfaceloginController implements Initializable {

    @FXML
    private Button savebtn;
    @FXML
    private TextField nomuser;
    @FXML
    private TextField prenomuser;
    @FXML
    private TextField emailuser;
    @FXML
    private DatePicker dateuser;
    @FXML
    private ComboBox<String> genreuser;
    @FXML
    private PasswordField mdpuser;
    @FXML
    private PasswordField mdp2user;
    @FXML
    private Button test;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> options = FXCollections.observableArrayList("Homme", "Femme");
        genreuser.setItems(options);
    }

    PersonneCRUD PC = new PersonneCRUD();

    @FXML
    private void savepersonne(MouseEvent event) throws IOException {
        String nomValue = nomuser.getText();
        String prenomValue = prenomuser.getText();
        String emailValue = emailuser.getText();
        String genreValue = genreuser.getValue();
        LocalDate selectedDate = dateuser.getValue();
        String passwordValue = mdpuser.getText();
        String passwordConfirmValue = mdp2user.getText();

        if (nomValue.isEmpty()) {
            afficherMessageErreur("Champ manquant", "Le champ 'Nom' est requis.");
            return;
        }

        if (prenomValue.isEmpty()) {
            afficherMessageErreur("Champ manquant", "Le champ 'Prénom' est requis.");
            return;
        }

        if (emailValue.isEmpty()) {
            afficherMessageErreur("Champ manquant", "Le champ 'Email' est requis.");
            return;
        }

        if (genreValue == null) {
            afficherMessageErreur("Champ manquant", "Le champ 'Genre' est requis.");
            return;
        }

        if (selectedDate == null) {
            afficherMessageErreur("Champ manquant", "Le champ 'Date de naissance' est requis.");
            return;
        }

        if (passwordValue.isEmpty()) {
            afficherMessageErreur("Champ .manquant", "Le champ 'Mot de passe' est requis.");
            return;
        }

        if (!passwordValue.equals(passwordConfirmValue)) {
            afficherMessageErreur("Mot de passe non conforme", "Les mots de passe ne correspondent pas.");
            return;
        }

        if (!estEmailValide(emailValue)) {
            afficherMessageErreur("Email invalide", "L'adresse e-mail n'est pas valide.");
            return;
        }

        Personne p = new Personne(0, nomValue, prenomValue, emailValue, genreValue, selectedDate, passwordValue, passwordConfirmValue);

        try {
            PC.ajouterPersonne(p);
            afficherMessageInformation("Bienvenue " + nomValue);
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceloginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Charger et afficher la nouvelle interface "gestionuser.fxml"
        Object root = FXMLLoader.load(getClass().getResource("gestionuser.fxml"));
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene((Parent) root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public boolean estEmailValide(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
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
    

    Object getButtonLogin() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @FXML
    private void logt(ActionEvent event)  {try {
 
        Parent page =FXMLLoader.load(getClass().getResource("gestionuser1.fxml"));
        Scene scene=new Scene(page);
        Stage appStage =(Stage) ((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    } catch (IOException e) {
        e.printStackTrace();

    
        
    }}

    @FXML
    private void log(ActionEvent event) {
}}