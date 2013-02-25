package com.faultsystem.beans;

public class State {
	private long stateId;
	private String stateName;
	private String stateDetail;
	
	public State(){
		this.stateId = 0;
		this.stateDetail = "";
		this.stateName = "";
		
	}
	public State( String stateName){
		this.stateName = stateName;
	}
	public State(long ststeId, String stateName, String stateDetail){
		this.stateId = ststeId;
		this.stateName = stateName;
		this.stateDetail = stateDetail;
	}
	public long getStateId() {
		return stateId;
	}
	public void setStateId(long stateId) {
		this.stateId = stateId;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getStateDetail() {
		return stateDetail;
	}
	public void setStateDetail(String stateDetail) {
		this.stateDetail = stateDetail;
	}
	@Override
	public String toString() {
		return stateName+stateDetail;
	}
	
	

}
