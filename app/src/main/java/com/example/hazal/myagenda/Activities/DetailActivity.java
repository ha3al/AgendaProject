package com.example.hazal.myagenda.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hazal.myagenda.DatabaseAndClasses.Database;
import com.example.hazal.myagenda.DatabaseAndClasses.Note;
import com.example.hazal.myagenda.R;

public class DetailActivity extends AppCompatActivity {
    String id = "";

    TextView date,title,desc,contact,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        id = getIntent().getStringExtra("noteID");

        date = (TextView) findViewById(R.id.textView_detail_date);
        title = (TextView) findViewById(R.id.textView_detail_title);
        desc = (TextView) findViewById(R.id.textView_detail_description);
        contact = (TextView) findViewById(R.id.textView_detail_contact);
        email = (TextView) findViewById(R.id.textView_detail_email);


        Database db = new Database(getApplicationContext());
        Note note = db.getSingleNoteDetailById(Integer.parseInt(id));
        date.setText(note.getCreated_at());
        title.setText(note.getTitle());
        desc.setText(note.getDescription());
        contact.setText(note.getContactNO());
        email.setText(note.getEmail());


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.edit);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailActivity.this, id, Toast.LENGTH_SHORT).show();
            }
        });
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.trash);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailActivity.this, id, Toast.LENGTH_SHORT).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
