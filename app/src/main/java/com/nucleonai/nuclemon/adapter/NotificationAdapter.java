package com.nucleonai.nuclemon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.nucleonai.nuclemon.R;
import com.nucleonai.nuclemon.pojo.DemoBO;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>{

    private Context context;
    private ArrayList<DemoBO> demoBOArrayList;

    public NotificationAdapter(Context context, ArrayList<DemoBO> demoBOArrayList) {
        this.context = context;
        this.demoBOArrayList = demoBOArrayList;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.row_notification,parent,false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        holder.tvNotificationName.setText(demoBOArrayList.get(position).getServerName());
        holder.tvTime.setText("Wed, Oct 27th, 04:00 PM");
    }

    @Override
    public int getItemCount() {
        return demoBOArrayList.size();
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_notification_name)
        TextView tvNotificationName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.btn_sendemail)
        AppCompatButton btnSendMail;
        @BindView(R.id.btn_sendsms)
        AppCompatButton btnSendSms;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
