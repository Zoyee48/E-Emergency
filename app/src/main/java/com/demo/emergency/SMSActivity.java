package com.demo.emergency;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.demo.loginsignup.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class SMSActivity extends AppCompatActivity {

    Button button, locButton;
    EditText editText, editText2;
    private FusedLocationProviderClient client;

    public static int requestcode=1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        client = LocationServices.getFusedLocationProviderClient(this);

        locButton = findViewById(R.id.locationButton);
        locButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(SMSActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){

                    return;
                }
                client.getLastLocation().addOnSuccessListener(SMSActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if(location!= null){
                            String loc=String.valueOf(location.getLatitude())+"   " +String.valueOf(location.getLongitude());
                            Toast.makeText(SMSActivity.this, loc ,Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        if (ContextCompat.checkSelfPermission(SMSActivity.this,
                Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(SMSActivity.this,
                    Manifest.permission.SEND_SMS)) {
                ActivityCompat.requestPermissions(SMSActivity.this,
                        new String[]{Manifest.permission.SEND_SMS}, 1);
            } else {
                ActivityCompat.requestPermissions(SMSActivity.this,
                        new String[]{Manifest.permission.SEND_SMS}, 1);
            }
        } else {
            //do nothing
        }

        button = (Button)findViewById(R.id.buttonl);
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = editText.getText().toString();
                //String sms = "welcome"; //fixed string to be sent
                String sms = editText2.getText().toString();
                //editText2.setText("Welcome" );

                try
                {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(number, null, sms, null, null);
                    Toast.makeText(SMSActivity.this, "Sent!", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(SMSActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(SMSActivity.this,
                            Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "No Permission Granted", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
    }
}
