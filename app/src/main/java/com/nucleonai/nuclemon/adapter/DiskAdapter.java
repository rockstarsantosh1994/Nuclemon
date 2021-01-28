package com.nucleonai.nuclemon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.akexorcist.roundcornerprogressbar.TextRoundCornerProgressBar;
import com.nucleonai.nuclemon.R;
import com.nucleonai.nuclemon.pojo.DemoBO;
import com.nucleonai.nuclemon.pojo.dashboard.DriveUtilizationBO;

import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DiskAdapter extends RecyclerView.Adapter<DiskAdapter.DiskViewHolder>{

    private Context context;
    private ArrayList<DriveUtilizationBO> driveUtilizationBOArrayList;

    public DiskAdapter(Context context, ArrayList<DriveUtilizationBO> driveUtilizationBOArrayList) {
        this.context = context;
        this.driveUtilizationBOArrayList = driveUtilizationBOArrayList;
    }

    @NonNull
    @Override
    public DiskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.row_disk,parent,false);
        return new DiskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiskViewHolder holder, int position) {
        holder.tvDiskName.setText(driveUtilizationBOArrayList.get(position).getVolumeMountPoint());
        holder.textRoundCornerProgressBar.setProgress(driveUtilizationBOArrayList.get(position).getUtilizedsizeGB());
        holder.textRoundCornerProgressBar.setProgressText(String.valueOf(driveUtilizationBOArrayList.get(position).getUtilizedsizeGB()));
        //holder.textRoundCornerProgressBar.setProgressColor(ContextCompat.getColor(context,demoBOArrayList.get(position).getServerColor()));
    }

    @Override
    public int getItemCount() {
        return driveUtilizationBOArrayList.size();
    }

    public class DiskViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_disk_name)
        TextView tvDiskName;
        @BindView(R.id.textBoxProgressBar)
        TextRoundCornerProgressBar textRoundCornerProgressBar;

        public DiskViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
