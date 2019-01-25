package com.github.mehrdadf7.javamvprx.base;

public interface BasePresenter<T extends BaseView> {
    void attachView(T view);
    void detachView();
}
