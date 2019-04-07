package com.example.sayed.myapplication.Ui.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sayed.myapplication.R;
import com.example.sayed.myapplication.data.model.posts.PostsDatum;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sayed on 28/01/2019.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.MyViewHolder> {


    private Context context;
    private Activity activity;
    private List<PostsDatum> postdatum=new ArrayList<>();

    public ArticleAdapter(Context context, List<PostsDatum> postdatum) {
        this.context = context;
        this.postdatum = postdatum;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_content, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        setData(holder,i);

    }

    private void setData(MyViewHolder viewHolder,int position){
        PostsDatum posts=postdatum.get(position);
        viewHolder.articleTitle.setText(posts.getTitle());

        Glide.with(context).load(posts.getThumbnailFullPath()).into(viewHolder.articleCardimage);

    }

    @Override
    public int getItemCount() {

        return postdatum.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.article_btnheart)
        Button articleBtnheart;
        @BindView(R.id.article_title)
        TextView articleTitle;
        @BindView(R.id.article_cardimage)
        ImageView articleCardimage;

       // private View view;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
          //  view = itemView;

        }

    }


}
