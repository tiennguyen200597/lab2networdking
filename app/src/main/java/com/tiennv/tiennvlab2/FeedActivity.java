package com.tiennv.tiennvlab2;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.tiennv.tiennvlab2.http_request.HttpGetRequest;


public class FeedActivity extends AppCompatActivity {

    private TextView tvResult;
    String link = "http://asian.dotplays.com/wp-json/wp/v2/comments";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        tvResult = findViewById(R.id.tvResult);
        HttpGetRequest httpGetRequest = new HttpGetRequest(tvResult);
        httpGetRequest.execute(link);
    }
}
