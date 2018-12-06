package com.example.rosa.finderseekers;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class CameraActivity extends AppCompatActivity {

    ImageView imageView;
    @Override
    protected void onCreate (Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_camera);

        Button btnCamera = (Button) findViewById(R.id.btnCamera);
        ImageView imageView = (ImageView)findViewById(R.id.imageView);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,1888);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);
        Intent intent = new Intent();
        intent.putExtra("bitmap", bitmap);
        setResult(SignUpActivity.CAMERA_RESULT,intent);



        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        Intent in1 = new Intent(this, SignUpActivity.class);
        in1.putExtra("bitmap",byteArray);
        finish();
    }
}
