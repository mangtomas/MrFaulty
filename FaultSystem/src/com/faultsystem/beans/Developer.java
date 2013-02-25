package com.faultsystem.beans;

import java.sql.Timestamp;

public class Developer {
	private long developerId;
	private String name;
	private String password;
	private String work;
	private String selfDesc;
	private String emailAdd;
	private Timestamp regTime;
	
	public Developer(){
		this.developerId = 0;
		this.name = "";
		this.password = "";
		this.work = "";
		this.selfDesc = "";
		this.emailAdd = "";
		this.regTime = new Timestamp(2013, 02, 19, 20,20, 0, 0);
	}
	public Developer(String name,String password, String work, String selfDesc,String emailAdd){
	
		this.name = name;
		this.password = password;
		this.work = work;
		this.selfDesc = selfDesc;
		this.emailAdd = emailAdd;
		
	}

	public Developer(long developerId,String name,String password, String work, String selfDesc,String emailAdd, Timestamp regTime){
		this.developerId = developerId;
		this.name = name;
		this.password = password;
		this.work = work;
		this.selfDesc = selfDesc;
		this.emailAdd = emailAdd;
		this.regTime = regTime;
	}


	public long getDeveloperId() {
		return developerId;
	}


	public void setDeveloperId(long developerId) {
		this.developerId = developerId;
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


	public Timestamp getRegTime() {
		return regTime;
	}


	public void setRegTime(Timestamp regTime) {
		this.regTime = regTime;
	}


	@Override
	public String toString() {
		return "Developer [developerId=" + developerId + ", emailAdd="
				+ emailAdd + ", name=" + name + ", password=" + password
				+ ", regTime=" + regTime + ", selfDesc=" + selfDesc + ", work="
				+ work + "]";
	}
	
	

}
