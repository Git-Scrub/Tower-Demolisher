package com.example.projectapplication.SpriteObjects.MenuItems;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLU;
import android.opengl.GLUtils;
import android.util.Log;

import com.example.projectapplication.R;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by dustytrash on 11/22/2016.
 */

public class SinglePlayerButton extends MenuObject
{
    private final int IMAGE = R.drawable.test_image_1;
    int[] texture = new int[1];

    static int real_width = 0;
    static int real_height = 0;

    public SinglePlayerButton(GL10 gl, Context context)
    {
        Bitmap bmp = BitmapFactory.decodeStream(context.getResources().openRawResource(+IMAGE));

        real_width = bmp.getWidth();
        real_height = bmp.getHeight();

        gl.glGenTextures(1, texture, 0);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, texture[0]);

        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);

        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bmp, 0);
        bmp.recycle();

        int error = gl.glGetError();
        if (error != GL10.GL_NO_ERROR)
        {
            Log.e("ROLL", "GL Texture Load Error: " + GLU.gluErrorString(error));
        }
    }

    public void draw(GL10 gl)
    {
        this.draw(gl, 10, 10, real_width, real_height);
    }

    public void draw(GL10 gl, float x, float y, float w, float h)
    {
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        float[] vertices = { x + w, y + h, x, y + h, x, y, x + w, y };
        float[] textureCoords = { 1, 1, 0, 1, 0, 0, 1, 0 };

        ByteBuffer byteBuf = ByteBuffer.allocateDirect(vertices.length * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        FloatBuffer vertexBuffer = byteBuf.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        byteBuf = ByteBuffer.allocateDirect(textureCoords.length * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        FloatBuffer textureBuffer = byteBuf.asFloatBuffer();
        textureBuffer.put(textureCoords);
        textureBuffer.position(0);

        gl.glBindTexture(GL10.GL_TEXTURE_2D, texture[0]);

        gl.glVertexPointer(2, GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);

        gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 4);

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
    }
}
