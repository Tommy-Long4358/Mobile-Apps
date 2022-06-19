package com.example.photocamerav0;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button takePhoto;
    ActivityResultLauncher<Intent> activityResultLauncher;
    private ImageView imageView;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get reference to imageView
        imageView = (ImageView) findViewById(R.id.picture);

        activityResultLauncher = registerForActivityResult(new
                ActivityResultContracts.StartActivityForResult(), new
                ActivityResultCallback<ActivityResult>() {
                    // Called when a user takes a picture
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            Bundle bundle = result.getData().getExtras();
                            Bitmap bitmap = (Bitmap) bundle.get("data");

                            imageView.setImageBitmap(bitmap);
                        }
                    }
                });

        // Check whether a device has a given feature (camera, bluetooth, gps, etc)
        PackageManager manager = this.getPackageManager();

        if (manager.hasSystemFeature(PackageManager.FEATURE_CAMERA))
        {
            // Toast to show that a camera exists and that the if statement executed
            Toast.makeText(MainActivity.this, "There is A CAMERA", Toast.LENGTH_SHORT).show();

            // Reference to a button of the photo taken
            takePhoto = findViewById(R.id.take_Photo);

            // Set up event handling
            takePhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Launch camera activity
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    if (intent.resolveActivity(getPackageManager()) != null) {
                        activityResultLauncher.launch(intent);
                    } else { Toast.makeText(MainActivity.this,
                            "There is no app that support this action",
                            Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}