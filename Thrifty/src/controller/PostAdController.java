package controller;
import logic.Client;
import logic.Account;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;

public class PostAdController {
	@FXML private TextField title;
	@FXML private TextField description;
	@FXML private TextField prodName;
	@FXML private TextField price;
	@FXML private TextField category;
	@FXML private TextField subCat;
	
	
	private Account acc;
	public PostAdController(Account ac){
		acc = ac;
	}
	
	@FXML
	public void InitiatePostAd(ActionEvent event) throws IOException {
		acc.postAd(title.getText(), description.getText(), prodName.getText(), price.getText(), category.getText(), subCat.getText());
		
		MainMenuController controller = new MainMenuController(acc);
		// Get Stage
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/Main_Menu.fxml"));
		loader.setController(controller);
		// Create new Scene
		Scene scene = new Scene (loader.load(), 500, 700);
		stage.setScene(scene);
	
	}
	
	@FXML
	public void handleGoBackButtonClick(ActionEvent event) throws IOException {
		MainMenuController controller = new MainMenuController(acc);
		// Get Stage
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/Main_Menu.fxml"));
		loader.setController(controller);
		// Create new Scene
		Scene scene = new Scene (loader.load(), 500, 700);
		stage.setScene(scene);
	}
}
