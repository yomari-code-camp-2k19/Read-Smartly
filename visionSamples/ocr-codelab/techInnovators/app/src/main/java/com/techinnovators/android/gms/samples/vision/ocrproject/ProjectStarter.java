package com.google.android.gms.samples.vision.ocrproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

public class ProjectStarter extends Activity {


    private final int SPLASH_DISPLAY_LENGTH = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_starter);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(ProjectStarter.this,HomePage.class);
//                ProjectStarter.this.startActivity(mai);
//                ProjectStarter.this.finish();
                startActivity(mainIntent);
                ProjectStarter.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
