package com.example.sayed.myapplication.Ui.Helper;

import android.support.v4.app.Fragment;
import   android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.widget.TextView;

import com.example.sayed.myapplication.R;

/**
 * Created by sayed on 16/01/2019.
 */

public class HelperMethod {


    public static void FragmentManager(Fragment fragment, FragmentManager supportFragmentManager, int Container
            , TextView textView, String title) {

        FragmentTransaction ft = supportFragmentManager.beginTransaction();
        ft.replace(Container, fragment);
        ft.commit();
        if (textView != null) {
            textView.setText(title);
        }
    }
}
