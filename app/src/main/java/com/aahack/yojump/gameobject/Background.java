package com.aahack.yojump.gameobject;

import android.graphics.Canvas;

import org.jetbrains.annotations.NotNull;

public class Background extends SpriteObject {

	float offset;

	@Override
	public void render(@NotNull Canvas canvas) {
		if (offset < -getW()) {
			offset += getW();
		}
		int l = (int) (getPos().x + offset);
		int t = (int) (getPos().y);

		getDrawable().setBounds(l, t, l + getW(), t + getH());
		getDrawable().draw(canvas);
		l += getW();
		getDrawable().setBounds(l, t, l + getW(), t + getH());
		getDrawable().draw(canvas);
	}

	@Override
	public void update(float delta) {
		super.update(delta);
		offset += -150f * delta;
	}

}
