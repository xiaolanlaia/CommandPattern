package com.example.commandpattern;

import android.graphics.Path;

/**
 * Created by W on 2018/12/17.
 */

public class CircleBrush implements IBrush {
    @Override
    public void down(Path path, float x, float y){

    }
    @Override
    public void move(Path path,float x,float y){
        path.addCircle(x,y,10,Path.Direction.CW);
    }
    @Override
    public void up(Path path,float x,float y){

    }
}
