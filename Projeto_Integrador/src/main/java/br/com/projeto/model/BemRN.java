package br.com.projeto.model;

import java.util.Date;
import java.util.List;
import br.com.projeto.util.Calculo;

public class BemRN {
	private Bem b = new Bem();
	private Calculo calculos = new Calculo();

	
	public List<Bem> listarTodos() {
		return new BemDAO().selectBaixados();
	}

	public void inserir(Bem bem) {
		new BemDAO().insert(bem);
	}

	public void salvar(Bem bem) {
		if (bem.getId() == null) {
			new BemDAO().insert(bem);
		} else {
			new BemDAO().update(bem);
		}
	}
	
	
	
	public void excluir(Bem bem) {
		new BemDAO().excluir(bem);
	}

	

	public void inserirBaixa(Bem bem) {
		new BemDAO().insertBaixa(bem);

	}

	public List<Bem> listarAtivos() {
		return new BemDAO().lista();
	}

	public Bem buscar(Bem b) {
		return new BemDAO().buscarPorCodigo(b);
	}

	public Bem calcular(Bem b, Date dateInf) {
		Bem objBem = new BemDAO().buscarPorCodigo(b);
		Bem x = new Calculo().calcularDepreciacao(objBem, dateInf);
		objBem.setValor_contabil(x.getValor_contabil());
		objBem.setValor_residual(x.getValor_residual());
		objBem.setPeriodo(x.getPeriodo());
		return objBem;
	}
	
	
	
	

	public Calculo getCalculos() {
		return calculos;
	}

	public void setCalculos(Calculo calculos) {
		this.calculos = calculos;
	}

	public Bem getB() {
		return b;
	}

	public void setB(Bem b) {
		this.b = b;
	}

}
