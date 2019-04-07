package com.example.sayed.myapplication.Ui.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.support.v4.app.FragmentManager;
import com.example.sayed.myapplication.R;
import com.example.sayed.myapplication.Ui.Activities.NavigationActivity;
import com.example.sayed.myapplication.Ui.Fragments.DonationDetailsFragment;
import com.example.sayed.myapplication.Ui.Helper.HelperMethod;
import com.example.sayed.myapplication.data.model.donationrequests.DonationRequestsDatum;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sayed on 31/01/2019.
 */

public class DonationAdapter extends RecyclerView.Adapter<DonationAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private List<DonationRequestsDatum> mydata = new ArrayList<>();

    public DonationAdapter(Context context, Activity activity, List<DonationRequestsDatum> mydata) {
        this.context = context;
        this.activity = activity;
        this.mydata = mydata;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.donation_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        setDonationData(holder,position);

        //details button
        final DonationDetailsFragment fragment=new DonationDetailsFragment();
        holder.donationlayoutDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DonationRequestsDatum datum = mydata.get(position);
                int id=datum.getId();
                Bundle bundle= new Bundle();
                bundle.putInt("key_id",id);
                fragment.setArguments(bundle);
                HelperMethod.FragmentManager(fragment,((NavigationActivity)context).getSupportFragmentManager(),R.id.nav_frame,null,null);

            }
        });

    }

    private void setDonationData(MyViewHolder viewHolder, int position) {
        DonationRequestsDatum datum = mydata.get(position);
        viewHolder.donationBloodtype.setText(datum.getBloodType());
        viewHolder.donationCityname.setText(datum.getCity().getName());
        viewHolder.donationHospitalName.setText(datum.getHospitalName());
        viewHolder.donationPatientname.setText(datum.getPatientName());


    }

    @Override
    public int getItemCount() {
        return mydata.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.donation_bloodtype)
        TextView donationBloodtype;
        @BindView(R.id.donation_patientname)
        TextView donationPatientname;
        @BindView(R.id.donation_hospital_name)
        TextView donationHospitalName;
        @BindView(R.id.donation_cityname)
        TextView donationCityname;
        @BindView(R.id.donationlayout_details)
        Button donationlayoutDetails;

        public MyViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
