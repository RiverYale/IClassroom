package com.river.iclassroom.model;

import java.sql.Timestamp;

public class ViolationRecord {
	private String reservationId;
	private int num;
	private String behavior;
	private String borrowA;
	private String phoneA;
	private String classroom;
	private Timestamp timestamp;
	public String getReservationId() {
		return reservationId;
	}
	public void setReservationId(String reservationId) {
		this.reservationId = reservationId;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getBehavior() {
		return behavior;
	}
	public void setBehavior(String behavior) {
		this.behavior = behavior;
	}
	public String getBorrowA() {
		return borrowA;
	}
	public void setBorrowA(String borrowA) {
		this.borrowA = borrowA;
	}
	public String getPhoneA() {
		return phoneA;
	}
	public void setPhoneA(String phoneA) {
		this.phoneA = phoneA;
	}
	public String getClassroom() {
		return classroom;
	}
	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
}
