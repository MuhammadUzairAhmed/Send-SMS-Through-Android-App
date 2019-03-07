package com.example.muzair.sms_send;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button send;
    EditText e1,e2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send = (Button)findViewById(R.id.button);
        e1 = (EditText)findViewById(R.id.editText);
        e2 = (EditText)findViewById(R.id.editText2);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = e1.getText().toString();
                String sms = e2.getText().toString();
 try {
     SmsManager smsManager = SmsManager.getDefault();
     smsManager.sendTextMessage(number, null, sms, null, null);
     Toast.makeText(MainActivity.this,"sent!",Toast.LENGTH_SHORT).show();
 }
 catch (Exception e){
     Toast.makeText(MainActivity.this,"Failed!",Toast.LENGTH_SHORT).show();
 }


            }
        });
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.SEND_SMS)) {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.SEND_SMS}, 1);
            } else
            {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.SEND_SMS}, 1);
            }
        } else
        {
            //do nothing
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions,  int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS)
                            != PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this, "Permisiion Granted", Toast.LENGTH_SHORT).show();
                    }
                } else
                    {
                        Toast.makeText(this, "No Permisiion Granted", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }
}
