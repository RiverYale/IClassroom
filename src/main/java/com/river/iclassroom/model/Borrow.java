package com.river.iclassroom.model;

public class Borrow {
	private String reservationId;
	private String objectName;
	private int objectNum;
	public String getReservationId() {
		return reservationId;
	}
	public void setReservationId(String reservationId) {
		this.reservationId = reservationId;
	}
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public int getObjectNum() {
		return objectNum;
	}
	public void setObjectNum(int objectNum) {
		this.objectNum = objectNum;
	}
}
