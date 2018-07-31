package com.mayus.crowdwash_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;

public class Client extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        try {
            ((TextView)findViewById(R.id.session_id)).setText(Login.loginstatus.getString("SessionID"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
