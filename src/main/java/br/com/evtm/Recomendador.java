package br.com.evtm;

import java.io.File;
import java.io.IOException;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.model.DataModel;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class Recomendador {

	private static final String SERVER_NAME = "localhost";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "";
	private static final String DATABASE = "LAB_RECOMENDADOR";

	public DataModel getModeloDeEventos() throws IOException {

		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setServerName(SERVER_NAME);
		dataSource.setUser(USER_NAME);
		dataSource.setPassword(PASSWORD);
		dataSource.setDatabaseName(DATABASE);

		DataModel model = new MySQLJDBCDataModel(dataSource, 
				"AVALIACAO_SUB_TIPO", "id_usuario", "id_categoria", "avaliacao", null);

		return model;
	}
	
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
}
