package com.example.seoulwalk.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seoulwalk.R;
import com.example.seoulwalk.adapter.Dulle1Adapter;
import com.example.seoulwalk.adapter.ImageSliderAdapter;
import com.example.seoulwalk.adapter.ReviewAdapter;
import com.example.seoulwalk.data.Exam_data;
import com.example.seoulwalk.data.Review_Data;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ActivityCourseInfo extends AppCompatActivity implements OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback, GoogleMap.OnMarkerClickListener {


    //탭 레이아웃을 레이아웃 보여줬다 안보여줬다로 만듦
    ConstraintLayout course_review_layout;
    ScrollView course_info_layout;
    boolean course_layout_flag = false;
    Button course_info_btn, course_review_btn;
    FloatingActionButton dulle_gil_walk; // 따라가기 플로팅버튼
    List<Polyline> polylines = new ArrayList<>();
    LatLng START_LOCATION;
    LatLng END_LOCATION;
    PolylineOptions options = new PolylineOptions();
    ArrayList<LatLng> latLngArrayList = new ArrayList<LatLng>();
    //뷰페이저
    private ViewPager2 sliderViewPager;
    private LinearLayout layoutIndicator;
    ImageView[] indicators;
    Button btn_M_Start,btn_M_End,btn_M_Map;

    private String[] images = new String[]{
            "https://i.imgur.com/36Bivob.jpeg", "https://i.imgur.com/oyFRppX_d.webp?maxwidth=1520&fidelity=grand", "https://i.imgur.com/wWNDVp6.jpeg", "https://i.imgur.com/GH67Dwj.jpeg"
    };

    //후기 리스트
    ArrayList<Review_Data> review_list = new ArrayList<>();

    //구글 맵
    private GoogleMap mMap;
    private Marker currentMarker = null;
    private static final String TAG = "googlemap_example";
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int UPDATE_INTERVAL_MS = 1000000;  // 1초
    private static final int FASTEST_UPDATE_INTERVAL_MS = 500; // 0.5초

    // onRequestPermissionsResult에서 수신된 결과에서 ActivityCompat.requestPermissions를 사용한 퍼미션 요청을 구별하기 위해 사용됩니다.
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    boolean needRequest = false;


    // 앱을 실행하기 위해 필요한 퍼미션을 정의합니다.
    String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};  // 외부 저장소


    Location mCurrentLocatiion;
    LatLng currentPosition;

    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest locationRequest;
    private Location location;
    private View mLayout;

    String dulle_start; //둘레길 시작점 이름
    String dulle_end; //둘레길 도착점 이름
    String Lat_start; //둘레길 도착점 이름
    String Lat_end; //둘레길 도착점 이름


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_course_info);

        /** 여기는 인테트로 시작점 끝점 받는 구간 */
        Intent intent = getIntent();
        dulle_start = intent.getStringExtra("dulle_start");
        dulle_end = intent.getStringExtra("dulle_end");
        Lat_start = intent.getStringExtra("LanLng");
        Lat_end = intent.getStringExtra("LatLng_end");
        System.out.println(dulle_start + "-- : 시작점 값 확인");
        System.out.println(dulle_end + "-- : 도착점 값 확인");
        System.out.println(Lat_start + "-- : 시작점 좌표 값 확인");
        System.out.println(Lat_end + "-- : 도착점 좌표 값 확인");

        /** 버튼 정의 */

        btn_M_Start = findViewById(R.id.map_star_btn);
        btn_M_End = findViewById(R.id.map_end_btn);
        btn_M_Map = findViewById(R.id.map_map_btn);

        btn_M_Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setStartLocation();
            }
        });

        btn_M_End.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEndLocation();
            }
        });

        btn_M_Map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = getCurrentAddress(currentPosition);
                System.out.println("현위치입니다.--> 지울것"+currentPosition);
                String b = getCurrentAddress(START_LOCATION);
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?saddr="+a+"&daddr="+b+""));
                startActivity(intent);
            }
        });

        course_review_layout = findViewById(R.id.course_review_layout);
        course_info_layout = findViewById(R.id.course_info_layout);

        course_info_btn = findViewById(R.id.course_info_btn);
        course_review_btn = findViewById(R.id.course_review_btn);

        dulle_gil_walk = findViewById(R.id.dulle_gil_walk);

        course_info_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                course_layout_flag = false;
                layoutChange(course_layout_flag);

            }
        });

        course_review_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                course_layout_flag = true;
                layoutChange(course_layout_flag);
            }
        });
// TODO: 11/10/21 지도 고정
        ImageView img_frag = findViewById(R.id.img_frag);
        ScrollView scrollView = findViewById(R.id.course_info_layout);
        img_frag.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        scrollView.requestDisallowInterceptTouchEvent(true);
                        // Disable touch on transparent view
                        return false;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        scrollView.requestDisallowInterceptTouchEvent(false);
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        scrollView.requestDisallowInterceptTouchEvent(true);
                        return false;

                    default:
                        return true;
                }
            }
        });


        //커스텀 액션바 세팅
        Toolbar toolbar;
        ActionBar actionBar;
        toolbar = findViewById(R.id.course_info_actionbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false); //뒤로가기 아이콘 없앰

        FloatingActionButton dulle_gil_walk = findViewById(R.id.dulle_gil_walk);

        mLayout = findViewById(R.id.layout_main);

        dulle_gil_walk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "따라걷기", Toast.LENGTH_SHORT).show();
            }
        });

        for (int i = 1; i <= 5; i++) {

            Review_Data review_data = new Review_Data("aaaa" + i);
            review_list.add(review_data);
            
        }

        RecyclerView review = findViewById(R.id.course_review_list);
        review.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ReviewAdapter reviewAdapter = new ReviewAdapter(review_list, this);
        review.setAdapter(reviewAdapter);

        locationRequest = new LocationRequest()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL_MS);
//                .setFastestInterval(FASTEST_UPDATE_INTERVAL_MS);

        LocationSettingsRequest.Builder builder =
                new LocationSettingsRequest.Builder();

        builder.addLocationRequest(locationRequest);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.course_map);
        mapFragment.getMapAsync(this);

        //저장되어있는 데이터들(구장정보, 이미지들) 불러올 것

        sliderViewPager = findViewById(R.id.sliderViewPager);
        layoutIndicator = findViewById(R.id.layoutIndicators);

        sliderViewPager.setOffscreenPageLimit(1);
        sliderViewPager.setAdapter(new ImageSliderAdapter(getApplicationContext(), images));

        sliderViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentIndicator(position);
            }
        });

        setupIndicators(images.length);
    }

    //레이아웃 변환(탭 레이아웃처럼)
    private void layoutChange(boolean course_layout_flag) {

        if (!course_layout_flag) {
            course_info_layout.setVisibility(View.VISIBLE);
            course_review_layout.setVisibility(View.GONE);
            dulle_gil_walk.setVisibility(View.VISIBLE);
        } else {
            course_info_layout.setVisibility(View.GONE);
            course_review_layout.setVisibility(View.VISIBLE);
            dulle_gil_walk.setVisibility(View.GONE);
        }
    }

    private void setupIndicators(int count) {
        indicators = new ImageView[count];
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params.setMargins(16, 8, 16, 8);

        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(this);
            indicators[i].setImageDrawable(ContextCompat.getDrawable(this,
                    R.drawable.bg_indicator_inactive));
            indicators[i].setLayoutParams(params);
            layoutIndicator.addView(indicators[i]);
        }
        setCurrentIndicator(0);
    }


    private void setCurrentIndicator(int position) {
        int childCount = layoutIndicator.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layoutIndicator.getChildAt(i);
            if (i == position) {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        this,
                        R.drawable.bg_indicator_active
                ));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        this,
                        R.drawable.bg_indicator_inactive
                ));
            }
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        Log.d(TAG, "onMapReady :");

        mMap = googleMap;

        //런타임 퍼미션 요청 대화상자나 GPS 활성 요청 대화상자 보이기전에
        //지도의 초기위치를 서울로 이동
        //setDefaultLocation();

        //마커 표시 && 폴리라인 그리기
        setStartLocation();
        setEndLocation();
        drawPath();

        //런타임 퍼미션 처리
        // 1. 위치 퍼미션을 가지고 있는지 체크합니다.
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);

        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {

            // 2. 이미 퍼미션을 가지고 있다면
            // ( 안드로이드 6.0 이하 버전은 런타임 퍼미션이 필요없기 때문에 이미 허용된 걸로 인식합니다.)

            startLocationUpdates(); // 3. 위치 업데이트 시작

        } else {  //2. 퍼미션 요청을 허용한 적이 없다면 퍼미션 요청이 필요합니다. 2가지 경우(3-1, 4-1)가 있습니다.

            // 3-1. 사용자가 퍼미션 거부를 한 적이 있는 경우에는
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])) {

                // 3-2. 요청을 진행하기 전에 사용자가에게 퍼미션이 필요한 이유를 설명해줄 필요가 있습니다.
                Snackbar.make(mLayout, "이 앱을 실행하려면 위치 접근 권한이 필요합니다.",
                        Snackbar.LENGTH_INDEFINITE).setAction("확인", new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        // 3-3. 사용자게에 퍼미션 요청을 합니다. 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                        ActivityCompat.requestPermissions(ActivityCourseInfo.this, REQUIRED_PERMISSIONS,
                                PERMISSIONS_REQUEST_CODE);
                    }
                }).show();

            } else {
                // 4-1. 사용자가 퍼미션 거부를 한 적이 없는 경우에는 퍼미션 요청을 바로 합니다.
                // 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }
        }


        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        // 현재 오동작을 해서 주석처리

        //mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {

                Log.d(TAG, "onMapClick :");
            }
        });
    }

    LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);

            List<Location> locationList = locationResult.getLocations();

            Log.e("최영완 로그", String.valueOf(locationList.size()));
            if (locationList.size() > 0) {
                location = locationList.get(locationList.size() - 1);
                //location = locationList.get(0);

                currentPosition
                        = new LatLng(location.getLatitude(), location.getLongitude());

                String markerTitle = "현 위치 : " + getCurrentAddress(currentPosition);
                /** 필요 없을 거 같은데 --> 나중에 길찾기 넣은면 될 듯  */
                String markerSnippet = "위도:" + String.valueOf(location.getLatitude())
                        + " 경도:" + String.valueOf(location.getLongitude());

                Log.d(TAG, "onLocationResult : " + markerSnippet);

//                String[] maker_title = getCurrentAddress(currentPosition).split("대한민국 서울특별시");
//                System.out.println("Marker"+maker_title[1]);

                //현재 위치에 마커 생성하고 이동
                setCurrentLocation(location, "현위치", null);

                mCurrentLocatiion = location;
            }

        }

    };

    private void startLocationUpdates() {

        if (!checkLocationServicesStatus()) {

            Log.d(TAG, "startLocationUpdates : call showDialogForLocationServiceSetting");
            showDialogForLocationServiceSetting();
        } else {

            int hasFineLocationPermission = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION);
            int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION);

            if (hasFineLocationPermission != PackageManager.PERMISSION_GRANTED ||
                    hasCoarseLocationPermission != PackageManager.PERMISSION_GRANTED) {

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
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "onStart");

        if (checkPermission()) {

            Log.d(TAG, "onStart : call mFusedLocationClient.requestLocationUpdates");
            mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);

            if (mMap != null)
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
//        if (currentMarker != null) currentMarker.remove();

        LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(currentLatLng);
        markerOptions.title(markerTitle);
        markerOptions.snippet(markerSnippet);
        markerOptions.draggable(true);

        currentMarker = mMap.addMarker(markerOptions);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(START_LOCATION, 15);
        mMap.animateCamera(cameraUpdate);
        mMap.moveCamera(cameraUpdate);

    }


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


    //여기부터는 런타임 퍼미션 처리을 위한 메소드들
    private boolean checkPermission() {

        int hasFineLocationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);

        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {
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
        if (permsRequestCode == PERMISSIONS_REQUEST_CODE && grandResults.length == REQUIRED_PERMISSIONS.length) {

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

    // TODO: 11/9/21 //Poly_Line_Draw
    String path;

    public void path_data() {
        Exam_data exam_data = new Exam_data();
        if (dulle_start.equals("도봉산역")) {
            path = exam_data.getDulle_1_1();
            options.color(Color.BLUE);
        } else if (dulle_start.equals("당고개공원 갈림길")) {
            path = exam_data.getDulle_1_2();
            options.color(Color.BLUE);
        } else if (dulle_start.equals("철쭉동산")) {
            path = exam_data.getDulle_1_3();
            options.color(Color.BLUE);
        } else if (dulle_start.equals("화랑대역")) {
            path = exam_data.getDulle_2_1();
            options.color(Color.RED);
        } else if (dulle_start.equals("깔딱고개 쉼터(사가정 역)")) {
            path = exam_data.getDulle_2_2();
            options.color(Color.RED);
        } else if (dulle_start.equals("광나루역")) {
            path = exam_data.getDulle_3_1();
            options.color(Color.BLACK);
        } else if (dulle_start.equals("명일근린공원 입구")) {
            path = exam_data.getDulle_3_2();
            options.color(Color.BLACK);
        } else if (dulle_start.equals("오금 1교")) {
            path = exam_data.getDulle_3_3();
            options.color(Color.BLACK);
        } else if (dulle_start.equals("수서역")) {
            path = exam_data.getDulle_4_1();
            options.color(Color.YELLOW);
        } else if (dulle_start.equals("양재시민숲")) {
            path = exam_data.getDulle_4_2();
            options.color(Color.YELLOW);
        } else if (dulle_start.equals("사당역 갈림길")) {
            path = exam_data.getDulle_5_1();
            options.color(Color.LTGRAY);
        } else if (dulle_start.equals("관악산 공원 입구")) {
            path = exam_data.getDulle_5_2();
            options.color(Color.LTGRAY);
        } else if (dulle_start.equals("석수역")) {
            path = exam_data.getDulle_6_1();
            options.color(Color.GREEN);
        } else if (dulle_start.equals("구일역")) {
            path = exam_data.getDulle_6_2();
            options.color(Color.GREEN);
        } else if (dulle_start.equals("가양대교 남단")) {
            path = exam_data.getDulle_7_1();
            options.color(Color.CYAN);
        } else if (dulle_start.equals("증산역 갈림길")) {
            path = exam_data.getDulle_7_2();
            options.color(Color.CYAN);
        } else {
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

    // TODO: 11/9/21 시작 지점
    public void setStartLocation() {

        String[] spil_t = Lat_start.split(",");

        //디폴트 위치, Seoul
        START_LOCATION = new LatLng(Double.parseDouble(spil_t[0]), Double.parseDouble(spil_t[1]));
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

        //onMarkerClick(currentMarker);
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(@NonNull Marker marker) {
                //Toast.makeText(ActivityCourseInfo.this, "AAAAa", Toast.LENGTH_SHORT).show();
                Bottom_SheetDialog bottomSheetDialog = new Bottom_SheetDialog();
                bottomSheetDialog.show(getSupportFragmentManager(), "myBottomSheetDialog");

            }
        });

//        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
//            @Nullable
//            @Override
//            public View getInfoContents(@NonNull Marker marker) {
//                return null;
//
//
//
//            }
//
//            @Nullable
//            @Override
//            public View getInfoWindow(@NonNull Marker marker) {
//                View infoWindow = getLayoutInflater().inflate(R.layout.info_window,
//                        findViewById(R.id.map), false);
//
//                ImageView info_img = ((ImageView) infoWindow.findViewById(R.id.img_info));
//                info_img.setImageResource(R.drawable.course);
//
//                TextView title = ((TextView) infoWindow.findViewById(R.id.title));
//                title.setText(marker.getTitle());
//
//                TextView snippet = ((TextView) infoWindow.findViewById(R.id.snippet));
//                snippet.setText(marker.getSnippet());
//
////                Button btn_start = ((Button) infoWindow.findViewById(R.id.btn_start));
////                btn_start.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View view) {
////                        Toast.makeText(ActivityCourseInfo.this, "Click", Toast.LENGTH_SHORT).show();
////                    }
////                });
////                Button btn_end = ((Button) infoWindow.findViewById(R.id.btn_end));
//
//
//                return infoWindow;
//            }
//        });


        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(START_LOCATION, 15);

        mMap.animateCamera(cameraUpdate);

    }

    // TODO: 11/9/21 도착 지점
    public void setEndLocation() {

        String[] spil_t2 = Lat_end.split(",");
        //디폴트 위치, Seoul
        END_LOCATION = new LatLng(Double.parseDouble(spil_t2[0]), Double.parseDouble(spil_t2[1]));
//        END_LOCATION = new LatLng(37.66800060666563, 127.083568905654);
        //String markerTitle = "당고개공원 갈림길";
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
        mMap.animateCamera(cameraUpdate);
        //mMap.moveCamera(cameraUpdate);

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        // 기존 뒤로 가기 버튼의 기능을 막기 위해 주석 처리 또는 삭제

        // 마지막으로 뒤로 가기 버튼을 눌렀던 시간에 2.5초를 더해 현재 시간과 비교 후
        // 마지막으로 뒤로 가기 버튼을 눌렀던 시간이 2.5초가 지났으면 Toast 출력
        // 2500 milliseconds = 2.5 seconds
        Intent intent = new Intent(ActivityCourseInfo.this, ActivityDulle.class); //지금 액티비티에서 다른 액티비티로 이동하는 인텐트 설정
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);    //인텐트 플래그 설정
        startActivity(intent);  //인텐트 이동
        finish();   //현재 액티비티 종료
    }


    //여기부터는 GPS 활성화를 위한 메소드들
    private void showDialogForLocationServiceSetting() {

        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityCourseInfo.this);
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

    // TODO: 11/10/21 마커 클릭 이벤트
    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {

        Toast.makeText(this, "클릭 이벤트 실험중 ", Toast.LENGTH_LONG).show();
        return true;
    }


}