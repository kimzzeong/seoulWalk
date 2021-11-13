package com.example.seoulwalk.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seoulwalk.R;
import com.example.seoulwalk.data.Exam_data;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ActivityMap extends AppCompatActivity
        implements OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback{


    private GoogleMap mMap;
    private Marker currentMarker = null;
    List<Polyline>polylines =new ArrayList<>();
    LatLng START_LOCATION;
    LatLng END_LOCATION;
    ArrayList<LatLng> latLngArrayList = new ArrayList<LatLng>();
    ArrayList<LatLng> my_load_ArrayList = new ArrayList<LatLng>();
    ArrayList<LatLng> my_load_ArrayList2 = new ArrayList<LatLng>();
    PolylineOptions options = new PolylineOptions();
    PolylineOptions options2 = new PolylineOptions();
    PolylineOptions options3 = new PolylineOptions();
    private static final String TAG = "googlemap_example";
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int UPDATE_INTERVAL_MS = 60000;  // 10초
    private static final int FASTEST_UPDATE_INTERVAL_MS = 5000; //5초

    private BottomSheetBehavior bottomSheetBehavior;

    // onRequestPermissionsResult에서 수신된 결과에서
    // ActivityCompat.requestPermissions를 사용한 퍼미션 요청을 구별하기 위해 사용됩니다.
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    boolean needRequest = false;
    Chronometer chronometer;
    private Thread timeThread = null;
    private Boolean isRunning = true;

    // 앱을 실행하기 위해 필요한 퍼미션을 정의합니다.
    String[] REQUIRED_PERMISSIONS  = {Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};  // 외부 저장소


    Location mCurrentLocatiion;
    LatLng currentPosition;


    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest locationRequest;
    private Location location;
    TextView dullegil_name,time_view;
    String dulle_start,courseNameString; //둘레길 시작점 이름
    String dulle_end; //둘레길 도착점 이름
    String Lat_start; //둘레길 도착점 이름
    String Lat_end; //둘레길 도착점 이름
    Button btn_finish,btn_start_walk;
    long stopTime = 0;
    private View mLayout;  // Snackbar 사용하기 위해서는 View가 필요합니다.
    // (참고로 Toast에서는 Context가 필요했습니다.)
    String results;
    /**여기다가 싹다 넣어 줘야 한다 */
    private ArrayList<LatLng> arrayPoints;
    //private PolylineOptions polylineOptions = new PolylineOptions();


    //polyline을 그려주는 메소드
//    private void drawPath(){
//
//
//        Exam_data exam_data = new Exam_data();
//        String a = exam_data.getDulle_1_1();
//        Log.e("CREATE!!!!",a);
//        //parde_Location(a);
//        //PolylineOptions options = new PolylineOptions();
////        for (int i =0; i<latLngArrayList.size(); i++){
////            options.add(latLngArrayList.get(i)).width(15).color(Color.BLACK).geodesic(true);
////            polylines.add(mMap.addPolyline(options));
////        }
//
//        options.addAll(latLngArrayList);
//        options.width(15);
//        options.color(Color.BLACK);
//
//        polylines.add(mMap.addPolyline(options));
//        Log.e("폴리라인","그려지나요?");
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(START_LOCATION, 18));
//    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.e(TAG, String.valueOf(checkPermission())+"여기는 START");
//        Intent intent = new Intent(getApplicationContext(), Map_service.class);
//        startService(intent);


        if (checkPermission()) {

            Log.e(TAG, "onStart : call mFusedLocationClient.requestLocationUpdates");
            mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);

            if (mMap!=null)
                mMap.setMyLocationEnabled(true);

        }


    }
    @Override
    protected void onResume() {
        super.onResume();

//        Intent intent = new Intent(getApplicationContext(), Map_Kid_Receiver_Service.class);
//        startService(intent);
//        Intent intent1 = new Intent(getApplicationContext(), Map_service.class);
//        startService(intent1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_maps);

        mLayout = findViewById(R.id.layout_main);
        btn_start_walk= (Button)findViewById(R.id.btn_start_walk);
        btn_finish = (Button)findViewById(R.id.btn_finish);
        dullegil_name = findViewById(R.id.dullegil_name);
        Intent intent = getIntent();
        dulle_start = intent.getStringExtra("dulle_start");
        dulle_end = intent.getStringExtra("dulle_end");
        Lat_start = intent.getStringExtra("LanLng");
        Lat_end = intent.getStringExtra("LatLng_end");
        courseNameString = intent.getStringExtra("courseNameString");
        dullegil_name.setText(courseNameString);
        System.out.println(dulle_start+"-- : 시작점 값 확인");
        System.out.println(dulle_end+"-- : 도착점 값 확인");
        System.out.println(Lat_start+"-- : 시작점 좌표 값 확인");
        System.out.println(Lat_end+"-- : 도착점 좌표 값 확인");
        time_view = findViewById(R.id.time_view);
        locationRequest = new LocationRequest()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL_MS);
//                .setFastestInterval(FASTEST_UPDATE_INTERVAL_MS);


        LocationSettingsRequest.Builder builder =
                new LocationSettingsRequest.Builder();

        builder.addLocationRequest(locationRequest);


        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                stopTime = chronometer.getBase() - SystemClock.elapsedRealtime();
//                chronometer.stop();
//                btn_start_walk.setVisibility(View.VISIBLE);
//                btn_finish.setVisibility(View.GONE);

                timeThread.interrupt();
                showDialog();
//                Intent intent = new Intent(getApplicationContext(), ActivityResultAfterWalk.class);
//                startService(intent);

            }
        });
        btn_start_walk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                chronometer.setBase(SystemClock.elapsedRealtime() + stopTime);
//                chronometer.start();
//                btn_start_walk.setVisibility(View.GONE);
//                btn_finish.setVisibility(View.VISIBLE);
                timeThread = new Thread(new timeThread());
                timeThread.start();
            }
        });

    }
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int mSec = msg.arg1 % 100;
            int sec = (msg.arg1 / 100) % 60;
            int min = (msg.arg1 / 100) / 60;
            int hour = (msg.arg1 / 100) / 360;
            //1000이 1초 1000*60 은 1분 1000*60*10은 10분 1000*60*60은 한시간

            @SuppressLint("DefaultLocale")  String result = String.format("%02d:%02d:%02d:%02d", hour, min, sec, mSec);
            if (result.equals("00:01:15:00")) {
                //Toast.makeText(MainActivity.this, "1분 15초가 지났습니다.", Toast.LENGTH_SHORT).show();
            }
            time_view.setText(result);
            results =result;
        }
    };

    public class timeThread implements Runnable {
        @Override
        public void run() {
            int i = 0;

            while (true) {
                while (isRunning) { //일시정지를 누르면 멈춤
                    Message msg = new Message();
                    msg.arg1 = i++;
                    handler.sendMessage(msg);

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable(){
                            @Override
                            public void run() {
                                time_view.setText("");
                                time_view.setText("00:00:00:00");
                            }
                        });
                        return; // 인터럽트 받을 경우 return
                    }
                }
            }
        }
    }


    @Override
    public void onMapReady(final GoogleMap googleMap) {
        Log.d(TAG, "onMapReady :");
        Gps_Tracker gps_tracker = new Gps_Tracker(getApplicationContext());
        Log.e("aaaa",String.valueOf(gps_tracker.getLatitude()));

        mMap = googleMap;

        //런타임 퍼미션 요청 대화상자나 GPS 활성 요청 대화상자 보이기전에
        //지도의 초기위치를 서울로 이동

//setDefaultLocation();
        setStartLocation();
        setEndLocation();
        drawPath();
       // drawPath2();
        //런타임 퍼미션 처리
        // 1. 위치 퍼미션을 가지고 있는지 체크합니다.
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);



        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED   ) {

            // 2. 이미 퍼미션을 가지고 있다면
            // ( 안드로이드 6.0 이하 버전은 런타임 퍼미션이 필요없기 때문에 이미 허용된 걸로 인식합니다.)

            Log.e("Persion_Check","If");
            startLocationUpdates(); // 3. 위치 업데이트 시작




        }else {  //2. 퍼미션 요청을 허용한 적이 없다면 퍼미션 요청이 필요합니다. 2가지 경우(3-1, 4-1)가 있습니다.

            Log.e("Persion_Check","Else");
            // 3-1. 사용자가 퍼미션 거부를 한 적이 있는 경우에는
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])) {
                setDefaultLocation();
                // 3-2. 요청을 진행하기 전에 사용자가에게 퍼미션이 필요한 이유를 설명해줄 필요가 있습니다.
                Snackbar.make(mLayout, "이 앱을 실행하려면 위치 접근 권한이 필요합니다.",
                        Snackbar.LENGTH_INDEFINITE).setAction("확인", new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        // 3-3. 사용자에게 퍼미션 요청을 합니다. 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                        ActivityCompat.requestPermissions( ActivityMap.this, REQUIRED_PERMISSIONS,
                                PERMISSIONS_REQUEST_CODE);
                    }
                }).show();


            } else {
                // 4-1. 사용자가 퍼미션 거부를 한 적이 없는 경우에는 퍼미션 요청을 바로 합니다.
                // 요청 결과는 onRequestPermissionResult 수신됩니다.
                ActivityCompat.requestPermissions( this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }

        }



        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        // 현재 오동작을 해서 주석처리
        //mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {

                Log.d( TAG, "onMapClick :");
            }
        });
    }

    LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);

            List<Location> locationList = locationResult.getLocations();

            if (locationList.size() > 0) {
                location = locationList.get(locationList.size() - 1);
                //location = locationList.get(0);

                currentPosition
                        = new LatLng(location.getLatitude(), location.getLongitude());

                System.out.println("현재 위치입니다."+currentPosition);

                String markerTitle = getCurrentAddress(currentPosition);
                String markerSnippet = "위도:" + String.valueOf(location.getLatitude())
                        + " 경도:" + String.valueOf(location.getLongitude());

                Log.d(TAG, "onLocationResult : " + markerSnippet);

//                if (!currentPosition.equals(new LatLng(37.6218817, 127.086575))){
//                    // TODO: 11/13/21 여기 만들기
//                    String exam = "127.0868762596695,37.62213946756835,0 127.0870212659526,37.62185124518431,0 127.0869320614216,37.62168127987147,0 127.0867778527135,37.62155989094231,0 127.0865706754879,37.62147452625686,0 127.0864253684765,37.62135459589448,0 127.0861796894806,37.62128716322113,0 127.0854037515403,37.62098433536994,0 127.0851414422273,37.62092616335796,0 127.0848704776604,37.6207875398179,0 127.084712434564,37.62070002518217";
//                    System.out.println("확인중입니다___제발");
//                    parse_Location3(exam);
////                    options3.add(new LatLng( 37.62213946756835, 127.0868762596695)).add(new LatLng(37.62070002518217,127.084712434564));
//                    options3.addAll(my_load_ArrayList2);
//                    options3.color(Color.RED);
//                    options3.width(8);
//                    //
//
//                    polylines.add(mMap.addPolyline(options3));
//                    showDialog();
//                    Toast.makeText(ActivityMap.this, "목적지에 도착하였습니다.", Toast.LENGTH_SHORT).show();
//                }
//                else
                    if (!currentPosition.equals(new LatLng(37.46701, 126.9492133))) {
                    String exam_5 = "126.9492421730048,37.467062766817,0 126.9485976145976,37.46667252705947,0 126.9483378919789,37.46655807261658,0 126.9480656303997,37.46656578225925,0 126.9478032562691,37.46677662602115";
String exam_5_1 =  "126.9480656303997,37.46656578225925,0 126.9478032562691,37.46677662602115";
                    parse_Location3(exam_5);
                    options3.addAll(my_load_ArrayList2);
                    options3.color(Color.RED);
                    options3.width(15);

                    polylines.add(mMap.addPolyline(options3));
//                    showDialog();
                    //Toast.makeText(ActivityMap.this, "목적지에 도착하였습니다.", Toast.LENGTH_SHORT).show();
                }


                //현재 위치에 마커 생성하고 이동
                setCurrentLocation(location, markerTitle, markerSnippet);

                mCurrentLocatiion = location;
            }


        }

    };


    // TODO: 11/8/21 위치 업데이트
    private void startLocationUpdates() {

        if (!checkLocationServicesStatus()) {

            Log.d(TAG, "startLocationUpdates : call showDialogForLocationServiceSetting");
            showDialogForLocationServiceSetting();
        }else {

            int hasFineLocationPermission = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION);
            int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION);



            if (hasFineLocationPermission != PackageManager.PERMISSION_GRANTED ||
                    hasCoarseLocationPermission != PackageManager.PERMISSION_GRANTED   ) {

                Log.d(TAG, "startLocationUpdates : 퍼미션 안가지고 있음");
                return;
            }


            Log.d(TAG, "startLocationUpdates : call mFusedLocationClient.requestLocationUpdates");

            mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());

            if (checkPermission())
                mMap.setMyLocationEnabled(true);

        }

    }





    @Override
    protected void onStop() {

        super.onStop();

        if (mFusedLocationClient != null) {

            Log.d(TAG, "onStop : call stopLocationUpdates");
            mFusedLocationClient.removeLocationUpdates(locationCallback);
        }
    }




    public String getCurrentAddress(LatLng latlng) {

        //지오코더... GPS를 주소로 변환
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        List<Address> addresses;

        try {

            addresses = geocoder.getFromLocation(
                    latlng.latitude,
                    latlng.longitude,
                    1);
        } catch (IOException ioException) {
            //네트워크 문제
            Toast.makeText(this, "지오코더 서비스 사용불가", Toast.LENGTH_LONG).show();
            return "지오코더 서비스 사용불가";
        } catch (IllegalArgumentException illegalArgumentException) {
            Toast.makeText(this, "잘못된 GPS 좌표", Toast.LENGTH_LONG).show();
            return "잘못된 GPS 좌표";

        }


        if (addresses == null || addresses.size() == 0) {
            Toast.makeText(this, "주소 미발견", Toast.LENGTH_LONG).show();
            return "주소 미발견";

        } else {
            Address address = addresses.get(0);
            return address.getAddressLine(0).toString();
        }

    }


    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }


    public void setCurrentLocation(Location location, String markerTitle, String markerSnippet) {


        //if (currentMarker != null) currentMarker.remove();


        LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(currentLatLng);
        markerOptions.title("현위치 입니다.");
        markerOptions.snippet(markerSnippet);
        markerOptions.draggable(true);


        currentMarker = mMap.addMarker(markerOptions);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(currentLatLng, 15);
        mMap.animateCamera(cameraUpdate);

    }


    // TODO: 11/8/21  여기는 위치 기본 값
    public void setDefaultLocation() {


        //디폴트 위치, Seoul
        LatLng DEFAULT_LOCATION = new LatLng(37.56, 126.97);
        String markerTitle = "위치정보 가져올 수 없음";
        String markerSnippet = "위치 퍼미션과 GPS 활성 요부 확인하세요";


        if (currentMarker != null) currentMarker.remove();

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(DEFAULT_LOCATION);
        markerOptions.title(markerTitle);
        markerOptions.snippet(markerSnippet);
        markerOptions.draggable(true);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        currentMarker = mMap.addMarker(markerOptions);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(DEFAULT_LOCATION, 15);
        mMap.moveCamera(cameraUpdate);

    }

    // TODO: 11/9/21 시작 지점
    public void setStartLocation() {

        String[] spil_t = Lat_start.split(",");

        //디폴트 위치, Seoul
        START_LOCATION = new LatLng(Double.parseDouble(spil_t[0]), Double.parseDouble(spil_t[1]));
        //디폴트 위치, Seoul

        String markerTitle = "도봉산역 ";
        String markerSnippet = "여기는 시작 지점입니다.";


        //if (currentMarker != null) currentMarker.remove();

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(START_LOCATION);
        markerOptions.title(dulle_start);
        markerOptions.snippet(markerSnippet);
        markerOptions.draggable(true);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        currentMarker = mMap.addMarker(markerOptions);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(START_LOCATION, 15);
        mMap.moveCamera(cameraUpdate);

    }

    // TODO: 11/9/21 도착 지점
    public void setEndLocation() {

        String[] spil_t2 = Lat_end.split(",");
        //디폴트 위치, Seoul
        END_LOCATION = new LatLng(Double.parseDouble(spil_t2[0]), Double.parseDouble(spil_t2[1]));
        //디폴트 위치, Seoul

        String markerTitle = "당고개공원 갈림길";
        String markerSnippet = "여기는 도착 지점입니다.";


       // if (currentMarker != null) currentMarker.remove();

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(END_LOCATION);
        markerOptions.title(dulle_end);
        markerOptions.snippet(markerSnippet);
        markerOptions.draggable(true);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        currentMarker = mMap.addMarker(markerOptions);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(END_LOCATION, 15);
        mMap.moveCamera(cameraUpdate);

    }

    //여기부터는 런타임 퍼미션 처리을 위한 메소드들
    private boolean checkPermission() {
        Log.e("퍼미션 체크", "권한 체크 중");

        int hasFineLocationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);



        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED   ) {
            return true;
        }

        return false;

    }



    /*
     * ActivityCompat.requestPermissions를 사용한 퍼미션 요청의 결과를 리턴받는 메소드입니다.
     */
    @Override
    public void onRequestPermissionsResult(int permsRequestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grandResults) {

        super.onRequestPermissionsResult(permsRequestCode, permissions, grandResults);
        if (permsRequestCode == PERMISSIONS_REQUEST_CODE
                && grandResults.length == REQUIRED_PERMISSIONS.length) {

            // 요청 코드가 PERMISSIONS_REQUEST_CODE 이고, 요청한 퍼미션 개수만큼 수신되었다면

            boolean check_result = true;


            // 모든 퍼미션을 허용했는지 체크합니다.

            for (int result : grandResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }


            if (check_result) {

                // 퍼미션을 허용했다면 위치 업데이트를 시작합니다.
                startLocationUpdates();
            } else {
                // 거부한 퍼미션이 있다면 앱을 사용할 수 없는 이유를 설명해주고 앱을 종료합니다.2 가지 경우가 있습니다.
                Log.e("Permission_Denied","Permission_Denied");
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[1])) {


                    // 사용자가 거부만 선택한 경우에는 앱을 다시 실행하여 허용을 선택하면 앱을 사용할 수 있습니다.
                    Snackbar.make(mLayout, "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요. ",
                            Snackbar.LENGTH_INDEFINITE).setAction("확인", new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {

                            finish();
                        }
                    }).show();

                } else {


                    // "다시 묻지 않음"을 사용자가 체크하고 거부를 선택한 경우에는 설정(앱 정보)에서 퍼미션을 허용해야 앱을 사용할 수 있습니다.
                    Snackbar.make(mLayout, "퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다. ",
                            Snackbar.LENGTH_INDEFINITE).setAction("확인", new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {

                            finish();
                        }
                    }).show();
                }
            }

        }
    }


    //여기부터는 GPS 활성화를 위한 메소드들
    private void showDialogForLocationServiceSetting() {

        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityMap.this);
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"
                + "위치 설정을 수정하실래요?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent
                        = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case GPS_ENABLE_REQUEST_CODE:

                //사용자가 GPS 활성 시켰는지 검사
                if (checkLocationServicesStatus()) {
                    if (checkLocationServicesStatus()) {

                        Log.d(TAG, "onActivityResult : GPS 활성화 되있음");


                        needRequest = true;

                        return;
                    }
                }

                break;
        }
    }

    // TODO: 11/9/21 //Poly_Line_Draw
    String path;
    String path2;

    public void path_data() {
        Exam_data exam_data = new Exam_data();
        if (dulle_start.equals("도봉산역")&&dulle_end.equals("당고개공원 갈림길")) {
            path = exam_data.getDulle_1_1();
            options.color(Color.BLUE);
        } else if (dulle_start.equals("당고개공원 갈림길")&&dulle_end.equals("철쭉동산")) {
            path = exam_data.getDulle_1_2();
            options.color(Color.BLUE);
        } else if (dulle_start.equals("철쭉동산")&&dulle_end.equals("화랑대역")) {
            path = exam_data.getDulle_1_3();
            options.color(Color.BLUE);
        } else if (dulle_start.equals("화랑대역")&&dulle_end.equals("깔딱고개 쉼터(사가정 역)")) {
            path = exam_data.getDulle_2_1();
            options.color(Color.RED);
        } else if (dulle_start.equals("깔딱고개 쉼터(사가정 역)")&&dulle_end.equals("광나루역")) {
            path = exam_data.getDulle_2_2();
            options.color(Color.RED);
        } else if (dulle_start.equals("광나루역")&&dulle_end.equals("명일근린공원 입구")) {
            path = exam_data.getDulle_3_1();
            options.color(Color.BLACK);
        } else if (dulle_start.equals("명일근린공원 입구")&&dulle_end.equals("오금 1교")) {
            path = exam_data.getDulle_3_2();
            options.color(Color.BLACK);
        } else if (dulle_start.equals("오금 1교")&&dulle_end.equals("오금 1교")) {
            path = exam_data.getDulle_3_3();
            options.color(Color.BLACK);
        } else if (dulle_start.equals("수서역")&&dulle_end.equals("양재시민숲")) {
            path = exam_data.getDulle_4_1();
            options.color(Color.YELLOW);
        } else if (dulle_start.equals("양재시민숲")&&dulle_end.equals("사당역 갈림길")) {
            path = exam_data.getDulle_4_2();
            options.color(Color.YELLOW);
        }
        // TODO: 11/13/21 여기가 보여주기 식
        else if (dulle_start.equals("사당역 갈림길")&&dulle_end.equals("관악산 공원 입구")) {
            path = exam_data.getDulle_5_1();
            options.color(Color.GREEN);
            //path2 ="126.9818863213483,37.47597056036504,0 126.9818035473729,37.47623831809352";
        } else if (dulle_start.equals("관악산 공원 입구")&&dulle_end.equals("석수역")) {
            path = exam_data.getDulle_5_2();
            options.color(Color.GREEN);
            //path2 =exam_data.getExam_dulle_5();
        } else if (dulle_start.equals("석수역")&&dulle_end.equals("구일역")) {
            path = exam_data.getDulle_6_1();
            options.color(Color.BLACK);
        } else if (dulle_start.equals("구일역")&&dulle_end.equals("가양대교 남단")) {
            path = exam_data.getDulle_6_2();
            options.color(Color.BLACK);
        } else if (dulle_start.equals("가양대교 남단")&&dulle_end.equals("증산역 갈림길")) {
            path = exam_data.getDulle_7_1();
            options.color(Color.CYAN);
        } else if (dulle_start.equals("증산역 갈림길")&&dulle_end.equals("구파발역")) {
            path = exam_data.getDulle_7_2();
            options.color(Color.CYAN);
        } else if (dulle_start.equals("구파발역")&&dulle_end.equals("북한산 생태공원")) {
            path = exam_data.getDulle_8_1();
            options.color(Color.MAGENTA);
        } else if (dulle_start.equals("북한산 생태공원")&&dulle_end.equals("형제봉 입구")) {
            path = exam_data.getDulle_8_2();
            options.color(Color.MAGENTA);
        } else if (dulle_start.equals("형제봉 입구")&&dulle_end.equals("화계사 일주문")) {
            path = exam_data.getDulle_8_3();
            options.color(Color.MAGENTA);
        } else if (dulle_start.equals("화계사 일주문")&&dulle_end.equals("북한산 우이역")) {
            path = exam_data.getDulle_8_4();
            options.color(Color.MAGENTA);
        } else if (dulle_start.equals("북한산 우이역")&&dulle_end.equals("도봉산역")) {
            path = exam_data.getDulle_8_5();
            options.color(Color.MAGENTA);


            /** 여기서 부턴 코스별 구분*/
        }else if (dulle_start.equals("도봉산역") && dulle_end.equals("화랑대역")){
            path = exam_data.getDulle_1_1()+",0"+exam_data.getDulle_1_2()+",0"+exam_data.getDulle_1_3();
            options.color(Color.BLUE);
            path2 =exam_data.getDulle_1_1()+",0"+exam_data.getDulle_exam();
            // TODO: 11/13/21 여기서 빼오기
//            "현위치"->127.0868762596695,37.62213946756835,0 127.0870212659526,37.62185124518431,0 127.0869320614216,37.62168127987147,0 127.0867778527135,37.62155989094231,0 127.0865706754879,37.62147452625686,0 127.0864253684765,37.62135459589448,0 127.0861796894806,37.62128716322113,0 127.0854037515403,37.62098433536994,0 127.0851414422273,37.62092616335796,0 127.0848704776604,37.6207875398179,0  "도착위치"->127.084712434564,37.62070002518217
        }
        else if (dulle_start.equals("화랑대역") && dulle_end.equals("광나루역")){
            path = exam_data.getDulle_2_1()+",0"+exam_data.getDulle_2_2();
            options.color(Color.RED);
        }
        else if (dulle_start.equals("광나루역") && dulle_end.equals("수서역")){
            path = exam_data.getDulle_3_1()+",0"+exam_data.getDulle_3_2()+",0"+exam_data.getDulle_3_3();
            options.color(Color.BLACK);
        }
        else if (dulle_start.equals("수서역") && dulle_end.equals("사당역 갈림길")){
            path = exam_data.getDulle_4_1()+",0"+exam_data.getDulle_4_2();
            options.color(Color.YELLOW);

        }
        else if (dulle_start.equals("사당역 갈림길") && dulle_end.equals("석수역")){
            path = exam_data.getDulle_5_1()+",0"+exam_data.getDulle_5_2();
            options.color(Color.GREEN);
            path2 =exam_data.getDulle_5_1()+",0"+exam_data.getExam_dulle_5();
        }
        else if (dulle_start.equals("석수역") && dulle_end.equals("가양대교 남단")){
            path = exam_data.getDulle_6_1()+",0"+exam_data.getDulle_6_2();
            options.color(Color.BLACK);
        }
        else if (dulle_start.equals("가양대교 남단") && dulle_end.equals("구파발역")){
            path = exam_data.getDulle_7_1()+",0"+exam_data.getDulle_7_2();
            options.color(Color.CYAN);
        }
        else if (dulle_start.equals("구파발역") && dulle_end.equals("도봉산역")){
            path = exam_data.getDulle_8_1()+",0"+exam_data.getDulle_8_2()+",0"+exam_data.getDulle_8_3()+",0"+exam_data.getDulle_8_4()+",0"+exam_data.getDulle_8_5();
            options.color(Color.MAGENTA);
        }

        else {
            path = exam_data.getDulle_7_2();
        }

    }

    private void drawPath() {

        path_data();


        parse_Location(path);

//        for (int i =0; i<latLngArrayList.size(); i++){
//            options.add(latLngArrayList.get(i)).width(15).color(Color.BLACK).geodesic(true);
//            polylines.add(mMap.addPolyline(options));
//        }


        options.addAll(latLngArrayList);
        options.width(15);
        //options.color(Color.BLACK);

        polylines.add(mMap.addPolyline(options));
        Log.e("폴리라인", "그려지나요?");
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(START_LOCATION, 18));
    }

    // TODO: 11/9/21 위치 데이터 파싱
    private void parse_Location(String response) {

        String[] filt1 = response.split(",0");
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < filt1.length; i++) {

            //System.out.println(filt1[i]+"확인중"+i);
            arrayList.add(filt1[i]);

        }
        System.out.println("확인합니다 " + arrayList.size());

        ArrayList<String> arrayList2 = new ArrayList<>();
        ArrayList<String> arrayList3 = new ArrayList<>();

        for (int i = 0; i < arrayList.size(); i++) {
            //System.out.println("array get i" + arrayList.get(i));
            String[] filt2 = arrayList.get(i).split(",");

            //System.out.println("filt2" + filt2.length);
//
//            System.out.println(filt2[0]);
//            System.out.println(filt2[1]);
            arrayList2.add(filt2[0]);
            arrayList3.add(filt2[1]);
            double longitude = Double.parseDouble(arrayList2.get(i).toString());
            double latitude = Double.parseDouble(arrayList3.get(i).toString());
            latLngArrayList.add(new LatLng(latitude, longitude));



        }


    }
    private void drawPath2() {

        path_data();



        if (!path2.equals("")){


        parse_Location2(path2);
//        for (int i =0; i<latLngArrayList.size(); i++){
//            options.add(latLngArrayList.get(i)).width(15).color(Color.BLACK).geodesic(true);
//            polylines.add(mMap.addPolyline(options));
//        }


        options2.color(Color.RED);
        options2.addAll(my_load_ArrayList);
        options2.width(8);
        //options.color(Color.BLACK);

        polylines.add(mMap.addPolyline(options2));
        Log.e("폴리라인2", "그려지나요?2"); }

       /// mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(START_LOCATION, 18));
    }



    private void parse_Location2(String response) {

        String[] filt1 = response.split(",0");
        ArrayList<String> arrayList4 = new ArrayList<>();
        for (int i = 0; i < filt1.length; i++) {

            //System.out.println(filt1[i]+"확인중"+i);
            arrayList4.add(filt1[i]);

        }
        System.out.println("확인합니다 " + arrayList4.size());

        ArrayList<String> arrayList5 = new ArrayList<>();
        ArrayList<String> arrayList6 = new ArrayList<>();

        for (int i = 0; i < arrayList4.size(); i++) {
            //System.out.println("array get i" + arrayList.get(i));
            String[] filt2 = arrayList4.get(i).split(",");

            //System.out.println("filt2" + filt2.length);
//
//            System.out.println(filt2[0]);
//            System.out.println(filt2[1]);
            arrayList5.add(filt2[0]);
            arrayList6.add(filt2[1]);
            double longitude = Double.parseDouble(arrayList5.get(i).toString());
            double latitude = Double.parseDouble(arrayList6.get(i).toString());


            my_load_ArrayList.add(new LatLng(latitude, longitude));


        }


    }

    private void parse_Location3(String response) {

        String[] filt1 = response.split(",0");
        ArrayList<String> arrayList4 = new ArrayList<>();
        for (int i = 0; i < filt1.length; i++) {

            //System.out.println(filt1[i]+"확인중"+i);
            arrayList4.add(filt1[i]);

        }
        System.out.println("확인합니다 " + arrayList4.size());

        ArrayList<String> arrayList5 = new ArrayList<>();
        ArrayList<String> arrayList6 = new ArrayList<>();

        for (int i = 0; i < arrayList4.size(); i++) {
            //System.out.println("array get i" + arrayList.get(i));
            String[] filt2 = arrayList4.get(i).split(",");

            //System.out.println("filt2" + filt2.length);
//
//            System.out.println(filt2[0]);
//            System.out.println(filt2[1]);
            arrayList5.add(filt2[0]);
            arrayList6.add(filt2[1]);
            double longitude = Double.parseDouble(arrayList5.get(i).toString());
            double latitude = Double.parseDouble(arrayList6.get(i).toString());


            my_load_ArrayList2.add(new LatLng(latitude, longitude));


        }
    }

    private void showDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityMap.this);
        // builder.setTitle("걷기 완료");
        builder.setMessage(
                 "걷기를 완료하시겠습니까?");
        builder.setCancelable(true);
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent
                        = new Intent(getApplicationContext(),ActivityResultAfterWalk.class);
                callGPSSettingIntent.putExtra("courseNameString",courseNameString);
                callGPSSettingIntent.putExtra("time_results",results);
                startActivity(callGPSSettingIntent);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }
}