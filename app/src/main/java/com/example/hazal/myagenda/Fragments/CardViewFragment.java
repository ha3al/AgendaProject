package com.example.hazal.myagenda.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hazal.myagenda.DatabaseAndClasses.Database;
import com.example.hazal.myagenda.DatabaseAndClasses.Note;
import com.example.hazal.myagenda.DatabaseAndClasses.SimpleRecyclerAdapter;
import com.example.hazal.myagenda.R;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CardViewFragment extends Fragment {

    private RecyclerView recycler_view;

    public CardViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cardview_main, container, false);
        recycler_view = (RecyclerView)view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recycler_view.setLayoutManager(layoutManager);

        Database db = new Database(getContext());
        List<Note> noteList = db.getAllNote();
        SimpleRecyclerAdapter adapter_items = new SimpleRecyclerAdapter(noteList);

        recycler_view.setHasFixedSize(true);
        recycler_view.setAdapter(adapter_items);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            @Override
            public void onTouchEvent(RecyclerView recycler, MotionEvent event) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }

            @Override
            public boolean onInterceptTouchEvent(RecyclerView recycler, MotionEvent event) {
                return false;
            }
        });

        return view;
    }

}
