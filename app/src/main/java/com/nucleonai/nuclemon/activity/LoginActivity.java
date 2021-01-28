package com.nucleonai.nuclemon.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nucleonai.nuclemon.R;
import com.nucleonai.nuclemon.constants.LocalConstatns;
import com.nucleonai.nuclemon.pojo.LoginDetailsPojo;
import com.nucleonai.nuclemon.retrofit.APIClient;
import com.nucleonai.nuclemon.retrofit.APIInterface;
import com.nucleonai.nuclemon.utils.Utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    EditText edtUserName,editText_password;
    Button btnLogIn;
    Utility utility=new Utility();
    private APIInterface apiInterface;
    private Retrofit retrofitClient;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mInitComp();

        //Temporary calling..
        startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
        finish();
    }

    void mInitComp(){
        retrofitClient = APIClient.getClient();
        apiInterface = retrofitClient.create(APIInterface.class);

        edtUserName=findViewById(R.id.edtUserName);
        editText_password=findViewById(R.id.editText_password);
        btnLogIn=findViewById(R.id.btnLogIn);
        btnLogIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==btnLogIn)
        {
            if(utility.isEmptyEditText(edtUserName.getText().toString())||utility.isEmptyEditText(editText_password.getText().toString()))
            {
                utility.ToastShort(getString(R.string.filldetails));
            }else{
                mLogin();
            }
        }
    }
    void  mLogin()
    {
        if(utility.isNetworkAvailable(this))
        {
            utility.showProgressDialog(LoginActivity.this);
            Call<LoginDetailsPojo> loginPojoCall;
            Calendar c = Calendar.getInstance();
            //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = sdf.format(c.getTime());
            System.out.println(strDate);

            loginPojoCall=apiInterface.getLogin(edtUserName.getText().toString(),editText_password.getText().toString());

            loginPojoCall.enqueue(new Callback<LoginDetailsPojo>() {
                @Override
                public void onResponse(Call<LoginDetailsPojo> call, Response<LoginDetailsPojo> response) {
                    LoginDetailsPojo loginPojo =response.body();
                    utility.dismissProgressDialog(LoginActivity.this);
                    Log.e("LOGIN_BODY:", String.valueOf(response.body().getStatus()));
                     //utility.ToastShort(loginPojo.getMessage());
                    if( loginPojo.getStatus()){
                        utility.ToastShort("Logged in ");
                        Log.e("LOGIN_TRUE:", "LOGIN_TRUE");
                         startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                        finish();
                        utility.setPreferences(LocalConstatns.LOGIN_USERID,loginPojo.getUserProfile().getUserId().toString());
                        utility.setPreferences(LocalConstatns.LOGIN_NAME,loginPojo.getUserProfile().getUserName().toString());

                     /*   if(utility.getPreferences(LocalConstatns.LOGIN_USERROLL).equals("6")){

                            // Distributor
                            utility.setPreferences(LocalConstatns.FROM, "splash");
                            utility.ToastShort(loginPojo.getMessage());
                            utility.setPreferences(LocalConstatns.DISTRIBUTOR_ID,loginPojo.getResult().getDistributorId().toString());
                            utility.setPreferences(LocalConstatns.LOGIN_USERID,loginPojo.getResult().getUserId().toString());
                            utility.setPreferences(LocalConstatns.LOGIN_PHOTO,loginPojo.getResult().getPhoto().toString());
                            utility.setPreferences(LocalConstatns.LOGIN_NAME,loginPojo.getResult().getUserName().toString());
                            utility.setPreferences(LocalConstatns.LOGIN_EMAIL,loginPojo.getResult().getEmail().toString());
                            utility.setPreferences(LocalConstatns.IsLogin,"YES");

                            Intent intent = new Intent(LoginActivity.this, DistributorActivity.class);

                            startActivity(intent);

                            // startActivity(new Intent(SplashActivity.this, DashboardActivity.class));
                            finish();
                        }else*/
                    }else{
                        utility.dismissProgressDialog(LoginActivity.this);
                        utility.ToastShort("Something went wrong");
                    }
                }

                @Override
                public void onFailure(Call<LoginDetailsPojo> call, Throwable t) {
                    utility.dismissProgressDialog(LoginActivity.this);
                    Log.e(TAG, "onFailure: "+t );
                }
            });
        }else
        {
            utility.ToastShort(getResources().getString(R.string.nointernet));
        }
    }
}