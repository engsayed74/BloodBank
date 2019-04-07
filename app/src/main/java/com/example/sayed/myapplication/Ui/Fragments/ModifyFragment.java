package com.example.sayed.myapplication.Ui.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sayed.myapplication.R;
import com.example.sayed.myapplication.data.model.cities.Cities;
import com.example.sayed.myapplication.data.model.cities.CitiesDatum;
import com.example.sayed.myapplication.data.model.governorates.Governorates;
import com.example.sayed.myapplication.data.model.governorates.GovernoratesDatum;
import com.example.sayed.myapplication.data.model.profile.Profile;
import com.example.sayed.myapplication.data.model.profile.ProfileData;
import com.example.sayed.myapplication.data.model.profile.User;
import com.example.sayed.myapplication.data.rest.ApiServices;

import java.util.ArrayList;
import java.util.List;

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
public class ModifyFragment extends Fragment {


    @BindView(R.id.modify_name)
    EditText modifyName;
    @BindView(R.id.modify_email)
    EditText modifyEmail;
    @BindView(R.id.modify_birthdate)
    EditText modifyBirthdate;
    @BindView(R.id.modify_bloodtype)
    EditText modifyBloodtype;
    @BindView(R.id.modify_lastdonation)
    EditText modifyLastdonation;
    @BindView(R.id.modify_govern_spinner)
    Spinner modifyGovernSpinner;
    @BindView(R.id.modify_cityspinner)
    Spinner modifyCityspinner;
    @BindView(R.id.modify_phone)
    EditText modifyPhone;
    @BindView(R.id.modify_password)
    EditText modifyPassword;
    @BindView(R.id.modify_confirmpassword)
    EditText modifyConfirmpassword;
    @BindView(R.id.modify_btnmodify)
    Button modifyBtnmodify;
    Unbinder unbinder;
    private ApiServices apiServices;
    private Integer CITYID;
    User data=new User();
    private String api_token="mzj4aDhSta6ulLzA4igpTrNXSSNqeNpzYBOKQKCyBNaCf9EgtOh5Vu6sXFyf";


    public ModifyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_modify, container, false);

        unbinder = ButterKnife.bind(this, view);
        apiServices = getClient().create(ApiServices.class);
        govern();


       modifyBtnmodify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Profile> call=apiServices.getProfile(data.getName().toString(),data.getEmail().toString(),
                                                          data.getBirthDate().toString(),CITYID,data.getPhone().toString(),
                                                          data.getDonationLastDate().toString(),modifyPassword.getText().toString(),
                                                          modifyConfirmpassword.getText().toString(),
                                                             data.getBloodType().toString(),api_token);

                call.enqueue(new Callback<Profile>() {
                    @Override
                    public void onResponse(Call<Profile> call, Response<Profile> response) {
                        if (response.body().getStatus()==1){
                            Toast.makeText(getContext(), "modified", Toast.LENGTH_SHORT).show();

                        }


                    }

                    @Override
                    public void onFailure(Call<Profile> call, Throwable t) {

                    }
                });

            }
        });



        return view;
    }

    public void govern() {
        apiServices.getGovernorates().enqueue(new Callback<Governorates>() {
            @Override
            public void onResponse(Call<Governorates> call, Response<Governorates> response) {
                List<GovernoratesDatum> data = response.body().getData();
                ArrayList<String> governName = new ArrayList<>();
                final ArrayList<Long> governId = new ArrayList<>();
                governName.add("اختار المحافظة");
                governId.add((long) 0);
                for (int i = 0; i < data.size(); i++) {

                    governName.add(data.get(i).getName());
                    governId.add(data.get(i).getId());
                    //set spinner adapter
                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, governName);
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    modifyGovernSpinner.setAdapter(spinnerAdapter);
                    modifyGovernSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                            if (position != 0) {
                                //  governId.get(position);
                                getCityId(governId.get(position));
                            }

                        }

                        public void getCityId(Long Idgovernment) {
                            apiServices.getCities(Idgovernment).enqueue(new Callback<Cities>() {
                                @Override
                                public void onResponse(Call<Cities> call, Response<Cities> response) {

                                    List<CitiesDatum> data1 = response.body().getData();
                                    ArrayList<String> cityName = new ArrayList<>();
                                    final ArrayList<Integer> cityId = new ArrayList<>();
                                    for (int i = 0; i < data1.size(); i++) {
                                        String name = data1.get(i).getName();
                                        Integer id = data1.get(i).getId();
                                        cityName.add(name);
                                        cityId.add(id);
                                        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, cityName);
                                        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                        modifyCityspinner.setAdapter(spinnerAdapter);
                                        modifyCityspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                                                CITYID = cityId.get(position);

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> adapterView) {

                                            }
                                        });


                                    }
                                }

                                @Override
                                public void onFailure(Call<Cities> call, Throwable t) {

                                }
                            });
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<Governorates> call, Throwable t) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
