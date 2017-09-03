package com.a16mb.gpslocator.tracker;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, Runnable
{

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;
    }

    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                URL url = new URL("http://logitekprojects.com/gps_locator/reader.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoOutput(true);
                BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String msgLine, serverEcho = "";
                while((msgLine = br.readLine())!=null)
                {
                    serverEcho += msgLine;
                }
                Message msg = Message.obtain();
                msg.obj = serverEcho;
                mHandler.sendMessage(msg);
            }catch(Exception e){}
        }
    }

    Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            String empLocation = msg.obj.toString();
            int dataBreaker = empLocation.indexOf(",");
            if(dataBreaker != -1)
            {
                String s1 = empLocation.substring(0, dataBreaker);
                Log.d("MSG", s1);
                String s2 = empLocation.substring(dataBreaker + 1, dataBreaker+10);
                Log.d("MSG", s2);
                String s3 = empLocation.substring(dataBreaker + 11, dataBreaker+20);
                Log.d("MSG", s3);
                String s4 =empLocation.substring(dataBreaker +21,dataBreaker+22);
                Log.d("MSG",s4);
                String s5 =empLocation.substring(dataBreaker +23,dataBreaker+32);
                Log.d("MSG",s5);
                String s6 =empLocation.substring(dataBreaker +33,dataBreaker+42);
                Log.d("MSG",s6);
                String s7 =empLocation.substring(dataBreaker +43,dataBreaker+44);
                Log.d("MSG",s7);
                String s8 =empLocation.substring(dataBreaker +45,dataBreaker+54);
                Log.d("MSG",s8);
                String s9 =empLocation.substring(dataBreaker +55,dataBreaker+64);
                Log.d("MSG",s9);
                String s10 = empLocation.substring(dataBreaker +65, dataBreaker+66);
                Log.d("MSG", s10);
                String s11 = empLocation.substring(dataBreaker +67, dataBreaker+76);
                Log.d("MSG", s11);
                String s12 = empLocation.substring(dataBreaker +77, dataBreaker+86);
                Log.d("MSG", s12);
                String s13 =empLocation.substring(dataBreaker +87,dataBreaker+88);
                Log.d("MSG",s13);
                String s14 =empLocation.substring(dataBreaker +89,dataBreaker+98);
                Log.d("MSG",s14);
                String s15 =empLocation.substring(dataBreaker +99,dataBreaker+108);
                Log.d("MSG",s15);
                String s16 =empLocation.substring(dataBreaker +109,dataBreaker+110);
                Log.d("MSG",s16);
                String s17 =empLocation.substring(dataBreaker +111,dataBreaker+120);
                Log.d("MSG",s17);
                String s18 =empLocation.substring(dataBreaker +121,dataBreaker+130);
                Log.d("MSG",s18);


                try
                {
                    showOnMap(s1, Double.parseDouble(s2), Double.parseDouble(s3),s4, Double.parseDouble(s5), Double.parseDouble(s6),s7, Double.parseDouble(s8), Double.parseDouble(s9),s10, Double.parseDouble(s11), Double.parseDouble(s12),s13, Double.parseDouble(s14), Double.parseDouble(s15),s16, Double.parseDouble(s17), Double.parseDouble(s18));
                }
                catch (Exception e)
                {
                    Log.d("MSG", e.toString());
                }
            }
            super.handleMessage(msg);
        }
    };
    public void showOnMap(String id, double lat, double lon,String id2,double lat2, double lon2,String id3,double lat3, double lon3,String id4, double lat4, double lon4,String id5,double lat5, double lon5,String id6,double lat6,double lon6)
    {
        mMap.clear();
        LatLng location = new LatLng(lat, lon);
        mMap.addMarker(new MarkerOptions().position(location).title(id));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 17));

        LatLng location2 = new LatLng(lat2, lon2);
        mMap.addMarker(new MarkerOptions().position(location2).title(id2));

        LatLng location3 = new LatLng(lat3, lon3);
        mMap.addMarker(new MarkerOptions().position(location3).title(id3));

        LatLng location4 = new LatLng(lat4, lon4);
        mMap.addMarker(new MarkerOptions().position(location4).title(id4));

        LatLng location5 = new LatLng(lat5, lon5);
        mMap.addMarker(new MarkerOptions().position(location5).title(id5));

        LatLng location6 = new LatLng(lat6, lon6);
        mMap.addMarker(new MarkerOptions().position(location6).title(id6));

    }
}