package com.mayus.crowdwash_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.URL;

import static com.mayus.crowdwash_android.WebContent.web;

public class Login extends AppCompatActivity {

    static Login login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        login = this;
        setContentView(R.layout.activity_login);


        Button debug = findViewById(R.id.debug);
        debug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent client = new Intent(Login.this, Client.class);
                startActivity(client);
            }
        });
        Button signin = Login.getInstance().findViewById(R.id.button3);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    System.out.println("Klick!");
                    //...
                    try {
                        //CrowdwashUser user = WebContent.getUserFromJson(WebContent.getLoginUser("Mayus", "TallerikIstKaka"));
                        WebContent web = new WebContent();
                        String user = web.getUserFromJson("Mayus", "TallerikIstKaka");

                        System.out.println(user);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    if (false) {
                        Intent client = new Intent(Login.getInstance(), Client.class);
                        Login.getInstance().startActivity(client);
                    }


            }
        });



    }

    public static Login getInstance() {
        return login;
    }
}
