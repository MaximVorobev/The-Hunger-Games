package src.game;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import src.DB.DatabaseHandler;
import src.DB.Player;
import src.SceneSwitcher;

public class RegistrController {

    public AnchorPane Pane;

    @FXML
    private Button btn_back_reg;

    @FXML
    private Button btn_enter_reg;

    @FXML
    private TextField login_text;

    @FXML
    private PasswordField pass_rep_txt;

    @FXML
    private PasswordField pass_text;

    @FXML
    void initialize() {
        btn_enter_reg.setOnAction(event -> signUpNewPlayer());

        btn_back_reg.setOnAction(event -> {
            try {
                new SceneSwitcher().switchScene("\\resources\\menu.fxml");
                Pane.getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void signUpNewPlayer() {
        DatabaseHandler dbHandler = new DatabaseHandler();

        String login = login_text.getText();
        String password = pass_text.getText();
        String password_rep = pass_rep_txt.getText();

        if (!login.equals("") & !password.equals("") & !password_rep.equals("") & password.equals(password_rep)){
            dbHandler.signUpPlayer(login, password);
        }
    }

}
