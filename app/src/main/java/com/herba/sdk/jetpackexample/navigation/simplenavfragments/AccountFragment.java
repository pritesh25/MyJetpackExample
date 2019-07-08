package com.herba.sdk.jetpackexample.navigation.simplenavfragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.herba.sdk.jetpackexample.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    public static String DATA = "data";
    private EditText nameEditText;
    private Button button;

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nameEditText = view.findViewById(R.id.nameEditText);
        button = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nameEditText.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString(DATA,nameEditText.getText().toString());
                Navigation.findNavController(view).navigate(R.id.nameFragment,bundle);
                //switch to another name
            }
        });

    }
}



















/*
Dear Sir,
            I write to inform you that I am resigning from my position here as Android Developer. My last day will be January 31.
Thank you so much for all of the opportunities this company has provided me. I have learned so much these past three years, and will never forget the kindness of all of my colleagues.
Let me know if there is anything I can do to make this transition easier. You can always contact me at priteshvishwakarma@email.com .
Thank you again for your years of support and encouragement.
Respectfully yours,
*/