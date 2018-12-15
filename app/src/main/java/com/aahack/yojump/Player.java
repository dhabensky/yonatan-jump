package com.aahack.yojump;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import org.jetbrains.annotations.NotNull;

public class Player extends GameObject implements JumpListener {

	public Drawable player;
	public int left, right, top, bottom;
	public int velocity = 0, gravity =5;
	int playerX, playerY;

	@Override
	public void render(@NotNull Canvas canvas) {
		super.render(canvas);

		player.setBounds(left, top, right, bottom);
		player.draw(canvas);

		velocity += gravity;
		playerY += velocity;
	}


	@Override
	public void onJump() {
		velocity = -40;
	}
}
