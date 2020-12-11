package com.qintess.processamento.vendas.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.qintess.processamento.vendas.entities.Sell;

public class Useful {
	
	public static List<String> readFile(String caminho) throws IOException {
		return Files.readAllLines(Path.of(caminho));
	}
}	
