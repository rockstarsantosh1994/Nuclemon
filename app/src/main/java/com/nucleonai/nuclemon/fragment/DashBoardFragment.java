package com.nucleonai.nuclemon.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nucleonai.nuclemon.R;
import com.nucleonai.nuclemon.adapter.DashboardButtonsAdapter;
import com.nucleonai.nuclemon.adapter.MyPagerAdapter;
import com.nucleonai.nuclemon.pojo.DemoBO;
import com.nucleonai.nuclemon.pojo.LoginDetailsPojo;
import com.nucleonai.nuclemon.pojo.dashboard.DashBoardResponse;
import com.nucleonai.nuclemon.retrofit.APIClient;
import com.nucleonai.nuclemon.retrofit.APIInterface;
import com.nucleonai.nuclemon.utils.Utility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DashBoardFragment extends Fragment {

    private static final String TAG = "DashBoardFragment";
    private Context context;
    private final Utility utility = new Utility();
    private APIInterface apiInterface;
    private Retrofit retrofitClient;
    private Unbinder unbinder;
    private Handler handler;
    private Runnable r;
    private boolean isFirstLaunched = true;

    @BindView(R.id.viewpager)
    ViewPager viewPager2;

    @BindView(R.id.rv_buttons)
    RecyclerView rvButtons;

    private ArrayList<DemoBO> demoButtonList = new ArrayList<>();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dash_board, container, false);
        unbinder = ButterKnife.bind(this, view);

        //basic intialisation...
        initViews();

        //get Dashboard data..
        getDashBoardData();

        return view;
    }

    private void initViews() {
        retrofitClient = APIClient.getClient();
        apiInterface = retrofitClient.create(APIInterface.class);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        rvButtons.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler = new Handler();
        r = new Runnable() {
            public void run() {
                //mLogin();
                getDashBoardData();
                handler.postDelayed(this, 30000);
            }
        };
        handler.postDelayed(r, 30000);
    }

    private void getDashBoardData() {
        if (Utility.isNetworkAvailable(context)) {
            utility.showProgressDialog(context);
            Call<DashBoardResponse> dashBoardResponseCall = apiInterface.getDashBoardData();
            dashBoardResponseCall.enqueue(new Callback<DashBoardResponse>() {
                @Override
                public void onResponse(Call<DashBoardResponse> call, Response<DashBoardResponse> response) {
                    DashBoardResponse dashBoardResponse = response.body();
                    utility.dismissProgressDialog(context);
                    Log.e("LOGIN_BODY:", "" + dashBoardResponse.isStatus());
                    //utility.ToastShort(loginPojo.getMessage());

                    if (dashBoardResponse.isStatus()) {
                        //utility.ToastShort(dashBoardResponse.getMessage());

                        demoButtonList.clear();
                        demoButtonList.add(new DemoBO(dashBoardResponse.getTotalServer(), "Total Servers", R.color.purple));
                        demoButtonList.add(new DemoBO(dashBoardResponse.getUpServer(), "Up Servers", R.color.colorGreen));
                        demoButtonList.add(new DemoBO(dashBoardResponse.getDownServer(), "Down Servers", R.color.colorOrange));
                        demoButtonList.add(new DemoBO(dashBoardResponse.getServices(), "Services", R.color.colorAccent));

                        DashboardButtonsAdapter dashboardButtonsAdapter = new DashboardButtonsAdapter(context, demoButtonList);
                        rvButtons.setAdapter(dashboardButtonsAdapter);
                        rvButtons.setAdapter(dashboardButtonsAdapter);

                        if (dashBoardResponse.getDashBoardDetailsBOArrayList().size() > 0) {
                            MyPagerAdapter myPagerAdapter = new MyPagerAdapter(context, dashBoardResponse.getDashBoardDetailsBOArrayList());
                            viewPager2.setAdapter(myPagerAdapter);
                            viewPager2.setPageTransformer(true, new RotationPageTransformer());
                            viewPager2.setOffscreenPageLimit(2);
                            //viewPager2.setPageMargin(15);
                        }
                    } else {
                        utility.dismissProgressDialog(context);
                        utility.ToastShort(dashBoardResponse.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<DashBoardResponse> call, Throwable t) {
                    utility.dismissProgressDialog(context);
                    Log.e(TAG, "onFailure: " + t);
                }
            });
        } else {
            utility.ToastShort(getResources().getString(R.string.nointernet));
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        // use this when you don't want callback to be called anymore
        if (handler != null) {
            handler.removeCallbacks(r);
        }
    }
}