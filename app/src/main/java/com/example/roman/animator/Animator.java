package com.example.roman.animator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.List;

/**
 * Created by Roman on 2016-09-29.
 */

public class Animator extends SurfaceView implements SurfaceHolder.Callback {

    private DrawingThread drawingThread;
    private List<String> images;

    public Animator(Context context) {
        this(context, null);
    }

    public Animator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Animator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setZOrderOnTop(true);
        getHolder().addCallback(this);
        getHolder().setFormat(PixelFormat.TRANSPARENT);
        drawingThread = new DrawingThread(getHolder(), this);
    }

    public void setImages(List<String> images) {
        this.images = images;
        drawingThread.setImages(images);
        drawingThread.setRunning(true);
    }

    @Override
    public void draw(Canvas canvas) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawingThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        drawingThread.stopDraw();

        try {
            drawingThread.join();
        } catch (InterruptedException ignored) {
        }

        drawingThread = null;
    }
}
