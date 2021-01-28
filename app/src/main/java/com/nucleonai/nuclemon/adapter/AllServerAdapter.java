package com.nucleonai.nuclemon.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nucleonai.nuclemon.R;
import com.nucleonai.nuclemon.activity.ServerChartActivity;
import com.nucleonai.nuclemon.pojo.allserver.AllServerBO;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllServerAdapter extends RecyclerView.Adapter<AllServerAdapter.AllServerViewHolder>{

    private Context context;
    private ArrayList<AllServerBO> serverBOArrayList;

    public AllServerAdapter(Context context, ArrayList<AllServerBO> serverBOArrayList) {
        this.context = context;
        this.serverBOArrayList = serverBOArrayList;
    }

    @NonNull
    @Override
    public AllServerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.row_all_servers,parent,false);
        return new AllServerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllServerViewHolder holder, int position) {
        holder.tvServerName.setText(serverBOArrayList.get(position).getServerNameIp());
        holder.tvPortNumber.setText(serverBOArrayList.get(position).getPort());
        holder.tvInstanceName.setText(serverBOArrayList.get(position).getInstName());
        holder.tvAuthentication.setText(serverBOArrayList.get(position).getAuth());
        holder.tvUserName.setText(serverBOArrayList.get(position).getUserName());
        holder.tvLastConnectionTime.setText(serverBOArrayList.get(position).getLastConnTime());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(serverBOArrayList.get(position).getServerId()!=null){
                    Intent intent=new Intent(context, ServerChartActivity.class);
                    intent.putExtra("serverid",serverBOArrayList.get(position).getServerId());
                    context.startActivity(intent);
                }else{
                    Toast.makeText(context, "Please try later!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return serverBOArrayList.size();
    }

    public class AllServerViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.cardview)
        CardView cardView;
        @BindView(R.id.tv_server_name)
        TextView tvServerName;
        @BindView(R.id.tv_port_number)
        TextView tvPortNumber;
        @BindView(R.id.tv_instance_name)
        TextView tvInstanceName;
        @BindView(R.id.tv_authentication)
        TextView tvAuthentication;
        @BindView(R.id.tv_username)
        TextView tvUserName;
        @BindView(R.id.tv_last_connection_time)
        TextView tvLastConnectionTime;

        public AllServerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
