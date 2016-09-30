package com.example.roman.animator;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.util.Log;
import android.util.LruCache;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Roman on 2016-09-30.
 */

public class DrawingThread extends Thread {
    public static final int ONE_FRAME_DURATION = 30;
    private int BYTES_IN_KBYTE = 1024;

    private SurfaceHolder sh;
    private SurfaceView sv;
    private boolean run;
    private Rect destRect;

    private LruCache<Integer, Bitmap> cache;
    private boolean stopped;
    private List<String> images;

    public DrawingThread(SurfaceHolder sh,SurfaceView sv) {
        this.sh = sh;
        this.sv = sv;

        cache = new LruCache<Integer, Bitmap>((int) ((Runtime.getRuntime().maxMemory() / BYTES_IN_KBYTE) / 4)) {
            @Override
            protected int sizeOf(Integer key, Bitmap value) {
                return value.getByteCount() / BYTES_IN_KBYTE;
            }
        };
    }

    public void setRunning(boolean run) {
        this.run = run;
    }

    @Override
    public synchronized void start() {
        super.start();
        destRect = new Rect(0,0,sv.getWidth(),sv.getHeight());
    }

    public void stopDraw() {
        run = false;
        stopped = true;
    }

    @Override
    public void run() {
        int iter = 0;
        while (!stopped) {
            if (!run) {
                continue;
            }
            long time = System.currentTimeMillis();
            iter = iter % images.size();
            Canvas c = sh.lockCanvas();
            draw(c, getBmp(iter));
            if (System.currentTimeMillis() < time + ONE_FRAME_DURATION) {
                try {
                    sleep(time + ONE_FRAME_DURATION - System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            sh.unlockCanvasAndPost(c);
            iter++;
        }
    }

    public void draw(Canvas canvas, Bitmap bmp) {
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        Log.i("DRAW", "width - " + bmp.getWidth() + " height - " + bmp.getHeight());
        canvas.drawBitmap(bmp, null, destRect, null);
    }

    public Bitmap getBmp(int i) {
        Bitmap bmp = cache.get(i);
        if (bmp == null) {
            bmp = getBitmapFromAsset(sv.getContext(), images.get(i), sv.getWidth(), sv.getHeight());
            int imageSize = bmp.getByteCount() / BYTES_IN_KBYTE;
            if (cache.size() + imageSize < cache.maxSize()) {
                cache.put(i, bmp);
            }
        }
        return bmp;
    }

    /**
     * @param context
     * @param filePath path filename OR directory/filename
     * @return
     */
    public static Bitmap getBitmapFromAsset(Context context, String filePath, int reqWidth, int reqHeight) {
        AssetManager assetManager = context.getAssets();

        InputStream istr;
        Bitmap bitmap = null;
        try {
            istr = assetManager.open(filePath);
            bitmap = decodeSampledBitmapFromResource(istr, reqWidth, reqHeight);
            istr.close();
        } catch (IOException ignored) {
        }

        return bitmap;
    }

    public static Bitmap decodeSampledBitmapFromResource(InputStream is, int reqWidth, int reqHeight) throws IOException {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        is.mark(Integer.MAX_VALUE);
        BitmapFactory.decodeStream(is, null, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        is.reset();
        return BitmapFactory.decodeStream(is, new Rect(), options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
