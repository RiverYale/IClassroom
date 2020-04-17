package com.river.iclassroom.model;

import java.sql.Date;
import java.sql.Timestamp;

public class UserRecord {
	private String id;
	private String area;
	private String building;
	private String number;
	private Date reserveDate;
	private int startCourse;
	private int endCourse;
	private String borrowA;
	private String borrowAId;
	private int result;
	private String remarks;
	private int violationNum;
	private Timestamp timestamp;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getReserveDate() {
		return reserveDate;
	}

	public void setReserveDate(Date reserveDate) {
		this.reserveDate = reserveDate;
	}

	public int getStartCourse() {
		return startCourse;
	}

	public void setStartCourse(int startCourse) {
		this.startCourse = startCourse;
	}

	public int getEndCourse() {
		return endCourse;
	}

	public void setEndCourse(int endCourse) {
		this.endCourse = endCourse;
	}

	public String getBorrowA() {
		return borrowA;
	}

	public void setBorrowA(String borrowA) {
		this.borrowA = borrowA;
	}

	public String getBorrowAId() {
		return borrowAId;
	}

	public void setBorrowAId(String borrowAId) {
		this.borrowAId = borrowAId;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getViolationNum() {
		return violationNum;
	}

	public void setViolationNum(int violationNum) {
		this.violationNum = violationNum;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
}
