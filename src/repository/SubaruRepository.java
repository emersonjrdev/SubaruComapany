package repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Carros;

public class SubaruRepository {
	private List<Carros> Subaru;
	private File database;

	public SubaruRepository() {
		this.database = new File("database-Subaru.txt");
		this.Subaru = new ArrayList<>();
		// Carregar os dados
		loadSubaru();
	}
	
	// Carregar
	private void loadSubaru() {
		try (Scanner sc = new Scanner(database)){
			while(sc.hasNextLine()) {
				
				String[] data= sc.nextLine().split(";");
				if(data.length >= 4) {
					Carros carros = new Carros();
					carros.setId(Integer.parseInt(data[0]));
					carros.setNome(data[1]);
					carros.setValor(data[2]);
					carros.setModelo(data[3]);
					Subaru.add(carros);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO: handle exception
		}
	}
	
	// Update - Atualizar
	public void updateSubaru(Carros updateSubaru) {
		for(int i = 0; i < Subaru.size(); i++) {
			if(Subaru.get(i).getId() == updateSubaru.getId()) {
				Subaru.set(i, updateSubaru);
				saveSubaru();
				break;
			}
		}
	}
	
	// Buscar Unico
	public Carros getCarrosById(int id) {
		for(Carros carros : Subaru) {
			if(carros.getId() == id) {
				return carros;
			}
		}
		return null;
	}

     // CRUD -> Create, Read, Update and Delete

     // Buscar todos
	public List<Carros> buscarTodos() {
		return new ArrayList<>(Subaru);
	}

	// Deletar Objeto Especifico

	public void deleteSubaru(int id) {
	// Percorrer todo o Array, caso seja igual ele vai remover
		Subaru.removeIf(carros -> carros.getId() == id);
		saveSubaru();
	}

	// Criar Subaru
	public void addSubaru(Carros carros) {
		carros.setId(getNextId());
		Subaru.add(carros);
	// Sobrescrever o arquivo database
		saveSubaru();
	}
	
	// Sobrescrever o arquivo
	private void saveSubaru() {
		// Buscando e recuperando arquivo.
		try (PrintWriter writer = new PrintWriter(new FileOutputStream(database, false))){
			for(Carros carros : Subaru) {
				// Formatando a linha do dado
				String data = carros.getId() + ";" + carros.getNome() + ";" + carros.getModelo() + ";" + carros.getValor() ;
				writer.println(data);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo database não encontrado");
		}
	}
	
	
	
	// Lógica para pegar o proximo ID
	public int getNextId() {
		int maxId = 0;
		for(Carros carros : Subaru) {
			// Verifica se o numero é maior que o nosso numero maximo
			if(carros.getId() > maxId) {
				maxId = carros.getId();
			}
		}
		return maxId + 1;
	}
	
	

}
