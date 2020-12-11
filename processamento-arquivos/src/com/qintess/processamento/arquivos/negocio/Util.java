package com.qintess.processamento.arquivos.negocio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Util {
	
	public static List<String> leArquivo(String caminho) throws IOException {
		
		return Files.readAllLines(Path.of(caminho));
		
	}
	
	public static long calculaDiferencaDias(LocalDate dataInicio, LocalDate dataFim) {
		return ChronoUnit.DAYS.between(dataInicio, dataFim);
	}

}
