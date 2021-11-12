package com.example.seoulwalk.Activity;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Map_Kid_Receiver_Service extends Service {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public Map_Kid_Receiver_Service() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ClientThread clientThread = new ClientThread();
        clientThread.start();
        return START_STICKY;
    }
    class ClientThread extends Thread {
        @Override
        public void run() {
            // TODO: 10/10/21  ///HOSt NAME 변경해야됌
            String host = "10.0.2.2";

            int port = 5678;
            Socket socket=null;

            try {
                socket = new Socket(host, port);

                DataOutputStream outstream = new DataOutputStream(socket.getOutputStream());
                ///서버로 부터 값을 보내는 작압
                if (outstream != null) {
                    //name을 서버로 던지면서 소켓을 연결한다.
                    outstream.writeUTF("name");

                }
                //연결이 되었으면 이제 쓰레드를 이용해서 서버로 부터 데이터를 받는다.
                Receiverss receiver = new Receiverss(socket);
                receiver.start();


            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }


        }

    }

    public class Receiverss extends Thread{
        private Socket socket;
        private DataInputStream dis;

        public Receiverss(Socket socket) {
            this.socket = socket;

            try {
                dis = new DataInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            sharedPreferences = getSharedPreferences("shared",MODE_PRIVATE);
            editor = sharedPreferences.edit();
            while(dis != null) {
                try {
                    //System.out.println(dis.readUTF());
                    Log.e("받는 메세지",dis.readUTF());
                    editor.putString("Kid_Locaion",dis.readUTF());
                    editor.apply();
                    // -> Sender에서 writeUTF 호출 전까지 block,
                    //    writeUTF 호출되면 그때부터 read하기 시작
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            super.run();
        }
    }

}