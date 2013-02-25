package com.faultsystem.beans;

public class Admin {

	
	private long adminId;
	private String adminName;
	private String password;
	private String emailAdd;
	
	public long getAdminId() {
		return adminId;
	}
	
	
	public Admin(){
		this.adminId = 0;
		this.adminName = "";
		this.password = "";
		this.emailAdd = "";
		
		
	}
	public Admin(String adminName,String password, String emailAdd){
		this.adminName = adminName;
		this.password = password;
		this.emailAdd = emailAdd;
		
		
	}
	public Admin(long adminId,String adminName,String password,String emailAdd){
		this.adminId = adminId;
		this.adminName = adminName;
		this.password = password;
		this.emailAdd = emailAdd;
		
	}
	
	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmailAdd() {
		return emailAdd;
	}


	public void setEmailAdd(String emailAdd) {
		this.emailAdd = emailAdd;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (adminId ^ (adminId >>> 32));
		result = prime * result
				+ ((adminName == null) ? 0 : adminName.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		if (adminId != other.adminId)
			return false;
		if (adminName == null) {
			if (other.adminName != null)
				return false;
		} else if (!adminName.equals(other.adminName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
	public String toString(){
		return adminId+"\t"+ adminName+"\t"+ password+"\t"+ emailAdd;
	}

}
