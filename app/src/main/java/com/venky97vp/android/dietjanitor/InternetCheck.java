package com.venky97vp.android.dietjanitor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;

import java.net.InetAddress;

/**
 * Created by venky on 02-04-2017.
 */

public class InternetCheck {

    Context context;

    static void noInternet(Context context){
        new AlertDialog.Builder(context)
                .setTitle("No Internet Access")
                .setMessage("Please Check your Internet connection")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setCancelable(false)
                .show();
    }
}
