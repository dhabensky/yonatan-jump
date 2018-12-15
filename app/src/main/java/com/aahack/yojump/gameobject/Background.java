package com.aahack.yojump.gameobject;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import org.jetbrains.annotations.NotNull;

public class Background extends SpriteObject {
	int x, y;
	public Drawable secondDrawable;

	public Background(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void render(@NotNull Canvas canvas) {
		super.render(canvas);

		int l = (int) (getPos().x);
		int t = (int) (getPos().y);

		if (getPos().x<x){
			secondDrawable.setBounds(getDrawable().getBounds().right, t, getDrawable().getBounds().right + getW(), t + getH());
			secondDrawable.draw(canvas);
		}
		if (getPos().x<-getW()){
			getPos().x += getW();
		}


	}
}
