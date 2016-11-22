package com.example.projectapplication.SpriteObjects;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by dustytrash on 11/6/2016.
 */

public abstract class GameObject
{
    /**
     * Draws the shape
     * @param gl OpenGLES object for drawing and rendering
     */
    public abstract void draw(GL10 gl);
}
