package com.herba.sdk.jetpackexample.navigation.bottomnavfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.herba.sdk.jetpackexample.R;
import com.herba.sdk.jetpackexample.navigation.NavigationRecyclerAdapter;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView dashboardRecyclerView = view.findViewById(R.id.dashboardRecyclerView);
        dashboardRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dashboardRecyclerView.setAdapter(new NavigationRecyclerAdapter(getContext()));
    }
}
