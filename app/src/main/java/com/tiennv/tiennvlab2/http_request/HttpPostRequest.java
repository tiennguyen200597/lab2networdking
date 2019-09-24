package com.tiennv.tiennvlab2.http_request;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;


import com.tiennv.tiennvlab2.FeedActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpPostRequest extends AsyncTask<String, Long, String> {

    String link = "http://113.190.232.235:30100/idocNet.Test.Logistic.WMS/Services/Login?fbclid=IwAR1tv8Ct3cgqco0HCvOl1LDYZzFT48dLPJ74jI7H2EDZv9yQXKw1f2SeeJQ";
    String username,password;
    Context context;

    public HttpPostRequest(String username, String password, Context context) {
        this.username = username;
        this.password = password;
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL(link);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            //Init param for http POST
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("username=");
            stringBuilder.append(username);
            stringBuilder.append("&password=");
            stringBuilder.append(password);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            //set param to body of request
            bufferedWriter.append(stringBuilder);
            // Flush memory
            bufferedWriter.flush();
            // ENd
            bufferedWriter.close();
            outputStream.close();
            //Get data
            StringBuilder response = new StringBuilder();

            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
            }
            return response.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        JSONObject mainObject;
        String status = null;
        try {
            mainObject = new JSONObject(s);
            status = mainObject.getString("Success");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (status == "true"){
            Intent intent = new Intent(context.getApplicationContext(), FeedActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.getApplicationContext().startActivity(intent);
        }else{
            Toast.makeText(context.getApplicationContext(),"Login Failed" + s,Toast.LENGTH_LONG).show();
        }
    }
}
