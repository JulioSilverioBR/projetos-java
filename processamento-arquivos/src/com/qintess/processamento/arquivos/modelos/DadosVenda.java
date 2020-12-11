package com.qintess.processamento.arquivos.modelos;

public class DadosVenda {
	
	private int idOpcao;
	private Double valorOpcao;
	private int prazo;
	
	public DadosVenda() {}
	
	public DadosVenda(String linha) {
		
		String[] colunas = linha.split(";");
		
		if(colunas.length == 3) {
			idOpcao = Integer.parseInt(colunas[0]);
			valorOpcao = Double.parseDouble(colunas[1]);
			prazo = Integer.parseInt(colunas[2]);
		}
	}

	public int getIdOpcao() {
		return idOpcao;
	}

	public void setIdOpcao(int idOpcao) {
		this.idOpcao = idOpcao;
	}

	public Double getValorOpcao() {
		return valorOpcao;
	}

	public void setValorOpcao(Double valorOpcao) {
		this.valorOpcao = valorOpcao;
	}

	public int getPrazo() {
		return prazo;
	}

	public void setPrazo(int prazo) {
		this.prazo = prazo;
	}
	
	

}
