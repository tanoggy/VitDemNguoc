package com.lencostudio.dew;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class FlashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_flash_screen);
        new Handler().postDelayed(new Runnable() {



            @Override
            public void run() {

                Intent i = new Intent(FlashScreen.this, MainActivity.class);
                startActivity(i);

                // kết thúc để chuyển sang màn hình tiếp theo
                finish();
            }
        }, 1500);

    }
}
