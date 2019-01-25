package com.github.mehrdadf7.javamvprx.pages;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.github.mehrdadf7.javamvprx.R;
import com.github.mehrdadf7.javamvprx.adapters.NewsAdapter;
import com.github.mehrdadf7.javamvprx.models.News;
import com.github.mehrdadf7.javamvprx.api.NewsRepositoryImpl;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private RecyclerView recyclerView;
    private MainContract.Presenter presenter;
    private View progressBar;
    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar  = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        presenter = new MainPresenter(new NewsRepositoryImpl());

    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.detachView();
    }

    @Override
    public void showNews(List<News> newsViewModels) {
        recyclerView.setAdapter(new NewsAdapter(newsViewModels));
        Log.e(TAG, "showNews: " + newsViewModels.size());
    }

    @Override
    public void setProgressIndicator(boolean mustShow) {
        if (mustShow) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getViewContext() {
        return this;
    }

}
