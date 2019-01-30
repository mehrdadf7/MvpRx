package com.github.mehrdadf7.javamvprx.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkStateReceiver extends BroadcastReceiver {

    private OnNetworkStateChangeListener onNetworkStateChangeListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (onNetworkStateChangeListener != null) {
            onNetworkStateChangeListener.onChangeState(isConnected(context));
        }
    }

    private static boolean isConnected(Context context){
        ConnectivityManager conMan = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMan.getActiveNetworkInfo();
        return (netInfo!= null && netInfo.isConnectedOrConnecting());
    }

    public void setOnNetworkStateChangeListener(OnNetworkStateChangeListener onNetworkStateChangeListener) {
        this.onNetworkStateChangeListener = onNetworkStateChangeListener;
    }

    public interface OnNetworkStateChangeListener {
        void onChangeState(boolean isOnline);
    }

}
