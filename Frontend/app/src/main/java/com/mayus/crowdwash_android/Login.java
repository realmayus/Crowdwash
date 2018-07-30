package com.mayus.crowdwash_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    static Login login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        login = this;
        setContentView(R.layout.activity_login);


        Button debug = (Button)findViewById(R.id.debug);
        debug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent client = new Intent(Login.this, Client.class);
                startActivity(client);
            }
        });



    }

    public static Login getInstance() {
        return login;
    }
}
