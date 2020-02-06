package com.sptek.facilitysupervision;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.sptek.facilitysupervision.cloudmessaging.CloudMessagingActivity;
import com.sptek.facilitysupervision.permission.SampleActivity;

public class FuncActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_func);

        Button btnPermission = findViewById(R.id.btn_permission);
        Button btnFcm = findViewById(R.id.btn_fcm);
        btnPermission.setOnClickListener(this);
        btnFcm.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_permission:
                startActivity(new Intent(this, SampleActivity.class));
                break;
            case R.id.btn_fcm:
                startActivity(new Intent(this, CloudMessagingActivity.class));
                break;
            default:
                break;
        }
    }
}
