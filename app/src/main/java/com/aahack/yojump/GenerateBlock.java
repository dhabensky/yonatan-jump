package com.aahack.yojump;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.aahack.yojump.gameobject.Block;

import java.util.Random;

public class GenerateBlock {
    //размеры человечка
    private int jonW = 100;
    private int jonH = 100;
    private int maxW = 300;
    private int maxJumpW = 150;
    private int maxJumpH = 200;
    private int lastX;
    private int lastY;
    private Random rand = new Random();
    private int screenH = getScreenHeight();

    public Block createBlock(){
        Block block = new Block();
        int blockW = randomNum(jonW, maxW);
        block.setW(blockW);
        block.setH(20);
        //нет метода, передающего цвет. Зачем кадлый раз передавать цвет, если все плашки будут черными?
        block.setDrawable(new ColorDrawable(Color.BLACK));
        block.setTag("block");
        if(lastX == 0 && lastY == 0) {
            int y = randomNum(0, screenH);
            block.getPos().set(0, y);
            lastX = blockW;
            lastY = y;
        } else {
            int x = randomNum(lastX, lastX + maxJumpW);
            int y = randomNum(lastY - maxJumpH, screenH);
            block.getPos().set(x, y);
            lastX = x + blockW;
            lastY = y;
        }
        return block;
    }

    private int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    private int randomNum(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }
}