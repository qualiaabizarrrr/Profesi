package com.qualia.pengenalanprofesi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class About extends AppCompatActivity {

    private SessionManager sessionManager;
    private TextView tvUsername, tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        sessionManager = new SessionManager(this);
        tvUsername = findViewById(R.id.tvUsername);
        tvEmail = findViewById(R.id.tvEmail);

        String username = sessionManager.getUsername();
        String email = sessionManager.getEmail();

        tvUsername.setText(username);
        tvEmail.setText(email);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView nohp = findViewById(R.id.nohp);
        ImageView ig = findViewById(R.id.ig);
        ImageView wa = findViewById(R.id.wa);

        nohp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0895374901672"));
                startActivity(intent);
            }
        });

        ig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://instagram.com/lbizaraa");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        wa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nohp = "62895374901672";
                String url = "https://wa.me/" + nohp;
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.home) {
            getSupportActionBar().setTitle("Home");
            Intent loginIntent = new Intent(About.this, MainActivity.class);
            startActivity(loginIntent);
            return true;
        } else if (item.getItemId() == R.id.about) {
            getSupportActionBar().setTitle("About");
            Intent aboutIntent = new Intent(About.this, About.class);
            startActivity(aboutIntent);
            return true;
        } else if (item.getItemId() == R.id.logout) {
            getSupportActionBar().setTitle("Logout");
            Intent loginIntent = new Intent(About.this, HalamanUtama.class);
            startActivity(loginIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}