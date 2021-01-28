package com.nucleonai.nuclemon.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nucleonai.nuclemon.R;
import com.nucleonai.nuclemon.adapter.AllServerAdapter;
import com.nucleonai.nuclemon.adapter.DashboardButtonsAdapter;
import com.nucleonai.nuclemon.adapter.MyPagerAdapter;
import com.nucleonai.nuclemon.pojo.DemoBO;
import com.nucleonai.nuclemon.pojo.DemoBO1;
import com.nucleonai.nuclemon.pojo.allserver.AllServerResponse;
import com.nucleonai.nuclemon.pojo.dashboard.DashBoardResponse;
import com.nucleonai.nuclemon.retrofit.APIClient;
import com.nucleonai.nuclemon.retrofit.APIInterface;
import com.nucleonai.nuclemon.utils.Utility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AllServersFragment extends Fragment{

    private static final String TAG = "AllServersFragment";
    private View view;
    private Context context;
    private Unbinder unbinder;
    @BindView(R.id.rv_all_servers)
    RecyclerView rvAllServers;
    private APIInterface apiInterface;
    private Retrofit retrofitClient;
    private final Utility utility = new Utility();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_all_servers, container, false);
        unbinder= ButterKnife.bind(this, view);

        //Basic intialisation..
        initViews();

        //getAll Server details..
        getAllServerDetailsMobile();

        return view;
    }

    private void initViews(){
        retrofitClient = APIClient.getClient();
        apiInterface = retrofitClient.create(APIInterface.class);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        rvAllServers.setLayoutManager(linearLayoutManager);

    }

    private void getAllServerDetailsMobile() {
        if (Utility.isNetworkAvailable(context)) {
            utility.showProgressDialog(context);
            Call<AllServerResponse> allServerResponseCall = apiInterface.getAllServerDetailsMobile();
            allServerResponseCall.enqueue(new Callback<AllServerResponse>() {
                @Override
                public void onResponse(Call<AllServerResponse> call, Response<AllServerResponse> response) {
                    AllServerResponse allServerResponse = response.body();
                    utility.dismissProgressDialog(context);
                    Log.e(TAG, "" + allServerResponse.isStatus());
                    //utility.ToastShort(loginPojo.getMessage());

                    if (allServerResponse.isStatus()) {
                        //utility.ToastShort(allServerResponse.getMessage());
                        AllServerAdapter allServerAdapter=new AllServerAdapter(context,allServerResponse.getServerDetails());
                        rvAllServers.setAdapter(allServerAdapter);
                    } else {
                        utility.dismissProgressDialog(context);
                        utility.ToastShort(allServerResponse.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<AllServerResponse> call, Throwable t) {
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
    }
}