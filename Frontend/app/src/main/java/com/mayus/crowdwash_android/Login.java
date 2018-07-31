package com.mayus.crowdwash_android;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.net.URL;

import static com.mayus.crowdwash_android.WebContent.web;

public class Login extends AppCompatActivity {

    static Login login;
    static JSONObject loginstatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        login = this;
        setContentView(R.layout.activity_login);


        TextView debug = findViewById(R.id.debug);
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
                        JSONObject user = web.getUserFromJson(((EditText)findViewById(R.id.editText4)).getText().toString(), ((EditText)findViewById(R.id.editText3)).getText().toString());
                        loginstatus = user;
                        System.out.println(user);

                        if(user.getBoolean("error")) {
                            System.out.println("Daten Falsch");
                            showWrongMSG();

                        } else {
                            System.out.println("Daten Richtig");

                            Intent client = new Intent(Login.getInstance(), Client.class);
                            Login.getInstance().startActivity(client);

                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    /*
                        Intent client = new Intent(Login.getInstance(), Client.class);
                        Login.getInstance().startActivity(client);
                    */


            }
        });



    }

    public static Login getInstance() {
        return login;
    }

    public void showWrongMSG() {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(Login.this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(Login.this);
        }
        builder.setTitle("Login Falsch")
                .setMessage("Bitte versuchen sie es erneut")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
