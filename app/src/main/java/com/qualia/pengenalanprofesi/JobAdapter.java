package com.qualia.pengenalanprofesi;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> {

    private List<JobData> jobList;
    private Context context;

    public JobAdapter(Context context, List<JobData> jobList) {
        this.context = context;
        this.jobList = jobList;
    }
    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_job, parent, false);
        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
        JobData jobData = jobList.get(position);

        holder.jobTitle.setText(jobData.getTitle());
        holder.jobDescription.setText(jobData.getDescription());
        holder.jobImage.setImageResource(jobData.getImageResourceId());

        holder.itemView.setOnClickListener(v -> {
            Log.d("JobAdapter", "Item clicked: " + jobData.getTitle());
            Intent intent = null;
            switch (jobData.getTitle()) {
                case "Dokter":
                    intent = new Intent(context, Dokter.class);
                    break;
                case "Guru":
                    intent = new Intent(context, Guru.class);
                    break;
                case "Polisi":
                    intent = new Intent(context, Polisi.class);
                    break;
                case "Pengacara":
                    intent = new Intent(context, Pengacara.class);
                    break;
                case "Engineer":
                    intent = new Intent(context, Engineer.class);
                    break;
                case "Jurnalis":
                    intent = new Intent(context, Jurnalis.class);
                    break;
                case "Chef":
                    intent = new Intent(context, Chef.class);
                    break;
                case "Arsitektur":
                    intent = new Intent(context, Arsitektur.class);
                    break;
                case "Programmer":
                    intent = new Intent(context, Programmer.class);
                    break;
                case "Perawat":
                    intent = new Intent(context, Perawat.class);
                    break;
            }
            if (intent != null) {
                context.startActivity(intent);
            }
        });
    }
    public void updateList(List<JobData> filteredList) {
        jobList = filteredList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    static class JobViewHolder extends RecyclerView.ViewHolder {
        TextView jobTitle, jobDescription;
        ImageView jobImage;

        public JobViewHolder(@NonNull View itemView) {
            super(itemView);
            jobTitle = itemView.findViewById(R.id.jobTitle);
            jobDescription = itemView.findViewById(R.id.jobDescription);
            jobImage = itemView.findViewById(R.id.jobImage);
        }
    }
}