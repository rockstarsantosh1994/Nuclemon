package com.nucleonai.nuclemon.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nucleonai.nuclemon.R;
import com.nucleonai.nuclemon.adapter.NotificationAdapter;
import com.nucleonai.nuclemon.pojo.DemoBO;
import com.nucleonai.nuclemon.utils.Utility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NotificationFragment extends Fragment {

    private static final String TAG = "DashBoardFragment";
    private Context context;
    private final Utility utility=new Utility();
    private Unbinder unbinder;

    @BindView(R.id.rv_notification)
    RecyclerView rvNotification;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_notification, container, false);
        unbinder= ButterKnife.bind(this,view);

        //basic intialisation...
        initViews();

        return view;
    }

    private void initViews() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        rvNotification.setLayoutManager(linearLayoutManager);

        ArrayList<DemoBO> demoBOArrayList=new ArrayList<>();
        demoBOArrayList.add(new DemoBO("20","Oracle Service Down",R.color.cardUp,"A:"));
        demoBOArrayList.add(new DemoBO("48","MySql  Service Down",R.color.cardService,"B:"));
        demoBOArrayList.add(new DemoBO("40","PostGreSQl  Service Down",R.color.cardDown,"A:"));
        demoBOArrayList.add(new DemoBO("70","MongoDB Service Down",R.color.cardTotal,"C:"));
        demoBOArrayList.add(new DemoBO("68","JavaJDBC  Service Down",R.color.purple,"D:"));
        demoBOArrayList.add(new DemoBO("28","Up Service Down",R.color.colorGreen,"E:"));

        NotificationAdapter notificationAdapter=new NotificationAdapter(context,demoBOArrayList);
        rvNotification.setAdapter(notificationAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}