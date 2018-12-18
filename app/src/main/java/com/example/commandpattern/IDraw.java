package com.example.commandpattern;

import android.graphics.Canvas;

/**
 * Created by W on 2018/12/17.
 */

public interface IDraw {
    /**
     * 绘制命令
     * @param canvas
     */
    void draw(Canvas canvas);

    /**
     * 撤销命令
     */
    void undo();
}
