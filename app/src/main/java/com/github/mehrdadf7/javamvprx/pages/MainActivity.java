package com.github.mehrdadf7.javamvprx.pages;

import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.mehrdadf7.javamvprx.R;
import com.github.mehrdadf7.javamvprx.adapters.NewsAdapter;
import com.github.mehrdadf7.javamvprx.api.NewsRepositoryImpl;
import com.github.mehrdadf7.javamvprx.models.News;
import com.github.mehrdadf7.javamvprx.receivers.NetworkStateReceiver;
import com.github.mehrdadf7.util.App;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View, NetworkStateReceiver.OnNetworkStateChangeListener {

    private RecyclerView recyclerView;
    private MainContract.Presenter presenter;
    private View progressBar;
    private NetworkStateReceiver networkStateReceiver;
    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar  = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        networkStateReceiver = new NetworkStateReceiver();
        presenter = new MainPresenter(new NewsRepositoryImpl());
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        networkStateReceiver.setOnNetworkStateChangeListener(this);
        registerReceiver(networkStateReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    @Override
    protected void onPause() {
        networkStateReceiver.setOnNetworkStateChangeListener(null);
        unregisterReceiver(networkStateReceiver);
        super.onPause();
    }

    @Override
    protected void onStop() {
        presenter.detachView();
        super.onStop();
    }

    @Override
    public void showNews(List<News> newsList) {
        recyclerView.setAdapter(new NewsAdapter(newsList));
    }

    @Override
    public void setProgressIndicator(boolean mustShow) {
        if (mustShow) {
            App.visible(progressBar);
        } else {
            App.gone(progressBar);
        }
    }

    @Override
    public void showError(String message) {
        App.t(message, Typeface.DEFAULT,
                ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary),
                ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark)
        );
    }

    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    public void onChangeState(boolean isOnline) {
        if (!isOnline) {
            App.t("You are Offline", Typeface.DEFAULT,
                    ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary),
                    ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark)
                    );
            App.gone(progressBar);
        }
    }
}
