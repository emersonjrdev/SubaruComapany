package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LoginController {
	
	@FXML
	private TextField email;
	
	@FXML
	private PasswordField senha;
	
	public Stage primaryStage;
	
	public void entrar() {
		System.out.println("Tentativa de login...");
		if(email.getText().equals("admin@gmail.com")
				&& senha.getText().equals("admin")) {
			System.out.println("Login realizado com sucesso");
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/DashboardSubaru.fxml"));
				
				StackPane root = loader.load();
				
				Scene scene = new Scene(root, 922, 526);
				
				Stage currentStage = (Stage) email.getScene().getWindow();
				currentStage.setScene(scene);
				currentStage.setTitle("DashboardSubaru");
				currentStage.show();				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else {
			mensagemDeErro();
		}
	}
	
	public void mensagemDeErro() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Sua senha esta errada");
		alert.setContentText("Senha Incorreta!");
		alert.setHeaderText(null);
		alert.showAndWait();
	}

}
