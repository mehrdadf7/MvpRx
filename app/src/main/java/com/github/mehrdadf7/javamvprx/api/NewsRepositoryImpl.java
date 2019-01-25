package com.github.mehrdadf7.javamvprx.api;

import com.github.mehrdadf7.javamvprx.models.NewsApiResponse;
import com.github.mehrdadf7.okhttp.OkHttpInjector;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewsRepositoryImpl implements NewsDataSource, Observer<NewsApiResponse> {

    private ObservableEmitter<NewsApiResponse> responseEmitter;

    @Override
    public Observable<NewsApiResponse> getNews() {
        downloadData(new RemoteNewsDataRepository(OkHttpInjector.getHttpClient()));
        return Observable.create(new ObservableOnSubscribe<NewsApiResponse>() {
            @Override
            public void subscribe(ObservableEmitter<NewsApiResponse> emitter) {
                NewsRepositoryImpl.this.responseEmitter = emitter;
            }
        });
    }

    private void downloadData(RemoteNewsDataRepository remoteNewsDataRepository) {
        remoteNewsDataRepository.getNews()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    public void onSubscribe(Disposable d) {}

    @Override
    public void onNext(NewsApiResponse newsApiResponse) {
        responseEmitter.onNext(newsApiResponse);
        responseEmitter.onComplete();
    }

    @Override
    public void onError(Throwable e) {
        responseEmitter.onError(e);
    }

    @Override
    public void onComplete() {}
}
