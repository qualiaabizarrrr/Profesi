package com.qualia.pengenalanprofesi;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login_activity extends AppCompatActivity {

    EditText username, password;
    Button login, register;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        sessionManager = new SessionManager(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (TextUtils.isEmpty(user)) {
                    Toast.makeText(Login_activity.this, "Username tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(Login_activity.this, "Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (sessionManager.checkUser(user, pass)) {
                    Toast.makeText(Login_activity.this, "Login Berhasil!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login_activity.this, MainActivity.class));
                } else {
                    Toast.makeText(Login_activity.this, "Username atau Password salah!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_activity.this, Register_activity.class));
            }
        });
    }
}