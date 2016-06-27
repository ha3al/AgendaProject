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

public class RegisterActivity extends AppCompatActivity {

    Button register;
    EditText username, password, conf_password, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.username_register);
        password = (EditText) findViewById(R.id.password_register);
        conf_password = (EditText) findViewById(R.id.password2);
        email = (EditText) findViewById(R.id.email_register);
        register = (Button) findViewById(R.id.btn_create);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (password.getText().toString().equals(conf_password.getText().toString())) {
                    Database db = new Database(getApplicationContext());
                    User user = new User();
                    user.setUsername(username.getText().toString());
                    user.setPassword(password.getText().toString());
                    user.setEmail(email.getText().toString());

                    db.registerUser(user);

                    /**
                     * Shared Preferences burda devreye girecek
                     *
                     * */

                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("session", "YES");
                    editor.commit();

                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(RegisterActivity.this, "Şifreler eşleşmiyor!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
