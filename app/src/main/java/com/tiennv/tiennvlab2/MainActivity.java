package com.tiennv.tiennvlab2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.tiennv.tiennvlab2.http_request.HttpPostRequest;


public class MainActivity extends AppCompatActivity {

    private EditText edtUsername,edtPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLogin();
            }
        });
    }

    private void initViews(){
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
    }

    private void onLogin() {
        HttpPostRequest httpPostRequest = new HttpPostRequest(edtUsername.getText().toString().trim(),edtPassword.getText().toString().trim(),MainActivity.this);
        httpPostRequest.execute();
    }

}
