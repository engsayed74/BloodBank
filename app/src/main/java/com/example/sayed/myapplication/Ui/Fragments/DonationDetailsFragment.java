package com.example.sayed.myapplication.Ui.Fragments;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sayed.myapplication.R;
import com.example.sayed.myapplication.data.model.donationdetails.DonationDetails;
import com.example.sayed.myapplication.data.model.donationdetails.DonationDetailsData;
import com.example.sayed.myapplication.data.rest.ApiServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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
public class DonationDetailsFragment extends Fragment {


    @BindView(R.id.donationdetails_name)
    EditText donationdetailsName;
    @BindView(R.id.donationdetails_age)
    EditText donationdetailsAge;
    @BindView(R.id.donationdetails_bloodtype)
    EditText donationdetailsBloodtype;
    @BindView(R.id.donationdetails_bagsnumber)
    EditText donationdetailsBagsnumber;
    @BindView(R.id.donationdetails_hospital)
    EditText donationdetailsHospital;
    @BindView(R.id.donationdetails_hospitaladdress)
    EditText donationdetailsHospitaladdress;
    @BindView(R.id.donationrequest_phonenumber)
    EditText donationrequestPhonenumber;
    @BindView(R.id.donationdetails_details)
    EditText donationdetailsDetails;

    Unbinder unbinder;
    private GoogleMap googleMap;
    private ApiServices apiServices;
    MapView mMapView;
    String api_token = "Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27";
    private double latitude;
    private double longitude;

    public DonationDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_donation_details, container, false);

        unbinder = ButterKnife.bind(this, view);
        apiServices = getClient().create(ApiServices.class);
        mMapView = (MapView) view.findViewById(R.id.google_mapview);

        int key_id = getArguments().getInt("key_id");

        Call<DonationDetails> call = apiServices.getDonationDetails(api_token, key_id);
        call.enqueue(new Callback<DonationDetails>() {
            @Override
            public void onResponse(Call<DonationDetails> call, Response<DonationDetails> response) {
                Log.v("DonationDetails: ", response.raw()+"");
                if (response.body().getStatus() == 1) {
                    DonationDetailsData data = response.body().getData();

                    if(data.getLatitude()!=null&&data.getLongitude()!=null){
                        Toast.makeText(getContext(), "data not null", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(getContext(), "data null", Toast.LENGTH_LONG).show();
                    }
                    latitude = Double.parseDouble(data.getLatitude());
                    longitude = Double.parseDouble(data.getLongitude());
                    String notes = data.getNotes();
                    String patientName = data.getPatientName();
                    String patientAge = data.getPatientAge();
                    String bloodType = data.getBloodType();
                    String bagsNum = data.getBagsNum();
                    String hospitalName = data.getHospitalName();
                    String hospitalAddress = data.getHospitalAddress();
                    String phone = data.getPhone();

                    donationdetailsName.setText(patientName);
                    donationdetailsAge.setText(patientAge);
                    donationdetailsBloodtype.setText(bloodType);
                    donationdetailsBagsnumber.setText(bagsNum);
                    donationdetailsHospital.setText(hospitalName);
                    donationdetailsHospitaladdress.setText(hospitalAddress);
                    donationrequestPhonenumber.setText(phone);
                    donationdetailsDetails.setText(notes);
                }else {
                    Toast.makeText(getContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<DonationDetails> call, Throwable t) {

            }
        });

        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                googleMap.setMyLocationEnabled(true);

                // For dropping a marker at a point on the Map
                LatLng sydney = new LatLng(latitude, longitude);
                googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description"));

                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}