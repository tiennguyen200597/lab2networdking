package com.tiennv.tiennvlab2.http_request;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class HttpGetRequest extends AsyncTask<String, Long, String> {

    TextView tvResult;

    public HttpGetRequest(TextView tvResult) {
        this.tvResult = tvResult;
    }

    @Override
    protected String doInBackground(String... strings) {
        String result = "";
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            while(scanner.hasNext()){
                result += scanner.nextLine();
            }
            scanner.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        tvResult.setText(s);
    }
}
