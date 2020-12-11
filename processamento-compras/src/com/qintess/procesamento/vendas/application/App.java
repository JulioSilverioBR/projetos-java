package com.qintess.procesamento.vendas.application;

import java.io.IOException;

import com.qintess.processamento.vendas.entities.Sell;
import com.qintess.processamento.vendas.services.Process;

public class App {

	public static void main(String[] args) throws IOException {
		Process process = new Process();
		process.start();
		System.out.println("------------------------------------------------------------------------------------------------------------------");
		System.out.println("Ex1 - Sell Total: "+ String.format("R$ %.2f", process.showSellTotal(new Sell())));
		
		Sell Sells = new Sell();
		Sells.setCreditor("Amazon UK MarketPlace");

		System.out.println("\n--------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Ex2 - Show Sells, Total Value and Services Areas of Creditor "+Sells.getCreditor()+"\n");
		process.showSellItemsofCreditor(Sells).forEach(sell -> System.out.println(sell));
		System.out.println("\nServices Areas of "+Sells.getCreditor());
		process.returnServicesAreas(Sells).forEach(sell -> System.out.println(sell));
		System.out.println("\nSell Total of Creditor "+ Sells.getCreditor()+ " R$ "+String.format("%.2f", process.showSellTotal(Sells)));

		System.out.println("\n----------------------------------------------------------------------------------");
		System.out.println("Ex3 - Write a file with the sells of Amazon EU and the value update by 5% plus");
		Sells.setCreditor("AMAZON EU");
		process.writeFileCreditor(Sells, 5);
		System.out.println("File Created with Successful");
		
		System.out.println("\n----------------------------------------------------------------------------------");
		System.out.println("Ex4 - Group the service areas and return the total purchase value of each one\n");
		process.groupServicesAreasAndShowTotalValue().forEach(sell -> System.out.println(sell));
		System.out.println("----------------------------------------------------------------------------------");
		
	}

}
