package com.example.commandpattern;

import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * 绘制画布
     */
    private DrawCanvas mCanvas;
    /**
     * 绘制路径
     */
    private DrawPath mPath;
    /**
     * 画笔对象
     */
    private Paint mPaint;

    /**
     *笔触对象
     */
    private IBrush mBrush;
    /**
     * 重做，撤销按钮
     */
    private Button btnRedo,btnUndo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPaint = new Paint();
        mPaint.setColor(0xFFFFFFFF);
        mPaint.setStrokeWidth(3);
        mBrush = new NormalBrush();
        mCanvas = (DrawCanvas)findViewById(R.id.draw_canvas);
        mCanvas.setOnTouchListener(new DrawTouchListener());

        btnRedo = (Button)findViewById(R.id.redo_btn);
        btnUndo = (Button)findViewById(R.id.undo_btn);
        btnRedo.setEnabled(false);
        btnUndo.setEnabled(false);
    }
    private class DrawTouchListener implements View.OnTouchListener{
        @Override
        public boolean onTouch(View v, MotionEvent event){
            if (event.getAction() == MotionEvent.ACTION_DOWN){
                mPath = new DrawPath();
                mPath.paint = mPaint;
                mPath.path = new Path();
                mBrush.down(mPath.path,event.getX(),event.getY());
            }else if (event.getAction() == MotionEvent.ACTION_MOVE){
                mBrush.move(mPath.path,event.getX(),event.getY());
            }else if (event.getAction() == MotionEvent.ACTION_UP){
                mBrush.up(mPath.path,event.getX(),event.getY());
                mCanvas.add(mPath);
                mCanvas.isDrawing = true;
                btnUndo.setEnabled(true);
                btnRedo.setEnabled(false);
            }
            return true;
        }
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.red_btn:
                mPaint = new Paint();
                mPaint.setStrokeWidth(3);
                mPaint.setColor(0xFFFF0000);
                break;
            case R.id.green_btn:
                mPaint = new Paint();
                mPaint.setStrokeWidth(3);
                mPaint.setColor(0xFF00FF00);
                break;
            case R.id.blue_btn:
                mPaint = new Paint();
                mPaint.setStrokeWidth(3);
                mPaint.setColor(0xFF0000FF);
                break;
            case R.id.undo_btn:
                mCanvas.undo();
                if (!mCanvas.canUndo()){
                    btnUndo.setEnabled(false);
                }
                btnRedo.setEnabled(true);
                break;
            case R.id.redo_btn:
                mCanvas.redo();
                if (!mCanvas.canRedo()){
                    btnRedo.setEnabled(false);
                }
                btnUndo.setEnabled(true);
                break;
            case R.id.circle_btn:
                mBrush = new CircleBrush();
                break;
            case R.id.normal_btn:
                mBrush = new NormalBrush();
                break;
            default:
                break;
        }
    }
}
