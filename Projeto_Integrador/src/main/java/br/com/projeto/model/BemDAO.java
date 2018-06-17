package br.com.projeto.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.projeto.util.ConnectionFactory;

public class BemDAO extends ConnectionFactory {
	private Connection conexao;
	private PreparedStatement ps;
	private ResultSet rs;

	public List<Bem> lista() {
		List<Bem> listaAtivos = null;
		String sql = "SELECT * FROM patrimonio WHERE id NOT IN (SELECT idpatrimonio FROM baixa) ORDER BY id";
		try {
			conexao = getConnection();
			ps = conexao.prepareStatement(sql);
			rs = ps.executeQuery();
			listaAtivos = new ArrayList<Bem>();
			while (rs.next()) {
				Bem bem = new Bem();
				bem.setId(rs.getLong("id"));
				bem.setNome_bem(rs.getString("nome_bem"));
				bem.setDataAquisicao(rs.getDate("dataAquisicao"));
				bem.setCategoria(rs.getString("categoria"));
				bem.setVida_util(rs.getFloat("vida_util"));
				bem.setBem_usado(rs.getString("bem_usado"));
				bem.setValor_aquisicao(rs.getFloat("valorAquisicao"));
				bem.setTempo_uso(rs.getFloat("tempo_uso"));
				bem.setTaxa_residual(rs.getFloat("taxa_residual"));
				bem.setTurnos(rs.getFloat("turnos"));
				listaAtivos.add(bem);
			}
		} catch (Exception e) {
			System.err.println("Erro: List<Bem> lista =>" + e.getMessage());
			e.printStackTrace();
		}
		return listaAtivos;
	}

	public Bem insert(Bem bem) {

		String sql = "INSERT INTO patrimonio (nome_bem, dataAquisicao, categoria, vida_util, "
				+ "bem_usado , valorAquisicao, taxa_residual, turnos, tempo_uso) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			Connection con;
			PreparedStatement ps;
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, bem.getNome_bem());
			ps.setDate(2, new Date(bem.getDataAquisicao().getTime()));
			ps.setString(3, bem.getCategoria());
			ps.setFloat(4, bem.getVida_util());
			ps.setString(5, bem.getBem_usado());
			ps.setFloat(6, bem.getValor_aquisicao());
			ps.setFloat(7, bem.getTaxa_residual());
			ps.setFloat(8, bem.getTurnos());
			ps.setFloat(9, bem.getTempo_uso());
			ps.executeUpdate();
			System.out.println("feito insert");

		} catch (Exception e) {
			System.err.println("Erro: insert =>" + e.getMessage());
			e.printStackTrace();
		}

		return bem;
	}
	
	
	
	
	public void excluir(Bem bem) {
		String sql = "DELETE FROM patrimonio WHERE id = ?";

		Connection conexao = null;
		PreparedStatement ps = null;

		try {
			conexao = ConnectionFactory.getConnection();
			ps = conexao.prepareStatement(sql);
			ps.setLong(1, bem.getId());
			ps.executeUpdate();

		} catch (Exception e) {
			System.err.println("Erro: Excluir =>" + e.getMessage());
			e.printStackTrace();
		}		
	}
	
	
	

	public Bem update(Bem bem) {
		String sql = "UPDATE patrimonio SET  bem_usado  = ?, tempo_uso  =?, nome_bem  = ?, "
				+ "dataAquisicao = ?, categoria = ?, vida_util = ?, "
				+ "valorAquisicao = ?, taxa_residual = ?, turnos = ? WHERE id = ?";

		try {
			conexao = getConnection();
			ps = conexao.prepareStatement(sql);
			ps.setString(1, bem.getBem_usado());
			ps.setFloat(2, bem.getTempo_uso());
			ps.setString(3, bem.getNome_bem());
			System.out.println(bem.getNome_bem());
			ps.setDate(4, new Date(bem.getDataAquisicao().getTime()));
			ps.setString(5, bem.getCategoria());
			ps.setFloat(6, bem.getVida_util());
			ps.setFloat(7, bem.getValor_aquisicao());
			ps.setFloat(8, bem.getTaxa_residual());
			ps.setFloat(9, bem.getTurnos());
			ps.setLong(10, bem.getId());
			ps.executeUpdate();

		} catch (Exception e) {
			System.err.println("Erro: update =>" + e.getMessage());
			e.printStackTrace();
		}

		return bem;
	}

	public Bem buscarPorCodigo(Bem b) {
		String sql = "SELECT * from patrimonio WHERE id = ?;";
		Bem objBem = null;
		try {
			conexao = getConnection();
			ps = conexao.prepareStatement(sql);
			ps.setLong(1, b.getId());
			rs = ps.executeQuery();
			while (rs.next()) {
				objBem = new Bem();
				objBem.setId(rs.getLong("id"));
				objBem.setNome_bem(rs.getString("nome_bem"));
				objBem.setDataAquisicao(rs.getDate("dataAquisicao"));
				objBem.setVida_util(rs.getFloat("vida_util"));
				objBem.setValor_aquisicao(rs.getFloat("valorAquisicao"));
				objBem.setTaxa_residual(rs.getFloat("taxa_residual"));
				objBem.setTurnos(rs.getFloat("turnos"));
				objBem.setTempo_uso(rs.getFloat("tempo_uso"));
				objBem.setBem_usado(rs.getString("bem_usado"));
			}
		} catch (Exception e) {
			System.err.println("Erro: BuscarPorcodigo =>" + e.getMessage());
			e.printStackTrace();
		}
		return objBem;
	}

	public Bem insertBaixa(Bem bem) {
		String sql = "INSERT INTO baixa (motivoDaBaixa, dataDaBaixa, valorDaBaixa, idPatrimonio) " + " VALUES (?, ?, ?, ?)";
		try {
			Connection con;
			PreparedStatement ps;
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, bem.getMotivoDaBaixa());
			ps.setDate(2, new Date(bem.getDataDaBaixa().getTime()));
			ps.setFloat(3, bem.getValorDaBaixa());
			ps.setLong(4, bem.getId());
			ps.executeUpdate();

		} catch (Exception e) {
			System.err.println("Erro: insertBaixa =>" + e.getMessage());
			e.printStackTrace();
		}

		return bem;
	}

	public List<Bem> selectBaixados() {
		List<Bem> listaBaixados = null;
		String sql = "SELECT * FROM patrimonio INNER JOIN baixa ON patrimonio.id = "
				+ "baixa.idpatrimonio ORDER BY nome_bem";
		try {
			conexao = getConnection();
			ps = conexao.prepareStatement(sql);
			rs = ps.executeQuery();
			listaBaixados = new ArrayList<Bem>();
			while (rs.next()) {
				Bem bem = new Bem();
				bem.setId(rs.getLong("id"));
				bem.setNome_bem(rs.getString("nome_bem"));
				bem.setDataAquisicao(rs.getDate("dataaquisicao"));
				bem.setCategoria(rs.getString("categoria"));
				bem.setVida_util(rs.getFloat("vida_util"));
				bem.setBem_usado(rs.getString("bem_usado"));
				bem.setValor_aquisicao(rs.getFloat("valorAquisicao"));
				bem.setTempo_uso(rs.getFloat("tempo_uso"));
				bem.setValor_residual(rs.getFloat("taxa_residual"));
				bem.setTurnos(rs.getFloat("turnos"));
				bem.setDataDaBaixa(rs.getDate("dataDaBaixa"));
				bem.setValorDaBaixa(rs.getFloat("valorDaBaixa"));
				bem.setMotivoDaBaixa(rs.getString("motivoDaBaixa"));
				listaBaixados.add(bem);
			}

		} catch (Exception e) {
			System.err.println("Erro: selectBaixados =>" + e.getMessage());
			e.printStackTrace();
		}

		return listaBaixados;
	}

}
