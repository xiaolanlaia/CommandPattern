package com.example.commandpattern;


import android.graphics.Path;

/**
 * Created by W on 2018/12/17.
 */

public class NormalBrush implements IBrush {
    @Override
    public void down(Path path,float x,float y){
        path.moveTo(x,y);
    }
    @Override
    public void move(Path path,float x,float y){
        path.lineTo(x,y);
    }
    @Override
    public void up(Path path,float x,float y){

    }
}
