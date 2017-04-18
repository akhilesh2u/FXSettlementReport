package com.jpmc.core.test;

/**
* FXSettlementDataModel is a POJO class which stores the FX Transactions
*
* @author  Akhilesh Perla
* @version 1.0
* @since   2017-04-17 
*/

import java.time.LocalDate;
import java.util.Currency;
import java.util.stream.IntStream;


public class FXTransactionDataModel {

	private String entity;
	private char transcationType;
	private float agreedFx;
	private Currency currenyType;
	private LocalDate instructionDate;
	private LocalDate settlementDate;
	private int units;
	private double pricePerUnit;
	private Double totalTransaction;
	private static final int[] nonSettlementDate = { 6, 7 };
	private int rank;
	private static final int[] aedNonSettlementDate = { 5, 6 };

	public FXTransactionDataModel(String entity, char transcationType, float agreedFx, Currency currenyType,
			LocalDate instructionDate, LocalDate settlementDate, int units, double pricePerUnit) {

		
		this.entity = entity;
		this.transcationType = transcationType;
		this.agreedFx = agreedFx;
		this.currenyType = currenyType;
		this.instructionDate = instructionDate;

		this.units = units;
		this.pricePerUnit = pricePerUnit;
		this.totalTransaction = pricePerUnit * units * agreedFx;

		this.settlementDate = settlementDate;

		/*
		 * Calculate the Settlement Date based on the currency working day
		 * calculator
		 */
		if (currenyType.toString().equals("SAR") || currenyType.toString().equals("AED")) {
			if (IntStream.of(aedNonSettlementDate).anyMatch(x -> x == settlementDate.getDayOfWeek().getValue())) {

				this.settlementDate = settlementDate.plusDays(7 % settlementDate.getDayOfWeek().getValue());
			}
		} else {
			if (IntStream.of(nonSettlementDate).anyMatch(x -> x == settlementDate.getDayOfWeek().getValue())) {
				int i = 7 % settlementDate.getDayOfWeek().getValue() + 1;
				this.settlementDate = settlementDate.plusDays(i);
			}
		}

	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public char getTranscationType() {
		return transcationType;
	}

	public void setTranscationType(char c) {
		transcationType = c;
	}

	public float getAgreedFx() {
		return agreedFx;
	}

	public void SetAgreedFx(float agred) {
		agreedFx = agred;
	}

	public Currency getCurrenyType() {
		return currenyType;
	}

	public void setCurrenyType(Currency curr) {
		currenyType = curr;
	}

	public LocalDate getInstructionDate() {
		return instructionDate;
	}

	public void setInstructionDate(LocalDate ld) {
		instructionDate = ld;
	}

	public LocalDate getSettlementDate() {

		return settlementDate;
	}

	public void setSettlementDate(LocalDate ld) {
		settlementDate = ld;
	}

	public int getUnints() {
		return units;
	}

	public void setUnints(int units) {
		this.units = units;
	}

	public double getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(double pri) {
		pricePerUnit = pri;
	}

	public Double getTotalTransaction() {
		return totalTransaction;
	}

	public void setTotalTransaction(Double d) {
		totalTransaction = d;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

}
