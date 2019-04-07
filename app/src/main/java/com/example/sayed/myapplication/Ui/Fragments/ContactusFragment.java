package com.example.sayed.myapplication.Ui.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.sayed.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactusFragment extends Fragment {


    @BindView(R.id.contactus_phone)
    EditText contactusPhone;
    @BindView(R.id.contactus_email)
    EditText contactusEmail;
    @BindView(R.id.contactus_name)
    EditText contactusName;
    @BindView(R.id.contactus_postal)
    EditText contactusPostal;
    @BindView(R.id.contactus_messageaddres)
    EditText contactusMessageaddres;
    @BindView(R.id.contactus_messagcontent)
    EditText contactusMessagcontent;
    @BindView(R.id.contactus_btnsend)
    Button contactusBtnsend;
    Unbinder unbinder;

    public ContactusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contactus, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
