package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Carros;
import repository.SubaruRepository;

public class SubaruController {
	
	@FXML
	private TableView<Carros> tableview;
	
	@FXML
	private TableColumn<Carros, String> alugarCarro;
	
	@FXML
	private TableColumn<Carros, String> comprarCarro;
	
	@FXML
	private TableColumn<Carros, String> valorCarros ;
	

	@FXML
	private TextField alugar;
	@FXML
	private TextField comprar;
	@FXML
	private TextField valor;
	
	private SubaruRepository subaru;
	
	@FXML
	public void initialize() {
		subaru = new SubaruRepository();
	}
	
	public void adicionar() {
		System.out.println("Clicou no botão adicionar");
		Carros carros = new Carros();
		
		carros.setNome(alugar.getText());
		carros.setModelo(comprar.getText());
		carros.setValor(valor.getText());		
		subaru.addSubaru(carros);		
		clearFields();
		
	}
	
	public void clearFields() {
		alugar.clear();
		comprar.clear();
		valor.clear();
	}
	
	public void limpar() {
		System.out.println("Clicou no botão limpar"); 
	}
}
