package com.nucleonai.nuclemon.retrofit;


import com.nucleonai.nuclemon.pojo.LoginDetailsPojo;
import com.nucleonai.nuclemon.pojo.allserver.AllServerResponse;
import com.nucleonai.nuclemon.pojo.dashboard.DashBoardResponse;
import com.nucleonai.nuclemon.pojo.serverchart.ServerChartResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    @Headers({"request: android"})
    @GET("login")
    Call<LoginDetailsPojo> getLogin(@Query("login_id") String login_id, @Query("upassword") String upassword);

    @GET("getDashboardData")
    Call<DashBoardResponse> getDashBoardData();

    @GET("getAllServerDetailsMobile")
    Call<AllServerResponse> getAllServerDetailsMobile();

    @GET("getServerChartDetailsMobile")
    Call<ServerChartResponse> getServerChartDetails(@Query("serverId") String serverId);
}

