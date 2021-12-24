package controller;
import logic.Account;
import DataAccess.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import DataAccess.AccountData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
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

public class SearchAdController {
	private Account acc;
	@FXML private GridPane grid;
	@FXML private TextField search;
	public SearchAdController(Account ac) {
		acc = ac;
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
	
	@FXML
	public void handleSearchButtonClick(ActionEvent event) throws IOException {
		String s = search.getText();
		grid.setHgap(30);
	    grid.setVgap(30);
	    
	    Socket socket = ClientController.getInstance().getClient();
	    //getting Search Items
	    OutputStream outToServer = socket.getOutputStream();
    	DataOutputStream out = new DataOutputStream(outToServer);
    	out.writeUTF("Search "+ s);
    	try {
	    	InputStream inputStream = socket.getInputStream();
	        // create a DataInputStream so we can read data from it.
	        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
	        
	        ArrayList<AdData> ad = (ArrayList<AdData>) objectInputStream.readObject();
	        
	        for(int i=0;i<ad.size();i++) {
	        	VBox inner = DisplayAds(ad.get(i));
				grid.add(inner, 0, i);
	        }
	        
    	}catch(Exception e ) {
    		
    	}
    	
    	
	}
	public VBox DisplayAds(AdData ad) {
		VBox inner = new VBox();
		inner.setMinWidth(500);
		inner.setMinHeight(100);
		inner.setSpacing(10);
		inner.setBackground(new Background(new BackgroundFill(Color.web("#121212"),CornerRadii.EMPTY,null)));
		VBox hbHolder = new VBox();
		hbHolder.setMinHeight(30);
		hbHolder.setSpacing(10);
		
		//HBox of buttons 
		HBox hb = new HBox();
		hb.setMinWidth(400);
		hb.setMinHeight(20);
		hb.setSpacing(300);
		hb.setBackground(new Background(new BackgroundFill(Color.web("#121212"),CornerRadii.EMPTY,null)));
		Text txt = new Text(ad.getTitle());
		txt.setFont(new Font(20));
		txt.setFill(Color.WHITE);
		txt.minWidth(150);
		
		Text price = new Text(Float.toString(ad.getPrice()));
		price.setFont(new Font(20));
		price.setFill(Color.WHITE);
		
		Text desc = new Text(ad.getDescription());
		desc.setFont(new Font(20));
		desc.setFill(Color.WHITE);
		
		hb.getChildren().addAll(txt,price);
		hbHolder.getChildren().addAll(hb, desc);
		inner.getChildren().add(hbHolder);
		return inner;
	}
	
}
