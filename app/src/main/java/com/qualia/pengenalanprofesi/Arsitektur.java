package com.qualia.pengenalanprofesi;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Arsitektur extends AppCompatActivity {

    private ImageView kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arsitektur);

        ImageView kembali = findViewById(R.id.kembali);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Arsitektur.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.home) {
            getSupportActionBar().setTitle("Home");
            Intent loginIntent = new Intent(Arsitektur.this, MainActivity.class);
            startActivity(loginIntent);
            return true;
        } else if (item.getItemId() == R.id.about) {
            getSupportActionBar().setTitle("About");
            Intent aboutIntent = new Intent(Arsitektur.this, About.class);
            startActivity(aboutIntent);
            return true;
        } else if (item.getItemId() == R.id.logout) {
            getSupportActionBar().setTitle("Logout");
            Intent loginIntent = new Intent(Arsitektur.this, HalamanUtama.class);
            startActivity(loginIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}