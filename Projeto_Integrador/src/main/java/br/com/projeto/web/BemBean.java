package br.com.projeto.web;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.projeto.model.Bem;
import br.com.projeto.model.BemRN;

@ManagedBean(name = "Bem_Bean")
public class BemBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Bem bem = new Bem();
	private Date d = new Date();
	private Date dateInf = new Date();
	

	public List<Bem> getListagem() {
		return new BemRN().listarTodos();
	}

	// listarBensAtivos
	public List<Bem> getList() {
		return new BemRN().listarAtivos();
	}

	public String actionNovo() {
		this.bem = new Bem();
		return "edicao_patrimonio";
	}

	public String actionVoltarMenu() {
		return "home";
	}

	public String actionGravar() {
		new BemRN().salvar(bem);
		return "form_alteracoes_bens";
	}

	public String actionCalcularDep() {
		bem = new BemRN().calcular(bem, dateInf);
		return "simulacao_dep_dataAtual";
	}
	
	public String actionSIMULARdep() {
		bem = new BemRN().calcular(bem, dateInf);
		return "simulacao_dep_dataBaixa";
	}
	
	public String actionExcluir(){
		new BemRN().excluir(bem);
		return "form_alteracoes_bens";
	}

	public String actionBaixar() {
		new BemRN().inserirBaixa(bem);
		return "home";
	}

	public String actionCalcularEbaixar() {
		bem = new BemRN().calcular(bem, dateInf);
		return "baixar";
	}

	
	
	
	
	
	
	
	

	//////// getters e setters

	public Bem getBem() {
		return bem;
	}

	public void setBem(Bem bem) {
		this.bem = bem;
	}

	public Date getD() {
		return d;
	}

	public void setD(Date d) {
		this.d = d;
	}

	public Date getDateInf() {
		return dateInf;
	}

	public void setDateInf(Date dateInf) {
		this.dateInf = dateInf;
	}

}
