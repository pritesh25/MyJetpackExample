package com.herba.sdk.jetpackexample.camerax2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraX;
import androidx.camera.core.FlashMode;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureConfig;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.core.PreviewConfig;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.herba.sdk.jetpackexample.R;
import com.herba.sdk.jetpackexample.camerax.AutoFitTextureView;

public class CameraXActivity2 extends AppCompatActivity {

    String permissions[] = {
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
    };
    CameraX.LensFacing lensFacing = CameraX.LensFacing.FRONT;
    //private String filename = "test.png";
    //private File sd = Environment.getExternalStorageDirectory();
    //private File dest = new File(sd, filename);
    private ImageCapture imageCapture = null;
    AutoFitTextureView textureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_x2);

        bindCamera();

        findViewById(R.id.fab_flash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FlashMode flashMode = imageCapture.getFlashMode();
                if (flashMode == FlashMode.ON) imageCapture.setFlashMode(FlashMode.OFF);
                else imageCapture.setFlashMode(FlashMode.ON);
            }
        });

        findViewById(R.id.fab_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                imageCapture.takePicture(new ImageCapture.OnImageCapturedListener() {
                    @Override
                    public void onCaptureSuccess(ImageProxy image, int rotationDegrees) {
                        super.onCaptureSuccess(image, rotationDegrees);
                        Log.v("Image", "Successfully saved image");
                    }

                    @Override
                    public void onError(ImageCapture.UseCaseError useCaseError, String message, @Nullable Throwable cause) {
                        super.onError(useCaseError, message, cause);
                        Log.e("Image", useCaseError.toString());
                    }
                });
            }
        });

        findViewById(R.id.fab_switch_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (CameraX.LensFacing.FRONT == lensFacing) {
                    lensFacing = CameraX.LensFacing.BACK;
                } else {
                    lensFacing = CameraX.LensFacing.FRONT;
                }
                bindCamera();
            }
        });
    }

    private void bindCamera() {

        CameraX.unbindAll();

        // Preview config for the camera
        PreviewConfig previewConfig = new PreviewConfig.Builder()
                .setLensFacing(lensFacing)
                .build();

        Preview preview = new Preview(previewConfig);

        // The view that displays the preview
        textureView = findViewById(R.id.view_finder);

        // Handles the output data of the camera
        preview.setOnPreviewOutputUpdateListener(new Preview.OnPreviewOutputUpdateListener() {
            @Override
            public void onUpdated(Preview.PreviewOutput output) {
                textureView.setSurfaceTexture(output.getSurfaceTexture());
            }
        });


        // Image capture config which controls the Flash and Lens
        ImageCaptureConfig imageCaptureConfig = new ImageCaptureConfig.Builder()
                .setTargetRotation(getWindowManager().getDefaultDisplay().getRotation())
                .setLensFacing(lensFacing)
                .setFlashMode(FlashMode.ON)
                .build();

        imageCapture = new ImageCapture(imageCaptureConfig);

        // Bind the camera to the lifecycle
        CameraX.bindToLifecycle(this, imageCapture, preview);
    }

    private boolean hasNoPermissions() {
        return ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED;
    }

    void requestPermission() {
        ActivityCompat.requestPermissions(this, permissions, 0);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (hasNoPermissions()) {
            requestPermission();
        }
    }
}
