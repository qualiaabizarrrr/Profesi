package com.qualia.pengenalanprofesi;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Register_activity extends AppCompatActivity {

    EditText nama, username, password, tanggal, email;
    Button registerBtn, loginBtn;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nama = findViewById(R.id.namalengkap);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        tanggal = findViewById(R.id.tanggal);
        registerBtn = findViewById(R.id.register);
        loginBtn = findViewById(R.id.login);
        email = findViewById(R.id.email);

        sessionManager = new SessionManager(this);

        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTanggal(v);
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nama.getText().toString();
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String Email = email.getText().toString();
                String birthDate = tanggal.getText().toString();

                if (TextUtils.isEmpty(user)) {
                    Toast.makeText(Register_activity.this, "Username tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(Register_activity.this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Email)) {
                    Toast.makeText(Register_activity.this, "Email tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(Register_activity.this, "Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (pass.length() < 6) {
                    Toast.makeText(Register_activity.this, "Password harus lebih dari 6 karakter", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(birthDate)) {
                    Toast.makeText(Register_activity.this, "Tanggal lahir tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    return;
                }

                sessionManager.saveUser(user, pass, name, birthDate, Email);
                Toast.makeText(Register_activity.this, "Registrasi Berhasil!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register_activity.this, Login_activity.class));
            }
        });
    }

    public void setTanggal(View v) {
        final Calendar c = Calendar.getInstance();
        int thn = c.get(Calendar.YEAR);
        int bln = c.get(Calendar.MONTH);
        int tgl = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(Register_activity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                tanggal.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
            }
        }, thn, bln, tgl);
        datePickerDialog.show();
    }
}