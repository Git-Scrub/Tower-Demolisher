package com.example.projectapplication;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import com.example.projectapplication.SpriteObjects.MenuItems.SinglePlayerButton;
import com.example.projectapplication.SpriteObjects.Pyramid;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Lionel on 11/4/2016.
 */
public class MainMenuGLRenderer implements GLSurfaceView.Renderer
{
    // Application context
    private Context context;
    private SinglePlayerButton singlePlayerButton;

    MainMenuGLRenderer(Context context)
    {
        this.context = context;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config)
    {
        // Clear everything. Set to a black background
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        gl.glEnable(GL10.GL_TEXTURE_2D);            //Enable Texture Mapping
        gl.glShadeModel(GL10.GL_SMOOTH);            //Enable Smooth Shading
        gl.glClearDepthf(1.0f);                     //Depth Buffer Setup

        //Really Nice Perspective Calculations
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);

        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glShadeModel(GL10.GL_SMOOTH);

        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        gl.glDisable(GL10.GL_DEPTH_TEST);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();

        gl.glViewport(0, 0, 500, 500);

        float left = 0;
        float right = 10;
        float top = 100;
        float bottom = 0;
        gl.glOrthof(left, right, top, bottom, -1, 1);

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();

        singlePlayerButton = new SinglePlayerButton(gl, this.context);
    }

    @Override
    public void onDrawFrame(GL10 gl)
    {
        // Clear everything
        gl.glClear(gl.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        // Draw out single player button
        this.singlePlayerButton.draw(gl);

    }

    /**
     * This will be called after onSurfaceCreated, or whenever the window is resized.
     * @param gl
     * @param width
     * @param height
     */
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height)
    {
        // We dont want to devide by zero.
        if(width < 1)
            width = 1;
        if(height < 1)
            height = 1;

        gl.glViewport(0, 0, width, height);

        float aspect = (float) width / height;

        // Set the viewport (display area) to cover the entire window
        gl.glViewport(0, 0, width, height);

        // Setup perspective projection, with aspect ratio matches viewport
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();

        // Use perspective projection
        GLU.gluPerspective(gl, 45, aspect, 0.1f, 100.f);

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
}
