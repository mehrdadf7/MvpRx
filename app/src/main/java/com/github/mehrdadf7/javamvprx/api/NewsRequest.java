package com.github.mehrdadf7.javamvprx.api;

import com.github.mehrdadf7.javamvprx.models.NewsResponse;
import com.github.mehrdadf7.okhttp.HttpStructure;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewsRequest implements NewsDataSource {

    private HttpStructure httpStructure;

    public NewsRequest(HttpStructure httpStructure) {
        this.httpStructure = httpStructure;
    }

    @Override
    public Observable<NewsResponse> getNews() {
        return httpStructure.makeRequest(
                "https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=847968758fc443dcbef779b238029441",
                NewsResponse.class
        ).send();
    }
}
