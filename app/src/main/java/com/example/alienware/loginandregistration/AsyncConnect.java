package com.example.alienware.loginandregistration;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Alienware on 22-01-2017.
 */

public class AsyncConnect extends AsyncTask<String,Void,String> {
    HttpURLConnection httpURLConnection;
    URL url;
    String give;
    JSONObject json;
    Context context;
    String link;
    AsyncConnect(Context c,String link,JSONObject json){
        context = c;
        this.link = link;
        this.json = json;
    }

    @Override
    protected String doInBackground(String... strings) {
        try{
            url = new URL(link);

            //httpURLConnection.setFixedLengthStreamingMode(json.toString().length());
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout( 10000 /*milliseconds*/ );
            httpURLConnection.setConnectTimeout( 15000 /* milliseconds */ );
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            httpURLConnection.connect();

            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
            bufferedOutputStream.write(json.toString().getBytes());
            bufferedOutputStream.flush();



            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            give = bufferedReader.readLine();
            bufferedReader.close();
            Toast.makeText(context,give,Toast.LENGTH_SHORT).show();

        }
        catch (Exception e){System.out.println(e);}
        finally {
            httpURLConnection.disconnect();
        }
        return give;
    }

    @Override
    protected void onPostExecute(String s) {
      Toast.makeText(context,give,Toast.LENGTH_SHORT).show();
    }
}
