package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Carros;
import repository.SubaruRepository;

public class SubaruController {
	
	@FXML
	private TableView<Carros> carview;
	
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
	
	private ObservableList<Carros> data;
	
	private SubaruRepository subaru;
	
	@FXML
	public void initialize() {
		alugarCarro.setCellValueFactory(new PropertyValueFactory<>("nome"));
		comprarCarro.setCellValueFactory(new PropertyValueFactory<>("modelo"));
		valorCarros.setCellValueFactory(new PropertyValueFactory<>("valor"));
		
		// instanciando lista observable
		data = FXCollections.observableArrayList();
		
		// tableview aceita somente ObservableList
		carview.setItems(data);
			
		subaru = new SubaruRepository();
		
		carregarDadosSalvos();
	}
	// Preencher Lista
	public void carregarDadosSalvos() {
		try (BufferedReader br = new BufferedReader(new FileReader("database-Subaru.txt"))){
			String line;
			
			while((line = br.readLine()) != null) {
				String[] parts = line.split(";");
				if(parts.length >=4) {
					Carros carros = new Carros();
					carros.setId(Integer.parseInt(parts[0]));
					carros.setNome(parts[1]);
					carros.setModelo(parts[2]);
					carros.setValor(parts[3]);
					
					data.add(carros);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void adicionar() {
		System.out.println("Adicionado com sucesso");
		Carros carros = new Carros();
		
		// Validação dos campos
        if (alugar.getText().isEmpty()) {
            showAlert("Erro", "Insira o nome do veiculo!", Alert.AlertType.ERROR);
            return;
        }
 
        if (comprar.getText().isEmpty()) {
            showAlert("Erro", "Insira o nome do veiculo!", Alert.AlertType.ERROR);
            return;
        }
        
        if (valor.getText().isEmpty()) {
            showAlert("Erro", "Insira o valor do veiculo!", Alert.AlertType.ERROR);
            return;
        }
		
        try {
        	carros.setNome(alugar.getText());
    		carros.setModelo(comprar.getText());
    		carros.setValor(valor.getText());		
    		subaru.addSubaru(carros);		
    		clearFields();
        } catch (NumberFormatException e) {
            showAlert("Erro de Formato", " O campo membros deve ser um número.", Alert.AlertType.ERROR);
        }
		
	}
	
	private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
	
	public void clearFields() {
		alugar.clear();
		comprar.clear();
		valor.clear();
	}
	
	public void limpar() {
		System.out.println("Limpo com sucesso"); 
	}
}
