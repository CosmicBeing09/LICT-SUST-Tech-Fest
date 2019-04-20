package com.example.raihan.sharefoods;

public class notification {
    String title,body;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public notification() {

    }

    public notification(String title, String body) {

        this.title = title;
        this.body = body;
    }
}
