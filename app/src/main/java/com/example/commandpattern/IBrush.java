package com.example.commandpattern;

import android.graphics.Path;

/**
 * Created by W on 2018/12/17.
 */

public interface IBrush {
    /**
     * 触点接触时
     * @param path
     * @param x
     * @param y
     */
    void down(Path path,float x,float y);

    /**
     * 触点移动时
     * @param path
     * @param x
     * @param y
     */
    void move(Path path,float x,float y);

    /**
     * 触点离开时
     * @param path
     * @param x
     * @param y
     */
    void up(Path path,float x,float y);
}
