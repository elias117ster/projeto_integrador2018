package br.com.projeto.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.projeto.util.ConnectionFactory;

public class UsuarioDAO extends ConnectionFactory {
	
	private Connection conexao;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public Usuario selectByloginAndSenha(Usuario usuario){
		String sql = "SELECT  login, senha FROM usuario "
				+ "WHERE login = ? AND senha = ?";
		Usuario tempUsuario = null;
		try {
			conexao = getConnection();
			ps = conexao.prepareStatement(sql);
			 ps.setString(1, usuario.getLogin());
		    ps.setString(2, usuario.getSenha());
			rs = ps.executeQuery();
			if (rs.next()){
			    tempUsuario = new Usuario();
			    tempUsuario.setLogin(rs.getString("login"));
			    tempUsuario.setSenha(rs.getString("senha"));
			}
			
		}catch (Exception e){
			System.err.println("Erro: selectAll =>" + e.getMessage());
			e.printStackTrace();
		}
		return tempUsuario;
	}

	
	
	public List<Usuario> selectAll(){
		List<Usuario> usuarios = null;
		String sql = "SELECT id, login, senha FROM usuario";
		try {
			conexao = getConnection();
			ps = conexao.prepareStatement(sql);
			rs = ps.executeQuery();
			usuarios = new ArrayList<>();
			while (rs.next()){
				Usuario u = new Usuario();
				u.setId(rs.getInt("id"));
				u.setLogin(rs.getString("login"));
				u.setSenha(rs.getString("senha"));
				usuarios.add(u);
			}
		}catch (Exception e){
			System.err.println("Erro: selectAll =>" + e.getMessage());
			e.printStackTrace();
		}
		return usuarios;
	}
	
	
	
	public void insert(Usuario usuario){
		String sql = "INSERT INTO usuario (login, senha) "
				+ "VALUES (?, ?)";
		
		try {
			conexao = getConnection();
			ps = conexao.prepareStatement(sql);
			
			ps.setString(1, usuario.getLogin());
			ps.setString(2, usuario.getSenha());
			ps.executeUpdate();
		}catch (Exception e){
			System.err.println("Erro: insert =>" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/*
	public static void main (String[] args){
		Usuario usuario = new Usuario();
		cliente.setId("id");
		cliente.setLogin("login");
		cliente.setSenha("senha");
		new ClienteDAO().insert(usuario);
	}
	*/

}








