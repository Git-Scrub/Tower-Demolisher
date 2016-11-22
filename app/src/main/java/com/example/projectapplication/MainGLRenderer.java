package com.example.projectapplication;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import com.example.projectapplication.SpriteObjects.Pyramid;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Lionel on 11/4/2016.
 */
public class MainGLRenderer implements GLSurfaceView.Renderer
{
    // Application context
    private Context context;
    private float pyramidAngle = 0;

    // GameObject
    private Pyramid pyramid;

    MainGLRenderer(Context context)
    {
        this.context = context;
        this.pyramid = new Pyramid();
    }
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config)
    {
        // Clear everything. Set to a black background
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        // set depth clear-value to farthest
        gl.glClearDepthf(1.0f);
        // This enables depth-buffer for hidden surface removal
        gl.glEnable(gl.GL_DEPTH_TEST);

        gl.glDepthFunc(gl.GL_LEQUAL);

        // nice perspective view
        gl.glHint(gl.GL_PERSPECTIVE_CORRECTION_HINT, gl.GL_NICEST);

        // Enable smooth shading of color
        gl.glShadeModel(GL10.GL_SMOOTH);

        // Disable dithering for better performance
        gl.glDisable(GL10.GL_DITHER);
    }

    @Override
    public void onDrawFrame(GL10 gl)
    {
        // Clear everything
        gl.glClear(gl.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        // Reset the model-view matrix
        gl.glLoadIdentity();
        // Translate a little to the right
        gl.glTranslatef(0f, -0.0f, -8.0f);

        // Rotate the pyramid
        gl.glRotatef(this.pyramidAngle, 0.0f, 1.0f, -0.f);

        this.pyramidAngle += 2.0f;
        // Draw our pyramid
        this.pyramid.draw(gl);

        //gl.glLoadIdentity();
        //gl.glTranslatef(1.5f, 0.05f, -6.0f);
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

        gl.glMatrixMode(GL10.GL_MODELVIEW);  // Select model-view matrix
        gl.glLoadIdentity();                 // Reset
    }

    /**
     * Renders a stringle containing OpenGL's shading code language
     * @param type
     * @param shaderCode Code to compile
     * @return Shader to used for shading
     */
    public static int loadShader(int type, String shaderCode){

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }


}
