package com.example.cloudit;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class ViewImageActivity extends Activity {
    //Spinner dropDown;
    Bitmap selectedImage;
    int statusCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_image_page);

        Intent intent = getIntent();
        selectedImage = (Bitmap) intent.getParcelableExtra("capturedImage");
        ImageView imageView = findViewById(R.id.capturedImageView);
        imageView.setImageBitmap(selectedImage);

        /*dropDown = findViewById(R.id.spinner);

        String[] dropDownItems = new String[]{"Mobile", "iPod", "Laptop", "TV", "HeadPhones"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dropDownItems);
        dropDown.setAdapter(adapter);
        */
    }

    public void onUploadButtonClick(View view){
        String url = "http://localhost:5000";
        StringRequest uploadImageRequest = new StringRequest(Request.Method.POST,url, this::handleResponse, this::handleErrorResponse){
            protected Map<String,String> getParams() {
                Map<String,String> params = new HashMap<>();

                /*String selectedCategory = dropDown.getSelectedItem().toString();
                System.out.println(selectedCategory);
                params.put("category",selectedCategory);*/

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                byte[] imageBytes = byteArrayOutputStream.toByteArray();
                String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);

                params.put("imageString",imageString);
                return params;
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response){
                statusCode = response.statusCode;
                return super.parseNetworkResponse(response);
            }
        };

        RequestQueue uploadImageRequestQueue = Volley.newRequestQueue(ViewImageActivity.this);
        uploadImageRequestQueue.add(uploadImageRequest);
    }

    private void handleResponse(String response) {

        if(statusCode==200){

            //System.out.println(response);
            Toast.makeText(ViewImageActivity.this, "Predicted Number - "+response, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ViewImageActivity.this,MainActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(ViewImageActivity.this, "Oops!! Upload failed due to technical issues. Contact network administrator", Toast.LENGTH_LONG).show();
        }
    }

    private void handleErrorResponse(VolleyError error) {
        System.out.println(error.toString());
        Toast.makeText(ViewImageActivity.this,"Oops!! Upload failed due to technical issues",Toast.LENGTH_LONG).show();
    }

}
