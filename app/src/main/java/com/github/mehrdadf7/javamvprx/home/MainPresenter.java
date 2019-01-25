package com.github.mehrdadf7.javamvprx.home;

import com.github.mehrdadf7.javamvprx.home.model.NewsApiResponse;
import com.github.mehrdadf7.javamvprx.home.model.repo.NewsRepositoryImpl;

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
                .subscribe(new Observer<NewsApiResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(NewsApiResponse newsApiResponse) {
                        view.showNews(newsApiResponse.getArticles());
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