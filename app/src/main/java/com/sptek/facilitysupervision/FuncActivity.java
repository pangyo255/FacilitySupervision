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
import com.sptek.facilitysupervision.restapi.RestApiTestActivity;
import com.sptek.facilitysupervision.temp.listview.MultipleListViewActivity;

public class FuncActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnPermission;
    private Button btnFcm;
    private Button btnRestApi;
    private Button btnMultipleListview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_func);

        btnPermission = findViewById(R.id.btn_permission);
        btnFcm = findViewById(R.id.btn_fcm);
        btnRestApi = findViewById(R.id.btn_restapi);
        btnMultipleListview = findViewById(R.id.btn_multiple_listview);

        btnPermission.setOnClickListener(this);
        btnFcm.setOnClickListener(this);
        btnRestApi.setOnClickListener(this);
        btnMultipleListview.setOnClickListener(this);

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
            case R.id.btn_restapi:
                startActivity(new Intent(this, RestApiTestActivity.class));
                break;
            case R.id.btn_multiple_listview:
                startActivity(new Intent(this, MultipleListViewActivity.class));
                break;
            default:
                break;
        }
    }
}
