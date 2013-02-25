package com.faultsystem.beans;

import java.sql.Timestamp;

public class Fault {
	private long faultId;
	private String summary;
	private String detail;
	private long reporterId;
	private long projectId;
	private long stateId;
	private long lifeId;
	private Timestamp createTime;
	private Timestamp updateTime;
	private int severityLevel;
	private String projectName;
	private String reporterName;
	private String stateName;
	private String lifeName;
	
	public Fault(){
		this.faultId = 0;
		this.summary = "";
		this.detail = "";
		this.projectId = 0;
		this.reporterId = 0;
		this.stateId = 0;
		this.lifeId = 0;
		this.severityLevel = 0;
		this.createTime = new Timestamp(2013, 02, 19, 20,20, 0, 0);
		this.updateTime = new Timestamp(2013, 02, 19, 20,20, 0, 0);
		
	}
public Fault(long faultId, String summary, String detail,int severityLevel,String reporterName, String projectName,String stateName,String lifeName,Timestamp createTime, Timestamp updateTime){
		
	this.faultId = faultId;
	this.summary = summary;
	this.detail = detail;
	this.projectName = projectName;
	this.reporterName = reporterName;
	this.stateName = stateName;;
	this.lifeName = lifeName;
	this.createTime = createTime;
	this.updateTime = updateTime;
	this.severityLevel = severityLevel;
	}
	public Fault( String summary, String detail,int severityLevel, long reporterId, long projectId,long stateId,long lifeId){
		
		this.summary = summary;
		this.detail = detail;
		this.projectId = projectId;
		this.reporterId = reporterId;
		this.stateId = stateId;
		this.lifeId = lifeId;
		
		this.severityLevel = severityLevel;
	}
	public Fault(long faultId, String summary, String detail,int severityLevel, long reporterId, long projectId,long stateId,long lifeId,Timestamp createTime, Timestamp updateTime){
		this.faultId = faultId;
		this.summary = summary;
		this.detail = detail;
		this.projectId = projectId;
		this.reporterId = reporterId;
		this.stateId = stateId;
		this.lifeId = lifeId;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.severityLevel = severityLevel;
	}
	public long getFaultId() {
		return faultId;
	}
	public void setFaultId(long faultId) {
		this.faultId = faultId;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public long getReporterId() {
		return reporterId;
	}
	public void setReporterId(long reporterId) {
		this.reporterId = reporterId;
	}
	public long getProjectId() {
		return projectId;
	}
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
	public long getStateId() {
		return stateId;
	}
	public void setStateId(long stateId) {
		this.stateId = stateId;
	}
	public long getLifeId() {
		return lifeId;
	}
	public void setLifeId(long lifeId) {
		this.lifeId = lifeId;
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
	
	public int getSeverityLevel() {
		return severityLevel;
	}
	public void setSeverityLevel(int severityLevel) {
		this.severityLevel = severityLevel;
	}
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getReporterName() {
		return reporterName;
	}
	public void setReporterName(String reporterName) {
		this.reporterName = reporterName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getLifeName() {
		return lifeName;
	}
	public void setLifeName(String lifeName) {
		this.lifeName = lifeName;
	}
	@Override
	public String toString() {
		/*return "Fault [createTime=" + createTime + ", detail=" + detail
				+ ", faultId=" + faultId + ", lifeId=" + lifeId
				+ ", projectId=" + projectId + ", reporterId=" + reporterId
				+ ", severityLevel=" + severityLevel + ", stateId=" + stateId
				+ ", summary=" + summary + ", updateTime=" + updateTime + "]";
	*/
		return faultId+"\t"+summary+"\t"+detail+"\t"+severityLevel+"\t"+projectName+"\t"+ reporterName+"\t"+stateName+"\t"+lifeName+"\t"+createTime+"\t"+updateTime;
	}
	
	
	

}
