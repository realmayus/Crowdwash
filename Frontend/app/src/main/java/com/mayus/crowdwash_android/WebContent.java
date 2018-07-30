package com.mayus.crowdwash_android;

import android.content.Intent;
import android.os.AsyncTask;
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

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public WebContent() {
        Button signin = (Button)Login.getInstance().findViewById(R.id.button3);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("Klick");

                try {
                    //CrowdwashUser user = WebContent.getUserFromJson(WebContent.getLoginUser("Mayus", "TallerikIstKaka"));
                    String user = getUserFromJson("Mayus", "TallerikIstKaka");
                    System.out.println(user);

                } catch(Exception e) {
                    e.printStackTrace();
                }





                if(false) {
                    Intent client = new Intent(Login.getInstance(), Client.class);
                    Login.getInstance().startActivity(client);}
            }
        });
    }

    /*public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {

    }
*/

/*
    public static StringBuilder getLoginUser(String name, String pw) {
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




        return res;

    }
*/

    public String getUserFromJson(String name, String pw) throws Exception{
           new WebContent().execute(new URL("http://tallerikyt.ddns.net:5000/login?user=" + name + "&pw=" + pw), null, null);



            if(obj.getBoolean("error")) {
                return "";
            } else {
                return obj.getString("SessionID");
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


}