package com.qualia.pengenalanprofesi;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Profile extends AppCompatActivity {

    EditText namaLengkap, tanggalLahir, email, username, password;
    String fullNameData, birthDateData, emailData, usernameData, passwordData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        namaLengkap = findViewById(R.id.namalengkap);
        tanggalLahir = findViewById(R.id.tanggal);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        Intent intent = getIntent();
        SessionManager sessionManager = new SessionManager(this);

        String fullNameData = sessionManager.getUserData("name");
        String birthDateData = sessionManager.getUserData("birthDate");
        String emailData = sessionManager.getUserData("email");
        String usernameData = sessionManager.getUserData("username");
        String passwordData = sessionManager.getUserData("password");

        if (fullNameData != null) {
            namaLengkap.setText(fullNameData);
        }
        if (birthDateData != null) {
            tanggalLahir.setText(birthDateData);
        }
        if (emailData != null) {
            email.setText(emailData);
        }
        if (usernameData != null) {
            username.setText(usernameData);
        }
        if (passwordData != null) {
            password.setText(passwordData);
        }

        tanggalLahir.setFocusable(false);
        tanggalLahir.setFocusableInTouchMode(false);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.home) {
            getSupportActionBar().setTitle("Home");
            Intent loginIntent = new Intent(Profile.this, MainActivity.class);
            startActivity(loginIntent);
            return true;
        } else if (item.getItemId() == R.id.about) {
            getSupportActionBar().setTitle("About");
            Intent aboutIntent = new Intent(Profile.this, About.class);
            startActivity(aboutIntent);
            return true;
        } else if (item.getItemId() == R.id.login) {
            getSupportActionBar().setTitle("Login");
            Intent loginIntent = new Intent(Profile.this, HalamanUtama.class);
            startActivity(loginIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}