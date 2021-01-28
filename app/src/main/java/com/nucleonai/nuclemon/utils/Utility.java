package com.nucleonai.nuclemon.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.net.InetAddress;

import static android.content.Context.MODE_PRIVATE;

public class Utility {

    Context context = MyApplication.getAppContext();
     String  MY_PREFS_NAME="OXYRICH_PREFERENCE";
    ProgressDialog progressDialog;
    public  boolean isValidEmail(CharSequence target) {
        return (TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public boolean isEmptyEditText(CharSequence target){
        return TextUtils.isEmpty(target);
    }
    public void ToastShort(String msg)
    {
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

    public void setPreferences(String KEY,String VALUE){
        SharedPreferences settings =  context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(KEY, VALUE);
        editor.commit();

    }
    public String getPreferences(String KEY)
    {
        SharedPreferences shared = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String value = (shared.getString(KEY, ""));
        return value;
    }
    public static void setPreferences(Context act, String key, String value) {
        SharedPreferences settings = act.getSharedPreferences(act.getApplicationInfo().packageName + "_preferences", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);

        // Commit the edits!
        editor.commit();
    }

    public static String getPreferences(Context act, String key, String def) {
        // [[NSUSerDefault standardUserDefault]setValue:firstName.text ForKey:@"keyname"]
        SharedPreferences settings = act.getSharedPreferences(act.getApplicationInfo().packageName + "_preferences", 0);
        return settings.getString(key, def);
    }
    public  void showMessageDialog(Context context, String message) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(MyApplication.getAppContext().getString(android.R.string.ok), null)
                .show();
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private boolean haveNetworkConnection(Context context) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;
        boolean haveConnectedInternet = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo)
        {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }

        haveConnectedInternet=isInternetAvailable();

        return haveConnectedWifi || haveConnectedMobile || haveConnectedInternet;
    }

    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com"); //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }

    }
    public void showProgressDialog(Context context)
    {

        progressDialog = new ProgressDialog(context);
        //making the progressbar visible
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
    public void dismissProgressDialog(Context context)
    {

        progressDialog.dismiss();
    }
    public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }

}
