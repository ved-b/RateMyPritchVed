package com.example.ratemypritch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;

public class captcha extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks {
    CheckBox cb;
    GoogleApiClient gpc;

    String siteKey = "6LdvD1EjAAAAAA1nkROyqiQA9MJTnN-Q3AAo8dzd";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captcha);

        cb = (CheckBox) findViewById(R.id.checkBox);


        gpc = new GoogleApiClient.Builder(this)
                .addApi(SafetyNet.API)
                .addConnectionCallbacks(captcha.this)
                .build();
        gpc.connect();

        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cb.isChecked()){
                    SafetyNet.SafetyNetApi.verifyWithRecaptcha(gpc, siteKey)
                            .setResultCallback(new ResultCallback<SafetyNetApi.RecaptchaTokenResult>() {
                                @Override
                                public void onResult(@NonNull SafetyNetApi.RecaptchaTokenResult recaptchaTokenResult) {
                                    Status status = recaptchaTokenResult.getStatus();
                                    if ((status != null) && status.isSuccess()) {
                                        //Display Successful Message
                                        Toast.makeText(captcha.this, "Account Succesfully Created!", Toast.LENGTH_SHORT).show();

                                        finish();
                                    }
                                }
                            });
                }
            }
        });
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}