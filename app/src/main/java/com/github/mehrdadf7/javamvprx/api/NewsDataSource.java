package com.github.mehrdadf7.javamvprx.api;

import com.github.mehrdadf7.javamvprx.models.NewsApiResponse;

import io.reactivex.Observable;

public interface NewsDataSource {
    Observable<NewsApiResponse> getNews();
}
