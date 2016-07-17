package com.example.hazal.myagenda.Fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.hazal.myagenda.Activities.DetailActivity;
import com.example.hazal.myagenda.Activities.MainActivity;
import com.example.hazal.myagenda.DatabaseAndClasses.CustomListAdapter;
import com.example.hazal.myagenda.R;

import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 */
public class ToolsFragment extends Fragment {

    public ToolsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tools, container,
                false);

        String[] values = new String[] { "background color", "font size"};
        Integer[] imgid={R.drawable.color,R.drawable.font};
        ListView lv = (ListView)rootView.findViewById(R.id.tool);
        //cardView = (CardView)rootView.findViewById(R.id.card_view);

        CustomListAdapter adapter=new CustomListAdapter(getActivity(), values, imgid);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch(i){
                    case 0:
                        final CharSequence colors[] = new CharSequence[]  {"red", "green", "blue"};

                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("Pick a color");
                        builder.setItems(colors, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // the user clicked on colors[which]
                                if(which == 0){
                                    dialog.dismiss();
                                   // cardView.setCardBackgroundColor(Color.RED);
                                    Toast.makeText(getContext(), "İşlem başarıyla tamamlandı!", Toast.LENGTH_SHORT).show();
                                }

                                else if(which == 1){
                                    dialog.dismiss();
                                    Toast.makeText(getContext(), "İşlem başarıyla tamamlandı!", Toast.LENGTH_SHORT).show();
                                }

                                else{
                                    dialog.dismiss();
                                    Toast.makeText(getContext(), "İşlem başarıyla tamamlandı!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        builder.show();
                        break;
                    case 1:
                        final CharSequence fonts[] = new CharSequence[]  {"small", "medium", "large"};

                        AlertDialog.Builder build = new AlertDialog.Builder(getActivity());
                        build.setTitle("select font size");
                        build.setItems(fonts, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // the user clicked on colors[which]

                            }
                        });
                        build.show();
                        break;
                }
            }
        });

        return rootView;
    }

}
