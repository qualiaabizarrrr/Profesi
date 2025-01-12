package com.qualia.pengenalanprofesi;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageView profile;
    private TextView tvUsername;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profile = findViewById(R.id.profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvUsername = findViewById(R.id.tvUsername);
        sessionManager = new SessionManager(this);

        String username = sessionManager.getUsername();
        if (username != null && !username.isEmpty()) {
            tvUsername.setText(username);
        } else {
            tvUsername.setText("Guest");
        }

        profile.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, Profile.class));
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<JobData> jobList = new ArrayList<>();
        jobList.add(new JobData("Dokter", "Dokter adalah seorang profesional di bidang kesehatan yang bertugas mendiagnosis, merawat, dan memberikan pengobatan kepada pasien untuk menjaga atau memulihkan kesehatan mereka.", R.drawable.dokter));
        jobList.add(new JobData("Guru", "Profesi ini memegang peran penting dalam membentuk generasi penerus dengan memberikan pendidikan yang bermakna dan relevan.", R.drawable.guru));
        jobList.add(new JobData("Polisi", "Polisi menjaga keamanan dan ketertiban masyarakat serta menegakkan hukum. Mereka juga melindungi warga dari ancaman dan kejahatan.", R.drawable.polisi));
        jobList.add(new JobData("Pengacara", "Pengacara memberikan nasihat hukum dan mewakili klien dalam kasus di pengadilan. Mereka bertugas menegakkan keadilan berdasarkan hukum yang berlaku.", R.drawable.pengacara));
        jobList.add(new JobData("Engineer", "Insinyur atau engineer merancang, mengembangkan, dan memelihara sistem atau produk dalam berbagai bidang seperti konstruksi, teknologi, dan energi. Mereka mengaplikasikan ilmu dan kreativitas untuk memecahkan masalah teknis.", R.drawable.engineer));
        jobList.add(new JobData("Jurnalis", "Jurnalis melaporkan berita dan informasi untuk disampaikan kepada publik melalui media cetak, televisi, atau online. Mereka bertugas menggali fakta dan menyampaikan informasi secara objektif.", R.drawable.jurnalis));
        jobList.add(new JobData("Chef", "Chef menciptakan dan menyiapkan hidangan dengan cita rasa yang berkualitas di restoran atau tempat makan lainnya. Mereka sering bereksperimen dengan bahan dan resep untuk menghasilkan menu yang menarik.", R.drawable.chef));
        jobList.add(new JobData("Arsitektur", "Arsitek merancang bangunan dan ruang dengan mempertimbangkan fungsi, estetika, dan keamanan. Mereka bekerja untuk menciptakan lingkungan yang nyaman dan sesuai dengan kebutuhan pengguna.", R.drawable.arsitek));
        jobList.add(new JobData("Programmer", "Programmer adalah profesional yang menulis dan mengembangkan kode untuk membuat aplikasi atau sistem komputer. Mereka menggunakan logika dan kreativitas untuk memecahkan masalah teknologi.", R.drawable.programmer));
        jobList.add(new JobData("Perawat", "Perawat adalah tenaga kesehatan yang merawat pasien dengan memberikan perhatian medis dan emosional. Mereka bekerja sama dengan dokter untuk memastikan pasien mendapatkan perawatan yang optimal.", R.drawable.perawat));
        JobAdapter adapter = new JobAdapter(this, jobList);
        recyclerView.setAdapter(adapter);

        EditText searchBox = findViewById(R.id.cari);
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString(), adapter, jobList);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
    private void filter(String text, JobAdapter adapter, ArrayList<JobData> jobList) {
        ArrayList<JobData> filteredList = new ArrayList<>();
        for (JobData job : jobList) {
            if (job.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(job);
            }
        }
        adapter.updateList(filteredList);
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
            Intent loginIntent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(loginIntent);
            return true;
        } else if (item.getItemId() == R.id.about) {
            getSupportActionBar().setTitle("About");
            Intent aboutIntent = new Intent(MainActivity.this, About.class);
            startActivity(aboutIntent);
            return true;
        } else if (item.getItemId() == R.id.logout) {
            getSupportActionBar().setTitle("Logout");
            Intent loginIntent = new Intent(MainActivity.this, HalamanUtama.class);
            startActivity(loginIntent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}