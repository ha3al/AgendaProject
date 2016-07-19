package com.example.hazal.myagenda.Fragments;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.PathDashPathEffect;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hazal.myagenda.DatabaseAndClasses.Database;
import com.example.hazal.myagenda.DatabaseAndClasses.Note;
import com.example.hazal.myagenda.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddItemFragment extends Fragment {
    TextView back;
    EditText title, description,number,mail;
    Button add;


    public AddItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_item, container, false);

        back = (TextView) view.findViewById(R.id.back);
        title = (EditText) view.findViewById(R.id.add_title);
        description = (EditText) view.findViewById(R.id.description);
        number = (EditText) view.findViewById(R.id.number);
        mail = (EditText) view.findViewById(R.id.mail);
        add = (Button) view.findViewById(R.id.btn_add);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReturnMainFragment();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Database database = new Database(getContext());
                    Note note = new Note();
                    note.setTitle(title.getText().toString());
                    note.setDescription(description.getText().toString());
                    note.setContactNO(number.getText().toString());
                    note.setEmail(mail.getText().toString());
                    database.addNote(note);

                    Toast.makeText(getContext(), "İşlem başarıyla tamamlandı!", Toast.LENGTH_SHORT).show();
                    ReturnMainFragment();

                } catch (Exception e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public void ReturnMainFragment(){
        CardViewFragment fragment = new CardViewFragment();
        this.getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,fragment)
                .addToBackStack(null)
                .commit();
    }

}
