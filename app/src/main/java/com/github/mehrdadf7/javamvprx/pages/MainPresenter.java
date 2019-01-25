package com.github.mehrdadf7.javamvprx.pages;

import com.github.mehrdadf7.javamvprx.models.NewsResponse;
import com.github.mehrdadf7.javamvprx.api.NewsRepositoryImpl;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private NewsRepositoryImpl newsRepository;
    private Disposable disposable;

    public MainPresenter(NewsRepositoryImpl newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public void loadNews() {
        view.setProgressIndicator(true);
        newsRepository.getNews()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<NewsResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(NewsResponse newsResponse) {
                        view.showNews(newsResponse.getArticles());
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        view.setProgressIndicator(false);
                    }
                });
    }

    @Override
    public void attachView(MainContract.View view) {
        this.view = view;
        loadNews();
    }

    @Override
    public void detachView() {
        this.view = null;
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
