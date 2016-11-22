package com.example.projectapplication;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class MainActivity extends Activity {
    private GLSurfaceView glSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.glSurfaceView = new MainGLSurfaceView(this);
        this.glSurfaceView.setRenderer(new MainGLRenderer(this));
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