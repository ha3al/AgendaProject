package com.example.hazal.myagenda.DatabaseAndClasses;

import android.content.Intent;
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

        /**
         *  Bu kısım en önemli kısım
         *  Veriler Bind yaniyüklenirken
         *  alıp verileri bir note classına atıyoruz
         *  MVC yapısının güzelliği de sonra bu verileri
         *  Holder classında oluşturduğumuz note classına yapıştırıyoruz
         *  Bu sayede gelen veriler anlık holder a da geçmiş oluyoz :)
         * */

        /** ############################################################### */

        holder.note = note;

        /** ############################################################### */

        /*holder.item_title.setText(list_item.get(position).getTitle());
        holder.item_description.setText(list_item.get(position).getDescription());
        holder.item_date.setText(list_item.get(position).getCreated_at());
        holder.item_img.setImageResource(R.drawable.note);
        *///holder.item_img.setImageResource(list_item_note.get(position).getPhoto_id());

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
