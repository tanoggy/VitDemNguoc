package com.lencostudio.dew;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import static com.lencostudio.dew.MainActivity.sharedPreferences;


public class Main2Activity extends AppCompatActivity {
    ListView listView;
    TextView tx;
    FirebaseDatabase database;
    DatabaseReference mData;
    public static String FACEBOOK_URL = "https://www.facebook.com/VitDemNguoc";
    public static String FACEBOOK_PAGE_ID = "VitDemNguoc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.activity_main2);
        relativeLayout.setBackgroundResource(R.drawable.vit0);
        listView = (ListView) findViewById(R.id.lvsetting);
         tx = (TextView) findViewById(R.id.txTtile);
        database = FirebaseDatabase.getInstance();
         mData = database.getReference("message");


        final String arr[] = {  "Tác giả",
                "Facebook chính thức",
                "Gửi lời muốn nói ^-^",
                "Thay đổi dòng chữ trên đồng hồ",
                "Thay đổi dòng chữ dưới đồng hồ"

                };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, arr);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        showDialog();
                        break;
                    case 1:
                        Toast.makeText(Main2Activity.this, "Nhớ like để theo dõi", Toast.LENGTH_SHORT).show();
                        Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                        String facebookUrl = getFacebookPageURL(Main2Activity.this);
                        facebookIntent.setData(Uri.parse(facebookUrl));
                        startActivity(facebookIntent);
                        break;
                    case 2:
                        showDialog2();
                        break;
                    case 3:
                        showDialog4();
                        break;
                    case 4:
                        showDialog3();
                        break;
                }
            }
        });


    }
    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }


    public void showDialog ()
    {
        final android.support.v7.app.AlertDialog.Builder builder =
                new android.support.v7.app.AlertDialog.Builder(Main2Activity.this,
                        R.style.AppCompatAlertDialogStyle);
        builder.setMessage("Rồng Giật Điện - Coder Fa");
        builder.setCancelable(true);


        android.support.v7.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void showDialog2 ()
    {
        android.support.v7.app.AlertDialog.Builder dialogBuilder =
                new android.support.v7.app.AlertDialog.Builder(Main2Activity.this,
                        R.style.MyDialogTheme);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText edt = (EditText) dialogView.findViewById(R.id.edit1);

        dialogBuilder.setTitle("Gửi lời muốn nói");
        dialogBuilder.setMessage("Nhập vào bên dưới sau đó nhấn gửi");
        dialogBuilder.setPositiveButton("Gửi", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                mData.push().setValue(edt.getText().toString());
                //do something with edt.getText().toString();

            }
        });
        dialogBuilder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                mData.push().setValue(edt.getText().toString() + "+  ahuhu"); //pass
            }
        });
        android.support.v7.app.AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }
    public void showDialog3 ()
    {
        android.support.v7.app.AlertDialog.Builder dialogBuilder =
                new android.support.v7.app.AlertDialog.Builder(Main2Activity.this,
                        R.style.MyDialogTheme);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog2, null);
        dialogBuilder.setView(dialogView);

        final EditText edt = (EditText) dialogView.findViewById(R.id.edit1);


        dialogBuilder.setMessage("Muốn thay gì thì ghi ở dưới nha");
        dialogBuilder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                MainActivity.tvMain.setText(edt.getText().toString());
                //do something with edt.getText().toString();
                MainActivity.editor = sharedPreferences.edit();
                MainActivity.editor.putString("key_name5", edt.getText().toString());
                MainActivity.editor.apply();
            }
        });
        dialogBuilder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });
        android.support.v7.app.AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    public void showDialog4 ()
    {
        android.support.v7.app.AlertDialog.Builder dialogBuilder =
                new android.support.v7.app.AlertDialog.Builder(Main2Activity.this,
                        R.style.MyDialogTheme);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog2, null);
        dialogBuilder.setView(dialogView);

        final EditText edt = (EditText) dialogView.findViewById(R.id.edit1);


        dialogBuilder.setMessage("Muốn thay gì thì ghi ở dưới nha");
        dialogBuilder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                MainActivity.tvTitle.setText(edt.getText().toString());
                //do something with edt.getText().toString();
                MainActivity.editor2 = sharedPreferences.edit();
                MainActivity.editor2.putString("key_name6", edt.getText().toString());
                MainActivity.editor2.apply();
            }
        });
        dialogBuilder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });
        android.support.v7.app.AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    public void move(View view) {
        Intent intent = new Intent(Main2Activity.this, MainActivity.class);
        startActivity(intent);
    }

    public void donate(View view) {
    }
}
