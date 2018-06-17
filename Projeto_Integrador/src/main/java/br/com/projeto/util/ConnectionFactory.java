package br.com.projeto.util;


import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	private static final String DRIVER = "org.postgresql.Driver";
	private static final String URL = "jdbc:postgresql://localhost:5432/depreciacao_bd";
	private static final String USER = "postgres";
	private static final String PASSWORD = "root";

	public static Connection getConnection() {
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			System.err.println("---------------------------------");
			System.err.println("Erro na ConnectionFactory");
			e.printStackTrace();
		}
		return null;
	}

}
