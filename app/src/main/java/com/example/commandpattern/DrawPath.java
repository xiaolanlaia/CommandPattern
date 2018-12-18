package com.example.commandpattern;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by W on 2018/12/17.
 */

public class DrawPath implements IDraw{
    /**
     * 需要绘制的路径
     */
    public Path path;
    /**
     * 绘制画笔
     */
    public Paint paint;
    @Override
    public void draw(Canvas canvas){
        canvas.drawPath(path,paint);
    }

    @Override
    public void undo(){

    }
}
