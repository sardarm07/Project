package controller;
import logic.*;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class EditAdPageController {
	
	@FXML private TextField title;
	@FXML private TextField description;
	@FXML private TextField prodName;
	@FXML private TextField price;
	@FXML private TextField category;
	@FXML private TextField subCat;
	
	private Ad ad;
	private Account account;
	
	public EditAdPageController(Ad ac, Account acc){
		ad = ac;
		account = acc;
	}
	
	@FXML
	public void handleUpdateButtonClick(ActionEvent event) throws IOException {
		ad.UpdateAd(title.getText(), description.getText(), prodName.getText(), price.getText(), category.getText(), subCat.getText(), account.getEmail());
		
		EditAdController controller = new EditAdController(account);
		// Get Stage
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/Edit_Ad.fxml"));
		loader.setController(controller);
		// Create new Scene
		Scene scene = new Scene (loader.load(), 500, 700);
		stage.setScene(scene);
	}
	
	@FXML
	public void handleGoBackButtonClick(ActionEvent event) throws IOException {
		MainMenuController controller = new MainMenuController(account);
		// Get Stage
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/Main_Menu.fxml"));
		loader.setController(controller);
		// Create new Scene
		Scene scene = new Scene (loader.load(), 500, 700);
		stage.setScene(scene);
	}
	
}
