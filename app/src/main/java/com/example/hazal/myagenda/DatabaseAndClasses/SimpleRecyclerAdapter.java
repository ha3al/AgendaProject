package com.example.hazal.myagenda.DatabaseAndClasses;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hazal.myagenda.Activities.DetailActivity;
import com.example.hazal.myagenda.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ha3al on 6/25/16.
 */
public class SimpleRecyclerAdapter extends RecyclerView.Adapter<SimpleRecyclerAdapter.ViewHolder> {

    List<Note> list_item;

    public SimpleRecyclerAdapter(List<Note> list_item) {

        this.list_item = list_item;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout, parent, false);

        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(SimpleRecyclerAdapter.ViewHolder holder, int position) {

        Note note = list_item.get(position);

        holder.item_title.setText(note.getTitle());
        holder.item_description.setText(note.getDescription());
        holder.item_date.setText(note.getCreated_at());
        holder.item_img.setImageResource(R.drawable.note);


        /** ############################################################### */

        holder.note = note;

        /** ############################################################### */


    }

    @Override
    public int getItemCount() {
        return list_item.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView item_title;
        public TextView item_description;
        public TextView item_date;
        public ImageView item_img;
        public CardView card_view;

        /**
         * ####################
         */

        public Note note;

        /**
         * ####################
         */

        public ViewHolder(final View view) {
            super(view);

            card_view = (CardView) view.findViewById(R.id.card_view);
            item_title = (TextView) view.findViewById(R.id.item_title);
            item_description = (TextView) view.findViewById(R.id.item_desc);
            item_date = (TextView) view.findViewById(R.id.item_date);
            item_img = (ImageView) view.findViewById(R.id.item_img);

            Database db = new Database(view.getContext());
            Settings settings = db.getSettings();

            if (!String.valueOf(settings.getColorHex()).equalsIgnoreCase("")){
                card_view.setCardBackgroundColor(Color.parseColor(String.valueOf("#"+settings.getColorHex())));
            }

            if (!String.valueOf(settings.getColorFont()).equalsIgnoreCase("")) {
                item_title.setTextSize(Float.parseFloat(String.valueOf(settings.getColorFont())));
                item_description.setTextSize(Float.parseFloat(String.valueOf(settings.getColorFont())));
            }


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(view.getContext(), DetailActivity.class);
                    intent.putExtra("noteID", String.valueOf(note.getNoteID()));
                    view.getContext().startActivity(intent);

                }
            });


        }
    }
}
