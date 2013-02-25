package com.faultsystem.beans;

import java.sql.Timestamp;

public class Reporter {

	private long reporterId;
	private String name;
	private String password;
	private String work;
	private String selfDesc;
	private String emailAdd;
	private int credit;
	private Timestamp regTime;
	
	public Reporter()
	{
		this.reporterId = 0;
		this.name = "";
		this.password = "";
		this.work = "";
		this.selfDesc = "";
		this.emailAdd = "";
		this.credit =0;
		this.regTime = new Timestamp(2013, 02, 19, 20,20, 0, 0);
	}
	public Reporter(String name,String password, String work, String selfDesc,String emailAdd,int credit){
	
		this.name = name;
		this.password = password;
		this.work = work;
		this.selfDesc = selfDesc;
		this.emailAdd = emailAdd;
	
		this.credit = credit;
	}
	public Reporter(long reporterId,String name,String password, String work, String selfDesc,String emailAdd,int credit, Timestamp regTime){
		this.reporterId = reporterId;
		this.name = name;
		this.password = password;
		this.work = work;
		this.selfDesc = selfDesc;
		this.emailAdd = emailAdd;
		this.regTime = regTime;
		this.credit = credit;
	}
	public long getReporterId() {
		return reporterId;
	}
	public void setReporterId(long reporterId) {
		this.reporterId = reporterId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public String getSelfDesc() {
		return selfDesc;
	}
	public void setSelfDesc(String selfDesc) {
		this.selfDesc = selfDesc;
	}
	public String getEmailAdd() {
		return emailAdd;
	}
	public void setEmailAdd(String emailAdd) {
		this.emailAdd = emailAdd;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public Timestamp getRegTime() {
		return regTime;
	}
	public void setRegTime(Timestamp regTime) {
		this.regTime = regTime;
	}
	@Override
	public String toString() {
		return "Reporter [credit=" + credit + ", emailAdd=" + emailAdd
				+ ", name=" + name + ", password=" + password + ", regTime="
				+ regTime + ", reporterId=" + reporterId + ", selfDesc="
				+ selfDesc + ", work=" + work + "]";
	}
	
}
