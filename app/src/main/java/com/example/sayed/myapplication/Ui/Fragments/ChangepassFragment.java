package com.example.sayed.myapplication.Ui.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sayed.myapplication.R;
import com.example.sayed.myapplication.Ui.Activities.NavigationActivity;
import com.example.sayed.myapplication.data.model.newpassword.NewPassword;
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
public class ChangepassFragment extends Fragment {


    @BindView(R.id.changpass_codeverify)
    EditText changpassCodeverify;
    @BindView(R.id.changpass_newpass)
    EditText changpassNewpass;
    @BindView(R.id.changpass_confirmnewpass)
    EditText changpassConfirmnewpass;
    @BindView(R.id.changpass_btnchangpass)
    Button changpassBtnchangpass;
    Unbinder unbinder;

    private ApiServices apiServices;

    public ChangepassFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_changepass, container, false);
        unbinder = ButterKnife.bind(this, v);

        apiServices = getClient().create(ApiServices.class);


        changpassBtnchangpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Call<NewPassword> call=apiServices.getNewPassword(changpassNewpass.getText().toString(),
                                                                  changpassConfirmnewpass.getText().toString(),
                                                                   changpassCodeverify.getText().toString());
                call.enqueue(new Callback<NewPassword>() {
                    @Override
                    public void onResponse(Call<NewPassword> call, Response<NewPassword> response) {

                        if((response.body()).getStatus()==1){

                            Toast.makeText(getActivity(),response.body().getMsg(),Toast.LENGTH_LONG).show();
                        }
                        else {Toast.makeText(getActivity(),"status not equal one",Toast.LENGTH_LONG).show();}
                    }

                    @Override
                    public void onFailure(Call<NewPassword> call, Throwable t) {

                    }
                });

                Intent myIntent=new Intent(getActivity(), NavigationActivity.class);
                getActivity().startActivity(myIntent);

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
