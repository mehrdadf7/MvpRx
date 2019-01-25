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

public class NewsRepositoryImpl implements NewsDataSource, Observer<NewsResponse> {

    private ObservableEmitter<NewsResponse> responseEmitter;

    @Override
    public Observable<NewsResponse> getNews() {
        downloadData(new NewsRepositoryData(OkHttpInjector.getHttpClient()));
        return Observable.create(new ObservableOnSubscribe<NewsResponse>() {
            @Override
            public void subscribe(ObservableEmitter<NewsResponse> emitter) {
                NewsRepositoryImpl.this.responseEmitter = emitter;
            }
        });
    }

    private void downloadData(NewsRepositoryData newsRepositoryData) {
        newsRepositoryData.getNews()
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
