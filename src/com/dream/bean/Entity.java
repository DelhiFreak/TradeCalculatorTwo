package com.dream.bean;

import java.util.Date;

public class Entity {
	private String name;
	private String type;
	private float agreedFx;
	private String currency;
	private Date instructionDate;
	private Date settlementDate;
	private int units;
	private float pricePerUnit;
	private int incomingRank;
	private int outgoingRank;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getAgreedFx() {
		return agreedFx;
	}

	public void setAgreedFx(float agreedFx) {
		this.agreedFx = agreedFx;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Date getInstructionDate() {
		return instructionDate;
	}

	public void setInstructionDate(Date instructionDate) {
		this.instructionDate = instructionDate;
	}

	public Date getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public float getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(float pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public Float getTradeAmount() {
		return this.pricePerUnit * this.units * this.agreedFx;
	}

	public int getIncomingRank() {
		return incomingRank;
	}

	public void setIncomingRank(int incomingRank) {
		this.incomingRank = incomingRank;
	}

	public int getOutgoingRank() {
		return outgoingRank;
	}

	public void setOutgoingRank(int outgoingRank) {
		this.outgoingRank = outgoingRank;
	}

	@Override
	public String toString() {
		return "Entity [name=" + name + ", type=" + type + ", agreedFx=" + agreedFx + ", currency=" + currency
				+ ", instructionDate=" + instructionDate + ", settlementDate=" + settlementDate + ", units=" + units
				+ ", pricePerUnit=" + pricePerUnit + ", incomingRank=" + incomingRank + ", outgoingRank=" + outgoingRank
				+ ", Trade Amount=" + getTradeAmount() + "]";
	}

}
