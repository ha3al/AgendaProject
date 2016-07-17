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

        final String titleExtra = getIntent().getStringExtra("title");
        final String descriptionExtra = getIntent().getStringExtra("description");
        final String numberExtra = getIntent().getStringExtra("number");
        final String emailExtra = getIntent().getStringExtra("email");


        title.setText(titleExtra);
        description.setText(descriptionExtra);
        number.setText(numberExtra);
        mail.setText(emailExtra);

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

                if (String.valueOf(title.getText().toString()).equals(String.valueOf(titleExtra)) && String.valueOf(description.getText().toString()).equals(String.valueOf(descriptionExtra)) && String.valueOf(number.getText().toString()).equals(String.valueOf(numberExtra)) && String.valueOf(mail.getText().toString()).equals(String.valueOf(emailExtra))) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else {

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(EditNoteActivity.this);
                    // Setting Dialog Title
                    alertDialog.setTitle("Warning...");
                    // Setting Dialog Message
                    alertDialog.setMessage("Did you save changes?");
                    // Setting Positive "Yes" Button
                    alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            note.setTitle(EditNoteActivity.this.title.getText().toString());
                            note.setDescription(EditNoteActivity.this.description.getText().toString());
                            note.setContactNO(EditNoteActivity.this.number.getText().toString());
                            note.setEmail(mail.getText().toString());

                            database.updateNote(note);
                            Toast.makeText(getApplicationContext(), "Note Saved Successfully!", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(EditNoteActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });
                    alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                    });
                    alertDialog.show();
                }
            }
        });
    }
    @Override
    public void onBackPressed() {

        String ID = "";
        ID = getIntent().getStringExtra("noteID");
        final Database database = new Database(getApplicationContext());
        final Note note = database.getSingleNoteDetailById(Integer.parseInt(ID));

        final String titleExtra = getIntent().getStringExtra("title");
        final String descriptionExtra = getIntent().getStringExtra("description");
        final String numberExtra = getIntent().getStringExtra("number");
        final String emailExtra = getIntent().getStringExtra("email");

        if (String.valueOf(title.getText().toString()).equals(String.valueOf(titleExtra)) && String.valueOf(description.getText().toString()).equals(String.valueOf(descriptionExtra)) && String.valueOf(number.getText().toString()).equals(String.valueOf(numberExtra)) && String.valueOf(mail.getText().toString()).equals(String.valueOf(emailExtra))) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        } else {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(EditNoteActivity.this);
            // Setting Dialog Title
            alertDialog.setTitle("Warning...");
            // Setting Dialog Message
            alertDialog.setMessage("Did you save changes?");
            // Setting Positive "Yes" Button
            alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    note.setTitle(EditNoteActivity.this.title.getText().toString());
                    note.setDescription(EditNoteActivity.this.description.getText().toString());
                    note.setContactNO(EditNoteActivity.this.number.getText().toString());
                    note.setEmail(mail.getText().toString());

                    database.updateNote(note);
                    Toast.makeText(getApplicationContext(), "Note Saved Successfully!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(EditNoteActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
            alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            });
            alertDialog.show();
        }

    }
}