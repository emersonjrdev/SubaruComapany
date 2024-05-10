package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
	
	@FXML
	private TextField email;
	
	@FXML
	private PasswordField senha;
	
	public void entrar() {
		System.out.println("Tentativa de login...");
		if(email.getText().equals("admin@gmail.com")
				&& senha.getText().equals("admin")) {
			System.out.println("Realizado com sucesso");
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
