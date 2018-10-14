package com.example.hp.yoganews;

public class News
{
    String sectionName;
    String header;
    String date;
    String url;
    String thumbnail;

    public News(String sectionName, String header, String date, String url, String thumbnail) {
        this.sectionName = sectionName;
        this.header = header;
        this.date = date;
        this.url = url;
        this.thumbnail = thumbnail;
    }

    public String getSectionName() {
        return sectionName;
    }
    public String getThumbnail() {
        return thumbnail;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
