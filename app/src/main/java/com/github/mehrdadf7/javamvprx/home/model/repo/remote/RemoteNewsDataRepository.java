package com.github.mehrdadf7.javamvprx.home.model.repo.remote;

import com.github.mehrdadf7.javamvprx.home.model.NewsApiResponse;
import com.github.mehrdadf7.javamvprx.home.model.repo.NewsDataSource;
import com.github.mehrdadf7.okhttp.HttpRequest;
import com.github.mehrdadf7.okhttp.HttpStructure;

import io.reactivex.Observable;

public class RemoteNewsDataRepository implements NewsDataSource {

    private HttpStructure httpStructure;

    public RemoteNewsDataRepository(HttpStructure httpStructure) {
        this.httpStructure = httpStructure;
    }

    @Override
    public Observable<NewsApiResponse> getNews() {
        return httpStructure.makeRequest(
                HttpRequest.Method.GET,
                "https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=847968758fc443dcbef779b238029441",
                "",
                NewsApiResponse.class
        ).send();
    }
}
