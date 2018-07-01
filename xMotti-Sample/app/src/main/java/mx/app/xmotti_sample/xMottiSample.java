package mx.app.xmotti_sample;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.support.v4.content.ContextCompat;

import mx.xmotti.xmotti.xMotti;

public class xMottiSample extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            xMotti.initializeService(this, R.drawable.ic_notification);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

}
