package com.example.sayed.myapplication.Ui.Fragments;


import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import com.example.sayed.myapplication.Ui.Activities.MapsActivity;
import com.example.sayed.myapplication.Ui.Activities.NavigationActivity;
import com.example.sayed.myapplication.data.model.cities.Cities;
import com.example.sayed.myapplication.data.model.cities.CitiesDatum;
import com.example.sayed.myapplication.data.model.donationrequestcreate.DonationRequestCreate;
import com.example.sayed.myapplication.data.model.governorates.Governorates;
import com.example.sayed.myapplication.data.model.governorates.GovernoratesDatum;
import com.example.sayed.myapplication.data.rest.ApiServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static com.example.sayed.myapplication.data.rest.RetrofitClient.getClient;

/**
 * A simple {@link Fragment} subclass.
 */
public class DonationRequestFragment extends Fragment {


    Unbinder unbinder;
    @BindView(R.id.donationrequest_name)
    EditText donationrequestName;
    @BindView(R.id.donationrequest_age)
    EditText donationrequestAge;
    @BindView(R.id.donationrequest_bloodtypespinner)
    Spinner donationrequestBloodtypespinner;
    @BindView(R.id.donationrequest_number)
    EditText donationrequestNumber;
    @BindView(R.id.donationrequest_hospitalname)
    EditText donationrequestHospitalname;
    @BindView(R.id.donationrequestbtn_location)
    Button donationrequestbtnLocation;
    @BindView(R.id.donationrequest_governspinner)
    Spinner donationrequestGovernspinner;
    @BindView(R.id.donationrequestcity_spinner)
    Spinner donationrequestcitySpinner;
    @BindView(R.id.donationrequest_phonenumber)
    EditText donationrequestPhonenumber;
    @BindView(R.id.donationrequest_notes)
    EditText donationrequestNotes;
    @BindView(R.id.donationrequestbtn_sendreeuest)
    Button donationrequestbtnSendreeuest;
    @BindView(R.id.donationrequest_hospitaladdress)
    EditText donationrequestHospitaladdress;
    private ApiServices apiServices;
    private int CITYID;
    private double latitude;
    private double longitude;
    private static final String TAG = "tag";
    private String apitoken="Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27";


    public DonationRequestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_donation_request, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiServices = getClient().create(ApiServices.class);
        govern();





        donationrequestbtnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(getActivity(), MapsActivity.class);
                startActivityForResult(myintent,1);
            }

        });


donationrequestbtnSendreeuest.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Call<DonationRequestCreate> call=apiServices.getDonationRequestCreate(apitoken,donationrequestName.getText().toString(),
                                                     donationrequestAge.getText().toString(),
                                                     donationrequestBloodtypespinner.getSelectedItem().toString(),donationrequestNumber.getText().toString(),
                                                      donationrequestHospitalname.getText().toString(),
                                                      donationrequestHospitaladdress.getText().toString(),CITYID,
                                                      donationrequestPhonenumber.getText().toString(),donationrequestNotes.getText().toString(),
                                                      latitude,longitude);

        call.enqueue(new Callback<DonationRequestCreate>() {
            @Override
            public void onResponse(Call<DonationRequestCreate> call, Response<DonationRequestCreate> response) {
                Log.v("sayedss",response.raw() + "");


                    if (response.body().getStatus() == 1) {
                        Toast.makeText(getActivity(),response.body().getMsg(), Toast.LENGTH_SHORT).show();

                    }
                    else {
                        Toast.makeText(getActivity(), "no", Toast.LENGTH_SHORT).show();
                    }

            }

            @Override
            public void onFailure(Call<DonationRequestCreate> call, Throwable t) {

            }
        });
    }
});
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                 latitude=data.getDoubleExtra("lat",1);
                 longitude=data.getDoubleExtra("long",1);
                String address = getAddressFromCoordinates(getContext(), latitude, longitude);
                donationrequestHospitaladdress.setText(address);
            }
            if (resultCode == RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }

    public static String getAddressFromCoordinates(Context context, double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(context, Locale.ENGLISH);
        String address = null;
        List<Address> addressList;
        try {
            addressList = geocoder.getFromLocation(latitude, longitude, 1);
            address = addressList.get(0).getAddressLine(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return address;
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
                    donationrequestGovernspinner.setAdapter(spinnerAdapter);
                    donationrequestGovernspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                                        donationrequestcitySpinner.setAdapter(spinnerAdapter);
                                        donationrequestcitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
