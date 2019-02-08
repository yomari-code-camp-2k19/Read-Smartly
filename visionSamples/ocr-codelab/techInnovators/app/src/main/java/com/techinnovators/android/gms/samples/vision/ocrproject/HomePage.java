package com.google.android.gms.samples.vision.ocrproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomePage extends AppCompatActivity {

    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
//        Button camera_button = (Button) findViewById(R.id.camera_Button);
//        camera_button.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                Intent i = new Intent(HomePage.this, OcrCaptureActivity.class);
//                startActivity(i);
//            }
//        });
    }

    public void function(View v)
    {
        switch (v.getId())   // v is the button that was clicked
        {
            case (R.id.camera_open):
                 i = new Intent(HomePage.this, OcrCaptureActivity.class);
                startActivity(i);

                break;

            case (R.id.aboutus):
                 i = new Intent(HomePage.this, AboutUs.class);
                startActivity(i);

                break;

            default:
                System.out.print("");
                break;
        }
    }
}
