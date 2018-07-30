package com.mayus.crowdwash_android;

import android.support.annotation.Nullable;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class WebContent {

    public static String getLoginUser(String name, String pw) {
        BufferedReader br = null;
        StringBuilder res = new StringBuilder();
        String result;
        try {
            URL url = new URL("http://tallerikyt.ddns.net:5000/login?user=" + name + "&pw=" + pw);
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;

            while ((line = br.readLine()) != null) {
                res.append(line);
                res.append(System.lineSeparator());
            }


        } catch (Exception e) {
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                }
            }
        }




        return res.toString();
    }


    public static String getUserFromJson(String Json) throws Exception{


            JSONObject obj = new JSONObject(Json);

            if(obj.getBoolean("error")) {
                return "";
            } else {
                return obj.getString("SessionID");
            }










    }
}