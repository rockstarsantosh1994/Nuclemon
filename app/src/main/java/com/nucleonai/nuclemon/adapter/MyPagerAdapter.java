package com.nucleonai.nuclemon.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.nucleonai.nuclemon.R;
import com.nucleonai.nuclemon.pojo.DemoBO;
import com.nucleonai.nuclemon.pojo.dashboard.DashBoardDetailsBO;

import java.util.ArrayList;

import antonkozyriatskyi.circularprogressindicator.CircularProgressIndicator;

public class MyPagerAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<DashBoardDetailsBO> dashBoardDetailsBOArrayList;

    public MyPagerAdapter(Context context, ArrayList<DashBoardDetailsBO> dashBoardDetailsBOArrayList) {
        this.context = context;
        this.dashBoardDetailsBOArrayList = dashBoardDetailsBOArrayList;
    }

    @Override
    public int getCount() {
        return dashBoardDetailsBOArrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_server_dashboard_adapter,container,false);

        TextView tvServerName=view.findViewById(R.id.tv_server_name);
        CircularProgressIndicator progressCpu=view.findViewById(R.id.circular_progress_cpu);
        CircularProgressIndicator progressMemory=view.findViewById(R.id.circular_progress_memory);
        RecyclerView rvDisk=view.findViewById(R.id.rv_disk);
        View viewColor=view.findViewById(R.id.view_color);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        rvDisk.setLayoutManager(linearLayoutManager);

        tvServerName.setText(dashBoardDetailsBOArrayList.get(position).getServerName());
        progressCpu.setProgress(dashBoardDetailsBOArrayList.get(position).getCpuUtilization(),100);
        progressMemory.setProgress(dashBoardDetailsBOArrayList.get(position).getMemmoryUtilization(),100);

        /*viewColor.setBackgroundColor(ContextCompat.getColor(context,demoBOArrayList.get(position).getServerColor()));
        progressCpu.setProgressColor(ContextCompat.getColor(context,demoBOArrayList.get(position).getServerColor()));
        progressMemory.setProgressColor(ContextCompat.getColor(context,demoBOArrayList.get(position).getServerColor()));
        progressCpu.setDotColor(ContextCompat.getColor(context,demoBOArrayList.get(position).getServerColor()));
        progressMemory.setDotColor(ContextCompat.getColor(context,demoBOArrayList.get(position).getServerColor()));
        progressCpu.setTextColor(ContextCompat.getColor(context,demoBOArrayList.get(position).getServerColor()));
        progressMemory.setTextColor(ContextCompat.getColor(context,demoBOArrayList.get(position).getServerColor()));*/

        if(dashBoardDetailsBOArrayList.get(position).getDriveUtilization().size()>0){
            DiskAdapter diskAdapter=new DiskAdapter(context,dashBoardDetailsBOArrayList.get(position).getDriveUtilization());
            rvDisk.setAdapter(diskAdapter);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}