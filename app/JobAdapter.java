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
    private OnJobClickListener listener;

    public JobAdapter(List<JobData> jobList, OnJobClickListener listener) {
        this.jobList = jobList;
        this.listener = listener;
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
        JobData JobData = jobList.get(position);
        holder.jobTitle.setText(JobData.getTitle());
        holder.jobDescription.setText(JobData.getDescription());
        holder.jobImage.setImageResource(JobData.getImageResourceId());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onJobClick(JobData);
            }
        });
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    public interface OnJobClickListener {
        void onJobClick(JobData job);
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
    public void updateList(List<JobData> newList) {
        jobList = newList;
        notifyDataSetChanged();
    }
}