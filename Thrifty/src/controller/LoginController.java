package controller;

import logic.Client;
import logic.Account;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import DataAccess.AccountData;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;

public class LoginController {
	@FXML private Button login_button;
	@FXML private TextField email;
	@FXML private PasswordField password;
	private Account acc;
	
	public void handleLoginButtonClick(ActionEvent event) throws IOException {
		// Connect to server
		// Authorize
		//Client c = ClientController.getInstance();
		// TODO: Add length checks
		String user = email.getText();
		String pass = password.getText();
		Socket socket = ClientController.getInstance().getClient();
		
		OutputStream outToServer = socket.getOutputStream();
    	DataOutputStream out = new DataOutputStream(outToServer);
    	out.writeUTF("Login "+ user+" "+ pass);
    	
    	InputStream inFromServer = socket.getInputStream();
    	DataInputStream in = new DataInputStream(inFromServer);
    	if (in.readUTF().equals("True")){
    		out.writeUTF("Send Object");
    		
    		//getting account
    		// get the input stream from the connected socket
    		try {
		        InputStream inputStream = socket.getInputStream();
		        // create a DataInputStream so we can read data from it.
		        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
		        
		        AccountData ad = (AccountData) objectInputStream.readObject();
		        acc = new Account(ad);
    		}
    		catch(Exception e) {
    			
    		}
  
    		
			// 
			
			
			MainMenuController controller = new MainMenuController(acc);
			// Get Stage
			Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/Main_Menu.fxml"));
			loader.setController(controller);
			// Create new Scene
			Scene scene = new Scene (loader.load(), 500, 700);
		
			stage.setScene(scene);
			// Get Stage
    	}
		//}
	}
	
	public void handleSignupButtonClick(ActionEvent event) throws IOException {
		// Get Stage
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		// Create new Scene
		Scene scene = new Scene (FXMLLoader.load(getClass().getResource("/ui/Signup_page.fxml")), 500, 850);
		// Update new Scene
		stage.setScene(scene);
	}
}
