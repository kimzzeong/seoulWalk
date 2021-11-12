package com.example.seoulwalk.Activity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.seoulwalk.R;

public class Notifi_Service extends Service {
    NotificationManager notificationManager;
    ServiceThread serviceThread;
    ServiceHandler serviceHandler;

    public Notifi_Service() {}

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "my_channel";
            String description = "this is my channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel =
                    new NotificationChannel("my_channel01", name, importance);
            channel.setDescription(description);
            channel.enableLights(true);
            channel.setLightColor(Color.WHITE);
            channel.enableVibration(true);
            //알림
            channel.setVibrationPattern(new long[] {100, 200, 300, 400,
                    500, 400, 300, 200, 400});
            channel.setShowBadge(false);
            notificationManager.createNotificationChannel(channel);
        }

        serviceHandler = new ServiceHandler();
        serviceThread = new ServiceThread(serviceHandler);
        serviceThread.setDaemon(true);
        serviceThread.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        serviceThread.stopThread();
        Log.d("hack4ork", "NetworkService 중지");
    }

    class ServiceHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
             if (msg.what == 1002) { // 날씨 정보 파싱 완료
                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(getApplicationContext(),
                                "my_channel01")
                                .setSmallIcon(R.drawable.course)
                                .setContentTitle("자녀 위치 알림!")
                                .setContentText("자녀의 위치가 이동되었습니다.");
//                ArrayList<WeatherData> arrayList = (ArrayList<WeatherData>)msg.obj;
                Intent intent = new Intent(getApplicationContext(),
                        ActivityMap.class);
                //intent.putExtra("WEATHER", arrayList);
                TaskStackBuilder stackBuilder =
                        TaskStackBuilder.create(getApplicationContext());
                stackBuilder.addParentStack(ActivityMap.class);
                stackBuilder.addNextIntent(intent);
                PendingIntent pendingIntent =
                        PendingIntent.getActivity(com.example.seoulwalk.Activity.Notifi_Service.this,
                                0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);
                notificationManager.notify(234, builder.build());
            }
        }
    }

    public class ServiceThread extends Thread {
        com.example.seoulwalk.Activity.Notifi_Service.ServiceHandler handler;
        boolean isRun = true;

        public ServiceThread(Handler handler) {
            this.handler = (com.example.seoulwalk.Activity.Notifi_Service.ServiceHandler) handler;
        }

        public void stopThread() {
            synchronized (this) {
                isRun = false;
            }
        }

        @Override
        public void run() {
            while (isRun) {
                try {
                    handler.sendEmptyMessage(1002);
                    Thread.sleep(1000 * 30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}