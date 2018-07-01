# Motti SDK

Motti SDK is an library written in Java for proximity triggered based content. You can read more about Motti SDK at [motti.com](https://motti.com/).


## Requirements

- Android 4.4+
- Android Studio
- Get your api key from [xretail.motti.com](https://xretail.motti.com/).

## Integration

**Gradle**
Add the [JCenter](https://bintray.com) Maven repository to install `xMotti` by adding it your top-level `build.gradle`.
```
repositories {  
    maven { url 'https://dl.bintray.com/juancarlostg/motti' }  
}
```

Add the Motti SDK to your app module's dependencies in Android Studio by adding the following line to your top-level `build.gradle`.
```
implementation 'mx.xmotti.xmotti:xmotti:1.1.15'
```

## Configuration

Create a custom  `Application`  class and add the following lines.
```java
public class MyApplication extends Application {  
  
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
```

Include the custom  `Application`  class in your `AndroidManifest.xml`
```xml
<application  
  android:name=".MyApplication"  
  android:allowBackup="true"  
  android:icon="@mipmap/ic_launcher"  
  android:label="@string/app_name"  
  android:roundIcon="@mipmap/ic_launcher_round"  
  android:supportsRtl="true"  
  android:theme="@style/AppTheme">  
    <activity android:name=".activities.MainActivity">  
        <intent-filter>  
            <action android:name="android.intent.action.MAIN" />  
  
            <category android:name="android.intent.category.LAUNCHER" />  
        </intent-filter>  
    </activity>  
</application>
```


 `NSLocationAlwaysAndWhenInUseUsageDescription` `NSLocationAlwaysUsageDescription` `NSLocationWhenInUseUsageDescription` in your `Info.plist`.
- Enable the `location` background mode.

## Usage

Include the following code at your first loaded `Activity` class.

```java
public class MainActivity extends AppCompatActivity {  
  
    private static final int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 0;  
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
```

## Support

Don't hesitate to contact us at info@motti.com
