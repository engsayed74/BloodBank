package com.example.sayed.myapplication.Ui.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sayed.myapplication.R;
import com.example.sayed.myapplication.Ui.Activities.NavigationActivity;
import com.example.sayed.myapplication.Ui.Helper.HelperMethod;
import com.example.sayed.myapplication.data.model.login.Login;
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
public class LoginFragment extends Fragment {

    @BindView(R.id.login_phone)
    EditText loginPhone;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.login_password_reset)
    TextView loginPasswordReset;
    @BindView(R.id.login_btnenter)
    Button loginBtnenter;
    @BindView(R.id.login_btncreate)
    Button loginBtncreate;
    Unbinder unbinder;

    private Fragment fragment, fragment1;

    private HelperMethod helperMethod;
    private ApiServices apiServices;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        unbinder = ButterKnife.bind(this, v);
         helperMethod=new HelperMethod();
        apiServices = getClient().create(ApiServices.class);
        fragment = new ResetFragment();


        loginPasswordReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               helperMethod.FragmentManager(fragment,getFragmentManager(),R.id.container_frame,null,null);

            }
        });


        fragment1 = new CreataccountFragment();

        loginBtncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                helperMethod.FragmentManager(fragment1,getFragmentManager(),R.id.container_frame,null,null);

            }
        });

        loginBtnenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Call<Login> call= apiServices.getLogin(loginPhone.getText().toString(),loginPassword.getText().toString());
                call.enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {
                        if((response.body()).getStatus()==1) {

                            Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                        }

                        else {Toast.makeText(getActivity(),"status not equal 1",Toast.LENGTH_LONG).show();}
                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {

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
