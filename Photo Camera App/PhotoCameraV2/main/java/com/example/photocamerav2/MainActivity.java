package com.example.photocamerav2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.nio.channels.SelectionKey;

public class MainActivity extends AppCompatActivity {
    private static String MA = "1";
    Button takePhoto;
    private BitmapGrayer grayer;
    ActivityResultLauncher<Intent> activityResultLauncher;
    private Bitmap bitmap;
    private ImageView imageView;
    private SeekBar redBar, greenBar, blueBar;
    private TextView redTV, greenTV, blueTV;
    Uri uri;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Reference to ImageView of the picture
        imageView = (ImageView)findViewById(R.id.picture);

        // Red, green, and blue seekbars references
        redBar = (SeekBar) findViewById(R.id.red_bar);
        greenBar = (SeekBar) findViewById(R.id.green_bar);
        blueBar = (SeekBar)findViewById(R.id.blue_bar);

        // Red, green, and blue bar TV
        redTV = (TextView) findViewById(R.id.red_tv);
        greenTV = (TextView) findViewById(R.id.green_tv);
        blueTV = (TextView) findViewById(R.id.blue_tv);

        // Set up event handling for seekbars
        GrayChangeHandler gch = new GrayChangeHandler();
        redBar.setOnSeekBarChangeListener(gch);
        greenBar.setOnSeekBarChangeListener(gch);
        blueBar.setOnSeekBarChangeListener(gch);

        activityResultLauncher = registerForActivityResult(new
        ActivityResultContracts.StartActivityForResult(), new
        ActivityResultCallback<ActivityResult>() {
            // Called when a user takes a picture
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Bundle bundle = result.getData().getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");

                    // "equal" coefficients for red, green, and blue
                    grayer = new BitmapGrayer(bitmap, 0.34f, 0.33f, 0.33f);

                    // grayscale bitmap
                    bitmap = grayer.grayScale();

                    // set ImageView as the grayscaled image
                    imageView.setImageBitmap(bitmap);
                }
            }
        });

        // Check whether a device has a given feature (camera, bluetooth, gps, etc)
        PackageManager manager = this.getPackageManager();

        if (manager.hasSystemFeature(PackageManager.FEATURE_CAMERA))
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
                    if (intent.resolveActivity(getPackageManager()) != null)
                    {
                        activityResultLauncher.launch(intent);
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,
                                "There is no app that support this action",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }

    /* Class to handle SeekBar event handling
        - Capture the event
        - Retrieve the value of each SeekBar
        - Update the model accordingly
        - Update the View accordingly when the user clicks on save
     */
    private class GrayChangeHandler implements SeekBar.OnSeekBarChangeListener {
        // Called continuously as the user moves the slider
        public void onProgressChanged(SeekBar seekBar,
                                      int progress, boolean fromUser) {

            // Test which SeekBar originated the event
            if (fromUser) {
                if (seekBar == redBar) {
                    // Update the model
                    grayer.setRedCoeff(progress / 100.0f);

                    // Enforces the constraint that the sum of the red, blue, and green coefficients must be less than or equal to 1
                    // If the SeekBar goes too far, it repositions the SeekBar based on its values
                    redBar.setProgress((int) (100 * grayer.getRedCoeff()));
                    redTV.setText( "" + MathRounding.keepTwoDigits(grayer.getRedCoeff( ) ) );

                } else if (seekBar == greenBar) {
                    grayer.setGreenCoeff(progress / 100.0f);
                    greenBar.setProgress((int) (100 * grayer.getGreenCoeff()));
                    greenTV.setText( "" + MathRounding.keepTwoDigits(grayer.getGreenCoeff( ) ) );

                } else if (seekBar == blueBar) {
                    grayer.setBlueCoeff(progress / 100.0f);
                    blueBar.setProgress((int) (100 * grayer.getBlueCoeff()));
                    blueTV.setText( ""+ MathRounding.keepTwoDigits(grayer.getBlueCoeff( ) ) );

                }

                bitmap = grayer.grayScale();
                imageView.setImageBitmap(bitmap);
            }
        }

        // Called when the user starts touching the slider
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        // Called when the user stops touching the slider
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }
}
