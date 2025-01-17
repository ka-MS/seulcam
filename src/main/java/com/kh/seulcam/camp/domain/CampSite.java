package com.kh.seulcam.camp.domain;

import java.sql.Date;

public class CampSite {
    private int siteNo;
    private String siteName;
    private int campId;
    private String campName;
    private String siteChar;
    private String siteInfo;
    private String siteIntro;
    private int standardPeople;
    private int maxPeople;
    private int excessCharge;
    private int inTime;
    private int outTime;
    private int sitePrice;
    private int siteCount;
    private String siteThumbnailName;
    private String siteThumbnailPath;
    private String siteThumbnailRename;
    private Date sCreateDate;
    private String sStatusUse;
    private String sStatusDel;
    
    public CampSite() {}

    @Override
    public String toString() {
        return "CampSite [siteNo=" + siteNo + ", siteName=" + siteName + ", campId=" + campId + ", campName=" + campName
                + ", siteChar=" + siteChar + ", siteInfo=" + siteInfo + ", siteIntro=" + siteIntro + ", standardPeople="
                + standardPeople + ", maxPeople=" + maxPeople + ", excessCharge=" + excessCharge + ", inTime=" + inTime
                + ", outTime=" + outTime + ", sitePrice=" + sitePrice + ", siteCount=" + siteCount
                + ", siteThumbnailName=" + siteThumbnailName + ", siteThumbnailPath=" + siteThumbnailPath
                + ", siteThumbnailRename=" + siteThumbnailRename + ", sCreateDate=" + sCreateDate + ", sStatusUse="
                + sStatusUse + ", sStatusDel=" + sStatusDel + "]";
    }

    public int getSiteNo() {
        return siteNo;
    }

    public void setSiteNo(int siteNo) {
        this.siteNo = siteNo;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public int getCampId() {
        return campId;
    }

    public void setCampId(int campId) {
        this.campId = campId;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public String getSiteChar() {
        return siteChar;
    }

    public void setSiteChar(String siteChar) {
        this.siteChar = siteChar;
    }

    public String getSiteInfo() {
        return siteInfo;
    }

    public void setSiteInfo(String siteInfo) {
        this.siteInfo = siteInfo;
    }

    public String getSiteIntro() {
        return siteIntro;
    }

    public void setSiteIntro(String siteIntro) {
        this.siteIntro = siteIntro;
    }

    public int getStandardPeople() {
        return standardPeople;
    }

    public void setStandardPeople(int standardPeople) {
        this.standardPeople = standardPeople;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public int getExcessCharge() {
        return excessCharge;
    }

    public void setExcessCharge(int excessCharge) {
        this.excessCharge = excessCharge;
    }

    public int getInTime() {
        return inTime;
    }

    public void setInTime(int inTime) {
        this.inTime = inTime;
    }

    public int getOutTime() {
        return outTime;
    }

    public void setOutTime(int outTime) {
        this.outTime = outTime;
    }

    public int getSitePrice() {
        return sitePrice;
    }

    public void setSitePrice(int sitePrice) {
        this.sitePrice = sitePrice;
    }

    public int getSiteCount() {
        return siteCount;
    }

    public void setSiteCount(int siteCount) {
        this.siteCount = siteCount;
    }

    public String getSiteThumbnailName() {
        return siteThumbnailName;
    }

    public void setSiteThumbnailName(String siteThumbnailName) {
        this.siteThumbnailName = siteThumbnailName;
    }

    public String getSiteThumbnailPath() {
        return siteThumbnailPath;
    }

    public void setSiteThumbnailPath(String siteThumbnailPath) {
        this.siteThumbnailPath = siteThumbnailPath;
    }

    public String getSiteThumbnailRename() {
        return siteThumbnailRename;
    }

    public void setSiteThumbnailRename(String siteThumbnailRename) {
        this.siteThumbnailRename = siteThumbnailRename;
    }

    public Date getsCreateDate() {
        return sCreateDate;
    }

    public void setsCreateDate(Date sCreateDate) {
        this.sCreateDate = sCreateDate;
    }

    public String getsStatusUse() {
        return sStatusUse;
    }

    public void setsStatusUse(String sStatusUse) {
        this.sStatusUse = sStatusUse;
    }

    public String getsStatusDel() {
        return sStatusDel;
    }

    public void setsStatusDel(String sStatusDel) {
        this.sStatusDel = sStatusDel;
    }

    
    
    
    
}
