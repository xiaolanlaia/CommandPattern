package com.example.commandpattern;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by W on 2018/12/18.
 */

public class DrawCanvas extends SurfaceView implements SurfaceHolder.Callback {
    /**
     * 标识是否可以绘制，绘制线程是否可以运行
     */
    public boolean isDrawing,isRunning;
    /**
     * 绘制到的位图对象
     */
    private Bitmap bitmap;
    /**
     * 绘制命令对象
     */
    private DrawInvoker mInvoker;
    /**
     * 绘制线程
     */
    private DrawThread mThread;

    public DrawCanvas(Context context, AttributeSet attrs){
        super(context,attrs);

        mInvoker = new DrawInvoker();
        mThread = new DrawThread();

        getHolder().addCallback(this);

    }

    /**
     * 增加一条路径
     * @param path
     */
    public void add(DrawPath path){
        mInvoker.add(path);
    }

    /**
     * 重做上一步撤销的绘制
     */
    public void redo(){
        isDrawing = true;
        mInvoker.redo();
    }

    /**
     * 撤销上一步的绘制
     */
    public void undo(){
        isDrawing = true;
        mInvoker.undo();
    }

    /**
     * 是否可以撤销
     * @return
     */
    public boolean canUndo(){
        return mInvoker.canUndo();
    }

    /**
     * 是否可以重做
     * @return
     */
    public boolean canRedo(){
        return mInvoker.canRedo();
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder){
        isRunning = true;
        mThread.start();
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder,int format,int width,int height){
        bitmap = Bitmap.createBitmap(width,height,Bitmap.Config.RGB_565);

    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        isRunning = false;
        while(retry){
            try {
                mThread.join();
                retry = false;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public class DrawThread extends Thread {
        @Override
        public void run(){
            Canvas canvas = null;
            while (isRunning){
                if (isDrawing){
                    try {
                        canvas = getHolder().lockCanvas(null);
                        if (bitmap == null){
                            bitmap = Bitmap.createBitmap(1,1,Bitmap.Config.ARGB_8888);
                        }
                        Canvas c = new Canvas(bitmap);
                        c.drawColor(0, PorterDuff.Mode.CLEAR);
                        mInvoker.execute(c);
                        canvas.drawBitmap(bitmap,0,0,null);
                    }finally {
                        getHolder().unlockCanvasAndPost(canvas);
                    }
                    isDrawing = false;
                }
            }
        }
    }
}
