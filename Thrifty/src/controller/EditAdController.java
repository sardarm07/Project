package controller;
import logic.Account;

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

public class EditAdController implements Initializable{
	private Account acc;
	@FXML private GridPane grid;
	public EditAdController(Account ac){
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
	
	@Override
	public void initialize(URL location, ResourceBundle resource) {
		grid.setHgap(30);
	    grid.setVgap(30);
	    for(int i=0;i<acc.getAds().size();i++) {
	    	VBox inner = new VBox();
			inner.setMinWidth(490);
			inner.setMinHeight(100);
			inner.setSpacing(10);
			//inner.setBackground(new Background(new BackgroundFill(Color.web("#121212"),CornerRadii.EMPTY,null)));
			VBox hbHolder = new VBox();
			hbHolder.setMinHeight(30);
			hbHolder.setSpacing(10);
			
			//HBox of buttons 
			HBox hb = new HBox();
			hb.setMinWidth(400);
			hb.setMinHeight(20);
			hb.setSpacing(20);
			hb.setBackground(new Background(new BackgroundFill(Color.web("#121212"),CornerRadii.EMPTY,null)));
			Text txt = new Text(acc.getAds().get(i).getTitle());
			txt.setFont(new Font(20));
			txt.setFill(Color.WHITE);
			txt.minWidth(150);
			
			Text price = new Text(Float.toString(acc.getAds().get(i).getProd().price));
			price.setFont(new Font(20));
			price.setFill(Color.WHITE);
			
			Text desc = new Text(acc.getAds().get(i).getDescription());
			desc.setFont(new Font(20));
			desc.setFill(Color.WHITE);
			

			Button edit = new Button("EDIT");
			edit.setStyle("-fx-font-size: 40pt;");
			edit.setStyle("-fx-background-color: WHITE;");
		    //reject.setStyle("fx-stroke-width: 2;");
			edit.setMinSize(100, 30);
		    
		    Button sold = new Button("SOLD");
		    sold.setStyle("-fx-font-size: 40pt;");
		    sold.setStyle("-fx-background-color: GREEN;");
		    //accept.setStyle("fx-stroke-width: 2;");
		    sold.setMinSize(100, 30);
		    
		    //marking ad as sold 
		    final int temp = i;
		    sold.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	Socket socket = ClientController.getInstance().getClient();
	            	try {
		        	    //getting Search Items
		        	    OutputStream outToServer = socket.getOutputStream();
		            	DataOutputStream out = new DataOutputStream(outToServer);
		            	out.writeUTF("Sold "+ acc.getAds().get(temp).id);
	            	}
	            	catch(Exception e) {
	            		
	            	}
	            	
	            }
	        });
		    
		    final int temp2 = i;
		    edit.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	
	            	try {
	            		//System.out.println("Editing now");
	            		EditAdPageController controller = new EditAdPageController(acc.getAds().get(temp2),acc);
	            		// Get Stage
	            		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	            		
	            		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/Edit_Ad_Page.fxml"));
	            		loader.setController(controller);
	            		// Create new Scene
	            		Scene scene = new Scene (loader.load(), 500, 700);
	            		stage.setScene(scene);
	            		

	            	}
	            	catch(Exception e) {
	            		System.out.println(e);
	            	}
	            	
	            }
	        });
			
			hb.getChildren().addAll(txt,price, edit, sold);
			hbHolder.getChildren().addAll(hb, desc);
			hbHolder.setBackground(new Background(new BackgroundFill(Color.web("#121212"),CornerRadii.EMPTY,null)));
			inner.getChildren().add(hbHolder);
			grid.add(inner, 0, i+2);
	    }
	}
	
}
