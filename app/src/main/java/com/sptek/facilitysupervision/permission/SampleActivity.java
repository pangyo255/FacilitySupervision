package com.sptek.facilitysupervision.permission;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.CompositeMultiplePermissionsListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.multi.SnackbarOnAnyDeniedMultiplePermissionsListener;
import com.karumi.dexter.listener.single.CompositePermissionListener;
import com.karumi.dexter.listener.single.DialogOnDeniedPermissionListener;
import com.karumi.dexter.listener.single.PermissionListener;
import com.karumi.dexter.listener.single.SnackbarOnDeniedPermissionListener;
import com.sptek.facilitysupervision.R;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class SampleActivity extends AppCompatActivity implements View.OnClickListener {
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
        createPermissionListeners();        // 권한 관련 리스너 세팅을 해줌
    }

    private void createPermissionListeners() {
        PermissionListener feedbackViewPermissionListener = new SamplePermissionListener(this);
        MultiplePermissionsListener feedbackViewMultiplePermissionListener =
                new SampleMultiplePermissionListener(this);

        allPermissionsListener =
                new CompositeMultiplePermissionsListener(feedbackViewMultiplePermissionListener,
                        SnackbarOnAnyDeniedMultiplePermissionsListener.Builder.with(contentView,
                                R.string.all_permissions_denied_feedback)
                                .withOpenSettingsButton(R.string.permission_rationale_settings_button_text)
                                .build());
        contactsPermissionListener = new CompositePermissionListener(feedbackViewPermissionListener,
                SnackbarOnDeniedPermissionListener.Builder.with(contentView,
                        R.string.contacts_permission_denied_feedback)
                        .withOpenSettingsButton(R.string.permission_rationale_settings_button_text)
                        .withCallback(new Snackbar.Callback() {
                            @Override public void onShown(Snackbar snackbar) {
                                super.onShown(snackbar);
                            }

                            @Override public void onDismissed(Snackbar snackbar, int event) {
                                super.onDismissed(snackbar, event);
                            }
                        })
                        .build());

        PermissionListener dialogOnDeniedPermissionListener =
                DialogOnDeniedPermissionListener.Builder.withContext(this)
                        .withTitle(R.string.audio_permission_denied_dialog_title)
                        .withMessage(R.string.audio_permission_denied_dialog_feedback)
                        .withButtonText(android.R.string.ok)
                        .withIcon(R.mipmap.ic_launcher)
                        .build();
        audioPermissionListener = new CompositePermissionListener(feedbackViewPermissionListener,
                dialogOnDeniedPermissionListener);
        cameraPermissionListener = new SampleBackgroundThreadPermissionListener(this);

        errorListener = new SampleErrorListener();
    }

    private void initView() {
        btnAllPermission = findViewById(R.id.all_permissions_button);
        btnCamera = findViewById(R.id.camera_permission_button);
        btnContacts = findViewById(R.id.contacts_permission_button);
        btnAudio = findViewById(R.id.audio_permission_button);
        contentView = findViewById(android.R.id.content);
        btnAllPermission.setOnClickListener(this);
        btnCamera.setOnClickListener(this);
        btnContacts.setOnClickListener(this);
        btnAudio.setOnClickListener(this);

        audioPermissionFeedbackView = findViewById(R.id.audio_permission_feedback);
        cameraPermissionFeedbackView = findViewById(R.id.camera_permission_feedback);
        contactsPermissionFeedbackView = findViewById(R.id.contacts_permission_feedback);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.all_permissions_button:
                // TODO: 2020-01-31 모든 권한 요청
                Dexter.withActivity(this)
                        .withPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_CONTACTS,
                                Manifest.permission.RECORD_AUDIO)
                        .withListener(allPermissionsListener)
                        .withErrorListener(errorListener)
                        .check();
                break;
            case R.id.camera_permission_button:
                // TODO: 2020-01-31 카메라 권한 요청
                Dexter.withActivity(SampleActivity.this)
                        .withPermission(Manifest.permission.CAMERA)
                        .withListener(cameraPermissionListener)
                        .withErrorListener(errorListener)
                        .onSameThread()
                        .check();
                break;
            case R.id.contacts_permission_button:
                // TODO: 2020-01-31 주소록 권한 요청
                Dexter.withActivity(this)
                        .withPermission(Manifest.permission.READ_CONTACTS)
                        .withListener(contactsPermissionListener)
                        .withErrorListener(errorListener)
                        .check();
                break;
            case R.id.audio_permission_button:
                // TODO: 2020-01-31 오디오 권한 요청
                Dexter.withActivity(this)
                        .withPermission(Manifest.permission.RECORD_AUDIO)
                        .withListener(audioPermissionListener)
                        .withErrorListener(errorListener)
                        .check();
                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void showPermissionRationale(final PermissionToken token) {
        new AlertDialog.Builder(this).setTitle(R.string.permission_rationale_title)
                .setMessage(R.string.permission_rationale_message)
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        token.cancelPermissionRequest();
                    }
                })
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        token.continuePermissionRequest();
                    }
                })
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override public void onDismiss(DialogInterface dialog) {
                        token.cancelPermissionRequest();
                    }
                })
                .show();
    }

    public void showPermissionGranted(String permission) {
        TextView feedbackView = getFeedbackViewForPermission(permission);
        feedbackView.setText(R.string.permission_granted_feedback);
        feedbackView.setTextColor(ContextCompat.getColor(this, R.color.permission_granted));
    }

    public void showPermissionDenied(String permission, boolean isPermanentlyDenied) {
        TextView feedbackView = getFeedbackViewForPermission(permission);
        feedbackView.setText(isPermanentlyDenied ? R.string.permission_permanently_denied_feedback
                : R.string.permission_denied_feedback);
        feedbackView.setTextColor(ContextCompat.getColor(this, R.color.permission_denied));
    }

    private TextView getFeedbackViewForPermission(String name) {
        TextView feedbackView;

        switch (name) {
            case Manifest.permission.CAMERA:
                feedbackView = cameraPermissionFeedbackView;
                break;
            case Manifest.permission.READ_CONTACTS:
                feedbackView = contactsPermissionFeedbackView;
                break;
            case Manifest.permission.RECORD_AUDIO:
                feedbackView = audioPermissionFeedbackView;
                break;
            default:
                throw new RuntimeException("No feedback view for this permission");
        }

        return feedbackView;
    }
}
