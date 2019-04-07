package com.example.sayed.myapplication.Ui.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sayed.myapplication.R;
import com.example.sayed.myapplication.Ui.Helper.HelperMethod;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {

   private Button skip;
   private LoginFragment fragment;
    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_info, container, false);

        skip=v.findViewById(R.id.btn_info);
        fragment=new LoginFragment();

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* FragmentManager fm= getFragmentManager();
                FragmentTransaction fragmentTransaction=fm.beginTransaction();
                fragmentTransaction.replace(R.id.container_frame,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/
                HelperMethod.FragmentManager(fragment,getFragmentManager(),R.id.container_frame,null,null);
            }
        });

        return v;
    }



}
