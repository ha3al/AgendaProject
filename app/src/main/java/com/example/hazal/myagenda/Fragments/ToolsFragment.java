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
import com.example.hazal.myagenda.DatabaseAndClasses.Database;
import com.example.hazal.myagenda.DatabaseAndClasses.Settings;
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

                final Database db = new Database(getContext()); /// ++++
                final Settings _settings = db.getSettings(); /// this will bring the current settings

                switch(i){
                    case 0:
                        final CharSequence colors[] = new CharSequence[]  {"orange", "green", "blue"};

                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("Pick a color");
                        builder.setItems(colors, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // the user clicked on colors[which]
                                if(which == 0){
                                    dialog.dismiss();
                                    // this is for red and reds color hex #FFFF0000
                                    Settings settings = new Settings();
                                    settings.setColorHex("FFFFA500");
                                    settings.setColorFont(_settings.getColorFont());
                                    db.setSettings(settings);

                                    Toast.makeText(getContext(), "İşlem başarıyla tamamlandı!", Toast.LENGTH_SHORT).show();
                                }

                                if(which == 1){
                                    dialog.dismiss();
                                    // this is for green and greens color hex #FF00FF00
                                    Settings settings = new Settings();
                                    settings.setColorHex("FF2E8B57");
                                    settings.setColorFont(_settings.getColorFont());
                                    db.setSettings(settings);

                                    Toast.makeText(getContext(), "İşlem başarıyla tamamlandı!", Toast.LENGTH_SHORT).show();
                                }

                                if (which ==2){
                                    dialog.dismiss();
                                    // this is for blue and blues color hex #FF0000FF
                                    Settings settings = new Settings();
                                    settings.setColorHex("FF00CED1");
                                    settings.setColorFont(_settings.getColorFont());
                                    db.setSettings(settings);

                                    Toast.makeText(getContext(), "İşlem başarıyla tamamlandı!", Toast.LENGTH_SHORT).show();
                                }

                                Intent intent = new Intent(getContext(),MainActivity.class);
                                startActivity(intent);
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

                                if (which == 0){
                                    dialog.dismiss();
                                    // this is for small size and smalls font size 16
                                    Settings settings = new Settings();
                                    settings.setColorFont(16);
                                    settings.setColorHex(_settings.getColorHex());
                                    db.setSettings(settings);

                                    Toast.makeText(getContext(), "İşlem başarıyla tamamlandı!", Toast.LENGTH_SHORT).show();
                                }

                                if (which == 1){
                                    dialog.dismiss();
                                    // this is for medium size and mediums font size 20
                                    Settings settings = new Settings();
                                    settings.setColorFont(20);
                                    settings.setColorHex(_settings.getColorHex());
                                    db.setSettings(settings);

                                    Toast.makeText(getContext(), "İşlem başarıyla tamamlandı!", Toast.LENGTH_SHORT).show();
                                }

                                if (which == 2){
                                    dialog.dismiss();
                                    // this is for large size and larges font size 22
                                    Settings settings = new Settings();
                                    settings.setColorFont(22);
                                    settings.setColorHex(_settings.getColorHex());
                                    db.setSettings(settings);

                                    Toast.makeText(getContext(), "İşlem başarıyla tamamlandı!", Toast.LENGTH_SHORT).show();
                                }

                                Intent intent = new Intent(getContext(),MainActivity.class);
                                startActivity(intent);

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
