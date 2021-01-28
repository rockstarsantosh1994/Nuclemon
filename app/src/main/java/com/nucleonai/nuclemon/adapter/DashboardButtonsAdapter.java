package com.nucleonai.nuclemon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.nucleonai.nuclemon.R;
import com.nucleonai.nuclemon.pojo.DemoBO;
import com.nucleonai.nuclemon.pojo.dashboard.DashBoardDetailsBO;

import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardButtonsAdapter extends RecyclerView.Adapter<DashboardButtonsAdapter.DashBoardButtonsViewHolder>{

    private Context context;
    private ArrayList<DemoBO> demoBOArrayList;
    //private final int[] backgroundColors = {R.color.purple,R.color.colorGreen,R.color.colorButton,R.color.colorAccent,R.color.colorOrange};

    public DashboardButtonsAdapter(Context context, ArrayList<DemoBO> demoBOArrayList) {
        this.context = context;
        this.demoBOArrayList = demoBOArrayList;
    }

    @NonNull
    @Override
    public DashBoardButtonsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.row_dashboard_buttons,parent,false);
        return new DashBoardButtonsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashBoardButtonsViewHolder holder, int position) {
        holder.tvServerCount.setText(demoBOArrayList.get(position).getServerCount());
        holder.tvServerName.setText(demoBOArrayList.get(position).getServerName());
     //   holder.cardView.setBackgroundColor(demoBOArrayList.get(position).getServerColor());

        if(position==0){
            holder.cardView.setBackground(context.getResources().getDrawable(R.drawable.card_totalserver));
        }else if(position==1){
            holder.cardView.setBackground(context.getResources().getDrawable(R.drawable.card_upserver));
        }
        else if(position==2){
            holder.cardView.setBackground(context.getResources().getDrawable(R.drawable.card_downserver));
        }
        else if(position==3){
            holder.cardView.setBackground(context.getResources().getDrawable(R.drawable.card_services));
            holder.img_Corner.setBackground(context.getResources().getDrawable(R.drawable.services_icon));
        }

        // holder.cardView.setCardBackgroundColor(demoBOArrayList.get(position).getServerColor());
    }

    @Override
    public int getItemCount() {
        return demoBOArrayList.size();
    }

    public class DashBoardButtonsViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.cardview)
        CardView cardView;
        @BindView(R.id.tv_server_count)
        TextView tvServerCount;
        @BindView(R.id.tv_server_name)
        TextView tvServerName;
        @BindView(R.id.imgCorner)
        ImageView img_Corner;

        public DashBoardButtonsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
