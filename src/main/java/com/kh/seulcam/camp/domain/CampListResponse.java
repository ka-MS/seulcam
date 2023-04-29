package com.kh.seulcam.camp.domain;

import java.util.List;

public class CampListResponse {
	private List<Camp>  camp;
	private int campListCount;
	
	public CampListResponse(List<Camp> cList,int campListCount){
		this.camp = cList;
		this.campListCount = campListCount;
	}
	
	@Override
	public String toString() {
		return "CampListResponse [camp=" + camp + ", campListCount=" + campListCount + "]";
	}

	
	public List<Camp> getCamp() {
		return camp;
	}

	public void setCamp(List<Camp> camp) {
		this.camp = camp;
	}

	public int getCampListCount() {
		return campListCount;
	}
	public void setCampListCount(int campListCount) {
		this.campListCount = campListCount;
	}
	
	
	

}
