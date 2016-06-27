package com.example.hazal.myagenda.Fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.hazal.myagenda.Activities.LoginActivity;
import com.example.hazal.myagenda.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HelpFragment extends Fragment {
Button cikis;

    public HelpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_help, container, false);
        cikis = (Button) view.findViewById(R.id.btn_cikis);
        cikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("session");
                editor.commit();
                Toast.makeText(getContext(), "Çıkış başarılı!!", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });


        return view;
    }

}
