package com.example.sayed.myapplication.Ui.Fragments;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sayed.myapplication.R;
import com.example.sayed.myapplication.Ui.Activities.NavigationActivity;
import com.example.sayed.myapplication.data.model.cities.Cities;
import com.example.sayed.myapplication.data.model.cities.CitiesDatum;
import com.example.sayed.myapplication.data.model.governorates.Governorates;
import com.example.sayed.myapplication.data.model.governorates.GovernoratesDatum;
import com.example.sayed.myapplication.data.model.register.Register;
import com.example.sayed.myapplication.data.rest.ApiServices;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
public class CreataccountFragment extends Fragment {

    String name, email, bloodtype, password, confirmpassword, date, phonenumber, lastdate;

    @BindView(R.id.city_spinner)
    Spinner citySpinner;
    @BindView(R.id.btn_register)
    Button btnRegister;
    Unbinder unbinder;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.creataccount_name)
    EditText creataccountName;
    @BindView(R.id.creataccount_email)
    EditText creataccountEmail;
    @BindView(R.id.creataccount_bloodtype)
    EditText creataccountBloodtype;
    @BindView(R.id.govern_spinner)
    Spinner governSpinner;
    @BindView(R.id.creataccount_phonenumber)
    EditText creataccountPhonenumber;
    @BindView(R.id.creataccount_password)
    EditText creataccountPassword;
    @BindView(R.id.creataccount_confirmpassword)
    EditText creataccountConfirmpassword;
    @BindView(R.id.creataccount_birthdate)
    EditText creataccountBirthdate;
    @BindView(R.id.creataccount_lastdonationdate)
    EditText creataccountLastdonationdate;

    private ApiServices apiServices;

    private Integer CITYID;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;

    public CreataccountFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_creataccount, container, false);
        unbinder = ButterKnife.bind(this, v);


        apiServices = getClient().create(ApiServices.class);
        govern();
        datePicker();
        dateDonationPicker();


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userRegister();

                Call<Register> call = apiServices.getRegister(name, email, date, CITYID, phonenumber, lastdate, password, confirmpassword, bloodtype);
                call.enqueue(new Callback<Register>() {
                    @Override
                    public void onResponse(Call<Register> call, Response<Register> response) {


                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onFailure(Call<Register> call, Throwable t) {

                    }
                });


                Intent myintent = new Intent(getActivity(), NavigationActivity.class);
                getActivity().startActivity(myintent);


            }
        });


        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void userRegister() {
        name = creataccountName.getText().toString();
        email = creataccountEmail.getText().toString();
        bloodtype = creataccountBloodtype.getText().toString();
        password = creataccountPassword.getText().toString();
        phonenumber = creataccountPhonenumber.getText().toString();
        confirmpassword = creataccountConfirmpassword.getText().toString();

    }




    @RequiresApi(api = Build.VERSION_CODES.N)
    public void datePicker() {
        creataccountBirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                creataccountBirthdate.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void dateDonationPicker() {
        creataccountLastdonationdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                creataccountLastdonationdate.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
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
                    governSpinner.setAdapter(spinnerAdapter);
                    governSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                                        citySpinner.setAdapter(spinnerAdapter);
                                        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
}
