package com.example.sayed.myapplication.Ui.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.sayed.myapplication.Ui.Fragments.ArticleFragment;
import com.example.sayed.myapplication.Ui.Fragments.DonationFragment;

/**
 * Created by sayed on 15/01/2019.
 */

public class CustomAdapter extends FragmentPagerAdapter {
    public CustomAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){

                case 0:
                    return new ArticleFragment();
                case 1:
                    return new DonationFragment();
                    default:
                        return null;

        }


    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){

            case 0:
                return "مقالات";

            case 1:
                return "طلبات التبرع";
        }
        return super.getPageTitle(position);
    }
}
