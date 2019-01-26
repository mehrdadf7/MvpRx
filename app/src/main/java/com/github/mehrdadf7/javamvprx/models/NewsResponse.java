package com.github.mehrdadf7.javamvprx.models;

import java.util.ArrayList;
import java.util.List;

public class NewsResponse {

    private String status;
    private int totalResults;
    private List<News> articles = new ArrayList<>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<News> getArticles() {
        return articles;
    }

    public void setArticles(List<News> articles) {
        this.articles = articles;
    }
}
