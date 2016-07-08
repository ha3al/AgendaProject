package com.example.hazal.myagenda.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hazal.myagenda.DatabaseAndClasses.Database;
import com.example.hazal.myagenda.DatabaseAndClasses.Note;
import com.example.hazal.myagenda.R;

public class EditNoteActivity extends AppCompatActivity {

    EditText title,description,number,mail;
    Button edit;
    TextView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        title = (EditText) findViewById(R.id.editTitle);
        description = (EditText) findViewById(R.id.editDescription);
        number = (EditText) findViewById(R.id.editNumber);
        mail = (EditText) findViewById(R.id.editMail);
        edit = (Button) findViewById(R.id.btn_edit);
        back = (TextView) findViewById(R.id.back);

        String ID = "";
        ID = getIntent().getStringExtra("noteID");
        final Database database = new Database(getApplicationContext());
        final Note note = database.getSingleNoteDetailById(Integer.parseInt(ID));

        title.setText(getIntent().getStringExtra("title"));
        description.setText(getIntent().getStringExtra("description"));
        number.setText(getIntent().getStringExtra("number"));
        mail.setText(getIntent().getStringExtra("email"));

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                note.setTitle(title.getText().toString());
                note.setDescription(description.getText().toString());
                note.setContactNO(number.getText().toString());
                note.setEmail(mail.getText().toString());


                database.updateNote(note);

                Toast.makeText(EditNoteActivity.this, "Note Saved Successfully!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(EditNoteActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(EditNoteActivity.this);
                // Setting Dialog Title
                alertDialog.setTitle("Warning...");
                // Setting Dialog Message
                alertDialog.setMessage("Did you save changes?");
                // Setting Positive "Yes" Button
                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {

                        note.setTitle(title.getText().toString());
                        note.setDescription(description.getText().toString());
                        note.setContactNO(number.getText().toString());
                        note.setEmail(mail.getText().toString());

                        database.updateNote(note);
                        Toast.makeText(getApplicationContext(), "Note Saved Successfully!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(EditNoteActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                });
                // Setting Negative "NO" Button
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to invoke NO event
                        dialog.cancel();
                    }
                });
                // Showing Alert Message
                alertDialog.show();
            }
        });


    }
}
