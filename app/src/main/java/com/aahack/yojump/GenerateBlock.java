package com.aahack.yojump;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.aahack.yojump.gameobject.Block;
import com.aahack.yojump.gameobject.Scene;

import java.util.Random;

public class GenerateBlock {
    public Resources resources;
    public Scene scene;
    int bound = 200;
    int lastX;
    int lastY;
    int screenH = getScreenHeight();
    Random rand = new Random();
    int counter;

    public Block createBlock(){
        counter++;
        Block block = new Block(scene);
        block.setH(32);
        int blockW;
        int betweenBlocksX;
        if(lastX == 0 && lastY == 0) {
            blockW = 1800;
            int y = 750;
            block.getPos().set(0, y);
            lastX = blockW;
            lastY = y;
        } else {
            blockW = randomNum(300, 1000);
            betweenBlocksX = lastX + 150;
            int x = randomNum(betweenBlocksX, betweenBlocksX + 500);
            int y = randomNum(bound, screenH);
            /*if(counter %2 == 0) {
                y = randomNum(bound, screenH/2 -50);
            } else {
                y = randomNum(screenH/2 + 50, screenH - 50);
            }*/
            block.getPos().set(x, y);
            lastX = x + blockW;
            lastY = y;
        }

        if(blockW % 2 == 0) {
            block.setDrawable(new ColorDrawable(Color.BLACK));
        } else {
            block.setDrawable(new ColorDrawable(Color.BLUE));
            block.setDestructible(true);
        }
        block.setW(blockW);

        if (rand.nextInt(6) < 1) {
            scene.delayedAddObject(DhabenskyActivity.Companion.createCollectable(
                    resources,
                    rand.nextInt(DhabenskyActivity.Companion.getIds().length),
                    block.getPos().x + block.getW() / 2,
                    block.getPos().y - block.getH()
            ));
        }else {
        	if (rand.nextInt(3)<1){
				scene.delayedAddObject(DhabenskyActivity.Companion.createBurgers(
						resources,
						rand.nextInt(DhabenskyActivity.Companion.getBIds().length),
						rand.nextInt((int) ((block.getPos().x - block.getPos().x + block.getW()) + 1)) + block.getPos().x + block.getW(),
						block.getPos().y - block.getH()
				));
			}

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
