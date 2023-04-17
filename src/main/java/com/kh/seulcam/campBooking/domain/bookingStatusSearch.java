package com.kh.seulcam.campBooking.domain;

public class bookingStatusSearch {
    private int siteNo;
    private String firstDay;
    private String lastDay;
    
    public bookingStatusSearch() {}
    public bookingStatusSearch(int siteNo,String firstDay,String lastDay) {
    	this.siteNo = siteNo;
    	this.firstDay = firstDay;
    	this.lastDay = lastDay;
    }
    @Override
    public String toString() {
        return "bookingStatusSearch [siteNo=" + siteNo + ", firstDay=" + firstDay + ", lastDay=" + lastDay + "]";
    }
    public int getSiteNo() {
        return siteNo;
    }
    public void setSiteNo(int siteNo) {
        this.siteNo = siteNo;
    }
    public String getFirstDay() {
        return firstDay;
    }
    public void setFirstDay(String firstDay) {
        this.firstDay = firstDay;
    }
    public String getLastDay() {
        return lastDay;
    }
    public void setLastDay(String lastDay) {
        this.lastDay = lastDay;
    }
    
    
}
