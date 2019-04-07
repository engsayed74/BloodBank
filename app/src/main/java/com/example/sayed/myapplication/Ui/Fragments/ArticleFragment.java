package com.example.sayed.myapplication.Ui.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sayed.myapplication.R;
import com.example.sayed.myapplication.Ui.Adapter.ArticleAdapter;
import com.example.sayed.myapplication.Ui.Helper.OnEndless;
import com.example.sayed.myapplication.data.model.posts.Posts;
import com.example.sayed.myapplication.data.model.posts.PostsDatum;
import com.example.sayed.myapplication.data.rest.ApiServices;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sayed.myapplication.data.rest.RetrofitClient.getClient;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleFragment extends Fragment {


    @BindView(R.id.article_recycler)
    RecyclerView articleRecycler;
   private ArticleAdapter articleAdapter;
   ArrayList<PostsDatum> postsDatum;
   private ApiServices apiServices;
   private int max=3;
   final String api_token="Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27";

    Unbinder unbinder;

    public ArticleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_article, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = getClient().create(ApiServices.class);

        final LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        articleRecycler.setLayoutManager(manager);
        postsDatum=new ArrayList<>();
        articleAdapter=new ArticleAdapter(getActivity(),postsDatum);

        OnEndless onEndless=new OnEndless(manager) {
            @Override
            public void onLoadMore(int current_page) {
                if(current_page<=max){
                    getPosts(current_page);
                }

            }
        };

        articleRecycler.addOnScrollListener(onEndless);
        articleRecycler.setAdapter(articleAdapter);
        getPosts(1);


        return view;
    }

    private void getPosts(int page) {
        apiServices.getPosts(api_token,page).enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {
                Log.i("sayedaa",response.body()+ "");
                try {

                    Posts posts = response.body();

                    if (response.body().getStatus() == 1) {
                        postsDatum.addAll(posts.getData().getData());
                        articleAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getContext(), posts.getMsg(), Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
