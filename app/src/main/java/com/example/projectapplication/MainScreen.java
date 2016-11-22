package com.example.projectapplication;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainScreen extends AppCompatActivity
{
    private GLSurfaceView glSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.glSurfaceView = new MainGLSurfaceView(this);
        this.glSurfaceView.setRenderer(new MainMenuGLRenderer(this));
       // glSurfaceView.setBackgroundResource(R.drawable.background1);

        this.setContentView(this.glSurfaceView);
    }

    @Override
    protected void onPause()
    {
        // Call default Activity's onPause
        super.onPause();
        // gl's onPause method
        glSurfaceView.onPause();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        glSurfaceView.onResume();
    }
}
