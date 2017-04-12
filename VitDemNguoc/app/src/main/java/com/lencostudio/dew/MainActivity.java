package com.lencostudio.dew;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import android.annotation.SuppressLint;
import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import android.support.v4.app.Fragment;
import android.view.View;
import android.view.WindowManager;

import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import static com.lencostudio.dew.R.id.tvFire;


public class MainActivity extends Activity {



    private static final String SHARED_PREFERENCES_NAME = "tvMAIN" ;
    private TextView tvDay, tvHour, tvMinute, tvSecond;
    public static TextView tvFirebase,txtCaunoi;
    public static TextView tvMain,tvTitle;
    private LinearLayout  linearLayout2;
    private Handler handler;
    private Runnable runnable;
    FirebaseDatabase database;
    public static  SharedPreferences sharedPreferences, sharedPreferences2;
    public static SharedPreferences.Editor editor, editor2;
    public static String dulieuMain, dulieuTitle;

    int[] source;
    int hinhNen;
    String[] cauNoi;
    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        if (1>2){
            Toast.makeText(this, "a", Toast.LENGTH_SHORT).show();
        }
        cauNoi = getResources().getStringArray(R.array.quotes);
       source =new int[] {

                R.drawable.vit0,
                R.drawable.vit2,
                R.drawable.vit3,
                R.drawable.vit4,
                R.drawable.vit5,
                R.drawable.vit6,
                R.drawable.vit7,
                R.drawable.vit8,
                R.drawable.vit9,
                R.drawable.vit10,


                R.drawable.vit11,
                R.drawable.vit12,
                R.drawable.vit13,
                R.drawable.vit14,
                R.drawable.vit15,
                R.drawable.vit16,
                R.drawable.vit17,

        };




        frameLayout = (FrameLayout) findViewById(R.id.mainActivity);
       /* hinhNen = (int)(Math.random() * source.length);
        frameLayout.setBackgroundResource(
                source[hinhNen]
        );*/


        initUI();

         sharedPreferences =
                getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);


        dulieuMain = sharedPreferences.getString("key_name5", null);
        dulieuTitle = sharedPreferences.getString("key_name6", null);

        if (dulieuMain == null)
        {
            tvMain.setText("Thời gian chuẩn bị trước khi thi");
        } else
        {
            tvMain.setText(dulieuMain);
        }



        if (dulieuTitle == null)
        {
            tvTitle.setText("Đếm ngược ngày thi THPT Quốc Gia");
        } else
        {
            tvTitle.setText(dulieuTitle);
        }





        database = FirebaseDatabase.getInstance();
        DatabaseReference mData = database.getReference("main");
        mData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot != null)
                {
                    tvFirebase.setText(dataSnapshot.getValue().toString());
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        countDownStart();

    }


    @SuppressLint("SimpleDateFormat")
    private void initUI() {

        linearLayout2 = (LinearLayout) findViewById(R.id.ll2);
        tvDay = (TextView) findViewById(R.id.txtTimerDay);
        tvHour = (TextView) findViewById(R.id.txtTimerHour);
        tvMinute = (TextView) findViewById(R.id.txtTimerMinute);
        tvSecond = (TextView) findViewById(R.id.txtTimerSecond);
        tvFirebase = (TextView) findViewById(tvFire);
        tvMain = (TextView) findViewById(R.id.tvMain);
        tvTitle = (TextView) findViewById(R.id.txTtile);
        txtCaunoi  = (TextView) findViewById(R.id.tvCauNoi);



    }

    // //////////////COUNT DOWN START/////////////////////////
    public void countDownStart() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                            "yyyy-MM-dd");
                    // Here Set your Event Date
                    Date eventDate = dateFormat.parse("2017-06-22");
                    Date currentDate = new Date();
                    if (!currentDate.after(eventDate)) {
                        long diff = eventDate.getTime()
                                - currentDate.getTime();
                        long days = diff / (24 * 60 * 60 * 1000);
                        diff -= days * (24 * 60 * 60 * 1000);
                        long hours = diff / (60 * 60 * 1000);
                        diff -= hours * (60 * 60 * 1000);
                        long minutes = diff / (60 * 1000);
                        diff -= minutes * (60 * 1000);
                        long seconds = diff / 1000;
                        tvDay.setText("" + String.format("%02d", days)+":");
                        tvHour.setText("" + String.format("%02d", hours)+":");
                        tvMinute.setText("" + String.format("%02d", minutes)+":");
                        tvSecond.setText("" + String.format("%02d", seconds));
                    } else {

                        linearLayout2.setVisibility(View.GONE);

                        handler.removeCallbacks(runnable);
                        // handler.removeMessages(0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 0);
    }

    public void showSetting(View view) {

        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent);
        

    }

    @Override
    protected void onResume() {
        super.onResume();
        hinhNen = (int)(Math.random() * source.length);
        frameLayout.setBackgroundResource(
                source[hinhNen]
        );

        String ranCauNoi = cauNoi[new Random().nextInt(cauNoi.length)];
        txtCaunoi.setText(ranCauNoi);

    }
// //////////////COUNT DOWN END/////////////////////////
}