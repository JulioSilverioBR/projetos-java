package com.qintess.processamento.arquivos.modelos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DadosMercado {
	
	private int idOpcao;
	private String subGrupo;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private Integer qtd;
	
	
	public DadosMercado() {}
	
	public DadosMercado(String linha) {
		
		String[] colunas = linha.split(";");
		
		this.idOpcao = Integer.parseInt(colunas[0]);
		this.subGrupo = colunas[1];
		this.dataInicio = LocalDate.parse(colunas[2], DateTimeFormatter.ofPattern("d/M/yyyy"));
		this.dataFim = LocalDate.parse(colunas[3], DateTimeFormatter.ofPattern("d/M/yyyy"));
		this.qtd = Integer.parseInt(colunas[4]);
		
	}
	
	public int getIdOpcao() {
		return idOpcao;
	}
	public void setIdOpcao(int idOpcao) {
		this.idOpcao = idOpcao;
	}
	public String getSubGrupo() {
		return subGrupo;
	}
	public void setSubGrupo(String subGrupo) {
		this.subGrupo = subGrupo;
	}
	public LocalDate getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}
	public LocalDate getDataFim() {
		return dataFim;
	}
	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}
	public Integer getQtd() {
		return qtd;
	}
	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	@Override
	public String toString() {
		return "DadosMercado [idOpcao=" + idOpcao + ", subGrupo=" + subGrupo + ", dataInicio=" + dataInicio
				+ ", dataFim=" + dataFim + ", qtd=" + qtd + "]";
	}
	
	

}
