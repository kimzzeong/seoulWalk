package com.example.seoulwalk.Activity;

import android.app.Service;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;
import android.widget.Toast;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.seoulwalk.data.Exam_data;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Map_service extends Service implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    FusedLocationProviderClient fusedLocationProviderClient;
    GoogleApiClient googleApiClient;
    double lat;
    double longt;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ArrayList<LatLng> latLngArrayList = new ArrayList<LatLng>();
    public Map_service() {
    }

    public double getLat() {
        return lat;
    }

    public double getLongt() {
        return longt;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        System.out.println("스타트 부분을 타고 잇나요?");

        check_permission();

        Location_map();

        Exam_data exam_data = new Exam_data();
        String abc =exam_data.getDulle_1_1();
        parse_Location(abc);


        return START_STICKY;
    }


    public void Location_map() {
        System.out.println("실행되나요?");
        googleApiClient = new GoogleApiClient.Builder(this).
                addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        googleApiClient.connect();
        ClientThread clientThread = new ClientThread();
        clientThread.start();

    }

    @Override
    public void onCreate() {
        super.onCreate();
        // System.out.println("서비스가 시작되고 있나요?");
        //check_permission();

    }


    public void check_permission() {

        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Log.e("권한 허용", "권한 허용");
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Log.e("권한 거부", "권한 거부");
            }

        };

        TedPermission.with(this).setPermissionListener(permissionListener)
                .setRationaleConfirmText("권한 필요")
                .setDeniedMessage("권한을 허용해 주세요")
                .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION
                        , Manifest.permission.ACCESS_COARSE_LOCATION)
                .check();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION);


        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {

            ;
        }
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(3000);
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, createLocationRequestCallback(), null);


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private LocationCallback createLocationRequestCallback() {
        return new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    lat = location.getLatitude();
                    longt = location.getLongitude();

                    // TODO: 10/10/21 위치 확인 할때 다시 열기
//                    Log.e("위치",String.valueOf(lat));
//                    Log.e("위치",String.valueOf(longt));


                }
            }
        };
    }

    class ClientThread extends Thread {
        //double a = latLng_data.getLat();

        String address;

        @Override
        public void run() {
            // TODO: 10/10/21 호스트 네임 변경하기
            String host = "10.0.2.2";
            sharedPreferences = getSharedPreferences("shared", MODE_PRIVATE);
            editor = sharedPreferences.edit();

            int port = 5678;
            Socket socket = null;

            try {
                socket = new Socket(host, port);

                DataOutputStream outstream = new DataOutputStream(socket.getOutputStream());
                ///서버로 부터 값을 보내는 작압
                if (outstream != null) {
                    outstream.writeUTF("자녀");

                }

                while (outstream != null) {

                    if (lat != 0.0 && longt != 0.0) {
                        System.out.println("실행되는지 좀 보자");
                        for (int i =0; i<latLngArrayList.size(); i++){
                            String a=String.valueOf(latLngArrayList.get(i).latitude);
                            String b=String.valueOf(latLngArrayList.get(i).longitude);
                            address = a+","+b;
                           // Thread.sleep(10000);
                            outstream.writeUTF(String.valueOf(address));
                            editor.putString("Kid_Locaion", String.valueOf(address));
                            editor.apply();
                            Log.e("DDDD", "서버로 보내는 값 :" + String.valueOf(address));
                            Thread.sleep(1000);
                        }
//                        address = getCurrentAddress(lat, longt);
                    } else {
                        address = "";
                        System.out.println("여기가 나오나?");
                    }

                }

//
                //outstream.flush();
                Log.e("ClientStream", "메세지를 보냈습니다.");


            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }

        }

    }

    public String getCurrentAddress(double latitude, double longitude) {
//
        //지오코더... GPS를 주소로 변환
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        List<Address> addresses;

        try {

            addresses = geocoder.getFromLocation(
                    latitude,
                    longitude,
                    7);
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

        }

        Address address = addresses.get(0);
        return address.getAddressLine(0).toString() + "\n";

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

}

