package com.herba.sdk.jetpackexample.navigation.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.herba.sdk.jetpackexample.R;

import static com.herba.sdk.jetpackexample.navigation.fragments.AccountFragment.DATA;


/**
 * A simple {@link Fragment} subclass.
 */
public class NameFragment extends Fragment {

    private final String TAG = this.getClass().getSimpleName();
    private TextView texview;
    private String name;


    public NameFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null)
        {
            name = getArguments().getString(DATA);
            Log.d(TAG,"name = "+name);
        }
        else
        {
            Log.d(TAG,"bundle is null");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_name, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        texview = view.findViewById(R.id.texview);
        texview.setText(name);
    }
}