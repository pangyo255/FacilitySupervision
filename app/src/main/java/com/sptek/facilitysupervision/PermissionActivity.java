package com.sptek.facilitysupervision;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;

public class PermissionActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnAllPermission;
    Button btnCamera;
    Button btnContacts;
    Button btnAudio;
    TextView audioPermissionFeedbackView;
    TextView cameraPermissionFeedbackView;
    TextView contactsPermissionFeedbackView;
    View contentView;

    private MultiplePermissionsListener allPermissionsListener;
    private PermissionListener cameraPermissionListener;
    private PermissionListener contactsPermissionListener;
    private PermissionListener audioPermissionListener;
    private PermissionRequestErrorListener errorListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        initView();
    }

    private void initView() {
        btnAllPermission = findViewById(R.id.all_permissions_button);
        btnCamera = findViewById(R.id.camera_permission_button);
        btnContacts = findViewById(R.id.contacts_permission_button);
        btnAudio = findViewById(R.id.audio_permission_button);

        audioPermissionFeedbackView = findViewById(R.id.audio_permission_feedback);
        cameraPermissionFeedbackView = findViewById(R.id.camera_permission_feedback);
        contactsPermissionFeedbackView = findViewById(R.id.contacts_permission_feedback);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.all_permissions_button:
                // TODO: 2020-01-31 모든 권한 요청
                break;
            case R.id.camera_permission_button:
                // TODO: 2020-01-31 카메라 권한 요청
                break;
            case R.id.contacts_permission_button:
                // TODO: 2020-01-31 주소록 권한 요청
                break;
            case R.id.audio_permission_button:
                // TODO: 2020-01-31 오디오 권한 요청
                break;
        }
    }
}
