package com.qintess.processamento.vendas.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.qintess.processamento.vendas.entities.Sell;

public class Process {
	public List<Sell> sellFile = new ArrayList<Sell>();
	public String filePathSell = "C:\\Arquivos\\data.txt";

	// Inicia o processo
	public void start() throws IOException {
		List<String> sellDataLine = Useful.readFile(this.filePathSell);
		fileSellProcess(sellDataLine);
	}

	// Mostra o valor total do CSV
	// Mostra o valor total por Creditor
	public double showSellTotal(Sell sellCreditor) {
		if (sellCreditor.getCreditor().isBlank()) {
			return this.sellFile.stream().mapToDouble(i -> i.getTotal()).sum();
		} else {
			double total = 0;
			for (Sell sellItem : this.sellFile) {
				if (sellItem.getCreditor().equalsIgnoreCase(sellCreditor.getCreditor()))
					total += sellItem.getTotal();
			}
			return total;
		}
	}

	// Retorna a venda do credito, retorna o como uma lista.
	public List<Sell> showSellItemsofCreditor(Sell sellCreditor) {
		List<Sell> lstSellsCreditor = new ArrayList<Sell>();

		for (Sell sellItem : this.sellFile) {
			if (sellItem.getCreditor().equalsIgnoreCase(sellCreditor.getCreditor())) {
				lstSellsCreditor.add(sellItem);
			}
		}
		return lstSellsCreditor;
	}
	// Retorna a service area por creditor
	public List<String> returnServicesAreas(Sell sellCreditor) {
		List<String> currentyServicesAreas = new ArrayList<String>();
		for (Sell sellItem : this.sellFile) {
			if (sellItem.getCreditor().equalsIgnoreCase(sellCreditor.getCreditor())) {
				if (!currentyServicesAreas.contains(sellItem.getServiceArea()))
					currentyServicesAreas.add(sellItem.getServiceArea());
			}
		}
		return currentyServicesAreas;
	}

	// Escreve o arquivo com os dados pedidos.
	public void writeFileCreditor(Sell sellCreditor, double tax) throws IOException {
		List<String> newFile = new ArrayList<String>();

		double oldTotal = showSellTotal(sellCreditor);
		for (Sell sellItem : this.sellFile) {
			if (sellItem.getCreditor().equalsIgnoreCase(sellCreditor.getCreditor())) {
				double oldValue = sellItem.getTotal();
				sellItem.setTotal((sellItem.getTotal() + (sellItem.getTotal() * (tax / 10))));
				newFile.add("|Creditor: " + sellItem.getCreditor() + "| Value: " + String.format("%.2f", oldValue)
						+ "| Updated Value Plus " + tax + "%: " + String.format("%.2f", sellItem.getTotal()) + "|");
			}
		}
		newFile.add("Old Total Value: " + String.format("%.2f", oldTotal) + " | Update Total Value: "
				+ String.format("%.2f", showSellTotal(sellCreditor)));
		Files.write(Path.of("C:\\Arquivos\\" + sellCreditor.getCreditor() + " Sells.txt"), newFile);
	}

	// Usa o metodo privados abaixo e envia o serviceArea com o valor de cada tipo
	// pro arquivo
	public List<String> groupServicesAreasAndShowTotalValue() {
		List<Double> lstServicesAreasValue = showSellTotal(returnServicesAreas());
		List<String> servicesAreasWithValue = new ArrayList<String>();
		int i = 0;
		for (String serviceArea : returnServicesAreas()) {
			servicesAreasWithValue.add(serviceArea.concat((serviceArea.isEmpty() ? "Total " : " ")
					+ String.valueOf(String.format("R$ %.2f", lstServicesAreasValue.get(i)))));
			i++;
		}
		return servicesAreasWithValue;
	}

	// Mostra o valor total de cada area, retorna em LIST de double
	private List<Double> showSellTotal(List<String> servicesAreas) {
		// Cria a lista de servicesAreas
		List<Double> lstServicesAreasValue = new ArrayList<Double>();
		// Valor total da lista inteira
		double totalList = 0;
		for (String serviceArea : servicesAreas) {
			double total = 0;
			for (Sell sellItem : this.sellFile) {
				if (sellItem.getServiceArea().equalsIgnoreCase(serviceArea)) {
					total += sellItem.getTotal();
				}
			}
			// Adiciona o total do serviceArea expecifico no total da lista inteira
			totalList += total;
			// Adiciona o total da service area na lista de double
			lstServicesAreasValue.add(total);
		}
		// Remove linha do for que é criado a mais
		lstServicesAreasValue.remove(lstServicesAreasValue.size() - 1);
		// Adiciona o valor total na lista
		lstServicesAreasValue.add(totalList);
		// Retorna a lista de double com os valores de cada service area
		return lstServicesAreasValue;
	}
	
	// Retorna as services areas do arquivo inteiro
	private List<String> returnServicesAreas() {
			List<String> currentyServicesAreas = new ArrayList<String>();
			for (Sell sellItem : this.sellFile) {
				if (!currentyServicesAreas.contains(sellItem.getServiceArea()))
					currentyServicesAreas.add(sellItem.getServiceArea());
			}
			return currentyServicesAreas;
		}
	
	// Função pro construtor inicializar o objeto
	private void fileSellProcess(List<String> sellDataLine) {
		sellDataLine.remove(0);
		for (String line : sellDataLine) {
			this.sellFile.add(new Sell(line));
		}
	}
}
