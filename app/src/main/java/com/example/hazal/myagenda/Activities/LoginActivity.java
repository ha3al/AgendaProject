package com.example.hazal.myagenda.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hazal.myagenda.DatabaseAndClasses.Database;
import com.example.hazal.myagenda.DatabaseAndClasses.User;
import com.example.hazal.myagenda.R;

public class LoginActivity extends AppCompatActivity {

    Button reg, login;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // This is for default Settings

        Database database = new Database(getApplicationContext());
        database.defaultSetting();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String Session = preferences.getString("session","N/A");
        String address = preferences.getString("address","N/A");
        if (Session.equals(String.valueOf("YES"))){

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("email",String.valueOf(address));
            startActivity(intent);
        }

        username = (EditText) findViewById(R.id.username_login);
        password = (EditText) findViewById(R.id.password_login);
        reg = (Button) findViewById(R.id.btn_register);
        login = (Button) findViewById(R.id.btn_login);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Database db = new Database(getApplicationContext());
                    User user = db.getUserDetail(username.getText().toString(), password.getText().toString());
                    if (user != null) {

                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("session","YES");
                        editor.putString("address",user.getEmail());
                        editor.commit();

                        Toast.makeText(LoginActivity.this, "Logged in successfully!!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("email",user.getEmail());
                        startActivity(intent);
                    }
                } catch (Exception e) {
                    Toast.makeText(LoginActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
