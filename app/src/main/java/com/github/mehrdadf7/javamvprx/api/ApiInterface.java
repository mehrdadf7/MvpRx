package com.github.mehrdadf7.javamvprx.api;

import com.github.mehrdadf7.javamvprx.models.NewsResponse;

import io.reactivex.Observable;

public interface ApiInterface {
    Observable<NewsResponse> getNews();
}
