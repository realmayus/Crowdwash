package com.mayus.crowdwash_android;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

public class WebContent extends AsyncTask<URL, Integer, JSONObject> {

    JSONObject obj;
    static WebContent web;
    static android.view.View.OnClickListener listener;

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

        public WebContent() {
            web = this;


        }


            //public String getUserFromJson(String name, String pw) throws Exception {
                /**WebContent webContent = new WebContent();
                 webContent.execute(new URL("http://tallerikyt.ddns.net:5000/login?user=" + "Mayus" + "&pw=" + "TallerikIstKaka"), null, null).get();
                 //new WebContent().execute(new URL("http://tallerikyt.ddns.net:5000/login?user=" + name + "&pw=" + pw), null, null);

                 if (obj.getBoolean("error")) {
                 return "";
                 } else {
                 return obj.getString("SessionID");
                 }**/
           // }
            /**
            public String getUserFromJson(String name, String pw) throws Exception {
            new WebContent().execute(new URL("http://tallerikyt.ddns.net:5000/login?user=" + name + "&pw=" + pw), null, null).get();
            if (obj.getBoolean("error")) {
                return "";
            } else {
                return obj.getString("SessionID");
            }**/

            public String getUserFromJson(String name, String pw) throws Exception {
                JSONObject data = new WebContent().execute(new URL("http://tallerikyt.ddns.net:5000/login?user=" + name + "&pw=" + pw), null, null).get();
                Log.d("From server" , data.toString());
                if (data.getBoolean("error")) {
                    return "";
                } else {
                    return data.getString("SessionID");
                }
            }




            @Override
            protected JSONObject doInBackground(URL... urls) {
                InputStream is = null;
                try {
                    is = urls[0].openStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                    String jsonText = readAll(rd);
                    System.out.println("Test" + jsonText);
                    JSONObject json = new JSONObject(jsonText);
                    obj = json;
                    return json;
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
            public static WebContent getWeb() { return web; }




}