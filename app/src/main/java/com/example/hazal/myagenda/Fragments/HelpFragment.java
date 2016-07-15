package com.example.hazal.myagenda.Fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hazal.myagenda.Activities.LoginActivity;
import com.example.hazal.myagenda.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HelpFragment extends Fragment {
    TextView help;
    ImageView twitter;

    public HelpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_help, container, false);

        help = (TextView)view.findViewById(R.id.help);
        twitter = (ImageView) view.findViewById(R.id.twitter);

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/ha3alkaya"));
                startActivity(browserIntent);
            }
        });

        help.setText("This is an Agenda Application, you can add,edit,delete your notes, so remember easily!");
        return view;
    }
}
