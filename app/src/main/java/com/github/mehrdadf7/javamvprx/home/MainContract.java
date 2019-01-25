package com.github.mehrdadf7.javamvprx.home;

import com.github.mehrdadf7.javamvprx.base.BasePresenter;
import com.github.mehrdadf7.javamvprx.base.BaseView;
import com.github.mehrdadf7.javamvprx.home.model.NewsViewModel;

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
