package com.demo.emergency;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    Intent login;
    Intent alert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SharedPreferences sharedPreferences = getSharedPreferences("USER_CREDENTIALS", MODE_PRIVATE);
        final String name = sharedPreferences.getString("NAME", "DEFAULT_NAME");
        Button logout = (Button) findViewById(R.id.logout_button_1);

        TextView welcometext = (TextView) findViewById(R.id.welcome_text);
        welcometext.setText("Welcome");
        login = new Intent(Main2Activity.this, LoginActivity.class);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().putBoolean("ISLOGGEDIN", false).commit();
                startActivity(login);
                finish();

            }
        });
        Button alert = (Button) findViewById(R.id.button2_1);
        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert();
            }
        });
    }

    public void alert() {
        Intent intent2=new Intent(Main2Activity.this,SMSActivity.class);
        startActivity(intent2);
    }

}
