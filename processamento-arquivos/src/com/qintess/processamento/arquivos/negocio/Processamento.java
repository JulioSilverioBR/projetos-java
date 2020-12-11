package com.qintess.processamento.arquivos.negocio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.qintess.processamento.arquivos.modelos.DadosMercado;
import com.qintess.processamento.arquivos.modelos.DadosVenda;

public class Processamento {
	
	public List<DadosMercado> arquivoDadosMercado = new ArrayList<DadosMercado>();
	public List<DadosVenda> arquivoDadosVenda = new ArrayList<DadosVenda>();
	
	public String caminhoArquivoDadosMercado = "C:\\arquivos\\dados_mercado.txt";
	public String caminhoArquivoDadosVenda = "C:\\arquivos\\dados_venda.txt";

	public void inicia() throws IOException {
		
		List<String> linhasDadosMercado = Util.leArquivo(this.caminhoArquivoDadosMercado);
		List<String> linhasDadosVenda =  Util.leArquivo(this.caminhoArquivoDadosVenda);
		
		processaArquivoDadosMercado(linhasDadosMercado);
		processaArquivoDadosVenda(linhasDadosVenda);
	}
	
	public DadosMercado selecionaOpcaoMaiorQtd() {
		
		int maiorQtd = 0;
		DadosMercado dadosMercadoMaiorQtd = new DadosMercado();
		
		for (DadosMercado dadosMercado : this.arquivoDadosMercado) {
			
			if(dadosMercado.getQtd() > maiorQtd) {
				maiorQtd = dadosMercado.getQtd();
				dadosMercadoMaiorQtd = dadosMercado;
			}
		}
		
		return dadosMercadoMaiorQtd;
		
	}
	
	public List<String> selecionaSubgrupos() {
		
		List<String> subGrupos = new ArrayList<String>();
		
		for (DadosMercado dadosMercado : this.arquivoDadosMercado) {
			
			if(!subGrupos.contains(dadosMercado.getSubGrupo())) {
				subGrupos.add(dadosMercado.getSubGrupo());
			}
		}
		
		return subGrupos;
	}
	
	public double selecionaValorUnitarioVenda(DadosMercado dadosMercadoParam) {
		
		double valorUnitarioVenda = 0.0;
		
		for (DadosMercado dadosMercado : this.arquivoDadosMercado) {
			
			if(dadosMercado.getIdOpcao() == dadosMercadoParam.getIdOpcao() 
					&& dadosMercado.getDataInicio().equals(dadosMercadoParam.getDataInicio())
					&& dadosMercado.getDataFim().equals(dadosMercadoParam.getDataFim())
					&& dadosMercado.getSubGrupo().equals(dadosMercadoParam.getSubGrupo())) {
				
				
				long diff = Util.calculaDiferencaDias(dadosMercado.getDataInicio(), dadosMercado.getDataFim());
				
				double valorVenda = 0.0;
				
				for (DadosVenda dadosVenda : this.arquivoDadosVenda) {
					if(dadosVenda.getIdOpcao() == dadosMercadoParam.getIdOpcao()
							&& dadosVenda.getPrazo() == diff) {
						
						valorVenda = dadosVenda.getValorOpcao();
						break;
						
					}
				}
				
				valorUnitarioVenda = valorVenda / dadosMercado.getQtd();
				break;
			}
			
		}
		
		return valorUnitarioVenda;
	}
	
	public void selecionaValorTotalPorSubgrupo() throws IOException {
		
		List<String> valoresTotais = new ArrayList<String>();
		
		for (String subgrupo : selecionaSubgrupos()) {
			
			double valorTotalSubgrupo = 0.0;
			
			for(DadosMercado dadosMercado : this.arquivoDadosMercado) {
				
				if(dadosMercado.getSubGrupo().equals(subgrupo)) {
					
					long diff = Util.calculaDiferencaDias(dadosMercado.getDataInicio(), dadosMercado.getDataFim());
					
					for (DadosVenda dadosVenda : this.arquivoDadosVenda) {
						if(dadosVenda.getIdOpcao() == dadosMercado.getIdOpcao()
								&& dadosVenda.getPrazo() == diff) {
							
							
							valorTotalSubgrupo += dadosVenda.getValorOpcao();
						}
					}
				}
			}
			
			valoresTotais.add(subgrupo + "|" + String.format("%.2f", valorTotalSubgrupo));
		}
		
		Files.write(Path.of("C:\\arquivos\\resultado_processamento_" + LocalDate.now() + ".txt"), valoresTotais);
	}
	
	private void processaArquivoDadosMercado(List<String> linhasDadosMercado) {
		
		linhasDadosMercado.remove(0);
		for (String linha : linhasDadosMercado) {
			this.arquivoDadosMercado.add(new DadosMercado(linha));
		}
	}
	
	private void processaArquivoDadosVenda(List<String> linhasDadosVenda) {
		
		linhasDadosVenda.remove(0);
		for (String linha : linhasDadosVenda) {
			this.arquivoDadosVenda.add(new DadosVenda(linha));
		}
	}
}
