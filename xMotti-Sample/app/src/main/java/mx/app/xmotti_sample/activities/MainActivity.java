package mx.app.xmotti_sample.activities;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import mx.app.xmotti_sample.R;
import mx.xmotti.xmotti.xMotti;

public class MainActivity extends AppCompatActivity {

    private static final int  REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 0;
    private static final String API_KEY = "XXXXXXXXXXXXXXXXXXXXX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            xMotti.initialize(API_KEY ,this, null);
            xMotti.start();
        }else {
            xMotti.requestLocationAlwaysPermission(this);
        }

    }

    @TargetApi(Build.VERSION_CODES.M)
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {

            case REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS: {
                Map<String, Integer> perms = new HashMap<String, Integer>();

                perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED);

                for (int i = 0; i < permissions.length; i++) {
                    perms.put(permissions[i], grantResults[i]);
                }

                if (perms.get(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && perms.get(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                    xMotti.initialize(API_KEY,this, null);
                    xMotti.start();

                } else {
                    Toast.makeText(this, mx.xmotti.xmotti.R.string.permission_denied_message, Toast.LENGTH_SHORT).show();
                }
            }
            break;
            default:
                this.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

}
