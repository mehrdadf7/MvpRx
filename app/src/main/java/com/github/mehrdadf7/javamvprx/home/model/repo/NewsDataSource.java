package com.github.mehrdadf7.javamvprx.home.model.repo;

import com.github.mehrdadf7.javamvprx.home.model.NewsApiResponse;

import io.reactivex.Observable;

public interface NewsDataSource {
    Observable<NewsApiResponse> getNews();
}
