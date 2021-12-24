package controller;

import java.io.IOException;
import logic.Account;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import logic.Client;
import javafx.scene.text.Text;

public class SignupController {
	@FXML private Button signUp_button;
	@FXML private TextField username;
	@FXML private PasswordField password;
	@FXML private PasswordField conf_password;
	@FXML private TextField email;
	@FXML private TextField age;
	@FXML private TextField cnic;
	@FXML private TextField Name;
	@FXML private TextField MobileNo;
	@FXML private TextField EmailCont;
	@FXML private TextField city;
	@FXML private TextField area;
	@FXML private TextField house;
	
	@FXML private Text passNotMatchingError;
	
	public void createAccount(ActionEvent event) throws IOException {
		// TODO: Add length checks
		String user = username.getText();
		String pass = password.getText();
		String conf_pass = conf_password.getText();
		String em = email.getText();
		String name = Name.getText();
		String ag = age.getText();
		String cn = cnic.getText();
		String mobile = MobileNo.getText();
		String Email = EmailCont.getText();
		String cit = city.getText();
		String are = area.getText();
		String hou = house.getText();
		
		if(!pass.equals(conf_pass)) {
			System.out.println("Password not matching");
			passNotMatchingErrorF("passwords don't match");
			return;
		}
		else {
			Account ac = new Account(user,em,  pass,  name, cn, ag, mobile,  Email,  cit, are,  hou); 
			// Get Stage
			Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
			// Create new Scene
			Scene scene = new Scene (FXMLLoader.load(getClass().getResource("/ui/Login_page.fxml")), 400, 400);
			// Update new Scene
			stage.setScene(scene);

		}
	}
	
	public void handleLoginButtonClick(ActionEvent event) throws IOException {
		// Get Stage
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		// Create new Scene
		Scene scene = new Scene (FXMLLoader.load(getClass().getResource("/ui/Login_page.fxml")), 400, 400);
		// Update new Scene
		stage.setScene(scene);
	}
	
	//when confirm pass doesnt match
	private void passNotMatchingErrorF(String error) {
		passNotMatchingError.setText(error);
	}
}
