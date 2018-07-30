package com.mayus.crowdwash_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /**
        Button debug = (Button)findViewById(R.id.debug);
        debug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent client = new Intent(Login.this, Client.class);
                startActivity(client);
            }
        }); **/


        Button signin = (Button)findViewById(R.id.button3);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("Klick");

                try {
                    //CrowdwashUser user = WebContent.getUserFromJson(WebContent.getLoginUser("Mayus", "TallerikIstKaka"));
                    String user = WebContent.getUserFromJson(WebContent.getLoginUser("Mayus", "TallerikIstKaka"));
                    System.out.println(user);

                } catch(Exception e) {
                    e.printStackTrace();
                }





                if(false) {
                Intent client = new Intent(Login.this, Client.class);
                startActivity(client);}
            }
        });
    }
}
