package com.github.mehrdadf7.javamvprx.home.model;

import com.github.mehrdadf7.javamvprx.server.DataSource;

import java.util.ArrayList;
import java.util.List;

public class NewsApiResponse {

    private String status;
    private int totalResults;

    private DataSource.Type type = DataSource.Type.REMOTE;
    private List<NewsViewModel> articles = new ArrayList<>();

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

    public DataSource.Type getType() {
        return type;
    }

    public void setType(DataSource.Type type) {
        this.type = type;
    }

    public List<NewsViewModel> getArticles() {
        return articles;
    }

    public void setArticles(List<NewsViewModel> articles) {
        this.articles = articles;
    }
}
