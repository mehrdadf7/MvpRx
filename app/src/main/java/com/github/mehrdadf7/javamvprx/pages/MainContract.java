package com.github.mehrdadf7.javamvprx.pages;

import com.github.mehrdadf7.javamvprx.base.BasePresenter;
import com.github.mehrdadf7.javamvprx.base.BaseView;
import com.github.mehrdadf7.javamvprx.models.NewsViewModel;

import java.util.List;

public interface MainContract {
    interface View extends BaseView {
        void showNews(List<NewsViewModel> newsViewModels);
        void setProgressIndicator(boolean mustShow);
        void showError(String message);
    }
    interface Presenter extends BasePresenter<View> {
        void loadNews();
    }
}
