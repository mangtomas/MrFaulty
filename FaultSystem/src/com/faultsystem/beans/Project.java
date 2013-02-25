package com.faultsystem.beans;

import java.sql.Timestamp;

public class Project {
	private long projectId;
	private String projectName;
	private String projetDesc;
	private String downloadLink;
	private long devId;
	private Timestamp createTime;
	private Timestamp updateTime;
	
	public Project(){
		this.projectId = 0;
		this.projectName = "";
		this.projetDesc = "";
		this.downloadLink = "";
		this.devId = 0;
		this.createTime = new Timestamp(2013, 02, 19, 20,20, 0, 0);
		this.updateTime = new Timestamp(2013, 02, 19, 20,20, 0, 0);
		
	}
	 public Project( String projectName, String projectDesc, String downloadLink, long devId){
		 	
			this.projectName = projectName;
			this.projetDesc = projectDesc;
			this.downloadLink = downloadLink;
			this.devId = devId;
			
	 }
	 public Project(long projectId, String projectName, String projectDesc, String downloadLink, long devId, Timestamp createTime, Timestamp updateTime){
		 	this.projectId = projectId;
			this.projectName = projectName;
			this.projetDesc = projectDesc;
			this.downloadLink = downloadLink;
			this.devId = devId;
			this.createTime = createTime;
			this.updateTime = updateTime;
	 }
	public long getProjectId() {
		return projectId;
	}
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjetDesc() {
		return projetDesc;
	}
	public void setProjetDesc(String projetDesc) {
		this.projetDesc = projetDesc;
	}
	public String getDownloadLink() {
		return downloadLink;
	}
	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}
	public long getDevId() {
		return devId;
	}
	public void setDevId(long devId) {
		this.devId = devId;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "Project [createTime=" + createTime + ", devId=" + devId
				+ ", downloadLink=" + downloadLink + ", projectId=" + projectId
				+ ", projectName=" + projectName + ", projetDesc=" + projetDesc
				+ ", updateTime=" + updateTime + "]";
	}
	 
	

}
