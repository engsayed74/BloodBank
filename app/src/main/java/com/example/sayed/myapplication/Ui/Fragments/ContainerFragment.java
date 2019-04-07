package com.example.sayed.myapplication.Ui.Fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sayed.myapplication.R;
import com.example.sayed.myapplication.Ui.Adapter.CustomAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContainerFragment extends Fragment {


    @BindView(R.id.container_tablayout)
    TabLayout containerTablayout;
    @BindView(R.id.container_viewpager)
    ViewPager containerViewpager;
    Unbinder unbinder;

    public ContainerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_container, container, false);
        unbinder = ButterKnife.bind(this, view);

        containerViewpager.setAdapter(new CustomAdapter(getActivity().getSupportFragmentManager()));
        containerTablayout.setupWithViewPager(containerViewpager);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
