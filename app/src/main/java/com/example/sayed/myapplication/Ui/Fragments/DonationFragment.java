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
import com.example.sayed.myapplication.Ui.Adapter.DonationAdapter;
import com.example.sayed.myapplication.Ui.Helper.OnEndless;
import com.example.sayed.myapplication.data.model.donationrequests.DonationRequests;
import com.example.sayed.myapplication.data.model.donationrequests.DonationRequestsDatum;
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
public class DonationFragment extends Fragment {


    @BindView(R.id.donation_recycler)
    RecyclerView donationRecycler;
    Unbinder unbinder;
    ArrayList<DonationRequestsDatum> donationdatum;
    private DonationAdapter donationAdapter;
    private int max=5;
    private ApiServices apiServices;
    private String api_token="Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27";

    public DonationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_donation, container, false);

        unbinder = ButterKnife.bind(this, view);
        apiServices = getClient().create(ApiServices.class);


        final LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        donationRecycler.setLayoutManager(manager);
        donationdatum=new ArrayList<>();
        donationAdapter=new DonationAdapter(getContext(),getActivity(),donationdatum);


        OnEndless onEndless=new OnEndless(manager) {
            @Override
            public void onLoadMore(int current_page) {
                if(current_page<=max){
                    getDonation(current_page);
                }

            }
        };

        donationRecycler.addOnScrollListener(onEndless);
        donationRecycler.setAdapter(donationAdapter);
        getDonation(1);

        return view;
    }

    private void getDonation(int page) {
        apiServices.getDonationRequests(api_token).enqueue(new Callback<DonationRequests>() {
            @Override
            public void onResponse(Call<DonationRequests> call, Response<DonationRequests> response) {
                Log.i("sayedd",response.body()+"");

                try {
                    DonationRequests donationRequests=response.body();
                    if(response.body().getStatus()==1){

                        donationdatum.addAll(donationRequests.getData().getData());
                        donationAdapter.notifyDataSetChanged();
                    }else {
                        Toast.makeText(getContext(),donationRequests.getMsg(),Toast.LENGTH_LONG).show();
                    }

                }catch (Exception e){
                }
            }

            @Override
            public void onFailure(Call<DonationRequests> call, Throwable t) {

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
