package com.kh.seulcam.campBooking.domain;

import java.sql.Date;

public class BookingStatus {
    private int bookingNo;
    private Integer siteNo;
    private String memberId;
    private String sBookDate;
    private int totalDay;
    
    public BookingStatus() {}
    
    public BookingStatus(Integer siteNo,String memberId,String sBookDate,int totalDay) {
    	this.memberId = memberId;
        this.sBookDate = sBookDate;
        this.siteNo = siteNo;
        this.totalDay = totalDay;
    }

    @Override
    public String toString() {
        return "BookingStatus [bookingNo=" + bookingNo + ", siteNo=" + siteNo + ", memberId=" + memberId
                + ", sBookDate=" + sBookDate + ", totalDay=" + totalDay + "]";
    }

    public int getBookingNo() {
        return bookingNo;
    }

    public void setBookingNo(int bookingNo) {
        this.bookingNo = bookingNo;
    }

    public int getSiteNo() {
        return siteNo;
    }

    public void setSiteNo(Integer siteNo) {
        this.siteNo = siteNo;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getsBookDate() {
        return sBookDate;
    }

    public void setsBookDate(String sBookDate) {
        this.sBookDate = sBookDate;
    }

    public int getTotalDay() {
        return totalDay;
    }

    public void setTotalDay(int totalDay) {
        this.totalDay = totalDay;
    }
    
    
    
}
