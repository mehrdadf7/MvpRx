package com.github.mehrdadf7.javamvprx.adapters;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mehrdadf7.javamvprx.R;
import com.github.mehrdadf7.javamvprx.home.model.NewsViewModel;

import java.util.List;
import java.util.Random;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private List<NewsViewModel> list;

    public NewsAdapter(List<NewsViewModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_article_row, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.bind(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list == null? 0: list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView color, title, description;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            color       = itemView.findViewById(R.id.textViewColor);
            title       = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
        }

        public void bind(final NewsViewModel newsViewModel) {
            color      .setText(newsViewModel.getTitle().substring(0, 1));
            title      .setText(newsViewModel.getTitle());
            description.setText(newsViewModel.getDescription());

            Random random = new Random();
            color.getBackground().setColorFilter(
                    Color.rgb(
                            random.nextInt(255),
                            random.nextInt(255),
                            random.nextInt(255)
                    ), PorterDuff.Mode.SRC_ATOP
            );

        }

    }
}
