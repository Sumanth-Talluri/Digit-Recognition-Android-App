package com.example.cloudit;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.result.contract.ActivityResultContracts;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void captureImage(View view) {
        Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        captureImageActivityResult.launch(intent);
    }

    ActivityResultLauncher<Intent> captureImageActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Bitmap capturedImage = (Bitmap) result.getData().getExtras().get("data");
                Intent intent =  new Intent(MainActivity.this, ViewImageActivity.class);
                intent.putExtra("capturedImage",capturedImage);
                startActivity(intent);
            }
        }
    });

}