package com.faultsystem.beans;

public class Life {
	private long lifeId;
	private String lifeName;
	private String lifeDetail;
	
	public Life(){
		this.lifeId = 0;
		this.lifeName = "";
		this.lifeDetail = "";
		
	}
	public Life(String lifeName){
		
		this.lifeName = lifeName;
	}
	public Life(long lifeId, String lifeName, String lifeDetail){
		this.lifeId = lifeId;
		this.lifeName = lifeName;
		this.lifeDetail = lifeDetail;
	}
	public long getLifeId() {
		return lifeId;
	}
	public void setLifeId(long lifeId) {
		this.lifeId = lifeId;
	}
	public String getLifeName() {
		return lifeName;
	}
	public void setLifeName(String lifeName) {
		this.lifeName = lifeName;
	}
	public String getLifeDetail() {
		return lifeDetail;
	}
	public void setLifeDetail(String lifeDetail) {
		this.lifeDetail = lifeDetail;
	}
	@Override
	public String toString() {
		return lifeName+lifeDetail;
	}
	
}
