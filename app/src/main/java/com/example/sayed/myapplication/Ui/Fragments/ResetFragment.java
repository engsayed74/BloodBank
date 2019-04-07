package com.example.sayed.myapplication.Ui.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sayed.myapplication.R;
import com.example.sayed.myapplication.Ui.Helper.HelperMethod;
import com.example.sayed.myapplication.data.model.resetpassword.ResetPassword;
import com.example.sayed.myapplication.data.rest.ApiServices;

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
public class ResetFragment extends Fragment {
    @BindView(R.id.reset_phone)
    EditText resetPhone;
    @BindView(R.id.reset_btnsend)
    Button resetBtnsend;
    Unbinder unbinder;

    private ChangepassFragment fragment;
    private HelperMethod helperMethod;
    private ApiServices apiServices;


    public ResetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_reset, container, false);

        unbinder = ButterKnife.bind(this, v);
        helperMethod=new HelperMethod();
        fragment = new ChangepassFragment();
        apiServices = getClient().create(ApiServices.class);


        resetBtnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Call<ResetPassword> call=apiServices.getResetPassword(resetPhone.getText().toString());
                call.enqueue(new Callback<ResetPassword>() {
                    @Override
                    public void onResponse(Call<ResetPassword> call, Response<ResetPassword> response) {

                        if((response.body()).getStatus()==1){

                          //  Toast.makeText(getActivity(),response.body().getMsg(),Toast.LENGTH_LONG).show();

                       }
                        else {Toast.makeText(getActivity(),"status not equal 1",Toast.LENGTH_LONG).show();}
                    }

                    @Override
                    public void onFailure(Call<ResetPassword> call, Throwable t) {

                    }
                });

                helperMethod.FragmentManager(fragment,getFragmentManager(),R.id.container_frame,null,null);


            }
        });



        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
