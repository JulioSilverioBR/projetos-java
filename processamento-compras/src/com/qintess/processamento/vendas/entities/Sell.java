package com.qintess.processamento.vendas.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Sell {
	
	String serviceArea = "";
	String accountDescription = "";
	String creditor = "";
	LocalDate journalDate = LocalDate.now() ;
	int journalReference = 0;
	double total = 0;
	
	public Sell() {}

	public Sell(String line) {
		String[] columns = line.split(";");
		if(columns.length == 6) {
		this.serviceArea = columns[0];
		this.accountDescription = columns[1];
		this.creditor = columns[2];
		this.journalDate = LocalDate.parse(columns[3], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.journalReference = Integer.parseInt(columns[4]);
		this.total = Double.parseDouble(columns[5]);
		}
	}
	
	public String getServiceArea() {
		return serviceArea;
	}
	public void setServiceArea(String serviceArea) {
		this.serviceArea = serviceArea;
	}
	public String getAccountDescription() {
		return accountDescription;
	}
	public void setAccountDescription(String accountDescription) {
		this.accountDescription = accountDescription;
	}
	public String getCreditor() {
		return creditor;
	}
	public void setCreditor(String creditor) {
		this.creditor = creditor;
	}
	public LocalDate getJournalDate() {
		return journalDate;
	}
	public void setJournalDate(LocalDate journalDate) {
		this.journalDate = journalDate;
	}
	public Integer getJournalReference() {
		return journalReference;
	}
	public void setJournalReference(Integer journalReference) {
		this.journalReference = journalReference;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return  "| Service Area: " + serviceArea +
				"| Creditor: "+ creditor + 
			    "| Date: " + journalDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
			    "| Reference: " + journalReference + 
			    "| Total: "+ total + 
			    "|";
	}
}
