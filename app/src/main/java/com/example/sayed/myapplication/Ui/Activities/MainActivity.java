package com.example.sayed.myapplication.Ui.Activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sayed.myapplication.R;
import com.example.sayed.myapplication.Ui.Fragments.InfoFragment;
import com.example.sayed.myapplication.Ui.Helper.HelperMethod;

public class MainActivity extends AppCompatActivity {
    private InfoFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment=new InfoFragment();
        //loadfragment(fragment);
        HelperMethod.FragmentManager(fragment,getSupportFragmentManager(),R.id.container_frame,null,null);


    }
   /* public void loadfragment(InfoFragment fragment){
        FragmentManager fm= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fm.beginTransaction();
        fragmentTransaction.replace(R.id.container_frame, fragment);
        fragmentTransaction.commit();
    }*/
}
