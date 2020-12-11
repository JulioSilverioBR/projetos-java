package com.qintess.processamento.arquivos.executavel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.qintess.processamento.arquivos.modelos.DadosMercado;
import com.qintess.processamento.arquivos.modelos.DadosVenda;
import com.qintess.processamento.arquivos.negocio.Processamento;

public class App {

	public static void main(String[] args) throws IOException {
		
		
		Processamento processamento = new Processamento();
		
		System.out.println("Processamento iniciado");
		processamento.inicia();
		
		
		System.out.println("Imprimindo subgrupos");
		List<String> subgrupos = processamento.selecionaSubgrupos();
		
		for (String subgrupo : subgrupos) {
			System.out.println(subgrupo);
		}
		
		System.out.println("");
		
		System.out.println("Imprimir opção com maior quantidade vendida");
		
		System.out.println(processamento.selecionaOpcaoMaiorQtd());
		
		System.out.println("");
		
		
		System.out.println("Imprimir o valor unitário de uma venda");
		
		DadosMercado dadosMercadoParam = new DadosMercado();
		dadosMercadoParam.setIdOpcao(8923);
		dadosMercadoParam.setDataFim(LocalDate.of(2020, 11, 30));
		dadosMercadoParam.setDataInicio(LocalDate.of(2020, 3, 10));
		dadosMercadoParam.setSubGrupo("ddddd");
		
		System.out.println(processamento.selecionaValorUnitarioVenda(dadosMercadoParam));
		

		System.out.println("");
		System.out.println("Imprimir o valor total de cada subgrupo");
		processamento.selecionaValorTotalPorSubgrupo();
		
		System.out.println("Processamento terminado com sucesso!");
		
	}

}
