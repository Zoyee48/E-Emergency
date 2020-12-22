package com.demo.emergency;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    Intent next_activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        next_activity=new Intent(this,MainActivity.class);
        final EditText name_field=(EditText)findViewById(R.id.Name);
        final EditText email_field=(EditText)findViewById(R.id.Email);
        final EditText password_field=(EditText)findViewById(R.id.Password);
        final EditText confirm_password_field=(EditText)findViewById(R.id.confirm_password);
        Button registerbutton=(Button)findViewById(R.id.register);
        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=name_field.getText().toString();
                String email_id=email_field.getText().toString();
                String password_1=password_field.getText().toString();
                String password_2=confirm_password_field.getText().toString();
                if(password_1.equals(password_2))
                {
                    //your code to register the user like an entry in the database
                    SharedPreferences sharedPreferences=getSharedPreferences("USER_CREDENTIALS",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("NAME",name);
                    editor.putString("EMAIL",email_id);
                    editor.putString("PASSWORD",password_1);
                    editor.putBoolean("ISLOGGEDIN",true);
                    editor.commit();
                    startActivity(next_activity);
                }
                else
                {
                    Toast.makeText(RegisterActivity.this,"Passwords don't match",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
