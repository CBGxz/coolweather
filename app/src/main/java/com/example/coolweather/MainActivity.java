package com.example.coolweather;

        import android.Manifest;
        import android.content.pm.PackageManager;
        import android.os.Bundle;
        import android.os.Handler;
        import android.os.Message;
        import android.support.annotation.NonNull;
        import android.support.v4.app.ActivityCompat;
        import android.support.v4.content.ContextCompat;
        import android.support.v7.app.AppCompatActivity;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.baidu.location.BDLocation;
        import com.baidu.location.BDLocationListener;
        import com.baidu.location.LocationClient;
        import com.baidu.location.LocationClientOption;

        import java.util.ArrayList;
        import java.util.List;


public class MainActivity extends AppCompatActivity implements BDLocationListener {
    public LocationClient locationClient;
    private TextView positionText;
    private Handler handler;
    private TextView countryView;
    private TextView provinceView;
    private TextView cityView;
    private TextView distructView;
    private TextView streetView;
    private TextView latlngView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countryView = (TextView)findViewById(R.id.countryView);
        provinceView = (TextView)findViewById(R.id.provinceView);
        cityView = (TextView)findViewById(R.id.cityView);
        distructView = (TextView)findViewById(R.id.distructView);
        streetView = (TextView)findViewById(R.id.streetView);
        latlngView = (TextView)findViewById(R.id.latlngView);
        locationClient = new LocationClient(getApplicationContext());
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setIsNeedAddress(true);
        locationClient.setLocOption(option);
        locationClient.registerLocationListener(this);
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MainActivity.this, permissions, 1);
        } else {
            requestLocation();

        }
        handler = new Handler() {
            public void handleMessage(Message message) {
                Bundle location = message.getData();

                countryView.setText("国别：" + location.getString("country"));
                provinceView.setText("省份：" + location.getString("province"));
                cityView.setText("城市：" + location.getString("city"));
                distructView.setText("区县：" + location.getString("distruct"));
                streetView.setText("街道：" + location.getString("street"));
                latlngView.setText("坐标：" + location.getString("latlng"));

            }
        };



    }

    private void requestLocation(){
        locationClient.start();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length > 0){
                    for (int result : grantResults){
                        if (result != PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this,"必须同意所有权限才能使用本程序",
                                    Toast.LENGTH_SHORT).show();
                            finish();
                            return;

                        }
                    }
                    requestLocation();
                }else{
                    Toast.makeText(this,"发生未知错误",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }



    public void onReceiveLocation(BDLocation bdLocation) {
        if (bdLocation.getLocType() == BDLocation.TypeNetWorkLocation||bdLocation.getLocType()==BDLocation.TypeNetWorkLocation){

            Bundle data = new Bundle();
            data.putString("country",bdLocation.getCountry());
            data.putString("province",bdLocation.getProvince());
            data.putString("city",bdLocation.getCity());
            data.putString("distruct",bdLocation.getDistrict());
            data.putString("street",bdLocation.getStreet());
            data.putString("latlng","("+ bdLocation.getLatitude()+ ","+ bdLocation.getLatitude() + ")");
            Message message = new Message();
            message.setData(data);
            handler.sendMessage(message);



        }


    }


    public void onConnectHotSpotMessage(String s, int i) {

    }


}

