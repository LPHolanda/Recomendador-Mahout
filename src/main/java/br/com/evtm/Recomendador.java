package br.com.evtm;

import java.io.File;
import java.io.IOException;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;

//usada somente para leitura de csv. Direto do banco não será usada.
public class Recomendador {

	public DataModel getModeloDeProdutos() throws IOException {
		return getModelo("dados.csv");
	}
	
	public DataModel getModelo(String path) throws IOException {
		File file = new File("src/main/resources/" + path);
		return new FileDataModel(file);
	}
	
	public DataModel getModeloDeCursos() throws IOException {
		return getModelo("evtm.csv");
	}
	

	public DataModel getModeloDeEventos() throws IOException {
		return getModelo("");
	}
}
