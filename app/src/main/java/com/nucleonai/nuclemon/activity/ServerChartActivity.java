package com.nucleonai.nuclemon.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.nucleonai.nuclemon.R;
import com.nucleonai.nuclemon.graph.MyMarkerView;
import com.nucleonai.nuclemon.pojo.serverchart.ServerChartResponse;
import com.nucleonai.nuclemon.retrofit.APIClient;
import com.nucleonai.nuclemon.retrofit.APIInterface;
import com.nucleonai.nuclemon.utils.Utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ServerChartActivity extends AppCompatActivity implements OnChartValueSelectedListener {

    private static final String TAG = "ServerChartActivity";
    private APIInterface apiInterface;
    private Retrofit retrofitClient;
    private final Utility utility = new Utility();
    @BindView(R.id.chart1)
    LineChart chart1;
    @BindView(R.id.chart2)
    LineChart chart2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_chart);
        ButterKnife.bind(this);

        //Basic intialisation..
        initViews();

        //intialise graph..
        initGraph();

        if (getIntent().getStringExtra("serverid") != null) {
            //getServerChartDetails
            //getServerChartDetails(getIntent().getStringExtra("serverid"));
            getServerChartDetails("1");
        }
    }

    private void initViews() {
        retrofitClient = APIClient.getClient();
        apiInterface = retrofitClient.create(APIInterface.class);
    }

    private void initGraph(){
        {
            // background color
            chart1.setBackgroundColor(Color.WHITE);
            chart2.setBackgroundColor(Color.WHITE);

            // disable description text
            chart1.getDescription().setEnabled(false);
            chart2.getDescription().setEnabled(false);

            // enable touch gestures
            chart1.setTouchEnabled(true);
            chart2.setTouchEnabled(true);

            // set listeners
            chart1.setOnChartValueSelectedListener(this);
            chart2.setOnChartValueSelectedListener(this);

            chart1.setDrawGridBackground(false);
            chart2.setDrawGridBackground(false);

            // create marker to display box when values are selected
            MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);

            // Set the marker to the chart
            mv.setChartView(chart1);

            chart1.setMarker(mv);

            // enable scaling and dragging
            chart1.setDragEnabled(true);

            chart1.setScaleEnabled(true);

            // chart.setScaleXEnabled(true);

            // chart.setScaleYEnabled(true);

            // force pinch zoom along both axis
            chart1.setPinchZoom(true);

            // get the legend (only possible after setting data)
            Legend l = chart1.getLegend();
            l.setEnabled(false);

            XAxis xAxis = chart1.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.TOP_INSIDE);
            //xAxis.setTypeface(tfLight);
            xAxis.setTextSize(10f);
            xAxis.setTextColor(Color.WHITE);
            xAxis.setDrawAxisLine(false);
            xAxis.setDrawGridLines(true);
            xAxis.setTextColor(Color.rgb(255, 192, 56));
            xAxis.setCenterAxisLabels(true);
            xAxis.setGranularity(1f); // one hour
            xAxis.setValueFormatter(new ValueFormatter() {

                private final SimpleDateFormat mFormat = new SimpleDateFormat("dd MMM HH:mm", Locale.ENGLISH);

                @Override
                public String getFormattedValue(float value, AxisBase axis) {

                    long millis = TimeUnit.HOURS.toMillis((long) value);
                    return mFormat.format(new Date(millis));
                }
            });

            YAxis leftAxis = chart1.getAxisLeft();
            leftAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
           // leftAxis.setTypeface(tfLight);
            leftAxis.setTextColor(ColorTemplate.getHoloBlue());
            leftAxis.setDrawGridLines(true);
            leftAxis.setGranularityEnabled(true);
            leftAxis.setAxisMinimum(0f);
            leftAxis.setAxisMaximum(170f);
            leftAxis.setYOffset(-9f);
            leftAxis.setTextColor(Color.rgb(255, 192, 56));

            YAxis rightAxis = chart1.getAxisRight();
            rightAxis.setEnabled(false);
        }
    }

    private void getServerChartDetails(String serverID) {
        if (Utility.isNetworkAvailable(ServerChartActivity.this)) {
            utility.showProgressDialog(ServerChartActivity.this);
            Call<ServerChartResponse> serverChartResponseCall = apiInterface.getServerChartDetails(serverID);
            serverChartResponseCall.enqueue(new Callback<ServerChartResponse>() {
                @Override
                public void onResponse(Call<ServerChartResponse> call, Response<ServerChartResponse> response) {
                    ServerChartResponse serverChartResponse = response.body();
                    utility.dismissProgressDialog(ServerChartActivity.this);
                    // Log.e(TAG, "" + serverChartResponse.isStatus());
                    if (serverChartResponse != null) {
                        Log.e(TAG, "" + serverChartResponse.isStatus());
                        Log.e(TAG, "" + serverChartResponse.getMessage());
                        //utility.ToastShort(loginPojo.getMessage());

                        if (serverChartResponse.isStatus()) {
                                //setGraphData();
                        } else {
                            utility.dismissProgressDialog(ServerChartActivity.this);
                            utility.ToastShort(serverChartResponse.getMessage());
                        }
                    }

                }

                @Override
                public void onFailure(Call<ServerChartResponse> call, Throwable t) {
                    utility.dismissProgressDialog(ServerChartActivity.this);
                    Log.e(TAG, "onFailure: " + t);
                }
            });
        } else {
            utility.ToastShort(getResources().getString(R.string.nointernet));
        }
    }

    private void setGraphData(){

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}