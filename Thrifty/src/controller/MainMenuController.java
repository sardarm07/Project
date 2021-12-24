package controller;
import logic.Account;
import logic.Client;

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

public class MainMenuController {
	
	private Account acc;
	
	public MainMenuController(Account ac) {
		acc = ac;
	}
	public void initData(Account ac) {
		acc = ac;
	}
	@FXML
	public void handlePostAdButtonClick(ActionEvent event) throws IOException {
		
		PostAdController controller = new PostAdController(acc);
		// Get Stage
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/Post_Ad.fxml"));
		loader.setController(controller);
		// Create new Scene
		Scene scene = new Scene (loader.load(), 500, 700);
		stage.setScene(scene);
	
	}
	@FXML
	public void ViewAdHistory(ActionEvent event) throws IOException {
		
		ViewAdHistoryController controller = new ViewAdHistoryController(acc);
		// Get Stage
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/View_Ad_History.fxml"));
		loader.setController(controller);
		// Create new Scene
		Scene scene = new Scene (loader.load(), 500, 700);
		stage.setScene(scene);
	}
	@FXML
	public void BrowseAdInitialize(ActionEvent event) throws IOException {
		
		BrowseAdController controller = new BrowseAdController(acc);
		// Get Stage
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/Browse_Ad.fxml"));
		loader.setController(controller);
		// Create new Scene
		Scene scene = new Scene (loader.load(), 500, 700);
		stage.setScene(scene);
		
	}
	@FXML
	public void EditAdInitialize(ActionEvent event) throws IOException {
		
		EditAdController controller = new EditAdController(acc);
		// Get Stage
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/Edit_Ad.fxml"));
		loader.setController(controller);
		// Create new Scene
		Scene scene = new Scene (loader.load(), 500, 700);
		stage.setScene(scene);
	}
	@FXML
	public void AdSearchRequest(ActionEvent event) throws IOException {
		
		SearchAdController controller = new SearchAdController(acc);
		// Get Stage
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/Search_Ad.fxml"));
		loader.setController(controller);
		// Create new Scene
		Scene scene = new Scene (loader.load(), 500, 700);
		stage.setScene(scene);
	}
	@FXML
	public void handleSignOutButtonClick(ActionEvent event) throws IOException {
		
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		// Create new Scene
		Scene scene = new Scene (FXMLLoader.load(getClass().getResource("/ui/Login_page.fxml")), 400, 400);
		// Update new Scene
		stage.setScene(scene);
	}
	
}
