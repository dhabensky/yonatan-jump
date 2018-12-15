package com.aahack.yojump;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import org.jetbrains.annotations.NotNull;

public class Player extends GameObject {

	public Drawable player;
	public int left, right, top, bottom;

	@Override
	public void render(@NotNull Canvas canvas) {
		super.render(canvas);

		player.setBounds(left, top, right, bottom);
		player.draw(canvas);
	}




}
