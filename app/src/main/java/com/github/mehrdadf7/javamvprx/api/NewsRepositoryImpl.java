package com.github.mehrdadf7.javamvprx.api;

import com.github.mehrdadf7.javamvprx.models.NewsResponse;
import com.github.mehrdadf7.okhttp.OkHttpInjector;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewsRepositoryImpl implements ApiInterface, Observer<NewsResponse> {

    private ObservableEmitter<NewsResponse> responseEmitter;

    @Override
    public Observable<NewsResponse> getNews() {
        return Observable.create(new ObservableOnSubscribe<NewsResponse>() {
            @Override
            public void subscribe(ObservableEmitter<NewsResponse> emitter) {
                NewsRepositoryImpl.this.responseEmitter = emitter;
                getData(new NewsRequest(OkHttpInjector.getHttpClient()));
            }
        });
    }

    private void getData(NewsRequest newsRequest) {
        newsRequest.getNews()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    public void onSubscribe(Disposable d) {}

    @Override
    public void onNext(NewsResponse newsResponse) {
        responseEmitter.onNext(newsResponse);
        responseEmitter.onComplete();
    }

    @Override
    public void onError(Throwable e) {
        responseEmitter.onError(e);
    }

    @Override
    public void onComplete() {}
}
