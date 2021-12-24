package controller;
import logic.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Pos;
//import javafx.scene.layout.GridPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.fxml.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.awt.*;
import javafx.scene.control.Button;

public class ViewAdHistoryController implements Initializable{
	private Account acc;
	@FXML private GridPane grid;
	public ViewAdHistoryController(Account ac) {
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
	    	VBox inner = DisplayAd(acc.getAds().get(i));
			grid.add(inner, 0, i);
	    }
		
	}
	
	public VBox DisplayAd(Ad ad) {
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
		
		Text price = new Text(Float.toString(ad.getProd().price));
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
