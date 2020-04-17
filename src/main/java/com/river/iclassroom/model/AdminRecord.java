package com.river.iclassroom.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class AdminRecord {
	private String id;
	private String area;
	private String building;
	private String number;
	private Date reserveDate;
	private int startCourse;
	private int endCourse;
	private String borrowA;
	private String borrowAId;
	private String borrowB;
	private String borrowBId;
	private String borrowC;
	private String borrowCId;
	private String phoneA;
	private int peopleNum;
	private String usage;
	private int result;
	private Timestamp timestamp;
	private String remarks;
	private int violationNum;
	private List<Borrow> borrows;

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

	public String getBorrowB() {
		return borrowB;
	}

	public void setBorrowB(String borrowB) {
		this.borrowB = borrowB;
	}

	public String getBorrowBId() {
		return borrowBId;
	}

	public void setBorrowBId(String borrowBId) {
		this.borrowBId = borrowBId;
	}

	public String getBorrowC() {
		return borrowC;
	}

	public void setBorrowC(String borrowC) {
		this.borrowC = borrowC;
	}

	public String getBorrowCId() {
		return borrowCId;
	}

	public void setBorrowCId(String borrowCId) {
		this.borrowCId = borrowCId;
	}

	public String getPhoneA() {
		return phoneA;
	}

	public void setPhoneA(String phoneA) {
		this.phoneA = phoneA;
	}

	public int getPeopleNum() {
		return peopleNum;
	}

	public void setPeopleNum(int peopleNum) {
		this.peopleNum = peopleNum;
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
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

	public List<Borrow> getBorrows() {
		return borrows;
	}

	public void setBorrows(List<Borrow> borrows) {
		this.borrows = borrows;
	}
}