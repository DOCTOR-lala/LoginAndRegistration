package com.example.alienware.loginandregistration;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import org.json.JSONObject;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Alienware on 22-01-2017.
 */

public class AsyncConnect extends AsyncTask<String,Void,JSONObject> {
    HttpURLConnection httpURLConnection;
    URL url;
    String inputBuffer;
    StringBuilder serverResponse;
    JSONObject json;
    JSONObject toReturn;
    Context context;
    String link;

    public interface AsyncRevert{
        void getJsonResponse(JSONObject jsonObject);
    }

    public AsyncRevert asyncRevert = null;

    AsyncConnect(Context c,String link,JSONObject json,AsyncRevert asyncRevert){
        context = c;
        this.link = link;
        this.json = json;
        this.asyncRevert = asyncRevert;

    }

    @Override
    protected JSONObject doInBackground(String... strings) {
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


            serverResponse = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            while( (inputBuffer = bufferedReader.readLine())!=null){
            serverResponse.append(inputBuffer + "\n");
            }
            bufferedReader.close();
            inputBuffer = serverResponse.toString();
            System.out.println("String from server->"+inputBuffer);
            toReturn = new JSONObject(inputBuffer);


        }
        catch (Exception e){System.out.println(e);}
        finally {
            httpURLConnection.disconnect();
        }

        return toReturn;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        asyncRevert.getJsonResponse(jsonObject);
    }

}
