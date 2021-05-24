package com.example.covid19tracker;

public class News {
    String source;
    String title;
    String url;
    String imageUrl;

    public News(String title, String url, String imageUrl,String source) {
        this.title = title;
        this.url = url;
        this.imageUrl = imageUrl;
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
